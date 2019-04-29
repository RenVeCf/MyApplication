package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.base.BaseView;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：简述概要
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/3.
 */
public class BriefOutlineActivity extends BaseActivity {

    @BindView(R.id.tv_brief_outline_top)
    TopView tvBriefOutlineTop;
    @BindView(R.id.ll_brief_outline_usage_of_loan)
    LinearLayout llBriefOutlineUsageOfLoan;
    @BindView(R.id.ll_brief_outline_payment)
    LinearLayout llBriefOutlinePayment;
    @BindView(R.id.ll_brief_outline_monthly_average_flow)
    LinearLayout llBriefOutlineMonthlyAverageFlow;
    @BindView(R.id.ll_brief_outline_annual_opening)
    LinearLayout llBriefOutlineAnnualOpening;
    @BindView(R.id.tv_usage_of_loan)
    TextView tvUsageOfLoan;
    @BindView(R.id.tv_payment)
    TextView tvPayment;
    @BindView(R.id.tv_monthly_average_flow)
    TextView tvMonthlyAverageFlow;
    @BindView(R.id.tv_annual_opening)
    TextView tvAnnualOpening;
    @BindView(R.id.bt_brief_outline_confirm)
    Button btBriefOutlineConfirm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_brief_outline;
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
        ImmersionBar.setTitleBar(this, tvBriefOutlineTop);

        if (getIntent().getStringExtra("usageOfLoan") != null && !getIntent().getStringExtra("usageOfLoan").equals("")) {
            tvUsageOfLoan.setText(getIntent().getStringExtra("usageOfLoan"));
        }
        if (getIntent().getStringExtra("payment") != null && !getIntent().getStringExtra("payment").equals("")) {
            tvPayment.setText(getIntent().getStringExtra("payment"));
        }
        if (getIntent().getStringExtra("monthlyAverageFlow") != null && !getIntent().getStringExtra("monthlyAverageFlow").equals("")) {
            tvMonthlyAverageFlow.setText(getIntent().getStringExtra("monthlyAverageFlow"));
        }
        if (getIntent().getStringExtra("annualOpening") != null && !getIntent().getStringExtra("annualOpening").equals("")) {
            tvAnnualOpening.setText(getIntent().getStringExtra("annualOpening"));
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null)
            switch (requestCode) {
                case IConstants.REQUEST_CODE_90:
                    if (data.getStringExtra("usageOfLoanBriefOutlineResult") != null && !data.getStringExtra("usageOfLoanBriefOutlineResult").equals(""))
                        tvUsageOfLoan.setText(data.getStringExtra("usageOfLoanBriefOutlineResult"));
                    break;
                case IConstants.REQUEST_CODE_91:
                    if (data.getStringExtra("paymentBriefOutlineResult") != null && !data.getStringExtra("paymentBriefOutlineResult").equals(""))
                        tvPayment.setText(data.getStringExtra("paymentBriefOutlineResult"));
                    break;
                case IConstants.REQUEST_CODE_92:
                    if (data.getStringExtra("monthlyAverageFlowBriefOutlineResult") != null && !data.getStringExtra("monthlyAverageFlowBriefOutlineResult").equals(""))
                        tvMonthlyAverageFlow.setText(data.getStringExtra("monthlyAverageFlowBriefOutlineResult"));
                    break;
                case IConstants.REQUEST_CODE_93:
                    if (data.getStringExtra("annualOpeningBriefOutlineResult") != null && !data.getStringExtra("annualOpeningBriefOutlineResult").equals(""))
                        tvAnnualOpening.setText(data.getStringExtra("annualOpeningBriefOutlineResult"));
                    break;
            }
    }

    @OnClick({R.id.ll_brief_outline_usage_of_loan, R.id.ll_brief_outline_payment, R.id.ll_brief_outline_monthly_average_flow, R.id.ll_brief_outline_annual_opening, R.id.bt_brief_outline_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_brief_outline_usage_of_loan:
                if (!tvUsageOfLoan.getText().toString().trim().equals("") && !tvUsageOfLoan.getText().toString().trim().equals("请输入"))
                    startActivityForResult(new Intent(ApplicationUtil.getContext(), UsageOfLoanActivity.class).putExtra("Usage", tvUsageOfLoan.getText().toString().trim()), IConstants.REQUEST_CODE_90);
                else
                    startActivityForResult(new Intent(ApplicationUtil.getContext(), UsageOfLoanActivity.class), IConstants.REQUEST_CODE_90);
                break;
            case R.id.ll_brief_outline_payment:
                if (!tvPayment.getText().toString().trim().equals("") && !tvPayment.getText().toString().trim().equals("请输入"))
                    startActivityForResult(new Intent(ApplicationUtil.getContext(), PaymentActivity.class).putExtra("Payment", tvPayment.getText().toString().trim()), IConstants.REQUEST_CODE_91);
                else
                    startActivityForResult(new Intent(this, PaymentActivity.class), IConstants.REQUEST_CODE_91);
                break;
            case R.id.ll_brief_outline_monthly_average_flow:
                if (!tvMonthlyAverageFlow.getText().toString().trim().equals("") && !tvMonthlyAverageFlow.getText().toString().trim().equals("请输入"))
                    startActivityForResult(new Intent(ApplicationUtil.getContext(), MonthlyAverageFlowActivity.class).putExtra("Monthly", tvMonthlyAverageFlow.getText().toString().trim()), IConstants.REQUEST_CODE_92);
                else
                    startActivityForResult(new Intent(this, MonthlyAverageFlowActivity.class), IConstants.REQUEST_CODE_92);
                break;
            case R.id.ll_brief_outline_annual_opening:
                if (!tvAnnualOpening.getText().toString().trim().equals("") && !tvAnnualOpening.getText().toString().trim().equals("请输入"))
                    startActivityForResult(new Intent(ApplicationUtil.getContext(), AnnualOpeningActivity.class).putExtra("Annual", tvAnnualOpening.getText().toString().trim()), IConstants.REQUEST_CODE_93);
                else
                    startActivityForResult(new Intent(this, AnnualOpeningActivity.class), IConstants.REQUEST_CODE_93);
                break;
            case R.id.bt_brief_outline_confirm:
                setResult(RESULT_OK, new Intent()
                        .putExtra("usageOfLoanResult", tvUsageOfLoan.getText().toString().trim())
                        .putExtra("paymentResult", tvPayment.getText().toString().trim())
                        .putExtra("monthlyAverageFlowResult", tvMonthlyAverageFlow.getText().toString().trim())
                        .putExtra("annualOpeningResult", tvAnnualOpening.getText().toString().trim()));
                finish();
                break;
        }
    }
}
