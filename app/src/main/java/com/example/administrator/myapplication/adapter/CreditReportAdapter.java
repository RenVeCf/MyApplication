package com.example.administrator.myapplication.adapter;

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
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.BigImageActivity;
import com.example.administrator.myapplication.activity.CreditReportActivity;
import com.example.administrator.myapplication.bean.GetCreditReportBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.utils.SPUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditReportAdapter extends BaseQuickAdapter<GetCreditReportBean.DataBeanX, BaseViewHolder> {
    private List<GetCreditReportBean.DataBeanX> data;
    private Context context;

    public CreditReportAdapter(@Nullable Context context, List<GetCreditReportBean.DataBeanX> data) {
        super(R.layout.adpter_test, data);
        this.data = data;
        this.context = context;
    }
    private boolean isLook = false;
    public CreditReportAdapter(@Nullable Context context, List<GetCreditReportBean.DataBeanX> data,boolean isLook) {
        super(R.layout.adpter_test, data);
        this.data = data;
        this.context = context;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, final GetCreditReportBean.DataBeanX item) {
        helper.addOnClickListener(R.id.tv_delete);
        TextView tvMyIdentityCard = helper.getView(R.id.tv_registered_residence);
        RecyclerView rv = helper.getView(R.id.rv_registered_residence);
        final int parentPosition = helper.getLayoutPosition();
        helper.setVisible(R.id.tv_delete, false);
        tvMyIdentityCard.setText(isLook?item.cre_type:getTitle(parentPosition));
        //设置RecyclerView方向和是否反转
        GridLayoutManager NotUseList = new GridLayoutManager(context, 2);
        rv.setLayoutManager(NotUseList);
        rv.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        final CategoryCreditReportAdapter categoryAdapter = new CategoryCreditReportAdapter(item.data,isLook);
        rv.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_household_registration:
                        String cre_imgurl = item.data.get(position).cre_imgurl;
                        if (TextUtils.isEmpty(cre_imgurl)){
                            if (context instanceof CreditReportActivity) {
                                ((CreditReportActivity) context).SelectPhotoEnterprise(parentPosition, position);
                            }
                        }else {
                            BigImageActivity.launch((Activity) context,cre_imgurl);
                        }

                        break;
                    case R.id.iv_household_registration_del:
                        categoryAdapter.removeData(position);
                        break;
                }
            }
        });

    }
    public boolean isCanAdd(){
        boolean isCanAdd = false;
        for (GetCreditReportBean.DataBeanX dataBean :data){
            for (GetCreditReportBean.DataBeanX.DataBean stroThreeBean:dataBean.data){
                if (!TextUtils.isEmpty(stroThreeBean.cre_imgurl)){
                    isCanAdd = true;
                    break;
                }
            }
            if (isCanAdd){
                break;
            }
        }
        return isCanAdd;

    }
    public String getLoadString(){
        List<Map<String, String>> listMap = new ArrayList<>();
        for (int i=0;i<data.size();i++){
            GetCreditReportBean.DataBeanX dataBean = data.get(i);
            for (GetCreditReportBean.DataBeanX.DataBean stroThreeBean:dataBean.data){
                if (!TextUtils.isEmpty(stroThreeBean.cre_imgurl)){
                    Map<String, String> map = new HashMap<>();
                    map.put("cre_userid", (String) SPUtil.get(mContext, IConstants.USER_ID, ""));
                    map.put("cre_type",getTitle(i)+"");
                    map.put("cre_imgurl", stroThreeBean.cre_imgurl);
                    map.put("cre_proid",stroThreeBean.cre_proid+"");
                    listMap.add(map);
                }
            }
        }
        Gson g = new Gson();
        String jsonString = g.toJson(listMap);
        return jsonString;

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
