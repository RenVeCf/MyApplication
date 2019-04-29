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

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.IdAdapter;
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
 * Description ：身份证
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/9.
 */
public class IdActivity extends BaseActivity<IdContract.View, IdContract.Presenter> implements IdContract.View {

    public static final String TAG= "ID_TAG";
    @BindView(R.id.tv_id_top)
    TopView tvIdTop;
    @BindView(R.id.tv_top_add)
    TextView tvIdAdd;
    @BindView(R.id.rv_id)
    RecyclerView rvId;
    @BindView(R.id.bt_id)
    Button btId;


    private int isAdd;//0：添加   1：修改
    private int flag = 0;//身份证的两个面
    private String proid;//资料id
    private IdAdapter idAdapter;
    private OptionsPickerView pvOptions;
    private ArrayList<String> listData = getData();//选择器数据源
    private List<GetIdImgBean.DataBean> list = new ArrayList<>();//数据源

    @Override
    public int getLayoutId() {
        return R.layout.activity_id;
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
        ImmersionBar.setTitleBar(this, tvIdTop);

        isAdd = getIntent().getIntExtra("isAdd", 0);
        proid = getIntent().getStringExtra("proidId");

        //设置RecyclerView方向和是否反转
        LinearLayoutManager NotUseList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvId.setLayoutManager(NotUseList);
        rvId.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvId.setItemAnimator(new DefaultItemAnimator()); //默认动画


        list.add(getImageData("本人"));
        idAdapter = new IdAdapter(list);
        rvId.setAdapter(idAdapter);
    }

    //得到一个新的 item的对象 用来创建新的item 标题不包含 身份证，
    private GetIdImgBean.DataBean getImageData(String title){
        //初始化数据
        GetIdImgBean.DataBean dataBean = new GetIdImgBean.DataBean();
        dataBean.setCard_type(StringUtils.gbEncoding(title));
        dataBean.setCard_proid(Integer.parseInt(proid));
        return dataBean;
    }

    GetIdImgBean.DataBean clickDataBean;
    @Override
    public void initListener() {
        idAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                clickDataBean = list.get(position);
                switch (view.getId()) {
                    case R.id.tv_delete:
                        idAdapter.removeData(position);
                        break;
                    case R.id.iv_id_one:
                        if (TextUtils.isEmpty(clickDataBean.getCard_positive())){
//                            clickImageView = (ImageView) view;
                            selectPhoto();
                            flag = 0;
                        }else {
                            BigImageActivity.launch(IdActivity.this,clickDataBean.getCard_positive());
                        }
                        break;
                    case R.id.iv_id_two:
                        if (TextUtils.isEmpty(clickDataBean.getCard_negative())){
//                            clickImageView = (ImageView) view;
                            selectPhoto();
                            flag = 1;
                        }else {
                            BigImageActivity.launch(IdActivity.this,clickDataBean.getCard_negative());
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
            getPresenter().getIdImg(getIdImgMap, true, false);
        }
//        map = new HashMap<>();
//        map.put("card_userid", (String) SPUtil.get(IdActivity.this, IConstants.USER_ID, ""));
//        map.put("card_proid", proid);
//        map.put("card_type", StringUtils.gbEncoding("本人"));
    }

    /**
     * 展示选择器
     * 核心代码
     */
    private void showPickerView() {
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                idAdapter.addData(list.size(),getImageData(listData.get(options1)));
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

    private void selectPhoto() {
        /**
         * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
         * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
         */
        PictureSelector.create(this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(false, 200, 200, 1, 1);
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

    @OnClick({R.id.bt_id, R.id.tv_top_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_id:
                if (idAdapter.isCanAdd()){
                    TreeMap<String, String> idImgDataMap = new TreeMap<>();
                    idImgDataMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
                    idImgDataMap.put("proid", proid);
                    idImgDataMap.put("data", idAdapter.getLoadString());
                    getPresenter().idImgData(idImgDataMap, true, false);
                }
                break;
            case R.id.tv_top_add:
                if (idAdapter.isCanAdd()){
                    showPickerView();
                }
                break;
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        String idImgPath = data.getData().get(0);
        if (flag == 0) {
            clickDataBean.setCard_positive(idImgPath);
        } else {
            clickDataBean.setCard_negative(idImgPath);
        }
        idAdapter.notifyDataSetChanged();
    }
    @Override
    public void resultIdImgData(String data) {
        Gson g = new Gson();
        IdImgDataBean obj = g.fromJson(data, IdImgDataBean.class);
        ToastUtil.showShortToast(obj.getMsg());
        if (obj.isData()) {
            setResult(RESULT_OK, new Intent().putExtra("idCardResult", "1"));
            finish();
        }
    }
    @Override
    public void resultGetIdImg(GetIdImgBean data) {
        //返回的获取的图片
        list.clear();
        list.addAll(data.getData());
        idAdapter.setNewData(list);
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

    }

    @Override
    public void resultGetBankImg(GetBankImgBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
