package com.example.administrator.myapplication.contract;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.base.BaseView;
import com.example.administrator.myapplication.bean.GetMyInfoBean;
import com.example.administrator.myapplication.bean.UploadDataBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：LoginContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface UploadDataContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultUploadData(UploadDataBean data);

        void resultModifyData(UploadDataBean data);

        void resultGetMyInfo(GetMyInfoBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void uploadData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void modifyData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getMyInfo(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}