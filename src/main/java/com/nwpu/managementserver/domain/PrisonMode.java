package com.nwpu.managementserver.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName prison_mode
 */
@Data
public class PrisonMode implements Serializable {
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
    private Long modeId;

    private static final long serialVersionUID = 1L;
}