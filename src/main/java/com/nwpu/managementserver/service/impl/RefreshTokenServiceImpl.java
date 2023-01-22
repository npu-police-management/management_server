package com.nwpu.managementserver.service.impl;

import com.nwpu.managementserver.domain.RefreshToken;
import com.nwpu.managementserver.exception.JwtAuthException;
import com.nwpu.managementserver.mapper.RefreshTokenMapper;
import com.nwpu.managementserver.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private RefreshTokenMapper refreshTokenMapper;

    @Autowired
    public void setRefreshTokenMapper(RefreshTokenMapper refreshTokenMapper) {

        this.refreshTokenMapper = refreshTokenMapper;
    }

    @Override
    public void saveRefreshToken(long id, String refreshToken) {

        refreshTokenMapper.insert(RefreshToken.of(id, refreshToken));
    }

    @Override
    public String getRefreshTokenById(long id) throws JwtAuthException {

        String token = refreshTokenMapper.getById(id).getToken();
        if (token == null) {
            throw JwtAuthException.RefreshTokenNotFound;
        }
        return token;
    }
}
