package com.example.ipd.yueyue.bean;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/7/11.
 */
public class BriefSummaryBean {

    /**
     * code : 200
     * msg : 获取成功
     * data : {"sch_id":119,"sch_userid":74,"sch_loanuse":null,"sch_asd":null,"sch_stream":null,"sch_amount":null,"sch_proid":121,"sch_addtime":"1556604294"}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sch_id : 119
         * sch_userid : 74
         * sch_loanuse : null
         * sch_asd : null
         * sch_stream : null
         * sch_amount : null
         * sch_proid : 121
         * sch_addtime : 1556604294
         */

        private int sch_id;
        private int sch_userid;
        private Object sch_loanuse;
        private Object sch_asd;
        private Object sch_stream;
        private Object sch_amount;
        private int sch_proid;
        private String sch_addtime;

        public int getSch_id() {
            return sch_id;
        }

        public void setSch_id(int sch_id) {
            this.sch_id = sch_id;
        }

        public int getSch_userid() {
            return sch_userid;
        }

        public void setSch_userid(int sch_userid) {
            this.sch_userid = sch_userid;
        }

        public Object getSch_loanuse() {
            return sch_loanuse;
        }

        public void setSch_loanuse(Object sch_loanuse) {
            this.sch_loanuse = sch_loanuse;
        }

        public Object getSch_asd() {
            return sch_asd;
        }

        public void setSch_asd(Object sch_asd) {
            this.sch_asd = sch_asd;
        }

        public Object getSch_stream() {
            return sch_stream;
        }

        public void setSch_stream(Object sch_stream) {
            this.sch_stream = sch_stream;
        }

        public Object getSch_amount() {
            return sch_amount;
        }

        public void setSch_amount(Object sch_amount) {
            this.sch_amount = sch_amount;
        }

        public int getSch_proid() {
            return sch_proid;
        }

        public void setSch_proid(int sch_proid) {
            this.sch_proid = sch_proid;
        }

        public String getSch_addtime() {
            return sch_addtime;
        }

        public void setSch_addtime(String sch_addtime) {
            this.sch_addtime = sch_addtime;
        }
    }
}
