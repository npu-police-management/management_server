package com.nwpu.managementserver.mapper;

import com.nwpu.managementserver.domain.PrisonAdmin;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Faust
* @description 针对表【prison_admin】的数据库操作Mapper
* @createDate 2023-01-18 12:09:20
* @Entity generator.domain.PrisonAdmin
*/
@Mapper
public interface PrisonAdminMapper {

    int insert(PrisonAdmin prisonAdmin);

    PrisonAdmin getByAccountId(Long accountId);
}




