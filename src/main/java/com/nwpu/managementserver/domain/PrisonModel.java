package com.nwpu.managementserver.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName prison_model
 */
@Data
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