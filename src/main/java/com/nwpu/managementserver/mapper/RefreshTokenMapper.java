package com.nwpu.managementserver.mapper;

import com.nwpu.managementserver.domain.RefreshToken;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ANO4679
* @description 针对表【refresh_token】的数据库操作Mapper
* @createDate 2023-01-20 19:05:06
* @Entity com.nwpu.managementserver.domain.RefreshToken
*/
@Mapper
public interface RefreshTokenMapper {

    int insert(RefreshToken refreshToken);

    RefreshToken getById(Long id);

    int deleteAllBySubject(String subject);
}




