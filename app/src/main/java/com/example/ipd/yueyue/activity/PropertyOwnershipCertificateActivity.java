package com.example.ipd.yueyue.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.adapter.PropertyOwnershipCertificateAdapter;
import com.example.ipd.yueyue.base.BaseActivity;
import com.example.ipd.yueyue.bean.GetAssessmentImgBean;
import com.example.ipd.yueyue.bean.GetBankImgBean;
import com.example.ipd.yueyue.bean.GetCollateralImgBean;
import com.example.ipd.yueyue.bean.GetHouseImgBean;
import com.example.ipd.yueyue.bean.GetIdImgBean;
import com.example.ipd.yueyue.bean.GetMarryImgBean;
import com.example.ipd.yueyue.bean.IdImgDataBean;
import com.example.ipd.yueyue.bean.UploadImgBean;
import com.example.ipd.yueyue.common.config.IConstants;
import com.example.ipd.yueyue.common.view.TopView;
import com.example.ipd.yueyue.contract.IdContract;
import com.example.ipd.yueyue.presenter.IdPresenter;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.example.ipd.yueyue.utils.SPUtil;
import com.example.ipd.yueyue.utils.ToastUtil;
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

import static com.example.ipd.yueyue.activity.PersonalDataActivity.getImageRequestBody;

/**
 * Description ：房产证
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/12.
 */
public class PropertyOwnershipCertificateActivity extends BaseActivity<IdContract.View, IdContract.Presenter> implements IdContract.View {

    @BindView(R.id.tv_property_ownership_certificate_top)
    TopView tvPropertyOwnershipCertificateTop;
    @BindView(R.id.tv_top_add)
    TextView tvIdAdd;
    @BindView(R.id.rv_property_ownership_certificate)
    RecyclerView rvPropertyOwnershipCertificate;
    @BindView(R.id.bt_property_ownership_certificate)
    Button btPropertyOwnershipCertificate;

    private PropertyOwnershipCertificateAdapter propertyOwnershipCertificateAdapter;
    private List<GetHouseImgBean.DataBean> list = new ArrayList<>();
    private int flag = 0;//证的两个面
    private String proid;//资料id
    private int isAdd;//0：添加   1：修改
    //得到一个新的 item的对象 用来创建新的item 标题不包含 身份证，
    private GetHouseImgBean.DataBean getImageData(String title){
        //初始化数据
        GetHouseImgBean.DataBean dataBean = new GetHouseImgBean.DataBean();
        dataBean.setEst_type(title);
        dataBean.setEst_proid(Integer.parseInt(proid));
        return dataBean;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_property_ownership_certificate;
    }

    @Override
    public IdContract.Presenter createPresenter() {
        return new IdPresenter(this);
    }

    @Override
    public IdContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvPropertyOwnershipCertificateTop);

        isAdd = getIntent().getIntExtra("isAdd", 0);
        proid = getIntent().getStringExtra("proidId");

        //设置RecyclerView方向和是否反转
        LinearLayoutManager NotUseList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvPropertyOwnershipCertificate.setLayoutManager(NotUseList);
        rvPropertyOwnershipCertificate.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvPropertyOwnershipCertificate.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        list.add(getImageData("房产证1"));
        propertyOwnershipCertificateAdapter = new PropertyOwnershipCertificateAdapter(list);
        rvPropertyOwnershipCertificate.setAdapter(propertyOwnershipCertificateAdapter);
    }

    GetHouseImgBean.DataBean clickDataBean;
    @Override
    public void initListener() {
        propertyOwnershipCertificateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                clickDataBean = list.get(position);
                switch (view.getId()) {
                    case R.id.tv_delete:
                        propertyOwnershipCertificateAdapter.removeData(position);
                        break;
                    case R.id.iv_property_ownership_certificate_one:
                        if (!TextUtils.isEmpty(clickDataBean.getEst_page1())){
                            BigImageActivity.launch(PropertyOwnershipCertificateActivity.this,clickDataBean.getEst_page1());
                            return;
                        }
                        selectPhoto();
                        flag = 1;
                        break;
                    case R.id.iv_property_ownership_certificate_two:
                        if (!TextUtils.isEmpty(clickDataBean.getEst_page2())){
                            BigImageActivity.launch(PropertyOwnershipCertificateActivity.this,clickDataBean.getEst_page2());
                            return;
                        }
                        selectPhoto();
                        flag = 2;
                        break;
                    case R.id.iv_property_ownership_certificate_three:
                        if (!TextUtils.isEmpty(clickDataBean.getEst_page3())){
                            BigImageActivity.launch(PropertyOwnershipCertificateActivity.this,clickDataBean.getEst_page3());
                            return;
                        }
                        selectPhoto();
                        flag = 3;
                        break;
                    case R.id.iv_property_ownership_certificate_four:
                        if (!TextUtils.isEmpty(clickDataBean.getEst_page4())){
                            BigImageActivity.launch(PropertyOwnershipCertificateActivity.this,clickDataBean.getEst_page4());
                            return;
                        }
                        selectPhoto();
                        flag = 4;
                        break;
                    case R.id.iv_property_ownership_certificate_five:
                        if (!TextUtils.isEmpty(clickDataBean.getEst_page5())){
                            BigImageActivity.launch(PropertyOwnershipCertificateActivity.this,clickDataBean.getEst_page5());
                            return;
                        }
                        selectPhoto();
                        flag = 5;
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        if (isAdd == 1) {
            TreeMap<String, String> getIdImgMap = new TreeMap<>();
            getIdImgMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
            getIdImgMap.put("proid", proid);
            getPresenter().getHouseImg(getIdImgMap, true, false);
        }

    }

    private void selectPhoto() {
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

    @OnClick({R.id.tv_top_add, R.id.bt_property_ownership_certificate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_top_add:
                propertyOwnershipCertificateAdapter.addData(getImageData("房产证"+(list.size()+1)));
                break;
            case R.id.bt_property_ownership_certificate:
                if (propertyOwnershipCertificateAdapter.isCanAdd()){
                    TreeMap<String, String> idImgDataMap = new TreeMap<>();
                    idImgDataMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
                    idImgDataMap.put("proid", proid);
                    idImgDataMap.put("data", propertyOwnershipCertificateAdapter.getLoadString());
                    getPresenter().modifyHouseImgData(idImgDataMap, true, false);
                }

                break;
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        String idImgPath = data.getData().get(0);
        if (flag == 1) {
            clickDataBean.setEst_page1(idImgPath);

        } else if (flag == 2) {
            clickDataBean.setEst_page2(idImgPath);

        } else if (flag == 3) {
            clickDataBean.setEst_page3(idImgPath);
        } else if (flag == 4) {
            clickDataBean.setEst_page4(idImgPath);

        } else if (flag == 5) {
            clickDataBean.setEst_page5(idImgPath);

        }
        propertyOwnershipCertificateAdapter.notifyDataSetChanged();
    }

    @Override
    public void resultIdImgData(String data) {

    }

    @Override
    public void resultGetIdImg(GetIdImgBean data) {

    }

    @Override
    public void resultModifyMarryImgData(String data) {

    }

    @Override
    public void resultGetMarryImg(GetMarryImgBean data) {

    }

    @Override
    public void resultModifyHouseImgData(String data) {
        Gson g = new Gson();
        IdImgDataBean obj = g.fromJson(data, IdImgDataBean.class);
        ToastUtil.showShortToast(obj.getMsg());
        if (obj.isData()) {
            setResult(RESULT_OK, new Intent().putExtra("propertyOwnershipCertificateResult", "1"));
            finish();
        }
    }

    @Override
    public void resultGetHouseImg(GetHouseImgBean data) {
        list.clear();

        list.addAll(data.getData());
        if (list.size() == 0){
            list.add(getImageData("房产证1"));
        }
        propertyOwnershipCertificateAdapter.setNewData(list);
    }

    @Override
    public void resultModifyCollateralImgData(String data) {

    }

    @Override
    public void resultGetCollateralImg(GetCollateralImgBean data) {

    }

    @Override
    public void resultModifyAssessmentImgData(String data) {

    }

    @Override
    public void resultGetAssessmentImg(GetAssessmentImgBean data) {

    }

    @Override
    public void resultModifyBankImgData(String data) {

    }

    @Override
    public void resultGetBankImg(GetBankImgBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
