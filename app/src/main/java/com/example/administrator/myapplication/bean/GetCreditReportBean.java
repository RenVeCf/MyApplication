package com.example.administrator.myapplication.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/24.
 */

public class GetCreditReportBean {


    public int code;
    public String msg;
    public List<DataBeanX> data;



    public static class DataBeanX {


        public String cre_type;
        public List<DataBean> data;


        public static class DataBean {


            public int cre_id;
            public int cre_userid;
            public String cre_type;
            public String cre_imgurl;
            public int cre_proid;
   }
    }
}
