package com.example.ipd.yueyue.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.bean.SelectOtherBean;
import com.example.ipd.yueyue.common.config.UrlConfig;
import com.example.ipd.yueyue.utils.ApplicationUtil;

import java.util.List;

public class OtherAssessmentReportAdapter extends BaseQuickAdapter<SelectOtherBean.DataBeanXXXXX.AssessBean, BaseViewHolder> {
    private List<SelectOtherBean.DataBeanXXXXX.AssessBean> data;

    public OtherAssessmentReportAdapter(@Nullable List<SelectOtherBean.DataBeanXXXXX.AssessBean> data) {
        super(R.layout.adapter_household_registration_item, data);
        this.data = data;
    }
    private boolean isLook =false;
 public OtherAssessmentReportAdapter(@Nullable List<SelectOtherBean.DataBeanXXXXX.AssessBean> data, boolean isLook) {
        super(R.layout.adapter_household_registration_item, data);
        this.data = data;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper,SelectOtherBean.DataBeanXXXXX.AssessBean item) {
        helper.addOnClickListener(R.id.iv_household_registration)
                .addOnClickListener(R.id.iv_household_registration_del);
        ImageView ivOne = helper.getView(R.id.iv_household_registration);
        ImageView ivOneDel = helper.getView(R.id.iv_household_registration_del);
        if (!TextUtils.isEmpty(item.getAss_imgurl())) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getAss_imgurl()).apply(new RequestOptions()).into(ivOne);
            ivOneDel.setVisibility(View.VISIBLE);
        }else {
            ivOne.setImageResource(R.drawable.bg_id);
            ivOneDel.setVisibility(View.GONE);
        }
        helper.getView(R.id.tv_household_registration_down).setVisibility(View.GONE);
        if (isLook){
            ivOneDel.setVisibility(View.GONE);
        }
    }


}
