package com.example.administrator.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/24.
 */

public class GetCollateralImgBean {

    /**
     * code : 200
     * msg : 获取成功
     * data : [{"mor_id":21,"mor_userid":74,"mor_type":"\\u62b5\\u62bc\\u7269","mor_proid":47,"mor_page1":"/pic/20190424/2e737f30354ca38e26f7de4ac9105cc7.jpeg","mor_page2":"","mor_page3":"","mor_page4":"","mor_page5":"","mor_page6":"","mor_page7":"","mor_page8":""},{"mor_id":22,"mor_userid":74,"mor_type":"\\u62b5\\u62bc\\u7269","mor_proid":47,"mor_page1":"","mor_page2":"","mor_page3":"","mor_page4":"","mor_page5":"","mor_page6":"","mor_page7":"","mor_page8":""}]
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
         * mor_id : 21
         * mor_userid : 74
         * mor_type : \u62b5\u62bc\u7269
         * mor_proid : 47
         * mor_page1 : /pic/20190424/2e737f30354ca38e26f7de4ac9105cc7.jpeg
         * mor_page2 :
         * mor_page3 :
         * mor_page4 :
         * mor_page5 :
         * mor_page6 :
         * mor_page7 :
         * mor_page8 :
         */

        private int mor_id;
        private int mor_userid;
        private String mor_type;
        private int mor_proid;
        private String mor_page1;
        private String mor_page2;
        private String mor_page3;
        private String mor_page4;
        private String mor_page5;
        private String mor_page6;
        private String mor_page7;
        private String mor_page8;

        public int getMor_id() {
            return mor_id;
        }

        public void setMor_id(int mor_id) {
            this.mor_id = mor_id;
        }

        public int getMor_userid() {
            return mor_userid;
        }

        public void setMor_userid(int mor_userid) {
            this.mor_userid = mor_userid;
        }

        public String getMor_type() {
            return mor_type;
        }

        public void setMor_type(String mor_type) {
            this.mor_type = mor_type;
        }

        public int getMor_proid() {
            return mor_proid;
        }

        public void setMor_proid(int mor_proid) {
            this.mor_proid = mor_proid;
        }

        public String getMor_page1() {
            return mor_page1;
        }

        public void setMor_page1(String mor_page1) {
            this.mor_page1 = mor_page1;
        }

        public String getMor_page2() {
            return mor_page2;
        }

        public void setMor_page2(String mor_page2) {
            this.mor_page2 = mor_page2;
        }

        public String getMor_page3() {
            return mor_page3;
        }

        public void setMor_page3(String mor_page3) {
            this.mor_page3 = mor_page3;
        }

        public String getMor_page4() {
            return mor_page4;
        }

        public void setMor_page4(String mor_page4) {
            this.mor_page4 = mor_page4;
        }

        public String getMor_page5() {
            return mor_page5;
        }

        public void setMor_page5(String mor_page5) {
            this.mor_page5 = mor_page5;
        }

        public String getMor_page6() {
            return mor_page6;
        }

        public void setMor_page6(String mor_page6) {
            this.mor_page6 = mor_page6;
        }

        public String getMor_page7() {
            return mor_page7;
        }

        public void setMor_page7(String mor_page7) {
            this.mor_page7 = mor_page7;
        }

        public String getMor_page8() {
            return mor_page8;
        }

        public void setMor_page8(String mor_page8) {
            this.mor_page8 = mor_page8;
        }
    }
}
