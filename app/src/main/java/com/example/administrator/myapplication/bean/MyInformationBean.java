package com.example.administrator.myapplication.bean;

import java.util.List;

public class MyInformationBean {

    /**
     * code : 200
     * msg : 获取成功
     * data : [{"pro_id":1,"pro_name":"王","pro_card":"510722199412256854","pro_phone":"13661750474","pro_handle":1,"pro_marriage":2,"pro_room":0,"pro_userid":62,"pro_addtime":"1552902402","pro_amount":20000,"pro_uptime":null,"pro_del":0}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pro_id : 1
         * pro_name : 王
         * pro_card : 510722199412256854
         * pro_phone : 13661750474
         * pro_handle : 1
         * pro_marriage : 2
         * pro_room : 0
         * pro_userid : 62
         * pro_addtime : 1552902402
         * pro_amount : 20000
         * pro_uptime : null
         * pro_del : 0
         */

        private int pro_id;
        private String pro_name;
        private String pro_card;
        private String pro_phone;
        private int pro_handle;
        private int pro_marriage;
        private int pro_room;
        private int pro_userid;
        private String pro_addtime;
        private int pro_amount;
        private Object pro_uptime;
        private int pro_del;

        public int getPro_id() {
            return pro_id;
        }

        public void setPro_id(int pro_id) {
            this.pro_id = pro_id;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public String getPro_card() {
            return pro_card;
        }

        public void setPro_card(String pro_card) {
            this.pro_card = pro_card;
        }

        public String getPro_phone() {
            return pro_phone;
        }

        public void setPro_phone(String pro_phone) {
            this.pro_phone = pro_phone;
        }

        public int getPro_handle() {
            return pro_handle;
        }

        public void setPro_handle(int pro_handle) {
            this.pro_handle = pro_handle;
        }

        public int getPro_marriage() {
            return pro_marriage;
        }

        public void setPro_marriage(int pro_marriage) {
            this.pro_marriage = pro_marriage;
        }

        public int getPro_room() {
            return pro_room;
        }

        public void setPro_room(int pro_room) {
            this.pro_room = pro_room;
        }

        public int getPro_userid() {
            return pro_userid;
        }

        public void setPro_userid(int pro_userid) {
            this.pro_userid = pro_userid;
        }

        public String getPro_addtime() {
            return pro_addtime;
        }

        public void setPro_addtime(String pro_addtime) {
            this.pro_addtime = pro_addtime;
        }

        public int getPro_amount() {
            return pro_amount;
        }

        public void setPro_amount(int pro_amount) {
            this.pro_amount = pro_amount;
        }

        public Object getPro_uptime() {
            return pro_uptime;
        }

        public void setPro_uptime(Object pro_uptime) {
            this.pro_uptime = pro_uptime;
        }

        public int getPro_del() {
            return pro_del;
        }

        public void setPro_del(int pro_del) {
            this.pro_del = pro_del;
        }
    }
}
