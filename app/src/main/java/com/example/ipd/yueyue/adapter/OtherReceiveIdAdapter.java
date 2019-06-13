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
 *
 */
public class OtherReceiveIdAdapter extends BaseQuickAdapter<SelectOtherBean.DataBeanXXXXX.CardBean, BaseViewHolder> {
    private List<SelectOtherBean.DataBeanXXXXX.CardBean> data;

    //编辑
    public OtherReceiveIdAdapter(@Nullable List<SelectOtherBean.DataBeanXXXXX.CardBean> data) {
        super(R.layout.adapter_id_item, data);
        this.data = data;
    }

    //编辑
    public boolean isLook = false;
    public OtherReceiveIdAdapter(@Nullable List<SelectOtherBean.DataBeanXXXXX.CardBean> data, boolean isLook) {
        super(R.layout.adapter_id_item, data);
        this.data = data;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SelectOtherBean.DataBeanXXXXX.CardBean item) {
        final ImageView  ivOne = helper.getView(R.id.iv_id_one);
        final ImageView ivTwo = helper.getView(R.id.iv_id_two);
        final ImageView ivOneDel = helper.getView(R.id.iv_id_one_del);
        final ImageView  ivTwoDel = helper.getView(R.id.iv_id_two_del);

        if (!TextUtils.isEmpty(item.getCard_positive())) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getCard_positive()).apply(new RequestOptions()).into(ivOne);
            ivOneDel.setVisibility(View.VISIBLE);
        }else {
            ivOneDel.setVisibility(View.GONE);
            ivOne.setImageResource(R.drawable.bg_id);
        }
        if (!TextUtils.isEmpty(item.getCard_negative())) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getCard_negative()).apply(new RequestOptions()).into(ivTwo);
            ivTwoDel.setVisibility(View.VISIBLE);
        }else {
            ivTwoDel.setVisibility(View.GONE);
            ivTwo.setImageResource(R.drawable.bg_id);
        }

        helper.addOnClickListener(R.id.tv_delete)
                .addOnClickListener(R.id.iv_id_one)
                .addOnClickListener(R.id.iv_id_two)
                .addOnClickListener(R.id.iv_id_one_del)
                .addOnClickListener(R.id.iv_id_two_del);

        ivOneDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setCard_positive("");
                notifyDataSetChanged();
            }
        });

        ivTwoDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setCard_negative("");
                notifyDataSetChanged();
            }
        });
        helper.setText(R.id.tv_my_identity_card,(item.getCard_type())+"身份证");
        int position = helper.getLayoutPosition();
        if (position == 0) {
            helper.setVisible(R.id.tv_delete, false);
        } else {
            helper.setVisible(R.id.tv_delete, true);
        }
        if (isLook){
            ivOneDel.setVisibility(View.GONE);
            ivTwoDel.setVisibility(View.GONE);
            helper.setVisible(R.id.tv_delete, false);
        }


    }



}
