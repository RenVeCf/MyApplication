package com.example.administrator.myapplication.presenter;

import android.content.Context;

import com.example.administrator.myapplication.bean.BrowseRecordBean;
import com.example.administrator.myapplication.contract.BrowseRecordContract;
import com.example.administrator.myapplication.model.BrowseRecordModel;
import com.example.administrator.myapplication.progress.ObserverResponseListener;
import com.example.administrator.myapplication.utils.ExceptionHandle;
import com.example.administrator.myapplication.utils.ToastUtil;

import java.util.TreeMap;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class BrowseRecordPresenter extends BrowseRecordContract.Presenter {

    private BrowseRecordModel model;
    private Context context;

    public BrowseRecordPresenter(Context context) {
        this.model = new BrowseRecordModel();
        this.context = context;
    }

    @Override
    public void browseRecord(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.browseRecord(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultBrowseRecord((BrowseRecordBean) o);
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