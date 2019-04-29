package com.example.administrator.myapplication.api;

import com.example.administrator.myapplication.base.BaseApi;

/**
 * Description ：处理请求链接的地方
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/27.
 */

public class Api {
    //域名
    private String baseUrl = "http://121.199.8.244:2837/"; //测试

    private volatile static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    new Api();
                }
            }
        }
        return apiService;
    }

    private Api() {
        BaseApi baseApi = new BaseApi();
        apiService = baseApi.getRetrofit(baseUrl).create(ApiService.class);
    }
}
