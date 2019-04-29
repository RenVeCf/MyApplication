package com.example.administrator.myapplication.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.LockedFilesBean;
import com.example.administrator.myapplication.bean.LockedFilesDelBean;
import com.example.administrator.myapplication.bean.LockedFilesSelectBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.LockedFilesContract;
import com.example.administrator.myapplication.presenter.LockedFilesPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.DateUtils;
import com.example.administrator.myapplication.utils.LogUtils;
import com.example.administrator.myapplication.utils.SPUtil;
import com.example.administrator.myapplication.utils.ToastUtil;
import com.example.administrator.myapplication.utils.isClickUtil;
import com.gyf.barlibrary.ImmersionBar;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import io.reactivex.ObservableTransformer;

/**
 * Description ：资料详情（锁定的文件）
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/8.
 */
public class ParticularsOfInformationLockActivity extends BaseActivity<LockedFilesContract.View, LockedFilesContract.Presenter> implements LockedFilesContract.View {

    @BindView(R.id.tv_particulars_of_information_lock_top)
    TopView tvParticularsOfInformationLockTop;
    @BindView(R.id.iv_top_back)
    ImageView ivTopBack;
    @BindView(R.id.tv_originator)
    TextView tvOriginator;
    @BindView(R.id.tv_forwarder)
    TextView tvForwarder;
    @BindView(R.id.tv_forwarding_time)
    TextView tvForwardingTime;
    @BindView(R.id.tv_name_of_borrower)
    TextView tvNameOfBorrower;
    @BindView(R.id.tv_id_card_num)
    TextView tvIdCardNum;
    @BindView(R.id.tv_handling_type)
    TextView tvHandlingType;
    @BindView(R.id.tv_loan_amount)
    TextView tvLoanAmount;
    @BindView(R.id.tv_upload_time)
    TextView tvUploadTime;
    @BindView(R.id.tv_marital_status)
    TextView tvMaritalStatus;
    @BindView(R.id.tv_whether_to_buy_houses)
    TextView tvWhetherToBuyHouses;
    @BindView(R.id.ll_particulars_of_information_lock_id_card)
    LinearLayout llParticularsOfInformationLockIdCard;
    @BindView(R.id.ll_particulars_of_information_lock_marriage_certificate)
    LinearLayout llParticularsOfInformationLockMarriageCertificate;
    @BindView(R.id.ll_particulars_of_information_lock_household_registration_book)
    LinearLayout llParticularsOfInformationLockHouseholdRegistrationBook;
    @BindView(R.id.ll_particulars_of_information_lock_property_ownership_certificate)
    LinearLayout llParticularsOfInformationLockPropertyOwnershipCertificate;
    @BindView(R.id.ll_particulars_of_information_lock_collateral)
    LinearLayout llParticularsOfInformationLockCollateral;
    @BindView(R.id.ll_particulars_of_information_lock_business_license)
    LinearLayout llParticularsOfInformationLockBusinessLicense;
    @BindView(R.id.ll_particulars_of_information_lock_credit_report)
    LinearLayout llParticularsOfInformationLockCreditReport;
    @BindView(R.id.ll_particulars_of_information_lock_bank_running_water)
    LinearLayout llParticularsOfInformationLockBankRunningWater;
    @BindView(R.id.ll_particulars_of_information_lock_assessment_report)
    LinearLayout llParticularsOfInformationLockAssessmentReport;
    @BindView(R.id.ll_particulars_of_information_lock_production_adjustment)
    LinearLayout llParticularsOfInformationLockProductionAdjustment;
    @BindView(R.id.ll_particulars_of_information_lock_supplementary_materials)
    LinearLayout llParticularsOfInformationLockSupplementaryMaterials;
    @BindView(R.id.bt_delete)
    Button btDelete;
    @BindView(R.id.tv_top_share)
    TextView tvTopShare;

    private String locId = "";
    private String proId = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_particulars_of_information_lock;
    }

    @Override
    public LockedFilesContract.Presenter createPresenter() {
        return new LockedFilesPresenter(this);
    }

    @Override
    public LockedFilesContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvParticularsOfInformationLockTop);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        locId = getIntent().getStringExtra("loc_id");
        proId = getIntent().getStringExtra("pro_id");
        TreeMap<String, String> selectMyInfoMap = new TreeMap<>();
        selectMyInfoMap.put("loc_id", locId);
        getPresenter().getLockedFilesSelect(selectMyInfoMap, true, false);
    }

    private void showShare(String url) {
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle("阅阅");
        oks.setText("test");
        Bitmap bitmap = BitmapFactory.decodeResource(this.getApplicationContext().getResources(), R.mipmap.logo);//显示APP本身自带图片
        oks.setImageData(bitmap);//bitmap格式图片
        oks.setUrl(url);
        oks.setComment("很棒，值得分享！！");
        oks.show(this);
    }

    private void setShareDialog() {
        final Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
        //Dialog布局
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.dialog_share, null);
        root.findViewById(R.id.iv_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare("http://121.199.8.244:2837/html/share.html?user=" + SPUtil.get(ParticularsOfInformationLockActivity.this, IConstants.USER_ID, "") + "&pro=" + proId);
                mCameraDialog.dismiss();
            }
        });
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM); //设置弹出方式
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = 500;

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    private void setLockedFilesDialog(final String locId) {
        TextView tv;
        final Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
        //Dialog布局
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.dialog_center, null);
        tv = root.findViewById(R.id.tv_dialog_center_end);
        tv.setText("是否删除？");
        //初始化视图
        root.findViewById(R.id.dialog_center_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TreeMap<String, String> lockedFilesDleMap = new TreeMap<>();
                lockedFilesDleMap.put("loc_id", locId);
                getPresenter().getLockedFilesDel(lockedFilesDleMap, true, false);
                mCameraDialog.dismiss();
            }
        });
        root.findViewById(R.id.dialog_center_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCameraDialog.dismiss();
            }
        });
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER); //设置弹出方式
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = 700;
        root.measure(0, 0);
        lp.height = 320;

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    @OnClick({R.id.iv_top_back, R.id.ll_particulars_of_information_lock_id_card, R.id.ll_particulars_of_information_lock_marriage_certificate, R.id.ll_particulars_of_information_lock_household_registration_book, R.id.ll_particulars_of_information_lock_property_ownership_certificate, R.id.ll_particulars_of_information_lock_collateral, R.id.ll_particulars_of_information_lock_business_license, R.id.ll_particulars_of_information_lock_credit_report, R.id.ll_particulars_of_information_lock_bank_running_water, R.id.ll_particulars_of_information_lock_assessment_report, R.id.ll_particulars_of_information_lock_production_adjustment, R.id.ll_particulars_of_information_lock_supplementary_materials, R.id.bt_delete, R.id.tv_top_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_particulars_of_information_lock_id_card:
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_lock_marriage_certificate:
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_lock_household_registration_book:
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_lock_property_ownership_certificate:
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_lock_collateral:
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_lock_business_license:
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_lock_credit_report:
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_lock_bank_running_water:
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_lock_assessment_report:
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_lock_production_adjustment:
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_lock_supplementary_materials:
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.bt_delete:
                setLockedFilesDialog(locId);
                break;
            case R.id.tv_top_share:
                setShareDialog();
                break;
            case R.id.iv_top_back:
                Intent intent = new Intent("android.intent.action.CART_BROADCAST");
                intent.putExtra("data", "refresh4");
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                sendBroadcast(intent);

                if (this instanceof Activity && isClickUtil.isFastClick()) {
                    (this).finish();
                    if ((this).getCurrentFocus() != null) {
                        ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                break;
        }
    }

    @Override
    public void resultLockedFiles(LockedFilesBean data) {

    }

    @Override
    public void resultLockedFilesDel(LockedFilesDelBean data) {
        ToastUtil.showShortToast(data.getMsg());
        setResult(RESULT_OK, new Intent().putExtra("lockedDelResult", "1"));
        finish();
    }

    @Override
    public void resultLockedFilesSelect(LockedFilesSelectBean data) {
        tvOriginator.setText(data.getData().getLocking().getHostname());
        tvForwarder.setText(data.getData().getLocking().getSponsorname());
        tvForwardingTime.setText(DateUtils.time(data.getData().getLocking().getLoc_addtime()));
        tvNameOfBorrower.setText(data.getData().getProfile().getPro_name());
        tvIdCardNum.setText(data.getData().getProfile().getPro_card());
        if (data.getData().getProfile().getPro_handle() == 0)
            tvHandlingType.setText("机构");
        else
            tvHandlingType.setText("个人");
        tvLoanAmount.setText(data.getData().getProfile().getPro_amount() + "");
        tvUploadTime.setText(DateUtils.timesTwo(data.getData().getProfile().getPro_addtime()));
        if (data.getData().getProfile().getPro_marriage() == 0)
            tvMaritalStatus.setText("离异");
        else if (data.getData().getProfile().getPro_marriage() == 1)
            tvMaritalStatus.setText("已婚");
        else
            tvMaritalStatus.setText("未婚");
        if (data.getData().getProfile().getPro_room() == 0)
            tvWhetherToBuyHouses.setText("是");
        else
            tvWhetherToBuyHouses.setText("否");
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
