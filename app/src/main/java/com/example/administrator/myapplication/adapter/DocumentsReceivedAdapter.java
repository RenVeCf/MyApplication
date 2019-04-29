package com.example.administrator.myapplication.adapter;

import android.support.annotation.Nullable;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.DocumentsReceivedBean;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.DateUtils;

import java.util.List;

public class DocumentsReceivedAdapter extends BaseQuickAdapter<DocumentsReceivedBean.DataBean, BaseViewHolder> {
    private String handlingType;
    private String uploadTime;
    private Button btDocumentsReceivedForward;
    private Button btDocumentsReceivedDownload;
    private Button btDocumentsReceivedLocking;
    private Button btDocumentsReceivedFollow;

    public DocumentsReceivedAdapter(@Nullable List<DocumentsReceivedBean.DataBean> data) {
        super(R.layout.adapter_documents_received_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DocumentsReceivedBean.DataBean item) {
        uploadTime = DateUtils.timesTwo(item.getPro_addtime());
        btDocumentsReceivedForward = helper.getView(R.id.bt_documents_received_forward);
        btDocumentsReceivedDownload = helper.getView(R.id.bt_documents_received_download);
        btDocumentsReceivedLocking = helper.getView(R.id.bt_documents_received_locking);
        btDocumentsReceivedFollow = helper.getView(R.id.bt_documents_received_follow);

        if (item.getPro_handle() == 0)
            handlingType = "机构";
        else
            handlingType = "个人";
        //水印状态
        if (item.getFor_watermark() == 0)
            helper.setBackgroundRes(R.id.iv_documents_received_watermark, 0);
        else
            helper.setBackgroundRes(R.id.iv_documents_received_watermark, R.mipmap.watermark);
        //转发状态
        if (item.getFor_send() == 0) {
            helper.setTextColor(R.id.bt_documents_received_forward, ApplicationUtil.getContext().getResources().getColor(R.color.tx_select_fragment))
                    .addOnClickListener(R.id.bt_documents_received_forward);
            btDocumentsReceivedForward.setEnabled(true);
        } else {
            btDocumentsReceivedForward.setEnabled(false);
            btDocumentsReceivedForward.setTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.tx_not_select_fragment));
        }
        //下载状态
        if (item.getFor_download() == 0) {
            helper.setTextColor(R.id.bt_documents_received_download, ApplicationUtil.getContext().getResources().getColor(R.color.tx_select_fragment))
                    .addOnClickListener(R.id.bt_documents_received_download);
            btDocumentsReceivedDownload.setEnabled(true);
        } else {
            btDocumentsReceivedDownload.setEnabled(false);
            btDocumentsReceivedDownload.setTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.tx_not_select_fragment));
        }
        //锁定状态
        if (item.getFor_locking() == 0) {
            helper.setTextColor(R.id.bt_documents_received_locking, ApplicationUtil.getContext().getResources().getColor(R.color.tx_select_fragment))
                    .addOnClickListener(R.id.bt_documents_received_locking);
            btDocumentsReceivedLocking.setEnabled(true);
        } else {
            btDocumentsReceivedLocking.setEnabled(false);
            btDocumentsReceivedLocking.setTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.tx_not_select_fragment));
        }
        //关注状态
        if (item.getFor_attention() == 0) {
            helper.setTextColor(R.id.bt_documents_received_follow, ApplicationUtil.getContext().getResources().getColor(R.color.tx_select_fragment))
                    .addOnClickListener(R.id.bt_documents_received_follow);
            btDocumentsReceivedFollow.setEnabled(true);
        } else {
            btDocumentsReceivedFollow.setEnabled(false);
            btDocumentsReceivedFollow.setTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.tx_not_select_fragment));
        }
        helper.setText(R.id.tv_documents_received_name_of_borrower, item.getPro_name())
                .setText(R.id.tv_documents_received_id_card_num, item.getPro_card())
                .setText(R.id.tv_documents_received_handling_type, handlingType)
                .setText(R.id.tv_documents_received_loan_amount, item.getPro_amount() + "")
                .setText(R.id.tv_documents_received_upload_time, uploadTime)
                .addOnClickListener(R.id.bt_documents_received_select);
    }
}
