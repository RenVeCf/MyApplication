package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.ModifyPersonalDataBean;
import com.example.administrator.myapplication.bean.UploadImgBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.config.UrlConfig;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.UploadImgContract;
import com.example.administrator.myapplication.presenter.UploadImgPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.SPUtil;
import com.gyf.barlibrary.ImmersionBar;
import com.wildma.pictureselector.PictureSelector;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Description ：个人资料
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/8.
 */
public class PersonalDataActivity extends BaseActivity<UploadImgContract.View, UploadImgContract.Presenter> implements UploadImgContract.View {

    @BindView(R.id.tv_personal_data_top)
    TopView tvPersonalDataTop;
    @BindView(R.id.iv_top_back)
    ImageView ivTopBack;
    @BindView(R.id.iv_personal_data_head)
    ImageView ivPersonalDataHead;
    @BindView(R.id.ll_update_personal_data_head)
    LinearLayout llUpdatePersonalDataHead;
    @BindView(R.id.tv_personal_data_name)
    TextView tvPersonalDataName;
    @BindView(R.id.ll_update_personal_data_name)
    LinearLayout llUpdatePersonalDataName;
    @BindView(R.id.tv_personal_data_phone)
    TextView tvPersonalDataPhone;
    @BindView(R.id.ll_update_personal_data_phone)
    LinearLayout llUpdatePersonalDataPhone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_data;
    }

    @Override
    public UploadImgContract.Presenter createPresenter() {
        return new UploadImgPresenter(this);
    }

    @Override
    public UploadImgContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvPersonalDataTop);

        Glide.with(this).load(SPUtil.get(this, IConstants.AVATAR, "")).apply(new RequestOptions()).into(ivPersonalDataHead);
        tvPersonalDataName.setText((String) SPUtil.get(this, IConstants.NAME, ""));
        tvPersonalDataPhone.setText((String) SPUtil.get(this, IConstants.PHONE, ""));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    private void selectPhoto() {
        /**
         * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
         * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
         */
        PictureSelector.create(PersonalDataActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(false, 200, 200, 1, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IConstants.REQUEST_CODE_95:
                if (!data.getStringExtra("nameResult").equals("")) {
                    tvPersonalDataName.setText(data.getStringExtra("nameResult"));
                    SPUtil.put(this, IConstants.NAME, data.getStringExtra("nameResult"));
                }
                break;
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

    public static RequestBody getImageRequestBody(String filePath) {
        return RequestBody.create(MediaType.parse("image/png"), new File(filePath));
    }

    @OnClick({R.id.ll_update_personal_data_head, R.id.ll_update_personal_data_name, R.id.iv_top_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_update_personal_data_head:
                selectPhoto();
                break;
            case R.id.ll_update_personal_data_name:
                startActivityForResult(new Intent(this, NameActivity.class), IConstants.REQUEST_CODE_95);
                break;
            case R.id.iv_top_back:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        TreeMap<String, String> modifyPersonalDataMap = new TreeMap<>();
        modifyPersonalDataMap.put("id", (String) SPUtil.get(this, IConstants.USER_ID, ""));
        modifyPersonalDataMap.put("avatar", data.getData().get(0));
        modifyPersonalDataMap.put("name", (String) SPUtil.get(this, IConstants.NAME, ""));
        getPresenter().modifyPersonalData(modifyPersonalDataMap, true, false);
    }

    @Override
    public void resultModifyPersonalData(ModifyPersonalDataBean data) {
        SPUtil.put(this, IConstants.AVATAR, UrlConfig.BASE_URL + data.getData().getAvatar());
        Glide.with(this).load(UrlConfig.BASE_URL + data.getData().getAvatar()).apply(new RequestOptions()).into(ivPersonalDataHead);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
