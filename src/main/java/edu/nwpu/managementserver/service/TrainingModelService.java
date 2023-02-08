package edu.nwpu.managementserver.service;

import edu.nwpu.managementserver.domain.TrainingModel;
import edu.nwpu.managementserver.dto.ModelParam;
import edu.nwpu.managementserver.mapper.TrainingModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jiayi Zhu
 * 2023/2/6
 */
public interface TrainingModelService {


    TrainingModel addModel(ModelParam param);

    void updateModel(long id,
                     ModelParam param);

    void deleteById(List<Long> idList);

    List<TrainingModel> queryTrainingModel(String query);

    TrainingModel getById(long id);

    List<TrainingModel> getTrainingModelForPrisonAdmin(String query);

    List<String> getTrainingModelNameForPrisonAdmin();
}
