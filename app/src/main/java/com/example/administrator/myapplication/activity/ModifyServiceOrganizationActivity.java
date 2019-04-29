package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.ModifyServiceOrganizationBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.ModifyServiceOrganizationContract;
import com.example.administrator.myapplication.presenter.ModifyServiceOrganizationPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.SPUtil;
import com.gyf.barlibrary.ImmersionBar;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class ModifyServiceOrganizationActivity extends BaseActivity<ModifyServiceOrganizationContract.View, ModifyServiceOrganizationContract.Presenter> implements ModifyServiceOrganizationContract.View {

    @BindView(R.id.tv_modify_service_organization_top)
    TopView tvModifyServiceOrganizationTop;
    @BindView(R.id.et_modify_service_organization)
    EditText etModifyServiceOrganization;
    @BindView(R.id.bt_modify_service_organization_confirm)
    Button btModifyServiceOrganizationConfirm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_service_organization;
    }

    @Override
    public ModifyServiceOrganizationContract.Presenter createPresenter() {
        return new ModifyServiceOrganizationPresenter(this);
    }

    @Override
    public ModifyServiceOrganizationContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvModifyServiceOrganizationTop);

        etModifyServiceOrganization.setText(getIntent().getStringExtra("Service"));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.bt_modify_service_organization_confirm)
    public void onViewClicked() {
        if (!etModifyServiceOrganization.getText().toString().trim().equals("")) {
            TreeMap<String, String> modifyServiceOrganizationMap = new TreeMap<>();
            modifyServiceOrganizationMap.put("id", (String) SPUtil.get(this, IConstants.USER_ID, ""));
            modifyServiceOrganizationMap.put("agency", etModifyServiceOrganization.getText().toString().trim());
            modifyServiceOrganizationMap.put("position", (String) SPUtil.get(this, IConstants.POSITION, ""));
            getPresenter().modifyServiceOrganization(modifyServiceOrganizationMap, true, false);
        } else {
            setResult(RESULT_OK, new Intent().putExtra("modifyServiceOrganizationResult", ""));
            finish();
        }
    }

    @Override
    public void resultModifyServiceOrganization(ModifyServiceOrganizationBean data) {
        SPUtil.put(this, IConstants.AGENCY, etModifyServiceOrganization.getText().toString().trim());
        setResult(RESULT_OK, new Intent().putExtra("modifyServiceOrganizationResult", etModifyServiceOrganization.getText().toString().trim()));
        finish();
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
