package com.example.ipd.yueyue.presenter;

import android.content.Context;

import com.example.ipd.yueyue.bean.BriefSummaryBean;
import com.example.ipd.yueyue.contract.BriefSummaryContract;
import com.example.ipd.yueyue.model.BriefSummaryModel;
import com.example.ipd.yueyue.progress.ObserverResponseListener;
import com.example.ipd.yueyue.utils.ExceptionHandle;
import com.example.ipd.yueyue.utils.ToastUtil;

import java.util.TreeMap;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class BriefSummaryPresenter extends BriefSummaryContract.Presenter {

    private BriefSummaryModel model;
    private Context context;

    public BriefSummaryPresenter(Context context) {
        this.model = new BriefSummaryModel();
        this.context = context;
    }

    @Override
    public void getBriefSummary(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getBriefSummary(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultBriefSummary((BriefSummaryBean) o);
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