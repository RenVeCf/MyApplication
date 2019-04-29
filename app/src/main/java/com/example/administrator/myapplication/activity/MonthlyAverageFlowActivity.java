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
 * Description ：月均流水
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/4.
 */
public class MonthlyAverageFlowActivity extends BaseActivity {

    @BindView(R.id.tv_monthly_average_flow_top)
    TopView tvMonthlyAverageFlowTop;
    @BindView(R.id.bt_monthly_average_flow_confirm)
    Button btMonthlyAverageFlowConfirm;
    @BindView(R.id.et_monthly_average_flow)
    EditText etMonthlyAverageFlow;

    @Override
    public int getLayoutId() {
        return R.layout.activity_monthly_average_flow;
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
        ImmersionBar.setTitleBar(this, tvMonthlyAverageFlowTop);

        etMonthlyAverageFlow.setText(getIntent().getStringExtra("Monthly"));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.bt_monthly_average_flow_confirm)
    public void onViewClicked() {
        setResult(RESULT_OK, new Intent().putExtra("monthlyAverageFlowBriefOutlineResult", etMonthlyAverageFlow.getText().toString().trim()));
        finish();
    }
}
