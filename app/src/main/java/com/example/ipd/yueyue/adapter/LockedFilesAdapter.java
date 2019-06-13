package com.example.ipd.yueyue.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.bean.LockedFilesBean;
import com.example.ipd.yueyue.utils.DateUtils;
import com.example.ipd.yueyue.utils.NumberUtils;

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
                .setText(R.id.tv_locked_files_id_card_num, item.getPro_card().substring(0, item.getPro_card().length() - 6) + "******")
                .setText(R.id.tv_locked_files_handling_type, handlingType)
                .setText(R.id.tv_locked_files_loan_amount, NumberUtils.formatOneDecimal((float) item.getPro_amount() / 10000) + "万元")
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
