package com.example.ipd.yueyue.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.base.BaseActivity;
import com.example.ipd.yueyue.base.BasePresenter;
import com.example.ipd.yueyue.base.BaseView;
import com.example.ipd.yueyue.common.view.TopView;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：年开票额
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/4.
 */
public class AnnualOpeningActivity extends BaseActivity {

    @BindView(R.id.tv_annual_opening_top)
    TopView tvAnnualOpeningTop;
    @BindView(R.id.bt_annual_opening_confirm)
    Button btAnnualOpeningConfirm;
    @BindView(R.id.et_annual_opening)
    EditText etAnnualOpening;

    @Override
    public int getLayoutId() {
        return R.layout.activity_annual_opening;
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
        ImmersionBar.setTitleBar(this, tvAnnualOpeningTop);

        etAnnualOpening.setText(getIntent().getStringExtra("Annual"));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.bt_annual_opening_confirm)
    public void onViewClicked() {
        setResult(RESULT_OK, new Intent().putExtra("annualOpeningBriefOutlineResult", etAnnualOpening.getText().toString().trim()));
        finish();
    }
}
