package com.example.administrator.myapplication.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.MyInformationBean;
import com.example.administrator.myapplication.utils.DateUtils;

import java.util.List;

public class MyInformationAdapter extends BaseQuickAdapter<MyInformationBean.DataBean, BaseViewHolder> {
    private String handlingType;
    private String uploadTime;
    private String off = "";

    public MyInformationAdapter(@Nullable List<MyInformationBean.DataBean> data) {
        super(R.layout.adapter_my_information_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyInformationBean.DataBean item) {
        uploadTime = DateUtils.timesTwo(item.getPro_addtime());
        if (item.getPro_handle() == 0)
            handlingType = "机构";
        else
            handlingType = "个人";
        if (item.getPro_del() == 0)
            off = "关闭";
        else
            off = "开启";
        helper.addOnClickListener(R.id.bt_my_information_edit)
                .addOnClickListener(R.id.bt_my_information_forward)
                .addOnClickListener(R.id.bt_my_information_off)
                .addOnClickListener(R.id.bt_my_information_browse_record)
                .setText(R.id.tv_my_information_name_of_borrower, item.getPro_name())
                .setText(R.id.tv_my_information_id_card_num, item.getPro_card())
                .setText(R.id.tv_my_information_handling_type, handlingType)
                .setText(R.id.tv_my_information_loan_amount, item.getPro_amount() + "")
                .setText(R.id.tv_my_information_upload_time, uploadTime)
                .setText(R.id.bt_my_information_off, off);
    }
}
