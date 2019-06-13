package com.example.ipd.yueyue.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.base.BaseActivity;
import com.example.ipd.yueyue.bean.CaptchaBean;
import com.example.ipd.yueyue.bean.RegisterBean;
import com.example.ipd.yueyue.common.config.IConstants;
import com.example.ipd.yueyue.common.config.UrlConfig;
import com.example.ipd.yueyue.contract.RegisterContract;
import com.example.ipd.yueyue.presenter.RegisterPresenter;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.example.ipd.yueyue.utils.CountDownUtil;
import com.example.ipd.yueyue.utils.SPUtil;
import com.example.ipd.yueyue.utils.ToastUtil;
import com.example.ipd.yueyue.utils.VerifyUtils;
import com.example.ipd.yueyue.utils.isClickUtil;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

/**
 * Description ：注册
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public class RegisterActivity extends BaseActivity<RegisterContract.View, RegisterContract.Presenter> implements RegisterContract.View {

    @BindView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @BindView(R.id.et_register_verification_code)
    EditText etRegisterVerificationCode;
    @BindView(R.id.bt_get_verification_code)
    Button btGetVerificationCode;
    @BindView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @BindView(R.id.cb_register)
    CheckBox cbRegister;
    @BindView(R.id.tv_register_login)
    TextView tvRegisterLogin;
    @BindView(R.id.bt_register)
    Button btRegister;

    private long firstTime = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterContract.Presenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public RegisterContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

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

    @OnClick({R.id.bt_get_verification_code, R.id.tv_register_login, R.id.bt_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_get_verification_code:
                if (etRegisterPhone.getText().toString().trim().length() == 11 && VerifyUtils.isMobileNumber(etRegisterPhone.getText().toString().trim())) {
                    TreeMap<String, String> captchaMap = new TreeMap<>();
                    //获取手机号码
                    captchaMap.put("phone", etRegisterPhone.getText().toString().trim());
                    //平台标识码
                    captchaMap.put("type", "1");
                    getPresenter().captcha(captchaMap, true, false);
                } else {
                    ToastUtil.showShortToast(getString(R.string.error_phone_num));
                }
                break;
            case R.id.tv_register_login:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.bt_register:
                if (isClickUtil.isFastClick()) {
                    //手机号码的长度判断，验证码的长度判断，复选框状态
                    if (etRegisterPhone.getText().toString().trim().length() == 11 && VerifyUtils.isMobileNumber(etRegisterPhone.getText().toString().trim()) && etRegisterVerificationCode.getText().toString().trim().length() == 6 && VerifyUtils.isNumeric(etRegisterVerificationCode.getText().toString().trim()) && etRegisterPwd.getText().toString().trim().length() >= 6 && etRegisterPwd.getText().toString().trim().length() <= 16 && cbRegister.isChecked() == true) {
                        TreeMap<String, String> registerMap = new TreeMap<>();
                        //获取手机号码
                        registerMap.put("phone", etRegisterPhone.getText().toString().trim());
                        //获取验证码
                        registerMap.put("mobile_code", etRegisterVerificationCode.getText().toString().trim());
                        //密码
                        registerMap.put("password", etRegisterPwd.getText().toString().trim());
                        //确认密码
                        registerMap.put("confirm_password", etRegisterPwd.getText().toString().trim());
                        getPresenter().register(registerMap, true, false);
                    } else if (cbRegister.isChecked() == false) {
                        ToastUtil.showShortToast(getString(R.string.error_check_box));
                    } else if (etRegisterPhone.getText().toString().trim().length() != 11 || !VerifyUtils.isMobileNumber(etRegisterPhone.getText().toString().trim())) {
                        ToastUtil.showShortToast(getString(R.string.error_phone_num));
                    } else if (etRegisterVerificationCode.getText().toString().trim().length() != 6) {
                        ToastUtil.showLongToast(getResources().getString(R.string.six_length_captcha));
                    } else {
                        ToastUtil.showShortToast(getString(R.string.error_login));
                    }
                }
                break;
        }
    }

    @Override
    public void resultRegister(RegisterBean data) {
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
    public void resultCaptcha(CaptchaBean data) {
        //验证码倒计时60内不能重新发送
        new CountDownUtil(btGetVerificationCode)
                .setCountDownMillis(60_000L)//倒计时60000ms
                .setCountDownColor(R.color.white, R.color.white)//不同状态字体颜色
                .setOnClickListener(new View.OnClickListener() {
                    //重新获取验证码
                    @Override
                    public void onClick(View v) {
                        if (etRegisterPhone.getText().toString().trim().length() == 11 && VerifyUtils.isMobileNumber(etRegisterPhone.getText().toString().trim())) {
                            TreeMap<String, String> captchaMap = new TreeMap<>();
                            //获取手机号码
                            captchaMap.put("phone", etRegisterPhone.getText().toString().trim());
                            //平台标识码
                            captchaMap.put("type", "1");
                            getPresenter().captcha(captchaMap, true, false);
                        } else {
                            ToastUtil.showShortToast(getString(R.string.error_phone_num));
                        }
                    }
                })
                .start();
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
