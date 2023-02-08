package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.constant.CodeEnum;
import edu.nwpu.managementserver.domain.TrainingModel;
import edu.nwpu.managementserver.dto.ModelParam;
import edu.nwpu.managementserver.exception.BusinessException;
import edu.nwpu.managementserver.mapper.TrainingModelMapper;
import edu.nwpu.managementserver.service.TrainingModelService;
import edu.nwpu.managementserver.util.SnowflakeIdUtil;
import edu.nwpu.managementserver.domain.TrainingModel;
import edu.nwpu.managementserver.mapper.TrainingModelMapper;
import edu.nwpu.managementserver.service.TrainingModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jiayi Zhu
 * 2023/2/6
 */
@Service
public class TrainingModelServiceImpl implements TrainingModelService {

    @Autowired
    private TrainingModelMapper trainingModelMapper;

    @Override
    public TrainingModel addModel(ModelParam param) {

        TrainingModel oldModel = trainingModelMapper.findByName(param.getName());
        if (oldModel != null) {
            throw new BusinessException(CodeEnum.CreationError, "该模型已存在");
        }
        if (param.getEnable() == null) {
            param.setEnable(true);
        }
        if (param.getPriority() == null) {
            param.setPriority(1);
        }
        TrainingModel model = new TrainingModel(
                SnowflakeIdUtil.nextId(),
                param.getName(),
                param.getDescription(),
                param.getEnable(),
                param.getPriority()
        );
        trainingModelMapper.insert(model);
        return model;
    }

    @Override
    public void updateModel(long id,
                            ModelParam param) {

        TrainingModel model = trainingModelMapper.findById(id);
        if (model == null) {
            throw new BusinessException(CodeEnum.NotFound, "找不到该训练模型");
        }
        if (param.getName() != null) {
            model.setName(param.getName());
        }
        if (param.getDescription() != null) {
            model.setDescription(param.getDescription());
        }
        if (param.getEnable() != null) {
            model.setEnable(param.getEnable());
        }
        if (param.getPriority() != null) {
            model.setPriority(param.getPriority());
        }
        trainingModelMapper.updateById(model);
    }

    @Override
    public void deleteById(List<Long> idList) {

        trainingModelMapper.deleteById(idList);
    }

    @Override
    public List<TrainingModel> queryTrainingModel(String query) {

        return trainingModelMapper.getAllByQuery(query);
    }

    @Override
    public TrainingModel getById(long id) {

        TrainingModel model = trainingModelMapper.findById(id);
        if (model == null) {
            throw new BusinessException(CodeEnum.NotFound, "模型不存在");
        }
        return model;
    }
}
