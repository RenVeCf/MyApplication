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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.BankRunningWaterActivity;
import com.example.administrator.myapplication.activity.BigImageActivity;
import com.example.administrator.myapplication.activity.CreditReportActivity;
import com.example.administrator.myapplication.bean.GetBankImgBean;
import com.example.administrator.myapplication.bean.GetCreditReportBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.config.UrlConfig;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.SPUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankAdapter extends BaseQuickAdapter<GetBankImgBean.DataBean, BaseViewHolder> {
    private List<GetBankImgBean.DataBean> data;
    private Context context;

    public BankAdapter(@Nullable Context context, @Nullable List<GetBankImgBean.DataBean> data) {
        super(R.layout.adpter_test, data);
        this.context = context;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final GetBankImgBean.DataBean item) {
        helper.addOnClickListener(R.id.tv_delete);
        TextView tvMyIdentityCard = helper.getView(R.id.tv_registered_residence);
        RecyclerView rv = helper.getView(R.id.rv_registered_residence);
        final int parentPosition = helper.getLayoutPosition();
        helper.getView(R.id.tv_delete).setVisibility(View.GONE);
        tvMyIdentityCard.setText(item.bank_type);
        //设置RecyclerView方向和是否反转
        GridLayoutManager NotUseList = new GridLayoutManager(context, 2);
        rv.setLayoutManager(NotUseList);
        rv.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        final CategoryBankWaterAdapter categoryAdapter = new CategoryBankWaterAdapter(item.data);
        rv.setAdapter(categoryAdapter);
        categoryAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_household_registration:
                        String cre_imgurl = item.data.get(position).bank_imgurl;
                        if (TextUtils.isEmpty(cre_imgurl)){
                            if (context instanceof BankRunningWaterActivity) {
                                ((BankRunningWaterActivity) context).SelectPhotoEnterprise(parentPosition, position);
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
        for (GetBankImgBean.DataBean dataBean :data){
            for (GetBankImgBean.DataBean.DataBeanSecond stroThreeBean:dataBean.data){
                if (!TextUtils.isEmpty(stroThreeBean.bank_imgurl)){
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
            GetBankImgBean.DataBean dataBean = data.get(i);
            for (GetBankImgBean.DataBean.DataBeanSecond stroThreeBean:dataBean.data){
                if (!TextUtils.isEmpty(stroThreeBean.bank_imgurl)){
                    Map<String, String> map = new HashMap<>();
                    map.put("bank_userid", (String) SPUtil.get(mContext, IConstants.USER_ID, ""));
                    map.put("bank_type",dataBean.bank_type);
                    map.put("bank_imgurl", stroThreeBean.bank_imgurl);
                    map.put("bank_proid",stroThreeBean.bank_proid+"");
                    listMap.add(map);
                }
            }
        }
        Gson g = new Gson();
        String jsonString = g.toJson(listMap);
//        Log.e("TAG",jsonString);
        return jsonString;

    }
}
