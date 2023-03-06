package edu.nwpu.managementserver.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName police_training
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliceTraining implements Serializable {
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
    private Long modelId;

    /**
     * 
     */
    private LocalDateTime startTime;

    /**
     * 
     */
    private LocalDateTime endTime;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private String result;

    @Serial
    private static final long serialVersionUID = 1L;
}