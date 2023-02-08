package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.domain.TrainingModel;
import edu.nwpu.managementserver.mapper.TrainingModelMapper;
import edu.nwpu.managementserver.service.TrainingModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/7 15:26
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.service.impl.TrainingModelServiceImpl
 * @description:
 */
@Service
public class TrainingModelServiceImpl implements TrainingModelService {
    @Autowired
    private TrainingModelMapper trainingModelMapper;
    @Override
    public List<TrainingModel> getTrainingModelForPrisonAdmin() {
	return trainingModelMapper.getModelListForPrisonAdmin();
    }

    @Override
    public List<String> getTrainingModelNameForPrisonAdmin() {
        return trainingModelMapper.getTrainingModelNameForPrisonAdmin();
    }
}
