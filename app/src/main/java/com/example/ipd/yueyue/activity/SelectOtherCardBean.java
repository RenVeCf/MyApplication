package com.example.ipd.yueyue.activity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/30.
 */
class SelectOtherCardBean implements Parcelable {

    private List<CardBean> card;

    public List<CardBean> getCard() {
        return card;
    }

    public void setCard(List<CardBean> card) {
        this.card = card;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static class CardBean implements Serializable {
        /**
         * card_id : 192
         * card_userid : 85
         * card_type : 本人
         * card_positive : /pic/20190430/38aa7589767f17f659acf45e29e05093.jpeg
         * card_negative : /pic/20190430/9b22dd581497dd3a20c38e9af1ab3312.jpeg
         * card_proid : 124
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
