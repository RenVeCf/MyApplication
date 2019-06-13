package com.example.ipd.yueyue.bean;

public class GetMyInfoBean {

    /**
     * code : 200
     * msg : 获取成功
     * data : {"profile":{"pro_id":41,"pro_name":"嗯没有了","pro_card":"610302199111210531","pro_phone":"18502994087","pro_handle":0,"pro_marriage":0,"pro_room":0,"pro_userid":74,"pro_addtime":"1555398102","pro_amount":3699,"pro_uptime":"1555398102","pro_del":0,"pro_knit":0,"pro_code":"MY155539810274"},"schema":{"sch_id":39,"sch_userid":74,"sch_loanuse":null,"sch_asd":null,"sch_stream":null,"sch_amount":null,"sch_proid":41,"sch_addtime":"1555398102"}}
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
         * profile : {"pro_id":41,"pro_name":"嗯没有了","pro_card":"610302199111210531","pro_phone":"18502994087","pro_handle":0,"pro_marriage":0,"pro_room":0,"pro_userid":74,"pro_addtime":"1555398102","pro_amount":3699,"pro_uptime":"1555398102","pro_del":0,"pro_knit":0,"pro_code":"MY155539810274"}
         * schema : {"sch_id":39,"sch_userid":74,"sch_loanuse":null,"sch_asd":null,"sch_stream":null,"sch_amount":null,"sch_proid":41,"sch_addtime":"1555398102"}
         */

        private ProfileBean profile;
        private SchemaBean schema;

        public ProfileBean getProfile() {
            return profile;
        }

        public void setProfile(ProfileBean profile) {
            this.profile = profile;
        }

        public SchemaBean getSchema() {
            return schema;
        }

        public void setSchema(SchemaBean schema) {
            this.schema = schema;
        }

        public static class ProfileBean {
            /**
             * pro_id : 41
             * pro_name : 嗯没有了
             * pro_card : 610302199111210531
             * pro_phone : 18502994087
             * pro_handle : 0
             * pro_marriage : 0
             * pro_room : 0
             * pro_userid : 74
             * pro_addtime : 1555398102
             * pro_amount : 3699
             * pro_uptime : 1555398102
             * pro_del : 0
             * pro_knit : 0
             * pro_code : MY155539810274
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
            private String pro_uptime;
            private int pro_del;
            private int pro_knit;
            private String pro_code;

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

            public String getPro_uptime() {
                return pro_uptime;
            }

            public void setPro_uptime(String pro_uptime) {
                this.pro_uptime = pro_uptime;
            }

            public int getPro_del() {
                return pro_del;
            }

            public void setPro_del(int pro_del) {
                this.pro_del = pro_del;
            }

            public int getPro_knit() {
                return pro_knit;
            }

            public void setPro_knit(int pro_knit) {
                this.pro_knit = pro_knit;
            }

            public String getPro_code() {
                return pro_code;
            }

            public void setPro_code(String pro_code) {
                this.pro_code = pro_code;
            }
        }

        public static class SchemaBean {
            /**
             * sch_id : 39
             * sch_userid : 74
             * sch_loanuse : null
             * sch_asd : null
             * sch_stream : null
             * sch_amount : null
             * sch_proid : 41
             * sch_addtime : 1555398102
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
}
