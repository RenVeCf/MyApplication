package com.example.administrator.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.GetMyInfoBean;
import com.example.administrator.myapplication.bean.UploadDataBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.UploadDataContract;
import com.example.administrator.myapplication.presenter.UploadDataPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.LogUtils;
import com.example.administrator.myapplication.utils.SPUtil;
import com.example.administrator.myapplication.utils.ToastUtil;
import com.example.administrator.myapplication.utils.VerifyUtils;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

/**
 * Description ：上传资料
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/3.
 */
public class UploadDataActivity extends BaseActivity<UploadDataContract.View, UploadDataContract.Presenter> implements UploadDataContract.View {

    @BindView(R.id.tv_upload_data_top)
    TopView tvUploadDataTop;
    @BindView(R.id.et_upload_data_name)
    EditText etUploadDataName;
    @BindView(R.id.et_upload_data_id_card)
    EditText etUploadDataIdCard;
    @BindView(R.id.et_upload_data_contact_number)
    EditText etUploadDataContactNumber;
    @BindView(R.id.et_upload_data_loan_amount)
    EditText etUploadDataLoanAmount;
    @BindView(R.id.ll_upload_data_handling_type)
    LinearLayout llUploadDataHandlingType;
    @BindView(R.id.ll_upload_data_marital_status)
    LinearLayout llUploadDataMaritalStatus;
    @BindView(R.id.ll_upload_data_spare_room)
    LinearLayout llUploadDataSpareRoom;
    @BindView(R.id.ll_upload_data_brief_outline)
    LinearLayout llUploadDataBriefOutline;
    @BindView(R.id.bt_upload_data_next)
    Button btUploadDataNext;
    @BindView(R.id.tv_handling_type)
    TextView tvHandlingType;
    @BindView(R.id.tv_marital_status)
    TextView tvMaritalStatus;
    @BindView(R.id.tv_spare_room)
    TextView tvSpareRoom;

    private OptionsPickerView pvOptions;
    private List<String> listData;
    private int handlingTypeFlag = 0;
    private int maritalStatusFlag = 0;
    private int spareRoomFlag = 0;

    private String usageOfLoanResult = "";
    private String paymentResult = "";
    private String monthlyAverageFlowResult = "";
    private String annualOpeningResult = "";

    private int flag = 0;
    private String proId = "";
    private String schId = "";
    private String nextProId = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_upload_data;
    }

    @Override
    public UploadDataContract.Presenter createPresenter() {
        return new UploadDataPresenter(this);
    }

    @Override
    public UploadDataContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvUploadDataTop);

        flag = getIntent().getIntExtra("my_info", 0);
        if (getIntent().getStringExtra("pro_id") != null && !getIntent().getStringExtra("pro_id").equals(""))
            proId = getIntent().getStringExtra("pro_id");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        if (flag == 1) {
            TreeMap<String, String> getMyInfoMap = new TreeMap<>();
            getMyInfoMap.put("userid", (String) SPUtil.get(ApplicationUtil.getContext(), IConstants.USER_ID, ""));
            getMyInfoMap.put("proid", proId);
            getPresenter().getMyInfo(getMyInfoMap, true, false);
        }
    }

    @Override
    public void resultUploadData(UploadDataBean data) {
        nextProId = data.getData() + "";
        LogUtils.i("nextProId = " + nextProId);
        startActivity(new Intent(this, CardInfoActivity.class).putExtra("proid", nextProId).putExtra("flag", "0"));
        finish();
    }

    @Override
    public void resultModifyData(UploadDataBean data) {
        LogUtils.i("nextProId 1111 = " + nextProId);
        startActivity(new Intent(this, CardInfoActivity.class).putExtra("proid", nextProId).putExtra("flag", "1"));
        finish();
    }

    @Override
    public void resultGetMyInfo(GetMyInfoBean data) {
        etUploadDataName.setText(data.getData().getProfile().getPro_name());
        etUploadDataIdCard.setText(data.getData().getProfile().getPro_card());
        etUploadDataContactNumber.setText(data.getData().getProfile().getPro_phone());
        etUploadDataLoanAmount.setText(data.getData().getProfile().getPro_amount() + "");
        if (data.getData().getProfile().getPro_handle() == 0)
            tvHandlingType.setText("机构");
        else
            tvHandlingType.setText("个人");
        if (data.getData().getProfile().getPro_marriage() == 0)
            tvMaritalStatus.setText("离异");
        else if (data.getData().getProfile().getPro_marriage() == 1)
            tvMaritalStatus.setText("已婚");
        else
            tvMaritalStatus.setText("未婚");
        if (data.getData().getProfile().getPro_room() == 0)
            tvSpareRoom.setText("是");
        else
            tvSpareRoom.setText("否");
        if (data.getData().getSchema().getSch_loanuse() != null && !data.getData().getSchema().getSch_loanuse().equals(""))
            usageOfLoanResult = data.getData().getSchema().getSch_loanuse() + "";
        if (data.getData().getSchema().getSch_asd() != null && !data.getData().getSchema().getSch_asd().equals(""))
            paymentResult = data.getData().getSchema().getSch_asd() + "";
        if (data.getData().getSchema().getSch_stream() != null && !data.getData().getSchema().getSch_stream().equals(""))
            monthlyAverageFlowResult = data.getData().getSchema().getSch_stream() + "";
        if (data.getData().getSchema().getSch_amount() != null && !data.getData().getSchema().getSch_amount().equals(""))
            annualOpeningResult = data.getData().getSchema().getSch_amount() + "";
        schId = data.getData().getSchema().getSch_id() + "";
        nextProId = data.getData().getProfile().getPro_id() + "";
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }

    /**
     * 展示选择器
     * 核心代码
     */
    private void showPickerView(String type) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

        switch (type) {
            case "0":
                listData = getHandlingTypeData();
                handlingTypeFlag = 1;
                maritalStatusFlag = 0;
                spareRoomFlag = 0;
                break;
            case "1":
                listData = getMaritalStatusData();
                handlingTypeFlag = 0;
                maritalStatusFlag = 1;
                spareRoomFlag = 0;
                break;
            case "2":
                listData = getSpareRoomData();
                handlingTypeFlag = 0;
                maritalStatusFlag = 0;
                spareRoomFlag = 1;
                break;
        }

        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                if (handlingTypeFlag == 1) {
                    tvHandlingType.setText(listData.get(options1));

                } else if (maritalStatusFlag == 1) {
                    tvMaritalStatus.setText(listData.get(options1));
                } else if (spareRoomFlag == 1) {
                    tvSpareRoom.setText(listData.get(options1));
                }
            }
        })
                .setDividerColor(getResources().getColor(R.color.white))//设置分割线的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final Button tvSubmit = (Button) v.findViewById(R.id.bt_pickview_confirm);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.returnData();
                                pvOptions.dismiss();
                            }
                        });
                    }
                })
                .setSelectOptions(1)//设置选择第一个
                .setOutSideCancelable(true)//点击背的地方不消失
                .build();//创建
        pvOptions.setPicker(listData);
        pvOptions.show();
    }

    private List<String> getHandlingTypeData() {
        List<String> list = new ArrayList<>();
        list.add("机构");
        list.add("个人");
        return list;
    }

    private List<String> getMaritalStatusData() {
        List<String> list = new ArrayList<>();
        list.add("离异");
        list.add("已婚");
        list.add("未婚");
        return list;
    }

    private List<String> getSpareRoomData() {
        List<String> list = new ArrayList<>();
        list.add("是");
        list.add("否");
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case IConstants.REQUEST_CODE_94:
                    LogUtils.i("jjjjjjjjjjjjjjjjjj");
//                    flag = 1;
                    if (data.getStringExtra("usageOfLoanResult") != null && !data.getStringExtra("usageOfLoanResult").equals("")) {
                        usageOfLoanResult = data.getStringExtra("usageOfLoanResult");
                    }
                    if (data.getStringExtra("paymentResult") != null && !data.getStringExtra("paymentResult").equals("")) {
                        paymentResult = data.getStringExtra("paymentResult");
                    }
                    if (data.getStringExtra("monthlyAverageFlowResult") != null && !data.getStringExtra("monthlyAverageFlowResult").equals("")) {
                        monthlyAverageFlowResult = data.getStringExtra("monthlyAverageFlowResult");
                    }
                    if (data.getStringExtra("annualOpeningResult") != null && !data.getStringExtra("annualOpeningResult").equals("")) {
                        annualOpeningResult = data.getStringExtra("annualOpeningResult");
                    }
                    break;
            }
        }
    }

    @OnClick({R.id.ll_upload_data_handling_type, R.id.ll_upload_data_marital_status, R.id.ll_upload_data_spare_room, R.id.ll_upload_data_brief_outline, R.id.bt_upload_data_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_upload_data_handling_type:
                showPickerView("0");
                break;
            case R.id.ll_upload_data_marital_status:
                showPickerView("1");
                break;
            case R.id.ll_upload_data_spare_room:
                showPickerView("2");
                break;
            case R.id.ll_upload_data_brief_outline:
                if (flag == 0)
                    startActivityForResult(new Intent(this, BriefOutlineActivity.class), IConstants.REQUEST_CODE_94);
                else {
                    startActivityForResult(new Intent(this, BriefOutlineActivity.class)
                            .putExtra("usageOfLoan", usageOfLoanResult)
                            .putExtra("payment", paymentResult)
                            .putExtra("monthlyAverageFlow", monthlyAverageFlowResult)
                            .putExtra("annualOpening", annualOpeningResult), IConstants.REQUEST_CODE_94);
                }
                break;
            case R.id.bt_upload_data_next:
                if (etUploadDataName.getText().toString().trim().equals("")) {
                    ToastUtil.showShortToast("请输入姓名");
                } else if (etUploadDataIdCard.getText().toString().trim().equals("")) {
                    ToastUtil.showShortToast("请输入身份证号");
                } else if (etUploadDataContactNumber.getText().toString().trim().equals("")) {
                    ToastUtil.showShortToast("请输入联系电话");
                } else if (etUploadDataLoanAmount.getText().toString().trim().equals("")) {
                    ToastUtil.showShortToast("请输入借款金额");
                } else if (tvHandlingType.getText().toString().trim().equals("")) {
                    ToastUtil.showShortToast("请选择办理类型");
                } else if (tvMaritalStatus.getText().toString().trim().equals("")) {
                    ToastUtil.showShortToast("请选择婚姻状况");
                } else if (tvSpareRoom.getText().toString().trim().equals("")) {
                    ToastUtil.showShortToast("请选择备用房");
                } else if (!VerifyUtils.isChineseCard(etUploadDataIdCard.getText().toString())) {
                    ToastUtil.showShortToast(getResources().getString(R.string.id_card_error));
                } else if (etUploadDataContactNumber.getText().toString().trim().length() != 11 || !VerifyUtils.isMobileNumber(etUploadDataContactNumber.getText().toString().trim())) {
                    ToastUtil.showShortToast(getResources().getString(R.string.error_phone_num));
                } else {
                    TreeMap<String, String> uploadDataMap = new TreeMap<>();
                    //用户id
                    uploadDataMap.put("pro_userid", (String) SPUtil.get(ApplicationUtil.getContext(), IConstants.USER_ID, ""));
                    //名字
                    uploadDataMap.put("pro_name", etUploadDataName.getText().toString().trim());
                    //身份证
                    uploadDataMap.put("pro_card", etUploadDataIdCard.getText().toString().trim());
                    //手机号码
                    uploadDataMap.put("pro_phone", etUploadDataContactNumber.getText().toString().trim());
                    //0:机构 1：个人
                    if (tvHandlingType.getText().toString().trim().equals("机构"))
                        uploadDataMap.put("pro_handle", "0");
                    else
                        uploadDataMap.put("pro_handle", "1");
                    //0:离异 1：已婚 2:未婚
                    if (tvMaritalStatus.getText().toString().trim().equals("离异"))
                        uploadDataMap.put("pro_marriage", "0");
                    else if (tvMaritalStatus.getText().toString().trim().equals("已婚"))
                        uploadDataMap.put("pro_marriage", "1");
                    else
                        uploadDataMap.put("pro_marriage", "2");
                    //0：是 1：否
                    if (tvSpareRoom.getText().toString().trim().equals("是"))
                        uploadDataMap.put("pro_room", "0");
                    else
                        uploadDataMap.put("pro_room", "1");
                    //申请金额
                    uploadDataMap.put("pro_amount", etUploadDataLoanAmount.getText().toString().trim());
                    //借款用途
                    uploadDataMap.put("sch_loanuse", usageOfLoanResult);
                    //还款来源
                    uploadDataMap.put("sch_asd", paymentResult);
                    //月均流水
                    uploadDataMap.put("sch_stream", monthlyAverageFlowResult);
                    //年开票额
                    uploadDataMap.put("sch_amount", annualOpeningResult);
                    if (flag == 0)
                        getPresenter().uploadData(uploadDataMap, true, false);
                    else {
                        LogUtils.i("pro_id = " + nextProId);
                        LogUtils.i("sch_id = " + schId);
                        uploadDataMap.put("pro_id", nextProId);
                        uploadDataMap.put("sch_id", schId);
                        getPresenter().modifyData(uploadDataMap, true, false);
                    }
                }
                break;
        }
    }
}
