package com.example.ipd.yueyue.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/24.
 */

public class GetHouseImgBean {

    /**
     * code : 200
     * msg : 获取成功
     * data : [{"est_id":36,"est_userid":74,"est_type":"\\u672c\\u4eba","est_proid":47,"est_page1":"","est_page2":"","est_page3":"","est_page4":"","est_page5":""}]
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
         * est_id : 36
         * est_userid : 74
         * est_type : \u672c\u4eba
         * est_proid : 47
         * est_page1 :
         * est_page2 :
         * est_page3 :
         * est_page4 :
         * est_page5 :
         */

        private int est_id;
        private int est_userid;
        private String est_type;
        private int est_proid;
        private String est_page1;
        private String est_page2;
        private String est_page3;
        private String est_page4;
        private String est_page5;

        public int getEst_id() {
            return est_id;
        }

        public void setEst_id(int est_id) {
            this.est_id = est_id;
        }

        public int getEst_userid() {
            return est_userid;
        }

        public void setEst_userid(int est_userid) {
            this.est_userid = est_userid;
        }

        public String getEst_type() {
            return est_type;
        }

        public void setEst_type(String est_type) {
            this.est_type = est_type;
        }

        public int getEst_proid() {
            return est_proid;
        }

        public void setEst_proid(int est_proid) {
            this.est_proid = est_proid;
        }

        public String getEst_page1() {
            return est_page1;
        }

        public void setEst_page1(String est_page1) {
            this.est_page1 = est_page1;
        }

        public String getEst_page2() {
            return est_page2;
        }

        public void setEst_page2(String est_page2) {
            this.est_page2 = est_page2;
        }

        public String getEst_page3() {
            return est_page3;
        }

        public void setEst_page3(String est_page3) {
            this.est_page3 = est_page3;
        }

        public String getEst_page4() {
            return est_page4;
        }

        public void setEst_page4(String est_page4) {
            this.est_page4 = est_page4;
        }

        public String getEst_page5() {
            return est_page5;
        }

        public void setEst_page5(String est_page5) {
            this.est_page5 = est_page5;
        }
    }
}
