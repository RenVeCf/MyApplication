package com.example.administrator.myapplication.bean;

import java.util.List;

public class DocumentsReceivedBean {

    /**
     * code : 200
     * msg : 获取成功
     * data : [{"for_id":2,"for_sponsor":62,"for_proid":1,"for_accept":62,"for_watermark":0,"for_send":0,"for_download":0,"for_locking":0,"for_attention":0,"for_addtime":"1552987257","pro_id":1,"pro_name":"王","pro_card":"510722199412256854","pro_phone":"13661750474","pro_handle":1,"pro_marriage":2,"pro_room":0,"pro_userid":62,"pro_addtime":"1552902402","pro_amount":20000,"pro_uptime":null,"pro_del":0}]
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
         * for_id : 2
         * for_sponsor : 62
         * for_proid : 1
         * for_accept : 62
         * for_watermark : 0
         * for_send : 0
         * for_download : 0
         * for_locking : 0
         * for_attention : 0
         * for_addtime : 1552987257
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

        private int for_id;
        private int for_sponsor;
        private int for_proid;
        private int for_accept;
        private int for_watermark;
        private int for_send;
        private int for_download;
        private int for_locking;
        private int for_attention;
        private String for_addtime;
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

        public int getFor_id() {
            return for_id;
        }

        public void setFor_id(int for_id) {
            this.for_id = for_id;
        }

        public int getFor_sponsor() {
            return for_sponsor;
        }

        public void setFor_sponsor(int for_sponsor) {
            this.for_sponsor = for_sponsor;
        }

        public int getFor_proid() {
            return for_proid;
        }

        public void setFor_proid(int for_proid) {
            this.for_proid = for_proid;
        }

        public int getFor_accept() {
            return for_accept;
        }

        public void setFor_accept(int for_accept) {
            this.for_accept = for_accept;
        }

        public int getFor_watermark() {
            return for_watermark;
        }

        public void setFor_watermark(int for_watermark) {
            this.for_watermark = for_watermark;
        }

        public int getFor_send() {
            return for_send;
        }

        public void setFor_send(int for_send) {
            this.for_send = for_send;
        }

        public int getFor_download() {
            return for_download;
        }

        public void setFor_download(int for_download) {
            this.for_download = for_download;
        }

        public int getFor_locking() {
            return for_locking;
        }

        public void setFor_locking(int for_locking) {
            this.for_locking = for_locking;
        }

        public int getFor_attention() {
            return for_attention;
        }

        public void setFor_attention(int for_attention) {
            this.for_attention = for_attention;
        }

        public String getFor_addtime() {
            return for_addtime;
        }

        public void setFor_addtime(String for_addtime) {
            this.for_addtime = for_addtime;
        }

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
