package com.example.administrator.myapplication.contract;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.base.BaseView;
import com.example.administrator.myapplication.bean.GetAssessmentImgBean;
import com.example.administrator.myapplication.bean.GetBankImgBean;
import com.example.administrator.myapplication.bean.GetCollateralImgBean;
import com.example.administrator.myapplication.bean.GetHouseImgBean;
import com.example.administrator.myapplication.bean.GetIdImgBean;
import com.example.administrator.myapplication.bean.GetMarryImgBean;
import com.example.administrator.myapplication.bean.UploadImgBean;

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
public interface IdContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultUploadImg(UploadImgBean data);

        void resultIdImgData(String data);

        void resultGetIdImg(GetIdImgBean data);

        void resultModifyMarryImgData(String data);

        void resultGetMarryImg(GetMarryImgBean data);

        void resultModifyHouseImgData(String data);

        void resultGetHouseImg(GetHouseImgBean data);

        void resultModifyCollateralImgData(String data);

        void resultGetCollateralImg(GetCollateralImgBean data);

        void resultModifyAssessmentImgData(String data);

        void resultGetAssessmentImg(GetAssessmentImgBean data);

        void resultModifyBankImgData(String data);

        void resultGetBankImg(GetBankImgBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void uploadImg(Map<String, RequestBody> map, boolean isDialog, boolean cancelable);

        public abstract void idImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getIdImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void modifyMarryImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getMarryImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void modifyHouseImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getHouseImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void modifyCollateralImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getCollateralImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void modifyAssessmentImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getAssessmentImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void modifyBankImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getBankImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

    }
}