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

public class OtherPropertyOwnershipCertificateAdapter extends BaseQuickAdapter<SelectOtherBean.DataBeanXXXXX.EstateBean, BaseViewHolder> {
    private List<SelectOtherBean.DataBeanXXXXX.EstateBean> data;


    public OtherPropertyOwnershipCertificateAdapter(@Nullable List<SelectOtherBean.DataBeanXXXXX.EstateBean> data) {
        super(R.layout.adapter_property_ownership_certificate_item, data);
        this.data = data;
    }
    private boolean isLook = false;
    public OtherPropertyOwnershipCertificateAdapter(@Nullable List<SelectOtherBean.DataBeanXXXXX.EstateBean> data, boolean isLook) {
        super(R.layout.adapter_property_ownership_certificate_item, data);
        this.data = data;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SelectOtherBean.DataBeanXXXXX.EstateBean item) {
        helper.addOnClickListener(R.id.tv_delete)
                .addOnClickListener(R.id.iv_property_ownership_certificate_one)
                .addOnClickListener(R.id.iv_property_ownership_certificate_two)
                .addOnClickListener(R.id.iv_property_ownership_certificate_three)
                .addOnClickListener(R.id.iv_property_ownership_certificate_four)
                .addOnClickListener(R.id.iv_property_ownership_certificate_five)
                .addOnClickListener(R.id.iv_property_ownership_certificate_one_del)
                .addOnClickListener(R.id.iv_property_ownership_certificate_two_del)
                .addOnClickListener(R.id.iv_property_ownership_certificate_three_del)
                .addOnClickListener(R.id.iv_property_ownership_certificate_four_del)
                .addOnClickListener(R.id.iv_property_ownership_certificate_five_del);
        int position = helper.getLayoutPosition();
        helper.setText(R.id.tv_property_ownership_certificate,"房产证" + (position+1));
        if (position == 0) {
            helper.setVisible(R.id.tv_delete, false);
        }else{
            helper.setVisible(R.id.tv_delete, true);

        }
        ImageView ivOne = helper.getView(R.id.iv_property_ownership_certificate_one);
        ImageView ivTwo = helper.getView(R.id.iv_property_ownership_certificate_two);
        ImageView ivThree = helper.getView(R.id.iv_property_ownership_certificate_three);
        ImageView ivFour = helper.getView(R.id.iv_property_ownership_certificate_four);
        ImageView ivFive = helper.getView(R.id.iv_property_ownership_certificate_five);
        ImageView ivOneDel = helper.getView(R.id.iv_property_ownership_certificate_one_del);
        ImageView ivTwoDel = helper.getView(R.id.iv_property_ownership_certificate_two_del);
        ImageView ivThreeDel = helper.getView(R.id.iv_property_ownership_certificate_three_del);
        ImageView ivFourDel = helper.getView(R.id.iv_property_ownership_certificate_four_del);
        ImageView ivFiveDel = helper.getView(R.id.iv_property_ownership_certificate_five_del);
        if (item.getEst_page1() != null && !item.getEst_page1().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getEst_page1()).apply(new RequestOptions()).into(ivOne);
            ivOneDel.setVisibility(View.VISIBLE);
        }else {
            ivOne.setImageResource(isLook?R.drawable.shape_white:R.drawable.bg_id);
            ivOneDel.setVisibility(View.GONE);
        }
        if (item.getEst_page2() != null && !item.getEst_page2().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getEst_page2()).apply(new RequestOptions()).into(ivTwo);
            ivTwoDel.setVisibility(View.VISIBLE);
        }else {
            ivTwo.setImageResource(isLook?R.drawable.shape_white:R.drawable.bg_id);
            ivTwoDel.setVisibility(View.GONE);
        }
        if (item.getEst_page3() != null && !item.getEst_page3().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getEst_page3()).apply(new RequestOptions()).into(ivThree);
            ivThreeDel.setVisibility(View.VISIBLE);
        }else {
            ivThree.setImageResource(isLook?R.drawable.shape_white:R.drawable.bg_id);
            ivThreeDel.setVisibility(View.GONE);
        }
        if (item.getEst_page4() != null && !item.getEst_page4().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getEst_page4()).apply(new RequestOptions()).into(ivFour);
            ivFourDel.setVisibility(View.VISIBLE);
        }else {
            ivFour.setImageResource(isLook?R.drawable.shape_white:R.drawable.bg_id);
            ivFourDel.setVisibility(View.GONE);
        }
        if (item.getEst_page5() != null && !item.getEst_page5().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getEst_page5()).apply(new RequestOptions()).into(ivFive);
            ivFiveDel.setVisibility(View.VISIBLE);
        }else {
            ivFive.setImageResource(isLook?R.drawable.shape_white:R.drawable.bg_id);
            ivFiveDel.setVisibility(View.GONE);
        }
        ivOneDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setEst_page1("");
                notifyDataSetChanged();
            }
        });ivTwoDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setEst_page2("");
                notifyDataSetChanged();
            }
        });ivThreeDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setEst_page3("");
                notifyDataSetChanged();
            }
        });ivFourDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setEst_page4("");
                notifyDataSetChanged();
            }
        });ivFiveDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setEst_page5("");
                notifyDataSetChanged();
            }
        });
        if (isLook){
            ivOneDel.setVisibility(View.GONE);
            ivTwoDel.setVisibility(View.GONE);
            ivThreeDel.setVisibility(View.GONE);
            ivFourDel.setVisibility(View.GONE);
            ivFiveDel.setVisibility(View.GONE);
            helper.setVisible(R.id.tv_delete, false);
        }
    }



}
