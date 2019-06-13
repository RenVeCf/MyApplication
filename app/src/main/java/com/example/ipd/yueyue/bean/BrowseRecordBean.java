package com.example.ipd.yueyue.bean;

import java.util.List;

public class BrowseRecordBean {

    /**
     * code : 200
     * msg : false
     * data : [{"bro_type":1,"bro_addtime":1553135719,"sponsorname":"13661750474","hostname":"13661750474"},{"bro_type":0,"bro_addtime":1553135458,"sponsorname":"13661750474","hostname":"13661750474"}]
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
         * bro_type : 1
         * bro_addtime : 1553135719
         * sponsorname : 13661750474
         * hostname : 13661750474
         */

        private int bro_type;
        private int bro_addtime;
        private String sponsorname;
        private String hostname;

        public int getBro_type() {
            return bro_type;
        }

        public void setBro_type(int bro_type) {
            this.bro_type = bro_type;
        }

        public int getBro_addtime() {
            return bro_addtime;
        }

        public void setBro_addtime(int bro_addtime) {
            this.bro_addtime = bro_addtime;
        }

        public String getSponsorname() {
            return sponsorname;
        }

        public void setSponsorname(String sponsorname) {
            this.sponsorname = sponsorname;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }
    }
}
