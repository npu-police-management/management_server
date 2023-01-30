package edu.nwpu.managementserver;

import cn.hutool.core.util.StrUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

@SpringBootTest
class ManagementServerApplicationTests {
	private final String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHQW5ppAC8daKpfhd38APfOjjy\nCcw9WXTJRVXnHYuH3EO4k0fHA6npSbT1IDgNOdigMEMDNnjwJ+No9icbVJuajlHv\nw4H8Q1xTJvyHfgeYOh7no2HovAXRz4TPcDu6PbJDeFhgF90Zn+WORyNR+LSNR0w6\nxOE8c+Lkop1duOVX2QIDAQAB";

	/**
	 * RSA公钥加密
	 *
	 * @param str
	 *            加密字符串
	 * @param publicKey
	 *            公钥
	 * @return 密文
	 * @throws Exception
	 *             加密过程中的异常信息
	 */
	public static String encrypt( String str, String publicKey ) throws Exception{
		//base64编码的公钥
		byte[] decoded = Base64.decodeBase64(publicKey);
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
		//RSA加密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
		return outStr;
	}

	@Test
	void contextLoads() throws Exception {

//		String res = encrypt("root",publicKey);
//		System.out.println(res);

	}

}
