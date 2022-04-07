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

import co.wigroup.order.common.responsecodes.ResponseCodesEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * Co-ordinates the API errors generated and how the user receives them.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class Error
{
    @ApiModelProperty(value = "System error code.")
    private String code;
    
    @ApiModelProperty(value = "A message to help the developer debug the error message.")
    private String faultMessage;
    
    @ApiModelProperty(value = "A friendly message to display to user.")
    private String displayMessage;

    /**
     * This function instantiates the API error variables.
     *
     * @access public
     * @param  responseCodeDescEnum Error response enum
     * @return void
     */
    public Error(ResponseCodesEnum responseCodeDescEnum)
    {
        this.code           = responseCodeDescEnum.getCode();
        this.faultMessage   = responseCodeDescEnum.getFaultMessage();
        this.displayMessage = responseCodeDescEnum.getDisplayMessage();
    }

    /**
     * This function retrieves the error code unique identifier.
     *
     * @access public
     * @return String code
     */
    public String getCode()
    {
        return code;
    }

    /**
     * This function instatiates the request/response error code
     *
     * @access public
     * @param  code API error unique identifier
     * @return void
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * This function retrieves a request/response fault message.
     *
     * @access public
     * @return String faultMessage
     */
    public String getFaultMessage()
    {
        return faultMessage;
    }

    /**
     * This function instantiates a request/response fault message.
     *
     * @access public
     * @param  faultMessage A recorded fault with the request/response
     * @return void
     */
    public void setFaultMessage(String faultMessage)
    {
        this.faultMessage = faultMessage;
    }

    /**
     * This function retrieves the error message to be displayed.
     *
     * @access public
     * @return String displayMessage
     */
    public String getDisplayMessage()
    {
        return displayMessage;
    }

    /**
     * This function instantiates a request/response user error.
     *
     * @access public
     * @param  displayMessage A recorded error made for end user consumption
     * @return void
     */
    public void setDisplayMessage(String displayMessage)
    {
        this.displayMessage = displayMessage;
    }

    /**
     * This function constructs an error string.
     *
     * @access public
     * @return String
     */
    @Override
    public String toString()
    {
        return "Error{" + "code=" + code + ", faultMessage=" + faultMessage + ", displayMessage=" + displayMessage + '}';
    }
}
