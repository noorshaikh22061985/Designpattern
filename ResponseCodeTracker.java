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

package co.wigroup.order.common.responsecodes;

import javax.ejb.Singleton;
import java.util.HashMap;

/**
 * Global response code tracker. When a error occurs of failed response must be set, it
 * is set for that threadId in this singleton.
 *
 * @access public
 * @author Phakamani Mavela (phakamnaim@wigroup.co.za)
 * @return void
 */
@Singleton
public class ResponseCodeTracker
{
    private static HashMap<Long, ResponseCodesEnum> threadIdToResponseCodeEnum = new HashMap<>();

    /**
     * Get reason for failure of method.
     *
     * @access public
     * @return {@link ResponseCodesEnum}
     */
    public static ResponseCodesEnum getResponseCodeEnum()
    {
        ResponseCodesEnum responseCodeEnum = threadIdToResponseCodeEnum.remove(Thread.currentThread().getId());
        return responseCodeEnum;
    }

    /**
     * This function will instantiate the reason for failure method
     *
     * @access public
     * @param  responseCodeEnum An enum of response codes
     * @return void
     */
    public static void setResponseCodeEnum(ResponseCodesEnum responseCodeEnum)
    {
        threadIdToResponseCodeEnum.put(Thread.currentThread().getId(), responseCodeEnum);
    }
}
