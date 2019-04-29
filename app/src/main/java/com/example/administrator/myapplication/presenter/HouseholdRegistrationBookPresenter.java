package com.example.administrator.myapplication.presenter;

import android.content.Context;

import com.example.administrator.myapplication.bean.GetCompanyInform;
import com.example.administrator.myapplication.bean.GetEnterpriseBean;
import com.example.administrator.myapplication.bean.GetCreditReportBean;
import com.example.administrator.myapplication.bean.GetHouseholdRegistrationBookBean;
import com.example.administrator.myapplication.bean.UploadImgBean;
import com.example.administrator.myapplication.contract.HouseholdRegistrationBookContract;
import com.example.administrator.myapplication.model.HouseholdRegistrationBookModel;
import com.example.administrator.myapplication.progress.ObserverResponseListener;
import com.example.administrator.myapplication.utils.ExceptionHandle;
import com.example.administrator.myapplication.utils.ToastUtil;

import java.util.Map;
import java.util.TreeMap;

import okhttp3.RequestBody;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class HouseholdRegistrationBookPresenter extends HouseholdRegistrationBookContract.Presenter {

    private HouseholdRegistrationBookModel model;
    private Context context;

    public HouseholdRegistrationBookPresenter(Context context) {
        this.model = new HouseholdRegistrationBookModel();
        this.context = context;
    }

    @Override
    public void uploadImg(Map<String, RequestBody> map, boolean isDialog, boolean cancelable) {
        model.uploadImg(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultUploadImg((UploadImgBean) o);
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
    public void modifyHouseholdRegistrationBookImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.modifyHouseholdRegistrationBookImgData(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultModifyHouseholdRegistrationBookImgData((String) o);
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

    //获取户口本
    @Override
    public void modifyGetHouseholdRegistrationBookImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.modifyGetHouseholdRegistrationBookImgData(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultGetHouseholdRegistrationBookImg((GetHouseholdRegistrationBookBean) o);
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
    public void modifyEnterpriseImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.modifyEnterpriseImgData(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultModifyEnterpriseImgData((String) o);
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
    public void getEnterpriseImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getEnterpriseImgData(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultGetEnterpriseImg((GetCompanyInform) o);
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
    public void modifyCreditReportImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.modifyCreditReportImgData(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultModifyCreditReportImgData((String) o);
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
    public void getCreditReportImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getCreditReportImgData(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultGetCreditReportImg((GetCreditReportBean) o);
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