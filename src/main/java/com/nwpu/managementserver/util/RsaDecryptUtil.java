package com.nwpu.managementserver.util;

import cn.hutool.crypto.PemUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PrivateKey;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
public class RsaDecryptUtil {

    public static String decrypt(String encryptPassword) {

        try (FileInputStream fi = new FileInputStream("private_key.pem")) {
            PrivateKey privateKey = PemUtil.readPemPrivateKey(fi);
            RSA rsa = new RSA();
            rsa.setPrivateKey(privateKey);
            return rsa.decrypt(encryptPassword, KeyType.PrivateKey).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
