package com.tec.utils;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * @author sunlei
 * @title: SelfCommonUtils
 * @projectName testcommon
 * @description: TODO
 * @date 2020/8/21 13:33
 */
public class SelfCommonUtils {
    public static void main(String[] args) {
        System.out.println(getChineseStrFirstPinyin("张三丰"));
    }

    public static String getChineseStrFirstPinyin(String chineseStr) {
        if (null != chineseStr && !"".equals(chineseStr)) {
            char[] chars = chineseStr.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                sb.append(getFirstPinyin(c));
            }
            return sb.toString().toUpperCase();
        } else {
            return chineseStr;
        }
    }

    /**
     * Description: 获得中文字符的首位拼音.
     * Date: 2018/9/20 14:33
     */
    private static String getFirstPinyin(char c) {
        String str = "";
        String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(c);
        if (stringArray != null && stringArray.length > 0) {
            str = stringArray[0].substring(0, 1);
        } else {
            str = String.valueOf(c);
        }
        return str;
    }
}
