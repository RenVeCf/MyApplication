package com.example.ipd.yueyue.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.bean.BrowseRecordBean;
import com.example.ipd.yueyue.utils.DateUtils;

import java.util.List;

public class BrowseRecordAdapter extends BaseQuickAdapter<BrowseRecordBean.DataBean, BaseViewHolder> {

    private String browseRecordTime;
    private String broType;
    private String broType1;
    private TextView tvHostname;

    public BrowseRecordAdapter(@Nullable List<BrowseRecordBean.DataBean> data) {
        super(R.layout.adapter_browse_record_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BrowseRecordBean.DataBean item) {
        tvHostname = helper.getView(R.id.tv_browse_record_hostname);
        if (item.getBro_type() == 0) {
            broType = "浏览";
            broType1 = "了您的资料";
            tvHostname.setVisibility(View.GONE);
        } else {
            broType = "转发";
            broType1 = "给了";
            tvHostname.setVisibility(View.VISIBLE);
            tvHostname.setText(item.getHostname());
        }
        browseRecordTime = DateUtils.timesTwo(item.getBro_addtime() + "");
        helper.setText(R.id.tv_browse_record_name, item.getSponsorname())
                .setText(R.id.tv_browse_record_time, browseRecordTime)
                .setText(R.id.tv_browse_record_type, broType)
                .setText(R.id.tv_browse_record_type_1, broType1);
    }
}
