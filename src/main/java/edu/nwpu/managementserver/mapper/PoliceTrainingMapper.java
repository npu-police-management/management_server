package edu.nwpu.managementserver.mapper;

import edu.nwpu.managementserver.vo.PrisonAdminMainPageDynamicVO;
import edu.nwpu.managementserver.vo.PrisonAdminMainPageStatsVO;
import edu.nwpu.managementserver.vo.TrainingDynamicForPoliceVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
* @author Faust
* @description 针对表【police_training】的数据库操作Mapper
* @createDate 2023-01-18 12:09:20
* @Entity generator.domain.PoliceTraining
*/
@Mapper
public interface PoliceTrainingMapper {
    int getNumberTodayFinish(long prisonId);
    int getNumberWeekFinish(long prisonId);
    List<PrisonAdminMainPageDynamicVO> getThreeDate(long PrisonId);
    List<PrisonAdminMainPageStatsVO> getWeeklyStatus(long prisonId);

    List<TrainingDynamicForPoliceVO> getTrainingDynamicListForPolice(@Value("id") Long id,@Value("query") String query);
}




