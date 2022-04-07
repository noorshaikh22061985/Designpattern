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

import java.util.UUID;

/**
 * This function instantiates a universal unique identifier
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 */
public class GenerateGUID
{
    UUID uuid = UUID.randomUUID();

    private final String guid = uuid.toString();

    /**
     * This function retrieves a GUID.
     *
     * @access public
     * @return void
     */
    public String getGuid()
    {
        return guid;
    }
}
