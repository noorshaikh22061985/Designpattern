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
 * Small class to hold the latency data.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class Latency
{
    private long request;

    /**
     * This function retrieves a API request.
     *
     * @access public
     * @return long request
     */
    public long getRequest()
    {
        return request;
    }

    /**
     * This function instantiates a API request object.
     *
     * @access public
     * @param  request API request object
     * @return void
     */
    public void setRequest(long request)
    {
        this.request = request;
    }
}
