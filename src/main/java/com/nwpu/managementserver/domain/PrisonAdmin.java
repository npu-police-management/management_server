package com.nwpu.managementserver.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName prison_admin
 */
@Data
public class PrisonAdmin implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String nickname;

    /**
     * 
     */
    private Long accountId;

    /**
     * 
     */
    private Long prisonId;

    private static final long serialVersionUID = 1L;
}