package edu.nwpu.managementserver.mapper;

import edu.nwpu.managementserver.domain.TrainingModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Faust
* @description 针对表【training_model】的数据库操作Mapper
* @createDate 2023-01-18 12:09:20
* @Entity generator.domain.TrainingModel
*/
@Mapper
public interface TrainingModelMapper {
    List<TrainingModel> getModelListForPrisonAdmin();

    int insert(TrainingModel trainingModel);

    TrainingModel findByName(String name);

    TrainingModel findById(Long id);

    int updateById(TrainingModel trainingModel);

    int deleteById(List<Long> idList);

    List<TrainingModel> getAllByQuery(String query);
}




