package com.example.ipd.yueyue.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.activity.MainActivity;
import com.example.ipd.yueyue.activity.ParticularsOfInformationReceivedActivity;
import com.example.ipd.yueyue.adapter.DocumentsReceivedAdapter;
import com.example.ipd.yueyue.base.BaseFragment;
import com.example.ipd.yueyue.bean.DocumentsReceivedBean;
import com.example.ipd.yueyue.bean.LockedFilesAddBean;
import com.example.ipd.yueyue.bean.ReceiveDownloadBean;
import com.example.ipd.yueyue.bean.ReceiveFollowBean;
import com.example.ipd.yueyue.bean.ReceiveForwardBean;
import com.example.ipd.yueyue.bean.SelectOtherBean;
import com.example.ipd.yueyue.common.config.IConstants;
import com.example.ipd.yueyue.common.view.CustomLoadMoreView;
import com.example.ipd.yueyue.contract.DocumentsReceivedContract;
import com.example.ipd.yueyue.presenter.DocumentsReceivedPresenter;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.example.ipd.yueyue.utils.SPUtil;
import com.example.ipd.yueyue.utils.ToastUtil;
import com.example.ipd.yueyue.utils.VerifyUtils;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

/**
 * Description ：收到文件
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/3.
 */
public class DocumentsReceivedFragment extends BaseFragment<DocumentsReceivedContract.View, DocumentsReceivedContract.Presenter> implements DocumentsReceivedContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_documents_received)
    RecyclerView rvDocumentsReceived;
    @BindView(R.id.srl_documents_received)
    SwipeRefreshLayout srlDocumentsReceived;

    private DocumentsReceivedAdapter documentsReceivedAdapter;
    private List<DocumentsReceivedBean.DataBean> documentsReceivedBean;
    private int pageNum = 1; //请求页数

    private EditText etDialogForward;
    private EditText etDialogDownload;
    private Button btLocking;
    private Button btFollow;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_documents_received;
    }

    @Override
    public DocumentsReceivedContract.Presenter createPresenter() {
        return new DocumentsReceivedPresenter(mContext);
    }

    @Override
    public DocumentsReceivedContract.View createView() {
        return this;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.tvTopTitle.setText(this.getResources().getString(R.string.documents_received));
        mainActivity.tvTopTitle.setTextColor(Color.BLACK);
    }

    @Override
    public void init() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.tvTopTitle.setText(this.getResources().getString(R.string.documents_received));
        mainActivity.tvTopTitle.setTextColor(Color.BLACK);

        //设置RecyclerView方向和是否反转
        LinearLayoutManager NotUseList = new LinearLayoutManager(ApplicationUtil.getContext(), LinearLayoutManager.VERTICAL, false);
        rvDocumentsReceived.setLayoutManager(NotUseList);
        rvDocumentsReceived.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvDocumentsReceived.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        documentsReceivedBean = new ArrayList<>();
        documentsReceivedAdapter = new DocumentsReceivedAdapter(documentsReceivedBean);
        rvDocumentsReceived.setAdapter(documentsReceivedAdapter);
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlDocumentsReceived.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                documentsReceivedAdapter.setNewData(documentsReceivedBean);
                srlDocumentsReceived.setRefreshing(false);
            }
        });

        documentsReceivedAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivityForResult(new Intent(getContext(), ParticularsOfInformationReceivedActivity.class).putExtra("for_id", documentsReceivedBean.get(position).getFor_id() + "").putExtra("pro_id", documentsReceivedBean.get(position).getPro_id() + ""), IConstants.REQUEST_CODE_113);
            }
        });

        documentsReceivedAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.bt_documents_received_select:
                        startActivityForResult(new Intent(getContext(), ParticularsOfInformationReceivedActivity.class).putExtra("for_id", documentsReceivedBean.get(position).getFor_id() + "").putExtra("pro_id", documentsReceivedBean.get(position).getPro_id() + ""), IConstants.REQUEST_CODE_113);
                        break;
                    case R.id.bt_documents_received_forward:
                        setDialog("1", documentsReceivedBean.get(position).getFor_id() + "");
                        break;
                    case R.id.bt_documents_received_download:
                        setDialog("2", documentsReceivedBean.get(position).getFor_id() + "");
                        break;
                    case R.id.bt_documents_received_locking:
                        btLocking = (Button) view;
                        setDocumentsReceivedDialog("0", documentsReceivedBean.get(position).getFor_id() + "", "");
                        break;
                    case R.id.bt_documents_received_follow:
                        btFollow = (Button) view;
                        String isFollow;
                        if (documentsReceivedBean.get(position).getFor_attention() == 0)
                            isFollow = "1";
                        else
                            isFollow = "0";
                        setDocumentsReceivedDialog("1", documentsReceivedBean.get(position).getFor_id() + "", isFollow);
                        break;
                }
            }
        });
    }

    private void setDocumentsReceivedDialog(final String falg, final String forId, final String isFollow) {
        final TextView tv;
        final Dialog mCameraDialog = new Dialog(getContext(), R.style.BottomDialog);
        //Dialog布局
        LinearLayout root = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.dialog_center, null);
        tv = root.findViewById(R.id.tv_dialog_center_end);
        switch (falg) {
            case "0":
                tv.setText("是否锁定？");
                break;
            case "1":
                tv.setText("是否关注？");
                break;
        }

        //初始化视图
        root.findViewById(R.id.dialog_center_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (falg) {
                    case "0":
                        TreeMap<String, String> lockingMap = new TreeMap<>();
                        lockingMap.put("userid", (String) SPUtil.get(getContext(), IConstants.USER_ID, ""));
                        lockingMap.put("for_id", forId);
                        getPresenter().getLockedFilesAdd(lockingMap, true, false);
                        break;
                    case "1":
                        TreeMap<String, String> followMap = new TreeMap<>();
                        followMap.put("for_id", forId);
                        followMap.put("attention", isFollow);
                        getPresenter().getReceiveFollow(followMap, true, false);
                        break;
                }
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CART_BROADCAST");
        BroadcastReceiver mItemViewListClickReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra("data");
                if ("refresh2".equals(msg)) {
                    initData();
                }
            }
        };
        broadcastManager.registerReceiver(mItemViewListClickReceiver, intentFilter);
    }

    private void setDialog(final String str, final String forId) {
        final Dialog mCameraDialog = new Dialog(getContext(), R.style.BottomDialog);
        int layout = 0;
        //Dialog布局
        switch (str) {
            case "1":
                layout = R.layout.dialog_bottom_forward_to_forward;
                break;
            case "2":
                layout = R.layout.dialog_bottom_download;
                break;

        }
        LinearLayout root = (LinearLayout) LayoutInflater.from(getContext()).inflate(layout, null);
        //初始化视图
        switch (str) {
            case "1":
                etDialogForward = root.findViewById(R.id.et_dialog_forward);
                break;
            case "2":
                etDialogDownload = root.findViewById(R.id.et_dialog_download);
                break;
        }

        root.findViewById(R.id.bt_dialog_bottom_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (str) {
                    case "1":
                        if (etDialogForward.getText().toString().trim().length() == 11 && VerifyUtils.isMobileNumber(etDialogForward.getText().toString().trim())) {
                            TreeMap<String, String> loginMap = new TreeMap<>();
                            loginMap.put("userid", (String) SPUtil.get(getContext(), IConstants.USER_ID, ""));
                            loginMap.put("for_id", forId);
                            loginMap.put("accept", etDialogForward.getText().toString().trim());
                            getPresenter().getReceiveForward(loginMap, true, false);
                            mCameraDialog.dismiss();
                        } else
                            ToastUtil.showShortToast("请输入正确的手机号！");
                        break;
                    case "2":
                        if (!etDialogDownload.getText().toString().trim().equals("") && VerifyUtils.isEmail(etDialogDownload.getText().toString().trim())) {
                            TreeMap<String, String> loginMap = new TreeMap<>();
                            loginMap.put("userid", (String) SPUtil.get(getContext(), IConstants.USER_ID, ""));
                            loginMap.put("dowid", forId);
                            loginMap.put("mailbox", etDialogDownload.getText().toString().trim());
                            getPresenter().getReceiveDownload(loginMap, true, false);
                            mCameraDialog.dismiss();
                        } else
                            ToastUtil.showShortToast("请输入正确的邮箱！");
                        break;
                }


                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                mCameraDialog.dismiss();
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
    }

    @Override
    public void initData() {
        TreeMap<String, String> loginMap = new TreeMap<>();
        //用户id
        loginMap.put("userid", (String) SPUtil.get(getContext(), IConstants.USER_ID, ""));
        //页数
        loginMap.put("page", pageNum + "");
        getPresenter().getDocumentsReceived(loginMap, true, false);
    }

    @Override
    public void resultDocumentsReceived(final DocumentsReceivedBean data) {
        documentsReceivedBean.clear();
        documentsReceivedBean.addAll(data.getData());
        documentsReceivedAdapter.setNewData(documentsReceivedBean);

        //上拉加载（设置这个监听就表示有上拉加载功能了）
        documentsReceivedAdapter.setLoadMoreView(new CustomLoadMoreView()
                /*new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rvDocumentsReceived.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (data.getMsg().equals("false")) {
                            //数据全部加载完毕
                            documentsReceivedAdapter.loadMoreEnd();
                        } else {
                            if (isErr) {
                                //成功获取更多数据（可以直接往适配器添加数据）
                                documentsReceivedAdapter.addData(documentsReceivedBean);
                                //主动调用加载完成，停止加载
                                documentsReceivedAdapter.loadMoreComplete();
                            } else {
                                //获取更多数据失败
                                isErr = true;
                                ToastUtil.showLongToast("加载失败！");                                //同理，加载失败也要主动调用加载失败来停止加载（而且该方法会提示加载失败）
                                documentsReceivedAdapter.loadMoreFail();

                            }
                        }
                    }

                }, 5000);
            }
        }, rvDocumentsReceived*/);
        //空数据时的页面
        documentsReceivedAdapter.setEmptyView(R.layout.null_data, rvDocumentsReceived);
    }

    @Override
    public void resultReceiveForward(ReceiveForwardBean data) {
        ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public void resultReceiveDownload(ReceiveDownloadBean data) {
        ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public void resultLockedFilesAdd(LockedFilesAddBean data) {
        btLocking.setTextColor(getContext().getResources().getColor(R.color.tx_not_select_fragment));
        btLocking.setEnabled(false);
        ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public void resultReceiveFollow(ReceiveFollowBean data) {
        btFollow.setTextColor(getContext().getResources().getColor(R.color.tx_not_select_fragment));
        btFollow.setEnabled(false);
        ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public void resultSelectOtherInfo(SelectOtherBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }

    @Override
    public void onLoadMoreRequested() {
        initData();
    }
}
