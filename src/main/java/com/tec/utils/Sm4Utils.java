package com.tec.utils;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sunlei
 * @title: Sm4Utils
 * @projectName tasly-supervise-sddxb-api
 * @description: TODO
 * @date 2020/8/2015:42
 */
public class Sm4Utils {

    public static final String ALGORIGTHM_NAME = "SM4";
    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS7Padding";
    public static final int DEFAULT_KEY_SIZE = 128;
    private static final String ENCODING = "UTF-8";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public Sm4Utils() {
    }

    /**
     * @Description:生成ecb暗号
     */
    private static Cipher generateEcbCipher(String algorithmName, int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORIGTHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }

    /**
     * @Description:自动生成密钥
     */
    public static byte[] generateKey() throws Exception {
        return generateKey(DEFAULT_KEY_SIZE);
    }

    public static byte[] generateKey(int keySize) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORIGTHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(keySize, new SecureRandom());
        return kg.generateKey().getEncoded();
    }


    /**
     * @Description:加密
     */
    public static String encryptEcb(String hexKey, String paramStr, String charset) throws Exception {
        String cipherText = "";
        if (null != paramStr && !"".equals(paramStr)) {
            byte[] keyData = ByteUtils.fromHexString(hexKey);
            charset = charset.trim();
            if (charset.length() <= 0) {
                charset = ENCODING;
            }
            byte[] srcData = paramStr.getBytes(charset);
            byte[] cipherArray = encrypt_Ecb_Padding(keyData, srcData);
            cipherText = ByteUtils.toHexString(cipherArray);
        }
        return cipherText;
    }

    /**
     * @Description:加密模式之ecb
     */
    public static byte[] encrypt_Ecb_Padding(byte[] key, byte[] data) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
        byte[] bs = cipher.doFinal(data);
        return bs;
    }

    /**
     * @Description:sm4解密
     */
    public static String decryptEcb(String hexKey, String cipherText, String charset) throws Exception {
        String decryptStr = "";
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        byte[] srcData = decrypt_Ecb_Padding(keyData, cipherData);
        charset = charset.trim();
        if (charset.length() <= 0) {
            charset = ENCODING;
        }
        decryptStr = new String(srcData, charset);
        return decryptStr;
    }

    /**
     * @Description:解密
     */
    public static byte[] decrypt_Ecb_Padding(byte[] key, byte[] cipherText) throws Exception {
        Cipher cipher = generateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }

    /**
     * @Description:密码校验
     */
    public static boolean verifyEcb(String hexKey, String cipherText, String paramStr) throws Exception {
        boolean flag = false;
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        byte[] cipherData = ByteUtils.fromHexString(cipherText);
        byte[] decryptData = decrypt_Ecb_Padding(keyData, cipherData);
        byte[] srcData = paramStr.getBytes(ENCODING);
        flag = Arrays.equals(decryptData, srcData);
        return flag;
    }

    /**
     * @Description:测试类
     */
    public static void main(String[] args) {
        try {
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
                            "</resultData>";
            // 自定义的32位16进制密钥
            String key = "D6D4E29F4DA1186CB17FF7C930691909";
            //"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
//            String result = StringUtils.trimAllWhitespace(json);
            String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + StringUtils.trimAllWhitespace(json);
            System.out.println("result:" + result);
            String cipher = Sm4Utils.encryptEcb(key, result, ENCODING);
            System.out.println(cipher);
            System.out.println("BASE64 后 : " + Base64.encodeBase64String(cipher.getBytes(Charset.forName("UTF-8"))));
            System.out.println("BASE64 后长度 : " + Base64.encodeBase64String(cipher.getBytes(Charset.forName("UTF-8"))).getBytes().length);
            System.out.println("密码校验结果:" + Sm4Utils.verifyEcb(key, cipher, result));
            json = Sm4Utils.decryptEcb(key, cipher, ENCODING);
            System.out.println("解密后报文:" + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String stripNonValidXMLChars(String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        return str.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
    }

    public static String stripNonValidXMLCharacters(String in) {
        StringBuffer out = new StringBuffer(); // Used to hold the output.
        char current; // Used to reference the current character.

        if (in == null || ("".equals(in))) return ""; // vacancy test.
        for (int i = 0; i < in.length(); i++) {
            current = in.charAt(i); // NOTE: No IndexOutOfBoundsException caught here; it should not happen.
            if ((current == 0x9) ||
                    (current == 0xA) ||
                    (current == 0xD) ||
                    ((current >= 0x20) && (current <= 0xD7FF)) ||
                    ((current >= 0xE000) && (current <= 0xFFFD)) ||
                    ((current >= 0x10000) && (current <= 0x10FFFF)))
                out.append(current);
        }
        return out.toString();
    }

    public static String removeIllegalXmlCharacters(String xml) {
        if (xml == null || xml.length() <= 0) {
            return "";
        }
        int len = xml.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char ch = xml.charAt(i);
            if (isLegalXMLCharacter(ch)) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static boolean isLegalXMLCharacter(int ch) {
        if (ch <= 0xD7FF) {
            if (ch >= 0x20) {
                return true;
            } else {
                return ch == '\n' || ch == '\r' || ch == '\t';
            }
        } else {
            return (ch >= 0xE000 && ch <= 0xFFFD) || (ch >= 0x10000 && ch <= 0x10FFFF);
        }
    }

    private static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            //String s4 = Integer.toHexString(ch);
            String s4 = org.apache.commons.lang3.StringUtils.leftPad(Integer.toHexString(ch), 2, '0');
            str = str + s4;
        }
        return str;
    }

    private static String strToUnicode(String strText) throws Exception {
        char c;
        StringBuilder str = new StringBuilder();
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++) {
            c = strText.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128) {
                str.append("\\u" + strHex);
            } else
            // 低位在前面补00
            {
                str.append(c);
            }
        }
        return str.toString();
    }

    private static String convertUnicodeToCh(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\w{4}))");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String unicodeFull = matcher.group(1);
            String unicodeNum = matcher.group(2);
            char singleChar = (char) Integer.parseInt(unicodeNum, 16);
            str = str.replace(unicodeFull, singleChar + "");
        }
        return str;
    }

    /**
     * 余数补齐算法
     */
    private byte[] padding(byte[] input, int mode) {
        if (input == null) {
            return null;
        }

        byte[] ret = (byte[]) null;
        if (mode == Cipher.ENCRYPT_MODE) // 加密模式
        {
            int p = 16 - input.length % 16; // p：看最后要补多少位
            ret = new byte[input.length + p]; // ret：新的 16 倍数组
            System.arraycopy(input, 0, ret, 0, input.length); // 原内容拷到新数组
            for (int i = 0; i < p; i++) // 最后 p 位填充内容也为 p，方便解密时删除相应位数，此循环改进建议见下文
            {
                ret[input.length + i] = (byte) p;
            }
        } else // 解密模式
        {
            int p = input[input.length - 1]; // 从最后一位读出 p，即补的位数
            ret = new byte[input.length - p]; // 生成原数组大小的新数组
            System.arraycopy(input, 0, ret, 0, input.length - p); // 把补位前的内容还原
        }
        return ret;
    }
}
