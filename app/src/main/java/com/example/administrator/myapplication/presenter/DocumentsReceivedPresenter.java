package com.example.administrator.myapplication.presenter;

import android.content.Context;

import com.example.administrator.myapplication.bean.DocumentsReceivedBean;
import com.example.administrator.myapplication.bean.LockedFilesAddBean;
import com.example.administrator.myapplication.bean.ReceiveDownloadBean;
import com.example.administrator.myapplication.bean.ReceiveFollowBean;
import com.example.administrator.myapplication.bean.ReceiveForwardBean;
import com.example.administrator.myapplication.bean.SelectOtherBean;
import com.example.administrator.myapplication.contract.DocumentsReceivedContract;
import com.example.administrator.myapplication.model.DocumentsReceivedModel;
import com.example.administrator.myapplication.progress.ObserverResponseListener;
import com.example.administrator.myapplication.utils.ExceptionHandle;
import com.example.administrator.myapplication.utils.ToastUtil;

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
