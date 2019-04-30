package com.example.administrator.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.AssessmentReportAdapter;
import com.example.administrator.myapplication.adapter.BankAdapter;
import com.example.administrator.myapplication.adapter.CollateralAdapter;
import com.example.administrator.myapplication.adapter.CreditReportAdapter;
import com.example.administrator.myapplication.adapter.EnterpriseInformationAdapter;
import com.example.administrator.myapplication.adapter.IdAdapter;
import com.example.administrator.myapplication.adapter.MarriageCertificateAdapter;
import com.example.administrator.myapplication.adapter.MultipleAdapter;
import com.example.administrator.myapplication.adapter.ProductionAdjustmentAdapter;
import com.example.administrator.myapplication.adapter.PropertyOwnershipCertificateAdapter;
import com.example.administrator.myapplication.adapter.SupplementaryAdapter;
import com.example.administrator.myapplication.base.BaseActivity;
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
import com.example.administrator.myapplication.bean.GetProAdjustBean;
import com.example.administrator.myapplication.bean.SupplementaryBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.LookDataContract;
import com.example.administrator.myapplication.presenter.LookDataPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.SPUtil;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
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
    private View emptyView;

    public static void launch(Activity activity, int type, String proid) {
        Intent intent = new Intent(activity, NullLayoutActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("proid", proid);
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
        type = getIntent().getIntExtra("type", -1);
        proid = getIntent().getStringExtra("proid");

        LinearLayoutManager NotUseList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMyInfoId.setLayoutManager(NotUseList);
        rvMyInfoId.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvMyInfoId.setItemAnimator(new DefaultItemAnimator()); //默认动画

        emptyView = View.inflate(this,R.layout.null_data,null);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
        map.put("proid", proid);
        switch (type) {
            case 0:
                getPresenter().getIdCardImage(map, true, true);
                break;
            case 1:
                getPresenter().getMarryCardImage(map, true, true);
                break;
            case 2:
                getPresenter().getAccountBookImg(map, true, true);
                break;
            case 3:
                getPresenter().getDeedImg(map, true, true);
                break;
            case 4:
                getPresenter().getPawnImg(map, true, true);
                break;
            case 5:
                getPresenter().getCompanyInformationImg(map, true, true);
                break;
            case 6:
                getPresenter().getCreditReportImg(map, true, true);
                break;
            case 7:
                getPresenter().getBankWaterImg(map, true, true);
                break;
            case 8:
                getPresenter().getEvaluationReportImg(map, true, true);
                break;
            case 9:
                getPresenter().getProductionAdjustmentImg(map, true, true);
                break;
            case 10:
                getPresenter().getSupplementaryMaterialImg(map, true, true);
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
        final List<GetIdImgBean.DataBean> dataList = data.getData();

        IdAdapter idAdapter = new IdAdapter(dataList, true);
        rvMyInfoId.setAdapter(idAdapter);
        idAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_id_one:
                        BigImageActivity.launch(NullLayoutActivity.this, dataList.get(position).getCard_positive());
                        break;
                    case R.id.iv_id_two:
                        BigImageActivity.launch(NullLayoutActivity.this, dataList.get(position).getCard_negative());
                        break;
                }
            }
        });
        idAdapter.setEmptyView(emptyView);
    }

    @Override
    public void resultMarryCardImage(GetMarryImgBean data) {
         final List<GetMarryImgBean.DataBean> list = data.getData();
         MarriageCertificateAdapter marriageCertificateAdapter= new MarriageCertificateAdapter(list,true);
         rvMyInfoId.setAdapter(marriageCertificateAdapter);
            marriageCertificateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                   case R.id.iv_marriage_certificate_one:
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getMar_page1());
                        break;
                    case R.id.iv_marriage_certificate_two:
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getMar_page2());
                        break;
                    case R.id.iv_marriage_certificate_three:
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getMar_page3());
                        break;

                }
            }
        });
        marriageCertificateAdapter.setEmptyView(emptyView);
    }

    @Override
    public void resultAccountBookImg(GetHouseholdRegistrationBookBean data) {
        final List<GetHouseholdRegistrationBookBean.DataBeanX> mDatas = data.getData();
        MultipleAdapter mGrowthValueAdapter = new MultipleAdapter(this, mDatas,true);
        rvMyInfoId.setAdapter(mGrowthValueAdapter);
        mGrowthValueAdapter.setEmptyView(emptyView);

    }

    @Override
    public void resultDeedImg(GetHouseImgBean data) {
         final List<GetHouseImgBean.DataBean> list = data.getData();
        PropertyOwnershipCertificateAdapter propertyOwnershipCertificateAdapter = new  PropertyOwnershipCertificateAdapter(list,true);
        rvMyInfoId.setAdapter(propertyOwnershipCertificateAdapter);
        propertyOwnershipCertificateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                   case R.id.iv_property_ownership_certificate_one:
                       if (TextUtils.isEmpty(list.get(position).getEst_page1()))
                       BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getEst_page1());
                        break;
                    case R.id.iv_property_ownership_certificate_two:
                        if (TextUtils.isEmpty(list.get(position).getEst_page2()))
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getEst_page2());
                        break;
                    case R.id.iv_property_ownership_certificate_three:
                        if (TextUtils.isEmpty(list.get(position).getEst_page3()))
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getEst_page3());
                        break;
                    case R.id.iv_property_ownership_certificate_four:
                        if (TextUtils.isEmpty(list.get(position).getEst_page4()))
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getEst_page4());
                        break;
                    case R.id.iv_property_ownership_certificate_five:
                        if (TextUtils.isEmpty(list.get(position).getEst_page5()))
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getEst_page5());
                        break;
                }
            }
        });
        propertyOwnershipCertificateAdapter.setEmptyView(emptyView);

    }

    @Override
    public void resultPawnImg(GetCollateralImgBean data) {
         final List<GetCollateralImgBean.DataBean> list = data.getData();
         CollateralAdapter collateralAdapter = new CollateralAdapter(list,true);
        rvMyInfoId.setAdapter(collateralAdapter);
        collateralAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_collateral_one:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page1()))
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getMor_page1());
                        break;
                    case R.id.iv_collateral_two:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page2()))
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getMor_page2());
                        break;
                    case R.id.iv_collateral_three:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page3()))
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getMor_page3());
                        break;
                    case R.id.iv_collateral_four:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page4()))
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getMor_page4());
                        break;
                    case R.id.iv_collateral_five:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page5()))
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getMor_page5());
                        break;
                    case R.id.iv_collateral_six:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page6()))
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getMor_page6());
                        break;
                    case R.id.iv_collateral_seven:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page7()))
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getMor_page7());
                        break;
                    case R.id.iv_collateral_eight:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page1()))
                        BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getMor_page8());
                        break;

                }
            }
        });
        collateralAdapter.setEmptyView(emptyView);

    }

    @Override
    public void resultCompanyInformationImg(GetCompanyInform data) {
        List<GetCompanyInform.DataBean.StroBean.StroSecondBean> multipleAdapterList = new ArrayList<>();


        GetCompanyInform.DataBean data1 = data.data;
        GetCompanyInform.DataBean.StroBean stro = data1.stro;
        if (stro._$1.data != null &&  stro._$1.data.size() != 0){
            multipleAdapterList.add(stro._$1);
        }if (stro._$2.data != null &&  stro._$2.data.size() != 0){
            multipleAdapterList.add(stro._$2);
        }if (stro._$3.data != null &&  stro._$3.data.size() != 0){
            multipleAdapterList.add(stro._$3);
        }if (stro._$4.data != null &&  stro._$4.data.size() != 0){
            multipleAdapterList.add(stro._$4);
        }if (stro._$5.data != null &&  stro._$5.data.size() != 0){
            multipleAdapterList.add(stro._$5);
        }

        EnterpriseInformationAdapter mGrowthValueAdapter  = new EnterpriseInformationAdapter(this,multipleAdapterList,true);
        rvMyInfoId.setAdapter(mGrowthValueAdapter);
        mGrowthValueAdapter.setEmptyView(emptyView);
    }

    @Override
    public void resultCreditReportImg(GetCreditReportBean data) {
         List<GetCreditReportBean.DataBeanX> multipleAdapterList = data.data;
        CreditReportAdapter mGrowthValueAdapter = new CreditReportAdapter(this, multipleAdapterList,true);
        rvMyInfoId.setAdapter(mGrowthValueAdapter);
        mGrowthValueAdapter.setEmptyView(emptyView);

    }

    @Override
    public void resultBankWaterImg(GetBankImgBean data) {
        List<GetBankImgBean.DataBean> list = new ArrayList<>();
        list.addAll(data.data);
        BankAdapter bankAdapter = new BankAdapter(this,list,true);
        rvMyInfoId.setAdapter(bankAdapter);
        bankAdapter.setEmptyView(emptyView);

    }

    @Override
    public void resultEvaluationReportImg(GetAssessmentImgBean data) {
        GridLayoutManager NotUseList = new GridLayoutManager(this, 4);
        rvMyInfoId.setLayoutManager(NotUseList);
        rvMyInfoId.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvMyInfoId.setItemAnimator(new DefaultItemAnimator()); //默认动画
        List<GetAssessmentImgBean.DataBean> list = new ArrayList<>();
        list.addAll(data.getData());
        AssessmentReportAdapter assessmentReportAdapter = new AssessmentReportAdapter(list,true);
        rvMyInfoId.setAdapter(assessmentReportAdapter);
        assessmentReportAdapter.setEmptyView(emptyView);

        assessmentReportAdapter.addHeaderView(View.inflate(this,R.layout.header_assessment_report_look,null));


    }

    @Override
    public void resultProductionAdjustmentImg(String data) {
        List<GetProAdjustBean.DataBeanFirst> mDatas = new ArrayList<>();
        Gson gson = new Gson();
        GetProAdjustBean getProAdjustBean = gson.fromJson(data, GetProAdjustBean.class);
        mDatas.addAll(getProAdjustBean.data);
        ProductionAdjustmentAdapter productionAdjustmentAdapter = new ProductionAdjustmentAdapter(this,mDatas,true);
        rvMyInfoId.setAdapter(productionAdjustmentAdapter);
        productionAdjustmentAdapter.setEmptyView(emptyView);

    }

    @Override
    public void resultSupplementaryMaterialImg(GetAddSupplementaryBean data) {
        GridLayoutManager NotUseList = new GridLayoutManager(this, 2);
        rvMyInfoId.setLayoutManager(NotUseList);
        rvMyInfoId.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvMyInfoId.setItemAnimator(new DefaultItemAnimator()); //默认动画

        SupplementaryAdapter mGrowthValueAdapter;
         final List<SupplementaryBean> list = new ArrayList<>();
        //初始化数据
        list.addAll(data.data);
        mGrowthValueAdapter = new SupplementaryAdapter(proid+"",list);
        rvMyInfoId.setAdapter(mGrowthValueAdapter);
        mGrowthValueAdapter.setEmptyView(emptyView);
        mGrowthValueAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_household_registration:
                            BigImageActivity.launch(NullLayoutActivity.this,list.get(position).getImgPath());
                        break;
                }
            }
        });


    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
