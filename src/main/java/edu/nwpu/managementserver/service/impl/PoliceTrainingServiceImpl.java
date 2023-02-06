package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.service.PoliceTrainingService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/6 20:36
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.service.impl.PoliceTrainingServiceImpl
 * @description:
 */
public class PoliceTrainingServiceImpl implements PoliceTrainingService {
    @Autowired
    PoliceTrainingService policeTrainingService;
    @Override
    public int getNumberTodayFinish(long prisonId) {
	return policeTrainingService.getNumberTodayFinish(prisonId);
    }
}
