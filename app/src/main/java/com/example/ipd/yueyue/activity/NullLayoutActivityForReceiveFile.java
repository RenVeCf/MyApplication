package com.example.ipd.yueyue.activity;

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
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.adapter.OtherAssessmentReportAdapter;
import com.example.ipd.yueyue.adapter.OtherBankAdapter;
import com.example.ipd.yueyue.adapter.OtherCollateralAdapter;
import com.example.ipd.yueyue.adapter.OtherCreditReportAdapter;
import com.example.ipd.yueyue.adapter.OtherEnterpriseInformationAdapter;
import com.example.ipd.yueyue.adapter.OtherMultipleAdapter;
import com.example.ipd.yueyue.adapter.OtherProductionAdjustmentAdapter;
import com.example.ipd.yueyue.adapter.OtherPropertyOwnershipCertificateAdapter;
import com.example.ipd.yueyue.adapter.OtherReceiveIdAdapter;
import com.example.ipd.yueyue.adapter.OtherReceiverMarryAdapter;
import com.example.ipd.yueyue.adapter.OtherSupplementaryAdapter;
import com.example.ipd.yueyue.base.BaseActivity;
import com.example.ipd.yueyue.bean.DocumentsReceivedBean;
import com.example.ipd.yueyue.bean.LockedFilesAddBean;
import com.example.ipd.yueyue.bean.ReceiveDownloadBean;
import com.example.ipd.yueyue.bean.ReceiveFollowBean;
import com.example.ipd.yueyue.bean.ReceiveForwardBean;
import com.example.ipd.yueyue.bean.SelectOtherBean;
import com.example.ipd.yueyue.common.view.TopView;
import com.example.ipd.yueyue.contract.DocumentsReceivedContract;
import com.example.ipd.yueyue.presenter.DocumentsReceivedPresenter;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.gyf.barlibrary.ImmersionBar;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class NullLayoutActivityForReceiveFile extends BaseActivity<DocumentsReceivedContract.View, DocumentsReceivedContract.Presenter> implements DocumentsReceivedContract.View {

    @BindView(R.id.tv_zi_liao_top)
    TopView tvZiLiaoTop;
    @BindView(R.id.bt_upload_data)
    Button btUploadData;
    @BindView(R.id.rv_my_info_id)
    RecyclerView rvMyInfoId;

    //    private List<SelectMyInfoBean.DataBeanX.CardBean> selectMyInfoBean;
//    private MyInfoIdAdapter myInfoIdAdapter;
    private int type;
    private String forId;
    private View emptyView;

    public static void launch(Activity activity, int type, String forId) {
        Intent intent = new Intent(activity, NullLayoutActivityForReceiveFile.class);
        intent.putExtra("type", type);
        intent.putExtra("forId", forId);
        activity.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_null_layout;
    }

    @Override
    public DocumentsReceivedContract.Presenter createPresenter() {
        return new DocumentsReceivedPresenter(this);
    }

    @Override
    public DocumentsReceivedContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvZiLiaoTop);
        type = getIntent().getIntExtra("type", -1);
        forId = getIntent().getStringExtra("forId");

        LinearLayoutManager NotUseList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMyInfoId.setLayoutManager(NotUseList);
        rvMyInfoId.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvMyInfoId.setItemAnimator(new DefaultItemAnimator()); //默认动画

        emptyView = View.inflate(this, R.layout.null_data, null);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        TreeMap<String, String> selectMyInfoMap = new TreeMap<>();
        selectMyInfoMap.put("for_id", forId);
        getPresenter().getSelectOtherInfo(selectMyInfoMap, true, false);

    }

    @OnClick({R.id.bt_upload_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_upload_data:
                startActivity(new Intent(this, UploadDataActivity.class));
                break;
        }
    }

    public void resultIdCard(SelectOtherBean data) {

        final List<SelectOtherBean.DataBeanXXXXX.CardBean> dataList = data.getData().getCard();

        OtherReceiveIdAdapter idAdapter = new OtherReceiveIdAdapter(dataList, true);
        rvMyInfoId.setAdapter(idAdapter);
        idAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_id_one:
                        BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, dataList.get(position).getCard_positive());
                        break;
                    case R.id.iv_id_two:
                        BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, dataList.get(position).getCard_negative());
                        break;
                }
            }
        });
        idAdapter.setEmptyView(emptyView);
    }

    public void resultMarryCardImage(SelectOtherBean data) {
        final List<SelectOtherBean.DataBeanXXXXX.MarryBean> list = data.getData().getMarry();
        OtherReceiverMarryAdapter marriageCertificateAdapter = new OtherReceiverMarryAdapter(list, true);
        rvMyInfoId.setAdapter(marriageCertificateAdapter);
        marriageCertificateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_marriage_certificate_one:
                        BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getMar_page1());
                        break;
                    case R.id.iv_marriage_certificate_two:
                        BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getMar_page2());
                        break;
                    case R.id.iv_marriage_certificate_three:
                        BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getMar_page3());
                        break;

                }
            }
        });
        marriageCertificateAdapter.setEmptyView(emptyView);
    }

    //
    public void resultAccountBookImg(SelectOtherBean data) {
        final List<SelectOtherBean.DataBeanXXXXX.AccountBean> mDatas = data.getData().getAccount();
        OtherMultipleAdapter mGrowthValueAdapter = new OtherMultipleAdapter(this, mDatas, true);
        rvMyInfoId.setAdapter(mGrowthValueAdapter);
        mGrowthValueAdapter.setEmptyView(emptyView);

    }

    //
    public void resultDeedImg(SelectOtherBean data) {
        final List<SelectOtherBean.DataBeanXXXXX.EstateBean> list = data.getData().getEstate();
        OtherPropertyOwnershipCertificateAdapter propertyOwnershipCertificateAdapter = new OtherPropertyOwnershipCertificateAdapter(list, true);
        rvMyInfoId.setAdapter(propertyOwnershipCertificateAdapter);
        propertyOwnershipCertificateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_property_ownership_certificate_one:
                        if (!TextUtils.isEmpty(list.get(position).getEst_page1()))
                            BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getEst_page1());
                        break;
                    case R.id.iv_property_ownership_certificate_two:
                        if (!TextUtils.isEmpty(list.get(position).getEst_page2()))
                            BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getEst_page2());
                        break;
                    case R.id.iv_property_ownership_certificate_three:
                        if (!TextUtils.isEmpty(list.get(position).getEst_page3()))
                            BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getEst_page3());
                        break;
                    case R.id.iv_property_ownership_certificate_four:
                        if (!TextUtils.isEmpty(list.get(position).getEst_page4()))
                            BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getEst_page4());
                        break;
                    case R.id.iv_property_ownership_certificate_five:
                        if (!TextUtils.isEmpty(list.get(position).getEst_page5()))
                            BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getEst_page5());
                        break;
                }
            }
        });
        propertyOwnershipCertificateAdapter.setEmptyView(emptyView);

    }

    public void resultPawnImg(SelectOtherBean data) {
        final List<SelectOtherBean.DataBeanXXXXX.MortgageBean> list = data.getData().getMortgage();
        OtherCollateralAdapter collateralAdapter = new OtherCollateralAdapter(list, true);
        rvMyInfoId.setAdapter(collateralAdapter);
        collateralAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_collateral_one:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page1()))
                            BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getMor_page1());
                        break;
                    case R.id.iv_collateral_two:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page2()))
                            BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getMor_page2());
                        break;
                    case R.id.iv_collateral_three:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page3()))
                            BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getMor_page3());
                        break;
                    case R.id.iv_collateral_four:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page4()))
                            BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getMor_page4());
                        break;
                    case R.id.iv_collateral_five:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page5()))
                            BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getMor_page5());
                        break;
                    case R.id.iv_collateral_six:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page6()))
                            BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getMor_page6());
                        break;
                    case R.id.iv_collateral_seven:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page7()))
                            BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getMor_page7());
                        break;
                    case R.id.iv_collateral_eight:
                        if (!TextUtils.isEmpty(list.get(position).getMor_page8()))
                            BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getMor_page8());
                        break;

                }
            }
        });
        collateralAdapter.setEmptyView(emptyView);

    }

    //
//    @Override
    public void resultCompanyInformationImg(SelectOtherBean data) {
        List<SelectOtherBean.DataBeanXXXXX.LicenseBean> license = data.getData().getLicense();
        OtherEnterpriseInformationAdapter mGrowthValueAdapter = new OtherEnterpriseInformationAdapter(this, license, true);
        rvMyInfoId.setAdapter(mGrowthValueAdapter);
        mGrowthValueAdapter.setEmptyView(emptyView);
    }

    //
//    @Override
    public void resultCreditReportImg(SelectOtherBean data) {
        List<SelectOtherBean.DataBeanXXXXX.CreditBean> multipleAdapterList = data.getData().getCredit();
        OtherCreditReportAdapter mGrowthValueAdapter = new OtherCreditReportAdapter(this, multipleAdapterList, true);
        rvMyInfoId.setAdapter(mGrowthValueAdapter);
        mGrowthValueAdapter.setEmptyView(emptyView);
    }

    //
//    @Override
    public void resultBankWaterImg(SelectOtherBean data) {
        List<SelectOtherBean.DataBeanXXXXX.BankBean> list = data.getData().getBank();
        OtherBankAdapter bankAdapter = new OtherBankAdapter(this, list, true);
        rvMyInfoId.setAdapter(bankAdapter);
        bankAdapter.setEmptyView(emptyView);

    }

    //
//    @Override
    public void resultEvaluationReportImg(SelectOtherBean data) {
        GridLayoutManager NotUseList = new GridLayoutManager(this, 4);
        rvMyInfoId.setLayoutManager(NotUseList);
        rvMyInfoId.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvMyInfoId.setItemAnimator(new DefaultItemAnimator()); //默认动画
        List<SelectOtherBean.DataBeanXXXXX.AssessBean> list = data.getData().getAssess();
        OtherAssessmentReportAdapter assessmentReportAdapter = new OtherAssessmentReportAdapter(list, true);
        rvMyInfoId.setAdapter(assessmentReportAdapter);
        assessmentReportAdapter.setEmptyView(emptyView);
        assessmentReportAdapter.addHeaderView(View.inflate(this, R.layout.header_assessment_report_look, null));
    }

    //
//    @Override
    public void resultProductionAdjustmentImg(SelectOtherBean data) {
        List<SelectOtherBean.DataBeanXXXXX.YieldBean> mDatas = data.getData().getYield();
        OtherProductionAdjustmentAdapter productionAdjustmentAdapter = new OtherProductionAdjustmentAdapter(this, mDatas, true);
        rvMyInfoId.setAdapter(productionAdjustmentAdapter);
        productionAdjustmentAdapter.setEmptyView(emptyView);

    }

    //
//    @Override
    public void resultSupplementaryMaterialImg(SelectOtherBean data) {
        GridLayoutManager NotUseList = new GridLayoutManager(this, 2);
        rvMyInfoId.setLayoutManager(NotUseList);
        rvMyInfoId.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvMyInfoId.setItemAnimator(new DefaultItemAnimator()); //默认动画

        OtherSupplementaryAdapter mGrowthValueAdapter;
        final List<SelectOtherBean.DataBeanXXXXX.FartherBean> list = data.getData().getFarther();
        //初始化数据
        mGrowthValueAdapter = new OtherSupplementaryAdapter(list, true);
        rvMyInfoId.setAdapter(mGrowthValueAdapter);
        mGrowthValueAdapter.setEmptyView(emptyView);
        mGrowthValueAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_household_registration:
                        BigImageActivity.launch(NullLayoutActivityForReceiveFile.this, list.get(position).getFar_imgurl());
                        break;
                }
            }
        });
    }

    @Override
    public void resultDocumentsReceived(DocumentsReceivedBean data) {

    }

    @Override
    public void resultReceiveForward(ReceiveForwardBean data) {

    }

    @Override
    public void resultReceiveDownload(ReceiveDownloadBean data) {

    }

    @Override
    public void resultLockedFilesAdd(LockedFilesAddBean data) {

    }

    @Override
    public void resultReceiveFollow(ReceiveFollowBean data) {

    }

    @Override
    public void resultSelectOtherInfo(SelectOtherBean data) {
        //处理结果
        switch (type) {
            case 0:
                resultIdCard(data);
                break;
            case 1:
                resultMarryCardImage(data);
                break;
            case 2:
                resultAccountBookImg(data);
                break;
            case 3:
                resultDeedImg(data);
                break;
            case 4:
                resultPawnImg(data);
                break;
            case 5:
                resultCompanyInformationImg(data);
                break;
            case 6:
                resultCreditReportImg(data);
                break;
            case 7:
                resultBankWaterImg(data);
                break;
            case 8:
                resultEvaluationReportImg(data);
                break;
            case 9:
                resultProductionAdjustmentImg(data);
                break;
            case 10:
                resultSupplementaryMaterialImg(data);
                break;


        }

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
