package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CnOrUs {
    public static void main(String[] args) {
        System.out.println(checkStr("啊啊"));
    }
    /**
     *  判断是中文还是英文还是符号 或是空字符串
     */
    public static int checkStr(String str){
        String regEx = "[\\u4e00-\\u9fa5]+";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(str);
        if ("".equals(str))
            return 0;
        if(m.find())

            return 1;

        else if(str.matches("^[a-zA-Z]*"))
            return 2;
        else//其他符号
            return 3;

    }




    /**

     * 是否是中文

     * @param c

     * @return

     */

    public static boolean isChinese(char c) {

        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION

                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {

            return true;

        }

        return false;

    }

    /**

     * 是否是英文

     * @param

     * @return

     */

    public static boolean isEnglish(String charaString){

        return charaString.matches("^[a-zA-Z]*");

    }

    public static boolean isChinese(String str){

        String regEx = "[\\u4e00-\\u9fa5]+";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(str);

        if(m.find())

            return true;

        else

            return false;

    }



//    public static void main(String[] args) {
//
//        System.out.println(isChinese('员'));
//
//        System.out.println(isChinese('s'));
//
//        System.out.println(isEnglish("程序员之家"));
//
//        System.out.println(isEnglish("robert"));
//
//        System.out.println(isChinese(" 程序员论坛"));
//
//
//
//    }
}
