package edu.nwpu.managementserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jiayi Zhu
 * 2023/2/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliceDynamicVO {

    private String modelName;

    private String startTime;

    private String endTime;
}
