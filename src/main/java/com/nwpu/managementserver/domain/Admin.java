package com.nwpu.managementserver.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName admin
 */
@Data
public class Admin implements Serializable {
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

    private static final long serialVersionUID = 1L;
}