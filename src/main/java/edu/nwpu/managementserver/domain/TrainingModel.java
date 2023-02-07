package edu.nwpu.managementserver.domain;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName training_model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingModel implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Integer enable;

    /**
     * 
     */
    private Integer priority;

    @Serial
    private static final long serialVersionUID = 1L;
}