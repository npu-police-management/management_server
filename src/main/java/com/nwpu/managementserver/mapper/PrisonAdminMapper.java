package com.nwpu.managementserver.mapper;

import com.nwpu.managementserver.domain.PrisonAdmin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Faust
* @description 针对表【prison_admin】的数据库操作Mapper
* @createDate 2023-01-18 12:09:20
* @Entity generator.domain.PrisonAdmin
*/
@Mapper
public interface PrisonAdminMapper {

    int insert(PrisonAdmin prisonAdmin);

    PrisonAdmin getById(Long id);

    PrisonAdmin getByAccountId(Long accountId);

    List<PrisonAdmin> getAllLike(String query);

    int deleteById(List<Long> idList);

    int deleteByPrisonId(List<Long> idList);

    List<Long> getPrisonIdByAccountId(long id);
}




