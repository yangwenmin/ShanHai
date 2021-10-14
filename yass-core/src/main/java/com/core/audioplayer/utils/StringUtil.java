package com.core.audioplayer.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : zlc
 * @date : On 2017/4/27
 * @eamil : zlc921022@163.com
 */

public class StringUtil {

    //非空判断
    public static boolean isEmpty(String s){

        if(TextUtils.isEmpty(s) || " ".equals(s)){
            return true;
        }else{
            return false;
        }
    }

    //判断字符串是否相等
    public static boolean isEqual(String s1, String s2){
        if(s1!=null) {
            return s1.equals(s2);
        }else{
            return false;
        }
    }

    //替换某个
    public static String replace(String oldStr, String newStr){
        if(oldStr!=null) {
            return oldStr.replace(oldStr, newStr);
        }else{
            return "";
        }
    }

    //替换全部
    public static String replaceAll(String str, String oldStr, String newStr){
       if(str!=null){
           return str.replaceAll(oldStr, newStr);
       }else{
           return "";
       }
    }

    //切割字符串
    public static String[] split(String str, String regex){
        if(str!=null){
            return str.split(regex);
        }else{
            return null;
        }
    }

    //截取子串
    public static String substring(String str, int startIndex, int endIndex){
        if(str!=null){
            return str.substring(startIndex,endIndex);
        }else{
            return "";
        }
    }

    //获取字符串中子串出现的次数
    private static int strAppearTimes(String str, String key)
    {
        int count = 0, index = str.indexOf(key);
        while(str.indexOf(key)!=-1)
        {
            str = str.substring(index+key.length());
            count++;
            index = str.indexOf(key);
        }
        return count;
    }

    //判断字符串是不是数字类型 避免强制转换错误
    public static boolean isNumber(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(str);
        if( matcher!=null && matcher.matches() ){
            return true;
        }else{
            return false;
        }
    }

    //获取字节数组
    public static byte[] getBytes(String str){
        if(str!=null){
            return str.getBytes();
        }else{
            return null;
        }
    }

    //去起始和末尾空格
    public static String trim(String str){
        if(str!=null){
            return str.trim();
        }else {
            return "";
        }
    }

}
