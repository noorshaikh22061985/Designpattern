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

import com.google.gson.Gson;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a utility class for JWT functions.
 *
 * @access public
 * @author Phakamani Mavela (phakamanim@wigroup.co.za)
 * @return void
 */
public class JWTUtils
{
    private Logger LOG = LoggerFactory.getLogger(JWTUtils.class);
    
    private final String secret;
    private final Gson   gson;

    /**
     * This a class constructor instantiating JWT secrete and Gson object.
     *
     * @access public
     * @param  secret JWT secrete
     */
    public JWTUtils(String secret)
    {
        this.secret = secret;
        this.gson   = new Gson();
    }

    /**
     * This fucntion generates the API payload.
     *
     * @access public
     * @param  apiConsumerId    API key
     * @param  OrderId          Order unique identifier
     * @param  issueDateMillis  Request date
     * @param  expiryDateMillis Request expiry
     * @return Jwts build
     */
    public String generate(String apiConsumerId, Long OrderId, Long issueDateMillis, Long expiryDateMillis)
    {
        JWTPayload payload = new JWTPayload();
        payload.setApiConsumerId(apiConsumerId);
        payload.setOrderId(OrderId);
        payload.setIssueDateMillis(issueDateMillis);
        payload.setExpiryDateMillis(expiryDateMillis);
        
        return Jwts.builder().setPayload(gson.toJson(payload)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * Checks the token against the signing key and returns the payload if the signing key is valid.
     *
     * @access public
     * @param  token JWT token
     * @return the payload (body) of the token or null if the signing key is invalid.
     */
    public JWTPayload getPayload(String token)
    {
        if (token != null && !token.isEmpty())
        {
            try
            {
                Jwt jwt = Jwts.parser().setSigningKey(secret).parse(token);
                
                JWTPayload payload = gson.fromJson(jwt.getBody().toString(), JWTPayload.class);
                return payload;
            }
            catch (Exception exception)
            {
                LOG.error("Unable to validate token", exception);
            }
        }
        
        return null;
    }

    /**
     * This function initiates the JWT utility functions.
     *
     * @access public
     * @param args string arguments
     * @throws Exception
     * @return void
     */
    public static void main(String[] args) throws Exception
    {
        String signingKey = "my dirty little secret";
        
        long now = System.currentTimeMillis();
        
        JWTUtils   jwtUtils  = new JWTUtils(signingKey);
        String     authToken = jwtUtils.generate("api-consumer-id", 2L, now, now + 60*60*1000L);
        JWTPayload payload   = jwtUtils.getPayload(authToken);

        System.err.println("consumer:    " + payload.getApiConsumerId());
        System.err.println("subscriber:  " + payload.getOrderId());
        System.err.println("issue date:  " + payload.getIssueDate());
        System.err.println("expiry date: " + payload.getExpiryDate());
        
        String authTokenNoSig = authToken.substring(0, authToken.lastIndexOf('.') + 1);

        Jwt jwt = Jwts.parser().parse(authTokenNoSig);
        System.err.println(jwt.getBody());
    }
}
