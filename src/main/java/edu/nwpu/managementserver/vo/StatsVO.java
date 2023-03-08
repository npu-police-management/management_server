package edu.nwpu.managementserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu
 * 2023/3/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsVO {

    private Integer accessCount;

    private Integer trainingCount;

    private Integer weeklyTrainingCount;

    private Integer flow;

    private List<Integer> accessLastWeek = new ArrayList<>();
}
