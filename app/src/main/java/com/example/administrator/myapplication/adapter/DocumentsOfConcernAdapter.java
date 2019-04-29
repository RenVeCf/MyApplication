package com.example.administrator.myapplication.adapter;

import android.support.annotation.Nullable;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.DocumentsOfConcernBean;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.DateUtils;

import java.util.List;

public class DocumentsOfConcernAdapter extends BaseQuickAdapter<DocumentsOfConcernBean.DataBean, BaseViewHolder> {
    private String handlingType;
    private String uploadTime;
    private Button btDocumentsReceivedForward;
    private Button btDocumentsReceivedDownload;
    private Button btDocumentsReceivedLocking;
    private Button btDocumentsReceivedFollow;
    private List<DocumentsOfConcernBean.DataBean> data;

    public DocumentsOfConcernAdapter(@Nullable List<DocumentsOfConcernBean.DataBean> data) {
        super(R.layout.adapter_documents_of_concern_item, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, DocumentsOfConcernBean.DataBean item) {
        uploadTime = DateUtils.timesTwo(item.getPro_addtime());
        btDocumentsReceivedForward = helper.getView(R.id.bt_documents_of_concern_forward);
        btDocumentsReceivedDownload = helper.getView(R.id.bt_documents_of_concern_download);
        btDocumentsReceivedLocking = helper.getView(R.id.bt_documents_of_concern_locking);
        btDocumentsReceivedFollow = helper.getView(R.id.bt_documents_of_concern_no_follow);

        if (item.getPro_handle() == 0)
            handlingType = "机构";
        else
            handlingType = "个人";
        //转发状态
        if (item.getFor_send() == 0) {
            helper.setTextColor(R.id.bt_documents_of_concern_forward, ApplicationUtil.getContext().getResources().getColor(R.color.tx_select_fragment))
                    .addOnClickListener(R.id.bt_documents_of_concern_forward);
            btDocumentsReceivedForward.setEnabled(true);
        } else {
            btDocumentsReceivedForward.setEnabled(false);
            btDocumentsReceivedForward.setTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.tx_not_select_fragment));
        }
        //下载状态
        if (item.getFor_download() == 0) {
            helper.setTextColor(R.id.bt_documents_of_concern_download, ApplicationUtil.getContext().getResources().getColor(R.color.tx_select_fragment))
                    .addOnClickListener(R.id.bt_documents_of_concern_download);
            btDocumentsReceivedDownload.setEnabled(true);
        } else {
            btDocumentsReceivedDownload.setEnabled(false);
            btDocumentsReceivedDownload.setTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.tx_not_select_fragment));
        }
        //锁定状态
        if (item.getFor_locking() == 0) {
            helper.setTextColor(R.id.bt_documents_of_concern_locking, ApplicationUtil.getContext().getResources().getColor(R.color.tx_select_fragment))
                    .addOnClickListener(R.id.bt_documents_of_concern_locking);
            btDocumentsReceivedLocking.setEnabled(true);
        } else {
            btDocumentsReceivedLocking.setEnabled(false);
            btDocumentsReceivedLocking.setTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.tx_not_select_fragment));
        }
        //关注状态
        if (item.getFor_attention() == 1) {
            helper.setTextColor(R.id.bt_documents_of_concern_no_follow, ApplicationUtil.getContext().getResources().getColor(R.color.tx_select_fragment))
                    .addOnClickListener(R.id.bt_documents_of_concern_no_follow);
            btDocumentsReceivedFollow.setEnabled(true);
        } else {
            btDocumentsReceivedFollow.setEnabled(false);
            btDocumentsReceivedFollow.setTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.tx_not_select_fragment));
        }

        helper.setText(R.id.tv_documents_of_concern_name_of_borrower, item.getPro_name())
                .setText(R.id.tv_documents_of_concern_id_card_num, item.getPro_card())
                .setText(R.id.tv_documents_of_concern_handling_type, handlingType)
                .setText(R.id.tv_documents_of_concern_loan_amount, item.getPro_amount() + "")
                .setText(R.id.tv_documents_of_concern_upload_time, uploadTime);
        helper.addOnClickListener(R.id.bt_documents_of_concern_select);
    }

    //  删除数据
    public void removeData(int position) {
        data.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
