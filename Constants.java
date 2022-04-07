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

/**
 * This class sets up constants that can be accessed accross the service.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 */
public class Constants
{
  //public static final String WW_DEV = "www-win-dev.woolworths.co.za/store/fragments/login/now-now-login.jsp?guid=";
	public static final String WW_DEV = "http://localhost:8180/test/login.jsp?guid=";
  public static final String WW_STAGING = "http://dev.ww.com?guid=";
  public static final String WW_PROD = "http://dev.ww.com?guid=";
  public static final String CONTENT_TYPE = "content-type";
  public static final String WI_TRACE_ID = "wi-trace-id";
  public static final String WI_TRACE_ID_VALUE = "1";
  public static final String STORES_CANCEL_INTERVAL = "4";
  public static final String X_API_KEY = "x-api-key";
}
