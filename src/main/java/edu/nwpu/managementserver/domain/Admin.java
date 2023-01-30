package edu.nwpu.managementserver.domain;

import java.io.Serial;
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

    @Serial
    private static final long serialVersionUID = 1L;
}