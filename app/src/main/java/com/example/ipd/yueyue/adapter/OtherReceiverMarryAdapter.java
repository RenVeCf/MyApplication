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
import com.example.ipd.yueyue.utils.StringUtils;

import java.util.List;

public class OtherReceiverMarryAdapter extends BaseQuickAdapter<SelectOtherBean.DataBeanXXXXX.MarryBean, BaseViewHolder> {
    private List<SelectOtherBean.DataBeanXXXXX.MarryBean> data;

    public OtherReceiverMarryAdapter(@Nullable List<SelectOtherBean.DataBeanXXXXX.MarryBean> data) {
        super(R.layout.adapter_marriage_certificate_item, data);
        this.data = data;
    }

    private boolean isLook= false;
    public OtherReceiverMarryAdapter(@Nullable List<SelectOtherBean.DataBeanXXXXX.MarryBean> data, boolean isLook) {
        super(R.layout.adapter_marriage_certificate_item, data);
        this.data = data;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper,final SelectOtherBean.DataBeanXXXXX.MarryBean item) {
        helper.addOnClickListener(R.id.tv_delete)
                .addOnClickListener(R.id.iv_marriage_certificate_one)
                .addOnClickListener(R.id.iv_marriage_certificate_two)
                .addOnClickListener(R.id.iv_marriage_certificate_three)
                .addOnClickListener(R.id.iv_marriage_certificate_one_del)
                .addOnClickListener(R.id.iv_marriage_certificate_two_del)
                .addOnClickListener(R.id.iv_marriage_certificate_three_del);
        helper.setText(R.id.tv_my_marriage_certificate_card,StringUtils.encodingGb(item.getMar_type())+"结婚证");
        int position = helper.getLayoutPosition();
        if (position == 0) {
            helper.setVisible(R.id.tv_delete, false);
        } else {
            helper.setVisible(R.id.tv_delete, true);
        }
        ImageView ivOne = helper.getView(R.id.iv_marriage_certificate_one);
        ImageView ivTwo = helper.getView(R.id.iv_marriage_certificate_two);
        ImageView ivThree = helper.getView(R.id.iv_marriage_certificate_three);
        ImageView ivOneDel = helper.getView(R.id.iv_marriage_certificate_one_del);
        ImageView ivTwoDel = helper.getView(R.id.iv_marriage_certificate_two_del);
        ImageView ivThreeDel = helper.getView(R.id.iv_marriage_certificate_three_del);

        if (!TextUtils.isEmpty(item.getMar_page1())) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getMar_page1()).apply(new RequestOptions()).into(ivOne);
            ivOneDel.setVisibility(View.VISIBLE);
        }else{
            ivOne.setImageResource(R.drawable.bg_id);
            ivOneDel.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(item.getMar_page2())) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getMar_page2()).apply(new RequestOptions()).into(ivTwo);
            ivTwoDel.setVisibility(View.VISIBLE);
        }else{
            ivTwo.setImageResource(R.drawable.bg_id);
            ivTwoDel.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(item.getMar_page3())) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getMar_page3()).apply(new RequestOptions()).into(ivThree);
            ivThreeDel.setVisibility(View.VISIBLE);
        }else{
            ivThree.setImageResource(R.drawable.bg_id);
            ivThreeDel.setVisibility(View.GONE);
        }

        ivOneDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setMar_page1("");
                notifyDataSetChanged();
            }
        });ivTwoDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setMar_page2("");
                notifyDataSetChanged();
            }
        });ivThreeDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setMar_page3("");
                notifyDataSetChanged();
            }
        });

        if (isLook){
            ivOneDel.setVisibility(View.GONE);
            ivTwoDel.setVisibility(View.GONE);
            ivThreeDel.setVisibility(View.GONE);
            helper.setVisible(R.id.tv_delete, false);

        }


    }


}
