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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import java.util.HashMap;
import java.util.Map;

/**
 * Try and keep them nicely grouped into ranges with stuff that fits together. For example error messages used for each interface response can be separated.
 * <p>
 * Response code naming conventions: project(1-2)/interface(3)/call_error(4-5)
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public enum ResponseCodesEnum
{
    SUCCESS(-1, Response.Status.OK, "Success", "Success"),
    SUCCESSFULLY_CREATED(-2, Response.Status.CREATED, "Successfully Created", "Success"),
    
    //==========================================================================
    // GENERAL SYSTEM ERRORS
    // Only these errors should trigger Email notifications to support.
    // Range: 0 - 99
    //==========================================================================

    GENERAL_NOT_YET_IMPLEMENTED(0, Response.Status.INTERNAL_SERVER_ERROR, "Sneaky Sneaky. The method you called is not yet implemented.", "Requested functionality not currently available."),
    INTERNAL_SERVER_ERROR_GENERAL(1, Response.Status.INTERNAL_SERVER_ERROR, "Internal Server Error.", "We cannot currently process your request. Please try again soon."),
    INTERNAL_SERVER_ERROR_CONFIG(2, Response.Status.INTERNAL_SERVER_ERROR, "Internal Server Error. Configuration related.", "We cannot currently process your request. Please try again soon."),
    INTERNAL_SERVER_ERROR_DB(3, Response.Status.INTERNAL_SERVER_ERROR, "Internal Server Error. Database access related.", "We cannot currently process your request. Please try again soon."),
    INTERNAL_SERVER_ERROR_GENERAL_NO_RESPONSECODE(4, Response.Status.INTERNAL_SERVER_ERROR, "Internal Server Error. No response set.", "We cannot currently process your request. Please try again soon."),
    INTERNAL_SERVER_ERROR_GENERAL_NO_RESULT(5, Response.Status.INTERNAL_SERVER_ERROR, "Internal Server Error. No result found.", "We cannot currently process your request. Please try again soon."),
    INVALID_REQUEST_UNSUPPORTED_MEDIA_TYPE(10, Response.Status.BAD_REQUEST, "Unsupported Media Type. Must be of type application/json.", "We cannot currently process your request. Please try again soon."),
    INVALID_REQUEST_JSON(11, Response.Status.BAD_REQUEST, "The JSON is not correctly formatted.", "We cannot currently process your request. Please try again soon."),
    
    //generic message validation errors
    POST_APP_BODY_REQUIRED(201, Response.Status.BAD_REQUEST, "All POST methods require a body.", "API call invalid."),
    
    REQUIRED_FIELD_MISSING(300, Response.Status.BAD_REQUEST, "A required field is missing. Check documentation for which fields are required.", 
                                                             "We cannot currently process your request. Please try again soon."),
    API_CONSUMER_ID_MISSING(301, Response.Status.BAD_REQUEST, "x-consumer-id header param not present.", 
                                                              "We cannot currently process your request. Please try again soon."),
    ORDER_REFERENCE_MISSING(302, Response.Status.BAD_REQUEST, "reference not present.",
                                                                   "We cannot currently process your request. Please try again soon."),
    ORDER_ID_MISSING(303, Response.Status.BAD_REQUEST, "id not present.", 
                                                            "We cannot currently process your request. Please try again soon."),
    ORDER_ID_LE_ZERO(304, Response.Status.BAD_REQUEST, "id <= 0.", 
                                                            "id <= 0."),
    AUTH_TOKEN_MISSING(305, Response.Status.BAD_REQUEST, "Authorization header param (containing bearer token) not present.", 
                                                         "We cannot currently process your request. Please try again soon."),
    
    AUTH_TOKEN_INVALID(400, Response.Status.BAD_REQUEST, "The bearer token associated with the request is invalid.", 
                                                         "We cannot currently process your request. Please try again soon."),
    
    AUTH_TOKEN_API_CONSUMER_ID_INVALID(401, Response.Status.BAD_REQUEST, "The bearer token is not associated with the consumer, apiConsumerId does not match", 
                                                                         "We cannot currently process your request. Please try again soon."),
    
    SUBSCRIBER_PASSWORD_FIELD_MISSING(306, Response.Status.BAD_REQUEST, "The password field is not preset in the request but is required for this subscriber.", 
                                                                        "We cannot currently process your request. Please try again soon."),
    
    //==========================================================================
    // Consumer API 
    //==========================================================================
    CONSUMER_ID_NOT_FOUND(1000, Response.Status.NOT_FOUND, "The consumer was not found. Does not exist or is not active.", "We cannot currently process your request. Please try again soon."),
    CONSUMER_INVALID_API_ID(1002, Response.Status.CONFLICT, "The consumer api id is already used.", "We cannot currently process your request. Please try again soon."),
    CONSUMER_API_ID_NOT_FOUND(1003, Response.Status.CONFLICT, "The consumer api id does not exist.", "We cannot currently process your request. Please try again soon."),
    
    CONSUMER_STATE_MISSING(1100, Response.Status.CONFLICT, "The consumer state field is missing from the request.", "We cannot currently process your request. Please try again soon."),
    CONSUMER_MESSAGING_CHANNEL_MISSING(1101, Response.Status.CONFLICT, "The consumer messagingChannel field is missing from the request.", "We cannot currently process your request. Please try again soon."),
    CONSUMER_USERNAME_MISSING(1102, Response.Status.CONFLICT, "The consumer username field is missing from the request.", "We cannot currently process your request. Please try again soon."),
    CONSUMER_OTP_EXPIRY_HOURS_MISSING(1103, Response.Status.CONFLICT, "The consumer otpExpiryHours field is missing from the request.", "We cannot currently process your request. Please try again soon."),
    CONSUMER_OTP_EXPIRY_MINUTES_MISSING(1104, Response.Status.CONFLICT, "The consumer otpExpiryMinutes field is missing from the request.", "We cannot currently process your request. Please try again soon."),
     
    CONSUMER_ACTIVATION_REQUIRED_FIELD_MISSING(1105, Response.Status.CONFLICT, "The consumer activatation Required field is missing from the request.",
                                                                            "We cannot currently process your request. Please try again soon."),
    CONSUMER_VALIDATE_EMAIL_FIELD_MISSING(1106, Response.Status.CONFLICT, "The consumer validateEmail field is missing from the request.", 
                                                                          "We cannot currently process your request. Please try again soon."),
    CONSUMER_VALIDATE_MOBILE_FIELD_MISSING(1107, Response.Status.CONFLICT, "The consumer validateMobile field is missing from the request.", 
                                                                            "We cannot currently process your request. Please try again soon."),
    CONSUMER_CREDENTIALS_REQUIRED_FIELD_MISSING(1108, Response.Status.CONFLICT, "The consumer credentialsRequired field is missing from the request.", 
                                                                            "We cannot currently process your request. Please try again soon."),
    CONSUMER_TOKEN_EXPIRY_MINUTES_FIELD_MISSING(1109, Response.Status.CONFLICT, "The consumer tokenExpiryMinutes field is missing from the request.", 
                                                                                "We cannot currently process your request. Please try again soon."),
    CONSUMER_TOKEN_EXPIRY_HOURS_FIELD_MISSING(1110, Response.Status.CONFLICT, "The consumer tokenExpiryHours field is missing from the request.", 
                                                                                "We cannot currently process your request. Please try again soon."),
    CONSUMER_REFERENCE_CONTACT_LINK_MISSING(1111, Response.Status.CONFLICT, "The consumer referenceContactLink field is missing from the request.", 
                                                                            "We cannot currently process your request. Please try again soon."),
    
    //==========================================================================
    // Order API
    //==========================================================================
    ORDER_ID_NOT_FOUND(4000, Response.Status.NOT_FOUND, "The order was not found. Does not exist or is not active.",
                                                             "Your order is not currently placed."),

    ORDER_REFERENCE_EXISTS(4001, Response.Status.CONFLICT, "The order reference is already used.",
                                                                "Your order has already been placed."),
    
    ORDER_REFERENCE_NOT_FOUND(4002, Response.Status.CONFLICT, "The order reference does not exist for the consumer.",
                                                                   "Your order is not currently placed."),

    ORDER_PRODUCT_IPC_FAILED(4003, Response.Status.NOT_FOUND, "There order service could not complete the request to the product service.",
            "Your order is not currently placed."), 
    WI_TRACE_ID_MISSING(4004, Response.Status.BAD_REQUEST, "wi-trace-id param not present.", 
            "We cannot currently process your request. Please try again soon."),
    ORDER_SUBSCRIBER_IPC_FAILED(4005,Response.Status.BAD_REQUEST , "The was an issue creating Now Now Subscription",
            "We cannot currently subscribe the user"),
    ORDER_SUBSCRIBER_IPC_SUCCESS(4006,Response.Status.OK , "A subscriber has been successfully added",
            "A new subscriber has been successfully added to the subscriber service."),
    ORDER_PAYMENT_ALREADY_PROCESSED(4007,Response.Status.CONFLICT ,"The order payment has already been processed" ,
            "The order payment has already been process"),
    INVALID_PRODUCTS(4008,Response.Status.OK ,"Invalid product(s) in basket" , "Some of your basket items are invalid for the given time the order was placed"),
    ORDER_STATE_UPDATED_SUCCESSFULLY(4009,Response.Status.OK ,"Order status updated successfully" ,"The order state has been transitioned successfully"),
    ORDER_STATE_UPDATE_FAILED(4010,Response.Status.OK ,"Unable to update order status" ,"Order status was unable to update"),
    NOTIFICATION_IPC_FAILED(4011, Response.Status.BAD_REQUEST, "Unable to send notifications", "Unable to connect to the notifications service."),
    ORDER_TIME_NULL(4012,Response.Status.BAD_REQUEST ,"Order time was not accepted." , "Malformed or NUll order time submitted."),
    ORDER_SUBSCRIBER_ID_EXISTS(4013,Response.Status.CONFLICT ,"Order reference invalid" ,"The order reference has already been used"),
    WW_ACCEPT_SHIPMENT_FAILED(4014,Response.Status.SERVICE_UNAVAILABLE ,"Card payment could not be settled." ,"There was an error settling order payment. Accept shipment service is not available."),
    WW_CANCEL_SHIPMENT_FAILED(4015,Response.Status.SERVICE_UNAVAILABLE ,"Shipment could not be canceled" ,"There was an error canceling order shipment. Cancel shipment service is not available."),
    DELIVERY_IPC_FAILED(4016, Response.Status.BAD_REQUEST, "unable to retrieve delivery information", "Unable to connect to the delivery service."),
    ORDER_ITEM_CANNOT_BE_ZERO(4017, Response.Status.BAD_REQUEST, "Order item/subitem quantity cannot be zero.", "Order item/subitem quantity cannot be zero."),
    ORDER_ITEM_PRODUCT_ID_MISSING(4017, Response.Status.BAD_REQUEST, "Order item/subitem product ID is missing.", "Order item/subitem product ID is missing.");

    private static final Logger log = LoggerFactory.getLogger(ResponseCodesEnum.class);
    
    public static final String RESPONSE_CODE_FORMAT        = "%05d"; //This will pad response code value with zeros up to length 5.
    public static final int    GENERAL_EXCEPTION_RANGE_END = 99;     //The end of General system exception range. Used to know what are system errors.
    
    private static final Map<String, ResponseCodesEnum> responseCodeValueMap = new HashMap<>();

    static
    {
        for (ResponseCodesEnum type : ResponseCodesEnum.values())
        {
            responseCodeValueMap.put(type.code, type);
        }
    }
    
    //==========================================================================
    // SETTERS AND GETTERS.
    //==========================================================================
    
    private StatusType httpStatusCode;
    
    private final int    errorCode;
    private final String code;
    private final String faultMessage;
    private final String displayMessage;

    //==========================================================================

    /**
     * This function creates the enum with responseCode and responseDesc.
     *
     * @access private
     * @param  errorCode      Error message identifier
     * @param  httpStatusCode Http response code
     * @param  faultMessage   Exception message
     * @param  displayMessage Error message
     * @return void
     */
    private ResponseCodesEnum(int errorCode, StatusType httpStatusCode, String faultMessage, String displayMessage)
    {
        this.errorCode      = errorCode;
        this.httpStatusCode = httpStatusCode;

        if (errorCode >= 0)
        {
            this.code = String.format(RESPONSE_CODE_FORMAT, errorCode);
        }
        else
        {
            this.code = String.valueOf(errorCode);
        }

        this.faultMessage   = faultMessage;
        this.displayMessage = displayMessage;
    }

    //==========================================================================

    /**
     * This function retrieves the enum with a response code.
     *
     * @access public
     * @param  responseCode An enum for an error
     * @return ResponseCodesEnum mapEnum | responseCode
     */
    public static ResponseCodesEnum mapCodeToEnum(String responseCode)
    {
        ResponseCodesEnum mapEnum = responseCodeValueMap.get(responseCode);

        if (mapEnum != null)
        {
            return mapEnum;
        }
        else
        {
            log.debug("Response code could not be mapped: {}", responseCode);
            return INTERNAL_SERVER_ERROR_GENERAL;
        }
    }

    /**
     * This function retrieves an http response code.
     *
     * @access public
     * @return StatusType httpStatusCode
     */
    public StatusType getHttpStatusCode()
    {
        return httpStatusCode;
    }

    /**
     * This function instantiates an http response code.
     *
     * @access public
     * @param  httpStatusCode An http response code
     * @return void
     */
    public void setHttpStatusCode(StatusType httpStatusCode)
    {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * This function retrieves a fault message.
     *
     * @access public
     * @return int errorCode
     */
    public int getErrorCode()
    {
        return errorCode;
    }

    /**
     * This function retrieves the error code unique identifier.
     *
     * @access public
     * @return String code
     */
    public String getCode() { return code; }

    /**
     * This function retrieves the request fault message.
     *
     * @access public
     * @return String faultMessage
     */
    public String getFaultMessage()
    {
        return faultMessage;
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
     * This function prints all enums to screen
     *
     * @access public
     * @return void
     */
    public static void printAllEnums()
    {
        for (ResponseCodesEnum code : ResponseCodesEnum.values())
        {
            System.out.println(code.getHttpStatusCode() + "," + code.getCode() + "," + code.getFaultMessage() + "," + code.getDisplayMessage() + "," + code.name());
        }
    }

    /**
     * This function will fun the printAllEnums method.
     *
     * @access public
     * @param  args An argument string
     * @retrun void
     */
    public static void main(String[] args)
    {
        ResponseCodesEnum.printAllEnums();
    }
}
