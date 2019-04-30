package com.jinnian.framework.common.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * @author liuqi
 * @date 2019/4/13
 */

@Component
public class TokenCache {

    private static final String GPS_TOKEN_ACCESS_KEY = "gps:token:access";

    private static final String GPS_TOKEN_ACCESS_FOREVER_KEY = "gps:token:access:forever";

    private static final String GPS_TOKEN_REFRESH_KEY = "gps:token:refresh";

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public String[] getToken() {
        String[] result = new String[3];
        BoundValueOperations<String, String> boundValueOpsAcc = stringRedisTemplate.boundValueOps(GPS_TOKEN_ACCESS_KEY);
        BoundValueOperations<String, String> boundValueOpsRef = stringRedisTemplate.boundValueOps(GPS_TOKEN_REFRESH_KEY);
        BoundValueOperations<String, String> boundValueOpsForeverAcc = stringRedisTemplate.boundValueOps(GPS_TOKEN_ACCESS_FOREVER_KEY);
        String accToken = boundValueOpsAcc.get();
        String refToken = boundValueOpsRef.get();
        String foreverAccToken = boundValueOpsForeverAcc.get();
        if (StringUtils.isNotBlank(accToken)) {
            result[0] = accToken;
        } else {
            result[0] = "";
        }
        if (StringUtils.isNotBlank(refToken)) {
            result[1] = refToken;
        } else {
            result[1] = "";
        }
        if (StringUtils.isNotBlank(foreverAccToken)) {
            result[2] = foreverAccToken;
        } else {
            result[2] = "";
        }
        return result;
    }

    public void insertToken(String accessToken, String refreshToken) {
        BoundValueOperations<String, String> boundValueOpsAcc = stringRedisTemplate.boundValueOps(GPS_TOKEN_ACCESS_KEY);
        BoundValueOperations<String, String> boundValueOpsRef = stringRedisTemplate.boundValueOps(GPS_TOKEN_REFRESH_KEY);
        BoundValueOperations<String, String> boundValueOpsForeverAcc = stringRedisTemplate.boundValueOps(GPS_TOKEN_ACCESS_FOREVER_KEY);

        boundValueOpsAcc.set(accessToken, 7200, TimeUnit.SECONDS);
        boundValueOpsRef.set(refreshToken);
        boundValueOpsForeverAcc.set(accessToken);
    }
}
