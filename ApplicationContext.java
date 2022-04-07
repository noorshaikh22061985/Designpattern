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

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

/**
 * Quick way to access CDI beans
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public abstract class ApplicationContext
{
    /**
     * This function is a generic method for retrieving beans.
     *
     * @access public
     * @param  beanClass application class to be injected
     * @param  <T>       generic type
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> beanClass)
    {
        BeanManager bm = CDI.current().getBeanManager();

        Bean<T> bn = (Bean<T>) bm.getBeans(beanClass).iterator().next();
        CreationalContext<T> ctx = bm.createCreationalContext(bn);
        T bean = (T) bm.getReference(bn, beanClass, ctx);
        return bean;
    }

}
