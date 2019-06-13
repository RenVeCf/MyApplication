package com.example.ipd.yueyue.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/23.
 */

public class GetProAdjustBean {


    public int code;
    public String msg;
    public List<DataBeanFirst> data;


    public static class DataBeanFirst {


        public String yi_type;
        public List<DataBeanSecond> data;



        public static class DataBeanSecond {

            public int yi_id;
            public int yi_userid;
            public String yi_type;
            public String yi_imgurl;
            public int yi_proid;


        }
    }
}
