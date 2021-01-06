/*
 * Project Name:cf-ghospital-service;<br/>
 * File Name:AESUtil;<br/>
 * Package Name:com.sq580.ms.hospital.biz.util;<br/>
 * Date: 2020-06-09 11:19;<br/>
 * Copyright (c) 2020, www.sq580.com All Rights Reserved.;<br/>
 */
package com.tec.utils;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;


public class AESUtil {


    /**
     * AES加密字符串
     */
    private static final String KEY = "f2bb2e100c08449290e603a0da2ef0d0";
    private static final String ENCODE = "UTF-8";
    private static final String FORMAT = "AES";
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";

    /**
     * 加密 ECB
     *
     * @param content 加密内容
     * @return String
     */
    public static String encodeData(String content) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            byte[] raw = KEY.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, FORMAT);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(content.getBytes(ENCODE));
            return Hex.encodeHexString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Assert.isTrue(checkTrue(), "Assert异常信息");
        String md5Password = DigestUtils.md5DigestAsHex("e6744f89c2e44bc".getBytes());
        System.out.println(md5Password);
        System.out.println(DigestUtils.md5DigestAsHex("e6744f89c2e44bc".getBytes()).equals(md5Password));

        String zyzyyAESResult = encodeData("zyzyy");
        System.out.println("加密结果：" + zyzyyAESResult);
        System.out.println("解密结果：" + decodeData(zyzyyAESResult));


    }

    private static boolean checkTrue() {
        return false;
    }
//    public static void main(String[] args) {
//        System.out.println(decodeData("dda79e2f5044af4cbba536d9de160558cd1e08319941b7d3449a7f1945c0ffc79a450b3c73b1bbf52dffdce7d93d4e635d59e9bc39c108c4efa38a8c6c5267d9"));
//    }

    /**
     * 解密
     */
    public static String decodeData(String str) {
        try {
            if (str == null) {
                return null;
            }
            byte[] data = Hex.decodeHex(str.toCharArray());
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(ENCODE), FORMAT);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return new String(cipher.doFinal(data), ENCODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        //String key = AesCryptography.generateKey();
//        System.out.println(KEY);
//        LoginRsp loginRsp =new LoginRsp();
//        loginRsp.setRealName("夏炜");
//        loginRsp.setUserId(125655L);
//        loginRsp.setToken("7897646546465464646");
//        String loginStr= JSON.toJSONString(ResultData.getSuccessData(loginRsp));
//        System.out.println(loginStr);
//
//        //String key = "tasly-hlwyy-aes-sercet-123456789";
//        String jiami = encodeData(loginStr);
//        System.out.println(jiami);
//        System.out.println(decodeData(jiami));
//    }
}
