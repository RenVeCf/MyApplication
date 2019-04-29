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
import com.example.administrator.myapplication.bean.GetHouseImgBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.config.UrlConfig;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.SPUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyOwnershipCertificateAdapter extends BaseQuickAdapter<GetHouseImgBean.DataBean, BaseViewHolder> {
    private List<GetHouseImgBean.DataBean> data;


    public PropertyOwnershipCertificateAdapter(@Nullable List<GetHouseImgBean.DataBean> data) {
        super(R.layout.adapter_property_ownership_certificate_item, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final GetHouseImgBean.DataBean item) {
        helper.addOnClickListener(R.id.tv_delete)
                .addOnClickListener(R.id.iv_property_ownership_certificate_one)
                .addOnClickListener(R.id.iv_property_ownership_certificate_two)
                .addOnClickListener(R.id.iv_property_ownership_certificate_three)
                .addOnClickListener(R.id.iv_property_ownership_certificate_four)
                .addOnClickListener(R.id.iv_property_ownership_certificate_five)
                .addOnClickListener(R.id.iv_property_ownership_certificate_one_del)
                .addOnClickListener(R.id.iv_property_ownership_certificate_two_del)
                .addOnClickListener(R.id.iv_property_ownership_certificate_three_del)
                .addOnClickListener(R.id.iv_property_ownership_certificate_four_del)
                .addOnClickListener(R.id.iv_property_ownership_certificate_five_del);
        int position = helper.getLayoutPosition();
        helper.setText(R.id.tv_property_ownership_certificate,"房产证" + (position+1));
        if (position == 0) {
            helper.setVisible(R.id.tv_delete, false);
        }
        ImageView ivOne = helper.getView(R.id.iv_property_ownership_certificate_one);
        ImageView ivTwo = helper.getView(R.id.iv_property_ownership_certificate_two);
        ImageView ivThree = helper.getView(R.id.iv_property_ownership_certificate_three);
        ImageView ivFour = helper.getView(R.id.iv_property_ownership_certificate_four);
        ImageView ivFive = helper.getView(R.id.iv_property_ownership_certificate_five);
        ImageView ivOneDel = helper.getView(R.id.iv_property_ownership_certificate_one_del);
        ImageView ivTwoDel = helper.getView(R.id.iv_property_ownership_certificate_two_del);
        ImageView ivThreeDel = helper.getView(R.id.iv_property_ownership_certificate_three_del);
        ImageView ivFourDel = helper.getView(R.id.iv_property_ownership_certificate_four_del);
        ImageView ivFiveDel = helper.getView(R.id.iv_property_ownership_certificate_five_del);
        if (item.getEst_page1() != null && !item.getEst_page1().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getEst_page1()).apply(new RequestOptions()).into(ivOne);
            ivOneDel.setVisibility(View.VISIBLE);
        }else {
            ivOne.setImageResource(R.drawable.bg_id);
            ivOneDel.setVisibility(View.GONE);
        }
        if (item.getEst_page2() != null && !item.getEst_page2().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getEst_page2()).apply(new RequestOptions()).into(ivTwo);
            ivTwoDel.setVisibility(View.VISIBLE);
        }else {
            ivTwo.setImageResource(R.drawable.bg_id);
            ivTwoDel.setVisibility(View.GONE);
        }
        if (item.getEst_page3() != null && !item.getEst_page3().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getEst_page3()).apply(new RequestOptions()).into(ivThree);
            ivThreeDel.setVisibility(View.VISIBLE);
        }else {
            ivThree.setImageResource(R.drawable.bg_id);
            ivThreeDel.setVisibility(View.GONE);
        }
        if (item.getEst_page4() != null && !item.getEst_page4().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getEst_page4()).apply(new RequestOptions()).into(ivFour);
            ivFourDel.setVisibility(View.VISIBLE);
        }else {
            ivFour.setImageResource(R.drawable.bg_id);
            ivFourDel.setVisibility(View.GONE);
        }
        if (item.getEst_page5() != null && !item.getEst_page5().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getEst_page5()).apply(new RequestOptions()).into(ivFive);
            ivFiveDel.setVisibility(View.VISIBLE);
        }else {
            ivFive.setImageResource(R.drawable.bg_id);
            ivFiveDel.setVisibility(View.GONE);
        }
        ivOneDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setEst_page1("");
                notifyDataSetChanged();
            }
        });ivTwoDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setEst_page2("");
                notifyDataSetChanged();
            }
        });ivThreeDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setEst_page3("");
                notifyDataSetChanged();
            }
        });ivFourDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setEst_page4("");
                notifyDataSetChanged();
            }
        });ivFiveDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setEst_page5("");
                notifyDataSetChanged();
            }
        });
    }



    //  添加数据
    public void addData(int position, GetHouseImgBean.DataBean str) {
        data.add(position, str);
        //添加动画
        notifyDataSetChanged();
    }

    //  删除数据
    public void removeData(int position) {
        data.remove(position);
        //删除动画
        notifyDataSetChanged();
    }

    public boolean isCanAdd(){
        boolean isCanAdd = false;
        for (GetHouseImgBean.DataBean dataBean :data){
            if (!TextUtils.isEmpty(dataBean.getEst_page1() )
                    || !TextUtils.isEmpty(dataBean.getEst_page2())
                    || !TextUtils.isEmpty(dataBean.getEst_page3())
                    || !TextUtils.isEmpty(dataBean.getEst_page4())
                    || !TextUtils.isEmpty(dataBean.getEst_page5())){
                isCanAdd = true;
                break;
            }
        }
        return isCanAdd;

    }
    public String getLoadString(){
        List<Map<String, String>> listMap = new ArrayList<>();
        for (int i=0;i<data.size();i++){
            GetHouseImgBean.DataBean dataBean  = data.get(i);
            Map<String, String> map = new HashMap<>();
            map.put("est_userid", (String) SPUtil.get(mContext, IConstants.USER_ID, ""));
            map.put("est_proid", dataBean.getEst_proid()+"");
            map.put("est_type", "房产证"+(i+1));
            map.put("est_page1",dataBean.getEst_page1() == null?"":dataBean.getEst_page1());
            map.put("est_page2",dataBean.getEst_page2() == null?"":dataBean.getEst_page2());
            map.put("est_page3",dataBean.getEst_page3() == null?"":dataBean.getEst_page3());
            map.put("est_page4",dataBean.getEst_page4() == null?"":dataBean.getEst_page4());
            map.put("est_page5",dataBean.getEst_page5() == null?"":dataBean.getEst_page5());
            listMap.add(map);
        }
        Gson g = new Gson();
        String jsonString = g.toJson(listMap);
        Log.e("shangchuanshuju",jsonString);
        return jsonString;

    }
}
