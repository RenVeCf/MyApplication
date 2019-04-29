package com.example.administrator.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/23.
 */

public class GetMarryImgBean {

    /**
     * code : 200
     * msg : 获取成功
     * data : [{"mar_id":35,"mar_userid":74,"mar_type":"\\u672c\\u4eba","mar_page1":"/pic/20190423/0223d26151356a153b14d3758a2e4d39.jpeg","mar_page2":"/pic/20190423/3773b16cc20f7bdfa2e33ac28eb40766.jpeg","mar_page3":"/pic/20190423/b462ad98021ae896df9f4a2ec8d73191.jpeg","mar_proid":47}]
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
         * mar_id : 35
         * mar_userid : 74
         * mar_type : \u672c\u4eba
         * mar_page1 : /pic/20190423/0223d26151356a153b14d3758a2e4d39.jpeg
         * mar_page2 : /pic/20190423/3773b16cc20f7bdfa2e33ac28eb40766.jpeg
         * mar_page3 : /pic/20190423/b462ad98021ae896df9f4a2ec8d73191.jpeg
         * mar_proid : 47
         */

        private int mar_id;
        private int mar_userid;
        private String mar_type;
        private String mar_page1;
        private String mar_page2;
        private String mar_page3;
        private int mar_proid;
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getMar_id() {
            return mar_id;
        }

        public void setMar_id(int mar_id) {
            this.mar_id = mar_id;
        }

        public int getMar_userid() {
            return mar_userid;
        }

        public void setMar_userid(int mar_userid) {
            this.mar_userid = mar_userid;
        }

        public String getMar_type() {
            return mar_type;
        }

        public void setMar_type(String mar_type) {
            this.mar_type = mar_type;
        }

        public String getMar_page1() {
            return mar_page1;
        }

        public void setMar_page1(String mar_page1) {
            this.mar_page1 = mar_page1;
        }

        public String getMar_page2() {
            return mar_page2;
        }

        public void setMar_page2(String mar_page2) {
            this.mar_page2 = mar_page2;
        }

        public String getMar_page3() {
            return mar_page3;
        }

        public void setMar_page3(String mar_page3) {
            this.mar_page3 = mar_page3;
        }

        public int getMar_proid() {
            return mar_proid;
        }

        public void setMar_proid(int mar_proid) {
            this.mar_proid = mar_proid;
        }
    }
}
