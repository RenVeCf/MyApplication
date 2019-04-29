package com.example.administrator.myapplication.activity;

import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bean.FeedBackBean;
import com.example.administrator.myapplication.common.config.IConstants;
import com.example.administrator.myapplication.common.view.TopView;
import com.example.administrator.myapplication.contract.FeedBackContract;
import com.example.administrator.myapplication.presenter.FeedBackPresenter;
import com.example.administrator.myapplication.utils.ApplicationUtil;
import com.example.administrator.myapplication.utils.SPUtil;
import com.example.administrator.myapplication.utils.ToastUtil;
import com.gyf.barlibrary.ImmersionBar;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

/**
 * Description ：意见反馈
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/8.
 */
public class FeedBackActivity extends BaseActivity<FeedBackContract.View, FeedBackContract.Presenter> implements FeedBackContract.View {

    @BindView(R.id.tv_feed_back_top)
    TopView tvFeedBackTop;
    @BindView(R.id.et_feed_back)
    EditText etFeedBack;
    @BindView(R.id.bt_feed_back_confirm)
    Button btFeedBackConfirm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_feed_back;
    }

    @Override
    public FeedBackContract.Presenter createPresenter() {
        return new FeedBackPresenter(this);
    }

    @Override
    public FeedBackContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvFeedBackTop);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.bt_feed_back_confirm)
    public void onViewClicked() {
        if (!etFeedBack.getText().toString().trim().equals("")) {
            TreeMap<String, String> feedBackMap = new TreeMap<>();
            feedBackMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
            feedBackMap.put("text", etFeedBack.getText().toString().trim());
            getPresenter().feedBack(feedBackMap, true, false);
        } else
            finish();
    }

    @Override
    public void resultFeedBack(FeedBackBean data) {
        if (data.isData())
            ToastUtil.showShortToast("意见反馈提交成功！");
        finish();
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
