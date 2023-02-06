package edu.nwpu.managementserver.service;

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
}
