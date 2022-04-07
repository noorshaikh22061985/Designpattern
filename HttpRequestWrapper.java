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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class frames API request data.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class HttpRequestWrapper extends HttpServletRequestWrapper
{
    private ByteArrayOutputStream cachedBytes;

    /**
     * This function wraps an http request.
     *
     * @access public
     * @param  request
     * @return void
     */
    public HttpRequestWrapper(HttpServletRequest request)
    {
        super(request);
    }

    /**
     * This function will map the request header.
     *
     * @access public
     * @return Map<String> headerMap
     */
    public Map<String, String> getHeadersAsMap()
    {
        Map<String, String> headerMap = new HashMap<>();

        List<String> names = Collections.list(super.getHeaderNames());
        for (String name : names)
        {
            headerMap.put(name, super.getHeader(name));
        }

        return headerMap;
    }

    /**
     * This function will map the request header and filter for logging.
     *
     * @access public
     * @return Map<String> headerMapCensoredForLogging
     */
    public Map<String, String> getHeadersAsMapCensoredForLogging()
    {

        Map<String, String> headerMapCensoredForLogging = new HashMap<>();

        //first super
        List<String> names = Collections.list(super.getHeaderNames());
        for (String name : names)
        {
            if (!name.equalsIgnoreCase("secretKey"))
            {
                headerMapCensoredForLogging.put(name, super.getHeader(name));
            }
        }

        return headerMapCensoredForLogging;
    }

    /**
     * This function retrieves the input stream.
     *
     * @access public
     * @return ServletInputStream
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException
    {
        if (cachedBytes == null)
        {
            cacheInputStream();
        }

        return new CachedServletInputStream();
    }

    /**
     * This function retrieves an input stream reader.
     *
     * @access public
     * @return BufferedReader
     * @throws IOException
     */
    @Override
    public BufferedReader getReader() throws IOException
    {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * This function will store the input stream.
     *
     * @access public
     * @throws IOException
     * @return void
     */
    private void cacheInputStream() throws IOException
    {
        /* Cache the inputstream in order to read it multiple times. For
         * convenience, I use apache.commons IOUtils
         */
        cachedBytes = new ByteArrayOutputStream();
        IOUtils.copy(super.getInputStream(), cachedBytes);
    }

    /**
     * An input stream which reads the cached request body
     *
     * @access public
     * @return void
     */
    public class CachedServletInputStream extends ServletInputStream
    {
        private final ByteArrayInputStream input;

        /**
         * create a new input stream from the cached request body
         *
         * @access public
         * @return void
         */
        public CachedServletInputStream()
        {
            input = new ByteArrayInputStream(cachedBytes.toByteArray());
        }

        /**
         * This function reads an input stream.
         *
         * @access public
         * @return int input
         * @throws IOException
         */
        @Override
        public int read() throws IOException
        {
            return input.read();
        }

        /**
         * This method validates if an input stream is supported.
         *
         * @access public
         * @throws UnsupportedOperationException
         * @return void
         */
        @Override
        public boolean isFinished()
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        /**
         * This method validates if an input stream is readable.
         *
         * @access public
         * @throws UnsupportedOperationException
         * @return void
         */
        @Override
        public boolean isReady()
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        /**
         * This method instantiates a read listener.
         *
         * @access public
         * @throws UnsupportedOperationException
         * @return void
         */
        @Override
        public void setReadListener(ReadListener rl)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
