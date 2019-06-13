package com.example.ipd.yueyue.model;

import android.content.Context;

import com.example.ipd.yueyue.api.Api;
import com.example.ipd.yueyue.base.BaseModel;
import com.example.ipd.yueyue.progress.ObserverResponseListener;

import java.util.Map;
import java.util.TreeMap;

import io.reactivex.ObservableTransformer;
import okhttp3.RequestBody;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class HouseholdRegistrationBookModel<T> extends BaseModel {

    public void uploadImg(Context context, Map<String, RequestBody> map, boolean isDialog, boolean cancelable,
                          ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getUploadImg(map), observerListener, transformer, isDialog, cancelable);
    }

    public void modifyHouseholdRegistrationBookImgData(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                                       ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getModifyHouseholdRegistrationBookImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    //获取户口本
    public void modifyGetHouseholdRegistrationBookImgData(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                                          ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getGetHouseholdRegistrationBookImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    public void modifyEnterpriseImgData(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                        ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getModifyEnterpriseImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getEnterpriseImgData(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                     ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getEnterpriseImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    public void modifyCreditReportImgData(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                          ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getModifyCreditReportImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getCreditReportImgData(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                       ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getCreditReportImgData(map), observerListener, transformer, isDialog, cancelable);
    }
    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作
}
