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
import com.example.administrator.myapplication.bean.GetIdImgBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.config.UrlConfig;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.SPUtil;
import com.example.administrator.myapplication.utils.ToastUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdAdapter extends BaseQuickAdapter<GetIdImgBean.DataBean, BaseViewHolder> {
    private List<GetIdImgBean.DataBean> data;

    //编辑
    public IdAdapter(@Nullable List<GetIdImgBean.DataBean> data) {
        super(R.layout.adapter_id_item, data);
        this.data = data;
    }

    //编辑
    public boolean isLook = false;
    public IdAdapter(@Nullable List<GetIdImgBean.DataBean> data,boolean isLook) {
        super(R.layout.adapter_id_item, data);
        this.data = data;
        this.isLook = isLook;
    }

    @Override
    protected void convert(BaseViewHolder helper, final GetIdImgBean.DataBean item) {
        final ImageView  ivOne = helper.getView(R.id.iv_id_one);
        final ImageView ivTwo = helper.getView(R.id.iv_id_two);
        final ImageView ivOneDel = helper.getView(R.id.iv_id_one_del);
        final ImageView  ivTwoDel = helper.getView(R.id.iv_id_two_del);

        if (!TextUtils.isEmpty(item.getCard_positive())) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getCard_positive()).apply(new RequestOptions()).into(ivOne);
            ivOneDel.setVisibility(View.VISIBLE);
        }else {
            ivOneDel.setVisibility(View.GONE);
            ivOne.setImageResource(R.drawable.bg_id);
        }
        if (!TextUtils.isEmpty(item.getCard_negative())) {
            Glide.with(ApplicationUtil.getContext()).load(UrlConfig.BASE_URL + item.getCard_negative()).apply(new RequestOptions()).into(ivTwo);
            ivTwoDel.setVisibility(View.VISIBLE);
        }else {
            ivTwoDel.setVisibility(View.GONE);
            ivTwo.setImageResource(R.drawable.bg_id);
        }

        helper.addOnClickListener(R.id.tv_delete)
                .addOnClickListener(R.id.iv_id_one)
                .addOnClickListener(R.id.iv_id_two)
                .addOnClickListener(R.id.iv_id_one_del)
                .addOnClickListener(R.id.iv_id_two_del);

        ivOneDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setCard_positive("");
                notifyDataSetChanged();
            }
        });

        ivTwoDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setCard_negative("");
                notifyDataSetChanged();
            }
        });
        helper.setText(R.id.tv_my_identity_card,(item.getCard_type())+"身份证");
        int position = helper.getLayoutPosition();
        if (position == 0) {
            helper.setVisible(R.id.tv_delete, false);
        } else {
            helper.setVisible(R.id.tv_delete, true);
        }
        if (isLook){
            ivOneDel.setVisibility(View.GONE);
            ivTwoDel.setVisibility(View.GONE);
            helper.setVisible(R.id.tv_delete, false);
        }


    }
    //  添加数据
    public void addData(int position, GetIdImgBean.DataBean dataBean) {
        data.add(position, dataBean);
        //添加动画
        notifyDataSetChanged();
    }

    //  删除数据
    public void removeData(int position) {
        data.remove(position);
        //删除动画
        notifyItemRemoved(position);
    }

    public boolean isCanAdd(){
        boolean isCanAdd = true;
        for (GetIdImgBean.DataBean dataBean :data){
            if (TextUtils.isEmpty(dataBean.getCard_positive())){
                ToastUtil.showShortToast("请选择"+(dataBean.getCard_type())+"身份证正面!");
                isCanAdd = false;
            }else if (TextUtils.isEmpty(dataBean.getCard_negative())){
                ToastUtil.showShortToast("请选择"+(dataBean.getCard_type())+"身份证反面!");
                isCanAdd = false;
            }
        }
        return isCanAdd;

    }
    public String getLoadString(){
        List<Map<String, String>> listMap = new ArrayList<>();
        for (GetIdImgBean.DataBean dataBean :data){
                Map<String, String> map = new HashMap<>();
                map.put("card_userid", (String) SPUtil.get(mContext, IConstants.USER_ID, ""));
                map.put("card_proid", dataBean.getCard_proid()+"");
                map.put("card_type", dataBean.getCard_type());
                map.put("card_positive",dataBean.getCard_positive());
                map.put("card_negative",dataBean.getCard_negative());
                listMap.add(map);
        }
        Gson g = new Gson();
        String jsonString = g.toJson(listMap);
        return jsonString;

    }
}
