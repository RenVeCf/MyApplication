package com.example.ipd.yueyue.presenter;

import android.content.Context;

import com.example.ipd.yueyue.bean.MyForwardsBean;
import com.example.ipd.yueyue.bean.MyInformationBean;
import com.example.ipd.yueyue.bean.MyOffBean;
import com.example.ipd.yueyue.contract.MyInformationContract;
import com.example.ipd.yueyue.model.MyInformationModel;
import com.example.ipd.yueyue.progress.ObserverResponseListener;
import com.example.ipd.yueyue.utils.ExceptionHandle;
import com.example.ipd.yueyue.utils.ToastUtil;

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