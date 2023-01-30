package edu.nwpu.managementserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@Data
@AllArgsConstructor
public class TokenDTO {

    private String accessToken;

    private String refreshToken;
}
