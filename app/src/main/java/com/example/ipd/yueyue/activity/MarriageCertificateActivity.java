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

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.adapter.MarriageCertificateAdapter;
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
import com.example.ipd.yueyue.utils.StringUtils;
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
 * Description ：结婚证
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/9.
 */
public class MarriageCertificateActivity extends BaseActivity<IdContract.View, IdContract.Presenter> implements IdContract.View {

    @BindView(R.id.tv_marriage_certificate_top)
    TopView tvMarriageCertificateTop;
    @BindView(R.id.tv_top_add)
    TextView tvIdAdd;
    @BindView(R.id.rv_marriage_certificate)
    RecyclerView rvMarriageCertificate;
    @BindView(R.id.bt_marriage_certificate)
    Button btMarriageCertificate;

    private int flag = 0;//证的三个面
    private String proid;//资料id
    private int isAdd;//0：添加   1：修改

    private MarriageCertificateAdapter marriageCertificateAdapter;
    private ArrayList<String> listData = getData();
    private List<GetMarryImgBean.DataBean> list = new ArrayList<>();

    private OptionsPickerView pvOptions;




    //得到一个新的 item的对象 用来创建新的item 标题不包含 身份证，
    private GetMarryImgBean.DataBean getImageData(String title){
//        初始化数据
        GetMarryImgBean.DataBean dataBean = new GetMarryImgBean.DataBean();
        dataBean.setMar_proid(Integer.parseInt(proid));
        dataBean.setMar_type(StringUtils.gbEncoding(title));
        return dataBean;
    }





    @Override
    public int getLayoutId() {
        return R.layout.activity_marriage_certificate;
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
        ImmersionBar.setTitleBar(this, tvMarriageCertificateTop);

        isAdd = getIntent().getIntExtra("isAdd", 0);
        proid = getIntent().getStringExtra("proidId");

        //设置RecyclerView方向和是否反转
        LinearLayoutManager NotUseList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMarriageCertificate.setLayoutManager(NotUseList);
        rvMarriageCertificate.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvMarriageCertificate.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        list.add(getImageData("本人"));
        marriageCertificateAdapter = new MarriageCertificateAdapter(list);
        rvMarriageCertificate.setAdapter(marriageCertificateAdapter);
    }

    GetMarryImgBean.DataBean clickDataBean;
    @Override
    public void initListener() {
        marriageCertificateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                clickDataBean = list.get(position);
                switch (view.getId()) {
                    case R.id.tv_delete:
                        marriageCertificateAdapter.removeData(position);
                        break;
                    case R.id.iv_marriage_certificate_one:
                        if (TextUtils.isEmpty(clickDataBean.getMar_page1())){
                            selectPhoto();
                            flag = 0;
                        }else{
                            BigImageActivity.launch(MarriageCertificateActivity.this,clickDataBean.getMar_page1());
                        }

                        break;
                    case R.id.iv_marriage_certificate_two:
                        if (TextUtils.isEmpty(clickDataBean.getMar_page2())){
                            selectPhoto();
                            flag = 1;
                        }else{
                            BigImageActivity.launch(MarriageCertificateActivity.this,clickDataBean.getMar_page2());
                        }
                        break;
                    case R.id.iv_marriage_certificate_three:
                        if (TextUtils.isEmpty(clickDataBean.getMar_page3())){
                            selectPhoto();
                            flag = 2;
                        }else{
                            BigImageActivity.launch(MarriageCertificateActivity.this,clickDataBean.getMar_page3());
                        }
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
            getPresenter().getMarryImg(getIdImgMap, true, false);
        }
    }

    /**
     * 展示选择器
     * 核心代码
     */
    private void showPickerView() {
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                marriageCertificateAdapter.addData(list.size(), getImageData(listData.get(options1)));

            }
        })
                .setDividerColor(getResources().getColor(R.color.white))//设置分割线的颜色
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        Button tvSubmit = (Button) v.findViewById(R.id.bt_pickview_confirm);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.returnData();
                                pvOptions.dismiss();
                            }
                        });
                    }
                })
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(true)//点击背的地方不消失
                .build();//创建
        pvOptions.setPicker(listData);
        pvOptions.show();
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

    /**
     * 造一组数据
     */
    private ArrayList<String> getData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("父母");
        list.add("子女");
        list.add("其他");
        return list;
    }

    @OnClick({R.id.tv_top_add, R.id.bt_marriage_certificate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_top_add:
                if (marriageCertificateAdapter.isCanAdd()){
                    showPickerView();
                }
                break;
            case R.id.bt_marriage_certificate:
                if (marriageCertificateAdapter.isCanAdd()){
                    TreeMap<String, String> idImgDataMap = new TreeMap<>();
                    idImgDataMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
                    idImgDataMap.put("proid", proid);
                    idImgDataMap.put("data", marriageCertificateAdapter.getLoadString());
                    getPresenter().modifyMarryImgData(idImgDataMap, true, false);

                }
                break;
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        String idImgPath = data.getData().get(0);
        if (flag == 0) {
            clickDataBean.setMar_page1(idImgPath);
        } else if (flag == 1) {
            clickDataBean.setMar_page2(idImgPath);
        } else {
            clickDataBean.setMar_page3(idImgPath);
        }
        marriageCertificateAdapter.notifyDataSetChanged();
    }

    @Override
    public void resultIdImgData(String data) {
    }

    @Override
    public void resultGetIdImg(GetIdImgBean data) {

    }

    @Override
    public void resultModifyMarryImgData(String data) {
        Gson g = new Gson();
        IdImgDataBean obj = g.fromJson(data, IdImgDataBean.class);
        ToastUtil.showShortToast(obj.getMsg());
        if (obj.isData()) {
            setResult(RESULT_OK, new Intent().putExtra("marriageCertificateCardResult", "1"));
            finish();
        }
    }

    @Override
    public void resultGetMarryImg(GetMarryImgBean data) {
        list.clear();
        list.addAll(data.getData());
        marriageCertificateAdapter.setNewData(list);
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
