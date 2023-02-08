package edu.nwpu.managementserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/7 17:18
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.vo.TrainingDynamicVO
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDynamicVO {
    private String id;
    private String policeId;
    private String policeName;
    private String modelId;
    private String modelName;
    private String status;
    private String result;
    private String startTime;
    private String endTime;
}
