package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.MyInfoIdAdapter;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.base.BaseView;
import com.example.administrator.myapplication.bean.SelectMyInfoBean;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.LogUtils;
import com.gyf.barlibrary.ImmersionBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NullLayoutActivity extends BaseActivity {

    @BindView(R.id.tv_zi_liao_top)
    TopView tvZiLiaoTop;
    @BindView(R.id.bt_upload_data)
    Button btUploadData;
    @BindView(R.id.rv_my_info_id)
    RecyclerView rvMyInfoId;

    private List<SelectMyInfoBean.DataBeanX.CardBean> selectMyInfoBean;
    private MyInfoIdAdapter myInfoIdAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_null_layout;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvZiLiaoTop);

        selectMyInfoBean = (List<SelectMyInfoBean.DataBeanX.CardBean>) getIntent().getSerializableExtra("selectMyInfoBean");
        //设置RecyclerView方向和是否反转
        LinearLayoutManager NotUseList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMyInfoId.setLayoutManager(NotUseList);
        rvMyInfoId.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvMyInfoId.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
//        myInfoIdAdapter = new MyInfoIdAdapter(selectMyInfoBean);
        rvMyInfoId.setAdapter(myInfoIdAdapter);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.bt_upload_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_upload_data:
                startActivity(new Intent(this, UploadDataActivity.class));
                break;
        }
    }
}
