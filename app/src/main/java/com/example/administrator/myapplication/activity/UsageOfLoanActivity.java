package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.base.BaseView;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：借款用途
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/3.
 */
public class UsageOfLoanActivity extends BaseActivity {

    @BindView(R.id.tv_usage_of_loan_top)
    TopView tvUsageOfLoanTop;
    @BindView(R.id.bt_usage_of_loan_confirm)
    Button btUsageOfLoanConfirm;
    @BindView(R.id.et_usage_of_loan)
    EditText etUsageOfLoan;

    @Override
    public int getLayoutId() {
        return R.layout.activity_usage_of_loan;
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
        ImmersionBar.setTitleBar(this, tvUsageOfLoanTop);

        etUsageOfLoan.setText(getIntent().getStringExtra("Usage"));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.bt_usage_of_loan_confirm)
    public void onViewClicked() {
        setResult(RESULT_OK, new Intent().putExtra("usageOfLoanBriefOutlineResult", etUsageOfLoan.getText().toString().trim()));
        finish();
    }
}
