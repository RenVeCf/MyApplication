package com.example.administrator.myapplication.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/23.
 */

public class GetHouseholdRegistrationBookBean {

    /**
     * code : 200
     * msg : 获取成功
     * data : [{"acc_type":0,"data":[{"acc_id":4,"acc_userid":62,"acc_type":0,"acc_imgurl":"路径","acc_proid":1}]}]
     */

    private int code;
    private String msg;
    private List<DataBeanX> data;

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

    public List<DataBeanX> getData() {
        return data;
    }

    public void setData(List<DataBeanX> data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * acc_type : 0
         * data : [{"acc_id":4,"acc_userid":62,"acc_type":0,"acc_imgurl":"路径","acc_proid":1}]
         */

        private String acc_type;
        private List<DataBean> data;

        public String getAcc_type() {
            return acc_type;
        }

        public void setAcc_type(String acc_type) {
            this.acc_type = acc_type;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public void generateData(){
            ArrayList<DataBean> datas = new ArrayList<>();
            datas.add(new DataBean());
            this.data = datas;
        }

        public static class DataBean {
            /**
             * acc_id : 4
             * acc_userid : 62
             * acc_type : 0
             * acc_imgurl : 路径
             * acc_proid : 1
             */

            private int acc_id;
            private int acc_userid;
            private String acc_type;
            private String acc_imgurl;
            private int acc_proid;

            public String getCompleteType(){
                return acc_type+"户口本";
            }

            public int getAcc_id() {
                return acc_id;
            }

            public void setAcc_id(int acc_id) {
                this.acc_id = acc_id;
            }

            public int getAcc_userid() {
                return acc_userid;
            }

            public void setAcc_userid(int acc_userid) {
                this.acc_userid = acc_userid;
            }

            public String getAcc_type() {
                return acc_type;
            }

            public void setAcc_type(String acc_type) {
                this.acc_type = acc_type;
            }

            public String getAcc_imgurl() {
                return acc_imgurl;
            }

            public void setAcc_imgurl(String acc_imgurl) {
                this.acc_imgurl = acc_imgurl;
            }

            public int getAcc_proid() {
                return acc_proid;
            }

            public void setAcc_proid(int acc_proid) {
                this.acc_proid = acc_proid;
            }
        }
    }
}
