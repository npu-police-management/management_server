package edu.nwpu.managementserver.mapper;

import edu.nwpu.managementserver.domain.Prison;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Faust
* @description 针对表【prison】的数据库操作Mapper
* @createDate 2023-01-18 12:09:20
* @Entity generator.domain.Prison
*/
@Mapper
public interface PrisonMapper {

    int insert(Prison prison);

    Prison getByName(String name);

    Prison getById(Long id);

    List<Prison> getAllByNameLike(String query);

    int deleteById(List<Long> idList);

    List<String> getPrisonNameList();
}




