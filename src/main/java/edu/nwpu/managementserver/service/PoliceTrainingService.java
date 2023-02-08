package edu.nwpu.managementserver.service;

import edu.nwpu.managementserver.vo.PrisonAdminMainPageDynamicVO;
import edu.nwpu.managementserver.vo.PrisonAdminMainPageStatsVO;
import edu.nwpu.managementserver.vo.TrainingDynamicForPoliceVO;
import edu.nwpu.managementserver.vo.TrainingDynamicVO;
import org.springframework.stereotype.Service;
import edu.nwpu.managementserver.domain.PoliceTraining;
import edu.nwpu.managementserver.vo.TrainingSituationVO;

import java.util.List;

/**
 * @author Jiayi Zhu
 * 2023/2/6
 */
public interface PoliceTrainingService {

    TrainingSituationVO getTrainingSituation(long policeId);

    List<PoliceTraining> getDynamic(long policeId);

    List<Integer> weekTraining(long policeId);

    boolean isTraining(long policeId);
    int getNumberTodayFinish(long prisonId);
    int getNumberWeekFinish(long prisonId);
    List<PrisonAdminMainPageDynamicVO> getThreeDate(long prisonId);
    List<PrisonAdminMainPageStatsVO> getWeeklyStatus(long prisonId);

    List<TrainingDynamicForPoliceVO> getTrainingDynamicListForPolice(Long id, String query);

    List<TrainingDynamicVO> queryTrainingDynamicForPrisonAdmin(String police, String modelName,long prisonId);
}
