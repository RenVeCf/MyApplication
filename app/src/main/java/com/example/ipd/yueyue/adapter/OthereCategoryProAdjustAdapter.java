package com.example.ipd.yueyue.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.bean.SelectOtherBean;
import com.example.ipd.yueyue.common.config.UrlConfig;
import com.example.ipd.yueyue.utils.ApplicationUtil;

import java.util.List;

/**
 * 产调的二级适配器
 */
public class OthereCategoryProAdjustAdapter extends BaseQuickAdapter<SelectOtherBean.DataBeanXXXXX.YieldBean.DataBeanXXX, BaseViewHolder> {
    private List<SelectOtherBean.DataBeanXXXXX.YieldBean.DataBeanXXX> data;

    public OthereCategoryProAdjustAdapter(@Nullable List<SelectOtherBean.DataBeanXXXXX.YieldBean.DataBeanXXX> data) {
        super(R.layout.adapter_household_registration_item, data);
        this.data = data;
    }
    private boolean isLook = false;
public OthereCategoryProAdjustAdapter(@Nullable List<SelectOtherBean.DataBeanXXXXX.YieldBean.DataBeanXXX> data, boolean isLook) {
        super(R.layout.adapter_household_registration_item, data);
        this.data = data;
        this.isLook = isLook;
    }


    @Override
    protected void convert(BaseViewHolder helper,final  SelectOtherBean.DataBeanXXXXX.YieldBean.DataBeanXXX item) {
        int position = helper.getAdapterPosition();
        //初始化数据
        ImageView ivHouseholdRegistration = helper.getView(R.id.iv_household_registration);
        ImageView ivHouseholdRegistrationDel = helper.getView(R.id.iv_household_registration_del);

        TextView tvHouseholdRegistrationDown = helper.getView(R.id.tv_household_registration_down);



        if (!TextUtils.isEmpty(item.getYi_imgurl())) {
            tvHouseholdRegistrationDown.setText("图"+(position+1));
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getYi_imgurl()).apply(new RequestOptions()).into(ivHouseholdRegistration);
            ivHouseholdRegistrationDel.setVisibility(View.VISIBLE);
        }else{
            tvHouseholdRegistrationDown.setText("请选择");
            ivHouseholdRegistration.setImageResource(R.drawable.bg_id);
            ivHouseholdRegistrationDel.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.iv_household_registration)
                .addOnClickListener(R.id.iv_household_registration_del);
        if (isLook){
            ivHouseholdRegistrationDel.setVisibility(View.GONE);
        }
    }



}
