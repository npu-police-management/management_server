package com.nwpu.managementserver.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName prison
 */
@Data
public class Prison implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String name;

    @Serial
    private static final long serialVersionUID = 1L;
}