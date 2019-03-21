package com.zhanyd.app.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by zhanyd@sina.com on 2018/2/15 0015.
 */
public class JwtUtils {

    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private static final String secret = "zhanyd";


    /**
     * 签发jwt
     * @param tel
     * @return token
     */
    public static String signJWT(Integer userId){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("zhanyd")
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000*60*5))  //过期时间
                    .withClaim("userId", userId)
                    .sign(algorithm);

            return token;
        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
            logger.error(exception.getMessage());
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            logger.error(exception.getMessage());
        }
        return null;
    }


    /**
     * 验证jwt
     * @param token
     * @return tel
     */
    public static Integer verifyJWT(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("zhanyd")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            Claim claim = jwt.getClaim("userId");
            return claim.asInt();
        } catch (UnsupportedEncodingException exception){
            exception.printStackTrace();
            logger.error(exception.getMessage());
        } catch (JWTVerificationException exception){
            exception.printStackTrace();
            logger.error(exception.getMessage());
        }
        return null;
    }
    
    /**
     * 获取用户id
     * @param request
     * @return
     */
    public static String getUserId(HttpServletRequest request) {
    	String token = request.getHeader("Authorization");
		return StringHelp.valueOf(JwtUtils.verifyJWT(token));
    }

    public static void main(String[] args){
        String token = signJWT(1);
        System.out.println(token);
        System.out.println(verifyJWT(token));
        /*try{
            System.out.println("sleeping");
            Thread.sleep(1000*10);
        }catch(Exception e){
            e.printStackTrace();
        }*/
        //System.out.println(verifyJWT(token));
       //verifyJWT("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ5aWx1IiwibmFtZSI6IjEyMyIsImV4cCI6MTUxODc1NDYzMn0.82a9YUSdOr4hwmU3xWFVRV_WapNZyW-9zWKI0P8wSZs");
    }
}
