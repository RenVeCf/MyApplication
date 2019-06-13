package com.example.ipd.yueyue.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.adapter.AssessmentReportAdapter;
import com.example.ipd.yueyue.base.BaseActivity;
import com.example.ipd.yueyue.bean.GetAddSupplementaryBean;
import com.example.ipd.yueyue.bean.GetAssessmentImgBean;
import com.example.ipd.yueyue.bean.UploadImgBean;
import com.example.ipd.yueyue.common.view.TopView;
import com.example.ipd.yueyue.contract.AddSupplementaryContract;
import com.example.ipd.yueyue.presenter.AddSupplementaryPresenter;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.example.ipd.yueyue.utils.ToastUtil;
import com.gyf.barlibrary.ImmersionBar;
import com.wildma.pictureselector.PictureSelector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import okhttp3.RequestBody;

import static com.example.ipd.yueyue.activity.PersonalDataActivity.getImageRequestBody;

/**
 * Description ：添加补充材料
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/9.
 */
public class AddSupplementaryActivity extends BaseActivity<AddSupplementaryContract.View, AddSupplementaryContract.Presenter> implements AddSupplementaryContract.View {

    @BindView(R.id.tv_add_supplementary_top)
    TopView tvAddSupplementaryTop;
    //    @BindView(R.id.iv_add_supplementary)
//    ImageView ivAddSupplementary;
    @BindView(R.id.et_add_supplementary)
    EditText etAddSupplementary;
    @BindView(R.id.rv_add_supplementary)
    RecyclerView rvAddSupplementary;
    @BindView(R.id.bt_supplementary)
    Button btSupplementary;
//    @BindView(R.id.iv_add_supplementary_del)
//    ImageView ivAddSupplementaryDel;

    private String idImgPath;
    private AssessmentReportAdapter assessmentReportAdapter;
    private List<GetAssessmentImgBean.DataBean> list = new ArrayList<>();
    private ArrayList<String> restult = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_supplementary;
    }

    @Override
    public AddSupplementaryContract.Presenter createPresenter() {
        return new AddSupplementaryPresenter(this);
    }

    @Override
    public AddSupplementaryContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvAddSupplementaryTop);

        //设置RecyclerView方向和是否反转
        GridLayoutManager NotUseList = new GridLayoutManager(this, 5);
        rvAddSupplementary.setLayoutManager(NotUseList);
        rvAddSupplementary.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvAddSupplementary.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        list.add(getImageData());
        assessmentReportAdapter = new AssessmentReportAdapter(list);
        rvAddSupplementary.setAdapter(assessmentReportAdapter);
    }

    private GetAssessmentImgBean.DataBean getImageData() {
        //初始化数据
        GetAssessmentImgBean.DataBean dataBean = new GetAssessmentImgBean.DataBean();
//        dataBean.setAss_proid(Integer.parseInt(proid));
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
                        if (TextUtils.isEmpty(clickDataBean.getAss_imgurl())) {
                            selectPhoto();
                        } else {
                            BigImageActivity.launch(AddSupplementaryActivity.this, clickDataBean.getAss_imgurl());
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

    @OnClick({R.id.bt_supplementary})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.iv_add_supplementary:
//                if (ivAddSupplementaryDel.getVisibility() != View.VISIBLE)
//                    selectPhoto();
//                else {
//                    BigImageActivity.launch(this, idImgPath);
//                }
//                break;
            case R.id.bt_supplementary:
                if (!"".equals(etAddSupplementary.getText().toString().trim()) && !"".equals(idImgPath)) {
                    for (int i = 0; i < list.size(); i++) {
                        restult.add(list.get(i).getAss_imgurl());
                    }
                    setResult(RESULT_OK, new Intent()
                            .putExtra("addSupplementaryResultTx", etAddSupplementary.getText().toString().trim())
                            .putStringArrayListExtra("addSupplementaryResultImg", restult));
                    finish();
                } else
                    ToastUtil.showLongToast("请填写完整！");
                break;
//            case R.id.iv_add_supplementary_del:
//                ivAddSupplementary.setImageResource(0);
//                ivAddSupplementaryDel.setVisibility(View.GONE);
//                break;
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        idImgPath = data.getData().get(0);
        clickDataBean.setAss_imgurl(idImgPath);
        list.add(getImageData());
        assessmentReportAdapter.notifyDataSetChanged();
//        Glide.with(this).load(UrlConfig.BASE_URL + idImgPath).apply(new RequestOptions()).into(ivAddSupplementary);
//        ivAddSupplementaryDel.setVisibility(View.VISIBLE);
    }

    @Override
    public void resultModifyAddSupplementarImgData(String data) {

    }

    @Override
    public void resultGetAddSupplementarImg(GetAddSupplementaryBean data) {


    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
