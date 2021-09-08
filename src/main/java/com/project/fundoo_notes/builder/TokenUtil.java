package com.project.fundoo_notes.builder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;


/**
 * purpose : To create and decode token
 * @author : Swati
 * @version : 1.0
 * @since : 8-7-21
 **/

@Component
public class TokenUtil {
    private static final long EXPIRE_DATE=30*60*100000;
    private static final String TOKEN_SECRET = "ZCEQIUBFKSJBFJH2020BQWE";

    /**
     * @param : id
     * @return : token
     */
    public  String createToken(Long id)   {
        try {
            //to set algorithm
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            String token = JWT.create()
                    .withClaim("user_id", id)
                    .sign(algorithm);
            System.out.println(token);
            return token;
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
            //log Token Signing Failed
        } catch (IllegalArgumentException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @param :token
     * @return : id
     */
    public Long decodeToken(String token)
    {
        Long userid;
        //for verification algorithm
        Verification verification = null;
        try {
            verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException  e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        JWTVerifier jwtverifier=verification.build();
        //to decode token
        DecodedJWT decodedjwt=jwtverifier.verify(token);

        Claim claim=decodedjwt.getClaim("user_id");
        userid=claim.asLong();
        return userid;
    }

}