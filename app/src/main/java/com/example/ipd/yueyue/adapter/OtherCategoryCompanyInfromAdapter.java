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

/**
 * 企业信息的 二级图片选择
 */
public class OtherCategoryCompanyInfromAdapter extends BaseQuickAdapter<SelectOtherBean.DataBeanXXXXX.LicenseBean.DataBeanX, BaseViewHolder> {
    private List<SelectOtherBean.DataBeanXXXXX.LicenseBean.DataBeanX> data;

    public OtherCategoryCompanyInfromAdapter(@Nullable List<SelectOtherBean.DataBeanXXXXX.LicenseBean.DataBeanX> data) {
        super(R.layout.adapter_household_registration_item, data);
        this.data = data;
    }
    private boolean isLook = false;
    public OtherCategoryCompanyInfromAdapter(@Nullable List<SelectOtherBean.DataBeanXXXXX.LicenseBean.DataBeanX> data, boolean isLook ) {
        super(R.layout.adapter_household_registration_item, data);
        this.data = data;
        this.isLook = isLook;
    }


    @Override
    protected void convert(BaseViewHolder helper,final  SelectOtherBean.DataBeanXXXXX.LicenseBean.DataBeanX item) {
        int position = helper.getAdapterPosition();
        //初始化数据
        ImageView imageView = helper.getView(R.id.iv_household_registration);
        ImageView imageDel = helper.getView(R.id.iv_household_registration_del);
        helper.getView(R.id.tv_household_registration_down).setVisibility(View.GONE);

        if (!TextUtils.isEmpty(item.getLic_imgurl())) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getLic_imgurl()).apply(new RequestOptions()).into(imageView);
            imageDel.setVisibility(View.VISIBLE);
        }else{
            imageView.setImageResource(R.drawable.bg_id);
            imageDel.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.iv_household_registration)
                .addOnClickListener(R.id.iv_household_registration_del);
        if (isLook){
            imageDel.setVisibility(View.GONE);
        }
    }

//    @Override
//    public int getItemCount() {
//        return data == null?1:data.size()+1;
//    }


}
