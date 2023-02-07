package edu.nwpu.managementserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/7 15:13
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.vo.TrainingModelVO
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingModelVO {
    private long id;
    private String name;
    private String description;
    private boolean enable;
    private int priority;
}
