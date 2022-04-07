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

import java.util.Map;

/**
 * This is a class that creates an api log response.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class ApiLogResponse
{
    private int   status;
    private Error error;
    private int   bodySize;
    private Map<String, String> headers;

    /**
     * This function retrieves a response status.
     *
     * @access public
     * @return int status
     */
    public int getStatus()
    {
        return status;
    }

    /**
     * This function instantiates a response status.
     *
     * @access public
     * @param  status API response status
     * @return void
     */
    public void setStatus(int status)
    {
        this.status = status;
    }

    /**
     * This function retrieves API response mapped headers.
     *
     * @access public
     * @return Map headers
     */
    public Map<String, String> getHeaders()
    {
        return headers;
    }

    /**
     * This function instantiates API response mapped headers.
     *
     * @access public
     * @param  headers Mapped API response headers
     * @return void
     */
    public void setHeaders(Map<String, String> headers)
    {
        this.headers = headers;
    }

    /**
     * This function retrieves API response errors.
     *
     * @access public
     * @return Error error
     */
    public Error getError()
    {
        return error;
    }

    /**
     * This function instantiates API response errors.
     *
     * @access public
     * @param  error
     * @return void
     */
    public void setError(Error error)
    {
        this.error = error;
    }

    /**
     * This function retrieves an API response body size.
     *
     * @access public
     * @return int bodySize
     */
    public int getBodySize()
    {
        return bodySize;
    }

    /**
     * This function instantiates the API response body size.
     *
     * @access public
     * @param  bodySize API call response body size
     * @return void
     */
    public void setBodySize(int bodySize)
    {
        this.bodySize = bodySize;
    }
}
