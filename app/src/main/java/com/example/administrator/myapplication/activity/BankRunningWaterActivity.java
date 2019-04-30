package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.BankAdapter;
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
 * Description ：银行流水
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/12.
 */
public class BankRunningWaterActivity extends BaseActivity<IdContract.View, IdContract.Presenter> implements IdContract.View {

    @BindView(R.id.tv_bank_running_water_top)
    TopView tvBankRunningWaterTop;
    @BindView(R.id.tv_top_add)
    TextView tvAddTop;
    @BindView(R.id.rv_bank_running_water)
    RecyclerView rvBankRunningWater;
    @BindView(R.id.bt_bank_running_water)
    Button btBankRunningWater;

    private BankAdapter bankAdapter;
    private List<GetBankImgBean.DataBean> list = new ArrayList<>();
    private List<String> listData = getData();

    private String proid;//资料id
    private int isAdd;//0：添加   1：修改
    private OptionsPickerView pvOptions;

    private GetBankImgBean.DataBean getData(String type){
        GetBankImgBean.DataBean dataBean = new GetBankImgBean.DataBean();
        dataBean.bank_type = type;
        List<GetBankImgBean.DataBean.DataBeanSecond> list = new ArrayList<>();
        list.add(getDataSecond());
        dataBean.data = list;
        return dataBean;
    }
    private GetBankImgBean.DataBean.DataBeanSecond getDataSecond(){
        GetBankImgBean.DataBean.DataBeanSecond dataBean = new GetBankImgBean.DataBean.DataBeanSecond();
        dataBean.bank_proid = Integer.parseInt(proid);
        return dataBean;

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_bank_running_water;
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
        ImmersionBar.setTitleBar(this, tvBankRunningWaterTop);

        isAdd = getIntent().getIntExtra("isAdd", 0);
        proid = getIntent().getStringExtra("proidId");

        //设置RecyclerView方向和是否反转
        LinearLayoutManager NotUseList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvBankRunningWater.setLayoutManager(NotUseList);
        rvBankRunningWater.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvBankRunningWater.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        bankAdapter = new BankAdapter(BankRunningWaterActivity.this,list);
        rvBankRunningWater.setAdapter(bankAdapter);
    }

    @Override
    public void initListener() {
        bankAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_delete:
                        list.remove(position);
                        adapter.notifyItemRemoved(position);
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
            getPresenter().getBankImg(getIdImgMap, true, false);
        }else {
            list.clear();
            list.add(getData("中国银行"));
            bankAdapter.setNewData(list);
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
                boolean isCanLoad = true;
                String s = listData.get(options1);
                for (GetBankImgBean.DataBean bean:list){
                    if (s.equals(bean.bank_type)){
                        isCanLoad = false;
                        break;
                    }
                }
                if (isCanLoad){
                    list.add(getData(s));
                    bankAdapter.notifyDataSetChanged();
                }

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
                .setSelectOptions(1)//设置选择第一个
                .setOutSideCancelable(true)//点击背的地方不消失
                .build();//创建
        pvOptions.setPicker(listData);
        pvOptions.show();
    }

    private ArrayList<String> getData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("中国银行");
        list.add("交通银行");
        list.add("中国工商银行");
        list.add("中国农业银行");
        list.add("中国建设银行");
        list.add("招商银行");
        list.add("华夏银行");
        list.add("中信银行");
        list.add("兴业银行");
        list.add("浦发银行");
        list.add("民生银行");
        list.add("其他银行");
        return list;
    }

    private void selectPhoto() {
        /**
         * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
         * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
         */
        PictureSelector.create(this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(false, 200, 200, 1, 1);
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

    @OnClick({R.id.tv_top_add, R.id.bt_bank_running_water})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_top_add:
                showPickerView();
                break;
            case R.id.bt_bank_running_water:
                if (bankAdapter.isCanAdd()){
                    TreeMap<String, String> idImgDataMap = new TreeMap<>();
                    idImgDataMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
                    idImgDataMap.put("proid", proid);
                    idImgDataMap.put("data", bankAdapter.getLoadString());
                    getPresenter().modifyBankImgData(idImgDataMap, true, false);
                }
                break;
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        String idImgPath = data.getData().get(0);
        GetBankImgBean.DataBean dataBean = list.get(position);
        GetBankImgBean.DataBean.DataBeanSecond dataBeanSecond = dataBean.data.get(childPosition);
        dataBeanSecond.bank_imgurl = idImgPath;
        dataBean.data.add(getDataSecond());
        bankAdapter.notifyDataSetChanged();
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

    }

    @Override
    public void resultGetAssessmentImg(GetAssessmentImgBean data) {

    }

    @Override
    public void resultModifyBankImgData(String data) {
        Gson g = new Gson();
        IdImgDataBean obj = g.fromJson(data, IdImgDataBean.class);
        ToastUtil.showShortToast(obj.getMsg());
        if (obj.isData()) {
            setResult(RESULT_OK, new Intent().putExtra("bankResult", "1"));
            finish();
        }
    }

    @Override
    public void resultGetBankImg(GetBankImgBean data) {
        list.clear();
        for (GetBankImgBean.DataBean dataBean:data.data){
            dataBean.data.add(getDataSecond());
        }
        list.addAll(data.data);
        bankAdapter.setNewData(list);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
