package com.example.ipd.yueyue.contract;

import com.example.ipd.yueyue.base.BasePresenter;
import com.example.ipd.yueyue.base.BaseView;
import com.example.ipd.yueyue.bean.GetAddSupplementaryBean;
import com.example.ipd.yueyue.bean.UploadImgBean;

import java.util.Map;
import java.util.TreeMap;

import io.reactivex.ObservableTransformer;
import okhttp3.RequestBody;

/**
 * Description ：LoginContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface AddSupplementaryContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultUploadImg(UploadImgBean data);

        void resultModifyAddSupplementarImgData(String data);

        void resultGetAddSupplementarImg(GetAddSupplementaryBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void uploadImg(Map<String, RequestBody> map, boolean isDialog, boolean cancelable);

        public abstract void modifyAddSupplementarImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getAddSupplementarImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}