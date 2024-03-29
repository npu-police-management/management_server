package edu.nwpu.managementserver.domain;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @TableName prison_admin
 */
@Data
@AllArgsConstructor
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

    @Serial
    private static final long serialVersionUID = 1L;
}