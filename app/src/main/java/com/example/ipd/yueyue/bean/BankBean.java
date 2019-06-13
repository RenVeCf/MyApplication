package com.example.ipd.yueyue.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/24.
 */

public class BankBean {

    /**
     * code : 200
     * msg : 获取成功
     * data : [{"bank_type":"农业银行","data":[{"bank_id":3,"bank_userid":62,"bank_type":"农业银行","bank_imgurl":"/images/default_head.png","bank_proid":1}]}]
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
         * bank_type : 农业银行
         * data : [{"bank_id":3,"bank_userid":62,"bank_type":"农业银行","bank_imgurl":"/images/default_head.png","bank_proid":1}]
         */

        private String bank_type;
        private List<DataBean> data;

        public String getBank_type() {
            return bank_type;
        }

        public void setBank_type(String bank_type) {
            this.bank_type = bank_type;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * bank_id : 3
             * bank_userid : 62
             * bank_type : 农业银行
             * bank_imgurl : /images/default_head.png
             * bank_proid : 1
             */

            private int bank_id;
            private int bank_userid;
            private String bank_type;
            private String bank_imgurl;
            private int bank_proid;

            public int getBank_id() {
                return bank_id;
            }

            public void setBank_id(int bank_id) {
                this.bank_id = bank_id;
            }

            public int getBank_userid() {
                return bank_userid;
            }

            public void setBank_userid(int bank_userid) {
                this.bank_userid = bank_userid;
            }

            public String getBank_type() {
                return bank_type;
            }

            public void setBank_type(String bank_type) {
                this.bank_type = bank_type;
            }

            public String getBank_imgurl() {
                return bank_imgurl;
            }

            public void setBank_imgurl(String bank_imgurl) {
                this.bank_imgurl = bank_imgurl;
            }

            public int getBank_proid() {
                return bank_proid;
            }

            public void setBank_proid(int bank_proid) {
                this.bank_proid = bank_proid;
            }
        }
    }
}
