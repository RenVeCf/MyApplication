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
public class IdModel<T> extends BaseModel {

    public void uploadImg(Context context, Map<String, RequestBody> map, boolean isDialog, boolean cancelable,
                          ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getUploadImg(map), observerListener, transformer, isDialog, cancelable);
    }

    public void idImgData(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                          ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getModifyIdImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    //身份证-获取
    public void getIdImg(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                          ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getIdImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    //身份证-修改
    public void modifyMarryImgData(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                          ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getModifyMarryImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getMarryImg(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                         ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getMarryImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    public void modifyHouseImgData(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                          ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getModifyHouseImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getHouseImg(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                            ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getHouseImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    public void modifyCollateralImgData(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                   ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getModifyCollateralImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getCollateralImg(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                            ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getCollateralImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    public void modifyAssessmentImgData(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                   ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getModifyAssessmentImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getAssessmentImg(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                 ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getAssessmentImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    public void modifyBankImgData(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                        ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getModifyBankImgData(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getBankImg(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                 ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getBankImgData(map), observerListener, transformer, isDialog, cancelable);
    }
    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作
}
