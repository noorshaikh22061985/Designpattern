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

import co.wigroup.order.common.responsecodes.ResponseCodeTracker;
import co.wigroup.order.common.responsecodes.ResponseCodesEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

/**
 * This class creates the the Entity management and persistence context for the order service to manage data entries.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return  void
 */
@Stateless
public class CrudRepository
{
    private static final Logger log = LoggerFactory.getLogger(CrudRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    //--------------------------------------------------------------------------
    // BASIC CRUD 
    //--------------------------------------------------------------------------

    /**
     * This function create a table entry.
     *
     * @access public
     * @param  entity An entity object
     * @param  <T>    A generic type
     * @return Persistable entity | null
     */
    public <T extends Persistable> T persist(T entity)
    {
        if (entity == null)
        {
            return null;
        }

        try
        {
            entityManager.persist(entity);
            entityManager.flush();
            entityManager.refresh(entity);
            return entity;
        }
        catch (Exception e)
        {
            log.error("Exception", e);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
            return null;
        }
    }

    /**
     * This function will initiate a transaction operation.
     *
     * @access public
     * @param  <T> A generic type
     * @return null
     */
    public <T extends  Persistable> T begin()
    {
        try
        {
            entityManager.getTransaction().begin();
        }
        catch (Exception e)
        {
            log.error("Exception", e);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
        }

        return null;
    }

    /**
     * This function will initiate a rollback operation.
     *
     * @access public
     * @param  <T> A generic type
     * @return null
     */
    public <T extends  Persistable> T rollback()
    {
        try
        {
            entityManager.getTransaction().rollback();
        }
        catch (Exception e)
        {
            log.error("Exception", e);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
        }

        return null;
    }

    /**
     * This function will initiate a commitment to data entry.
     *
     * @access public
     * @param  <T> A generic type
     * @return null
     */
    public <T extends  Persistable> T commit()
    {
        try
        {
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            log.error("Exception", e);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
        }

        return null;
    }

    /**
     * This function will close a data entry transaction.
     *
     * @access public
     * @param  <T> A generic type
     * @return null
     */
    public <T extends  Persistable> T close()
    {
        try
        {
            entityManager.close();
        }
        catch (Exception e)
        {
            log.error("Exception", e);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
        }

        return null;
    }

    //READ

    /**
     * This function will retrieve a data entry object by id.
     *
     * @access public
     * @param  type An object type
     * @param  id   An object unique identifier
     * @param  <T>  A generic type
     * @return null
     */
    public <T extends Persistable> T find(Class<T> type, Object id)
    {
        if (type == null || id == null)
        {
            return null;
        }
        
        try
        {
            T object = entityManager.find(type, id);
            return object;
        }
        catch (Exception e)
        {
            log.error("Exception", e);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
            return null;
        }
    }

    /**
     * This function writes a data entry with the assumption it might fail.
     *
     * @access public
     * @param  type An object type
     * @param  id   An object unique identifier
     * @param  <T>  A generic type
     * @return null
     */
    public <T extends Persistable> T findAndLock(Class<T> type, Object id)
    {
        if (type == null || id == null)
        {
            return null;
        }
        
        try
        {
            T object = entityManager.find(type, id, LockModeType.PESSIMISTIC_WRITE);
            return object;
        }
        catch (Exception e)
        {
            log.error("Exception", e);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
            return null;
        }
    }

    /**
     * This function will overwrite the current state of an entity object.
     *
     * @access public
     * @param  entity An entity manager instance
     * @param  <T>    A generic type
     * @return <T>    entity | null
     */
    public <T> T refresh(T entity)
    {
        if (entity == null)
        {
            return null;
        }
        
        try
        {
            entityManager.refresh(entity);
            return entity;
        }
        catch (Exception e)
        {
            log.error("Exception", e);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
            return null;
        }
    }

    //UPDATE

    /**
     * This function will merge an entity state into the current persistence context
     *
     * @access public
     * @param  entity An entity manager instance
     * @param  <T>    A generic type
     * @return <T>    entity | null
     */
    public <T extends Persistable> T merge(T entity)
    {
        if (entity == null)
        {
            return null;
        }

        try
        {
            entityManager.merge(entity);
            entityManager.flush();

            return entity;
        }
        catch (Exception e)
        {
            log.error("Exception", e);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
            return null;
        }
    }

    //DELETE

    /**
     * This function removes data entries based on object id.
     *
     * @access public
     * @param  type An object type
     * @param  id   An object unique identifier
     * @param  <T>  A generic type
     * @return Boolean
     */
    public <T extends Persistable> boolean delete(Class<T> type, Object id)
    {
        if (type == null || id == null)
        {
            return false;
        }
        
        try
        {
            Object entity = entityManager.getReference(type, id);
            entityManager.remove(entity);
            return true;
        }
        catch (Exception e)
        {
            log.error("Exception", e);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
            return false;
        }
    }

    /**
     * This function removes an entity object.
     *
     * @access public
     * @param  entity An entity manager instance
     * @param  <T>    A generic type
     * @return Boolean
     */
    public <T extends Persistable> boolean delete(T entity)
    {
        if (entity == null)
        {
            return false;
        }
        
        try
        {
            delete(entity.getClass(), entity.getId());
            return true;
        }
        catch (Exception e)
        {
            log.error("Exception", e);
            ResponseCodeTracker.setResponseCodeEnum(ResponseCodesEnum.INTERNAL_SERVER_ERROR_DB);
            return false;
        }
    }
}
