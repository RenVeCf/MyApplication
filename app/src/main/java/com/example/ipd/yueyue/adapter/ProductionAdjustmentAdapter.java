package com.example.ipd.yueyue.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.activity.BigImageActivity;
import com.example.ipd.yueyue.activity.ProductionAdjustmentActivity;
import com.example.ipd.yueyue.bean.GetProAdjustBean;
import com.example.ipd.yueyue.common.config.IConstants;
import com.example.ipd.yueyue.utils.SPUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductionAdjustmentAdapter extends BaseQuickAdapter<GetProAdjustBean.DataBeanFirst, BaseViewHolder> {

    private List<GetProAdjustBean.DataBeanFirst> mDatas;
    private Context context;


    public ProductionAdjustmentAdapter(@Nullable Context context,List<GetProAdjustBean.DataBeanFirst> data) {
        super(R.layout.adpter_test, data);
        this.mDatas = data;
        this.context = context;
    }
    private boolean isLook;

    public ProductionAdjustmentAdapter(@Nullable Context context,List<GetProAdjustBean.DataBeanFirst> data,boolean isLook) {
        super(R.layout.adpter_test, data);
        this.mDatas = data;
        this.context = context;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, final GetProAdjustBean.DataBeanFirst item) {
        helper.addOnClickListener(R.id.tv_delete);
        helper.setText(R.id.tv_registered_residence,item.yi_type);

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
        final CategoryProAdjustAdapter categoryAdapter = new CategoryProAdjustAdapter(item.data,isLook);
        rv.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_household_registration:
                        GetProAdjustBean.DataBeanFirst.DataBeanSecond dataBeanSecond = item.data.get(position);
                        if (!TextUtils.isEmpty(dataBeanSecond.yi_imgurl)) {
                            BigImageActivity.launch((Activity) mContext, dataBeanSecond.yi_imgurl);
                        } else {
                            ((ProductionAdjustmentActivity) context).SelectPhotoMultiple(parentPosition, position);
                        }
                        break;
                    case R.id.iv_household_registration_del:
                        item.data.remove(position);
                        categoryAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        if (isLook){
            helper.setVisible(R.id.tv_delete, false);
        }
    }

    public boolean isCanLoad(){
        boolean isCanLoad = false;
        for (GetProAdjustBean.DataBeanFirst dataBeanX : mDatas) {
            for (GetProAdjustBean.DataBeanFirst.DataBeanSecond dataBean : dataBeanX.data) {
                if (TextUtils.isEmpty(dataBean.yi_imgurl)) {
                    isCanLoad = true;
                    break;
                }
            }
            if (isCanLoad == true){
                break;
            }
        }
        return isCanLoad;
    }
    public String getLoadString() {
        List<Map<String, String>> listMap = new ArrayList<>();
        for (GetProAdjustBean.DataBeanFirst dataBeanX : mDatas) {
            for (int i=0;i<dataBeanX.data.size()-1;i++) {
                GetProAdjustBean.DataBeanFirst.DataBeanSecond dataBean = dataBeanX.data.get(i);
                if (!TextUtils.isEmpty(dataBean.yi_imgurl)) {
                    HashMap map = new HashMap<>();
                    map.put("yi_userid", (String) SPUtil.get(mContext, IConstants.USER_ID, ""));
                    map.put("yi_proid", dataBean.yi_proid+"");
                    map.put("yi_type", dataBean.yi_type);
                    map.put("yi_imgurl", dataBean.yi_imgurl);
                    listMap.add(map);
                }
            }
        }
        Gson g = new Gson();
        String jsonString = g.toJson(listMap);
        Log.e("TAG",jsonString);
        return jsonString;
    }




}
