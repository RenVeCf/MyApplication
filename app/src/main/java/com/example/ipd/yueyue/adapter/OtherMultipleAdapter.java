package com.example.ipd.yueyue.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.activity.BigImageActivity;
import com.example.ipd.yueyue.activity.HouseholdRegistrationBookActivity;
import com.example.ipd.yueyue.bean.SelectOtherBean;

import java.util.List;

public class OtherMultipleAdapter extends BaseQuickAdapter<SelectOtherBean.DataBeanXXXXX.AccountBean, BaseViewHolder> {
    private List<SelectOtherBean.DataBeanXXXXX.AccountBean> data;
    private Context context;

    public OtherMultipleAdapter(@Nullable Context context, List<SelectOtherBean.DataBeanXXXXX.AccountBean> data) {
        super(R.layout.adpter_test, data);
        this.data = data;
        this.context = context;
    }
    private boolean isLook = false;
    public OtherMultipleAdapter(@Nullable Context context, List<SelectOtherBean.DataBeanXXXXX.AccountBean> data, boolean isLook) {
        super(R.layout.adpter_test, data);
        this.data = data;
        this.context = context;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SelectOtherBean.DataBeanXXXXX.AccountBean item) {

        helper.addOnClickListener(R.id.tv_delete);

        helper.setText(R.id.tv_registered_residence, item.getAcc_type() + "户口本");
        RecyclerView rv = helper.getView(R.id.rv_registered_residence);

        final int parentposition = helper.getLayoutPosition();
        if (parentposition == 0) {
            helper.setVisible(R.id.tv_delete, false);
        } else {
            helper.setVisible(R.id.tv_delete, true);
        }

        //设置RecyclerView方向和是否反转
        GridLayoutManager NotUseList = new GridLayoutManager(context, 2);
        rv.setLayoutManager(NotUseList);
        rv.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        final OtherAccountCategoryAdapter categoryAdapter = new OtherAccountCategoryAdapter(item.getData(),isLook);
        rv.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_household_registration:
                        SelectOtherBean.DataBeanXXXXX.AccountBean.DataBean dataBean = item.getData().get(position);
                        if (!TextUtils.isEmpty(dataBean.getAcc_imgurl())) {
                            BigImageActivity.launch((Activity) mContext, dataBean.getAcc_imgurl());
                        } else {
                            ((HouseholdRegistrationBookActivity) context).SelectPhotoMultiple(parentposition, position);
                        }
                        break;
                    case R.id.iv_household_registration_del:
                        item.getData().remove(position);
                        categoryAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        if (isLook){
            helper.setVisible(R.id.tv_delete, false);
        }
    }



}
