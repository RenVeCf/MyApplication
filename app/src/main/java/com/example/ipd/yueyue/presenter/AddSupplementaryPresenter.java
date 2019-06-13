package com.example.ipd.yueyue.presenter;

import android.content.Context;

import com.example.ipd.yueyue.bean.GetAddSupplementaryBean;
import com.example.ipd.yueyue.bean.UploadImgBean;
import com.example.ipd.yueyue.contract.AddSupplementaryContract;
import com.example.ipd.yueyue.model.AddSupplementaryModel;
import com.example.ipd.yueyue.progress.ObserverResponseListener;
import com.example.ipd.yueyue.utils.ExceptionHandle;
import com.example.ipd.yueyue.utils.ToastUtil;

import java.util.Map;
import java.util.TreeMap;

import okhttp3.RequestBody;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class AddSupplementaryPresenter extends AddSupplementaryContract.Presenter {

    private AddSupplementaryModel model;
    private Context context;

    public AddSupplementaryPresenter(Context context) {
        this.model = new AddSupplementaryModel();
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
    public void modifyAddSupplementarImgData(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.modifyAddSupplementaryImgData(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultModifyAddSupplementarImgData((String) o);
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
    public void getAddSupplementarImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getAddSupplementaryImgData(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultGetAddSupplementarImg((GetAddSupplementaryBean) o);
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