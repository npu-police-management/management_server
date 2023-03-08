package edu.nwpu.managementserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jiayi Zhu
 * 2023/3/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalAssessDTO {

    private String id;
    private String name;
    private String mentalPercentList;
    private boolean result;
    private String description;
    private String createTime;
    private String updateTime;
}
