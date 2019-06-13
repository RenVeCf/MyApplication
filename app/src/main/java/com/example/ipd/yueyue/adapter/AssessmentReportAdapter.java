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
import com.example.ipd.yueyue.bean.GetAssessmentImgBean;
import com.example.ipd.yueyue.common.config.IConstants;
import com.example.ipd.yueyue.common.config.UrlConfig;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.example.ipd.yueyue.utils.SPUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssessmentReportAdapter extends BaseQuickAdapter<GetAssessmentImgBean.DataBean, BaseViewHolder> {
    private List<GetAssessmentImgBean.DataBean> data;

    public AssessmentReportAdapter(@Nullable List<GetAssessmentImgBean.DataBean> data) {
        super(R.layout.adapter_household_registration_item, data);
        this.data = data;
    }

    private boolean isLook = false;

    public AssessmentReportAdapter(@Nullable List<GetAssessmentImgBean.DataBean> data, boolean isLook) {
        super(R.layout.adapter_household_registration_item, data);
        this.data = data;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, GetAssessmentImgBean.DataBean item) {
        helper.addOnClickListener(R.id.iv_household_registration)
                .addOnClickListener(R.id.iv_household_registration_del);
        ImageView ivOne = helper.getView(R.id.iv_household_registration);
        ImageView ivOneDel = helper.getView(R.id.iv_household_registration_del);
        if (!TextUtils.isEmpty(item.getAss_imgurl())) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getAss_imgurl()).apply(new RequestOptions()).into(ivOne);
            ivOneDel.setVisibility(View.VISIBLE);
        } else {
            ivOne.setImageResource(R.drawable.bg_id);
            ivOneDel.setVisibility(View.GONE);
        }
        helper.getView(R.id.tv_household_registration_down).setVisibility(View.GONE);
        if (isLook) {
            ivOneDel.setVisibility(View.GONE);
        }
    }


    public boolean isCanAdd() {
        boolean isCanAdd = false;
        for (GetAssessmentImgBean.DataBean dataBean : data) {
            if (!TextUtils.isEmpty(dataBean.getAss_imgurl())) {
                isCanAdd = true;
            }
        }
        return isCanAdd;

    }

    public String getLoadString() {
        List<Map<String, String>> listMap = new ArrayList<>();
        for (GetAssessmentImgBean.DataBean dataBean : data) {
            if (!TextUtils.isEmpty(dataBean.getAss_imgurl())) {
                Map<String, String> map = new HashMap<>();
                map.put("ass_userid", (String) SPUtil.get(mContext, IConstants.USER_ID, ""));
                map.put("ass_proid", dataBean.getAss_proid() + "");
                map.put("ass_type", "评估报告");
                map.put("ass_imgurl", dataBean.getAss_imgurl());
                listMap.add(map);
            }

        }
        Gson g = new Gson();
        String jsonString = g.toJson(listMap);
        Log.e("TAG", jsonString);
        return jsonString;
    }
}
