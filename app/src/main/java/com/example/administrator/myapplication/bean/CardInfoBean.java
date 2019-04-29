package com.example.administrator.myapplication.bean;

/**
 * Created by Administrator on 2019/4/21.
 */

public class CardInfoBean {

    /**
     * code : 200
     * msg : 获取成功
     * data : {"account":"true","card":"true","estate":"true","marry":"true","license":"true","bank":"true","assess":"true","yield":"true","farther":"true","credit":"true","mortgage":"true","schema":"true"}
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
         * account : true
         * card : true
         * estate : true
         * marry : true
         * license : true
         * bank : true
         * assess : true
         * yield : true
         * farther : true
         * credit : true
         * mortgage : true
         * schema : true
         */

        private String account;
        private String card;
        private String estate;
        private String marry;
        private String license;
        private String bank;
        private String assess;
        private String yield;
        private String farther;
        private String credit;
        private String mortgage;
        private String schema;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getEstate() {
            return estate;
        }

        public void setEstate(String estate) {
            this.estate = estate;
        }

        public String getMarry() {
            return marry;
        }

        public void setMarry(String marry) {
            this.marry = marry;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getAssess() {
            return assess;
        }

        public void setAssess(String assess) {
            this.assess = assess;
        }

        public String getYield() {
            return yield;
        }

        public void setYield(String yield) {
            this.yield = yield;
        }

        public String getFarther() {
            return farther;
        }

        public void setFarther(String farther) {
            this.farther = farther;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getMortgage() {
            return mortgage;
        }

        public void setMortgage(String mortgage) {
            this.mortgage = mortgage;
        }

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }
    }
}
