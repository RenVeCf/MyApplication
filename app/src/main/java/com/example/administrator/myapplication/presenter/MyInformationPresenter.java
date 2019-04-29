package com.example.administrator.myapplication.presenter;

import android.content.Context;

import com.example.administrator.myapplication.bean.MyForwardsBean;
import com.example.administrator.myapplication.bean.MyInformationBean;
import com.example.administrator.myapplication.bean.MyOffBean;
import com.example.administrator.myapplication.contract.MyInformationContract;
import com.example.administrator.myapplication.model.MyInformationModel;
import com.example.administrator.myapplication.progress.ObserverResponseListener;
import com.example.administrator.myapplication.utils.ExceptionHandle;
import com.example.administrator.myapplication.utils.ToastUtil;

import java.util.TreeMap;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class MyInformationPresenter extends MyInformationContract.Presenter {

    private MyInformationModel model;
    private Context context;

    public MyInformationPresenter(Context context) {
        this.model = new MyInformationModel();
        this.context = context;
    }

    @Override
    public void getMyInformation(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.myInformation(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultMyInformation((MyInformationBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void getMyForwards(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.myForwards(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultMyForwards((MyForwardsBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void getMyOff(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.myOff(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultMyOff((MyOffBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }
}