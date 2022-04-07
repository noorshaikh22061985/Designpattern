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

package co.wigroup.order.common.rest.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter allows Cross site resource sharing. Cross-origin resource sharing (CORS) is a mechanism that restrict resources (e.g. fonts, JavaScript, etc.) on a web page to be requested from another
 * domain outside the domain from which the resource originated.
 * <p>
 * NOTE: This might not be safe. We currently accept all incoming sites. Might want to limit admin to only our domain.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class CorsFilter implements Filter
{
    private static final Logger log = LoggerFactory.getLogger(CorsFilter.class);

    /**
     * This function instantiates a filter object.
     *
     * @access public
     * @param  filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    /**
     * This function will filter a response.
     *
     * @access public
     * @param  servletRequest  The servlet request object
     * @param  servletResponse The servlet response object
     * @param  filterChain     The filter parameters
     * @throws IOException
     * @throws ServletException
     * @return void
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, HEAD, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Origin, Accept, X-Requested-With, Cache-Control, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, wi-trace-id, x-api-key, profile-id, X-Amz-Date X-Amz-Security-Token");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * This function destroys the log.
     *
     * @access public
     * @return void
     */
    @Override
    public void destroy()
    {
    }
}
