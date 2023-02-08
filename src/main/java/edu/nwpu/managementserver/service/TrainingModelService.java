package edu.nwpu.managementserver.service;

import edu.nwpu.managementserver.domain.TrainingModel;
import edu.nwpu.managementserver.mapper.TrainingModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/7 15:24
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.service.TrainingModelService
 * @description:
 */
@Service
public interface TrainingModelService {
    List<TrainingModel> getTrainingModelForPrisonAdmin();

    List<String> getAllTrainingModelNames();
}
