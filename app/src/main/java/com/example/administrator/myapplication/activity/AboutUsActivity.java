package com.example.administrator.myapplication.activity;

import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.TextBean;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.AboutUsContract;
import com.example.administrator.myapplication.presenter.AboutUsPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.gyf.barlibrary.ImmersionBar;

import java.util.TreeMap;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

/**
 * Description ：关于我们
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/8.
 */
public class AboutUsActivity extends BaseActivity<AboutUsContract.View, AboutUsContract.Presenter> implements AboutUsContract.View {

    @BindView(R.id.tv_about_us_top)
    TopView tvAboutUsTop;
    @BindView(R.id.wv_about_us)
    WebView wvAboutUs;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    public AboutUsContract.Presenter createPresenter() {
        return new AboutUsPresenter(this);
    }

    @Override
    public AboutUsContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvAboutUsTop);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        TreeMap<String, String> aboutUsMap = new TreeMap<>();
        aboutUsMap.put("id", "3");
        getPresenter().aboutUs(aboutUsMap, true, false);
    }

    @Override
    public void resultAboutUs(TextBean data) {
        WebSettings settings = wvAboutUs.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        wvAboutUs.setWebViewClient(new WebViewClient());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        wvAboutUs.loadData(getHtmlData(data.getData().getText()), "text/html;charset=utf-8", "utf-8");
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>html{padding:15px;} body{word-wrap:break-word;font-size:13px;padding:0px;margin:0px} p{padding:0px;margin:0px;font-size:13px;color:#222222;line-height:1.3;} img{padding:0px,margin:0px;max-width:100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
