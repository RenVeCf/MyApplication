package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.CardInfoBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.CardInfoContract;
import com.example.administrator.myapplication.presenter.CardInfoPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.LogUtils;
import com.gyf.barlibrary.ImmersionBar;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

/**
 * Description ：上传资料
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/4.
 */
public class CardInfoActivity extends BaseActivity<CardInfoContract.View, CardInfoContract.Presenter> implements CardInfoContract.View {

    @BindView(R.id.tv_card_info_top)
    TopView tvCardInfoTop;
    @BindView(R.id.ll_card_info_id_card)
    LinearLayout llCardInfoIdCard;
    @BindView(R.id.ll_card_info_marriage_certificate)
    LinearLayout llCardInfoMarriageCertificate;
    @BindView(R.id.ll_card_info_household_registration_book)
    LinearLayout llCardInfoHouseholdRegistrationBook;
    @BindView(R.id.ll_card_info_property_ownership_certificate)
    LinearLayout llCardInfoPropertyOwnershipCertificate;
    @BindView(R.id.ll_card_info_collateral)
    LinearLayout llCardInfoCollateral;
    @BindView(R.id.ll_card_info_enterprise_information)
    LinearLayout llCardInfoEnterpriseInformation;
    @BindView(R.id.ll_card_info_credit_report)
    LinearLayout llCardInfoCreditReport;
    @BindView(R.id.ll_card_info_bank_running_water)
    LinearLayout llCardInfoBankRunningWater;
    @BindView(R.id.ll_card_info_assessment_report)
    LinearLayout llCardInfoAssessmentReport;
    @BindView(R.id.ll_card_info_production_adjustment)
    LinearLayout llCardInfoProductionAdjustment;
    @BindView(R.id.ll_card_info_supplementary_materials)
    LinearLayout llCardInfoSupplementaryMaterials;
    @BindView(R.id.bt_card_info_confirm)
    Button btCardInfoConfirm;
    @BindView(R.id.tv_card_info_id)
    TextView tvCardInfoId;
    @BindView(R.id.tv_card_info_marriage_certificate)
    TextView tvCardInfoMarriageCertificate;
    @BindView(R.id.tv_card_info_household_registration_book)
    TextView tvCardInfoHouseholdRegistrationBook;
    @BindView(R.id.tv_card_info_ownership_certificate)
    TextView tvCardInfoOwnershipCertificate;
    @BindView(R.id.tv_card_info_collateral)
    TextView tvCardInfoCollateral;
    @BindView(R.id.tv_card_info_enterprise_information)
    TextView tvCardInfoEnterpriseInformation;
    @BindView(R.id.tv_card_info_credit_report)
    TextView tvCardInfoCreditReport;
    @BindView(R.id.tv_card_info_bank_running_water)
    TextView tvCardInfoBankRunningWater;
    @BindView(R.id.tv_card_info_assessment_report)
    TextView tvCardInfoAssessmentReport;
    @BindView(R.id.tv_card_info_production_adjustment)
    TextView tvCardInfoProductionAdjustment;
    @BindView(R.id.tv_card_info_supplementary_materials)
    TextView tvCardInfoSupplementaryMaterials;

    private String proid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_card_info;
    }

    @Override
    public CardInfoContract.Presenter createPresenter() {
        return new CardInfoPresenter(this);
    }

    @Override
    public CardInfoContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvCardInfoTop);
        proid = getIntent().getStringExtra("proid");
        LogUtils.i("proid card = " + proid);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        TreeMap<String, String> cardInfoMap = new TreeMap<>();
        cardInfoMap.put("pro_id", proid);
        getPresenter().cardInfo(cardInfoMap, true, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case IConstants.REQUEST_CODE_99:
                    if (!data.getStringExtra("idCardResult").equals("")) {
                        tvCardInfoId.setVisibility(View.VISIBLE);
                    }
                    break;
                case IConstants.REQUEST_CODE_100:
                    if (!data.getStringExtra("marriageCertificateCardResult").equals("")) {
                        tvCardInfoMarriageCertificate.setVisibility(View.VISIBLE);
                    }
                    break;
                case IConstants.REQUEST_CODE_101:
                    if (!data.getStringExtra("householdRegistrationBookCardResult").equals("")) {
                        tvCardInfoHouseholdRegistrationBook.setVisibility(View.VISIBLE);
                    }
                    break;
                case IConstants.REQUEST_CODE_102:
                    if (!data.getStringExtra("propertyOwnershipCertificateResult").equals("")) {
                        tvCardInfoOwnershipCertificate.setVisibility(View.VISIBLE);
                    }
                    break;
                case IConstants.REQUEST_CODE_103:
                    if (!data.getStringExtra("collateralResult").equals("")) {
                        tvCardInfoCollateral.setVisibility(View.VISIBLE);
                    }
                    break;
                case IConstants.REQUEST_CODE_104:
                    if (!data.getStringExtra("enterpriseResult").equals("")) {
                        tvCardInfoEnterpriseInformation.setVisibility(View.VISIBLE);
                    }
                    break;
                case IConstants.REQUEST_CODE_105:
                    if (!data.getStringExtra("creditReportResult").equals("")) {
                        tvCardInfoCreditReport.setVisibility(View.VISIBLE);
                    }
                    break;
                case IConstants.REQUEST_CODE_106:
                    if (!data.getStringExtra("bankResult").equals("")) {
                        tvCardInfoBankRunningWater.setVisibility(View.VISIBLE);
                    }
                    break;
                case IConstants.REQUEST_CODE_107:
                    if (!data.getStringExtra("assessmentCardResult").equals("")) {
                        tvCardInfoAssessmentReport.setVisibility(View.VISIBLE);
                    }
                    break;
                case IConstants.REQUEST_CODE_108:
                    if (!data.getStringExtra("productionAdjustmentResult").equals("")) {
                        tvCardInfoProductionAdjustment.setVisibility(View.VISIBLE);
                    }
                    break;
                case IConstants.REQUEST_CODE_109:
                    if (!data.getStringExtra("supplementaryResult").equals("")) {
                        tvCardInfoSupplementaryMaterials.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    }

    /*
    列表（全部选择 才可添加）+ 固定   1：身份证 固定2个 2：结婚证 固定3个
    列表 （无限添加）+ 不固定添加   1：户口本 2：产调
     列表 （无限添加）+ 固定   1：房产证 5个 2：抵押物 8个

     固定列表 + 不固定添加  1：企业信息 5行  2：信用报告 5行 3：评估报告 1行
     固定列表 + 不固定添加 +自定义标题  1：补充材料
     无限添加 1：银行流水



     */
    @OnClick({R.id.ll_card_info_id_card, R.id.ll_card_info_marriage_certificate, R.id.ll_card_info_household_registration_book, R.id.ll_card_info_property_ownership_certificate, R.id.ll_card_info_collateral, R.id.ll_card_info_enterprise_information, R.id.ll_card_info_credit_report, R.id.ll_card_info_bank_running_water, R.id.ll_card_info_assessment_report, R.id.ll_card_info_production_adjustment, R.id.ll_card_info_supplementary_materials, R.id.bt_card_info_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_card_info_id_card:
                //身份证
                int flag = 0;
                if (tvCardInfoId.getVisibility() == View.VISIBLE)
                    flag = 1;
                startActivityForResult(new Intent(this, IdActivity.class).putExtra("proidId", proid).putExtra("isAdd", flag), IConstants.REQUEST_CODE_99);
                break;
            case R.id.ll_card_info_marriage_certificate:
                //结婚证
                int flag1 = 0;
                if (tvCardInfoMarriageCertificate.getVisibility() == View.VISIBLE)
                    flag1 = 1;
                startActivityForResult(new Intent(this, MarriageCertificateActivity.class).putExtra("proidId", proid).putExtra("isAdd", flag1), IConstants.REQUEST_CODE_100);
                break;
            case R.id.ll_card_info_household_registration_book:
                //户口本
                int flag2 = 0;
                if (tvCardInfoHouseholdRegistrationBook.getVisibility() == View.VISIBLE)
                    flag2 = 1;
                startActivityForResult(new Intent(this, HouseholdRegistrationBookActivity.class).putExtra("proidId", proid).putExtra("isAdd", flag2), IConstants.REQUEST_CODE_101);
                break;
            //房产证
            case R.id.ll_card_info_property_ownership_certificate:
                int flag3 = 0;
                if (tvCardInfoOwnershipCertificate.getVisibility() == View.VISIBLE)
                    flag3 = 1;
                startActivityForResult(new Intent(this, PropertyOwnershipCertificateActivity.class).putExtra("proidId", proid).putExtra("isAdd", flag3), IConstants.REQUEST_CODE_102);
                break;
            //抵押物
            case R.id.ll_card_info_collateral:
                int flag4 = 0;
                if (tvCardInfoCollateral.getVisibility() == View.VISIBLE)
                    flag4 = 1;
                startActivityForResult(new Intent(this, CollateralActivity.class).putExtra("proidId", proid).putExtra("isAdd", flag4), IConstants.REQUEST_CODE_103);
                break;
            case R.id.ll_card_info_enterprise_information:
                //企业信息
                int flag5 = 0;
                if (tvCardInfoEnterpriseInformation.getVisibility() == View.VISIBLE)
                    flag5 = 1;
                startActivityForResult(new Intent(this, EnterpriseInformationActivity.class).putExtra("proidId", proid).putExtra("isAdd", flag5), IConstants.REQUEST_CODE_104);
                break;
            case R.id.ll_card_info_credit_report:
                //信用报告
                int flag6 = 0;
                if (tvCardInfoCreditReport.getVisibility() == View.VISIBLE)
                    flag6 = 1;
                startActivityForResult(new Intent(this, CreditReportActivity.class).putExtra("proidId", proid).putExtra("isAdd", flag6), IConstants.REQUEST_CODE_105);
                break;
            case R.id.ll_card_info_bank_running_water:
                //银行流水
                int flag7 = 0;
                if (tvCardInfoBankRunningWater.getVisibility() == View.VISIBLE)
                    flag7 = 1;
                startActivityForResult(new Intent(this, BankRunningWaterActivity.class).putExtra("proidId", proid).putExtra("isAdd", flag7), IConstants.REQUEST_CODE_106);
                break;
            //评估报告
            case R.id.ll_card_info_assessment_report:
                int flag8 = 0;
                if (tvCardInfoAssessmentReport.getVisibility() == View.VISIBLE)
                    flag8 = 1;
                startActivityForResult(new Intent(this, AssessmentReportActivity.class).putExtra("proidId", proid).putExtra("isAdd", flag8), IConstants.REQUEST_CODE_107);
                break;
            //产调
            case R.id.ll_card_info_production_adjustment:
                int flag9 = 0;
                if (tvCardInfoProductionAdjustment.getVisibility() == View.VISIBLE)
                    flag9 = 1;
                startActivityForResult(new Intent(this, ProductionAdjustmentActivity.class).putExtra("proidId", proid).putExtra("isAdd", flag9), IConstants.REQUEST_CODE_108);
                break;
            //补充材料
            case R.id.ll_card_info_supplementary_materials:
                int flag10 = 0;
                if (tvCardInfoSupplementaryMaterials.getVisibility() == View.VISIBLE)
                    flag10 = 1;
                startActivityForResult(new Intent(this, SupplementaryActivity.class).putExtra("proidId", proid).putExtra("isAdd", flag10), IConstants.REQUEST_CODE_109);
                break;
            case R.id.bt_card_info_confirm:
                startActivity(new Intent(this, MainActivity.class).putExtra("refresh", "1"));
                break;
        }
    }

    @Override
    public void resultCardInfo(CardInfoBean data) {
        if (data.getData().getCard().equals("true"))
            tvCardInfoId.setVisibility(View.VISIBLE);
        if (data.getData().getMarry().equals("true"))
            tvCardInfoMarriageCertificate.setVisibility(View.VISIBLE);
        if (data.getData().getAccount().equals("true"))
            tvCardInfoHouseholdRegistrationBook.setVisibility(View.VISIBLE);
        if (data.getData().getEstate().equals("true"))
            tvCardInfoOwnershipCertificate.setVisibility(View.VISIBLE);
        if (data.getData().getMortgage().equals("true"))
            tvCardInfoCollateral.setVisibility(View.VISIBLE);
        if (data.getData().getLicense().equals("true"))
            tvCardInfoEnterpriseInformation.setVisibility(View.VISIBLE);
        if (data.getData().getCredit().equals("true"))
            tvCardInfoCreditReport.setVisibility(View.VISIBLE);
        if (data.getData().getBank().equals("true"))
            tvCardInfoBankRunningWater.setVisibility(View.VISIBLE);
        if (data.getData().getAssess().equals("true"))
            tvCardInfoAssessmentReport.setVisibility(View.VISIBLE);
        if (data.getData().getYield().equals("true"))
            tvCardInfoProductionAdjustment.setVisibility(View.VISIBLE);
        if (data.getData().getFarther().equals("true"))
            tvCardInfoSupplementaryMaterials.setVisibility(View.VISIBLE);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
