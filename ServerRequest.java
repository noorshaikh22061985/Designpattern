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

import co.wigroup.order.common.config.Config;

//commented by Noor
//import co.wigroup.order.common.lifecycle_publisher.OrderLifecycleEventsToSNSTopic;
//import co.wigroup.order.common.lifecycle_publisher.OrderLifecyclePublisher;

import co.wigroup.order.common.repository.CrudRepository;
import co.wigroup.order.common.repository.OrderRepository;
import co.wigroup.order.common.responsecodes.ResponseCodeTracker;
import co.wigroup.order.common.responsecodes.ResponseCodesEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * This class handles API requests.
 *
 * @access public
 * @author Phakamani Mavela (phakamnim@wigroup.co.za)
 * @return void
 */
public class ServerRequest
{
    public static final String UNDEFINED_WITRACEID = "undefined";
    
    private CrudRepository           crudRepository;
    private OrderRepository          orderRepository;

    private Config config;

    //commentd by Noor
   // private OrderLifecyclePublisher orderLifecyclePublisher;

    // These fields will be added by the gateway - KONG is this case
    private String wiTraceId = UNDEFINED_WITRACEID;
    private String apiConsumerId;

    /**
     * This function will instantiate database objects and publishing instances
     *
     * @access public
     * @return void
     */
    public ServerRequest()
    {
        crudRepository           = ApplicationContext.getBean(CrudRepository.class);
        orderRepository          = ApplicationContext.getBean(OrderRepository.class);
        //commented by NOOR
       // orderLifecyclePublisher = new OrderLifecycleEventsToSNSTopic();
    }

    /**
     * This function process an API response.
     *
     * @access public
     * @return ServerResponse
     */
    public ServerResponse process()
    {
        return new ServerResponse(ResponseCodesEnum.SUCCESS);
    }

    /**
     * This function retrieves the wiTraceId.
     *
     * @access public
     * @return String wiTraceId
     */
    @ApiModelProperty(hidden = true)
    public String getWiTraceId()
    {
        return wiTraceId;
    }

    /**
     * This function instantiates a wiTraceId.
     *
     * @access public
     * @param  wiTraceId Keeps track of request traffic
     * @return void
     */
    public void setWiTraceId(String wiTraceId)
    {
        if (wiTraceId == null)
        {
            wiTraceId = UNDEFINED_WITRACEID;
        }
        else
        {
            this.wiTraceId = wiTraceId;
        }
    }

    /**
     * This function retrieves an API consumer unique identifier.
     *
     * @access public
     * @return String apiConsumerId
     */
    @ApiModelProperty(hidden = true)
    public String getApiConsumerId()
    {
        return apiConsumerId;
    }

    /**
     * This function instatiates a API consumer unique identifier.
     *
     * @access public
     * @param  apiConsumerId API key to use the service
     * @return void
     */
    public void setApiConsumerId(String apiConsumerId)
    {
        this.apiConsumerId = apiConsumerId;
    }

    /**
     * This function verifies if an API consumer id exists
     *
     * @access public
     * @return Boolean
     */
    @ApiModelProperty(hidden = true)
    public boolean isValid()
    {
        if (apiConsumerId == null || apiConsumerId.isEmpty()) 
        {
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.API_CONSUMER_ID_MISSING);
            return false;
        }
        
        return true;
    }

    /**
     * This function  sanitises server requests.
     *
     * @access public
     * @return void
     */
    @ApiModelProperty(hidden = true)
    public void sanitise()
    {
    }

    /**
     * This function retrieves a generic class to perform CRUD calls.
     *
     * @access public
     * @return CrudRepository crudRepository
     */
    @ApiModelProperty(hidden = true)
    public CrudRepository getCrudRepository()
    {
        return crudRepository;
    }

    /**
     * This function instantiates a generic class to perform CRUD calls.
     *
     * @access public
     * @param  crudRepository Generic class to perform GRUD calls to datasource
     * @return void
     */
    public void setCrudRepository(CrudRepository crudRepository)
    {
        this.crudRepository = crudRepository;
    }

    /**
     * This function retrieves an order class to perform CRUD calls.
     *
     * @access public
     * @return OrderRepository orderRepository
     */
    @ApiModelProperty(hidden = true)
    public OrderRepository getOrderRepository()
    {
        return orderRepository;
    }
    /**
     * This function instantiates a order class to perform CRUD calls.
     *
     * @access public
     * @param  orderRepository Order class to perform GRUD calls to datasource
     * @return void
     */
    public void setOrderRepository(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

       /**
     * This function retrieves a request configuration.
     *
     * @access public
     * @return Config config
     */
    @ApiModelProperty(hidden = true)
    public Config getConfig()
    {
        return config;
    }

    /**
     * This function instantiates the request configuration object.
     *
     * @access public
     * @param  config Object with request configuration
     * @return void
     */
    public void setConfig(Config config)
    {
        this.config = config;
    }

    //commented by Noor
    /**
     * This function retrieves an AWS order publishing instance.
     *
     * @access public
     * @return OrderLifecyclePublisher orderLifecyclePublisher
     */
   /* @ApiModelProperty(hidden = true)
    public OrderLifecyclePublisher getOrderLifecyclePublisher()
    {
        return orderLifecyclePublisher;
    }*/

    /**
     * This function instantiates the AWS order publishing instance.
     *
     * @access public
     * @param  orderLifecyclePublisher AWS SNS Topic publisher
     * @return void
     */
   /* public void setOrderLifecyclePublisher(OrderLifecyclePublisher orderLifecyclePublisher)
    {
        this.orderLifecyclePublisher = orderLifecyclePublisher;
    }*/
}
