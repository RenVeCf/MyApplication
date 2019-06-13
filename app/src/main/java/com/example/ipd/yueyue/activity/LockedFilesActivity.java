package com.example.ipd.yueyue.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.adapter.LockedFilesAdapter;
import com.example.ipd.yueyue.base.BaseActivity;
import com.example.ipd.yueyue.bean.LockedFilesBean;
import com.example.ipd.yueyue.bean.LockedFilesDelBean;
import com.example.ipd.yueyue.bean.LockedFilesSelectBean;
import com.example.ipd.yueyue.common.config.IConstants;
import com.example.ipd.yueyue.common.view.CustomLoadMoreView;
import com.example.ipd.yueyue.common.view.TopView;
import com.example.ipd.yueyue.contract.LockedFilesContract;
import com.example.ipd.yueyue.presenter.LockedFilesPresenter;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.example.ipd.yueyue.utils.SPUtil;
import com.example.ipd.yueyue.utils.ToastUtil;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

/**
 * Description ：锁定的文件
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/4.
 */
public class LockedFilesActivity extends BaseActivity<LockedFilesContract.View, LockedFilesContract.Presenter> implements LockedFilesContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.tv_locked_files_top)
    TopView tvLockedFilesTop;
    @BindView(R.id.rv_locked_files)
    RecyclerView rvLockedFiles;
    @BindView(R.id.srl_locked_files)
    SwipeRefreshLayout srlLockedFiles;

    private LockedFilesAdapter lockedFilesAdapter;
    private List<LockedFilesBean.DataBean> lockedFilesBean;
    private int pageNum = 1; //请求页数
    private int delPosition;

    @Override
    public int getLayoutId() {
        return R.layout.activity_locked_files;
    }

    @Override
    public LockedFilesContract.Presenter createPresenter() {
        return new LockedFilesPresenter(this);
    }

    @Override
    public LockedFilesContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvLockedFilesTop);

        //设置RecyclerView方向和是否反转
        LinearLayoutManager NotUseList = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvLockedFiles.setLayoutManager(NotUseList);
        rvLockedFiles.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvLockedFiles.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        lockedFilesBean = new ArrayList<>();
        lockedFilesAdapter = new LockedFilesAdapter(lockedFilesBean);
        rvLockedFiles.setAdapter(lockedFilesAdapter);
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlLockedFiles.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                lockedFilesAdapter.setNewData(lockedFilesBean);
                srlLockedFiles.setRefreshing(false);
            }
        });

        lockedFilesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivityForResult(new Intent(LockedFilesActivity.this, ParticularsOfInformationLockActivity.class).putExtra("loc_id", lockedFilesBean.get(position).getLoc_id() + "").putExtra("pro_id", lockedFilesBean.get(position).getPro_id() + ""), IConstants.REQUEST_CODE_111);
            }
        });
        lockedFilesAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.bt_locked_files_select:
                        startActivityForResult(new Intent(LockedFilesActivity.this, ParticularsOfInformationLockActivity.class).putExtra("loc_id", lockedFilesBean.get(position).getLoc_id() + "").putExtra("pro_id", lockedFilesBean.get(position).getPro_id() + ""), IConstants.REQUEST_CODE_111);
                        break;
                    case R.id.bt_locked_files_delete:
                        delPosition = position;
                        String locId = lockedFilesBean.get(position).getLoc_id() + "";
                        setLockedFilesDialog(locId);
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        TreeMap<String, String> lockedFilesMap = new TreeMap<>();
        //用户id
        lockedFilesMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
        //页数
        lockedFilesMap.put("page", pageNum + "");
        getPresenter().getLockedFiles(lockedFilesMap, true, false);
    }

    private void setLockedFilesDialog(final String locId) {
        TextView tv;
        final Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
        //Dialog布局
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.dialog_center, null);
        tv = root.findViewById(R.id.tv_dialog_center_end);
        tv.setText("是否删除？");
        //初始化视图
        root.findViewById(R.id.dialog_center_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TreeMap<String, String> lockedFilesDleMap = new TreeMap<>();
                lockedFilesDleMap.put("loc_id", locId);
                getPresenter().getLockedFilesDel(lockedFilesDleMap, true, false);
                mCameraDialog.dismiss();
            }
        });
        root.findViewById(R.id.dialog_center_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCameraDialog.dismiss();
            }
        });
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER); //设置弹出方式
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = 700;
        root.measure(0, 0);
        lp.height = 320;

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    @Override
    public void onLoadMoreRequested() {
        initData();
    }

    @Override
    public void resultLockedFiles(final LockedFilesBean data) {
        lockedFilesBean.clear();
        lockedFilesBean.addAll(data.getData());
        lockedFilesAdapter.setNewData(lockedFilesBean);

        //上拉加载（设置这个监听就表示有上拉加载功能了）
        lockedFilesAdapter.setLoadMoreView(new CustomLoadMoreView()
        /*new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rvLockedFiles.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (data.getMsg().equals("false")) {
                            //数据全部加载完毕
                            lockedFilesAdapter.loadMoreEnd();
                        } else {
                            if (isErr) {
                                //成功获取更多数据（可以直接往适配器添加数据）
                                lockedFilesAdapter.addData(lockedFilesBean);
                                //主动调用加载完成，停止加载
                                lockedFilesAdapter.loadMoreComplete();
                            } else {
                                //获取更多数据失败
                                isErr = true;
                                ToastUtil.showLongToast("加载失败！");
                                //同理，加载失败也要主动调用加载失败来停止加载（而且该方法会提示加载失败）
                                lockedFilesAdapter.loadMoreFail();

                            }
                        }
                    }

                }, 5000);
            }
        }, rvLockedFiles*/);
        //空数据时的页面
        lockedFilesAdapter.setEmptyView(R.layout.null_data, rvLockedFiles);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case IConstants.REQUEST_CODE_111:
                    initData();
                    break;
            }
        }
    }

    @Override
    public void resultLockedFilesDel(LockedFilesDelBean data) {
        lockedFilesAdapter.removeData(delPosition);
        ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public void resultLockedFilesSelect(LockedFilesSelectBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
