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
 * This is a class that creates an api log request
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class ApiLogRequest
{
    private String method;
    private String uri;
    private String querystring;

    private Map<String, String> headers;

    private int bodySize;

    /**
     * This function retrieves an API call method used.
     *
     * @access public
     * @return String method
     */
    public String getMethod()
    {
        return method;
    }

    /**
     * This function instantiates an API call method.
     *
     * @access public
     * @param  method API call method
     * @return void
     */
    public void setMethod(String method)
    {
        this.method = method;
    }

    /**
     * This function retrieves a API Uri.
     *
     * @access public
     * @return String uri
     */
    public String getUri()
    {
        return uri;
    }

    /**
     * This function instantiates a Uri.
     *
     * @access public
     * @param  uri API Resource identifier
     * @return void
     */
    public void setUri(String uri)
    {
        this.uri = uri;
    }

    /**
     * This function retrieves a query string.
     *
     * @access public
     * @return String query
     */
    public String getQuerystring()
    {
        return querystring;
    }

    /**
     * This function instantiates a query string.
     *
     * @access public
     * @param  querystring API call body
     * @return void
     */
    public void setQuerystring(String querystring)
    {
        this.querystring = querystring;
    }

    /**
     * This function retrieves mapped header information.
     *
     * @access public
     * @return Map headers
     */
    public Map<String, String> getHeaders()
    {
        return headers;
    }

    /**
     * This function instantiates a mapped headers object.
     *
     * @access public
     * @param  headers API header variables
     * @return void
     */
    public void setHeaders(Map<String, String> headers)
    {
        this.headers = headers;
    }

    /**
     * This function retrieves the API call body size.
     *
     * @access public
     * @return int bodySize
     */
    public int getBodySize()
    {
        return bodySize;
    }

    /**
     * This function instantiates the size of an API body.
     *
     * @access public
     * @param  bodySize API body size
     * @return void
     */
    public void setBodySize(int bodySize)
    {
        this.bodySize = bodySize;
    }
}
