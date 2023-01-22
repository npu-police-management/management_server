package com.nwpu.managementserver.dto;

import lombok.Data;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@Data
public class LoginParam {

    String accountNumber;

    String password;

    public String getDecryptPassword() {

        return password;
        //联调时采用以下返回
        //return RsaDecryptUtil.decrypt(password);
    }
}
