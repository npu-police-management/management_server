package com.nwpu.managementserver.service;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
public interface RefreshTokenService {

    void saveRefreshToken(long id, String subject, String refreshToken);

    String getRefreshTokenById(long id);

    void deleteAllBySubject(String subject);
}
