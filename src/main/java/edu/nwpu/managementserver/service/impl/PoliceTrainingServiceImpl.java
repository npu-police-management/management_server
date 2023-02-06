package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.mapper.PoliceTrainingMapper;
import edu.nwpu.managementserver.service.PoliceTrainingService;
import edu.nwpu.managementserver.vo.PrisonAdminMainPageDynamicVO;
import edu.nwpu.managementserver.vo.PrisonAdminMainPageStatsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/6 20:36
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.service.impl.PoliceTrainingServiceImpl
 * @description:
 */
@Service
public class PoliceTrainingServiceImpl  implements PoliceTrainingService {
    @Autowired
    PoliceTrainingMapper policeTrainingMapper;
    @Override
    public int getNumberTodayFinish(long prisonId) {
	return policeTrainingMapper.getNumberTodayFinish(prisonId);
    }

    @Override
    public int getNumberWeekFinish(long prisonId) {
	return policeTrainingMapper.getNumberWeekFinish(prisonId);
    }

    @Override
    public List<PrisonAdminMainPageDynamicVO> getThreeDate(long prisonId) {
	return policeTrainingMapper.getThreeDate(prisonId);
    }

    @Override
    public List<PrisonAdminMainPageStatsVO> getWeeklyStatus(long prisonId) {
	return policeTrainingMapper.getWeeklyStatus(prisonId);
    }
}
