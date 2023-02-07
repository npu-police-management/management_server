package edu.nwpu.managementserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/7 17:57
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.vo.TotalAssessForPoliceVO
 * @description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TotalAssessForPoliceVO {
    private long id;
    private int[] mentalPercentList;
    private boolean result;
    private String description;
    private String createTime;
    private String updateTime;
}
