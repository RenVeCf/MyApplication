package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.GetAddSupplementaryBean;
import com.example.administrator.myapplication.bean.UploadImgBean;
import com.example.administrator.myapplication.common.config.UrlConfig;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.AddSupplementaryContract;
import com.example.administrator.myapplication.presenter.AddSupplementaryPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.ToastUtil;
import com.gyf.barlibrary.ImmersionBar;
import com.wildma.pictureselector.PictureSelector;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import okhttp3.RequestBody;

import static com.example.administrator.myapplication.activity.PersonalDataActivity.getImageRequestBody;

/**
 * Description ：添加补充材料
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/9.
 */
public class AddSupplementaryActivity extends BaseActivity<AddSupplementaryContract.View, AddSupplementaryContract.Presenter> implements AddSupplementaryContract.View {

    @BindView(R.id.tv_add_supplementary_top)
    TopView tvAddSupplementaryTop;
    @BindView(R.id.iv_add_supplementary)
    ImageView ivAddSupplementary;
    @BindView(R.id.bt_supplementary)
    Button btSupplementary;
    @BindView(R.id.et_add_supplementary)
    EditText etAddSupplementary;

    private String idImgPath;

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

    @OnClick({R.id.iv_add_supplementary, R.id.bt_supplementary})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add_supplementary:
                selectPhoto();
                break;
            case R.id.bt_supplementary:
                if (!"".equals(etAddSupplementary.getText().toString().trim()) && !"".equals(idImgPath)) {
                    setResult(RESULT_OK, new Intent()
                            .putExtra("addSupplementaryResultTx", etAddSupplementary.getText().toString().trim())
                            .putExtra("addSupplementaryResultImg", idImgPath));
                    finish();
                } else
                    ToastUtil.showLongToast("请填写完整！");
                break;
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        idImgPath = data.getData().get(0);
        Glide.with(this).load(UrlConfig.BASE_URL + idImgPath).apply(new RequestOptions()).into(ivAddSupplementary);
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
