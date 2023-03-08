package edu.nwpu.managementserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jiayi Zhu
 * 2023/3/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDynamicVO {

    private String policeName;

    private String modelName;

    private String startTime;

    private String endTime;

}
