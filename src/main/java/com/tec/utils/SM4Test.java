package com.tec.utils;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;

/**
 * @author sunlei
 * @title: SM4
 * @projectName tasly-supervise-sddxb-api
 * @description: TODO
 * @date 2020/8/2021:22
 */

public class SM4Test {

    private static final BouncyCastleProvider BOUNCY_CASTLE_PROVIDER = new BouncyCastleProvider();
    private static final String BOUNCY_CASTLE_PROVIDER_NME = BouncyCastleProvider.PROVIDER_NAME;
    private static final String SM4_ALGORITHM = "SM4";
    private static final int DEFAULT_KEY_SIZE = 128;
    private static final String DEFAULT_ENCODING = "UTF-8";

    static {
        Security.removeProvider(BOUNCY_CASTLE_PROVIDER_NME);
        Security.addProvider(BOUNCY_CASTLE_PROVIDER);
    }

    /**
     * SM4 加密
     *
     * @param plainText      明文数据
     * @param key            密钥
     * @param modeAndPadding 加密模式和padding模式
     * @param iv             初始向量(ECB模式下传NULL)
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] plainText, byte[] key, SM4ModeAndPaddingEnum modeAndPadding, byte[] iv) {
        return sm4(plainText, key, modeAndPadding, iv, Cipher.ENCRYPT_MODE);
    }

    /**
     * SM4 解密
     *
     * @param cipherText            密文数据
     * @param key                   密钥
     * @param sm4ModeAndPaddingEnum 加密模式和padding模式
     * @param iv                    初始向量(ECB模式下传NULL)
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] cipherText, byte[] key, SM4ModeAndPaddingEnum sm4ModeAndPaddingEnum, byte[] iv) {
        return sm4(cipherText, key, sm4ModeAndPaddingEnum, iv, Cipher.DECRYPT_MODE);
    }

    /**
     * SM4算法
     *
     * @param input                 输入数据
     * @param key                   密钥
     * @param sm4ModeAndPaddingEnum SM4模式
     * @param iv                    初始向量ECB模式下为null
     * @param mode                  加密或解密模式
     * @return
     * @throws Exception
     */
    private static byte[] sm4(byte[] input, byte[] key, SM4ModeAndPaddingEnum sm4ModeAndPaddingEnum, byte[] iv, int mode) {
        SecretKeySpec sm4Key = new SecretKeySpec(key, SM4_ALGORITHM);
        byte[] output = null;
        try {
            Cipher cipher = Cipher.getInstance(sm4ModeAndPaddingEnum.getName(), BOUNCY_CASTLE_PROVIDER_NME);
            if (iv == null) {
                cipher.init(mode, sm4Key);
            } else {
                cipher.init(mode, sm4Key, new IvParameterSpec(iv));
            }
            output = cipher.doFinal(input);
        } catch (NoSuchAlgorithmException e) {
        } catch (NoSuchProviderException e) {
        } catch (NoSuchPaddingException e) {
        } catch (InvalidKeyException e) {
        } catch (InvalidAlgorithmParameterException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (BadPaddingException e) {
        }

        return output;
    }

    /**
     * 生成密钥
     *
     * @param sm4ModeAndPaddingEnum
     * @return
     * @throws Exception
     */
    public static byte[] generateKey(SM4ModeAndPaddingEnum sm4ModeAndPaddingEnum) throws NoSuchProviderException, NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(sm4ModeAndPaddingEnum.getName(), BOUNCY_CASTLE_PROVIDER_NME);
        kg.init(DEFAULT_KEY_SIZE, new SecureRandom());
        return kg.generateKey().getEncoded();
    }

    /**
     * 生成密钥
     *
     * @return
     */
    public static String generateKey() {
        /**
         * 默认使用SM4_ECB_NoPadding
         */
        String key = "";
        try {
            key = Base64.encodeBase64URLSafeString(generateKey(SM4ModeAndPaddingEnum.SM4_ECB_NoPadding));
        } catch (NoSuchProviderException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        return key;
    }

    /**
     * 加密
     *
     * @param plainText
     * @param base64Key
     * @return
     */
    public static String encrypt(String plainText, String base64Key) {
        /**
         * 默认使用SM4_ECB_NoPadding
         */
        String base64Cipher = "";
        try {
            byte[] plain = plainText.getBytes(DEFAULT_ENCODING);
            byte[] key = Base64.decodeBase64(base64Key);
            base64Cipher = Base64.encodeBase64URLSafeString(encrypt(plain, key, SM4ModeAndPaddingEnum.SM4_ECB_NoPadding, null));
        } catch (UnsupportedEncodingException e) {
        }
        return base64Cipher;
    }

    /**
     * 解密
     *
     * @param base64Cipher
     * @return
     */
    public static String decrypt(String base64Cipher, String base64Key) {
        /**
         * 默认使用SM4_ECB_NoPadding
         */
        String plain = "";
        try {
            byte[] cipher = Base64.decodeBase64(base64Cipher);
            byte[] key = Base64.decodeBase64(base64Key);
            plain = new String(decrypt(cipher, key, SM4ModeAndPaddingEnum.SM4_ECB_NoPadding, null), DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return plain;
    }

    public static void main(String[] args) {
        String json =
                "<resultData>" +
                        "  <basicInfo>" +
                        "    <onlyId>2019010300000006</onlyId>" +
                        "    <mrId>68778103</mrId>" +
                        "    <idCard>370104****01021234</idCard>" +
                        "    <name>策士德</name>" +
                        "    <sex>2</sex>" +
                        "    <birthday>2019-01-0515:57:51</birthday>" +
                        "    <ageYear>56</ageYear>" +
                        "    <ageMonth>0</ageMonth>" +
                        "    <ageDay>0</ageDay>" +
                        "    <nationality>CHN</nationality>" +
                        "    <birthPlace>370104</birthPlace>" +
                        "    <nation>01</nation>" +
                        "    <occupation>17</occupation>" +
                        "    <marriage>20</marriage>" +
                        "    <administrativeDivision>370104</administrativeDivision>" +
                        "    <address>山东省济南市经五纬七路324号</address>" +
                        "    <mobilePhone>152***88888</mobilePhone>" +
                        "    <eMail>telemedicine@126.com</eMail>" +
                        "    <workUnit>山东省立医院</workUnit>" +
                        "    <linkman>策士德</linkman>" +
                        "    <relationship>01</relationship>" +
                        "    <linkmanAddr>山东省济南市经五纬七路324号</linkmanAddr>" +
                        "    <linkmanTele>152***8888</linkmanTele>" +
                        "    <medicalPayment>10</medicalPayment>" +
                        "    <condition>3</condition>" +
                        "    <medicalHistory>策士德，女，测试</medicalHistory>" +
                        "  </basicInfo>" +
                        "  <diagnosesList>" +
                        "    <diagnosis>" +
                        "      <flag>2</flag>" +
                        "      <diagOrder>1</diagOrder>" +
                        "      <diagCode>I61.902</diagCode>" +
                        "      <diagnosis>高血压脑出血</diagnosis>" +
                        "    </diagnosis>" +
                        "    <diagnosis>" +
                        "      <flag>2</flag>" +
                        "      <diagOrder>2</diagOrder>" +
                        "      <diagCode>I61.902</diagCode>" +
                        "      <diagnosis>高血压脑出血</diagnosis>" +
                        "    </diagnosis>" +
                        "  </diagnosesList>" +
                        "  <reportsList>" +
                        "    <report>" +
                        "      <startTime>2019-01-0515:57:51</startTime>" +
                        "      <endTime>2019-01-0515:57:51</endTime>" +
                        "      <tmMode>1</tmMode>" +
                        "      <tmType>01</tmType>" +
                        "      <organizationId>495571XX-0</organizationId>" +
                        "      <organization>山东省立医院</organization>" +
                        "      <doctorId>370104****08162911</doctorId>" +
                        "      <doctor>牛代福</doctor>" +
                        "      <standardSubject>03.03</standardSubject>" +
                        "      <reportTxt>会诊意见：观察颅内有无XX的记录，建议XXXXXXX。</reportTxt>" +
                        "    </report>" +
                        "    <report>" +
                        "      <startTime>2019-01-0515:57:51</startTime>" +
                        "      <endTime>2019-01-0515:57:51</endTime>" +
                        "      <tmMode>1</tmMode>" +
                        "      <tmType>01</tmType>" +
                        "      <organizationId>495571XX-0</organizationId>" +
                        "      <organization>山东省立医院</organization>" +
                        "      <doctorId>370104****08162911</doctorId>" +
                        "      <doctor>牛代福</doctor>" +
                        "      <standardSubject>03.03</standardSubject>" +
                        "      <reportTxt>会诊意见：观察颅内有无XX的记录，建议XXXXXXX。</reportTxt>" +
                        "    </report>" +
                        "  </reportsList>" +
                        "  <ordersList>" +
                        "    <order>" +
                        "      <orderNo>1</orderNo>" +
                        "      <orderDoctor>下达医师</orderDoctor>" +
                        "      <orderTime>2019-01-0515:57:51</orderTime>" +
                        "      <executor>执行人</executor>" +
                        "      <checked>审核人</checked>" +
                        "      <orderGrouping>1</orderGrouping>" +
                        "      <orderClassification>31</orderClassification>" +
                        "      <orderName>乌拉地尔压宁定</orderName>" +
                        "      <drugDosageform>1</drugDosageform>" +
                        "      <drugSpecifications>25mg</drugSpecifications>" +
                        "      <drugNumber>3</drugNumber>" +
                        "      <drugFrequency>02</drugFrequency>" +
                        "      <drugDosage>60.0</drugDosage>" +
                        "      <drugUseUnit>02</drugUseUnit>" +
                        "      <drugIntegraldose>180.0</drugIntegraldose>" +
                        "      <drugRoute>1</drugRoute>" +
                        "      <drugDay>10</drugDay>" +
                        "      <executionTime>2019-01-0515:57:51</executionTime>" +
                        "      <startTime>2019-01-0515:57:51</startTime>" +
                        "      <stopTime>2019-01-0515:57:51</stopTime>" +
                        "      <skinTest>0</skinTest>" +
                        "      <cancelTime>2019-01-0515:57:51</cancelTime>" +
                        "      <stopDoctor>停止医嘱医师</stopDoctor>" +
                        "    </order>" +
                        "    <order>" +
                        "      <orderNo>2</orderNo>" +
                        "      <orderDoctor>下达医师</orderDoctor>" +
                        "      <orderTime>2019-01-0515:57:51</orderTime>" +
                        "      <executor>执行人</executor>" +
                        "      <checked>审核人</checked>" +
                        "      <orderGrouping>1</orderGrouping>" +
                        "      <orderClassification>31</orderClassification>" +
                        "      <orderName>乌拉地尔压宁定</orderName>" +
                        "      <drugDosageform>1</drugDosageform>" +
                        "      <drugSpecifications>25mg</drugSpecifications>" +
                        "      <drugNumber>3</drugNumber>" +
                        "      <drugFrequency>02</drugFrequency>" +
                        "      <drugDosage>60.0</drugDosage>" +
                        "      <drugUseUnit>02</drugUseUnit>" +
                        "      <drugIntegraldose>180.0</drugIntegraldose>" +
                        "      <drugRoute>1</drugRoute>" +
                        "      <drugDay>10</drugDay>" +
                        "      <executionTime>2019-01-0515:57:51</executionTime>" +
                        "      <startTime>2019-01-0515:57:51</startTime>" +
                        "      <stopTime>2019-01-0515:57:51</stopTime>" +
                        "      <skinTest>0</skinTest>" +
                        "      <cancelTime>2019-01-0515:57:51</cancelTime>" +
                        "      <stopDoctor>停止医嘱医师</stopDoctor>" +
                        "    </order>" +
                        "  </ordersList>" +
                        "</resultData>00000000000";
        // 自定义的32位16进制密钥
        String key = "D6D4E29F4DA1186CB17FF7C930691909";
        String cipherText = ByteUtils.toHexString(SM4Test.sm4(json.getBytes(), key.getBytes(), SM4ModeAndPaddingEnum.SM4_CBC_PKCS7Padding, null, Cipher.ENCRYPT_MODE));
        System.out.println(cipherText);
//        System.out.println(result.getBytes().length);
    }

    public enum SM4ModeAndPaddingEnum {
        SM4_ECB_NoPadding("SM4/ECB/NoPadding"),
        SM4_ECB_PKCS5Padding("SM4/ECB/PKCS5Padding"),
        SM4_ECB_PKCS7Padding("SM4/ECB/PKCS7Padding"),
        SM4_CBC_NoPadding("SM4/CBC/NoPadding"),
        SM4_CBC_PKCS5Padding("SM4/CBC/PKCS5Padding"),
        SM4_CBC_PKCS7Padding("SM4/CBC/PKCS7Padding");

        private String name;

        SM4ModeAndPaddingEnum(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}