package com.example.administrator.myapplication.model;

import android.content.Context;

import com.example.administrator.myapplication.api.Api;
import com.example.administrator.myapplication.base.BaseModel;
import com.example.administrator.myapplication.progress.ObserverResponseListener;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

public class DocumentsOfConcernModel<T> extends BaseModel {

    public void getDocumentsOfConcern(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                      ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {
        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getDocumentsOfConcern(map), observerListener, transformer, isDialog, cancelable);
    }

    public void receiveForward(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                               ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getReceiveForward(map), observerListener, transformer, isDialog, cancelable);
    }

    public void receiveDownload(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getReceiveDownload(map), observerListener, transformer, isDialog, cancelable);
    }

    public void lockedFilesAdd(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                               ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getLockedFilesAdd(map), observerListener, transformer, isDialog, cancelable);
    }

    public void receiveFollow(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                              ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getReceiveFollow(map), observerListener, transformer, isDialog, cancelable);
    }
    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作
}
