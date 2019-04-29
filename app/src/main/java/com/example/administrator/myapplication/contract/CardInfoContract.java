package com.example.administrator.myapplication.contract;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.base.BaseView;
import com.example.administrator.myapplication.bean.CaptchaBean;
import com.example.administrator.myapplication.bean.CardInfoBean;
import com.example.administrator.myapplication.bean.RegisterBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：LoginContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface CardInfoContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultCardInfo(CardInfoBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void cardInfo(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}