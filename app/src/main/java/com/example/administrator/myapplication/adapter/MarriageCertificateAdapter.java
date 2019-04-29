package com.example.administrator.myapplication.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.GetMarryImgBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.config.UrlConfig;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.SPUtil;
import com.example.administrator.myapplication.utils.StringUtils;
import com.example.administrator.myapplication.utils.ToastUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarriageCertificateAdapter extends BaseQuickAdapter<GetMarryImgBean.DataBean, BaseViewHolder> {
    private List<GetMarryImgBean.DataBean> data;

    public MarriageCertificateAdapter(@Nullable List<GetMarryImgBean.DataBean> data) {
        super(R.layout.adapter_marriage_certificate_item, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper,final GetMarryImgBean.DataBean item) {
        helper.addOnClickListener(R.id.tv_delete)
                .addOnClickListener(R.id.iv_marriage_certificate_one)
                .addOnClickListener(R.id.iv_marriage_certificate_two)
                .addOnClickListener(R.id.iv_marriage_certificate_three)
                .addOnClickListener(R.id.iv_marriage_certificate_one_del)
                .addOnClickListener(R.id.iv_marriage_certificate_two_del)
                .addOnClickListener(R.id.iv_marriage_certificate_three_del);
        helper.setText(R.id.tv_my_marriage_certificate_card,StringUtils.encodingGb(item.getMar_type())+"结婚证");
        int position = helper.getLayoutPosition();
        if (position == 0) {
            helper.setVisible(R.id.tv_delete, false);
        } else {
            helper.setVisible(R.id.tv_delete, true);
        }
        ImageView ivOne = helper.getView(R.id.iv_marriage_certificate_one);
        ImageView ivTwo = helper.getView(R.id.iv_marriage_certificate_two);
        ImageView ivThree = helper.getView(R.id.iv_marriage_certificate_three);
        ImageView ivOneDel = helper.getView(R.id.iv_marriage_certificate_one_del);
        ImageView ivTwoDel = helper.getView(R.id.iv_marriage_certificate_two_del);
        ImageView ivThreeDel = helper.getView(R.id.iv_marriage_certificate_three_del);

        if (!TextUtils.isEmpty(item.getMar_page1())) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getMar_page1()).apply(new RequestOptions()).into(ivOne);
            ivOneDel.setVisibility(View.VISIBLE);
        }else{
            ivOne.setImageResource(R.drawable.bg_id);
            ivOneDel.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(item.getMar_page2())) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getMar_page2()).apply(new RequestOptions()).into(ivTwo);
            ivTwoDel.setVisibility(View.VISIBLE);
        }else{
            ivTwo.setImageResource(R.drawable.bg_id);
            ivTwoDel.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(item.getMar_page3())) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getMar_page3()).apply(new RequestOptions()).into(ivThree);
            ivThreeDel.setVisibility(View.VISIBLE);
        }else{
            ivThree.setImageResource(R.drawable.bg_id);
            ivThreeDel.setVisibility(View.GONE);
        }

        ivOneDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setMar_page1("");
                notifyDataSetChanged();
            }
        });ivTwoDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setMar_page2("");
                notifyDataSetChanged();
            }
        });ivThreeDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setMar_page3("");
                notifyDataSetChanged();
            }
        });


    }


    //  添加数据
    public void addData(int position, GetMarryImgBean.DataBean str) {
        data.add(str);
        //添加动画
        notifyDataSetChanged();
    }

    //  删除数据
    public void removeData(int position) {
        data.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public boolean isCanAdd(){
        boolean isCanAdd = true;
        for (GetMarryImgBean.DataBean dataBean :data){
            if (TextUtils.isEmpty(dataBean.getMar_page1())){
                ToastUtil.showShortToast("请选择"+StringUtils.encodingGb(dataBean.getMar_type())+"结婚证1页!");
                isCanAdd = false;
            }else if (TextUtils.isEmpty(dataBean.getMar_page2())){
                ToastUtil.showShortToast("请选择"+StringUtils.encodingGb(dataBean.getMar_type())+"结婚证2页!");
                isCanAdd = false;
            }else if (TextUtils.isEmpty(dataBean.getMar_page3())){
                ToastUtil.showShortToast("请选择"+StringUtils.encodingGb(dataBean.getMar_type())+"结婚证3页!");
                isCanAdd = false;
            }
        }
        return isCanAdd;

    }
    public String getLoadString(){
        List<Map<String, String>> listMap = new ArrayList<>();
        for (GetMarryImgBean.DataBean dataBean :data){
            Map<String, String> map = new HashMap<>();
            map.put("mar_userid", (String) SPUtil.get(mContext, IConstants.USER_ID, ""));
            map.put("mar_proid", dataBean.getMar_proid()+"");
            map.put("mar_type", dataBean.getMar_type());
            map.put("mar_page1",dataBean.getMar_page1());
            map.put("mar_page2",dataBean.getMar_page2());
            map.put("mar_page3",dataBean.getMar_page3());
            listMap.add(map);
        }
        Gson g = new Gson();
        String jsonString = g.toJson(listMap);
        Log.e("TAG",jsonString);
        return jsonString;

    }
}
