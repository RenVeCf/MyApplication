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
import com.example.administrator.myapplication.activity.EnterpriseInformationActivity;
import com.example.administrator.myapplication.bean.GetCompanyInform;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.utils.SPUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnterpriseInformationAdapter extends BaseQuickAdapter<GetCompanyInform.DataBean.StroBean.StroSecondBean, BaseViewHolder> {
    private List<GetCompanyInform.DataBean.StroBean.StroSecondBean> data;
    private Context context;
    private TextView tvMyIdentityCard;
    private RecyclerView rv;

    public EnterpriseInformationAdapter(@Nullable Context context, List<GetCompanyInform.DataBean.StroBean.StroSecondBean> data) {
        super(R.layout.adpter_test, data);
        this.data = data;
        this.context = context;
    }
    private boolean isLook = false;
public EnterpriseInformationAdapter(@Nullable Context context, List<GetCompanyInform.DataBean.StroBean.StroSecondBean> data,boolean isLook) {
        super(R.layout.adpter_test, data);
        this.data = data;
        this.context = context;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, final GetCompanyInform.DataBean.StroBean.StroSecondBean item) {
        helper.addOnClickListener(R.id.tv_delete);
        tvMyIdentityCard = helper.getView(R.id.tv_registered_residence);
        rv = helper.getView(R.id.rv_registered_residence);
        final int parentposition = helper.getLayoutPosition();
        helper.setVisible(R.id.tv_delete, false);

        tvMyIdentityCard.setText(isLook?item.data.get(0).lic_type:getTitle(parentposition));

        //设置RecyclerView方向和是否反转
        GridLayoutManager NotUseList = new GridLayoutManager(context, 2);
        rv.setLayoutManager(NotUseList);
        rv.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        final CategoryCompanyInfromAdapter categoryAdapter = new CategoryCompanyInfromAdapter(item.data,isLook);
        rv.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GetCompanyInform.DataBean.StroBean.StroSecondBean.StroThreeBean stroThreeBean = item.data.get(position);
                switch (view.getId()) {
                    case R.id.iv_household_registration: //点击图片
                        if (!TextUtils.isEmpty(stroThreeBean.lic_imgurl)){
                            BigImageActivity.launch((Activity) mContext,stroThreeBean.lic_imgurl);
                        }else {
                            if (context instanceof EnterpriseInformationActivity) {
                                ((EnterpriseInformationActivity) context).SelectPhotoEnterprise(parentposition, position);
                            }
                        }
                        break;
                    case R.id.iv_household_registration_del: //点击删除
                        if (parentposition == 0 || parentposition ==1){

                            stroThreeBean.lic_imgurl = "";
                            categoryAdapter.notifyDataSetChanged();
                        }else {

                            categoryAdapter.removeData(position);
                        }
                        break;
                }
            }
        });

    }

    public boolean isCanAdd(){
        boolean isCanAdd = false;
        for (GetCompanyInform.DataBean.StroBean.StroSecondBean dataBean :data){
            for (GetCompanyInform.DataBean.StroBean.StroSecondBean.StroThreeBean stroThreeBean:dataBean.data){
                if (!TextUtils.isEmpty(stroThreeBean.lic_imgurl)){
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
            GetCompanyInform.DataBean.StroBean.StroSecondBean dataBean = data.get(i);
            for (GetCompanyInform.DataBean.StroBean.StroSecondBean.StroThreeBean stroThreeBean:dataBean.data){
                if (!TextUtils.isEmpty(stroThreeBean.lic_imgurl)){
                    Map<String, String> map = new HashMap<>();
                    map.put("lic_userid", (String) SPUtil.get(mContext, IConstants.USER_ID, ""));
                    map.put("lic_type",getTitle(i)+"");
                    map.put("lic_imgurl", stroThreeBean.lic_imgurl);
                    map.put("lic_proid",stroThreeBean.lic_proid+"");
                    map.put("lic_stro",(i+1)+"");
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
                s = "营业执照";
                break;
            case 1:
                s = "开户许可证";
                break;
            case 2:
                s = "企业信用报告";
                break;
            case 3:
                s = "企业章程";
                break;
            case 4:
                s = "验资报告";
                break;
        }
        return s;
    }


}
