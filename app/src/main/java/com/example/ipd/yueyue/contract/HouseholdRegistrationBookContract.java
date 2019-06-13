package com.example.ipd.yueyue.contract;

import com.example.ipd.yueyue.bean.GetCompanyInform;
import com.example.ipd.yueyue.base.BasePresenter;
import com.example.ipd.yueyue.base.BaseView;
import com.example.ipd.yueyue.bean.GetCreditReportBean;
import com.example.ipd.yueyue.bean.GetHouseholdRegistrationBookBean;
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
public interface HouseholdRegistrationBookContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultUploadImg(UploadImgBean data);

        void resultModifyHouseholdRegistrationBookImgData(String data);

        void resultGetHouseholdRegistrationBookImg(GetHouseholdRegistrationBookBean data);

        void resultModifyEnterpriseImgData(String data);

        void resultGetEnterpriseImg(GetCompanyInform data);

        void resultModifyCreditReportImgData(String data);

        void resultGetCreditReportImg(GetCreditReportBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void uploadImg(Map<String, RequestBody> map, boolean isDialog, boolean cancelable);

        public abstract void modifyHouseholdRegistrationBookImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void modifyGetHouseholdRegistrationBookImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void modifyEnterpriseImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getEnterpriseImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void modifyCreditReportImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getCreditReportImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}