package com.example.administrator.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.base.BaseView;
import com.example.administrator.myapplication.common.config.UrlConfig;
import com.example.administrator.myapplication.contract.AboutUsContract;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.weight.scaleimage.PhotoView;
import com.example.administrator.myapplication.weight.scaleimage.PhotoViewAttacher;

import butterknife.BindView;

/**
 * Description ：关于我们
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/8.
 */
public class BigImageActivity extends BaseActivity {
    public static final String PATH = "image_path";

    public static void launch(Activity activity,String path){
        Intent intent = new Intent(activity, BigImageActivity.class);
        intent.putExtra(PATH,path);
        activity.startActivity(intent);

    }


    @BindView(R.id.pv_image)
    PhotoView pvImage;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bigimage;
    }

    @Override
    public AboutUsContract.Presenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }


    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        Log.e("TAG",UrlConfig.BASE_URL +getIntent().getStringExtra(PATH));
        Glide.with(this).load(UrlConfig.BASE_URL +getIntent().getStringExtra(PATH)).into(pvImage);
    }

    @Override
    public void initListener() {
        pvImage.setClickListener(new PhotoViewAttacher.onClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });


    }

    @Override
    public void initData() {
    }


}
