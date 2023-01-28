package com.nwpu.managementserver.dto;

import com.nwpu.managementserver.util.RsaDecryptUtil;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@Data
public class LoginParam {

    String accountNumber;

    String password;

    public String getDecryptPassword(String privateKey) {

        //return password;
        //联调时采用以下返回
        return RsaDecryptUtil.decrypt(privateKey, password);
    }
}
