package com.example.ipd.yueyue.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.bean.SupplementaryBean;
import com.example.ipd.yueyue.common.config.IConstants;
import com.example.ipd.yueyue.common.config.UrlConfig;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.example.ipd.yueyue.utils.SPUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplementaryAdapter extends BaseQuickAdapter<SupplementaryBean, BaseViewHolder> {

    private List<SupplementaryBean> data;

    private String far_proid;

    public SupplementaryAdapter(String far_proid,@Nullable List<SupplementaryBean> data) {
        super(R.layout.adapter_supplementary_item, data);
        this.data = data;
        this.far_proid = far_proid;
    }

    @Override
    protected void convert(BaseViewHolder helper, SupplementaryBean item) {
        ImageView ivSupplementary = helper.getView(R.id.iv_household_registration);
        ImageView ivSupplementaryDel = helper.getView(R.id.iv_household_registration_del);
        helper.setText(R.id.tv_household_registration_down, item.getImgName())
                .setVisible(R.id.tv_household_registration_down, true);
        helper.addOnClickListener(R.id.iv_household_registration)
                .addOnClickListener(R.id.iv_household_registration_del);
        Log.e("TAG==",item.getImgPath()+"==");
        Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getImgPath()).apply(new RequestOptions()).into(ivSupplementary);
        ivSupplementaryDel.setVisibility(View.VISIBLE);
    }



    //  删除数据
    public void removeData(int position) {
        data.remove(position);
        //删除动画
        notifyItemRemoved(position);
    }
    public boolean isCanAdd(){
        boolean isCanAdd = false;
        for (SupplementaryBean dataBean :data){
            if (!TextUtils.isEmpty(dataBean.getImgPath())){
                isCanAdd = true;
                break;
            }
        }
        return isCanAdd;

    }
    public String getLoadString(){
        List<Map<String, String>> listMap = new ArrayList<>();
        for (SupplementaryBean dataBean :data){
            if (!TextUtils.isEmpty(dataBean.getImgPath())){
                Map<String, String> map = new HashMap<>();
                map.put("far_userid", (String) SPUtil.get(mContext, IConstants.USER_ID, ""));
                map.put("far_type", dataBean.getImgName()+"");
                map.put("far_imgurl", dataBean.getImgPath());
                map.put("far_proid",far_proid);
                listMap.add(map);
            }
        }
        Gson g = new Gson();
        String jsonString = g.toJson(listMap);
        Log.e("TAG",jsonString);
        return jsonString;

    }
}
