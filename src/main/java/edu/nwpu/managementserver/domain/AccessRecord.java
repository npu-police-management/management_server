package edu.nwpu.managementserver.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName access_record
 */
@Data
public class AccessRecord implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private LocalDateTime accessTime;

    /**
     * 
     */
    private Long accountId;

    /**
     * 
     */
    private String content;

    @Serial
    private static final long serialVersionUID = 1L;
}