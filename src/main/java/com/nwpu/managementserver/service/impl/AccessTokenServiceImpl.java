package com.nwpu.managementserver.service.impl;

import com.nwpu.managementserver.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

import static com.nwpu.managementserver.constant.RedisConstant.S_ACCESS_TOKEN_BLACKLIST;

/**
 * @author Jiayi Zhu
 * 2023/1/25
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {

        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void addToBlacklist(String accessToken) {

        stringRedisTemplate.opsForSet().add(S_ACCESS_TOKEN_BLACKLIST, accessToken);
    }

    @Override
    public boolean existInBlacklist(String accessToken) {

        return Boolean.TRUE.equals(
                stringRedisTemplate.opsForSet().isMember(S_ACCESS_TOKEN_BLACKLIST, accessToken));
    }

    @Override
    public void removeFromBlacklistIf(Predicate<String> condition) {

    }
}
