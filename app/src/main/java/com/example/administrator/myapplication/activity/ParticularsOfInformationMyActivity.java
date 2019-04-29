package com.example.administrator.myapplication.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.MyForwardsBean;
import com.example.administrator.myapplication.bean.MyOffBean;
import com.example.administrator.myapplication.bean.SelectMyInfoBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.SelectMyInfoContract;
import com.example.administrator.myapplication.presenter.SelectMyInfoPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.DateUtils;
import com.example.administrator.myapplication.utils.LogUtils;
import com.example.administrator.myapplication.utils.SPUtil;
import com.example.administrator.myapplication.utils.ToastUtil;
import com.example.administrator.myapplication.utils.isClickUtil;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import io.reactivex.ObservableTransformer;

/**
 * Description ：资料详情（我的资料）
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/8.
 */
public class ParticularsOfInformationMyActivity extends BaseActivity<SelectMyInfoContract.View, SelectMyInfoContract.Presenter> implements SelectMyInfoContract.View {

    @BindView(R.id.tv_particulars_of_information_my_top)
    TopView tvParticularsOfInformationMyTop;
    @BindView(R.id.iv_top_back)
    ImageView ivTopBack;
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
    @BindView(R.id.ll_particulars_of_information_my_id_card)
    LinearLayout llParticularsOfInformationMyIdCard;
    @BindView(R.id.ll_particulars_of_information_my_marriage_certificate)
    LinearLayout llParticularsOfInformationMyMarriageCertificate;
    @BindView(R.id.ll_particulars_of_information_my_household_registration_book)
    LinearLayout llParticularsOfInformationMyHouseholdRegistrationBook;
    @BindView(R.id.ll_particulars_of_information_my_property_ownership_certificate)
    LinearLayout llParticularsOfInformationMyPropertyOwnershipCertificate;
    @BindView(R.id.ll_particulars_of_information_my_collateral)
    LinearLayout llParticularsOfInformationMyCollateral;
    @BindView(R.id.ll_particulars_of_information_my_business_license)
    LinearLayout llParticularsOfInformationMyBusinessLicense;
    @BindView(R.id.ll_particulars_of_information_my_credit_report)
    LinearLayout llParticularsOfInformationMyCreditReport;
    @BindView(R.id.ll_particulars_of_information_my_bank_running_water)
    LinearLayout llParticularsOfInformationMyBankRunningWater;
    @BindView(R.id.ll_particulars_of_information_my_assessment_report)
    LinearLayout llParticularsOfInformationMyAssessmentReport;
    @BindView(R.id.ll_particulars_of_information_my_production_adjustment)
    LinearLayout llParticularsOfInformationMyProductionAdjustment;
    @BindView(R.id.ll_particulars_of_information_my_supplementary_materials)
    LinearLayout llParticularsOfInformationMySupplementaryMaterials;
    @BindView(R.id.bt_edit)
    Button btEdit;
    @BindView(R.id.bt_forward)
    Button btForward;
    @BindView(R.id.bt_off)
    Button btOff;
    @BindView(R.id.bt_browse_record)
    Button btBrowseRecord;
    @BindView(R.id.tv_top_share)
    TextView tvTopShare;

    private EditText etDialogForward;
    private RadioButton rbDialogForwardWatermark;
    private RadioButton rbDialogForwardNoWatermark;
    private RadioButton rbDialogForwardCanForward;
    private RadioButton rbDialogForwardNoForward;
    private RadioButton rbDialogForwardCanDownload;
    private RadioButton rbDialogForwardNoDownload;

    private List<SelectMyInfoBean.DataBeanX.CardBean> selectMyInfoBean;
    private String proId = "";//资料id

    @Override
    public int getLayoutId() {
        return R.layout.activity_particulars_of_information_my;
    }

    @Override
    public SelectMyInfoContract.Presenter createPresenter() {
        return new SelectMyInfoPresenter(this);
    }

    @Override
    public SelectMyInfoContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvParticularsOfInformationMyTop);
        proId = getIntent().getStringExtra("pro_id");
        selectMyInfoBean = new ArrayList<>();
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
        TreeMap<String, String> selectMyInfoMap = new TreeMap<>();
        selectMyInfoMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
        selectMyInfoMap.put("proid", proId);
        getPresenter().selectMyInfo(selectMyInfoMap, true, false);
    }

    private void setMyInformationDialog(final String pro_id, final String btType) {
        TextView tv;
        final Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
        //Dialog布局
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.dialog_center, null);
        tv = root.findViewById(R.id.tv_dialog_center_end);
        tv.setText("是否关闭？");
        //初始化视图
        root.findViewById(R.id.dialog_center_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TreeMap<String, String> myOffMap = new TreeMap<>();
                myOffMap.put("userid", (String) SPUtil.get(ParticularsOfInformationMyActivity.this, IConstants.USER_ID, ""));
                myOffMap.put("proid", pro_id + "");
                if (btType.equals("关闭"))
                    myOffMap.put("del", "1");
                else
                    myOffMap.put("del", "0");
                getPresenter().getMyOff(myOffMap, true, false);
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

    private void setDialog(final String pro_id) {
        final Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
        //Dialog布局
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.dialog_bottom_forward, null);
        //初始化视图
        etDialogForward = root.findViewById(R.id.et_dialog_forward);
        rbDialogForwardWatermark = root.findViewById(R.id.rb_dialog_forward_watermark);
        rbDialogForwardNoWatermark = root.findViewById(R.id.rb_dialog_forward_no_watermark);
        rbDialogForwardCanForward = root.findViewById(R.id.rb_dialog_forward_can_forward);
        rbDialogForwardNoForward = root.findViewById(R.id.rb_dialog_forward_no_forward);
        rbDialogForwardCanDownload = root.findViewById(R.id.rb_dialog_forward_can_download);
        rbDialogForwardNoDownload = root.findViewById(R.id.rb_dialog_forward_no_download);
        root.findViewById(R.id.bt_dialog_bottom_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etDialogForward.getText().toString().trim().equals("")) {
                    TreeMap<String, String> loginMap = new TreeMap<>();
                    loginMap.put("for_sponsor", (String) SPUtil.get(ParticularsOfInformationMyActivity.this, IConstants.USER_ID, ""));
                    loginMap.put("for_proid", pro_id + "");
                    loginMap.put("for_accept", etDialogForward.getText().toString().trim());
                    if (rbDialogForwardWatermark.isChecked())
                        loginMap.put("for_watermark", "1");
                    if (rbDialogForwardNoForward.isChecked())
                        loginMap.put("for_send", "1");
                    if (rbDialogForwardNoDownload.isChecked())
                        loginMap.put("for_download", "1");
                    getPresenter().getMyForwards(loginMap, false, false);
                    mCameraDialog.dismiss();
                } else
                    ToastUtil.showShortToast("请输入手机号！");

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
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
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();

        //定义底部标签图片大小
        Drawable drawableWatermark = getResources().getDrawable(R.drawable.select_icon_forward);
        drawableWatermark.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y40), (int) getResources().getDimension(R.dimen.y40));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbDialogForwardWatermark.setCompoundDrawables(drawableWatermark, null, null, null);//只放上面

        Drawable drawableNoWatermark = getResources().getDrawable(R.drawable.select_icon_forward);
        drawableNoWatermark.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y40), (int) getResources().getDimension(R.dimen.y40));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbDialogForwardNoWatermark.setCompoundDrawables(drawableNoWatermark, null, null, null);//只放上面

        Drawable drawableCanForward = getResources().getDrawable(R.drawable.select_icon_forward);
        drawableCanForward.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y40), (int) getResources().getDimension(R.dimen.y40));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbDialogForwardCanForward.setCompoundDrawables(drawableCanForward, null, null, null);//只放上面

        Drawable drawableNoForward = getResources().getDrawable(R.drawable.select_icon_forward);
        drawableNoForward.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y40), (int) getResources().getDimension(R.dimen.y40));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbDialogForwardNoForward.setCompoundDrawables(drawableNoForward, null, null, null);//只放上面

        Drawable drawableCanDownload = getResources().getDrawable(R.drawable.select_icon_forward);
        drawableCanDownload.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y40), (int) getResources().getDimension(R.dimen.y40));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbDialogForwardCanDownload.setCompoundDrawables(drawableCanDownload, null, null, null);//只放上面

        Drawable drawableNoDownload = getResources().getDrawable(R.drawable.select_icon_forward);
        drawableNoDownload.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y40), (int) getResources().getDimension(R.dimen.y40));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbDialogForwardNoDownload.setCompoundDrawables(drawableNoDownload, null, null, null);//只放上面
    }

    private void setShareDialog() {
        final Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
        //Dialog布局
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.dialog_share, null);
        root.findViewById(R.id.iv_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare("http://121.199.8.244:2837/html/share.html?user=" + SPUtil.get(ParticularsOfInformationMyActivity.this, IConstants.USER_ID, "") + "&pro=" + proId);
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

    @OnClick({R.id.ll_particulars_of_information_my_id_card, R.id.ll_particulars_of_information_my_marriage_certificate, R.id.ll_particulars_of_information_my_household_registration_book, R.id.ll_particulars_of_information_my_property_ownership_certificate, R.id.ll_particulars_of_information_my_collateral, R.id.ll_particulars_of_information_my_business_license, R.id.ll_particulars_of_information_my_credit_report, R.id.ll_particulars_of_information_my_bank_running_water, R.id.ll_particulars_of_information_my_assessment_report, R.id.ll_particulars_of_information_my_production_adjustment, R.id.ll_particulars_of_information_my_supplementary_materials, R.id.bt_edit, R.id.bt_forward, R.id.bt_off, R.id.bt_browse_record, R.id.tv_top_share, R.id.iv_top_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_particulars_of_information_my_id_card:
                //身份证
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("selectMyInfoBean", selectMyInfoBean);
//                startActivity(new Intent(this, NullLayoutActivity.class).putExtras(bundle));
                NullLayoutActivity.launch(this,0,proId);
                break;
            case R.id.ll_particulars_of_information_my_marriage_certificate:
                //结婚证

//                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_my_household_registration_book:
                //户口本
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_my_property_ownership_certificate:
                //房产证
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_my_collateral:
                //抵押物
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_my_business_license:
                //企业信息
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_my_credit_report:
                //信用报告
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_my_bank_running_water:
                //银行流水
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_my_assessment_report:
                //评估报告
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_my_production_adjustment:
                //产调
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.ll_particulars_of_information_my_supplementary_materials:
                //补充资料
                startActivity(new Intent(this, NullLayoutActivity.class));
                break;
            case R.id.bt_edit:
                startActivity(new Intent(this, UploadDataActivity.class)
                        .putExtra("my_info", 1)
                        .putExtra("pro_id", proId + ""));
                break;
            case R.id.bt_forward:
                setDialog(proId);
                break;
            case R.id.bt_off:
                setMyInformationDialog(proId, btOff.getText().toString().trim());
                break;
            case R.id.bt_browse_record:
                startActivity(new Intent(this, BrowseRecordActivity.class).putExtra("pro_id", proId + ""));
                break;
            case R.id.tv_top_share:
                setShareDialog();
                break;
            case R.id.iv_top_back:
                Intent intent = new Intent("android.intent.action.CART_BROADCAST");
                intent.putExtra("data", "refresh1");
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
    public void resultSelectMyInfo(SelectMyInfoBean data) {
        selectMyInfoBean.clear();
        selectMyInfoBean.addAll(data.getData().getCard());
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
        if (data.getData().getProfile().getPro_del() == 0)
            btOff.setText("关闭");
        else
            btOff.setText("开启");
    }

    @Override
    public void resultMyForwards(MyForwardsBean data) {
        ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public void resultMyOff(MyOffBean data) {
        if (btOff.getText().toString().trim().equals("关闭"))
            btOff.setText("开启");
        else
            btOff.setText("关闭");
        ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent("android.intent.action.CART_BROADCAST");
        intent.putExtra("data", "refresh1");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        sendBroadcast(intent);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
