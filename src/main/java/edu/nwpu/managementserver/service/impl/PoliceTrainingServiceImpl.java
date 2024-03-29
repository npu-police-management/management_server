package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.constant.CodeEnum;
import edu.nwpu.managementserver.constant.TrainingStatusEnum;
import edu.nwpu.managementserver.domain.PoliceTraining;
import edu.nwpu.managementserver.exception.BusinessException;
import edu.nwpu.managementserver.mapper.PoliceTrainingMapper;
import edu.nwpu.managementserver.service.PoliceTrainingService;
import edu.nwpu.managementserver.util.WeekUtil;
import edu.nwpu.managementserver.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static edu.nwpu.managementserver.util.WeekUtil.getCurrentDayOfWeek;
import static java.time.DayOfWeek.*;

/**
 * @author Jiayi Zhu
 * 2023/2/6
 */
@Service
public class PoliceTrainingServiceImpl implements PoliceTrainingService {

    @Autowired
    PoliceTrainingMapper policeTrainingMapper;

    @Override
    public TrainingSituationVO getTrainingSituation(long policeId) {

        LocalDateTime currentTime = LocalDateTime.now();
        int day = currentTime.getDayOfWeek().getValue();

        List<PoliceTraining> policeTrainingList = policeTrainingMapper.getByPoliceId(policeId);

        List<PoliceTraining> weeklyTaskList = policeTrainingList.stream().filter(
                policeTraining -> WeekUtil.isSameWeek(currentTime, policeTraining.getStartTime())
        ).toList();
        List<PoliceTraining> dailyTaskList = weeklyTaskList.stream().filter(
                policeTraining -> policeTraining.getStartTime().getDayOfWeek().getValue() == day
        ).toList();

        int dailyTasks = dailyTaskList.size();
        int dailyCompletedTasks = dailyTaskList.stream().filter(
                policeTraining -> policeTraining.getEndTime().isBefore(currentTime)
        ).toList().size();
        int weeklyModels = weeklyTaskList.size();

        return new TrainingSituationVO(dailyTasks,dailyCompletedTasks,weeklyModels);
    }

    @Override
    public List<PoliceTraining> getDynamic(long policeId) {

        List<PoliceTraining> policeTrainingList = policeTrainingMapper.getByPoliceId(policeId);

        policeTrainingList.sort((p1, p2) -> {
            if (p1.getStartTime().isAfter(p2.getStartTime())) {
                return -1;
            } else if (p1.getStartTime().isBefore(p2.getStartTime())) {
                return 1;
            }
            else return 0;
        });
        if (policeTrainingList.size() <= 3) {
            return policeTrainingList;
        }
        else return policeTrainingList.subList(0, 3);
    }

    @Override
    public List<Integer> weekTraining(long policeId) {

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDate currentDate = LocalDate.now();

        List<PoliceTraining> policeTrainingList = policeTrainingMapper.getByPoliceId(policeId);

        List<PoliceTraining> weekList = policeTrainingList.stream()
                .filter(policeTraining -> WeekUtil.isSameWeek(currentTime, policeTraining.getStartTime()))
                .toList();

        return List.of(
                weekList.stream().filter(policeTraining -> policeTraining.getStartTime().toLocalDate()
                        .isEqual(getCurrentDayOfWeek(currentDate, MONDAY))).toList().size(),
                weekList.stream().filter(policeTraining -> policeTraining.getStartTime().toLocalDate()
                        .isEqual(getCurrentDayOfWeek(currentDate, TUESDAY))).toList().size(),
                weekList.stream().filter(policeTraining -> policeTraining.getStartTime().toLocalDate()
                        .isEqual(getCurrentDayOfWeek(currentDate, WEDNESDAY))).toList().size(),
                weekList.stream().filter(policeTraining -> policeTraining.getStartTime().toLocalDate()
                        .isEqual(getCurrentDayOfWeek(currentDate, THURSDAY))).toList().size(),
                weekList.stream().filter(policeTraining -> policeTraining.getStartTime().toLocalDate()
                        .isEqual(getCurrentDayOfWeek(currentDate, FRIDAY))).toList().size(),
                weekList.stream().filter(policeTraining -> policeTraining.getStartTime().toLocalDate()
                        .isEqual(getCurrentDayOfWeek(currentDate, SATURDAY))).toList().size(),
                weekList.stream().filter(policeTraining -> policeTraining.getStartTime().toLocalDate()
                        .isEqual(getCurrentDayOfWeek(currentDate, SUNDAY))).toList().size()
        );
    }

    @Override
    public Integer weekTrainingCounts() {

        return policeTrainingMapper.getWeekTrainingCounts();
    }

    @Override
    public Integer todayTrainingCounts() {

        return policeTrainingMapper.getTodayTrainingCounts();
    }

    @Override
    public boolean isTraining(long policeId) {

        LocalDateTime currentTime = LocalDateTime.now();

        List<PoliceTraining> policeTrainingList = policeTrainingMapper.getByPoliceId(policeId);
        return policeTrainingList.stream().anyMatch(
                policeTraining ->
                    policeTraining.getStartTime().isBefore(currentTime)
                && policeTraining.getEndTime().isAfter(currentTime)
        );
    }

    @Override
    public void pauseTraining(long trainingId) {

        int status = policeTrainingMapper.getStatus(trainingId);
        if (status == TrainingStatusEnum.Training.getValue()) {
            policeTrainingMapper.setStatus(trainingId,
                                           TrainingStatusEnum.Paused.getValue());
        } else if (status == TrainingStatusEnum.Paused.getValue()) {
            policeTrainingMapper.setStatus(trainingId,
                                           TrainingStatusEnum.Training.getValue());
        }
    }

    @Override
    public void cancelTraining(long trainingId) {

        policeTrainingMapper.deleteById(trainingId);
    }

    @Override
    public PoliceTraining stopTraining(long trainingId) {

        PoliceTraining policeTraining = policeTrainingMapper.getById(trainingId);
        if (policeTraining == null) {
            throw new BusinessException(CodeEnum.NotFound, "不存在该训练记录");
        }
        System.out.println(policeTraining.getPoliceId());
        policeTrainingMapper.setStatus(trainingId, TrainingStatusEnum.Finished.getValue());
        policeTrainingMapper.setEndTime(trainingId, LocalDateTime.now());
        return policeTraining;
    }

    @Override
    public List<AdminDynamicVO> getDynamic() {

        List<AdminDynamicVO> adminDynamicVOList = policeTrainingMapper.getTrainingDynamicOrderByTimeDesc();
        return adminDynamicVOList.size() <= 3 ?
                adminDynamicVOList : adminDynamicVOList.subList(0, 3);
    }

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

    @Override
    public List<TrainingDynamicForPoliceVO> getTrainingDynamicListForPolice(Long id, String query) {

        List<TrainingDynamicForPoliceVO> list = policeTrainingMapper.getTrainingDynamicListForPolice(id, query);
        try {
            for (TrainingDynamicForPoliceVO trainingDynamic : list) {
                Integer v = Integer.valueOf(trainingDynamic.getStatus());
                trainingDynamic.setStatus(TrainingStatusEnum.nameOf(v));
            }
        } catch (NumberFormatException | NoSuchElementException e) {
            throw new BusinessException(CodeEnum.RequestError, "训练状态出错");
        }
        return list;
    }

    @Override
    public List<TrainingDynamicVO> queryTrainingDynamicForPrisonAdmin(String police, String modelName,long prisonId) {
        List<TrainingDynamicVO> list = policeTrainingMapper.queryTrainingDynamicForPrisonAdmin(police,modelName,prisonId);
        try {
            for (TrainingDynamicVO trainingDynamicVO : list) {
                Integer v = Integer.valueOf(trainingDynamicVO.getStatus());
                trainingDynamicVO.setStatus(TrainingStatusEnum.nameOf(v));
            }
        } catch (NumberFormatException | NoSuchElementException e) {
            throw new BusinessException(CodeEnum.RequestError, "训练状态出错");
        }
        return list;
    }

    @Override
    public int addOne(PoliceTraining policeTraining) {
        return policeTrainingMapper.addOne(policeTraining);
    }
}
