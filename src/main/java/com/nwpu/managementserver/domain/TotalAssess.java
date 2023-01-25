package com.nwpu.managementserver.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName total_assess
 */
@Data
public class TotalAssess implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private Long policeId;

    /**
     * 
     */
    private String mentalPercentList;

    /**
     * 
     */
    private Boolean result;

    /**
     *
     */
    private String description;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime updateTime;

    @Serial
    private static final long serialVersionUID = 1L;
}