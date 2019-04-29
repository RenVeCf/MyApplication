package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.EnterpriseInformationAdapter;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.GetCompanyInform;
import com.example.administrator.myapplication.bean.GetCreditReportBean;
import com.example.administrator.myapplication.bean.GetEnterpriseBean;
import com.example.administrator.myapplication.bean.GetHouseholdRegistrationBookBean;
import com.example.administrator.myapplication.bean.IdImgDataBean;
import com.example.administrator.myapplication.bean.UploadImgBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.config.UrlConfig;
import com.example.administrator.myapplication.common.view.CustomLinearLayoutManager;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.HouseholdRegistrationBookContract;
import com.example.administrator.myapplication.presenter.HouseholdRegistrationBookPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.SPUtil;
import com.example.administrator.myapplication.utils.StringUtils;
import com.example.administrator.myapplication.utils.ToastUtil;
import com.example.administrator.myapplication.weight.MyRecycleview;
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
 * Description ：企业信息
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/12.
 */
public class EnterpriseInformationActivity extends BaseActivity<HouseholdRegistrationBookContract.View, HouseholdRegistrationBookContract.Presenter> implements HouseholdRegistrationBookContract.View {

    @BindView(R.id.tv_enterprise_information_top)
    TopView tvEnterpriseInformationTop;
    @BindView(R.id.rv_enterprise_information)
    MyRecycleview rvEnterpriseInformation;
    @BindView(R.id.bt_enterprise_information)
    Button btEnterpriseInformation;

    private List<GetCompanyInform.DataBean.StroBean.StroSecondBean> multipleAdapterList;
    private EnterpriseInformationAdapter mGrowthValueAdapter;

    private String proid;//资料id
    private int isAdd;//0：添加   1：修改

    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprise_information;
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
        ImmersionBar.setTitleBar(this, tvEnterpriseInformationTop);

        isAdd = getIntent().getIntExtra("isAdd", 0);
        proid = getIntent().getStringExtra("proidId");

        //设置RecyclerView方向和是否反转
        CustomLinearLayoutManager NotUseList = new CustomLinearLayoutManager(this);
        NotUseList.setScrollEnabled(false);
        rvEnterpriseInformation.setLayoutManager(NotUseList);
        rvEnterpriseInformation.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvEnterpriseInformation.setItemAnimator(new DefaultItemAnimator()); //默认动画
        //初始化数据
        multipleAdapterList = new ArrayList<>();
        mGrowthValueAdapter = new EnterpriseInformationAdapter(this, multipleAdapterList);
        rvEnterpriseInformation.setAdapter(mGrowthValueAdapter);
    }

    private GetCompanyInform.DataBean.StroBean.StroSecondBean getInsertData(boolean isAddSecond){
        GetCompanyInform.DataBean.StroBean.StroSecondBean bean = new GetCompanyInform.DataBean.StroBean.StroSecondBean();
        List<GetCompanyInform.DataBean.StroBean.StroSecondBean.StroThreeBean> list = new ArrayList<>();
        list.add(getInsertSecondData());
        if (isAddSecond){
            bean.data = list;
        }
        return bean;
    }
  private GetCompanyInform.DataBean.StroBean.StroSecondBean.StroThreeBean getInsertSecondData(){
        GetCompanyInform.DataBean.StroBean.StroSecondBean.StroThreeBean bean = new GetCompanyInform.DataBean.StroBean.StroSecondBean.StroThreeBean();
        bean.lic_proid = Integer.parseInt(proid);
        return bean;
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
            getPresenter().getEnterpriseImgData(getIdImgMap, true, false);
        }else {
            multipleAdapterList.add( getInsertData(true));
            multipleAdapterList.add( getInsertData(true));
            multipleAdapterList.add( getInsertData(true));
            multipleAdapterList.add( getInsertData(true));
            multipleAdapterList.add( getInsertData(true));
            mGrowthValueAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({ R.id.bt_enterprise_information})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//
            case R.id.bt_enterprise_information:
                if (mGrowthValueAdapter.isCanAdd()){
                    TreeMap<String, String> idImgDataMap = new TreeMap<>();
                    idImgDataMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
                    idImgDataMap.put("proid", proid);
                    idImgDataMap.put("data", mGrowthValueAdapter.getLoadString());
                    getPresenter().modifyEnterpriseImgData(idImgDataMap, true, false);
                }
                break;
        }
    }

    public void SelectPhotoMultiple00000() {
        /**
         * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
         * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
         */
        PictureSelector.create(this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(false, 200, 200, 1, 1);
    }


    private int   position,childPosition;
    public void SelectPhotoEnterprise(int position, int childPosition) {
        this.position = position;
        this.childPosition = childPosition;
        /**
         * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
         * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
         */
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

    @Override
    public void resultUploadImg(UploadImgBean data) {
        String idImgPath = data.getData().get(0);
        GetCompanyInform.DataBean.StroBean.StroSecondBean bean = multipleAdapterList.get(position);
        GetCompanyInform.DataBean.StroBean.StroSecondBean.StroThreeBean stroThreeBean = bean.data.get(childPosition);
        stroThreeBean.lic_imgurl = idImgPath;
        if (position>1){
            bean.data.add(getInsertSecondData());
        }
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
        Gson g = new Gson();
        IdImgDataBean obj = g.fromJson(data, IdImgDataBean.class);
        ToastUtil.showShortToast(obj.getMsg());
        if (obj.isData()) {
            setResult(RESULT_OK, new Intent().putExtra("enterpriseResult", "1"));
            finish();
        }
    }

    @Override
    public void resultGetEnterpriseImg(GetCompanyInform data) {
        GetCompanyInform.DataBean data1 = data.data;
        GetCompanyInform.DataBean.StroBean stro = data1.stro;
        if (stro._$1.data == null || stro._$1.data.size() == 0){
            stro._$1.data.add(getInsertSecondData());
        }
        multipleAdapterList.add(stro._$1);
        if (stro._$2.data == null || stro._$2.data.size() == 0){
            stro._$2.data.add(getInsertSecondData());
        }
        multipleAdapterList.add(stro._$2);
        stro._$3.data.add(getInsertSecondData());
        multipleAdapterList.add(stro._$3);
        stro._$4.data.add(getInsertSecondData());
        multipleAdapterList.add(stro._$4);
        stro._$5.data.add(getInsertSecondData());
        multipleAdapterList.add(stro._$5);
        mGrowthValueAdapter.notifyDataSetChanged();
    }

    @Override
    public void resultModifyCreditReportImgData(String data) {

    }

    @Override
    public void resultGetCreditReportImg(GetCreditReportBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
