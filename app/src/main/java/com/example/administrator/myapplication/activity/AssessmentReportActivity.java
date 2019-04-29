package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.AssessmentReportAdapter;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.GetAssessmentImgBean;
import com.example.administrator.myapplication.bean.GetBankImgBean;
import com.example.administrator.myapplication.bean.GetCollateralImgBean;
import com.example.administrator.myapplication.bean.GetHouseImgBean;
import com.example.administrator.myapplication.bean.GetIdImgBean;
import com.example.administrator.myapplication.bean.GetMarryImgBean;
import com.example.administrator.myapplication.bean.IdImgDataBean;
import com.example.administrator.myapplication.bean.UploadImgBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.config.UrlConfig;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.IdContract;
import com.example.administrator.myapplication.presenter.IdPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.SPUtil;
import com.example.administrator.myapplication.utils.StringUtils;
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
 * Description ：评估报告
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/12.
 */
public class AssessmentReportActivity extends BaseActivity<IdContract.View, IdContract.Presenter> implements IdContract.View {

    @BindView(R.id.tv_assessment_report_top)
    TopView tvAssessmentReportTop;
    @BindView(R.id.tv_top_add)
    TextView tvAddTop;
    @BindView(R.id.rv_assessment_report)
    RecyclerView rvAssessmentReport;
    @BindView(R.id.bt_assessment_report)
    Button btAssessmentReport;

    private AssessmentReportAdapter assessmentReportAdapter;
    private List<GetAssessmentImgBean.DataBean> list = new ArrayList<>();
    private String proid;//资料id
    private int isAdd;//0：添加   1：修改

    @Override
    public int getLayoutId() {
        return R.layout.activity_assessment_report;
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
        ImmersionBar.setTitleBar(this, tvAssessmentReportTop);

        isAdd = getIntent().getIntExtra("isAdd", 0);
        proid = getIntent().getStringExtra("proidId");

        //设置RecyclerView方向和是否反转
        GridLayoutManager NotUseList = new GridLayoutManager(this, 4);
        rvAssessmentReport.setLayoutManager(NotUseList);
        rvAssessmentReport.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvAssessmentReport.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        list.add(getImageData());
        assessmentReportAdapter = new AssessmentReportAdapter(list);
        rvAssessmentReport.setAdapter(assessmentReportAdapter);
    }


    private GetAssessmentImgBean.DataBean getImageData(){
        //初始化数据
        GetAssessmentImgBean.DataBean dataBean = new GetAssessmentImgBean.DataBean();
        dataBean.setAss_proid(Integer.parseInt(proid));
        return dataBean;
    }
    GetAssessmentImgBean.DataBean clickDataBean;
    @Override
    public void initListener() {
        assessmentReportAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                clickDataBean = list.get(position);
                switch (view.getId()) {
                    case R.id.iv_household_registration:
                        if (TextUtils.isEmpty(clickDataBean.getAss_imgurl())){
                            selectPhoto();
                        }else {
                            BigImageActivity.launch(AssessmentReportActivity.this,clickDataBean.getAss_imgurl());
                        }
                        break;
                    case R.id.iv_household_registration_del:
                        list.remove(position);
                        assessmentReportAdapter.notifyItemRemoved(position);
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
            getPresenter().getAssessmentImg(getIdImgMap, true, false);
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

    @OnClick({R.id.bt_assessment_report})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_assessment_report:
                if (assessmentReportAdapter.isCanAdd()){
                        TreeMap<String, String> idImgDataMap = new TreeMap<>();
                        idImgDataMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
                        idImgDataMap.put("proid", proid);
                        idImgDataMap.put("data", assessmentReportAdapter.getLoadString());
                        getPresenter().modifyAssessmentImgData(idImgDataMap, true, false);
                }
                break;
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        String idImgPath = data.getData().get(0);
        clickDataBean.setAss_imgurl(idImgPath);
        list.add(getImageData());
        assessmentReportAdapter.notifyDataSetChanged();
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

    }

    @Override
    public void resultGetHouseImg(GetHouseImgBean data) {

    }

    @Override
    public void resultModifyCollateralImgData(String data) {

    }

    @Override
    public void resultGetCollateralImg(GetCollateralImgBean data) {

    }

    @Override
    public void resultModifyAssessmentImgData(String data) {
        Gson g = new Gson();
        IdImgDataBean obj = g.fromJson(data, IdImgDataBean.class);
        ToastUtil.showShortToast(obj.getMsg());
        if (obj.isData()) {
            setResult(RESULT_OK, new Intent().putExtra("assessmentCardResult", "1"));
            finish();
        }
    }

    @Override
    public void resultGetAssessmentImg(GetAssessmentImgBean data) {
        list.clear();
        list.addAll(data.getData());
        list.add(getImageData());
        assessmentReportAdapter.setNewData(list);
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
