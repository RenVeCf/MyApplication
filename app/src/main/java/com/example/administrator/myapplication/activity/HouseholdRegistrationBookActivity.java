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
import com.example.administrator.myapplication.adapter.MultipleAdapter;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.GetCompanyInform;
import com.example.administrator.myapplication.bean.GetCreditReportBean;
import com.example.administrator.myapplication.bean.GetEnterpriseBean;
import com.example.administrator.myapplication.bean.GetHouseholdRegistrationBookBean;
import com.example.administrator.myapplication.bean.IdImgDataBean;
import com.example.administrator.myapplication.bean.UploadImgBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.HouseholdRegistrationBookContract;
import com.example.administrator.myapplication.presenter.HouseholdRegistrationBookPresenter;
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
 * Description ：户口本
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/9.
 */
public class HouseholdRegistrationBookActivity extends BaseActivity<HouseholdRegistrationBookContract.View, HouseholdRegistrationBookContract.Presenter> implements HouseholdRegistrationBookContract.View {

    @BindView(R.id.tv_household_registration_book_top)
    TopView tvHouseholdRegistrationBookTop;
    @BindView(R.id.tv_top_add)
    TextView tvAddTop;
    @BindView(R.id.rv_household_registration_book)
    RecyclerView rvHouseholdRegistrationBook;
    @BindView(R.id.bt_household_registration_book)
    Button btHouseholdRegistrationBook;

    private MultipleAdapter mGrowthValueAdapter;
    private List<GetHouseholdRegistrationBookBean.DataBeanX> mDatas;

//    private int position;
//    private List<GetHouseholdRegistrationBookBean.DataBeanX.DataBean> childAdapterList;
//    private List<Map<String, String>> listMap = new ArrayList<>();
//    private Map<String, String> map;//一个item
//    private String idImgPath = "";//本地图片地址
    private String proid;//资料id
    private int isAdd;//0：添加   1：修改

    @Override
    public int getLayoutId() {
        return R.layout.activity_household_registration_book;
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
        ImmersionBar.setTitleBar(this, tvHouseholdRegistrationBookTop);

        isAdd = getIntent().getIntExtra("isAdd", 0);
        proid = getIntent().getStringExtra("proidId");

        //设置RecyclerView方向和是否反转
        LinearLayoutManager NotUseList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        NotUseList.setAutoMeasureEnabled(false);
        rvHouseholdRegistrationBook.setLayoutManager(NotUseList);
        rvHouseholdRegistrationBook.setHasFixedSize(false); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvHouseholdRegistrationBook.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        mDatas = new ArrayList<>();
        mGrowthValueAdapter = new MultipleAdapter(this, mDatas);
        rvHouseholdRegistrationBook.setAdapter(mGrowthValueAdapter);
    }

    private GetHouseholdRegistrationBookBean.DataBeanX getInsertDate(String title){
        GetHouseholdRegistrationBookBean.DataBeanX bean = new GetHouseholdRegistrationBookBean.DataBeanX();
        List<GetHouseholdRegistrationBookBean.DataBeanX.DataBean> data = new ArrayList<>();
        bean.setAcc_type(title);
        data.add(getInsertSecondDate(bean.getAcc_type()));
        bean.setData(data);
        return bean;
    }
    private GetHouseholdRegistrationBookBean.DataBeanX.DataBean getInsertSecondDate(String type){
        GetHouseholdRegistrationBookBean.DataBeanX.DataBean bean = new GetHouseholdRegistrationBookBean.DataBeanX.DataBean();
        bean.setAcc_proid(Integer.parseInt(proid));
        bean.setAcc_type(type);
        return bean;
    }

    @Override
    public void initListener() {
        mGrowthValueAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_delete:
                        mDatas.remove(position);
                        mGrowthValueAdapter.notifyDataSetChanged();
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
            getPresenter().modifyGetHouseholdRegistrationBookImgData(getIdImgMap, true, false);
        }else {
            mDatas.add(getInsertDate("本人"));
            mGrowthValueAdapter.setNewData(mDatas);
        }
    }

    int position,childposition;
    public void SelectPhotoMultiple(int position,int childposition) {
        this.position = position;
        this.childposition = childposition;

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
     * 展示选择器
     * 核心代码
     */
    private OptionsPickerView pvOptions;
    private ArrayList<String> listData = getData();//选择器数据源
    private void showPickerView() {
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                GetHouseholdRegistrationBookBean.DataBeanX insertDate = getInsertDate(listData.get(options1));
                mDatas.add(insertDate);
                mGrowthValueAdapter.notifyDataSetChanged();
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
    /**
     * 造一组数据
     */
    private ArrayList<String> getData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("配偶");
        list.add("父母");
        list.add("子女");
        return list;
    }

    @OnClick({R.id.tv_top_add, R.id.bt_household_registration_book})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_top_add:
                showPickerView();
                break;
            case R.id.bt_household_registration_book:
                if (mGrowthValueAdapter.isCanLoad()){
                    TreeMap<String, String> idImgDataMap = new TreeMap<>();
                    idImgDataMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
                    idImgDataMap.put("proid", proid);
                    idImgDataMap.put("data", mGrowthValueAdapter.getLoadString());
                    getPresenter().modifyHouseholdRegistrationBookImgData(idImgDataMap, true, false);
                }
//                Gson g = new Gson();
//                String jsonString = g.toJson(listMap);
//                TreeMap<String, String> idImgDataMap = new TreeMap<>();
//                idImgDataMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
//                idImgDataMap.put("proid", proid);
//                idImgDataMap.put("data", jsonString);
//                getPresenter().modifyHouseholdRegistrationBookImgData(idImgDataMap, true, false);
                break;
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        String idImgPath = data.getData().get(0);
        List<GetHouseholdRegistrationBookBean.DataBeanX.DataBean> data1 = mDatas.get(position).getData();
        data1.get(childposition).setAcc_imgurl(idImgPath);
        data1.add(getInsertSecondDate(mDatas.get(position).getAcc_type()));
        mGrowthValueAdapter.notifyDataSetChanged();

//        map = new HashMap<>();
//        map.put("acc_userid", (String) SPUtil.get(HouseholdRegistrationBookActivity.this, IConstants.USER_ID, ""));
//        map.put("acc_proid", proid);
//        map.put("acc_type", StringUtils.gbEncoding("户口本"));
//        map.put("acc_imgurl", idImgPath);
//        listMap.add(map);
        //返回上传图片的地址
//        GetHouseholdRegistrationBookBean.DataBeanX.DataBean dataBean = new GetHouseholdRegistrationBookBean.DataBeanX.DataBean();
//        dataBean.setAcc_imgurl(data.getData().get(0));
//        childAdapterList.set(position, dataBean);
//        childAdapterList.add(new GetHouseholdRegistrationBookBean.DataBeanX.DataBean());
//        mGrowthValueAdapter.notifyDataSetChanged();
    }

    @Override
    public void resultModifyHouseholdRegistrationBookImgData(String data) {
        Gson g = new Gson();
        IdImgDataBean obj = g.fromJson(data, IdImgDataBean.class);
        ToastUtil.showShortToast(obj.getMsg());
        if (obj.isData()) {
            setResult(RESULT_OK, new Intent().putExtra("householdRegistrationBookCardResult", "1"));
            finish();
        }
    }

    @Override
    public void resultGetHouseholdRegistrationBookImg(GetHouseholdRegistrationBookBean data) {
//        ToastUtil.showLongToast(data.getMsg());
//        Gson g = new Gson();
//        GetHouseholdRegistrationBookBean obj = g.fromJson(data, GetHouseholdRegistrationBookBean.class);
//
////        mDatas.clear();
        //添加一个数据 默认是请选择
        for (GetHouseholdRegistrationBookBean.DataBeanX dataBeanX:data.getData()){
            dataBeanX.getData().add(getInsertSecondDate(dataBeanX.getAcc_type()));
        }
        mDatas.addAll(data.getData());
        if (mDatas.size() == 0){
            mDatas.add(getInsertDate("本人"));
        }
        mGrowthValueAdapter.setNewData(mDatas);
    }

    @Override
    public void resultModifyEnterpriseImgData(String data) {

    }

    @Override
    public void resultGetEnterpriseImg(GetCompanyInform data) {

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
