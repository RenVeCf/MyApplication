package com.example.administrator.myapplication.contract;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.base.BaseView;
import com.example.administrator.myapplication.bean.GetAddSupplementaryBean;
import com.example.administrator.myapplication.bean.GetAssessmentImgBean;
import com.example.administrator.myapplication.bean.GetBankImgBean;
import com.example.administrator.myapplication.bean.GetCollateralImgBean;
import com.example.administrator.myapplication.bean.GetCompanyInform;
import com.example.administrator.myapplication.bean.GetCreditReportBean;
import com.example.administrator.myapplication.bean.GetHouseImgBean;
import com.example.administrator.myapplication.bean.GetHouseholdRegistrationBookBean;
import com.example.administrator.myapplication.bean.GetIdImgBean;
import com.example.administrator.myapplication.bean.GetMarryImgBean;

import java.util.Map;
import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：LoginContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface LookDataContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultIdCard(GetIdImgBean data);
        void resultMarryCardImage(GetMarryImgBean data);
        void resultAccountBookImg(GetHouseholdRegistrationBookBean data);
        void resultDeedImg(GetHouseImgBean data);
        void resultPawnImg(GetCollateralImgBean data);
        void resultCompanyInformationImg(GetCompanyInform data);
        void resultCreditReportImg(GetCreditReportBean data);
        void resultBankWaterImg(GetBankImgBean data);
        void resultEvaluationReportImg(GetAssessmentImgBean data);
        void resultProductionAdjustmentImg(String data);
        void resultSupplementaryMaterialImg(GetAddSupplementaryBean data);
        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        //身份证
        public abstract void getIdCardImage(Map<String, String> map, boolean isDialog, boolean cancelable);
        //结婚证
        public abstract void getMarryCardImage(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
        //户口本
        public abstract void getAccountBookImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
        //房产证
        public abstract void getDeedImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
        //抵押物
        public abstract void getPawnImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
        //企业信息
        public abstract void getCompanyInformationImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
        //信用报告
        public abstract void getCreditReportImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
        //银行流水
        public abstract void getBankWaterImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
        //评估报告
        public abstract void getEvaluationReportImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
        //产调
        public abstract void getProductionAdjustmentImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
        //补充材料
        public abstract void getSupplementaryMaterialImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

    }
}