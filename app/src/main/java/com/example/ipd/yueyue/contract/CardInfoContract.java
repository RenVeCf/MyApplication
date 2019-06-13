package com.example.ipd.yueyue.contract;

import com.example.ipd.yueyue.base.BasePresenter;
import com.example.ipd.yueyue.base.BaseView;
import com.example.ipd.yueyue.bean.CardInfoBean;

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