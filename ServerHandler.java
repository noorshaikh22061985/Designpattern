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

/**
 * This class processes an API request and response.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class ServerHandler
{
    /**
     * this functon will generate a response based on the request object.
     *
     * @access public
     * @param  serverRequest API request object
     * @return ServerResponse
     */
    public ServerResponse process(ServerRequest serverRequest)
    {
        if (serverRequest == null)
        {
            return new ServerResponse(ResponseCodesEnum.POST_APP_BODY_REQUIRED);
        }

        serverRequest.sanitise();

        if (!serverRequest.isValid())
        {
            return new ServerResponse(ResponseCodeTracker.getResponseCodeEnum());
        }

        return serverRequest.process();
    }
}
