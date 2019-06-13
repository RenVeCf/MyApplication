package com.example.ipd.yueyue.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/4/21.
 */

public class SelectMyInfoBean implements Serializable {

    /**
     * code : 200
     * msg : 获取成功
     * data : {"profile":{"pro_id":47,"pro_name":"哈哈哈","pro_card":"610302199111210531","pro_phone":"18502994087","pro_handle":0,"pro_marriage":0,"pro_room":0,"pro_userid":74,"pro_addtime":"1555998653","pro_amount":1580,"pro_uptime":"1556023031","pro_del":0,"pro_knit":0,"pro_code":"MY155599865374"},"schema":{"sch_id":45,"sch_userid":74,"sch_loanuse":null,"sch_asd":null,"sch_stream":null,"sch_amount":null,"sch_proid":47,"sch_addtime":"1556023032"},"marry":[],"card":[{"card_id":74,"card_userid":74,"card_type":"\\u672c\\u4eba","card_positive":"/pic/20190423/4c522cf070227e055d95c63fef1ad7fd.jpeg","card_negative":"/pic/20190423/c2c9e5964481c2a6d02bce18c8e3e7ae.jpeg","card_proid":47},{"card_id":75,"card_userid":74,"card_type":"\\u7236\\u6bcd","card_positive":"/pic/20190423/8820680e376ec81b9c6c7c6f959e8381.jpeg","card_negative":"/pic/20190423/264b3d2a05e03f2508d529c7baf77616.jpeg","card_proid":47}],"account":[{"acc_type":"\\u6237\\u53e3\\u672c","data":[{"acc_id":42,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/4e273bf007dd4c2ae8068ab6f95fd699.jpeg","acc_proid":47},{"acc_id":43,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/324007122e8a2da91e49a4e2c042ea99.jpeg","acc_proid":47},{"acc_id":44,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/2cccd7e07f6d57965bfe7e152bbc75cd.jpeg","acc_proid":47},{"acc_id":45,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/b41ca25f2e966bc59d356708ef9d5287.jpeg","acc_proid":47},{"acc_id":46,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/2fd638f67c9fa2656f1c9136695d1a37.jpeg","acc_proid":47},{"acc_id":47,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/240e36389660b622d99f073deb26f25a.jpeg","acc_proid":47},{"acc_id":48,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/9833a3ba56c999704c538d34c3cd2bd5.jpeg","acc_proid":47}]}],"estate":[],"license":[],"bank":[],"yield":[],"assess":[],"farther":[],"credit":[],"mortgage":[]}
     */

    private int code;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX implements Serializable {
        /**
         * profile : {"pro_id":47,"pro_name":"哈哈哈","pro_card":"610302199111210531","pro_phone":"18502994087","pro_handle":0,"pro_marriage":0,"pro_room":0,"pro_userid":74,"pro_addtime":"1555998653","pro_amount":1580,"pro_uptime":"1556023031","pro_del":0,"pro_knit":0,"pro_code":"MY155599865374"}
         * schema : {"sch_id":45,"sch_userid":74,"sch_loanuse":null,"sch_asd":null,"sch_stream":null,"sch_amount":null,"sch_proid":47,"sch_addtime":"1556023032"}
         * marry : []
         * card : [{"card_id":74,"card_userid":74,"card_type":"\\u672c\\u4eba","card_positive":"/pic/20190423/4c522cf070227e055d95c63fef1ad7fd.jpeg","card_negative":"/pic/20190423/c2c9e5964481c2a6d02bce18c8e3e7ae.jpeg","card_proid":47},{"card_id":75,"card_userid":74,"card_type":"\\u7236\\u6bcd","card_positive":"/pic/20190423/8820680e376ec81b9c6c7c6f959e8381.jpeg","card_negative":"/pic/20190423/264b3d2a05e03f2508d529c7baf77616.jpeg","card_proid":47}]
         * account : [{"acc_type":"\\u6237\\u53e3\\u672c","data":[{"acc_id":42,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/4e273bf007dd4c2ae8068ab6f95fd699.jpeg","acc_proid":47},{"acc_id":43,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/324007122e8a2da91e49a4e2c042ea99.jpeg","acc_proid":47},{"acc_id":44,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/2cccd7e07f6d57965bfe7e152bbc75cd.jpeg","acc_proid":47},{"acc_id":45,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/b41ca25f2e966bc59d356708ef9d5287.jpeg","acc_proid":47},{"acc_id":46,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/2fd638f67c9fa2656f1c9136695d1a37.jpeg","acc_proid":47},{"acc_id":47,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/240e36389660b622d99f073deb26f25a.jpeg","acc_proid":47},{"acc_id":48,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/9833a3ba56c999704c538d34c3cd2bd5.jpeg","acc_proid":47}]}]
         * estate : []
         * license : []
         * bank : []
         * yield : []
         * assess : []
         * farther : []
         * credit : []
         * mortgage : []
         */

        private ProfileBean profile;
        private SchemaBean schema;
        private List<?> marry;
        private List<CardBean> card;
        private List<AccountBean> account;
        private List<?> estate;
        private List<?> license;
        private List<?> bank;
        private List<?> yield;
        private List<?> assess;
        private List<?> farther;
        private List<?> credit;
        private List<?> mortgage;

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

        public List<?> getMarry() {
            return marry;
        }

        public void setMarry(List<?> marry) {
            this.marry = marry;
        }

        public List<CardBean> getCard() {
            return card;
        }

        public void setCard(List<CardBean> card) {
            this.card = card;
        }

        public List<AccountBean> getAccount() {
            return account;
        }

        public void setAccount(List<AccountBean> account) {
            this.account = account;
        }

        public List<?> getEstate() {
            return estate;
        }

        public void setEstate(List<?> estate) {
            this.estate = estate;
        }

        public List<?> getLicense() {
            return license;
        }

        public void setLicense(List<?> license) {
            this.license = license;
        }

        public List<?> getBank() {
            return bank;
        }

        public void setBank(List<?> bank) {
            this.bank = bank;
        }

        public List<?> getYield() {
            return yield;
        }

        public void setYield(List<?> yield) {
            this.yield = yield;
        }

        public List<?> getAssess() {
            return assess;
        }

        public void setAssess(List<?> assess) {
            this.assess = assess;
        }

        public List<?> getFarther() {
            return farther;
        }

        public void setFarther(List<?> farther) {
            this.farther = farther;
        }

        public List<?> getCredit() {
            return credit;
        }

        public void setCredit(List<?> credit) {
            this.credit = credit;
        }

        public List<?> getMortgage() {
            return mortgage;
        }

        public void setMortgage(List<?> mortgage) {
            this.mortgage = mortgage;
        }

        public static class ProfileBean implements Serializable {
            /**
             * pro_id : 47
             * pro_name : 哈哈哈
             * pro_card : 610302199111210531
             * pro_phone : 18502994087
             * pro_handle : 0
             * pro_marriage : 0
             * pro_room : 0
             * pro_userid : 74
             * pro_addtime : 1555998653
             * pro_amount : 1580
             * pro_uptime : 1556023031
             * pro_del : 0
             * pro_knit : 0
             * pro_code : MY155599865374
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

        public static class SchemaBean implements Serializable {
            /**
             * sch_id : 45
             * sch_userid : 74
             * sch_loanuse : null
             * sch_asd : null
             * sch_stream : null
             * sch_amount : null
             * sch_proid : 47
             * sch_addtime : 1556023032
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

        public static class CardBean implements Serializable {
            /**
             * card_id : 74
             * card_userid : 74
             * card_type : \u672c\u4eba
             * card_positive : /pic/20190423/4c522cf070227e055d95c63fef1ad7fd.jpeg
             * card_negative : /pic/20190423/c2c9e5964481c2a6d02bce18c8e3e7ae.jpeg
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

        public static class AccountBean implements Serializable {
            /**
             * acc_type : \u6237\u53e3\u672c
             * data : [{"acc_id":42,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/4e273bf007dd4c2ae8068ab6f95fd699.jpeg","acc_proid":47},{"acc_id":43,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/324007122e8a2da91e49a4e2c042ea99.jpeg","acc_proid":47},{"acc_id":44,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/2cccd7e07f6d57965bfe7e152bbc75cd.jpeg","acc_proid":47},{"acc_id":45,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/b41ca25f2e966bc59d356708ef9d5287.jpeg","acc_proid":47},{"acc_id":46,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/2fd638f67c9fa2656f1c9136695d1a37.jpeg","acc_proid":47},{"acc_id":47,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/240e36389660b622d99f073deb26f25a.jpeg","acc_proid":47},{"acc_id":48,"acc_userid":74,"acc_type":"\\u6237\\u53e3\\u672c","acc_imgurl":"/pic/20190423/9833a3ba56c999704c538d34c3cd2bd5.jpeg","acc_proid":47}]
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

            public static class DataBean implements Serializable {
                /**
                 * acc_id : 42
                 * acc_userid : 74
                 * acc_type : \u6237\u53e3\u672c
                 * acc_imgurl : /pic/20190423/4e273bf007dd4c2ae8068ab6f95fd699.jpeg
                 * acc_proid : 47
                 */

                private int acc_id;
                private int acc_userid;
                private String acc_type;
                private String acc_imgurl;
                private int acc_proid;

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
}
