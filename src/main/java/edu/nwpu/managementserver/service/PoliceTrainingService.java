package edu.nwpu.managementserver.service;

import org.springframework.stereotype.Service;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/6 20:35
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.service.PoliceTrainingService
 * @description:
 */
public interface PoliceTrainingService {
    int getNumberTodayFinish(long prisonId);
    int getNumberWeekFinish(long prisonId);

}
