package com.example.ipd.yueyue.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.base.BaseActivity;
import com.example.ipd.yueyue.base.BasePresenter;
import com.example.ipd.yueyue.base.BaseView;
import com.example.ipd.yueyue.common.view.TopView;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.example.ipd.yueyue.utils.SPUtil;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：设置
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/8.
 */
public class SettingsActivity extends BaseActivity {

    @BindView(R.id.tv_setting_top)
    TopView tvSettingTop;
    @BindView(R.id.ll_about_us)
    LinearLayout llAboutUs;
    @BindView(R.id.ll_feed_back)
    LinearLayout llFeedBack;
    @BindView(R.id.bt_setting_out)
    Button btSettingOut;

    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    public BasePresenter createPresenter() {
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
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvSettingTop);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ll_about_us, R.id.ll_feed_back, R.id.bt_setting_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_about_us:
                startActivity(new Intent(this, AboutUsActivity.class).putExtra("type", 3));
                break;
            case R.id.ll_feed_back:
                startActivity(new Intent(this, FeedBackActivity.class));
                break;
            case R.id.bt_setting_out:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }
}
