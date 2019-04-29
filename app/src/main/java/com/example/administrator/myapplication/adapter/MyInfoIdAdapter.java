package com.example.administrator.myapplication.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.SelectMyInfoBean;
import com.example.administrator.myapplication.utils.ApplicationUtil;

import java.util.List;

public class MyInfoIdAdapter extends BaseQuickAdapter<SelectMyInfoBean.DataBeanX.CardBean, BaseViewHolder> {

    private ImageView ivOne;
    private ImageView ivTwo;

    public MyInfoIdAdapter(@Nullable List<SelectMyInfoBean.DataBeanX.CardBean> data) {
        super(R.layout.adapter_id_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectMyInfoBean.DataBeanX.CardBean item) {
        ivOne = helper.getView(R.id.iv_id_one);
        ivTwo = helper.getView(R.id.iv_id_two);
        helper.addOnClickListener(R.id.iv_id_one)
                .addOnClickListener(R.id.iv_id_two)
                .setText(R.id.tv_my_identity_card, item.getCard_type());
        Glide.with(ApplicationUtil.getContext()).load(item.getCard_positive()).apply(new RequestOptions()).into(ivOne);
        Glide.with(ApplicationUtil.getContext()).load(item.getCard_negative()).apply(new RequestOptions()).into(ivTwo);
    }
}
