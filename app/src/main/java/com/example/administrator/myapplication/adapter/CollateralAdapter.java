package com.example.administrator.myapplication.adapter;

import android.support.annotation.Nullable;
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
import com.example.administrator.myapplication.bean.GetCollateralImgBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.config.UrlConfig;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.SPUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollateralAdapter extends BaseQuickAdapter<GetCollateralImgBean.DataBean, BaseViewHolder> {
    private List<GetCollateralImgBean.DataBean> data;


    public CollateralAdapter(@Nullable List<GetCollateralImgBean.DataBean> data) {
        super(R.layout.adapter_collater_item, data);
        this.data = data;
    }
    private boolean isLook;
 public CollateralAdapter(@Nullable List<GetCollateralImgBean.DataBean> data,boolean isLook) {
        super(R.layout.adapter_collater_item, data);
        this.data = data;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, GetCollateralImgBean.DataBean item) {
        helper.addOnClickListener(R.id.tv_delete)
                .addOnClickListener(R.id.iv_collateral_one)
                .addOnClickListener(R.id.iv_collateral_two)
                .addOnClickListener(R.id.iv_collateral_three)
                .addOnClickListener(R.id.iv_collateral_four)
                .addOnClickListener(R.id.iv_collateral_five)
                .addOnClickListener(R.id.iv_collateral_six)
                .addOnClickListener(R.id.iv_collateral_seven)
                .addOnClickListener(R.id.iv_collateral_eight)
                .addOnClickListener(R.id.iv_collateral_one_del)
                .addOnClickListener(R.id.iv_collateral_two_del)
                .addOnClickListener(R.id.iv_collateral_three_del)
                .addOnClickListener(R.id.iv_collateral_four_del)
                .addOnClickListener(R.id.iv_collateral_five_del)
                .addOnClickListener(R.id.iv_collateral_six_del)
                .addOnClickListener(R.id.iv_collateral_seven_del)
                .addOnClickListener(R.id.iv_collateral_eight_del);
        TextView tvCollateral = helper.getView(R.id.tv_collateral);
        int position = helper.getLayoutPosition();
        tvCollateral.setText("抵押物" + (position+1));
        if (position == 0) {
            helper.setVisible(R.id.tv_delete, false);
        }else {
            helper.setVisible(R.id.tv_delete, true);

        }
        ImageView ivOne = helper.getView(R.id.iv_collateral_one);
        ImageView ivTwo = helper.getView(R.id.iv_collateral_two);
        ImageView ivThree = helper.getView(R.id.iv_collateral_three);
        ImageView ivFour = helper.getView(R.id.iv_collateral_four);
        ImageView ivFive = helper.getView(R.id.iv_collateral_five);
        ImageView ivSix = helper.getView(R.id.iv_collateral_six);
        ImageView ivSeven = helper.getView(R.id.iv_collateral_seven);
        ImageView ivEight = helper.getView(R.id.iv_collateral_eight);
        ImageView ivOneDel = helper.getView(R.id.iv_collateral_one_del);
        ImageView ivTwoDel = helper.getView(R.id.iv_collateral_two_del);
        ImageView ivThreeDel = helper.getView(R.id.iv_collateral_three_del);
        ImageView ivFourDel = helper.getView(R.id.iv_collateral_four_del);
        ImageView ivFiveDel = helper.getView(R.id.iv_collateral_five_del);
        ImageView ivSixDel = helper.getView(R.id.iv_collateral_six_del);
        ImageView ivSevenDel = helper.getView(R.id.iv_collateral_seven_del);
        ImageView ivEightDel = helper.getView(R.id.iv_collateral_eight_del);
        if (item.getMor_page1() != null && !item.getMor_page1().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getMor_page1()).apply(new RequestOptions()).into(ivOne);
            ivOneDel.setVisibility(View.VISIBLE);
        }else {
            ivOne.setImageResource(isLook?R.drawable.shape_white:R.drawable.bg_id);

            ivOneDel.setVisibility(View.GONE);
        }
        if (item.getMor_page2() != null && !item.getMor_page2().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getMor_page2()).apply(new RequestOptions()).into(ivTwo);
            ivTwoDel.setVisibility(View.VISIBLE);
        }else {
            ivTwo.setImageResource(isLook?R.drawable.shape_white:R.drawable.bg_id);
            ivTwoDel.setVisibility(View.GONE);
        }
        if (item.getMor_page3() != null && !item.getMor_page3().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getMor_page3()).apply(new RequestOptions()).into(ivThree);
            ivThreeDel.setVisibility(View.VISIBLE);
        }else {
            ivThree.setImageResource(isLook?R.drawable.shape_white:R.drawable.bg_id);
            ivThreeDel.setVisibility(View.GONE);
        }
        if (item.getMor_page4() != null && !item.getMor_page4().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getMor_page4()).apply(new RequestOptions()).into(ivFour);
            ivFourDel.setVisibility(View.VISIBLE);
        }else {
            ivFour.setImageResource(isLook?R.drawable.shape_white:R.drawable.bg_id);
            ivFourDel.setVisibility(View.GONE);
        }
        if (item.getMor_page5() != null && !item.getMor_page5().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getMor_page5()).apply(new RequestOptions()).into(ivFive);
            ivFiveDel.setVisibility(View.VISIBLE);
        }else {
            ivFive.setImageResource(isLook?R.drawable.shape_white:R.drawable.bg_id);
            ivFiveDel.setVisibility(View.GONE);
        }
        if (item.getMor_page6() != null && !item.getMor_page6().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getMor_page6()).apply(new RequestOptions()).into(ivSix);
            ivSixDel.setVisibility(View.VISIBLE);
        }else {
            ivSix.setImageResource(isLook?R.drawable.shape_white:R.drawable.bg_id);
            ivSixDel.setVisibility(View.GONE);
        }
        if (item.getMor_page7() != null && !item.getMor_page7().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getMor_page7()).apply(new RequestOptions()).into(ivSeven);
            ivSevenDel.setVisibility(View.VISIBLE);
        }else {
            ivSeven.setImageResource(isLook?R.drawable.shape_white:R.drawable.bg_id);
            ivSevenDel.setVisibility(View.GONE);
        }
        if (item.getMor_page8() != null && !item.getMor_page8().equals("")) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getMor_page8()).apply(new RequestOptions()).into(ivEight);
            ivEightDel.setVisibility(View.VISIBLE);
        }else {
            ivEight.setImageResource(isLook?R.drawable.shape_white:R.drawable.bg_id);
            ivEightDel.setVisibility(View.GONE);
        }
        if (isLook){
            ivOneDel.setVisibility(View.GONE);
            ivTwoDel.setVisibility(View.GONE);
            ivThreeDel.setVisibility(View.GONE);
            ivFourDel.setVisibility(View.GONE);
            ivFiveDel.setVisibility(View.GONE);
            ivSixDel.setVisibility(View.GONE);
            ivSevenDel.setVisibility(View.GONE);
            ivEightDel.setVisibility(View.GONE);
            helper.setVisible(R.id.tv_delete, false);
        }
    }



    //  添加数据
    public void addData(int position, GetCollateralImgBean.DataBean str) {
        data.add(position, str);
        //添加动画
        notifyItemInserted(position);
    }

    //  删除数据
    public void removeData(int position) {

        data.remove(position);
        //删除动画
        notifyDataSetChanged();
    }

    public boolean isCanAdd(){
        boolean isCanAdd = false;
        for (GetCollateralImgBean.DataBean dataBean :data){
            if (!TextUtils.isEmpty(dataBean.getMor_page1() )
                    || !TextUtils.isEmpty(dataBean.getMor_page2())
                    || !TextUtils.isEmpty(dataBean.getMor_page3())
                    || !TextUtils.isEmpty(dataBean.getMor_page4())
                    || !TextUtils.isEmpty(dataBean.getMor_page5())
                    || !TextUtils.isEmpty(dataBean.getMor_page6())
                    || !TextUtils.isEmpty(dataBean.getMor_page7())
                    || !TextUtils.isEmpty(dataBean.getMor_page8())){
                isCanAdd = true;
                break;
            }
        }
        return isCanAdd;

    }
    public String getLoadString(){
        List<Map<String, String>> listMap = new ArrayList<>();
        for (int i=0;i<data.size();i++){
            GetCollateralImgBean.DataBean dataBean  = data.get(i);
            Map<String, String> map = new HashMap<>();
            map.put("mor_userid", (String) SPUtil.get(mContext, IConstants.USER_ID, ""));
            map.put("mor_proid", dataBean.getMor_proid()+"");
            map.put("mor_type", "抵押物"+(i+1));
            map.put("mor_page1",dataBean.getMor_page1() == null?"":dataBean.getMor_page1());
            map.put("mor_page2",dataBean.getMor_page2() == null?"":dataBean.getMor_page2());
            map.put("mor_page3",dataBean.getMor_page3() == null?"":dataBean.getMor_page3());
            map.put("mor_page4",dataBean.getMor_page4() == null?"":dataBean.getMor_page4());
            map.put("mor_page5",dataBean.getMor_page5() == null?"":dataBean.getMor_page5());
            map.put("mor_page6",dataBean.getMor_page6() == null?"":dataBean.getMor_page6());
            map.put("mor_page7",dataBean.getMor_page7() == null?"":dataBean.getMor_page7());
            map.put("mor_page8",dataBean.getMor_page8() == null?"":dataBean.getMor_page8());
            listMap.add(map);
        }
        Gson g = new Gson();
        String jsonString = g.toJson(listMap);
        Log.e("shangchuanshuju",jsonString);
        return jsonString;

    }
}
