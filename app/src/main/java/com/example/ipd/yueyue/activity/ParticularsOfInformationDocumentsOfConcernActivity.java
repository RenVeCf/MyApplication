package com.example.ipd.yueyue.activity;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.base.BaseActivity;
import com.example.ipd.yueyue.bean.DocumentsReceivedBean;
import com.example.ipd.yueyue.bean.LockedFilesAddBean;
import com.example.ipd.yueyue.bean.ReceiveDownloadBean;
import com.example.ipd.yueyue.bean.ReceiveFollowBean;
import com.example.ipd.yueyue.bean.ReceiveForwardBean;
import com.example.ipd.yueyue.bean.SelectOtherBean;
import com.example.ipd.yueyue.common.config.IConstants;
import com.example.ipd.yueyue.common.view.TopView;
import com.example.ipd.yueyue.contract.DocumentsReceivedContract;
import com.example.ipd.yueyue.presenter.DocumentsReceivedPresenter;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.example.ipd.yueyue.utils.DateUtils;
import com.example.ipd.yueyue.utils.NumberUtils;
import com.example.ipd.yueyue.utils.SPUtil;
import com.example.ipd.yueyue.utils.ToastUtil;
import com.example.ipd.yueyue.utils.VerifyUtils;
import com.gyf.barlibrary.ImmersionBar;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;
import io.reactivex.ObservableTransformer;

/**
 * Description ：资料详情（关注文件）
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/8.
 */
public class ParticularsOfInformationDocumentsOfConcernActivity extends BaseActivity<DocumentsReceivedContract.View, DocumentsReceivedContract.Presenter> implements DocumentsReceivedContract.View {

    @BindView(R.id.tv_particulars_of_information_top)
    TopView tvParticularsOfInformationTop;
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
    @BindView(R.id.ll_particulars_of_information_id_card)
    LinearLayout llParticularsOfInformationIdCard;
    @BindView(R.id.ll_particulars_of_information_marriage_certificate)
    LinearLayout llParticularsOfInformationMarriageCertificate;
    @BindView(R.id.ll_particulars_of_information_household_registration_book)
    LinearLayout llParticularsOfInformationHouseholdRegistrationBook;
    @BindView(R.id.ll_particulars_of_information_property_ownership_certificate)
    LinearLayout llParticularsOfInformationPropertyOwnershipCertificate;
    @BindView(R.id.ll_particulars_of_information_collateral)
    LinearLayout llParticularsOfInformationCollateral;
    @BindView(R.id.ll_particulars_of_information_business_license)
    LinearLayout llParticularsOfInformationBusinessLicense;
    @BindView(R.id.ll_particulars_of_information_credit_report)
    LinearLayout llParticularsOfInformationCreditReport;
    @BindView(R.id.ll_particulars_of_information_bank_running_water)
    LinearLayout llParticularsOfInformationBankRunningWater;
    @BindView(R.id.ll_particulars_of_information_assessment_report)
    LinearLayout llParticularsOfInformationAssessmentReport;
    @BindView(R.id.ll_particulars_of_information_production_adjustment)
    LinearLayout llParticularsOfInformationProductionAdjustment;
    @BindView(R.id.ll_particulars_of_information_supplementary_materials)
    LinearLayout llParticularsOfInformationSupplementaryMaterials;
    @BindView(R.id.bt_forward)
    Button btForward;
    @BindView(R.id.bt_download)
    Button btDownload;
    @BindView(R.id.bt_locking)
    Button btLocking;
    @BindView(R.id.bt_no_follow)
    Button btNoFollow;
    @BindView(R.id.tv_top_share)
    TextView tvTopShare;
    @BindView(R.id.iv_particulars_of_information_watermark)
    ImageView llParticularsOfInformationWatermark;

    private SelectOtherBean selectOtherBean = new SelectOtherBean();
    private String forId;
    private String proId;
    private EditText etDialogForward;
    private EditText etDialogDownload;

    @Override
    public int getLayoutId() {
        return R.layout.activity_documents_of_concern;
    }

    @Override
    public DocumentsReceivedContract.Presenter createPresenter() {
        return new DocumentsReceivedPresenter(this);
    }

    @Override
    public DocumentsReceivedContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvParticularsOfInformationTop);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        forId = getIntent().getStringExtra("for_id");
        proId = getIntent().getStringExtra("pro_id");
        TreeMap<String, String> selectMyInfoMap = new TreeMap<>();
        selectMyInfoMap.put("for_id", forId);
        getPresenter().getSelectOtherInfo(selectMyInfoMap, true, false);
    }

    private void setDocumentsReceivedDialog(final String falg, final String forId, final String isFollow) {
        final TextView tv;
        final Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
        //Dialog布局
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.dialog_center, null);
        tv = root.findViewById(R.id.tv_dialog_center_end);
        switch (falg) {
            case "0":
                tv.setText("是否锁定？");
                break;
            case "1":
                tv.setText("是否取消关注？");
                break;
        }

        //初始化视图
        root.findViewById(R.id.dialog_center_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (falg) {
                    case "0":
                        TreeMap<String, String> lockingMap = new TreeMap<>();
                        lockingMap.put("userid", (String) SPUtil.get(ParticularsOfInformationDocumentsOfConcernActivity.this, IConstants.USER_ID, ""));
                        lockingMap.put("for_id", forId);
                        getPresenter().getLockedFilesAdd(lockingMap, true, false);
                        break;
                    case "1":
                        TreeMap<String, String> followMap = new TreeMap<>();
                        followMap.put("for_id", forId);
                        followMap.put("attention", isFollow);
                        getPresenter().getReceiveFollow(followMap, true, false);
                        break;
                }
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

    private void setDialog(final String str, final String forId) {
        final Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
        int layout = 0;
        //Dialog布局
        switch (str) {
            case "1":
                layout = R.layout.dialog_bottom_forward_to_forward;
                break;
            case "2":
                layout = R.layout.dialog_bottom_download;
                break;

        }
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(layout, null);
        //初始化视图
        switch (str) {
            case "1":
                etDialogForward = root.findViewById(R.id.et_dialog_forward);
                break;
            case "2":
                etDialogDownload = root.findViewById(R.id.et_dialog_download);
                break;
        }

        root.findViewById(R.id.bt_dialog_bottom_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (str) {
                    case "1":
                        if (etDialogForward.getText().toString().trim().length() == 11 && VerifyUtils.isMobileNumber(etDialogForward.getText().toString().trim())) {
                            TreeMap<String, String> loginMap = new TreeMap<>();
                            loginMap.put("userid", (String) SPUtil.get(ParticularsOfInformationDocumentsOfConcernActivity.this, IConstants.USER_ID, ""));
                            loginMap.put("for_id", forId);
                            loginMap.put("accept", etDialogForward.getText().toString().trim());
                            getPresenter().getReceiveForward(loginMap, true, false);
                            mCameraDialog.dismiss();
                        } else
                            ToastUtil.showShortToast("请输入正确的手机号！");
                        break;
                    case "2":
                        if (!etDialogDownload.getText().toString().trim().equals("") && VerifyUtils.isEmail(etDialogDownload.getText().toString().trim())) {
                            TreeMap<String, String> loginMap = new TreeMap<>();
                            loginMap.put("userid", (String) SPUtil.get(ParticularsOfInformationDocumentsOfConcernActivity.this, IConstants.USER_ID, ""));
                            loginMap.put("dowid", forId);
                            loginMap.put("mailbox", etDialogDownload.getText().toString().trim());
                            getPresenter().getReceiveDownload(loginMap, true, false);
                            mCameraDialog.dismiss();
                        } else
                            ToastUtil.showShortToast("请输入正确的邮箱！");
                        break;
                }


                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
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
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    private void setShareDialog() {
        final Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
        //Dialog布局
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.dialog_share, null);
        root.findViewById(R.id.iv_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare("http://121.199.8.244:2837/html/share.html?user=" + SPUtil.get(ParticularsOfInformationDocumentsOfConcernActivity.this, IConstants.USER_ID, "") + "&pro=" + proId, Wechat.NAME);
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

    /**
     * 界面分享到微信微博QQ等其他平台的方法
     */

    //其中url是当前被访问的界面（网页）的地址
    private void showShare(String url, String platform) {
        OnekeyShare oks = new OnekeyShare();
        if (platform != null) {
            oks.setPlatform(platform);
        }
        oks.disableSSOWhenAuthorize();
        oks.setTitle("阅阅");
        oks.setText("专业文件资料管理工具，隐私传阅，操作简便，实用性强，档案私密性高，永久免费不当机！");
        Bitmap bitmap = BitmapFactory.decodeResource(this.getApplicationContext().getResources(), R.mipmap.logo);//显示APP本身自带图片
        oks.setImageData(bitmap);//bitmap格式图片
        oks.setUrl(url);
        oks.setComment("很棒，值得分享！！");
        oks.show(this);
    }

    @OnClick({R.id.ll_particulars_of_information_id_card, R.id.ll_particulars_of_information_marriage_certificate, R.id.ll_particulars_of_information_household_registration_book, R.id.ll_particulars_of_information_property_ownership_certificate, R.id.ll_particulars_of_information_collateral, R.id.ll_particulars_of_information_business_license, R.id.ll_particulars_of_information_credit_report, R.id.ll_particulars_of_information_bank_running_water, R.id.ll_particulars_of_information_assessment_report, R.id.ll_particulars_of_information_production_adjustment, R.id.ll_particulars_of_information_supplementary_materials, R.id.bt_forward, R.id.bt_download, R.id.bt_locking, R.id.bt_no_follow, R.id.tv_top_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_particulars_of_information_id_card:
                NullLayoutActivityForReceiveFile.launch(this, 0, forId);
                break;
            case R.id.ll_particulars_of_information_marriage_certificate:
                NullLayoutActivityForReceiveFile.launch(this, 1, forId);
                break;
            case R.id.ll_particulars_of_information_household_registration_book:
                NullLayoutActivityForReceiveFile.launch(this, 2, forId);
                break;
            case R.id.ll_particulars_of_information_property_ownership_certificate:
                NullLayoutActivityForReceiveFile.launch(this, 3, forId);
                break;
            case R.id.ll_particulars_of_information_collateral:
                NullLayoutActivityForReceiveFile.launch(this, 4, forId);
                break;
            case R.id.ll_particulars_of_information_business_license:
                NullLayoutActivityForReceiveFile.launch(this, 5, forId);
                break;
            case R.id.ll_particulars_of_information_credit_report:
                NullLayoutActivityForReceiveFile.launch(this, 6, forId);
                break;
            case R.id.ll_particulars_of_information_bank_running_water:
                NullLayoutActivityForReceiveFile.launch(this, 7, forId);
                break;
            case R.id.ll_particulars_of_information_assessment_report:
                NullLayoutActivityForReceiveFile.launch(this, 8, forId);
                break;
            case R.id.ll_particulars_of_information_production_adjustment:
                NullLayoutActivityForReceiveFile.launch(this, 9, forId);
                break;
            case R.id.ll_particulars_of_information_supplementary_materials:
                NullLayoutActivityForReceiveFile.launch(this, 10, forId);
                break;
            case R.id.bt_forward:
                setDialog("1", forId);
                break;
            case R.id.bt_download:
                setDialog("2", forId);
                break;
            case R.id.bt_locking:
                setDocumentsReceivedDialog("0", forId, "");
                break;
            case R.id.bt_no_follow:
                String isFollow;
                if (selectOtherBean.getData().getForward().getFor_attention() == 0)
                    isFollow = "1";
                else
                    isFollow = "0";
                setDocumentsReceivedDialog("1", forId, isFollow);
                break;
            case R.id.tv_top_share:
                setShareDialog();
                break;
        }
    }

    @Override
    public void resultDocumentsReceived(DocumentsReceivedBean data) {

    }

    @Override
    public void resultReceiveForward(ReceiveForwardBean data) {
        ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public void resultReceiveDownload(ReceiveDownloadBean data) {
        ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public void resultLockedFilesAdd(LockedFilesAddBean data) {
        btLocking.setTextColor(getResources().getColor(R.color.tx_not_select_fragment));
        btLocking.setEnabled(false);
        ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public void resultReceiveFollow(ReceiveFollowBean data) {
        Intent intent = new Intent("android.intent.action.CART_BROADCAST");
        intent.putExtra("data", "refresh3");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        sendBroadcast(intent);
        finish();
    }

    @Override
    public void resultSelectOtherInfo(SelectOtherBean data) {
        selectOtherBean = data;
        if (!VerifyUtils.isMobileNumber(data.getData().getForward().getHostname()))
            tvOriginator.setText(data.getData().getForward().getHostname());
        if (!VerifyUtils.isMobileNumber(data.getData().getForward().getSponsorname()))
            tvForwarder.setText(data.getData().getForward().getSponsorname());
        tvForwardingTime.setText(DateUtils.time(data.getData().getForward().getFor_addtime()));
        tvNameOfBorrower.setText(data.getData().getProfile().getPro_name());
        tvIdCardNum.setText(data.getData().getProfile().getPro_card().substring(0, data.getData().getProfile().getPro_card().length() - 6) + "******");
        if (data.getData().getProfile().getPro_handle() == 0)
            tvHandlingType.setText("机构");
        else
            tvHandlingType.setText("个人");
        tvLoanAmount.setText(NumberUtils.formatOneDecimal((float) data.getData().getProfile().getPro_amount() / 10000) + "万元");
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

        if (data.getData().getForward().getFor_watermark() == 0)
            llParticularsOfInformationWatermark.setBackgroundResource(0);
        if (data.getData().getForward().getFor_send() != 0) {
            btForward.setTextColor(getResources().getColor(R.color.tx_not_select_fragment));
            btForward.setEnabled(false);
        }
        if (data.getData().getForward().getFor_download() != 0) {
            btDownload.setTextColor(getResources().getColor(R.color.tx_not_select_fragment));
            btDownload.setEnabled(false);
        }
        if (data.getData().getForward().getFor_locking() != 0) {
            btLocking.setTextColor(getResources().getColor(R.color.tx_not_select_fragment));
            btLocking.setEnabled(false);
        }
        if (data.getData().getForward().getFor_attention() == 0) {
            btNoFollow.setTextColor(getResources().getColor(R.color.tx_not_select_fragment));
            btNoFollow.setEnabled(false);
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
