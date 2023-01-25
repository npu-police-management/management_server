package com.nwpu.managementserver.service;

import java.util.function.Predicate;

/**
 * @author Jiayi Zhu
 * 2023/1/25
 */
public interface AccessTokenService {

    void addToBlacklist(String accessToken);

    boolean existInBlacklist(String accessToken);

    void removeFromBlacklistIf(Predicate<String> condition);
}
