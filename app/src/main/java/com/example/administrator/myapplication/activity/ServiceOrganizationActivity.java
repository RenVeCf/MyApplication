package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.base.BasePresenter;
import com.example.administrator.myapplication.base.BaseView;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.SPUtil;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：服务机构
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/3.
 */
public class ServiceOrganizationActivity extends BaseActivity {

    @BindView(R.id.tv_service_organization_top)
    TopView tvServiceOrganizationTop;
    @BindView(R.id.tv_service_organization)
    TextView tvServiceOrganization;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.ll_service_organization)
    LinearLayout llServiceOrganization;
    @BindView(R.id.ll_position)
    LinearLayout llPosition;

    @Override
    public int getLayoutId() {
        return R.layout.activity_service_organization;
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
        ImmersionBar.setTitleBar(this, tvServiceOrganizationTop);

        tvServiceOrganization.setText((String) SPUtil.get(this, IConstants.AGENCY, ""));
        tvPosition.setText((String) SPUtil.get(this, IConstants.POSITION, ""));
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
        if (data != null) {
            switch (requestCode) {
                case IConstants.REQUEST_CODE_96:
                    if (!data.getStringExtra("modifyServiceOrganizationResult").equals(""))
                        tvServiceOrganization.setText(data.getStringExtra("modifyServiceOrganizationResult"));
                    break;
                case IConstants.REQUEST_CODE_97:
                    if (!data.getStringExtra("modifyPositionResult").equals(""))
                        tvPosition.setText(data.getStringExtra("modifyPositionResult"));
                    break;
            }
        }
    }

    @OnClick({R.id.ll_service_organization, R.id.ll_position})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_service_organization:
                if (!tvServiceOrganization.getText().toString().trim().equals(""))
                    startActivityForResult(new Intent(this, ModifyServiceOrganizationActivity.class).putExtra("Service", tvServiceOrganization.getText().toString().trim()), IConstants.REQUEST_CODE_96);
                else
                    startActivityForResult(new Intent(this, ModifyServiceOrganizationActivity.class), IConstants.REQUEST_CODE_96);
                break;
            case R.id.ll_position:
                if (!tvPosition.getText().toString().trim().equals(""))
                    startActivityForResult(new Intent(this, ModifyPositionActivity.class).putExtra("Position", tvPosition.getText().toString().trim()), IConstants.REQUEST_CODE_97);
                else
                    startActivityForResult(new Intent(this, ModifyPositionActivity.class), IConstants.REQUEST_CODE_97);
                break;
        }
    }
}
