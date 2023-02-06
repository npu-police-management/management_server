package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.domain.PrisonModel;
import edu.nwpu.managementserver.mapper.PrisonModelMapper;
import edu.nwpu.managementserver.service.PrisonModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/6 18:03
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.service.impl.PrisonModelServiceImpl
 * @description:
 */
public class PrisonModelServiceImpl implements PrisonModelService {
    @Autowired
    PrisonModelMapper prisonModelMapper;

    @Override
    public int getTheOpeningModeSizeForPrison(Long prisonId) {
	return prisonModelMapper.getModelIdSizeByPrisonId(prisonId);
    }

    @Override
    public int addOne(PrisonModel prisonModel) {
	return prisonModelMapper.addRecord(prisonModel);
    }

    @Override
    public int deleteOne(long prisonId, long modelId) {
	return prisonModelMapper.deleteRecord(prisonId,modelId);
    }

    @Override
    public List<Long> getModelIdListForPrisonId(long prisonId) {
	return prisonModelMapper.getModelIdListByPrisonId(prisonId);
    }
}
