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
import com.example.ipd.yueyue.activity.EnterpriseInformationActivity;
import com.example.ipd.yueyue.bean.SelectOtherBean;

import java.util.List;

public class OtherEnterpriseInformationAdapter extends BaseQuickAdapter<SelectOtherBean.DataBeanXXXXX.LicenseBean, BaseViewHolder> {
    private List<SelectOtherBean.DataBeanXXXXX.LicenseBean> data;
    private Context context;
    private TextView tvMyIdentityCard;
    private RecyclerView rv;

    public OtherEnterpriseInformationAdapter(@Nullable Context context, List<SelectOtherBean.DataBeanXXXXX.LicenseBean> data) {
        super(R.layout.adpter_test, data);
        this.data = data;
        this.context = context;
    }
    private boolean isLook = false;
public OtherEnterpriseInformationAdapter(@Nullable Context context, List<SelectOtherBean.DataBeanXXXXX.LicenseBean> data, boolean isLook) {
        super(R.layout.adpter_test, data);
        this.data = data;
        this.context = context;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SelectOtherBean.DataBeanXXXXX.LicenseBean item) {
        helper.addOnClickListener(R.id.tv_delete);
        tvMyIdentityCard = helper.getView(R.id.tv_registered_residence);
        rv = helper.getView(R.id.rv_registered_residence);
        final int parentposition = helper.getLayoutPosition();
        helper.setVisible(R.id.tv_delete, false);

        tvMyIdentityCard.setText(isLook?item.getData().get(0).getLic_type():getTitle(parentposition));

        //设置RecyclerView方向和是否反转
        GridLayoutManager NotUseList = new GridLayoutManager(context, 2);
        rv.setLayoutManager(NotUseList);
        rv.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        final OtherCategoryCompanyInfromAdapter categoryAdapter = new OtherCategoryCompanyInfromAdapter(item.getData(),isLook);
        rv.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SelectOtherBean.DataBeanXXXXX.LicenseBean.DataBeanX stroThreeBean = item.getData().get(position);
                switch (view.getId()) {
                    case R.id.iv_household_registration: //点击图片
                        if (!TextUtils.isEmpty(stroThreeBean.getLic_imgurl())){
                            BigImageActivity.launch((Activity) mContext,stroThreeBean.getLic_imgurl());
                        }else {
                            if (context instanceof EnterpriseInformationActivity) {
                                ((EnterpriseInformationActivity) context).SelectPhotoEnterprise(parentposition, position);
                            }
                        }
                        break;
                    case R.id.iv_household_registration_del: //点击删除

                        break;
                }
            }
        });

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
