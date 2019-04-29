package com.example.administrator.myapplication.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/22.
 */

public class GetIdImgBean {

    /**
     * code : 200
     * msg : 获取成功
     * data : [{"card_id":71,"card_userid":74,"card_type":"\\u672c\\u4eba","card_positive":"/pic/20190423/7b81488178e170dce198f48421256553.jpeg","card_negative":"/pic/20190423/8cc42387e51903d80abc90ec92ea70a2.jpeg","card_proid":47}]
     */

    private int code;
    private String msg;
    private List<DataBean> data = new ArrayList<>();
    private DataBean a;

    public GetIdImgBean() {
        data.add(a);
    }

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
         * card_id : 71
         * card_userid : 74
         * card_type : \u672c\u4eba
         * card_positive : /pic/20190423/7b81488178e170dce198f48421256553.jpeg
         * card_negative : /pic/20190423/8cc42387e51903d80abc90ec92ea70a2.jpeg
         * card_proid : 47
         */

        private int card_id;
        private int card_userid;
        private String card_type;
        private String card_positive;
        private String card_negative;
        private int card_proid;



        public int getCard_id() {
            return card_id;
        }

        public void setCard_id(int card_id) {
            this.card_id = card_id;
        }

        public int getCard_userid() {
            return card_userid;
        }

        public void setCard_userid(int card_userid) {
            this.card_userid = card_userid;
        }

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public String getCard_positive() {
            return card_positive;
        }

        public void setCard_positive(String card_positive) {
            this.card_positive = card_positive;
        }

        public String getCard_negative() {
            return card_negative;
        }

        public void setCard_negative(String card_negative) {
            this.card_negative = card_negative;
        }

        public int getCard_proid() {
            return card_proid;
        }

        public void setCard_proid(int card_proid) {
            this.card_proid = card_proid;
        }
    }
}
