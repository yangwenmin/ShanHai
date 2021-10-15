package com.shanhai.utils;

import android.content.Context;

import com.core.utils.dbtutil.PrefUtils;
import com.shanhai.application.ConstValues;
import com.shanhai.func_video.domain.CategoryStc;
import com.shanhai.func_video.domain.VideoStc;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;


/**
 * UI工具类
 */
public class ShanHaiUtil {

    // 从中随机取一个数字
    static int randstart = 144;

    /**
     * 读取文件内容
     *
     * @param
     * @return
     */
    public static String parseFileToString(File docFile) throws Exception {

        int length = (int) docFile.length();
        byte[] buff = new byte[length];
        FileInputStream fin = new FileInputStream(docFile);
        fin.read(buff);
        fin.close();
        String result = new String(buff, "GB2312");
        if (result != null) {
            // 判断是否是乱码
            if (!(java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(result))) {
                try {
                    result = new String(buff, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }



    /**
     * 将字符串分割成数组
     *
     * @param result
     * @return
     */
    public static String[] splitStringToArray(String result) {

        String[] read;
        if (result.contains("\r\n")) {
            read = result.split("\r\n");
        } else if (result.contains("\r")) {
            read = result.split("\r");
        } else {
            read = result.split("\n");
        }
        return read;
    }

    /**
     * 将分类数组转成分类集合
     *
     * @param readResult
     * @return
     */
    public static ArrayList<CategoryStc> parseStringArrayToList(String[] readResult) {

        Random rand = new Random();
        int ab = rand.nextInt(randstart);

        ArrayList<CategoryStc> categoryStcs = new ArrayList<>();

        CategoryStc stc;

        /*for (String s : readResult) {
            // 分类
            stc = new CategoryStc();
            if (!(s.contains("_list") || s.contains("_LIST") || s.contains("双击提取文件夹中文件名") || s.contains("新建文件夹"))) {
                stc.setCategoryname(s);// 分类名称
                stc.setImageurl(ConstValues.videoUrlList[rand.nextInt(ConstValues.videoUrlList.length)]);// 分类图片链接
                categoryStcs.add(stc);
            }
        }*/

        for (int i = 0; i < readResult.length; i++) {
            String s = readResult[i];

            // 分类
            stc = new CategoryStc();
            if (!(s.contains("_list") || s.contains("_LIST") || s.contains("双击提取文件夹中文件名") || s.contains("新建文件夹"))) {
                stc.setCategoryname(s);// 分类名称
                int sel = (ab + i) >= ConstValues.videoUrlList.length ? rand.nextInt(ConstValues.videoUrlList.length) : (ab + i);
                stc.setImageurl(ConstValues.videoUrlList[sel]);// 分类图片链接
                categoryStcs.add(stc);
            }
        }


        return categoryStcs;
    }


    /**
     * 将字符串数组转成视频对象集合
     *
     * @param readResult
     * @return
     */
    public static ArrayList<VideoStc> getVideoListByVideoArray(String httphead,String categoryname, String[] readResult) {

        Random rand = new Random();
        int ab = rand.nextInt(randstart);

        ArrayList<VideoStc> videoStcs = new ArrayList<>();

        VideoStc stc;

        /*for (String s : readResult) {
            //
            stc = new VideoStc();
            if (!(s.contains("_list") || s.contains("_LIST") || s.contains("双击提取文件夹中文件名") || s.contains("新建文件夹"))) {
                stc.setVideourl(ConstValues.HTTPID + ConstValues.VIDEOPATH + categoryname + "/" + s);
                stc.setVideoname(s);
                stc.setImageurl(ConstValues.videoUrlList[rand.nextInt(ConstValues.videoUrlList.length)]);
                videoStcs.add(stc);
            }
        }*/

        for (int i = 0; i < readResult.length; i++) {
            String s = readResult[i];

            stc = new VideoStc();
            if (!(s.contains("_list") || s.contains("_LIST") || s.contains("双击提取文件夹中文件名") || s.contains("新建文件夹"))) {
                stc.setVideourl(httphead + ConstValues.VIDEOPATH + categoryname + "/" + s);
                stc.setVideoname(s);
                int sel = (ab + i) >= ConstValues.videoUrlList.length ? rand.nextInt(ConstValues.videoUrlList.length) : (ab + i);
                stc.setImageurl(ConstValues.videoUrlList[sel]);
                videoStcs.add(stc);
            }
        }

        return videoStcs;
    }



    public static  String getHttpid(Context context){
        // 获取链接的头部 默认Home
        return PrefUtils.getString(context, ConstValues.HTTPSERVICE, ConstValues.HTTPHEAD_HOME);
    }


}
