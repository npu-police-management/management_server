package com.nwpu.managementserver.domain;

import java.io.Serial;
import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName refresh_token
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshToken implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     *
     */
    private String subject;

    /**
     * 
     */
    private String token;

    @Serial
    private static final long serialVersionUID = 1L;

    public static RefreshToken of(long id, String subject, String refreshToken) {

        return new RefreshToken(id, subject, refreshToken);
    }
}