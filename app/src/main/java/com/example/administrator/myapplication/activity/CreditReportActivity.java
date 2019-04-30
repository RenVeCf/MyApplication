package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.CreditReportAdapter;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.GetCompanyInform;
import com.example.administrator.myapplication.bean.GetCreditReportBean;
import com.example.administrator.myapplication.bean.GetHouseholdRegistrationBookBean;
import com.example.administrator.myapplication.bean.IdImgDataBean;
import com.example.administrator.myapplication.bean.UploadImgBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.HouseholdRegistrationBookContract;
import com.example.administrator.myapplication.presenter.HouseholdRegistrationBookPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.SPUtil;
import com.example.administrator.myapplication.utils.ToastUtil;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.wildma.pictureselector.PictureSelector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import okhttp3.RequestBody;

import static com.example.administrator.myapplication.activity.PersonalDataActivity.getImageRequestBody;

/**
 * Description ：信用报告
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/12.
 */
public class CreditReportActivity extends BaseActivity<HouseholdRegistrationBookContract.View, HouseholdRegistrationBookContract.Presenter> implements HouseholdRegistrationBookContract.View {
    @BindView(R.id.tv_credit_report_top)
    TopView tvCreditReportTop;
    @BindView(R.id.rv_business_license)
    RecyclerView rvBusinessLicense;
    @BindView(R.id.bt_credit_report)
    Button btCreditReport;

    private CreditReportAdapter mGrowthValueAdapter;
    private List<GetCreditReportBean.DataBeanX> multipleAdapterList;
    private String proid;//资料id
    private int isAdd;//0：添加   1：修改

    private GetCreditReportBean.DataBeanX getInsertData(){
        GetCreditReportBean.DataBeanX bean = new GetCreditReportBean.DataBeanX();
        List<GetCreditReportBean.DataBeanX.DataBean> list = new ArrayList<>();
        list.add(getInsertSecondData());
        bean.data = list;
        return bean;
    }
    private GetCreditReportBean.DataBeanX.DataBean getInsertSecondData(){
        GetCreditReportBean.DataBeanX.DataBean bean = new GetCreditReportBean.DataBeanX.DataBean();
        bean.cre_proid = Integer.parseInt(proid);
        return bean;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_credit_report;
    }

    @Override
    public HouseholdRegistrationBookContract.Presenter createPresenter() {
        return new HouseholdRegistrationBookPresenter(this);
    }

    @Override
    public HouseholdRegistrationBookContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvCreditReportTop);

        isAdd = getIntent().getIntExtra("isAdd", 0);
        proid = getIntent().getStringExtra("proidId");

        //设置RecyclerView方向和是否反转
        LinearLayoutManager NotUseList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvBusinessLicense.setLayoutManager(NotUseList);
        rvBusinessLicense.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvBusinessLicense.setItemAnimator(new DefaultItemAnimator()); //默认动画
        //初始化数据
        multipleAdapterList = new ArrayList<>();
        multipleAdapterList.add( getInsertData());
        multipleAdapterList.add( getInsertData());
        multipleAdapterList.add( getInsertData());
        multipleAdapterList.add( getInsertData());
        mGrowthValueAdapter = new CreditReportAdapter(this, multipleAdapterList);
        rvBusinessLicense.setAdapter(mGrowthValueAdapter);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
        if (isAdd == 1) {
            TreeMap<String, String> getIdImgMap = new TreeMap<>();
            getIdImgMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
            getIdImgMap.put("proid", proid);
            getPresenter().getCreditReportImgData(getIdImgMap, true, false);
        }
    }

    private int position, childPosition;

    public void SelectPhotoEnterprise(int position, int childPosition) {
        this.position = position;
        this.childPosition = childPosition;
        PictureSelector.create(this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(false, 200, 200, 1, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureSelector.SELECT_REQUEST_CODE:
                if (data != null) {
                    String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                    Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                    map.put("image\";filename=\"" + ".jpeg", getImageRequestBody(picturePath));
                    getPresenter().uploadImg(map, true, false);
                }
                break;
        }
    }

    @OnClick({R.id.bt_credit_report})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_credit_report:
                if (mGrowthValueAdapter.isCanAdd()){
                    TreeMap<String, String> idImgDataMap = new TreeMap<>();
                    idImgDataMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
                    idImgDataMap.put("proid", proid);
                    idImgDataMap.put("data", mGrowthValueAdapter.getLoadString());
                    getPresenter().modifyCreditReportImgData(idImgDataMap, true, false);
                }
                break;
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        GetCreditReportBean.DataBeanX dataBeanX = multipleAdapterList.get(position);
        List<GetCreditReportBean.DataBeanX.DataBean> data1 = dataBeanX.data;
        data1.get(childPosition).cre_imgurl = data.getData().get(0);
        data1.add(getInsertSecondData());
        mGrowthValueAdapter.notifyDataSetChanged();
    }

    @Override
    public void resultModifyHouseholdRegistrationBookImgData(String data) {

    }

    @Override
    public void resultGetHouseholdRegistrationBookImg(GetHouseholdRegistrationBookBean data) {

    }

    @Override
    public void resultModifyEnterpriseImgData(String data) {

    }

    @Override
    public void resultGetEnterpriseImg(GetCompanyInform data) {

    }

    @Override
    public void resultModifyCreditReportImgData(String data) {
        Gson g = new Gson();
        IdImgDataBean obj = g.fromJson(data, IdImgDataBean.class);
        ToastUtil.showShortToast(obj.getMsg());
        if (obj.isData()) {
            setResult(RESULT_OK, new Intent().putExtra("creditReportResult", "1"));
            finish();
        }
    }

    @Override
    public void resultGetCreditReportImg(GetCreditReportBean data) {
        for (GetCreditReportBean.DataBeanX dataBeanX:data.data){
            if ("主贷人信用报告".equals(dataBeanX.cre_type)){
                multipleAdapterList.get(0).data.clear();
                multipleAdapterList.get(0).data.addAll(dataBeanX.data);
                multipleAdapterList.get(0).data.add(getInsertSecondData());
            }else if ("配偶信用报告".equals(dataBeanX.cre_type)){
                multipleAdapterList.get(1).data.clear();
                multipleAdapterList.get(1).data.addAll(dataBeanX.data);
                multipleAdapterList.get(1).data.add(getInsertSecondData());
            }else if ("其他权利人信用报告".equals(dataBeanX.cre_type)){
                multipleAdapterList.get(2).data.clear();
                multipleAdapterList.get(2).data.addAll(dataBeanX.data);
                multipleAdapterList.get(2).data.add(getInsertSecondData());
            }else if ("股东信用报告".equals(dataBeanX.cre_type)){
                multipleAdapterList.get(3).data.clear();
                multipleAdapterList.get(3).data.addAll(dataBeanX.data);
                multipleAdapterList.get(3).data.add(getInsertSecondData());
            }
        }
        mGrowthValueAdapter.notifyDataSetChanged();

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
