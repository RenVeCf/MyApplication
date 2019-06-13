package com.example.ipd.yueyue.contract;

import com.example.ipd.yueyue.base.BasePresenter;
import com.example.ipd.yueyue.base.BaseView;
import com.example.ipd.yueyue.bean.DocumentsOfConcernBean;
import com.example.ipd.yueyue.bean.LockedFilesAddBean;
import com.example.ipd.yueyue.bean.ReceiveDownloadBean;
import com.example.ipd.yueyue.bean.ReceiveFollowBean;
import com.example.ipd.yueyue.bean.ReceiveForwardBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

public interface DocumentsOfConcernContract {
    interface View extends BaseView {
        //不同的Bean单独处理
        void resultDocumentsOfConcern(DocumentsOfConcernBean data);

        void resultReceiveForward(ReceiveForwardBean data);

        void resultReceiveDownload(ReceiveDownloadBean data);

        void resultLockedFilesAdd(LockedFilesAddBean data);

        void resultReceiveFollow(ReceiveFollowBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<DocumentsOfConcernContract.View> {
        public abstract void getDocumentsOfConcern(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getReceiveForward(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getReceiveDownload(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getLockedFilesAdd(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getReceiveFollow(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}
