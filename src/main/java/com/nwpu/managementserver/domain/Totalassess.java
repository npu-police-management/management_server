package com.nwpu.managementserver.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName totalAssess
 */
@Data
public class Totalassess implements Serializable {
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
    private String mentalState;

    /**
     * 
     */
    private String pressType;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}