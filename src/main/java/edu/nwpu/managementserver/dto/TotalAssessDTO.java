package edu.nwpu.managementserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
