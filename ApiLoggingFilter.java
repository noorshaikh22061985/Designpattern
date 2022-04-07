/**
 * ********************************************************
 *                                           ::            *
 *           _  ____                         ,:            *
 * __      _(_)/ ___|_ __ ___  _   _ _ __    ::  ;:        *
 * \ \ /\ / / | |  _| '__/ _ \| | | | '_ \   ,:  ::        *
 *  \ V  V /| | |_| | | | (_) | |_| | |_) |  ,:  ::  :,    *
 *   \_/\_/ |_|\____|_|  \___/ \__,_| .__/   ,:  ::  :, :. *
 *                                  |_|      ,:  ::`;:.    *
 * Copyright (c) by wiGroup                  ,:  :::;.     *
 *                                           ,:`::;`       *
 *                                           ::::`         *
 *********************************************************
 */

package co.wigroup.order.common.rest.filter;

import co.wigroup.order.common.rest.ApiLogMessage;
import co.wigroup.order.common.rest.ApiLogRequest;
import co.wigroup.order.common.rest.ApiLogResponse;
import co.wigroup.order.common.rest.Latency;
import co.wigroup.order.common.rest.ServerResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.ws.rs.HttpMethod;

/**
 * Logs the request and response. Wraps the request and response messages so that we can attach headers
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class ApiLoggingFilter implements Filter
{
    private static final Logger log = LoggerFactory.getLogger(ApiLoggingFilter.class);

    private static final Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

    private static String hostAddress;
    private static String hostName;

    static
    {
        try
        {
            InetAddress localhost = InetAddress.getLocalHost();

            hostAddress = localhost.getHostAddress();
            hostName    = localhost.getHostName();

        }
        catch (UnknownHostException exception)
        {
            log.warn("unable to determine localhost name and address", exception);

            hostAddress = "UNRESOLVABLE";
            hostName    = "UNRESOLVABLE";
        }
    }

    /**
     * This function instantiates a filter object.
     *
     * @access public
     * @param  filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    /**
     * This function will log a response.
     *
     * @access public
     * @param  servletRequest  The servlet request object
     * @param  servletResponse The servlet response object
     * @param  filterChain     The filter parameters
     * @throws IOException
     * @throws ServletException
     * @return void
     */
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException
    {

        long startedAt = System.currentTimeMillis();

        ApiLogMessage logMsg = new ApiLogMessage();

        logMsg.setStartedAt(startedAt);
        logMsg.setHostAddress(hostAddress);
        logMsg.setHostName(hostName);

        HttpRequestWrapper httpRequest = new HttpRequestWrapper((HttpServletRequest) servletRequest);
        
        //Get HttpResponseWrapper, this will copy everything written to the outputstream.
        HttpResponseWrapper httpResponse = new HttpResponseWrapper((HttpServletResponse) servletResponse);

        //Retrieve the wiTraceId. IT SHOULD BE PROVIDED... but if not just set as empty string.
        String wiTraceId = httpRequest.getHeader("wi-trace-id");
        if (wiTraceId == null)
        {
            wiTraceId = "";
        }
        MDC.put("wiTraceId", wiTraceId);
        MDC.put("logType", LoggingTypes.PROCESS);

        //----------------------------------------------------------------------
        //Construct request
        //----------------------------------------------------------------------
        ApiLogRequest logRequest = this.buildApiLogRequest(httpRequest);

        logMsg.setRequest(logRequest);

        //----------------------------------------------------------------------
        // Continue with request
        //----------------------------------------------------------------------
        String responseBody = null;
        try
        {
            //forward down the chain of filters
            filterChain.doFilter(httpRequest, httpResponse);
            httpResponse.flushBuffer();
        }
        finally
        {
            //extract response body so that we can log it.
            responseBody = new String(httpResponse.getCopy(), httpResponse.getCharacterEncoding());
        }

        //----------------------------------------------------------------------
        //Construct response
        //----------------------------------------------------------------------
        ApiLogResponse logResponse = this.buildApiLogResponse(httpResponse, httpRequest, responseBody);
        
        logMsg.setResponse(logResponse);

        // Latency
        Latency latency = new Latency();
        logMsg.setLatency(latency);
        long completedAt = System.currentTimeMillis();
        logMsg.getLatency().setRequest(completedAt - startedAt);

        //===============================================================
        // Log the response 
        //===============================================================
        MDC.put("logType", LoggingTypes.API);
        log.info(gson.toJson(logMsg));

    }

    /**
     * This function destroys the log.
     *
     * @access public
     * @return void
     */
    @Override
    public void destroy()
    {
    }

    /**
     * This function builds an API request log.
     *
     * @access public
     * @param  httpRequest wrapped request body
     * @return ApiLogRequest
     */
    public ApiLogRequest buildApiLogRequest(HttpRequestWrapper httpRequest)
    {
        ApiLogRequest logRequest = new ApiLogRequest();
        logRequest.setMethod(httpRequest.getMethod());
        logRequest.setUri(httpRequest.getRequestURI());
        logRequest.setHeaders(httpRequest.getHeadersAsMap());
        logRequest.setQuerystring(httpRequest.getQueryString());
        int requestBodySize = httpRequest.getContentLength();
        logRequest.setBodySize(requestBodySize >= 0 ? requestBodySize : 0);

        return logRequest;
    }

    /**
     * This function builds an API response log.
     *
     * @access public
     * @param  httpResponse wrapped response
     * @param  httpRequest  wrapped request
     * @param  responseBody body of the request
     * @return ApiLogResponse
     */
    public ApiLogResponse buildApiLogResponse(HttpResponseWrapper httpResponse, HttpRequestWrapper httpRequest, String responseBody)
    {
        ApiLogResponse logResponse = new ApiLogResponse();
        logResponse.setStatus(httpResponse.getStatus());
        logResponse.setHeaders(httpResponse.getHeadersAsMap());
        int responseBodySize = responseBody.length();
        logResponse.setBodySize(responseBodySize >= 0 ? responseBodySize : 0);

        // An OPTIONS call does not return a ServerResponse as it's body
        if (!httpRequest.getMethod().equals(HttpMethod.OPTIONS))
        {
            ServerResponse serverResponse = gson.fromJson(responseBody, ServerResponse.class);
            if (serverResponse != null)
            {
                logResponse.setError(serverResponse.getError());
            }
        }

        return logResponse;
    }
}
