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
public class TrainingModelVO {

    private String id;

    private String name;

    private String description;

    private Boolean enable;

    private Integer priority;

}
