package com.example.ipd.yueyue.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.base.BaseActivity;
import com.example.ipd.yueyue.bean.ModifyServiceOrganizationBean;
import com.example.ipd.yueyue.common.config.IConstants;
import com.example.ipd.yueyue.common.view.TopView;
import com.example.ipd.yueyue.contract.ModifyServiceOrganizationContract;
import com.example.ipd.yueyue.presenter.ModifyServiceOrganizationPresenter;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.example.ipd.yueyue.utils.SPUtil;
import com.gyf.barlibrary.ImmersionBar;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class ModifyPositionActivity extends BaseActivity<ModifyServiceOrganizationContract.View, ModifyServiceOrganizationContract.Presenter> implements ModifyServiceOrganizationContract.View {

    @BindView(R.id.tv_modify_position_top)
    TopView tvModifyPositionTop;
    @BindView(R.id.et_modify_position)
    EditText etModifyPosition;
    @BindView(R.id.bt_modify_position_confirm)
    Button btModifyPositionConfirm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_position;
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
        ImmersionBar.setTitleBar(this, tvModifyPositionTop);

        etModifyPosition.setText(getIntent().getStringExtra("Position"));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void resultModifyServiceOrganization(ModifyServiceOrganizationBean data) {
        SPUtil.put(this, IConstants.POSITION, etModifyPosition.getText().toString().trim());
        setResult(RESULT_OK, new Intent().putExtra("modifyPositionResult", etModifyPosition.getText().toString().trim()));
        finish();
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }

    @OnClick(R.id.bt_modify_position_confirm)
    public void onViewClicked() {
        if (!etModifyPosition.getText().toString().trim().equals("")) {
            TreeMap<String, String> modifyServiceOrganizationMap = new TreeMap<>();
            modifyServiceOrganizationMap.put("id", (String) SPUtil.get(this, IConstants.USER_ID, ""));
            modifyServiceOrganizationMap.put("agency", (String) SPUtil.get(this, IConstants.AGENCY, ""));
            modifyServiceOrganizationMap.put("position", etModifyPosition.getText().toString().trim());
            getPresenter().modifyServiceOrganization(modifyServiceOrganizationMap, true, false);
        } else {
            setResult(RESULT_OK, new Intent().putExtra("modifyPositionResult", ""));
            finish();
        }
    }
}
