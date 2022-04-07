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

import io.swagger.annotations.ApiModel;

/**
 * This class generates a API response.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @retrun void
 */
@ApiModel(value = "ServerResponse", description = "API REST response object")
public class ServerResponse
{
    private Error error;

    /**
     * This function constructs an API response.
     *
     * @access public
     * @param responseCode Enum with a response code
     * @return void
     */
    public ServerResponse(ResponseCodesEnum responseCode)
    {
        if (responseCode != null)
        {
            ResponseCodeTracker.setResponseCodeEnum(responseCode);
            
            // only set the error message if the response is an error message
            if (responseCode.getErrorCode() >= 0)
            {
                this.error = new Error(responseCode);
            }
        }
        else
        {
            // this is a safety check for in case the response code has not been set
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_GENERAL_NO_RESPONSECODE);
        }
    }

    /**
     * This function retrieves a request error.
     *
     * @access public
     * @return Error error
     */
    public Error getError()
    {
        return error;
    }

    /**
     * This function instantiates an error object.
     *
     * @access public
     * @param  error An error object
     * @return Error error
     */
    public void setError(Error error)
    {
        this.error = error;
    }

}
