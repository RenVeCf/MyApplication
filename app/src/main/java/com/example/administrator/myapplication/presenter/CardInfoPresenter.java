package com.example.administrator.myapplication.presenter;

import android.content.Context;

import com.example.administrator.myapplication.bean.CardInfoBean;
import com.example.administrator.myapplication.contract.CardInfoContract;
import com.example.administrator.myapplication.model.CardInfoModel;
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
public class CardInfoPresenter extends CardInfoContract.Presenter {

    private CardInfoModel model;
    private Context context;

    public CardInfoPresenter(Context context) {
        this.model = new CardInfoModel();
        this.context = context;
    }

    @Override
    public void cardInfo(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.cardInfo(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultCardInfo((CardInfoBean) o);
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