package com.example.ipd.yueyue.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/24.
 */

public class GetAssessmentImgBean {

    /**
     * code : 200
     * msg : 获取成功
     * data : [{"ass_id":27,"ass_userid":74,"ass_type":"\\u8bc4\\u4f30\\u62a5\\u544a","ass_imgurl":"/pic/20190424/8430b0b7c3025c5ae15b046070fb721b.jpeg","ass_proid":47},{"ass_id":28,"ass_userid":74,"ass_type":"\\u8bc4\\u4f30\\u62a5\\u544a","ass_imgurl":"/pic/20190424/458c72245ed8a104e54a9834b7da5c63.jpeg","ass_proid":47},{"ass_id":29,"ass_userid":74,"ass_type":"\\u8bc4\\u4f30\\u62a5\\u544a","ass_imgurl":"/pic/20190424/13d80dcf42e0c58887da440e21ec743c.jpeg","ass_proid":47},{"ass_id":30,"ass_userid":74,"ass_type":"\\u8bc4\\u4f30\\u62a5\\u544a","ass_imgurl":"/pic/20190424/6d8b12d29ca003403640dd0d0ebea536.jpeg","ass_proid":47}]
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
         * ass_id : 27
         * ass_userid : 74
         * ass_type : \u8bc4\u4f30\u62a5\u544a
         * ass_imgurl : /pic/20190424/8430b0b7c3025c5ae15b046070fb721b.jpeg
         * ass_proid : 47
         */

        private int ass_id;
        private int ass_userid;
        private String ass_type;
        private String ass_imgurl;
        private int ass_proid;

        public int getAss_id() {
            return ass_id;
        }

        public void setAss_id(int ass_id) {
            this.ass_id = ass_id;
        }

        public int getAss_userid() {
            return ass_userid;
        }

        public void setAss_userid(int ass_userid) {
            this.ass_userid = ass_userid;
        }

        public String getAss_type() {
            return ass_type;
        }

        public void setAss_type(String ass_type) {
            this.ass_type = ass_type;
        }

        public String getAss_imgurl() {
            return ass_imgurl;
        }

        public void setAss_imgurl(String ass_imgurl) {
            this.ass_imgurl = ass_imgurl;
        }

        public int getAss_proid() {
            return ass_proid;
        }

        public void setAss_proid(int ass_proid) {
            this.ass_proid = ass_proid;
        }
    }
}
