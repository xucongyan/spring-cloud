package com.spring.cloudcommon.util;

import com.spring.cloudcommon.bean.JWTInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xucongyan
 */

public class JwtTokenUtil {

    private static int expire = 100;

    //@Autowired
    //private KeyConfiguration keyConfiguration;

    public static String generateToken(JWTInfo jwtInfo) {
        return JWTHelper.generateToken(jwtInfo, null, expire);
    }

}
