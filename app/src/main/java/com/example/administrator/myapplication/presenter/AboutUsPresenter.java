package com.example.administrator.myapplication.presenter;

import android.content.Context;

import com.example.administrator.myapplication.bean.CaptchaBean;
import com.example.administrator.myapplication.bean.TextBean;
import com.example.administrator.myapplication.contract.AboutUsContract;
import com.example.administrator.myapplication.contract.RegisterContract;
import com.example.administrator.myapplication.model.AboutUsModel;
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
public class AboutUsPresenter extends AboutUsContract.Presenter {

    private AboutUsModel model;
    private Context context;

    public AboutUsPresenter(Context context) {
        this.model = new AboutUsModel();
        this.context = context;
    }

    @Override
    public void aboutUs(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.aboutUs(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultAboutUs((TextBean) o);
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