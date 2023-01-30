package edu.nwpu.managementserver.vo;

import lombok.Data;

/**
 * @author Jiayi Zhu
 * 2023/1/21
 */
@Data
public class LoginVO {

    private Object person;

    private String role;

    private String accessToken;

    private String refreshToken;

}
