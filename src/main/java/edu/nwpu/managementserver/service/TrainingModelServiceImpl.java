package edu.nwpu.managementserver.service;

import edu.nwpu.managementserver.domain.TrainingModel;
import edu.nwpu.managementserver.mapper.TrainingModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/7 15:26
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.service.TrainingModelServiceImpl
 * @description:
 */
public class TrainingModelServiceImpl implements TrainingModelService{
    @Autowired
    private TrainingModelMapper trainingModelMapper;
    @Override
    public List<TrainingModel> getTrainingModelForPrisonAdmin() {
	return trainingModelMapper.getModelListForPrisonAdmin();
    }
}
