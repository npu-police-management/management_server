package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.domain.PoliceTraining;
import edu.nwpu.managementserver.mapper.PoliceTrainingMapper;
import edu.nwpu.managementserver.service.PoliceTrainingService;
import edu.nwpu.managementserver.util.WeekUtil;
import edu.nwpu.managementserver.vo.TrainingSituationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static edu.nwpu.managementserver.util.WeekUtil.getCurrentDayOfWeek;
import static java.time.DayOfWeek.*;

/**
 * @author Jiayi Zhu
 * 2023/2/6
 */
@Service
public class PoliceTrainingServiceImpl implements PoliceTrainingService {

    @Autowired
    private PoliceTrainingMapper policeTrainingMapper;

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
    public boolean isTraining(long policeId) {

        LocalDateTime currentTime = LocalDateTime.now();

        List<PoliceTraining> policeTrainingList = policeTrainingMapper.getByPoliceId(policeId);
        return policeTrainingList.stream().anyMatch(
                policeTraining ->
                    policeTraining.getStartTime().isBefore(currentTime)
                && policeTraining.getEndTime().isAfter(currentTime)
        );
    }

}
