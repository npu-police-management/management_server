package com.nwpu.managementserver.util;

import cn.hutool.crypto.CryptoException;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.nwpu.managementserver.constant.CodeEnum;
import com.nwpu.managementserver.exception.BusinessException;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
public class RsaDecryptUtil {

    public static String decrypt(String privateKey, String encryptPassword) {

        try {
            RSA rsa = new RSA(privateKey, null);
            return new String(rsa.decrypt(encryptPassword, KeyType.PrivateKey));
        } catch (CryptoException e) {
            throw new BusinessException(CodeEnum.RequestError, "密码无法用RSA解密");
        }
    }
}
