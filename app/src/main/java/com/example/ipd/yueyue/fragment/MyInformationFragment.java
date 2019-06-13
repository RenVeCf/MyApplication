package com.example.ipd.yueyue.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.activity.BrowseRecordActivity;
import com.example.ipd.yueyue.activity.MainActivity;
import com.example.ipd.yueyue.activity.ParticularsOfInformationMyActivity;
import com.example.ipd.yueyue.activity.UploadDataActivity;
import com.example.ipd.yueyue.adapter.MyInformationAdapter;
import com.example.ipd.yueyue.base.BaseFragment;
import com.example.ipd.yueyue.bean.MyForwardsBean;
import com.example.ipd.yueyue.bean.MyInformationBean;
import com.example.ipd.yueyue.bean.MyOffBean;
import com.example.ipd.yueyue.common.config.IConstants;
import com.example.ipd.yueyue.common.view.CustomLoadMoreView;
import com.example.ipd.yueyue.contract.MyInformationContract;
import com.example.ipd.yueyue.presenter.MyInformationPresenter;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.example.ipd.yueyue.utils.SPUtil;
import com.example.ipd.yueyue.utils.ToastUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;


/**
 * Description ：我的资料
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/3.
 */
public class MyInformationFragment extends BaseFragment<MyInformationContract.View, MyInformationContract.Presenter> implements MyInformationContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_my_information)
    RecyclerView rvMyInformation;
    @BindView(R.id.srl_my_information)
    SwipeRefreshLayout srlMyInformation;
    @BindView(R.id.bt_upload_data)
    Button btUploadData;

    private MyInformationAdapter myInformationAdapter;
    private List<MyInformationBean.DataBean> myInformationBean;
    private int pageNum = 1; //请求页数

    private EditText etDialogForward;
    private Button bt;
    private RadioButton rbDialogForwardWatermark;
    private RadioButton rbDialogForwardNoWatermark;
    private RadioButton rbDialogForwardCanForward;
    private RadioButton rbDialogForwardNoForward;
    private RadioButton rbDialogForwardCanDownload;
    private RadioButton rbDialogForwardNoDownload;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_information;
    }

    @Override
    public MyInformationPresenter createPresenter() {
        return new MyInformationPresenter(mContext);
    }

    @Override
    public MyInformationContract.View createView() {
        return this;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.tvTopTitle.setText(this.getResources().getString(R.string.my_information));
        mainActivity.tvTopTitle.setTextColor(Color.BLACK);
    }

    @Override
    public void init() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.tvTopTitle.setText(this.getResources().getString(R.string.my_information));
        mainActivity.tvTopTitle.setTextColor(Color.BLACK);

        //设置RecyclerView方向和是否反转
        LinearLayoutManager NotUseList = new LinearLayoutManager(ApplicationUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        rvMyInformation.setLayoutManager(NotUseList);
        rvMyInformation.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvMyInformation.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        myInformationBean = new ArrayList<>();
        myInformationAdapter = new MyInformationAdapter(myInformationBean);
        rvMyInformation.setAdapter(myInformationAdapter);
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlMyInformation.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                myInformationAdapter.setNewData(myInformationBean);
                srlMyInformation.setRefreshing(false);
            }
        });

        myInformationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivityForResult(new Intent(getContext(), ParticularsOfInformationMyActivity.class).putExtra("pro_id", myInformationBean.get(position).getPro_id() + ""), IConstants.REQUEST_CODE_112);
            }
        });

        myInformationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.bt_my_information_edit:
                        startActivity(new Intent(getContext(), UploadDataActivity.class)
                                .putExtra("my_info", 1)
                                .putExtra("pro_id", myInformationBean.get(position).getPro_id() + ""));
                        break;
                    case R.id.bt_my_information_forward:
                        setDialog(myInformationBean.get(position).getPro_id());
                        break;
                    case R.id.bt_my_information_off:
                        bt = (Button) view;
                        setMyInformationDialog(myInformationBean.get(position).getPro_id(), bt.getText().toString().trim());
                        break;
                    case R.id.bt_my_information_browse_record:
                        startActivity(new Intent(getContext(), BrowseRecordActivity.class).putExtra("pro_id", myInformationBean.get(position).getPro_id() + ""));
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        TreeMap<String, String> loginMap = new TreeMap<>();
        //用户id
        loginMap.put("userid", (String) SPUtil.get(getContext(), IConstants.USER_ID, ""));
        //页数
        loginMap.put("page", pageNum + "");
        getPresenter().getMyInformation(loginMap, true, false);
    }

    private void setMyInformationDialog(final int pro_id, final String btType) {
        TextView tv;
        final Dialog mCameraDialog = new Dialog(getContext(), R.style.BottomDialog);
        //Dialog布局
        LinearLayout root = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.dialog_center, null);
        tv = root.findViewById(R.id.tv_dialog_center_end);
        tv.setText("是否关闭？");
        //初始化视图
        root.findViewById(R.id.dialog_center_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TreeMap<String, String> myOffMap = new TreeMap<>();
                myOffMap.put("userid", (String) SPUtil.get(getContext(), IConstants.USER_ID, ""));
                myOffMap.put("proid", pro_id + "");
                if (btType.equals("关闭"))
                    myOffMap.put("del", "1");
                else
                    myOffMap.put("del", "0");
                getPresenter().getMyOff(myOffMap, true, false);
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

    private void setDialog(final int pro_id) {
        final Dialog mCameraDialog = new Dialog(getContext(), R.style.BottomDialog);
        //Dialog布局
        LinearLayout root = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.dialog_bottom_forward, null);
        //初始化视图
        etDialogForward = root.findViewById(R.id.et_dialog_forward);
        rbDialogForwardWatermark = root.findViewById(R.id.rb_dialog_forward_watermark);
        rbDialogForwardNoWatermark = root.findViewById(R.id.rb_dialog_forward_no_watermark);
        rbDialogForwardCanForward = root.findViewById(R.id.rb_dialog_forward_can_forward);
        rbDialogForwardNoForward = root.findViewById(R.id.rb_dialog_forward_no_forward);
        rbDialogForwardCanDownload = root.findViewById(R.id.rb_dialog_forward_can_download);
        rbDialogForwardNoDownload = root.findViewById(R.id.rb_dialog_forward_no_download);
        root.findViewById(R.id.bt_dialog_bottom_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etDialogForward.getText().toString().trim().equals("")) {
                    TreeMap<String, String> loginMap = new TreeMap<>();
                    loginMap.put("for_sponsor", (String) SPUtil.get(getContext(), IConstants.USER_ID, ""));
                    loginMap.put("for_proid", pro_id + "");
                    loginMap.put("for_accept", etDialogForward.getText().toString().trim());
                    if (rbDialogForwardWatermark.isChecked())
                        loginMap.put("for_watermark", "1");
                    else
                        loginMap.put("for_watermark", "0");
                    if (rbDialogForwardNoForward.isChecked())
                        loginMap.put("for_send", "1");
                    else
                        loginMap.put("for_send", "0");
                    if (rbDialogForwardNoDownload.isChecked())
                        loginMap.put("for_download", "1");
                    else
                        loginMap.put("for_download", "0");
                    getPresenter().getMyForwards(loginMap, false, false);
                    mCameraDialog.dismiss();
                } else
                    ToastUtil.showShortToast("请输入手机号！");

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
            }
        });
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM); //设置弹出方式
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();

        //定义底部标签图片大小
        Drawable drawableWatermark = getResources().getDrawable(R.drawable.select_icon_forward);
        drawableWatermark.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y40), (int) getResources().getDimension(R.dimen.y40));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbDialogForwardWatermark.setCompoundDrawables(drawableWatermark, null, null, null);//只放上面

        Drawable drawableNoWatermark = getResources().getDrawable(R.drawable.select_icon_forward);
        drawableNoWatermark.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y40), (int) getResources().getDimension(R.dimen.y40));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbDialogForwardNoWatermark.setCompoundDrawables(drawableNoWatermark, null, null, null);//只放上面

        Drawable drawableCanForward = getResources().getDrawable(R.drawable.select_icon_forward);
        drawableCanForward.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y40), (int) getResources().getDimension(R.dimen.y40));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbDialogForwardCanForward.setCompoundDrawables(drawableCanForward, null, null, null);//只放上面

        Drawable drawableNoForward = getResources().getDrawable(R.drawable.select_icon_forward);
        drawableNoForward.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y40), (int) getResources().getDimension(R.dimen.y40));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbDialogForwardNoForward.setCompoundDrawables(drawableNoForward, null, null, null);//只放上面

        Drawable drawableCanDownload = getResources().getDrawable(R.drawable.select_icon_forward);
        drawableCanDownload.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y40), (int) getResources().getDimension(R.dimen.y40));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbDialogForwardCanDownload.setCompoundDrawables(drawableCanDownload, null, null, null);//只放上面

        Drawable drawableNoDownload = getResources().getDrawable(R.drawable.select_icon_forward);
        drawableNoDownload.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y40), (int) getResources().getDimension(R.dimen.y40));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbDialogForwardNoDownload.setCompoundDrawables(drawableNoDownload, null, null, null);//只放上面
    }

    @Override
    public void resultMyInformation(final MyInformationBean data) {
        myInformationBean.clear();
        myInformationBean.addAll(data.getData());
        myInformationAdapter.setNewData(myInformationBean);

        //上拉加载（设置这个监听就表示有上拉加载功能了）
        myInformationAdapter.setLoadMoreView(new CustomLoadMoreView()
                /*new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rvMyInformation.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (data.getMsg().equals("false")) {
                            //数据全部加载完毕
                            myInformationAdapter.loadMoreEnd();
                        } else {
                            if (isErr) {
                                //成功获取更多数据（可以直接往适配器添加数据）
                                myInformationAdapter.addData(myInformationBean);
                                //主动调用加载完成，停止加载
                                myInformationAdapter.loadMoreComplete();
                            } else {
                                //获取更多数据失败
                                isErr = true;
                                ToastUtil.showLongToast("加载失败！");
                                //同理，加载失败也要主动调用加载失败来停止加载（而且该方法会提示加载失败）
                                myInformationAdapter.loadMoreFail();

                            }
                        }
                    }

                }, 5000);
            }
        }, rvMyInformation*/);
        //空数据时的页面
        myInformationAdapter.setEmptyView(R.layout.null_data, rvMyInformation);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CART_BROADCAST");
        BroadcastReceiver mItemViewListClickReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra("data");
                if ("refresh1".equals(msg)) {
                    initData();
                }
            }
        };
        broadcastManager.registerReceiver(mItemViewListClickReceiver, intentFilter);
    }

    @Override
    public void resultMyForwards(MyForwardsBean data) {
        ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public void resultMyOff(MyOffBean data) {
        if (bt.getText().toString().trim().equals("关闭"))
            bt.setText("开启");
        else
            bt.setText("关闭");
        ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }

    @Override
    public void onLoadMoreRequested() {
        initData();
    }

    @OnClick({R.id.bt_upload_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_upload_data:
                startActivity(new Intent(getActivity(), UploadDataActivity.class).putExtra("my_info", 0));
                break;
        }
    }
}
