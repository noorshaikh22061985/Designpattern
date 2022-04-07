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

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logs the request and response. Wraps the request and response messages so that we can attach headers
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class ApplicationWadlFilter implements Filter
{

    private static final Logger log = LoggerFactory.getLogger(ApplicationWadlFilter.class);

    @Override
    public void init(FilterConfig fc) throws ServletException
    {
    }

    /**
     * This function will filter a response.
     *
     * @access public
     * @param  request  The servlet request object
     * @param  response The servlet response object
     * @param  chain    The filter parameters
     * @throws IOException
     * @throws ServletException
     * @return void
     */
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {

        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        String path = httpRequest.getPathInfo();
        String method = httpRequest.getMethod();
        String full_url = httpRequest.getRequestURL().toString();
        String query = httpRequest.getQueryString();

        if (query != null)
        {
            full_url = full_url + "?" + query;
        }

        // WADL Request
        if (path != null && (path.startsWith("/application.wadl") || path.startsWith("/xsd"))
            && !path.endsWith(".class")
            && "GET".equals(method))
        {
            String resourcePath = "";
            if (httpRequest.getServletPath() != null && httpRequest.getServletPath().length() > 1)
            {
                resourcePath = httpRequest.getServletPath().substring(1) + path;
            }
            else
            {
                resourcePath = path.substring(1);
            }
            InputStream systemResourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);

            if (systemResourceAsStream == null)
            {
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getOutputStream().print("<BODY>");
                response.getOutputStream().print("Resource not found<BR>");
                response.getOutputStream().print("URL: " + full_url + "<BR>");
                response.getOutputStream().print("URI: " + httpRequest.getRequestURI() + "<BR>");
                response.getOutputStream().print("Context: " + httpRequest.getContextPath() + "<BR>");
                response.getOutputStream().print("Servlet: " + httpRequest.getServletPath() + "<BR>");
                response.getOutputStream().print("Path: " + httpRequest.getPathInfo() + "<BR>");
                response.getOutputStream().print("Query: " + httpRequest.getQueryString() + "<BR>");
                response.getOutputStream().print("LocalName: " + httpRequest.getLocalName() + "<BR>");
                response.getOutputStream().print("ServerName: " + httpRequest.getServerName() + "<BR>");
                response.getOutputStream().print("RemoteAddr: " + httpRequest.getRemoteAddr() + "<BR>");
                response.getOutputStream().print("RemoteHost: " + httpRequest.getRemoteHost() + "<BR>");
                response.getOutputStream().print("LocalAddr: " + httpRequest.getLocalAddr() + "<BR>");
                response.getOutputStream().print("LocalName: " + httpRequest.getLocalName() + "<BR>");
                response.getOutputStream().print("PathTranslated: " + httpRequest.getPathTranslated() + "<BR>");
                response.getOutputStream().print("Scheme: " + httpRequest.getScheme() + "<BR>");
                response.getOutputStream().print("Protocol: " + httpRequest.getProtocol() + "<BR>");
                response.getOutputStream().print("isSecure: " + httpRequest.isSecure() + "<BR>");
                response.getOutputStream().print("</BODY>");
                return;
            }

            byte[] streamData = new byte[1048576]; //1MB
            int bytesRead = 0;
            int totalBytesRead = 0;
            boolean loaded = false;
            while (!loaded)
            {
                try
                {
                    bytesRead = systemResourceAsStream.read(streamData, totalBytesRead, streamData.length - totalBytesRead);
                }
                catch (IOException iOException)
                {
                    break;
                }
                if (bytesRead == -1)
                {
                    loaded = true;
                    break;
                }

                totalBytesRead += bytesRead;

                if (bytesRead == streamData.length)
                {
                    log.warn("File requested is greater than 1MB");
                    break;
                }
            }

            if (loaded)
            {
                log.debug("Loaded {} bytes into stream buffer of size {}", totalBytesRead, streamData.length);
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);

                String body = new String(streamData, 0, totalBytesRead);

                boolean secure = false;

                if ("https".equals(httpRequest.getScheme()))
                {
                    secure = true;
                }
                else if (httpRequest.getHeader("x-forwarded-proto") != null)
                {
                    secure = httpRequest.getHeader("x-forwarded-proto").contains("https");
                }

                String scheme;

                // Jetty doesn't seem to report the scheme used correctly,
                // so adapt based on configuration
                if (secure)
                {
                    scheme = "https";
                }
                else
                {
                    scheme = "http";
                }

                String context = httpRequest.getContextPath() != null ? httpRequest.getContextPath() : "";

                String baseUri
                       = scheme + "://"
                         + httpRequest.getServerName() + ":"
                         + httpRequest.getServerPort()
                         + context;

                body = body.replaceAll("__SERVER_LOCATION__", baseUri);
                response.getOutputStream().write(body.getBytes());
                return;
            }
            else
            {
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getOutputStream().print("<BODY>");
                response.getOutputStream().print("Unable to serve file<BR>");
                response.getOutputStream().print("URL: " + full_url + "<BR>");
                response.getOutputStream().print("URI: " + ((HttpServletRequest) request).getRequestURI() + "<BR>");
                response.getOutputStream().print("Context: " + ((HttpServletRequest) request).getContextPath() + "<BR>");
                response.getOutputStream().print("Servlet: " + ((HttpServletRequest) request).getServletPath() + "<BR>");
                response.getOutputStream().print("Query: " + ((HttpServletRequest) request).getQueryString() + "<BR>");
                response.getOutputStream().print("</BODY>");
                return;
            }
        }

        chain.doFilter(request, response);
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
}
