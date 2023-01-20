package com.nwpu.managementserver.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName refresh_token
 */
@Data
public class RefreshToken implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String token;

    @Serial
    private static final long serialVersionUID = 1L;
}