package com.example.administrator.myapplication.contract;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.base.BaseView;
import com.example.administrator.myapplication.bean.LockedFilesBean;
import com.example.administrator.myapplication.bean.LockedFilesDelBean;
import com.example.administrator.myapplication.bean.LockedFilesSelectBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface LockedFilesContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultLockedFiles(LockedFilesBean data);

        void resultLockedFilesDel(LockedFilesDelBean data);

        void resultLockedFilesSelect(LockedFilesSelectBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getLockedFiles(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getLockedFilesDel(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getLockedFilesSelect(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}