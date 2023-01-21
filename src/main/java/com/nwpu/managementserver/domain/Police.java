package com.nwpu.managementserver.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName police
 */
@Data
public class Police implements Serializable {
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
    private String imageUrl;

    /**
     * 
     */
    private Long prisonId;

    /**
     * 
     */
    private Long accountId;

    @Serial
    private static final long serialVersionUID = 1L;
}