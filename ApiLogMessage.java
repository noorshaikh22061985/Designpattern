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

/**
 * This is a class that creates an api log message
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class ApiLogMessage
{
    private long startedAt;

    private String         hostAddress;
    private String         hostName;
    private Latency        latency;
    private ApiLogRequest  request;
    private ApiLogResponse response;

    /**
     * This function retrieves the location where a request started from.
     *
     * @access public
     * @return long startedAt
     */
    public long getStartedAt()
    {
        return startedAt;
    }

    /**
     * This function instantiates the location of where a request started from.
     *
     * @access public
     * @param  startedAt The location of where a request started from
     * @return void
     */
    public void setStartedAt(long startedAt)
    {
        this.startedAt = startedAt;
    }

    /**
     * This function retrieves the address a request originates from.
     *
     * @access public
     * @return String hostAddress
     */
    public String getHostAddress()
    {
        return hostAddress;
    }

    /**
     * This function instantiates the address a request originates from.
     *
     * @access public
     * @param  hostAddress Request origination
     * @return void
     */
    public void setHostAddress(String hostAddress)
    {
        this.hostAddress = hostAddress;
    }

    /**
     * This function retrieves the name of the host a request originates from.
     *
     * @access public
     * @return String hostName
     */
    public String getHostName()
    {
        return hostName;
    }

    /**
     * This function instantiate a request host name.
     *
     * @access public
     * @param  hostName
     * @return void
     */
    public void setHostName(String hostName)
    {
        this.hostName = hostName;
    }

    /**
     * This function retrieves a request latency.
     *
     * @access public
     * @return Latency latency
     */
    public Latency getLatency()
    {
        return latency;
    }

    /**
     * This function instantiates a request latency.
     *
     * @access public
     * @param  latency
     * @return void
     */
    public void setLatency(Latency latency)
    {
        this.latency = latency;
    }

    /**
     * This function retrieves a request.
     *
     * @access public
     * @return ApiLogRequest request
     */
    public ApiLogRequest getRequest()
    {
        return request;
    }

    /**
     * This function instantiates a request object.
     *
     * @access public
     * @param  request Contains contents of a request
     * @return void
     */
    public void setRequest(ApiLogRequest request)
    {
        this.request = request;
    }

    /**
     * This function retrieves a request response.
     *
     * @access public
     * @return ApiLogResponse response
     */
    public ApiLogResponse getResponse()
    {
        return response;
    }

    /**
     * This function instantiates a request response.
     *
     * @aacess public
     * @param  response Contains contents of a request
     * @return void
     */
    public void setResponse(ApiLogResponse response)
    {
        this.response = response;
    }
}
