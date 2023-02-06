package edu.nwpu.managementserver.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
* @author Faust
* @description 针对表【access_record】的数据库操作Mapper
* @createDate 2023-01-18 12:08:07
* @Entity generator.domain.AccessRecord
*/
@Mapper
public interface AccessRecordMapper {
    int getNumberTodayAccess(long prisonId);
}




