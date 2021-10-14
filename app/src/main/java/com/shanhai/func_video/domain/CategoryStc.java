package com.shanhai.func_video.domain;

import java.io.Serializable;

/**
 * Created by ywm on 2021/9/7
 */
public class CategoryStc implements Serializable {
    private String categoryname;// 分类
    private String imageurl;//

    public CategoryStc() {
    }

    public CategoryStc(String categoryname, String imageurl) {
        this.categoryname = categoryname;
        this.imageurl = imageurl;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
