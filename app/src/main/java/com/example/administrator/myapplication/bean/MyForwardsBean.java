package com.example.administrator.myapplication.bean;

public class MyForwardsBean {

    /**
     * code : 200
     * msg : 转发成功
     * data : true
     */

    private int code;
    private String msg;
    private boolean data;

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

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }
}
