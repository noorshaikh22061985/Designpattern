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

package co.wigroup.order.common.rest;

import co.wigroup.order.common.responsecodes.ResponseCodeTracker;
import co.wigroup.order.common.responsecodes.ResponseCodesEnum;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Basic utils class for working with REST.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class UtilsRest
{
    static final Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

    /**
     * This function is used by api request filter class to read json stream.
     *
     * @access public
     * @param  inputStream API payload
     * @return Response
     */
    public static String stringFromInputStream(InputStream inputStream)
    {
        BufferedReader br = null;
        StringBuilder requestBody = new StringBuilder();

        String line;
        try
        {
            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null)
            {
                requestBody.append(line);
            }
        }
        catch (IOException e)
        {
        }
        return requestBody.toString();
    }

    /**
     * This function constructs an API response.
     *
     * @accsess public
     * @param  apiResponse An API response
     * @return Response
     */
    public static Response buildRestResponse(ServerResponse apiResponse)
    {
        return Response.status(ResponseCodeTracker.getResponseCodeEnum().getHttpStatusCode()).entity(apiResponse).build();
    }

    /**
     * This function flags a failed API request.
     *
     * @access public
     * @param  servletResponse A server response object
     * @param  error           A response enum
     * @throws IOException
     * @return void
     */
    public static void failServletRequest(ServletResponse servletResponse, ResponseCodesEnum error) throws IOException
    {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setStatus(error.getHttpStatusCode().getStatusCode());
        
        ServerResponse apiResponse = new ServerResponse(error);
        httpResponse.getOutputStream().write(gson.toJson(apiResponse).getBytes());
    }
}
