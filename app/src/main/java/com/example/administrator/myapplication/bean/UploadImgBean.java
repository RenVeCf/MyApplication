package com.example.administrator.myapplication.bean;

import java.util.List;

public class UploadImgBean {

    /**
     * code : 200
     * msg :
     * data : ["/pic/20190417/5151030d17a4a39725bf0b75f4cf8b20.png"]
     */

    private int code;
    private String msg;
    private List<String> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
