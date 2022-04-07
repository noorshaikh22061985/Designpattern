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
package co.wigroup.order.common.util;

import java.util.Date;

/**
 * This class contains a JWT payload
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class JWTPayload
{
    private String api;
    private Long   oid;
    private Long   iat;
    private Long   exp;

    /**
     * This function retrieves an API consumer id.
     *
     * @access public
     * @return String api
     */
    public String getApiConsumerId()
    {
        return api;
    }

    /**
     * This function instantiates an API consumer id.
     *
     * @access public
     * @param  api The cosumer API id
     * @return void
     */
    public void setApiConsumerId(String api)
    {
        this.api = api;
    }

    /**
     * This function retrieves an order id.
     *
     * @access public
     * @return Long orderId
     */
    public Long getOrderId() { return oid; }

    /**
     * This function instantiates an order id.
     *
     * @access public
     * @param  sid The order id
     * @return void
     */
    public void setOrderId(Long sid)
    {
        this.oid = sid;
    }

    /**
     * This function retrieves the date a request was issued.
     *
     * @access public
     * @return Long iat
     */
    public Long getIssueDateMillis()
    {
        return iat;
    }

    /**
     * This function instantiates the date a request is issued.
     *
     * @access public
     * @param  iat The request issue date
     * @return void
     */
    public void setIssueDateMillis(Long iat)
    {
        this.iat = iat;
    }

    /**
     * This function retrieves the date a request is issued.
     *
     * @access public
     * @return Date iat
     */
    public Date getIssueDate()
    {
        return iat == null ? null : new Date(iat);
    }

    /**
     * This function instantiates the date a request is issued.
     *
     * @access public
     * @param  issueDate The date a request was issued
     * @return void
     */
    public void setIssueDate(Date issueDate)
    {
        this.iat = issueDate == null ? null : issueDate.getTime();
    }

    /**
     * This function retrieves a request expiry date.
     *
     * @access public
     * @return Long exp
     */
    public Long getExpiryDateMillis()
    {
        return exp;
    }

    /**
     * This function instantiates a date the request expires.
     *
     * @access public
     * @param  exp The request expiry date
     * @return void
     */
    public void setExpiryDateMillis(Long exp)
    {
        this.exp = exp;
    }

    /**
     * This function retrieves a request expiry date.
     *
     * @access public
     * @return Date exp
     */
    public Date getExpiryDate()
    {
        return exp == null ? null : new Date(exp);
    }

    /**
     * This function instantiates a date the request expires.
     *
     * @access public
     * @param  expiryDate The request expiry date
     * @return void
     */
    public void setExpiryDate(Date expiryDate)
    {
        this.exp = expiryDate == null ? null : expiryDate.getTime();
    }
}
