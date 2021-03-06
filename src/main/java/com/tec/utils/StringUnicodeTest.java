package com.tec.utils;

/**
 * @author sunlei
 * @title: StringUnicodeTest
 * @projectName tasly-supervise-sddxb-api
 * @description: TODO
 * @date 2020/8/2023:35
 */
public class StringUnicodeTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        String str = "汉语pinyin";
        String unicode = stringToUnicode(str);
        System.out.println("字符串转unicode结果：" + unicode);
        String s = unicodeToString(unicode);
        System.out.println("unicode转字符串结果：" + s);

    }

    /**
     * 字符串转unicode
     *
     * @param str
     * @return
     */
    public static String stringToUnicode(String str) {
        StringBuffer sb = new StringBuffer();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            sb.append("\\u" + Integer.toHexString(c[i]));
        }
        return sb.toString();
    }

    /**
     * unicode转字符串
     *
     * @param unicode
     * @return
     */
    public static String unicodeToString(String unicode) {
        StringBuffer sb = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int index = Integer.parseInt(hex[i], 16);
            sb.append((char) index);
        }
        return sb.toString();
    }
}
