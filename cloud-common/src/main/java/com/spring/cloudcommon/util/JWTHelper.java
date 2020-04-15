package com.spring.cloudcommon.util;


import com.spring.cloudcommon.bean.JWTInfo;
import com.spring.cloudcommon.constant.CommonConstants;
import io.jsonwebtoken.Jwts;
import org.joda.time.DateTime;

/**
 * @author xucongyan
 */
public class JWTHelper {

    //private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();

    public static String generateToken(JWTInfo jwtInfo, byte[] priKey, int expire) {
        return Jwts.builder()
                //.setSubject(jwtInfo.getUniqueName())
                //.claim(CommonConstants.JWT_KEY_USER_ID, jwtInfo.getId())
                .claim(CommonConstants.JWT_KEY_NAME, jwtInfo.getName())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                //.signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKey))
                .compact();
    }
}
