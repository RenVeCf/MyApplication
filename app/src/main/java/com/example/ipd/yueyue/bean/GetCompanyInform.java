package com.example.ipd.yueyue.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2019/4/23.
 * 企业信息（营业执照）
 */

public class GetCompanyInform {


    

    public int code;
    public String msg;
    public DataBean data;


    public static class DataBean {


        public String lic_type;
        public StroBean stro;

        public static class StroBean {


            @SerializedName("1")
            public StroSecondBean _$1;
            @SerializedName("2")
            public StroSecondBean _$2;
            @SerializedName("3")
            public StroSecondBean _$3;
            @SerializedName("4")
            public StroSecondBean _$4;
            @SerializedName("5")
            public StroSecondBean _$5;



            public static class StroSecondBean {


                public int lic_stro;
                public List<StroThreeBean> data;


                public static class StroThreeBean {

                    public int lic_id;
                    public int lic_userid;
                    public String lic_type;
                    public String lic_imgurl;
                    public int lic_proid;
                    public String lic_stro;


                }
            }

        }
    }
}
