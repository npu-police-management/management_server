package com.nwpu.managementserver.mapper;

import com.nwpu.managementserver.domain.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Faust
* @description 针对表【admin】的数据库操作Mapper
* @createDate 2023-01-18 12:09:20
* @Entity generator.domain.Admin
*/
@Mapper
public interface AdminMapper {

    Admin getByAccountId(Long accountId);
}




