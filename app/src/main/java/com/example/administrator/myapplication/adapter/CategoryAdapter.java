package com.example.administrator.myapplication.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.BigImageActivity;
import com.example.administrator.myapplication.bean.GetHouseholdRegistrationBookBean;
import com.example.administrator.myapplication.common.config.UrlConfig;
import com.example.administrator.myapplication.utils.ApplicationUtil;

import java.util.List;

public class CategoryAdapter extends BaseQuickAdapter<GetHouseholdRegistrationBookBean.DataBeanX.DataBean, BaseViewHolder> {
    private List<GetHouseholdRegistrationBookBean.DataBeanX.DataBean> data;

    public CategoryAdapter(@Nullable List<GetHouseholdRegistrationBookBean.DataBeanX.DataBean> data) {
        super(R.layout.adapter_household_registration_item, data);
        this.data = data;
    }


    @Override
    protected void convert(BaseViewHolder helper,final  GetHouseholdRegistrationBookBean.DataBeanX.DataBean item) {
        int position = helper.getAdapterPosition();
        //初始化数据
        ImageView ivHouseholdRegistration = helper.getView(R.id.iv_household_registration);
        ImageView ivHouseholdRegistrationDel = helper.getView(R.id.iv_household_registration_del);

        TextView tvHouseholdRegistrationDown = helper.getView(R.id.tv_household_registration_down);



        if (!TextUtils.isEmpty(item.getAcc_imgurl())) {
            tvHouseholdRegistrationDown.setText(item.getCompleteType()+(position+1));
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getAcc_imgurl()).apply(new RequestOptions()).into(ivHouseholdRegistration);
            ivHouseholdRegistrationDel.setVisibility(View.VISIBLE);
        }else{
            tvHouseholdRegistrationDown.setText("请选择");
            ivHouseholdRegistration.setImageResource(R.drawable.bg_id);
            ivHouseholdRegistrationDel.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.iv_household_registration)
                .addOnClickListener(R.id.iv_household_registration_del);
    }

//    @Override
//    public int getItemCount() {
//        return data == null?1:data.size()+1;
//    }

    //  添加数据
    public void addData() {
        data.add(new GetHouseholdRegistrationBookBean.DataBeanX.DataBean());
        //添加动画
        notifyItemInserted(data.size());
    }

    //  删除数据
    public void removeData(int position) {
        data.remove(position);
        //删除动画
        notifyItemRemoved(position);
    }
}
