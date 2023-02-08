package edu.nwpu.managementserver.domain;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName prison_model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrisonModel implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private Long prisonId;

    /**
     * 
     */
    private Long modelId;

    @Serial
    private static final long serialVersionUID = 1L;
}