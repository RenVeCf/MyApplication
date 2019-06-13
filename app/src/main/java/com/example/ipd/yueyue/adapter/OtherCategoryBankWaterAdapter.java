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
 * 银行流水的 二级图片选择
 */
public class OtherCategoryBankWaterAdapter extends BaseQuickAdapter<SelectOtherBean.DataBeanXXXXX.BankBean.DataBeanXX, BaseViewHolder> {
    private List<SelectOtherBean.DataBeanXXXXX.BankBean.DataBeanXX> data;
    private boolean isLook = false;

    public OtherCategoryBankWaterAdapter(@Nullable List<SelectOtherBean.DataBeanXXXXX.BankBean.DataBeanXX> data, boolean isLook) {
        super(R.layout.adapter_household_registration_item, data);
        this.data = data;
        this.isLook = isLook;
    }


    @Override
    protected void convert(BaseViewHolder helper,final  SelectOtherBean.DataBeanXXXXX.BankBean.DataBeanXX item) {
        int position = helper.getAdapterPosition();
        //初始化数据
        ImageView imageView = helper.getView(R.id.iv_household_registration);
        ImageView imageDel = helper.getView(R.id.iv_household_registration_del);
        helper.getView(R.id.tv_household_registration_down).setVisibility(View.GONE);

        if (!TextUtils.isEmpty(item.getBank_imgurl())) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getBank_imgurl()).apply(new RequestOptions()).into(imageView);
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

}
