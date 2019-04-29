package com.example.administrator.myapplication.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.LockedFilesBean;
import com.example.administrator.myapplication.utils.DateUtils;

import java.util.List;

public class LockedFilesAdapter extends BaseQuickAdapter<LockedFilesBean.DataBean, BaseViewHolder> {
    private List<LockedFilesBean.DataBean> data;
    private String handlingType;
    private String uploadTime;

    public LockedFilesAdapter(@Nullable List<LockedFilesBean.DataBean> data) {
        super(R.layout.adapter_locked_files_item, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, LockedFilesBean.DataBean item) {
        uploadTime = DateUtils.timesTwo(item.getPro_addtime());
        if (item.getPro_handle() == 0)
            handlingType = "机构";
        else
            handlingType = "个人";

        //水印状态
        if (item.getLoc_watermark() == 0)
            helper.setBackgroundRes(R.id.iv_locked_watermark, 0);
        else
            helper.setBackgroundRes(R.id.iv_locked_watermark, R.mipmap.watermark);

        helper.setText(R.id.tv_locked_files_name_of_borrower, item.getPro_name())
                .setText(R.id.tv_locked_files_id_card_num, item.getPro_card())
                .setText(R.id.tv_locked_files_handling_type, handlingType)
                .setText(R.id.tv_locked_files_loan_amount, item.getPro_amount() + "")
                .setText(R.id.tv_locked_files_upload_time, uploadTime);
        helper.addOnClickListener(R.id.bt_locked_files_select)
                .addOnClickListener(R.id.bt_locked_files_delete);
    }

    //  删除数据
    public void removeData(int position) {
        data.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
