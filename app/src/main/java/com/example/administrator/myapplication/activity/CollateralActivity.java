package com.example.administrator.myapplication.activity;

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
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.CollateralAdapter;
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
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.IdContract;
import com.example.administrator.myapplication.presenter.IdPresenter;
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
 * Description ：抵押物
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/12.
 */
public class CollateralActivity extends BaseActivity<IdContract.View, IdContract.Presenter> implements IdContract.View {

    @BindView(R.id.tv_collateral_top)
    TopView tvCollateralTop;
    @BindView(R.id.tv_top_add)
    TextView tvIdAdd;
    @BindView(R.id.rv_collateral)
    RecyclerView rvCollateral;
    @BindView(R.id.bt_collateral)
    Button btCollateral;

    private CollateralAdapter collateralAdapter;
    private List<GetCollateralImgBean.DataBean> list = new ArrayList<>();
    private int flag = 0;//证的两个面
    private String proid;//资料id
    private int isAdd;//0：添加   1：修改

    private GetCollateralImgBean.DataBean getImageData(String title){
        //初始化数据
        GetCollateralImgBean.DataBean dataBean = new GetCollateralImgBean.DataBean();
        dataBean.setMor_type(title);
        dataBean.setMor_proid(Integer.parseInt(proid));
        return dataBean;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collateral;
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
        ImmersionBar.setTitleBar(this, tvCollateralTop);

        isAdd = getIntent().getIntExtra("isAdd", 0);
        proid = getIntent().getStringExtra("proidId");

        //设置RecyclerView方向和是否反转
        LinearLayoutManager NotUseList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCollateral.setLayoutManager(NotUseList);
        rvCollateral.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvCollateral.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        list.add(getImageData("抵押物1"));
        collateralAdapter = new CollateralAdapter(list);
        rvCollateral.setAdapter(collateralAdapter);
    }

    GetCollateralImgBean.DataBean clickDataBean;
    @Override
    public void initListener() {
        collateralAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                clickDataBean = list.get(position);
                switch (view.getId()) {
                    case R.id.tv_delete:
                        collateralAdapter.removeData(position);
                        break;
                    case R.id.iv_collateral_one:
                        if (!TextUtils.isEmpty(clickDataBean.getMor_page1())){
                            BigImageActivity.launch(CollateralActivity.this,clickDataBean.getMor_page1());
                            return;
                        }
                        selectPhoto();
                        flag = 0;
                        break;
                    case R.id.iv_collateral_two:
                        if (!TextUtils.isEmpty(clickDataBean.getMor_page2())){
                            BigImageActivity.launch(CollateralActivity.this,clickDataBean.getMor_page2());
                            return;
                        }
                        selectPhoto();
                        flag = 1;
                        break;
                    case R.id.iv_collateral_three:
                        if (!TextUtils.isEmpty(clickDataBean.getMor_page3())){
                            BigImageActivity.launch(CollateralActivity.this,clickDataBean.getMor_page3());
                            return;
                        }
                        selectPhoto();
                        flag = 2;
                        break;
                    case R.id.iv_collateral_four:
                        if (!TextUtils.isEmpty(clickDataBean.getMor_page4())){
                            BigImageActivity.launch(CollateralActivity.this,clickDataBean.getMor_page4());
                            return;
                        }

                        selectPhoto();
                        flag = 3;
                        break;
                    case R.id.iv_collateral_five:
                        if (!TextUtils.isEmpty(clickDataBean.getMor_page5())){
                            BigImageActivity.launch(CollateralActivity.this,clickDataBean.getMor_page5());
                            return;
                        }
                        selectPhoto();
                        flag = 4;
                        break;
                    case R.id.iv_collateral_six:
                        if (!TextUtils.isEmpty(clickDataBean.getMor_page6())){
                            BigImageActivity.launch(CollateralActivity.this,clickDataBean.getMor_page6());
                            return;
                        }
                        selectPhoto();
                        flag = 5;
                        break;
                    case R.id.iv_collateral_seven:
                        if (!TextUtils.isEmpty(clickDataBean.getMor_page7())){
                            BigImageActivity.launch(CollateralActivity.this,clickDataBean.getMor_page7());
                            return;
                        }
                        selectPhoto();
                        flag = 6;
                        break;
                    case R.id.iv_collateral_eight:
                        if (!TextUtils.isEmpty(clickDataBean.getMor_page8())){
                            BigImageActivity.launch(CollateralActivity.this,clickDataBean.getMor_page8());
                            return;
                        }
                        selectPhoto();
                        flag = 7;
                        break;
                    case R.id.iv_collateral_one_del:
                        clickDataBean.setMor_page1("");
                        collateralAdapter.notifyDataSetChanged();
                        break;
                    case R.id.iv_collateral_two_del:
                        clickDataBean.setMor_page2("");
                        collateralAdapter.notifyDataSetChanged();
                        break;
                    case R.id.iv_collateral_three_del:
                        clickDataBean.setMor_page3("");
                        collateralAdapter.notifyDataSetChanged();

                        break;
                    case R.id.iv_collateral_four_del:
                        clickDataBean.setMor_page4("");
                        collateralAdapter.notifyDataSetChanged();

                        break;
                    case R.id.iv_collateral_five_del:
                        clickDataBean.setMor_page5("");
                        collateralAdapter.notifyDataSetChanged();;
                        break;
                    case R.id.iv_collateral_six_del:
                        clickDataBean.setMor_page6("");
                        collateralAdapter.notifyDataSetChanged();
                        break;
                    case R.id.iv_collateral_seven_del:
                        clickDataBean.setMor_page7("");
                        collateralAdapter.notifyDataSetChanged();
                        break;
                    case R.id.iv_collateral_eight_del:
                        clickDataBean.setMor_page8("");
                        collateralAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
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

    @Override
    public void initData() {
        if (isAdd == 1) {
            TreeMap<String, String> getIdImgMap = new TreeMap<>();
            getIdImgMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
            getIdImgMap.put("proid", proid);
            getPresenter().getCollateralImg(getIdImgMap, true, false);
        }
    }

    @OnClick({R.id.tv_top_add, R.id.bt_collateral})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_top_add:
                list.add(getImageData("抵押物"+(list.size()+1)));
                collateralAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_collateral:
                if (collateralAdapter.isCanAdd()){
                TreeMap<String, String> idImgDataMap = new TreeMap<>();
                idImgDataMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
                idImgDataMap.put("proid", proid);
                idImgDataMap.put("data", collateralAdapter.getLoadString());
                getPresenter().modifyCollateralImgData(idImgDataMap, true, false);
                }
                break;
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        String idImgPath = data.getData().get(0);
        if (flag == 0) {
            clickDataBean.setMor_page1(idImgPath);
        } else if (flag == 1) {
            clickDataBean.setMor_page2(idImgPath);
        } else if (flag == 2) {
            clickDataBean.setMor_page3(idImgPath);
        } else if (flag == 3) {
            clickDataBean.setMor_page4(idImgPath);
        } else if (flag == 4) {
            clickDataBean.setMor_page5(idImgPath);
        } else if (flag == 5) {
            clickDataBean.setMor_page6(idImgPath);
        } else if (flag == 6) {
            clickDataBean.setMor_page7(idImgPath);
        } else if (flag == 7) {
            clickDataBean.setMor_page8(idImgPath);
        }
        collateralAdapter.notifyDataSetChanged();
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
        Gson g = new Gson();
        IdImgDataBean obj = g.fromJson(data, IdImgDataBean.class);
        ToastUtil.showShortToast(obj.getMsg());
        if (obj.isData()) {
            setResult(RESULT_OK, new Intent().putExtra("collateralResult", "1"));
            finish();
        }
    }

    @Override
    public void resultGetCollateralImg(GetCollateralImgBean data) {
        list.clear();
        list.addAll(data.getData());
        if (list.size() == 0){
            list.add(getImageData("抵押物1"));
        }
        collateralAdapter.setNewData(list);
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
