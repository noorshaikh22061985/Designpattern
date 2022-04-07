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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class frames API response data.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class HttpResponseWrapper extends HttpServletResponseWrapper
{
    private ServletOutputStream       outputStream;
    private PrintWriter               writer;
    private ServletOutputStreamCopier copier;

    /**
     * This is the class constructor.
     *
     * @access public
     * @param  response Http response
     * @throws IOException
     */
    public HttpResponseWrapper(HttpServletResponse response) throws IOException
    {
        super(response);
    }

    /**
     * This function retrieves an output stream
     *
     * @access public
     * @return ServletOutputStreamCopier copier
     * @throws IOException
     */
    @Override
    public ServletOutputStream getOutputStream() throws IOException
    {
        if (writer != null)
        {
            throw new IllegalStateException("getWriter() has already been called on this response.");
        }

        if (outputStream == null)
        {
            outputStream = getResponse().getOutputStream();
            copier       = new ServletOutputStreamCopier(outputStream);
        }

        return copier;
    }

    /**
     * This function retrieves mapped http response headers as a map
     *
     * @access public
     * @return Map headerMap
     */
    public Map<String, String> getHeadersAsMap()
    {

        Map<String, String> headerMap = new HashMap<>();

        List<String> names = new ArrayList<>(super.getHeaderNames());
        for (String name : names)
        {
            headerMap.put(name, super.getHeader(name));
        }

        return headerMap;
    }

    /**
     * This function formats an output stream.
     *
     * @access public
     * @return PrintWriter writer
     * @throws IOException
     */
    @Override
    public PrintWriter getWriter() throws IOException
    {
        if (outputStream != null)
        {
            throw new IllegalStateException("getOutputStream() has already been called on this response.");
        }

        if (writer == null)
        {
            copier = new ServletOutputStreamCopier(getResponse().getOutputStream());
            writer = new PrintWriter(new OutputStreamWriter(copier, getResponse().getCharacterEncoding()), true);
        }

        return writer;
    }

    /**
     * This function deletes an output stream.
     *
     * @access public
     * @throws IOException
     * @return void
     */
    @Override
    public void flushBuffer() throws IOException
    {
        if (writer != null)
        {
            writer.flush();
        }
        else if (outputStream != null)
        {
            copier.flush();
        }
    }

    /**
     * This function copies an output stream.
     *
     * @access public
     * @return Collection byte | copier
     */
    public byte[] getCopy()
    {
        if (copier != null)
        {
            return copier.getCopy();
        }
        else
        {
            return new byte[0];
        }
    }

    /**
     * This class co-ordinates the implementation of I/O streams
     *
     * @access public
     * @return void
     */
    public class ServletOutputStreamCopier extends ServletOutputStream
    {
        private final OutputStream          outputStream;
        private final ByteArrayOutputStream copy;

        /**
         * This function instantiates I/O management streams.
         *
         * @access public
         * @param  outputStream Http response output
         * @return void
         */
        public ServletOutputStreamCopier(OutputStream outputStream)
        {
            this.outputStream = outputStream;
            this.copy         = new ByteArrayOutputStream(1024);
        }

        /**
         * This function the writing into an output stream.
         *
         * @access public
         * @param  b object to be written
         * @throws IOException
         */
        @Override
        public void write(int b) throws IOException
        {
            outputStream.write(b);
            copy.write(b);
        }

        /**
         * This function retrieves a copy of the bytes stream as an array.
         *
         * @access public
         * @return byte copy
         */
        public byte[] getCopy()
        {
            return copy.toByteArray();
        }

        /**
         * This function validates if an output stream is ready to be consumed.
         *
         * @access public
         * @thows  UnsupportedOperationException
         * @return Boolean
         */
        @Override
        public boolean isReady()
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        /**
         * This function instantiates a write listener.
         *
         * @access public
         * @param  wl writeListener
         * @throws UnsupportedOperationException
         * @return void
         */
        @Override
        public void setWriteListener(WriteListener wl)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
