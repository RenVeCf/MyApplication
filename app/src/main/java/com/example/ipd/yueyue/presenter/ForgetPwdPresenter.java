package com.example.ipd.yueyue.presenter;

import android.content.Context;

import com.example.ipd.yueyue.bean.CaptchaBean;
import com.example.ipd.yueyue.bean.RegisterBean;
import com.example.ipd.yueyue.contract.ForgetPwdContract;
import com.example.ipd.yueyue.model.ForgetPwdModel;
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
public class ForgetPwdPresenter extends ForgetPwdContract.Presenter {

    private ForgetPwdModel model;
    private Context context;

    public ForgetPwdPresenter(Context context) {
        this.model = new ForgetPwdModel();
        this.context = context;
    }

    @Override
    public void captcha(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.captcha(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultCaptcha((CaptchaBean) o);
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
    public void forgetPwd(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.forgetPwd(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultForgetPwd((RegisterBean) o);
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