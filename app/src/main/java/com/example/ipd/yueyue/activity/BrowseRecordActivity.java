package com.example.ipd.yueyue.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.adapter.BrowseRecordAdapter;
import com.example.ipd.yueyue.base.BaseActivity;
import com.example.ipd.yueyue.bean.BrowseRecordBean;
import com.example.ipd.yueyue.common.view.CustomLoadMoreView;
import com.example.ipd.yueyue.common.view.TopView;
import com.example.ipd.yueyue.contract.BrowseRecordContract;
import com.example.ipd.yueyue.presenter.BrowseRecordPresenter;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

/**
 * Description ：浏览记录
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/4.
 */
public class BrowseRecordActivity extends BaseActivity<BrowseRecordContract.View, BrowseRecordContract.Presenter> implements BrowseRecordContract.View, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.tv_browse_record_top)
    TopView tvBrowseRecordTop;
    @BindView(R.id.rv_browse_record)
    RecyclerView rvBrowseRecord;
    @BindView(R.id.srl_browse_record)
    SwipeRefreshLayout srlBrowseRecord;

    private BrowseRecordAdapter browseRecordAdapter;
    private List<BrowseRecordBean.DataBean> browseRecordBean;
    private int pageNum = 1; //请求页数
    private boolean isErr = true;
    private String proId = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_browse_record;
    }

    @Override
    public BrowseRecordContract.Presenter createPresenter() {
        return new BrowseRecordPresenter(this);
    }

    @Override
    public BrowseRecordContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvBrowseRecordTop);

        proId = getIntent().getStringExtra("pro_id");

        //设置RecyclerView方向和是否反转
        LinearLayoutManager NotUseList = new LinearLayoutManager(ApplicationUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        rvBrowseRecord.setLayoutManager(NotUseList);
        rvBrowseRecord.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvBrowseRecord.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        browseRecordBean = new ArrayList<>();
        browseRecordAdapter = new BrowseRecordAdapter(browseRecordBean);
        rvBrowseRecord.setAdapter(browseRecordAdapter);
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlBrowseRecord.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                browseRecordAdapter.setNewData(browseRecordBean);
                srlBrowseRecord.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {
        TreeMap<String, String> browseRecordMap = new TreeMap<>();
        //用户id
        browseRecordMap.put("pro_id", proId);
        //页数
        browseRecordMap.put("page", pageNum + "");
        getPresenter().browseRecord(browseRecordMap, true, false);
    }

    @Override
    public void onLoadMoreRequested() {
        initData();
    }

    @Override
    public void resultBrowseRecord(final BrowseRecordBean data) {
        browseRecordBean.clear();
        browseRecordBean.addAll(data.getData());
        browseRecordAdapter.setNewData(browseRecordBean);

        //上拉加载（设置这个监听就表示有上拉加载功能了）
        browseRecordAdapter.setLoadMoreView(new CustomLoadMoreView()
        /*new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rvBrowseRecord.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (data.getMsg().equals("false")) {
                            //数据全部加载完毕
                            browseRecordAdapter.loadMoreEnd();
                        } else {
                            if (isErr) {
                                //成功获取更多数据（可以直接往适配器添加数据）
                                browseRecordAdapter.addData(browseRecordBean);
                                //主动调用加载完成，停止加载
                                browseRecordAdapter.loadMoreComplete();
                            } else {
                                //获取更多数据失败
                                isErr = true;
                                ToastUtil.showLongToast("加载失败！");
                                //同理，加载失败也要主动调用加载失败来停止加载（而且该方法会提示加载失败）
                                browseRecordAdapter.loadMoreFail();

                            }
                        }
                    }

                }, 5000);
            }
        }, rvBrowseRecord*/);
        //空数据时的页面
        browseRecordAdapter.setEmptyView(R.layout.null_data, rvBrowseRecord);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
