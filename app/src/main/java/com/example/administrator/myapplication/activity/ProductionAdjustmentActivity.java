package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.ProductionAdjustmentAdapter;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.GetProAdjustBean;
import com.example.administrator.myapplication.bean.IdImgDataBean;
import com.example.administrator.myapplication.bean.UploadImgBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.ProductionAdjustmentContract;
import com.example.administrator.myapplication.presenter.ProductionAdjustmentPresenter;
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
 * Description ：产调
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/9.
 */
public class ProductionAdjustmentActivity extends BaseActivity<ProductionAdjustmentContract.View, ProductionAdjustmentContract.Presenter> implements ProductionAdjustmentContract.View {

    @BindView(R.id.tv_production_adjustment_top)
    TopView tvproduction_adjustmentTop;
    @BindView(R.id.rv_production_adjustment)
    RecyclerView rvproduction_adjustment;
    @BindView(R.id.bt_production_adjustment)
    Button btproduction_adjustment;

    private String proid;//资料id
    private int isAdd;//0：添加   1：修改

    List<GetProAdjustBean.DataBeanFirst> mDatas = new ArrayList<>();
    private ProductionAdjustmentAdapter productionAdjustmentAdapter;


    private GetProAdjustBean.DataBeanFirst getInsertDate(String title){
        GetProAdjustBean.DataBeanFirst bean = new GetProAdjustBean.DataBeanFirst();
        List<GetProAdjustBean.DataBeanFirst.DataBeanSecond> data = new ArrayList<>();
        bean.yi_type = title;
        data.add(getInsertSecondDate(title));
        bean.data = data;
        return bean;
    }
    private GetProAdjustBean.DataBeanFirst.DataBeanSecond getInsertSecondDate(String type){
        GetProAdjustBean.DataBeanFirst.DataBeanSecond bean = new GetProAdjustBean.DataBeanFirst.DataBeanSecond();
        bean.yi_proid = Integer.parseInt(proid);
        bean.yi_type = type;
        return bean;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_production_adjustment;
    }

    @Override
    public ProductionAdjustmentContract.Presenter createPresenter() {
        return new ProductionAdjustmentPresenter(this);
    }

    @Override
    public ProductionAdjustmentContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvproduction_adjustmentTop);

        //设置RecyclerView方向和是否反转
        LinearLayoutManager NotUseList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvproduction_adjustment.setLayoutManager(NotUseList);
        rvproduction_adjustment.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvproduction_adjustment.setItemAnimator(new DefaultItemAnimator()); //默认动画

        productionAdjustmentAdapter = new ProductionAdjustmentAdapter(ProductionAdjustmentActivity.this,mDatas);
        rvproduction_adjustment.setAdapter(productionAdjustmentAdapter);
    }


    @Override
    public void initListener() {
        productionAdjustmentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_delete:
                        mDatas.remove(position);
                        for (int i=0;i<mDatas.size();i++){
                            GetProAdjustBean.DataBeanFirst bean = mDatas.get(i);
                            bean.yi_type = "产调"+(i+1);
                        }
                        productionAdjustmentAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        proid = getIntent().getStringExtra("proidId");
        isAdd = getIntent().getIntExtra("isAdd", 0);

        if (isAdd == 1) {
            TreeMap<String, String> getIdImgMap = new TreeMap<>();
            getIdImgMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
            getIdImgMap.put("proid", proid);
            getPresenter().getData(getIdImgMap, true, false);
        }else {
            mDatas.clear();
            if (mDatas.size() == 0){
                mDatas.add(getInsertDate("产调1"));
            }
            productionAdjustmentAdapter.setNewData(mDatas);
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
//                    map = new HashMap<>();
//                    map.put("yi_userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
//                    map.put("yi_proid", proid);
//                    map.put("yi_type", StringUtils.gbEncoding("产调"));

                    String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                    Map<String, RequestBody> map = new HashMap<String, RequestBody>();
                    map.put("image\";filename=\"" + ".jpeg", getImageRequestBody(picturePath));
                    getPresenter().uploadImg(map, true, false);
                }
                break;
        }
    }

    @OnClick({R.id.bt_production_adjustment, R.id.tv_top_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_production_adjustment: //确认上传
                if (productionAdjustmentAdapter.isCanLoad()){
                    TreeMap<String, String> idImgDataMap = new TreeMap<>();
                    idImgDataMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
                    idImgDataMap.put("proid", proid);
                    idImgDataMap.put("data", productionAdjustmentAdapter.getLoadString());
                    getPresenter().productionAdjustmentImgData(idImgDataMap, true, false);
                }

                break;
            case R.id.tv_top_add:
                GetProAdjustBean.DataBeanFirst insertDate = getInsertDate("产调"+(mDatas.size()+1));
                mDatas.add(insertDate);
                productionAdjustmentAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        String idImgPath = data.getData().get(0);
        List<GetProAdjustBean.DataBeanFirst.DataBeanSecond> data1 = mDatas.get(position).data;
        data1.get(childposition).yi_imgurl = idImgPath;
        data1.add(getInsertSecondDate(mDatas.get(position).yi_type));
        productionAdjustmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void resultProductionAdjustmentImgData(String data) {
        Gson g = new Gson();
        IdImgDataBean obj = g.fromJson(data, IdImgDataBean.class);
        ToastUtil.showShortToast(obj.getMsg());
        if (obj.isData()) {
            setResult(RESULT_OK, new Intent().putExtra("productionAdjustmentResult", "1"));
            finish();
        }
    }

    //处理获取数据
    @Override
    public void resultData(String data) {
//        ToastUtil.showLongToast(data);
        Gson gson = new Gson();
        GetProAdjustBean getProAdjustBean = gson.fromJson(data, GetProAdjustBean.class);
        for (GetProAdjustBean.DataBeanFirst beanFirst:getProAdjustBean.data){
            beanFirst.data.add(getInsertSecondDate(beanFirst.yi_type));
        }
        mDatas.addAll(getProAdjustBean.data);
        if (mDatas.size() == 0){
            mDatas.add(getInsertDate("产调1"));
        }
        productionAdjustmentAdapter.setNewData(mDatas);


    }

    int position,childposition;
    public void SelectPhotoMultiple(int position,int childposition) {
        this.position = position;
        this.childposition = childposition;
        PictureSelector.create(this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(false, 200, 200, 1, 1);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
