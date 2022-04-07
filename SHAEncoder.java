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

import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class creates an input stream.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class SHAEncoder
{
    private static final Logger LOG = LoggerFactory.getLogger(SHAEncoder.class);
    
    private static final String SHA_256 = "SHA-256";
    private static final String SHA_512 = "SHA-512";

    /**
     * This function encrypts using 256 bits.
     *
     * @access public
     * @param  input Plain text input
     * @return
     */
    public static String encode256(String input)
    {
        return encode(input, SHA_256);
    }

    /**
     * This function encrypts using 512 bits.
     *
     * @access public
     * @param  input Plain text input
     * @return
     */
    public static String encode512(String input)
    {
        return encode(input, SHA_512);
    }

    /**
     * This function encrypts an input stream using a specific algorithm.
     *
     * @access public
     * @param  input     Plain text input
     * @param  algorithm Encryption algorithm
     * @return String input | null
     */
    private static String encode(String input, String algorithm)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] bytes  = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) 
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        }
        catch (Exception exception)
        {
            // Should not occur as this is a private method and known algorithms are passed as a parameter
            LOG.error("unable to SHA-256 encode input", exception);
        }

        return null;
    }
  
}
