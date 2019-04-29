package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.ModifyPersonalDataBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.ModifyPersonalDataContract;
import com.example.administrator.myapplication.presenter.ModifyPersonalDataPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.SPUtil;
import com.example.administrator.myapplication.utils.ToastUtil;
import com.gyf.barlibrary.ImmersionBar;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

/**
 * Description ：姓名
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/8.
 */
public class NameActivity extends BaseActivity<ModifyPersonalDataContract.View, ModifyPersonalDataContract.Presenter> implements ModifyPersonalDataContract.View {

    @BindView(R.id.tv_name_top)
    TopView tvNameTop;
    @BindView(R.id.bt_name_confirm)
    Button btNameConfirm;
    @BindView(R.id.et_name_name)
    EditText etNameName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_name;
    }

    @Override
    public ModifyPersonalDataContract.Presenter createPresenter() {
        return new ModifyPersonalDataPresenter(this);
    }

    @Override
    public ModifyPersonalDataContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvNameTop);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.bt_name_confirm)
    public void onViewClicked() {
        TreeMap<String, String> modifyPersonalDataMap = new TreeMap<>();
        modifyPersonalDataMap.put("id", (String) SPUtil.get(this, IConstants.USER_ID, ""));
        modifyPersonalDataMap.put("avatar", (String) SPUtil.get(this, IConstants.AVATAR, ""));
        modifyPersonalDataMap.put("name", etNameName.getText().toString().trim());
        getPresenter().modifyPersonalData(modifyPersonalDataMap, true, false);
    }

    @Override
    public void resultModifyPersonalData(ModifyPersonalDataBean data) {
        ToastUtil.showShortToast(data.getMsg());
        setResult(RESULT_OK, new Intent().putExtra("nameResult", etNameName.getText().toString().trim()));
        finish();
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
