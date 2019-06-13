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
import com.example.ipd.yueyue.activity.BankRunningWaterActivity;
import com.example.ipd.yueyue.activity.BigImageActivity;
import com.example.ipd.yueyue.bean.SelectOtherBean;

import java.util.List;

public class OtherBankAdapter extends BaseQuickAdapter<SelectOtherBean.DataBeanXXXXX.BankBean, BaseViewHolder> {
    private List<SelectOtherBean.DataBeanXXXXX.BankBean> data;
    private Context context;

    public OtherBankAdapter(@Nullable Context context, @Nullable List<SelectOtherBean.DataBeanXXXXX.BankBean> data) {
        super(R.layout.adpter_test, data);
        this.context = context;
        this.data = data;
    }
    private boolean isLook = false;
public OtherBankAdapter(@Nullable Context context, @Nullable List<SelectOtherBean.DataBeanXXXXX.BankBean> data, boolean isLook) {
        super(R.layout.adpter_test, data);
        this.context = context;
        this.data = data;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SelectOtherBean.DataBeanXXXXX.BankBean item) {
        helper.addOnClickListener(R.id.tv_delete);
        TextView tvMyIdentityCard = helper.getView(R.id.tv_registered_residence);
        RecyclerView rv = helper.getView(R.id.rv_registered_residence);
        final int parentPosition = helper.getLayoutPosition();
        helper.getView(R.id.tv_delete).setVisibility(View.GONE);
        tvMyIdentityCard.setText(item.getBank_type());
        //设置RecyclerView方向和是否反转
        GridLayoutManager NotUseList = new GridLayoutManager(context, 2);
        rv.setLayoutManager(NotUseList);
        rv.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        final OtherCategoryBankWaterAdapter categoryAdapter = new OtherCategoryBankWaterAdapter(item.getData(),isLook);
        rv.setAdapter(categoryAdapter);
        categoryAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_household_registration:
                        String cre_imgurl = item.getData().get(position).getBank_imgurl();
                        if (TextUtils.isEmpty(cre_imgurl)){
                            if (context instanceof BankRunningWaterActivity) {
                                ((BankRunningWaterActivity) context).SelectPhotoEnterprise(parentPosition, position);
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

}
