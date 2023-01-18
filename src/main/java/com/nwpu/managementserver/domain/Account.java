package com.nwpu.managementserver.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName account
 */
@Data
public class Account implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String accountNumber;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Integer role;

    private static final long serialVersionUID = 1L;
}