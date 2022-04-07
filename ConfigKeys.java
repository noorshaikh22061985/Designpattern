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
package co.wigroup.order.common.config;

/**
 * This class will keep the keys needed to access aws.
 *
 * @access public
 * @author Ushe Bangure (UsheB@wigroup.co.za)
 * @return void
 */
public class ConfigKeys
{
    /*
     * Order service specific properties
     */
    public static final String DEFAULT_PAGE_SIZE     = "order-service.defaultPageSize";

    public static final String LIFECYCLE_EVENT_TOPIC = "order-service.lifecycleEventTopic";
    public static final String ORDER_PAYMENT_TOPIC   = "order-service.orderPaymentEventTopic";

    /*
     * Order service Rest Endpoint Urls
     */
    public static final String PUSH_NOTIFICATION_URL = "order-service.pushNotificationUrl";
    public static final String PRODUCT_INFO_URL      = "order-service.productInfoUrl";
    public static final String STORES_URL            = "order-service.storesUrl";
    public static final String STORES_VARIABLE_URL   = "order-service.storesVariableUrl";
    public static final String STORES_VERIFY_URL     = "order-service.storesVerifyStoreUrl";
    public static final String SUBSCRIBER_URL        = "order-service.subscriberUrl";
    public static final String DELIVERY_URL          = "order-service.deliveryUrl";

    /*
     * Order service WW GUID Url
     */
    public static final String WW_GUID_URL            = "order-service.guidUrl";
    public static final String WW_CANCEL_SHIPMENT     = "order-service.cancelShipmentUrl";
    public static final String WW_SET_SHIPMENT_PICKED = "order-service.setShipmentPickedUrl";

    /**
     * IPC API Keys
     */
    public static final String STORES_IPC_KEY = "order-service.storesIpcKey";

    /**
     * NowNow specific constants
     */
    public static final String FULFILMENT_SYSTEM_TYPE = "order-service.fulfillmentSystemType";
}
