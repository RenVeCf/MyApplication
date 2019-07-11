package com.example.ipd.yueyue.activity;

import android.widget.TextView;

import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.base.BaseActivity;
import com.example.ipd.yueyue.bean.BriefSummaryBean;
import com.example.ipd.yueyue.common.view.TopView;
import com.example.ipd.yueyue.contract.BriefSummaryContract;
import com.example.ipd.yueyue.presenter.BriefSummaryPresenter;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.gyf.barlibrary.ImmersionBar;

import java.util.TreeMap;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

/**
 * Description ：资料详情（简述概要）
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/8.
 */
public class BriefSummaryActivity extends BaseActivity<BriefSummaryContract.View, BriefSummaryContract.Presenter> implements BriefSummaryContract.View {

    @BindView(R.id.tv_brief_summary_top)
    TopView tvBriefSummaryTop;
    @BindView(R.id.tv_use_loan)
    TextView tvUseLoan;
    @BindView(R.id.tv_asd)
    TextView tvAsd;
    @BindView(R.id.tv_monthly_average_water)
    TextView tvMonthlyAverageWater;
    @BindView(R.id.tv_annual_billing_amount)
    TextView tvAnnualBillingAmount;

    private String proid = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_brief_summary;
    }

    @Override
    public BriefSummaryContract.Presenter createPresenter() {
        return new BriefSummaryPresenter(this);
    }

    @Override
    public BriefSummaryContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvBriefSummaryTop);

        proid = getIntent().getStringExtra("proId");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        TreeMap<String, String> briefSummaryMap = new TreeMap<>();
        briefSummaryMap.put("proid", proid + "");
        getPresenter().getBriefSummary(briefSummaryMap, true, false);
    }

    @Override
    public void resultBriefSummary(BriefSummaryBean data) {
        if (data.getCode() == 200) {
            tvUseLoan.setText(data.getData().getSch_loanuse() + "");
            tvAsd.setText(data.getData().getSch_asd() + "");
            tvMonthlyAverageWater.setText(data.getData().getSch_stream() + "");
            tvAnnualBillingAmount.setText(data.getData().getSch_amount() + "");
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
