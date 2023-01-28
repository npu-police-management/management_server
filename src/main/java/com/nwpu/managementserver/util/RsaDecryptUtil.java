package com.nwpu.managementserver.util;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
public class RsaDecryptUtil {

    public static String decrypt(String privateKey, String encryptPassword) {

        RSA rsa = new RSA(privateKey, null);
        return new String(rsa.decrypt(encryptPassword, KeyType.PrivateKey));
    }
}
