package edu.nwpu.managementserver.service;

import edu.nwpu.managementserver.domain.TrainingModel;
import edu.nwpu.managementserver.dto.ModelParam;

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
}
