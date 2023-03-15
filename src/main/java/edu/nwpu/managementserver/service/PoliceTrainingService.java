package edu.nwpu.managementserver.service;

import edu.nwpu.managementserver.vo.*;
import org.springframework.stereotype.Service;
import edu.nwpu.managementserver.domain.PoliceTraining;

import java.util.List;

/**
 * @author Jiayi Zhu
 * 2023/2/6
 */
public interface PoliceTrainingService {

    TrainingSituationVO getTrainingSituation(long policeId);

    List<PoliceTraining> getDynamic(long policeId);

    List<Integer> weekTraining(long policeId);

    Integer weekTrainingCounts();

    Integer todayTrainingCounts();

    boolean isTraining(long policeId);

    void pauseTraining(long trainingId);

    void cancelTraining(long trainingId);

    PoliceTraining stopTraining(long trainingId);

    List<AdminDynamicVO> getDynamic();

    int getNumberTodayFinish(long prisonId);
    int getNumberWeekFinish(long prisonId);
    List<PrisonAdminMainPageDynamicVO> getThreeDate(long prisonId);
    List<PrisonAdminMainPageStatsVO> getWeeklyStatus(long prisonId);

    List<TrainingDynamicForPoliceVO> getTrainingDynamicListForPolice(Long id, String query);

    List<TrainingDynamicVO> queryTrainingDynamicForPrisonAdmin(String police, String modelName,long prisonId);

    int addOne(PoliceTraining policeTraining);
}
