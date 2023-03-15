package edu.nwpu.managementserver.mapper;

import edu.nwpu.managementserver.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;

import edu.nwpu.managementserver.domain.PoliceTraining;
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

    List<TrainingDynamicVO> queryTrainingDynamicForPrisonAdmin(@Value("police") String police,@Value("modelName") String modelName,@Value("prisonId") long prisonId);

    List<PoliceTraining> getByPoliceId(Long policeId);

    int addOne(PoliceTraining policeTraining);

    List<AdminDynamicVO> getTrainingDynamicOrderByTimeDesc();

    Integer getWeekTrainingCounts();

    Integer getTodayTrainingCounts();

    int setStatus(long id, int status);

    int setResult(long id, String result);

    int setEndTime(long id, LocalDateTime endTime);

    Integer getStatus(long id);

    int deleteById(long id);

    PoliceTraining getById(long id);
}




