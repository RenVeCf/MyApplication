package com.example.ipd.yueyue.bean;

public class ReceiveDownloadBean {

    /**
     * code : 200
     * msg : 转发成功
     * data : true
     */

    private int code;
    private String msg;
    private Object data;

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

    public Object isData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
