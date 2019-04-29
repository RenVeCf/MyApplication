package com.example.administrator.myapplication.contract;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.base.BaseView;
import com.example.administrator.myapplication.bean.MyForwardsBean;
import com.example.administrator.myapplication.bean.MyInformationBean;
import com.example.administrator.myapplication.bean.MyOffBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface MyInformationContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultMyInformation(MyInformationBean data);

        void resultMyForwards(MyForwardsBean data);

        void resultMyOff(MyOffBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getMyInformation(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getMyForwards(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getMyOff(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}