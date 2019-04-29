package com.example.administrator.myapplication.common.config;

/**
 * Description ：公共配置类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface IConstants {
    /**
     * 包名
     */
    String PACKAGE_NAME = "com.example.administrator.myapplication";

    /**
     * SharedPreferences
     * 共享参数
     */
    Boolean FIRST_APP = true; //第一次进应用
    Boolean IS_LOGIN = false; //已经登录
    String USER_TOKEN = "token"; //token
    String USER_ID = "user_id"; //用户标识
    String NAME = "name"; //用户真实姓名
    String PHONE = "phone"; //用户手机号码
    String AVATAR = "avatar"; //头像
    String AGENCY = "agency"; //服务机构
    String POSITION = "position"; //职务

    /**
     * requestCode
     * 请求码
     */
    //相册请求码
    int ALBUM_REQUEST_CODE = 1;
    //相机请求码
    int CAMERA_REQUEST_CODE = 2;
    int REQUEST_CODE_90 = 90;
    int REQUEST_CODE_91 = 91;
    int REQUEST_CODE_92 = 92;
    int REQUEST_CODE_93 = 93;
    int REQUEST_CODE_94 = 94;
    int REQUEST_CODE_95 = 95;
    //服务机构
    int REQUEST_CODE_96 = 96;
    //职位
    int REQUEST_CODE_97 = 97;
    int REQUEST_CODE_98 = 98;
    int REQUEST_CODE_99 = 99;
    int REQUEST_CODE_100 = 100;
    int REQUEST_CODE_101 = 101;
    int REQUEST_CODE_102 = 102;
    int REQUEST_CODE_103 = 103;
    int REQUEST_CODE_104 = 104;
    int REQUEST_CODE_105 = 105;
    int REQUEST_CODE_106 = 106;
    int REQUEST_CODE_107 = 107;
    int REQUEST_CODE_108 = 108;
    int REQUEST_CODE_109 = 109;
    int REQUEST_CODE_110 = 110;
    int REQUEST_CODE_111 = 111;
    int REQUEST_CODE_112 = 112;
    int REQUEST_CODE_113 = 113;
    int REQUEST_CODE_114 = 114;
    int REQUEST_CODE_115 = 115;

    /**
     * resultCode
     * 返回码
     */
    int RESULT_CODE = 0;
}
