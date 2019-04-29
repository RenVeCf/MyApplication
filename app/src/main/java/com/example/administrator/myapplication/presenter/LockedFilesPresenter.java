package com.example.administrator.myapplication.presenter;

import android.content.Context;

import com.example.administrator.myapplication.bean.LockedFilesBean;
import com.example.administrator.myapplication.bean.LockedFilesDelBean;
import com.example.administrator.myapplication.bean.LockedFilesSelectBean;
import com.example.administrator.myapplication.contract.LockedFilesContract;
import com.example.administrator.myapplication.model.LockedFilesModel;
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
public class LockedFilesPresenter extends LockedFilesContract.Presenter {

    private LockedFilesModel model;
    private Context context;

    public LockedFilesPresenter(Context context) {
        this.model = new LockedFilesModel();
        this.context = context;
    }

    @Override
    public void getLockedFiles(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.lockedFiles(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultLockedFiles((LockedFilesBean) o);
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
    public void getLockedFilesDel(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.lockedFilesDel(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultLockedFilesDel((LockedFilesDelBean) o);
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
    public void getLockedFilesSelect(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.lockedFilesSelect(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultLockedFilesSelect((LockedFilesSelectBean) o);
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