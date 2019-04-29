package com.example.administrator.myapplication.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.GetCompanyInform;
import com.example.administrator.myapplication.bean.GetCreditReportBean;
import com.example.administrator.myapplication.common.config.UrlConfig;
import com.example.administrator.myapplication.utils.ApplicationUtil;

import java.util.List;

/**
 * 信用报告的 二级图片选择
 */
public class CategoryCreditReportAdapter extends BaseQuickAdapter<GetCreditReportBean.DataBeanX.DataBean, BaseViewHolder> {
    private List<GetCreditReportBean.DataBeanX.DataBean> data;

    public CategoryCreditReportAdapter(@Nullable List<GetCreditReportBean.DataBeanX.DataBean> data) {
        super(R.layout.adapter_household_registration_item, data);
        this.data = data;
    }


    @Override
    protected void convert(BaseViewHolder helper,final  GetCreditReportBean.DataBeanX.DataBean item) {
        int position = helper.getAdapterPosition();
        //初始化数据
        ImageView imageView = helper.getView(R.id.iv_household_registration);
        ImageView imageDel = helper.getView(R.id.iv_household_registration_del);
        helper.getView(R.id.tv_household_registration_down).setVisibility(View.GONE);

        if (!TextUtils.isEmpty(item.cre_imgurl)) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.cre_imgurl).apply(new RequestOptions()).into(imageView);
            imageDel.setVisibility(View.VISIBLE);
        }else{
            imageView.setImageResource(R.drawable.bg_id);
            imageDel.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.iv_household_registration)
                .addOnClickListener(R.id.iv_household_registration_del);
    }


    //  添加数据
    public void addData() {
        data.add(new GetCreditReportBean.DataBeanX.DataBean());
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
