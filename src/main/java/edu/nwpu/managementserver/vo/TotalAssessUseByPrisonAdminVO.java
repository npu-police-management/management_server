package edu.nwpu.managementserver.vo;

import edu.nwpu.managementserver.domain.TotalAssess;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/7 18:06
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.vo.TotalAssessUseByPrisonAdminVO
 * @description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TotalAssessUseByPrisonAdminVO {
    private String id;
    private String policeName;
    private int[] mentalPercentList;
    private String result;
    private String description;
    private String createTime;
    private String updateTime;
}
