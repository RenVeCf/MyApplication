package com.example.ipd.yueyue.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.base.BaseActivity;
import com.example.ipd.yueyue.bean.LoginBean;
import com.example.ipd.yueyue.common.config.IConstants;
import com.example.ipd.yueyue.common.config.UrlConfig;
import com.example.ipd.yueyue.contract.LoginContract;
import com.example.ipd.yueyue.presenter.LoginPresenter;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.example.ipd.yueyue.utils.SPUtil;
import com.example.ipd.yueyue.utils.ToastUtil;
import com.example.ipd.yueyue.utils.VerifyUtils;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

/**
 * Description ：登录
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public class LoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.bt_login)
    Button btLogin;

    private long firstTime = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public LoginContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);

        //自动登录
        if (!SPUtil.get(this, IConstants.USER_TOKEN, "").equals("")) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void resultLogin(LoginBean data) {
        if (data.getData() != null) {
            SPUtil.put(this, String.valueOf(IConstants.IS_LOGIN), true);
            SPUtil.put(this, IConstants.USER_TOKEN, data.getData().getUser_token());
            SPUtil.put(this, IConstants.USER_ID, data.getData().getUser_id() + "");
            SPUtil.put(this, IConstants.NAME, data.getData().getName());
            SPUtil.put(this, IConstants.PHONE, data.getData().getPhone());
            SPUtil.put(this, IConstants.AVATAR, UrlConfig.BASE_URL + data.getData().getAvatar());
            if (data.getData().getAgency() == null)
                SPUtil.put(this, IConstants.AGENCY, "");
            else
                SPUtil.put(this, IConstants.AGENCY, data.getData().getAgency());
            if (data.getData().getPosition() == null)
                SPUtil.put(this, IConstants.POSITION, "");
            else
                SPUtil.put(this, IConstants.POSITION, data.getData().getPosition());
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            ToastUtil.showShortToast(data.getMsg());
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();//绑定activity，与activity生命周期一样
    }

    /**
     * 双击退出程序
     */
    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            ToastUtil.showShortToast(getResources().getString(R.string.click_out_again));
            firstTime = secondTime;
        } else {
            ApplicationUtil.getManager().exitApp();
        }
    }

    @OnClick({R.id.tv_forget_pwd, R.id.tv_register, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_pwd:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                finish();
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
            case R.id.bt_login:
                if (etLoginPhone.getText().toString().trim().length() == 11 && VerifyUtils.isMobileNumber(etLoginPhone.getText().toString().trim()) && etLoginPwd.getText().toString().trim().length() >= 6 && etLoginPwd.getText().toString().trim().length() <= 16) {
                    TreeMap<String, String> loginMap = new TreeMap<>();
                    //获取手机号码
                    loginMap.put("phone", etLoginPhone.getText().toString().trim());
                    //获取密码
                    loginMap.put("password", etLoginPwd.getText().toString().trim());
                    getPresenter().login(loginMap, true, false);
                } else if (etLoginPhone.getText().toString().trim().length() != 11 || !VerifyUtils.isMobileNumber(etLoginPhone.getText().toString().trim())) {
                    ToastUtil.showShortToast(getString(R.string.error_phone_num));
                } else if (etLoginPwd.getText().toString().trim().length() < 6 || etLoginPwd.getText().toString().trim().length() > 16) {
                    ToastUtil.showLongToast(getResources().getString(R.string.error_pwd));
                }
                break;
        }
    }
}
