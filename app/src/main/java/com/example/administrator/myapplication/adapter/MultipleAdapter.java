package com.example.administrator.myapplication.adapter;

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
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.BigImageActivity;
import com.example.administrator.myapplication.activity.HouseholdRegistrationBookActivity;
import com.example.administrator.myapplication.bean.GetHouseholdRegistrationBookBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.utils.SPUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipleAdapter extends BaseQuickAdapter<GetHouseholdRegistrationBookBean.DataBeanX, BaseViewHolder> {
    private List<GetHouseholdRegistrationBookBean.DataBeanX> data;
    private Context context;

    public MultipleAdapter(@Nullable Context context, List<GetHouseholdRegistrationBookBean.DataBeanX> data) {
        super(R.layout.adpter_test, data);
        this.data = data;
        this.context = context;
    }
    private boolean isLook = false;
    public MultipleAdapter(@Nullable Context context, List<GetHouseholdRegistrationBookBean.DataBeanX> data,boolean isLook) {
        super(R.layout.adpter_test, data);
        this.data = data;
        this.context = context;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, final GetHouseholdRegistrationBookBean.DataBeanX item) {

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
        final CategoryAdapter categoryAdapter = new CategoryAdapter(item.getData(),isLook);
        rv.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_household_registration:
                        GetHouseholdRegistrationBookBean.DataBeanX.DataBean dataBean = item.getData().get(position);
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


    public boolean isCanLoad() {
        boolean isCanLoad = false;
        for (GetHouseholdRegistrationBookBean.DataBeanX dataBeanX : data) {
            for (GetHouseholdRegistrationBookBean.DataBeanX.DataBean dataBean : dataBeanX.getData()) {
                if (TextUtils.isEmpty(dataBean.getAcc_imgurl())) {
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
        for (GetHouseholdRegistrationBookBean.DataBeanX dataBeanX : data) {
            for (int i=0;i<dataBeanX.getData().size()-1;i++) {
                GetHouseholdRegistrationBookBean.DataBeanX.DataBean dataBean = dataBeanX.getData().get(i);
                if (!TextUtils.isEmpty(dataBean.getAcc_imgurl())) {
                    HashMap map = new HashMap<>();
                    map.put("acc_userid", (String) SPUtil.get(mContext, IConstants.USER_ID, ""));
                    map.put("acc_proid", dataBean.getAcc_proid()+"");
                    map.put("acc_type", dataBean.getAcc_type());
                    map.put("acc_imgurl", dataBean.getAcc_imgurl());
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
