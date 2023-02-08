package edu.nwpu.managementserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/7 17:49
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.vo.TrainingDynamicForPoliceVO
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDynamicForPoliceVO {
    private String id;
    private String modelId;
    private String modelName;
    private String status;
    private String result;
    private String startTime;
    private String endTime;
}
