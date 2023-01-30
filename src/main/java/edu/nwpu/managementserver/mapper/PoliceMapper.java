package edu.nwpu.managementserver.mapper;

import edu.nwpu.managementserver.domain.Police;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Faust
* @description 针对表【police】的数据库操作Mapper
* @createDate 2023-01-18 12:09:20
* @Entity generator.domain.Police
*/
@Mapper
public interface PoliceMapper {
    List<Police> getPoliceListByNameLike(@Param("query") String query,@Param("prison_id")long prison_id);
    Police getById(long id);
    List<Police> getList();
    int save(Police police);
    int updateById(Police police);
    int deleteByIdList(List<Long> idList);


    Police getPoliceByAccountId(Long id);
}




