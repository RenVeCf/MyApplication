package com.example.ipd.yueyue.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.adapter.SupplementaryAdapter;
import com.example.ipd.yueyue.base.BaseActivity;
import com.example.ipd.yueyue.bean.GetAddSupplementaryBean;
import com.example.ipd.yueyue.bean.IdImgDataBean;
import com.example.ipd.yueyue.bean.SupplementaryBean;
import com.example.ipd.yueyue.bean.UploadImgBean;
import com.example.ipd.yueyue.common.config.IConstants;
import com.example.ipd.yueyue.common.view.TopView;
import com.example.ipd.yueyue.contract.AddSupplementaryContract;
import com.example.ipd.yueyue.presenter.AddSupplementaryPresenter;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.example.ipd.yueyue.utils.SPUtil;
import com.example.ipd.yueyue.utils.ToastUtil;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

/**
 * Description ：补充材料
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/9.
 */
public class SupplementaryActivity extends BaseActivity<AddSupplementaryContract.View, AddSupplementaryContract.Presenter> implements AddSupplementaryContract.View {

    @BindView(R.id.tv_supplementary_top)
    TopView tvSupplementaryTop;
    @BindView(R.id.tv_top_add)
    TextView tvIdAdd;
    @BindView(R.id.rv_supplementary)
    RecyclerView rvSupplementary;
    @BindView(R.id.bt_supplementary)
    Button btSupplementary;

    private SupplementaryAdapter mGrowthValueAdapter;
    private List<SupplementaryBean> list = new ArrayList<>();
    private String proid;//资料id
    private int isAdd;//0：添加   1：修改

    private SupplementaryBean getData(String path, String name) {
        SupplementaryBean bean = new SupplementaryBean();
        bean.setImgName(name);
        bean.setImgPath(path);
        return bean;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_supplementary;
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
        ImmersionBar.setTitleBar(this, tvSupplementaryTop);

//        isAdd = getIntent().getIntExtra("isAdd", 0);
        proid = getIntent().getStringExtra("proidId");

        GridLayoutManager NotUseList = new GridLayoutManager(this, 2);
        rvSupplementary.setLayoutManager(NotUseList);
        rvSupplementary.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvSupplementary.setItemAnimator(new DefaultItemAnimator()); //默认动画

        //初始化数据
        mGrowthValueAdapter = new SupplementaryAdapter(proid + "", list);
        rvSupplementary.setAdapter(mGrowthValueAdapter);
    }

    @Override
    public void initListener() {
        mGrowthValueAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_household_registration_del:
                        mGrowthValueAdapter.removeData(position);
                        break;
                    case R.id.iv_household_registration:
                        SupplementaryBean supplementaryBean = list.get(position);
                        if (!TextUtils.isEmpty(supplementaryBean.getImgPath())) {
                            BigImageActivity.launch(SupplementaryActivity.this, supplementaryBean.getImgPath());
                        } else {

                        }
                        break;
                }
            }
        });
        View view = View.inflate(this, R.layout.null_data, null);
        mGrowthValueAdapter.setEmptyView(view);
    }

    @Override
    public void initData() {
        TreeMap<String, String> getIdImgMap = new TreeMap<>();
        getIdImgMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
        getIdImgMap.put("proid", proid);
        getPresenter().getAddSupplementarImg(getIdImgMap, true, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case IConstants.REQUEST_CODE_110:
                    String name = "";
                    ArrayList<String> path = new ArrayList<>();

                    if (!data.getStringExtra("addSupplementaryResultTx").equals("")) {
                        name = (data.getStringExtra("addSupplementaryResultTx"));
                    }
                    if (data.getStringArrayListExtra("addSupplementaryResultImg").size() > 0) {
                        path = (data.getStringArrayListExtra("addSupplementaryResultImg"));
                    }
                    for (int i = 0; i < path.size() - 1; i++) {
                        list.add(getData(path.get(i), name));
                    }
                    mGrowthValueAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @OnClick({R.id.tv_top_add, R.id.bt_supplementary})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_top_add:
                startActivityForResult(new Intent(this, AddSupplementaryActivity.class), IConstants.REQUEST_CODE_110);
                break;
            case R.id.bt_supplementary:
                if (mGrowthValueAdapter.isCanAdd()) {
                    TreeMap<String, String> idImgDataMap = new TreeMap<>();
                    idImgDataMap.put("userid", (String) SPUtil.get(this, IConstants.USER_ID, ""));
                    idImgDataMap.put("proid", proid);
                    idImgDataMap.put("data", mGrowthValueAdapter.getLoadString());
                    getPresenter().modifyAddSupplementarImgData(idImgDataMap, true, false);
                }
                break;
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {

    }

    @Override
    public void resultModifyAddSupplementarImgData(String data) {
        Gson g = new Gson();
        IdImgDataBean obj = g.fromJson(data, IdImgDataBean.class);
        ToastUtil.showShortToast(obj.getMsg());
        if (obj.isData()) {
            setResult(RESULT_OK, new Intent().putExtra("supplementaryResult", "1"));
            finish();
        }
    }

    @Override
    public void resultGetAddSupplementarImg(GetAddSupplementaryBean data) {
        list.clear();
        list.addAll(data.data);
        if (list.size() == 0) {

        }
        mGrowthValueAdapter.notifyDataSetChanged();


    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
