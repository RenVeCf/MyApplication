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
import com.example.ipd.yueyue.activity.ProductionAdjustmentActivity;
import com.example.ipd.yueyue.bean.SelectOtherBean;

import java.util.List;

public class OtherProductionAdjustmentAdapter extends BaseQuickAdapter<SelectOtherBean.DataBeanXXXXX.YieldBean, BaseViewHolder> {

    private List<SelectOtherBean.DataBeanXXXXX.YieldBean> mDatas;
    private Context context;


    public OtherProductionAdjustmentAdapter(@Nullable Context context, List<SelectOtherBean.DataBeanXXXXX.YieldBean> data) {
        super(R.layout.adpter_test, data);
        this.mDatas = data;
        this.context = context;
    }
    private boolean isLook;

    public OtherProductionAdjustmentAdapter(@Nullable Context context, List<SelectOtherBean.DataBeanXXXXX.YieldBean> data, boolean isLook) {
        super(R.layout.adpter_test, data);
        this.mDatas = data;
        this.context = context;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SelectOtherBean.DataBeanXXXXX.YieldBean item) {
        helper.addOnClickListener(R.id.tv_delete);
        helper.setText(R.id.tv_registered_residence,item.getYi_type());

        final int parentPosition = helper.getLayoutPosition();

        if (parentPosition == 0) {
            helper.setVisible(R.id.tv_delete, false);
        }else {
            helper.setVisible(R.id.tv_delete, true);
        }

        RecyclerView rv = helper.getView(R.id.rv_registered_residence);
        //设置RecyclerView方向和是否反转
        GridLayoutManager NotUseList = new GridLayoutManager(context, 2);
        rv.setLayoutManager(NotUseList);
        rv.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        final OthereCategoryProAdjustAdapter categoryAdapter = new OthereCategoryProAdjustAdapter(item.getData(),isLook);
        rv.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_household_registration:
                        SelectOtherBean.DataBeanXXXXX.YieldBean.DataBeanXXX dataBeanSecond = item.getData().get(position);
                        if (!TextUtils.isEmpty(dataBeanSecond.getYi_imgurl())) {
                            BigImageActivity.launch((Activity) mContext, dataBeanSecond.getYi_imgurl());
                        } else {
                            ((ProductionAdjustmentActivity) context).SelectPhotoMultiple(parentPosition, position);
                        }
                        break;
                    case R.id.iv_household_registration_del:
                        break;
                }
            }
        });
        if (isLook){
            helper.setVisible(R.id.tv_delete, false);
        }
    }



}
