package edu.nwpu.managementserver.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName total_assess
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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