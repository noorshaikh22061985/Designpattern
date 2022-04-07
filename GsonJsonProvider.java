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

import co.wigroup.order.common.util.DateFunctions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;

import java.lang.reflect.Type;

import java.sql.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class will handle the consumption of Json objects.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
@Provider
@Consumes({MediaType.APPLICATION_JSON, "text/json"})
@Produces({MediaType.APPLICATION_JSON, "text/json"})
public class GsonJsonProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object>
{
    private static final Logger LOG = LoggerFactory.getLogger(GsonJsonProvider.class);

    static final JsonSerializer<Date> ser = new JsonSerializer<Date>()
    {
        /**
         * This function converts objects into a json context.
         *
         * @access public
         * @param  src       Date of conversion
         * @param  typeOfSrc File type
         * @param  context   The state to transform the object into
         * @return Date src
         */
        @Override
        public JsonElement serialize(Date src,
                                     Type typeOfSrc,
                                     JsonSerializationContext context)
        {
            return src == null ? null : new JsonPrimitive(DateFunctions.getDateAsString(new java.util.Date(src.getTime()), "yyyy-MM-dd"));
        }
    };

    static final JsonDeserializer<java.sql.Date> deser = new JsonDeserializer<java.sql.Date>()
    {
        /**
         * This function breaks down a json object into a specified context.
         *
         * @access public
         * @param  json    A json object
         * @param  typeOfT File type
         * @param  context The state to transform the object into
         * @return Json
         * @throws JsonParseException
         */
        @Override
        public Date deserialize(JsonElement json,
                                Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException
        {
            return json == null ? null : new Date(DateFunctions.convertToDate(json.getAsString()).getTime());
        }
    };
    static final Gson gson = new GsonBuilder().serializeNulls().registerTypeAdapter(java.sql.Date.class, ser).registerTypeAdapter(java.sql.Date.class, deser).setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

    /**
     * This function validates if an object type is valid.
     *
     * @access public
     * @param  type        Class type
     * @param  genericType Generic type
     * @param  annotations Collection of annotations
     * @param  mediaType   Consumer and producer media type
     * @return Boolean
     */
    @Override
    public boolean isReadable(Class<?> type,
                              Type genericType,
                              Annotation[] annotations,
                              MediaType mediaType)
    {
        return true;
    }

    /**
     * This function consumes an input stream and return a json.
     *
     * @access public
     * @param  type         Object type
     * @param  genericType  Generic type
     * @param  annotations  Collection of annotations
     * @param  mediaType    Context type of input stream
     * @param  httpHeaders  String of headers
     * @param  entityStream An input stream
     * @return json | null
     * @throws IOException
     * @throws WebApplicationException
     */
    @Override
    public Object readFrom(Class<Object> type,
                           Type genericType,
                           Annotation[] annotations,
                           MediaType mediaType,
                           MultivaluedMap<String, String> httpHeaders,
                           InputStream entityStream) throws IOException, WebApplicationException
    {
        Reader reader = new InputStreamReader(entityStream);
        try
        {
            return gson.fromJson(reader, genericType);
        }
        catch (Exception exception)
        {
            LOG.error("unable to parse JSON", exception);
            return null;
        }
        finally
        {
            reader.close();
        }
    }

    /**
     * This function validates if an object type is writable
     *
     * @access public
     * @param  type        Class type
     * @param  genericType Generic type
     * @param  annotations Collection of annotations
     * @param  mediaType   Consumer and producer media type
     * @return Boolean
     */
    @Override
    public boolean isWriteable(Class<?> type,
                               Type genericType,
                               Annotation[] annotations,
                               MediaType mediaType)
    {
        return true;
    }

    /**
     * This function retreives the size of an object.
     *
     * @access public
     * @param  o           The object that is evaluated
     * @param  type        The class type of the object
     * @param  genericType The generic type
     * @param  annotations A collection of annotations
     * @param  mediaType   The consumer or producer media type
     * @return -1
     */
    @Override
    public long getSize(Object o,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType)
    {
        return -1;
    }

    /**
     * This function writes to an object
     *
     * @access public
     * @param  o            The object that is evaluated
     * @param  type         The class type of the object
     * @param  genericType  The generic type
     * @param  annotations  A collection of annotations
     * @param  mediaType    The consumer or producer media type
     * @param  httpHeaders  String of headers
     * @param  entityStream An input stream
     * @throws IOException
     * @throws WebApplicationException
     */
    @Override
    public void writeTo(Object o,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException, WebApplicationException
    {
        Writer writer = new OutputStreamWriter(entityStream);
        try
        {
            gson.toJson(o, genericType, writer);
        }
        finally
        {
            writer.close();
        }
    }
}
