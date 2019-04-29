package com.example.administrator.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.IdAdapter;
import com.example.administrator.myapplication.adapter.MyInfoIdAdapter;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.base.BaseView;
import com.example.administrator.myapplication.bean.GetAddSupplementaryBean;
import com.example.administrator.myapplication.bean.GetAssessmentImgBean;
import com.example.administrator.myapplication.bean.GetBankImgBean;
import com.example.administrator.myapplication.bean.GetCollateralImgBean;
import com.example.administrator.myapplication.bean.GetCompanyInform;
import com.example.administrator.myapplication.bean.GetCreditReportBean;
import com.example.administrator.myapplication.bean.GetHouseImgBean;
import com.example.administrator.myapplication.bean.GetHouseholdRegistrationBookBean;
import com.example.administrator.myapplication.bean.GetIdImgBean;
import com.example.administrator.myapplication.bean.GetMarryImgBean;
import com.example.administrator.myapplication.bean.SelectMyInfoBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.IdContract;
import com.example.administrator.myapplication.contract.LookDataContract;
import com.example.administrator.myapplication.presenter.LookDataPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.LogUtils;
import com.example.administrator.myapplication.utils.SPUtil;
import com.gyf.barlibrary.ImmersionBar;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class NullLayoutActivity extends BaseActivity<LookDataContract.View, LookDataContract.Presenter> implements LookDataContract.View {

    @BindView(R.id.tv_zi_liao_top)
    TopView tvZiLiaoTop;
    @BindView(R.id.bt_upload_data)
    Button btUploadData;
    @BindView(R.id.rv_my_info_id)
    RecyclerView rvMyInfoId;

//    private List<SelectMyInfoBean.DataBeanX.CardBean> selectMyInfoBean;
//    private MyInfoIdAdapter myInfoIdAdapter;
    private int type;
    private String proid;

    public static void launch(Activity activity,int type,String proid){
        Intent intent = new Intent(activity, NullLayoutActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("proid",proid);
        activity.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_null_layout;
    }

    @Override
    public LookDataPresenter createPresenter() {
        return new LookDataPresenter(this);
    }

    @Override
    public LookDataContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvZiLiaoTop);
        type = getIntent().getIntExtra("type",-1);
        proid = getIntent().getStringExtra("proid");

//        selectMyInfoBean = (List<SelectMyInfoBean.DataBeanX.CardBean>) getIntent().getSerializableExtra("selectMyInfoBean");
//        //设置RecyclerView方向和是否反转
//        LinearLayoutManager NotUseList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        rvMyInfoId.setLayoutManager(NotUseList);
//        rvMyInfoId.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
//        rvMyInfoId.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
//        myInfoIdAdapter = new MyInfoIdAdapter(selectMyInfoBean);
//        rvMyInfoId.setAdapter(myInfoIdAdapter);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
        map.put("proid", proid);
        switch (type){
            case 0:
                getPresenter().getIdCardImage(map,true,true);
                break;
        }

    }

    @OnClick({R.id.bt_upload_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_upload_data:
                startActivity(new Intent(this, UploadDataActivity.class));
                break;
        }
    }

    @Override
    public void resultIdCard(GetIdImgBean data) {
        //设置RecyclerView方向和是否反转
        LinearLayoutManager NotUseList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMyInfoId.setLayoutManager(NotUseList);
        rvMyInfoId.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvMyInfoId.setItemAnimator(new DefaultItemAnimator()); //默认动画
        IdAdapter idAdapter = new IdAdapter(data.getData());
        rvMyInfoId.setAdapter(idAdapter);


    }

    @Override
    public void resultMarryCardImage(GetMarryImgBean data) {

    }

    @Override
    public void resultAccountBookImg(GetHouseholdRegistrationBookBean data) {

    }

    @Override
    public void resultDeedImg(GetHouseImgBean data) {

    }

    @Override
    public void resultPawnImg(GetCollateralImgBean data) {

    }

    @Override
    public void resultCompanyInformationImg(GetCompanyInform data) {

    }

    @Override
    public void resultCreditReportImg(GetCreditReportBean data) {

    }

    @Override
    public void resultBankWaterImg(GetBankImgBean data) {

    }

    @Override
    public void resultEvaluationReportImg(GetAssessmentImgBean data) {

    }

    @Override
    public void resultProductionAdjustmentImg(String data) {

    }

    @Override
    public void resultSupplementaryMaterialImg(GetAddSupplementaryBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
