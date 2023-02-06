package edu.nwpu.managementserver.service;

import edu.nwpu.managementserver.domain.PrisonModel;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/6 18:03
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.service.PrionModelService
 * @description:
 */
public interface PrisonModelService {
    int getTheOpeningModeSizeForPrison(Long prisonId);
    int addOne(PrisonModel prisonModel);
    int deleteOne(long prisonId, long modelId);
    List<Long> getModelIdListForPrisonId(long prisonId);
}
