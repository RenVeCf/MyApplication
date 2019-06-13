package com.example.ipd.yueyue.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.activity.BigImageActivity;
import com.example.ipd.yueyue.activity.CreditReportActivity;
import com.example.ipd.yueyue.bean.SelectOtherBean;

import java.util.List;

public class OtherCreditReportAdapter extends BaseQuickAdapter<SelectOtherBean.DataBeanXXXXX.CreditBean, BaseViewHolder> {
    private List<SelectOtherBean.DataBeanXXXXX.CreditBean> data;
    private Context context;

    public OtherCreditReportAdapter(@Nullable Context context, List<SelectOtherBean.DataBeanXXXXX.CreditBean> data) {
        super(R.layout.adpter_test, data);
        this.data = data;
        this.context = context;
    }
    private boolean isLook = false;
    public OtherCreditReportAdapter(@Nullable Context context, List<SelectOtherBean.DataBeanXXXXX.CreditBean> data, boolean isLook) {
        super(R.layout.adpter_test, data);
        this.data = data;
        this.context = context;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SelectOtherBean.DataBeanXXXXX.CreditBean item) {
        helper.addOnClickListener(R.id.tv_delete);
        TextView tvMyIdentityCard = helper.getView(R.id.tv_registered_residence);
        RecyclerView rv = helper.getView(R.id.rv_registered_residence);
        final int parentPosition = helper.getLayoutPosition();
        helper.setVisible(R.id.tv_delete, false);
        tvMyIdentityCard.setText(isLook?item.getCre_type():getTitle(parentPosition));
        //设置RecyclerView方向和是否反转
        GridLayoutManager NotUseList = new GridLayoutManager(context, 2);
        rv.setLayoutManager(NotUseList);
        rv.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        final OtherCategoryCreditReportAdapter categoryAdapter = new OtherCategoryCreditReportAdapter(item.getData(),isLook);
        rv.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_household_registration:
                        String cre_imgurl = item.getData().get(position).getCre_imgurl();
                        if (TextUtils.isEmpty(cre_imgurl)){
                            if (context instanceof CreditReportActivity) {
                                ((CreditReportActivity) context).SelectPhotoEnterprise(parentPosition, position);
                            }
                        }else {
                            BigImageActivity.launch((Activity) context,cre_imgurl);
                        }

                        break;
                    case R.id.iv_household_registration_del:
                        break;
                }
            }
        });

    }

    private String getTitle(int position){
        String s = "";
        switch (position) {
            case 0:
                s = "主贷人信用报告";
                break;
            case 1:
                s = "配偶信用报告";
                break;
            case 2:
                s = "其他权利人信用报告";
                break;
            case 3:
                s = "股东信用报告";
                break;

        }
        return s;
    }

}
