package edu.nwpu.managementserver.mapper;

import edu.nwpu.managementserver.domain.PoliceTraining;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Faust
* @description 针对表【police_training】的数据库操作Mapper
* @createDate 2023-01-18 12:09:20
* @Entity generator.domain.PoliceTraining
*/
@Mapper
public interface PoliceTrainingMapper {

    List<PoliceTraining> getByPoliceId(Long policeId);
}




