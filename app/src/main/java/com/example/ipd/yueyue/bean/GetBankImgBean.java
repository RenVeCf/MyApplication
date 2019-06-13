package com.example.ipd.yueyue.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/24.
 */

public class GetBankImgBean {

    /**
     * code : 200
     * msg : 获取成功
     * data : [{"ass_id":27,"ass_userid":74,"ass_type":"\\u8bc4\\u4f30\\u62a5\\u544a","ass_imgurl":"/pic/20190424/8430b0b7c3025c5ae15b046070fb721b.jpeg","ass_proid":47},{"ass_id":28,"ass_userid":74,"ass_type":"\\u8bc4\\u4f30\\u62a5\\u544a","ass_imgurl":"/pic/20190424/458c72245ed8a104e54a9834b7da5c63.jpeg","ass_proid":47},{"ass_id":29,"ass_userid":74,"ass_type":"\\u8bc4\\u4f30\\u62a5\\u544a","ass_imgurl":"/pic/20190424/13d80dcf42e0c58887da440e21ec743c.jpeg","ass_proid":47},{"ass_id":30,"ass_userid":74,"ass_type":"\\u8bc4\\u4f30\\u62a5\\u544a","ass_imgurl":"/pic/20190424/6d8b12d29ca003403640dd0d0ebea536.jpeg","ass_proid":47}]
     */

    public int code;
    public String msg;
    public List<DataBean> data;



    public static class DataBean {

        public String bank_type;
        public List<DataBeanSecond> data;



        public static class DataBeanSecond {


            public int bank_id;
            public int bank_userid;
            public String bank_type;
            public String bank_imgurl;
            public int bank_proid;
            public String title;


        }


    }
}
