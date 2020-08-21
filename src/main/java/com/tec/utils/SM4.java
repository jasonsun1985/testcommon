package com.tec.utils;

import java.util.Base64;

public class SM4 {
    //系统参数1：SboxTable，16*16的无符号数组
    private int[][] SboxTable = new int[][]{
            {0xd6, 0x90, 0xe9, 0xfe, 0xcc, 0xe1, 0x3d, 0xb7, 0x16, 0xb6, 0x14, 0xc2, 0x28, 0xfb, 0x2c, 0x05},
            {0x2b, 0x67, 0x9a, 0x76, 0x2a, 0xbe, 0x04, 0xc3, 0xaa, 0x44, 0x13, 0x26, 0x49, 0x86, 0x06, 0x99},
            {0x9c, 0x42, 0x50, 0xf4, 0x91, 0xef, 0x98, 0x7a, 0x33, 0x54, 0x0b, 0x43, 0xed, 0xcf, 0xac, 0x62},
            {0xe4, 0xb3, 0x1c, 0xa9, 0xc9, 0x08, 0xe8, 0x95, 0x80, 0xdf, 0x94, 0xfa, 0x75, 0x8f, 0x3f, 0xa6},
            {0x47, 0x07, 0xa7, 0xfc, 0xf3, 0x73, 0x17, 0xba, 0x83, 0x59, 0x3c, 0x19, 0xe6, 0x85, 0x4f, 0xa8},
            {0x68, 0x6b, 0x81, 0xb2, 0x71, 0x64, 0xda, 0x8b, 0xf8, 0xeb, 0x0f, 0x4b, 0x70, 0x56, 0x9d, 0x35},
            {0x1e, 0x24, 0x0e, 0x5e, 0x63, 0x58, 0xd1, 0xa2, 0x25, 0x22, 0x7c, 0x3b, 0x01, 0x21, 0x78, 0x87},
            {0xd4, 0x00, 0x46, 0x57, 0x9f, 0xd3, 0x27, 0x52, 0x4c, 0x36, 0x02, 0xe7, 0xa0, 0xc4, 0xc8, 0x9e},
            {0xea, 0xbf, 0x8a, 0xd2, 0x40, 0xc7, 0x38, 0xb5, 0xa3, 0xf7, 0xf2, 0xce, 0xf9, 0x61, 0x15, 0xa1},
            {0xe0, 0xae, 0x5d, 0xa4, 0x9b, 0x34, 0x1a, 0x55, 0xad, 0x93, 0x32, 0x30, 0xf5, 0x8c, 0xb1, 0xe3},
            {0x1d, 0xf6, 0xe2, 0x2e, 0x82, 0x66, 0xca, 0x60, 0xc0, 0x29, 0x23, 0xab, 0x0d, 0x53, 0x4e, 0x6f},
            {0xd5, 0xdb, 0x37, 0x45, 0xde, 0xfd, 0x8e, 0x2f, 0x03, 0xff, 0x6a, 0x72, 0x6d, 0x6c, 0x5b, 0x51},
            {0x8d, 0x1b, 0xaf, 0x92, 0xbb, 0xdd, 0xbc, 0x7f, 0x11, 0xd9, 0x5c, 0x41, 0x1f, 0x10, 0x5a, 0xd8},
            {0x0a, 0xc1, 0x31, 0x88, 0xa5, 0xcd, 0x7b, 0xbd, 0x2d, 0x74, 0xd0, 0x12, 0xb8, 0xe5, 0xb4, 0xb0},
            {0x89, 0x69, 0x97, 0x4a, 0x0c, 0x96, 0x77, 0x7e, 0x65, 0xb9, 0xf1, 0x09, 0xc5, 0x6e, 0xc6, 0x84},
            {0x18, 0xf0, 0x7d, 0xec, 0x3a, 0xdc, 0x4d, 0x20, 0x79, 0xee, 0x5f, 0x3e, 0xd7, 0xcb, 0x39, 0x48}
    };

    //系统参数2：FK，4个32位无符号整数
    private long[] FK = new long[]{0xa3b1bac6, 0x56aa3350, 0x677d9197, 0xb27022dc};

    //系统参数3：CK，32个32位无符号整数
    private long[] CK = new long[]{
            0x00070e15, 0x1c232a31, 0x383f464d, 0x545b6269,
            0x70777e85, 0x8c939aa1, 0xa8afb6bd, 0xc4cbd2d9,
            0xe0e7eef5, 0xfc030a11, 0x181f262d, 0x343b4249,
            0x50575e65, 0x6c737a81, 0x888f969d, 0xa4abb2b9,
            0xc0c7ced5, 0xdce3eaf1, 0xf8ff060d, 0x141b2229,
            0x30373e45, 0x4c535a61, 0x686f767d, 0x848b9299,
            0xa0a7aeb5, 0xbcc3cad1, 0xd8dfe6ed, 0xf4fb0209,
            0x10171e25, 0x2c333a41, 0x484f565d, 0x646b7279
    };

    //系统参数4：SK，32个32位无符号整数，子密钥
    private long[] SK = new long[32];

    //缺省16字节的密钥
    private int[] KEY = new int[]{0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff, 0x19};


    //错误信息
    private String error = "";

    public SM4() {
        error = "";
    }

    public static void main(String[] args) {
//        String str = "";
        String str =
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
                        "      <organizationId>1237000049557243XX</organizationId>" +
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
                        "      <organizationId>1237000049557243XX</organizationId>" +
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

        for (int i = 0; i < 1000; i++) {
            str += Integer.toString(i);
        }
        SM4 sm = new SM4();
//        sm.SetKey("0x1122334455667788");
        sm.SetKey("D6D4E29F4DA1186CB17FF7C930691909");
        String se = sm.Encrypt(str);
        System.out.println(se);
        String sd = sm.Decrypt(se);
        System.out.print(sd);


        System.out.print("");
    }

    public String GetError() {
        return error;
    }

    /*内部函数*/
    private int BYTE8(byte x) {
        return ((int) x) & 0xFF;
    }

    private int BYTE8(int x) {
        return x & 0xFF;
    }

    private long UInt32(int x) {
        return ((long) x) & 0xFFFFFFFF;
    }

    private long UInt32(long x) {
        return x & 0xFFFFFFFF;
    }

    //位移函数
    private long shl(long x, int n) {
        return (x & 0xFFFFFFFF) << n;
    }

    private long rotl(long x, int n) {
        long r1 = UInt32(x) << n;
        long r2 = UInt32(x) >> (32 - n);
        long r = UInt32(r1 ^ r2);
        return r;
    }

    private long get_ulong_be(int[] b, int i) {
        return ((long) (b[i] & 0xff) << 24) | ((long) (b[i + 1] & 0xff) << 16) | ((long) (b[i + 2] & 0xff) << 8) | ((long) b[i + 3] & 0xff);
    }

    private void put_ulong_be(long n, int[] b, int i) {
        b[i] = BYTE8((int) (n & 0xFFFFFFFF) >> 24);
        b[i + 1] = BYTE8((int) (n & 0xFFFFFFFF) >> 16);
        b[i + 2] = BYTE8((int) (n & 0xFFFFFFFF) >> 8);
        b[i + 3] = BYTE8((int) (n & 0xFFFFFFFF));
        ;
    }

    private int sm4Sbox(int inch) {
        int r = (inch & 0xff) / 16;
        int h = (inch & 0xff) % 16;
        return SboxTable[r][h];
    }

    private long sm4Lt(long ka) {
        int[] a = new int[4];
        int[] b = new int[4];
        put_ulong_be(ka, a, 0);
        b[0] = sm4Sbox(a[0]);
        b[1] = sm4Sbox(a[1]);
        b[2] = sm4Sbox(a[2]);
        b[3] = sm4Sbox(a[3]);
        long bb = get_ulong_be(b, 0);
        long c = bb ^ rotl(bb, 2) ^ rotl(bb, 10) ^ rotl(bb, 18) ^ rotl(bb, 24);
        return c;
    }

    private long sm4F(long x0, long x1, long x2, long x3, long rk) {
        return (UInt32(x0) ^ sm4Lt(UInt32(x1) ^ UInt32(x2) ^ UInt32(x3) ^ UInt32(rk)));
    }

    private long sm4CalciRK(long ka) {
        int[] a = new int[4];
        int[] b = new int[4];
        put_ulong_be(ka, a, 0);
        b[0] = sm4Sbox(a[0]);
        b[1] = sm4Sbox(a[1]);
        b[2] = sm4Sbox(a[2]);
        b[3] = sm4Sbox(a[3]);
        long bb = get_ulong_be(b, 0);
        long rk = UInt32(bb) ^ rotl(UInt32(bb), 13) ^ rotl(UInt32(bb), 23);
        return rk;
    }

    private void sm4_setkey() {
        long[] MK = new long[4];
        long[] k = new long[36];

        MK[0] = get_ulong_be(KEY, 0);
        MK[1] = get_ulong_be(KEY, 4);
        MK[2] = get_ulong_be(KEY, 8);
        MK[3] = get_ulong_be(KEY, 12);
        k[0] = MK[0] ^ FK[0];
        k[1] = MK[1] ^ FK[1];
        k[2] = MK[2] ^ FK[2];
        k[3] = MK[3] ^ FK[3];
        for (int i = 0; i < 32; i++) {
            k[i + 4] = k[i] ^ (sm4CalciRK(k[i + 1] ^ k[i + 2] ^ k[i + 3] ^ CK[i]));
            SK[i] = k[i + 4];
        }
    }

    //一轮加密：输入16字节，输出16字节
    private void sm4_one_round(int[] input, int[] output) {
        int i = 0;
        long[] ulbuf = new long[36];
        ulbuf[0] = get_ulong_be(input, 0);
        ulbuf[1] = get_ulong_be(input, 4);
        ulbuf[2] = get_ulong_be(input, 8);
        ulbuf[3] = get_ulong_be(input, 12);
        while (i < 32) {
            ulbuf[i + 4] = sm4F(ulbuf[i], ulbuf[i + 1], ulbuf[i + 2], ulbuf[i + 3], SK[i]);
            i++;
        }
        put_ulong_be(ulbuf[35], output, 0);
        put_ulong_be(ulbuf[34], output, 4);
        put_ulong_be(ulbuf[33], output, 8);
        put_ulong_be(ulbuf[32], output, 12);
    }

    private void sm4_setkey_enc() {
        sm4_setkey();
    }

    private void sm4_setkey_dec() {
        sm4_setkey();
        for (int i = 0; i < 16; i++) {
            long t = SK[i];
            SK[i] = SK[31 - i];
            SK[31 - i] = t;
        }
    }

    //input的字节长度必须是16的整数倍
    //output的字节长度必须跟input相同
    private void sm4_crypt_ecb(int[] input, int[] output) {
        int idx = 0;
        int[] a = new int[16];
        int[] b = new int[16];
        int ilen = input.length;
        while (ilen > 0) {
            for (int i = 0; i < 16; i++) {
                a[i] = input[idx + i];
            }
            sm4_one_round(a, b);
            for (int i = 0; i < 16; i++) {
                output[idx + i] = b[i];
            }
            idx += 16;
            ilen -= 16;
        }
    }

    /*对外公开的方法*/
    public void SetKey(String key) {
        try {
            if (key.length() != 32) return;
            for (int i = 0; i < 16; i++) {
                KEY[i] = Integer.parseInt(key.substring(i * 2, i * 2 + 2), 16);
            }
        } catch (Exception ex) {
        }
    }

    //对输入的String字符串进行加密：
    //输入参数：待加密串（UTF-8格式）
    //返回值：加密后的BASE64字符串
    public String Encrypt(String str) {
        String sRet = "";

        error = "";
        if (str == null || str.equals("")) return sRet;

        try {
            sm4_setkey_enc();

            //1、把输入的str字符串转换成字节数组
            byte[] sb = str.getBytes("utf-8");
            int len = sb.length;
            int left = len % 16;
            //设置输入缓冲区是16的整数倍，不足16位的补0
            int blen = (len / 16) * 16;
            if (left > 0) blen += 16;
            int[] b = new int[blen];
            for (int i = 0; i < len; i++) b[i] = (int) sb[i];
            for (int i = len; i < blen; i++) b[i] = 0;   //最后补齐0
            int[] e = new int[blen];    //指定密文输出缓冲区
            sm4_crypt_ecb(b, e);      //SM4加密

            //把密文缓冲区转换成BASE64
            //把密文缓冲区压缩
            byte[] eb = new byte[blen];
            for (int i = 0; i < blen; i++) eb[i] = (byte) e[i];
            sRet = Base64.getEncoder().encodeToString(eb);
        } catch (Exception ex) {
            error = ex.getMessage();
        }

        return sRet;
    }

    //对输入的BASE64字符串进行解密：
    //输入参数：待解密串（BASE64格式）
    //返回值：解密后的UTF-8字符串
    public String Decrypt(String str) {
        String sRet = "";

        error = "";

        //设置解密模式
        sm4_setkey_dec();

        try {
            //1、把输入的Base64字符串转换成字节数组
            byte[] sb = Base64.getDecoder().decode(str);
            int len = sb.length;
            int left = len % 16;
            if (left != 0) {
                //不合法的密文
                error = "输入的密文格式不正确";
                return sRet;
            }
            int[] b = new int[len];
            for (int i = 0; i < len; i++) b[i] = BYTE8(sb[i]);
            int[] e = new int[len];    //指定解密输出缓冲区
            sm4_crypt_ecb(b, e);      //SM4加密

            //把输出缓冲区转换成UTF-8串
            //压缩缓冲区
            byte[] eb = new byte[len];
            for (int i = 0; i < len; i++) eb[i] = (byte) e[i];
            sRet = new String(eb, "utf-8");
        } catch (Exception ex) {
            error = ex.getMessage();
        }

        return sRet;
    }
}
