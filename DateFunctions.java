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

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class contains all date related functions to convert Strings to dates
 * and vice versa.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class DateFunctions {
    private static final Logger log = LoggerFactory.getLogger(DateFunctions.class);

    /**
     * The function takes in a string and tries to parse it as a date. WARNING:
     * This function does not output anything.
     *
     * @access public
     * @param  theDateString The string to parse for possible date.
     * @return Date object | null
     */
    public static Date convertToDate(String theDateString) {

        SimpleDateFormat formatDate;
        String[] formats = {"ddMMMyyyy", "dd-MMM-yyyy", "dd-MMM-yy", "yyyyMMdd", "ddMMyyyy", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "dd/MM/yyyy", "MMddyy", "MM/dd/yy", "MMddyyyy", "MM/dd/yyyy", "yyyy-MM-dd'T'HH:mm:ssZ"};


        for (String dateFormat : formats) {
            formatDate = new SimpleDateFormat(dateFormat);

            try {
                Date converted = formatDate.parse(theDateString);

                if (formatDate.format(converted).equals(theDateString)) {
                    //date is valid
                    return converted;
                }

            } catch (Exception e) {
                log.error("Exception converting {} to a Date: {}", theDateString, e);
            }
        }
        // COULD NOT CONVERT.
        return null;
    }

    /**
     * The function takes in a time string and tries to parse it as a date.
     *
     * @access public
     * @param  timeString The string to parse for possible date.
     * @return Date object | null
     */
    public static Date timeStringToDate(String timeString) {
        try {
            SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
            Date converted = parser.parse(timeString);

            if (parser.format(converted).equals(timeString)) {
                return converted;
            }
        } catch (Exception e) {
            log.error("Exception converting {} to a Date: {}", timeString, e);
        }

        return null;
    }

    /**
     * The purpose of this funciton is to get the time part of a date object.
     * 
     * @param Date The date object to be formatted
     * @return Date The formatted date object
     */
    public static Date getTimePart(Date date) {
        try {
            SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
            String timeString = parser.format(date);

            return parser.parse(timeString);
        } catch (Exception e) {
            log.error("Exception converting {} to a Time: {}", date, e);
        }

        return null;
    }

    /**
     * This function returns the date as a string in the specified format.
     *
     * @access public
     * @param  date   Date to format as string
     * @param  format The date format to use.
     * @return String formatted date.
     */
    public static String getDateAsString(Date date, String format) {
        SimpleDateFormat formatDate = new SimpleDateFormat(format);

        return formatDate.format(date);

    }

}
