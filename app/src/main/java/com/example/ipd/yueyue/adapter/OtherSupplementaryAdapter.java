package com.example.ipd.yueyue.adapter;

import android.support.annotation.Nullable;
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

public class OtherSupplementaryAdapter extends BaseQuickAdapter<SelectOtherBean.DataBeanXXXXX.FartherBean, BaseViewHolder> {

    private List<SelectOtherBean.DataBeanXXXXX.FartherBean> data;
    private boolean isLook;

    public OtherSupplementaryAdapter(@Nullable List<SelectOtherBean.DataBeanXXXXX.FartherBean> data, boolean isLook) {
        super(R.layout.adapter_supplementary_item, data);
        this.data = data;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectOtherBean.DataBeanXXXXX.FartherBean item) {
        ImageView ivSupplementary = helper.getView(R.id.iv_household_registration);
        ImageView ivSupplementaryDel = helper.getView(R.id.iv_household_registration_del);
        helper.setText(R.id.tv_household_registration_down, item.getFar_type())
                .setVisible(R.id.tv_household_registration_down, true);
        helper.addOnClickListener(R.id.iv_household_registration)
                .addOnClickListener(R.id.iv_household_registration_del);
        Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getFar_imgurl()).apply(new RequestOptions()).into(ivSupplementary);
        ivSupplementaryDel.setVisibility(View.VISIBLE);
        if (isLook) {
            ivSupplementaryDel.setVisibility(View.GONE);
        }
    }


}
