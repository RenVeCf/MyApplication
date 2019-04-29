package com.example.administrator.myapplication.contract;

import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.base.BaseView;
import com.example.administrator.myapplication.bean.DocumentsReceivedBean;
import com.example.administrator.myapplication.bean.LockedFilesAddBean;
import com.example.administrator.myapplication.bean.ReceiveDownloadBean;
import com.example.administrator.myapplication.bean.ReceiveFollowBean;
import com.example.administrator.myapplication.bean.ReceiveForwardBean;
import com.example.administrator.myapplication.bean.SelectOtherBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

public interface DocumentsReceivedContract {
    interface View extends BaseView {
        //不同的Bean单独处理
        void resultDocumentsReceived(DocumentsReceivedBean data);

        void resultReceiveForward(ReceiveForwardBean data);

        void resultReceiveDownload(ReceiveDownloadBean data);

        void resultLockedFilesAdd(LockedFilesAddBean data);

        void resultReceiveFollow(ReceiveFollowBean data);

        void resultSelectOtherInfo(SelectOtherBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getDocumentsReceived(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getReceiveForward(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getReceiveDownload(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getLockedFilesAdd(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getReceiveFollow(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getSelectOtherInfo(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}
