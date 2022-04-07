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
package co.wigroup.order.common.repository;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.wigroup.order.common.order.MockOrder;
import co.wigroup.order.common.order.MockProduct;
import co.wigroup.order.common.order.MockProfile;
import co.wigroup.order.common.responsecodes.ResponseCodeTracker;
import co.wigroup.order.common.responsecodes.ResponseCodesEnum;

/**
 * This class is the order repository. Creates queries that read from the order database.
 *
 * @access public
 * @author NoorAlam Shaikh (nsahikh@zensar.com)
 * @return  void
 */
@Dependent
public class OrderRepository
{
    private static final Logger log = LoggerFactory.getLogger(OrderRepository.class);

    @PersistenceContext
    private EntityManager entityManager;
    
    public List<MockOrder> findMockOrders()
    {
        return findMockOrders(0, Integer.MAX_VALUE);
    }

    /**
     * This function executes a query that retrieves all orders in the orders table.
     *
     * @access public
     * @param  pageOffset The current position in the orders table
     * @param  pageSize   The number of entries in the orders table
     * @return Collection orders | null
     */
    public List<MockOrder> findMockOrders(Integer pageOffset, Integer pageSize)
    {
        try
        {
            Query query = entityManager.createNativeQuery("SELECT * FROM mockorders ORDER BY order_time ASC", MockOrder.class);
            query.setFirstResult(pageOffset);
            query.setMaxResults(pageSize);

            List<MockOrder> mockorders = query.getResultList();
            return mockorders;
        }
        catch (Exception exception)
        {
            log.error("Unable to find mock orders", exception);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
        }

        return null;
    }

    /**
     * This function executes a query that retrieves an order based on order id.
     *
     * @access public
     * @param  id The order unique identifier
     * @return Collection orders | null
     */
    public MockOrder findMockOrder(Long id)
    {
        try
        {
            Query query = entityManager.createNativeQuery("SELECT * FROM mockorders WHERE id = ?", MockOrder.class);
            query.setParameter(1, id);

            List<MockOrder> mockorders = query.getResultList();
            if (mockorders.isEmpty())
            {
                ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.ORDER_ID_NOT_FOUND);
            }
            else
            {
                return mockorders.get(0);
            }
        }
        catch (Exception exception)
        {
            log.error("Unable to find mock order", exception);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
        }

        return null;
    }

	public Object findMockProfileTest(Long id) {
		 try
	        {
			 System.out.println("Begin executing sql query.........................");
	            Object profile = entityManager.createQuery("SELECT mp.id, mp.profileId, mp.surname, mp.name, mp.primaryContact, mp.orderReferenceNumber, mpi.itemId, mpi.productSku, mpi.quantity, mpi.lineNumber " +
	                                    "FROM MockProfile mp ,MockProfileItem mpi " +
	                                    "WHERE mp.id = mpi.mockProfile and mpi.mockProfile= "+id).getResultList();

	            System.out.println("End executing sql query........................." + profile);
	            return profile;
	        }
	        catch (Exception exception)
	        {
	            log.error("Unable to find profiles", exception);
	            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
	        }

	        return null;

	}

	public MockProfile findMockProfile(Long id) {
		 try
	        {
	            Query query = entityManager.createNativeQuery("SELECT * FROM mock_profile WHERE id = ?", MockProfile.class);
	            query.setParameter(1, id);

	            List<MockProfile> mockprofiles = query.getResultList();
	            if (mockprofiles.isEmpty())
	            {
	                ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.ORDER_ID_NOT_FOUND);
	            }
	            else
	            {
	                return mockprofiles.get(0);
	            }
	        }
	        catch (Exception exception)
	        {
	            log.error("Unable to find mock order", exception);
	            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
	        }
		 	return null;	
		}
	
	/**
     * This function executes a query that retrieves an order based on order id.
     *
     * @access public
     * @param  id The order unique identifier
     * @return Collection orders | null
     */
    public List<MockOrder> findMockOrderProfile(String profileId)
    {
        try
        {
            Query query = entityManager.createNativeQuery("SELECT * FROM mockorders WHERE profile_id = ?", MockOrder.class);
            query.setParameter(1, profileId);

            List<MockOrder> mockorders = query.getResultList();
            if (mockorders.isEmpty())
            {
                ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.ORDER_ID_NOT_FOUND);
            }
            else
            {
                return mockorders;
            }
        }
        catch (Exception exception)
        {
            log.error("Unable to find mock order profile", exception);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
        }

        return null;
    }
    
    /**
     * This function executes a query that retrieves an order based on order id.
     *
     * @access public
     * @param  id The order unique identifier
     * @return Collection orders | null
     */
    public MockProduct findMockProduct(String skuId)
    {
        try
        {
            Query query = entityManager.createNativeQuery("SELECT * FROM mock_products WHERE sku = ?", MockProduct.class);
            query.setParameter(1, skuId);

            List<MockProduct> mockproducts = query.getResultList();
            if (mockproducts.isEmpty())
            {
                ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.ORDER_ID_NOT_FOUND);
            }
            else
            {
                return mockproducts.get(0);
            }
        }
        catch (Exception exception)
        {
            log.error("Unable to find mock product", exception);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
        }

        return null;
    }


}
