package com.example.ipd.yueyue.bean;

/**
 * Created by Administrator on 2019/4/24.
 */

public class SupplementaryBean {
    private String far_type;
    private String far_imgurl;

    public String getImgName() {
        return far_type;
    }

    public void setImgName(String imgName) {
        this.far_type = imgName;
    }

    public String getImgPath() {
        return far_imgurl;
    }

    public void setImgPath(String imgPath) {
        this.far_imgurl = imgPath;
    }
}
