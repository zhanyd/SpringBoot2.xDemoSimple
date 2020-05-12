package com.canaan.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;


/**
 *
 * @author zhanyd
 */
public final class JwtUtils {

    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private static final String SECRET = "canaan";
    private static final String ISS = "canaan";

    private JwtUtils() {

    }
    /**
     * 签发jwt
     *
     * @param userId
     * @return token
     */
    public static String signJWT(Integer userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withIssuer(ISS)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                    .withClaim("userId", userId)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException | JWTCreationException exception) {
            //UTF-8 encoding not supported
            logger.error(exception.getMessage());
        }
        return null;
    }


    /**
     * 验证jwt
     *
     * @param token
     * @return tel
     */
    public static Integer verifyJWT(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISS)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            Claim claim = jwt.getClaim("userId");
            return claim.asInt();
        } catch (UnsupportedEncodingException | JWTVerificationException exception) {
            logger.error(exception.getMessage());
        }
        return null;
    }

    /**
     * 获取用户id
     *
     * @param request
     * @return
     */
    public static Integer getUserId(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return JwtUtils.verifyJWT(token);
    }

}
