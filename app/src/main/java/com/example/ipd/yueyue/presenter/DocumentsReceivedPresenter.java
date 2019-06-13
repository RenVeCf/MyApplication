package com.example.ipd.yueyue.presenter;

import android.content.Context;

import com.example.ipd.yueyue.bean.DocumentsReceivedBean;
import com.example.ipd.yueyue.bean.LockedFilesAddBean;
import com.example.ipd.yueyue.bean.ReceiveDownloadBean;
import com.example.ipd.yueyue.bean.ReceiveFollowBean;
import com.example.ipd.yueyue.bean.ReceiveForwardBean;
import com.example.ipd.yueyue.bean.SelectOtherBean;
import com.example.ipd.yueyue.contract.DocumentsReceivedContract;
import com.example.ipd.yueyue.model.DocumentsReceivedModel;
import com.example.ipd.yueyue.progress.ObserverResponseListener;
import com.example.ipd.yueyue.utils.ExceptionHandle;
import com.example.ipd.yueyue.utils.ToastUtil;

import java.util.TreeMap;

public class DocumentsReceivedPresenter extends DocumentsReceivedContract.Presenter {

    private DocumentsReceivedModel model;
    private Context context;

    public DocumentsReceivedPresenter(Context context) {
        this.model = new DocumentsReceivedModel();
        this.context = context;
    }

    @Override
    public void getDocumentsReceived(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.documentsReceived(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultDocumentsReceived((DocumentsReceivedBean) o);
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
    public void getReceiveForward(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.receiveForward(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultReceiveForward((ReceiveForwardBean) o);
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
    public void getReceiveDownload(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.receiveDownload(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultReceiveDownload((ReceiveDownloadBean) o);
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
    public void getLockedFilesAdd(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.lockedFilesAdd(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultLockedFilesAdd((LockedFilesAddBean) o);
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
    public void getReceiveFollow(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.receiveFollow(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultReceiveFollow((ReceiveFollowBean) o);
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
    public void getSelectOtherInfo(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.selectOtherInfo(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultSelectOtherInfo((SelectOtherBean) o);
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
