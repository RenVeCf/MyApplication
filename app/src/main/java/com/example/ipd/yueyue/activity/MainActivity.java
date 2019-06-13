package com.example.ipd.yueyue.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ipd.yueyue.R;
import com.example.ipd.yueyue.base.BaseActivity;
import com.example.ipd.yueyue.base.BasePresenter;
import com.example.ipd.yueyue.base.BaseView;
import com.example.ipd.yueyue.common.config.IConstants;
import com.example.ipd.yueyue.common.view.TopView;
import com.example.ipd.yueyue.fragment.DocumentsOfConcernFragment;
import com.example.ipd.yueyue.fragment.DocumentsReceivedFragment;
import com.example.ipd.yueyue.fragment.MyInformationFragment;
import com.example.ipd.yueyue.utils.ApplicationUtil;
import com.example.ipd.yueyue.utils.NavigationBarUtil;
import com.example.ipd.yueyue.utils.PermissionUtils;
import com.example.ipd.yueyue.utils.SPUtil;
import com.example.ipd.yueyue.utils.ToastUtil;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：主页
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public class MainActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    @BindView(R.id.tv_main_top)
    TopView tvMainTop;
    @BindView(R.id.tv_top_title)
    public TextView tvTopTitle;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.rb_navigation_my_information)
    RadioButton rbNavigationMyInformation;
    @BindView(R.id.rb_navigation_documents_received)
    RadioButton rbNavigationDocumentsReceived;
    @BindView(R.id.rb_navigation_documents_of_concern)
    RadioButton rbNavigationDocumentsOfConcern;
    @BindView(R.id.iv_user_head)
    ImageView ivUserHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.ll_sidebar_my_information)
    LinearLayout llSidebarMyInformation;
    @BindView(R.id.dl_my_information)
    DrawerLayout dlMyInformation;
    @BindView(R.id.iv_top_sidebar)
    ImageView ivTopSidebar;
    @BindView(R.id.iv_white_service_organization)
    ImageView ivWhiteServiceOrganization;
    @BindView(R.id.iv_blue_service_organization)
    ImageView ivBlueServiceOrganization;
    @BindView(R.id.iv_service_organization)
    ImageView ivServiceOrganization;
    @BindView(R.id.tv_service_organization)
    TextView tvServiceOrganization;
    @BindView(R.id.rl_service_organization)
    RelativeLayout rlServiceOrganization;
    @BindView(R.id.iv_white_contact_platform)
    ImageView ivWhiteContactPlatform;
    @BindView(R.id.iv_blue_contact_platform)
    ImageView ivBlueContactPlatform;
    @BindView(R.id.iv_contact_platform)
    ImageView ivContactPlatform;
    @BindView(R.id.tv_contact_platform)
    TextView tvContactPlatform;
    @BindView(R.id.rl_contact_platform)
    RelativeLayout rlContactPlatform;
    @BindView(R.id.iv_white_business_cooperation)
    ImageView ivWhiteBusinessCooperation;
    @BindView(R.id.iv_blue_business_cooperation)
    ImageView ivBlueBusinessCooperation;
    @BindView(R.id.iv_business_cooperation)
    ImageView ivBusinessCooperation;
    @BindView(R.id.tv_business_cooperation)
    TextView tvBusinessCooperation;
    @BindView(R.id.rl_business_cooperation)
    RelativeLayout rlBusinessCooperation;
    @BindView(R.id.iv_white_locked_files)
    ImageView ivWhiteLockedFiles;
    @BindView(R.id.iv_blue_locked_files)
    ImageView ivBlueLockedFiles;
    @BindView(R.id.iv_locked_files)
    ImageView ivLockedFiles;
    @BindView(R.id.tv_locked_files)
    TextView tvLockedFiles;
    @BindView(R.id.rl_locked_files)
    RelativeLayout rlLockedFiles;
    @BindView(R.id.iv_white_setting)
    ImageView ivWhiteSetting;
    @BindView(R.id.iv_blue_setting)
    ImageView ivBlueSetting;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.ll_not_sidebar_my_information)
    LinearLayout llNotSidebarMyInformation;

    private long firstTime = 0;
    private Fragment currentFragment = new Fragment();
    private MyInformationFragment myInformationFragment = new MyInformationFragment();
    private DocumentsReceivedFragment documentsReceivedFragment = new DocumentsReceivedFragment();
    private DocumentsOfConcernFragment documentsOfConcernFragment = new DocumentsOfConcernFragment();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
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
        //适配虚拟按键
        if (NavigationBarUtil.hasNavigationBar(this)) {
            NavigationBarUtil.initActivity(findViewById(android.R.id.content));
        }
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvMainTop);
        //上一层界面跳过来时，要求显示对的碎片
        switch (getIntent().getIntExtra("howPage", 0)) {
            case 0:
                rbNavigationMyInformation.setChecked(true);
                switchFragment(myInformationFragment).commit();
                break;
            case 1:
                rbNavigationDocumentsReceived.setChecked(true);
                switchFragment(documentsReceivedFragment).commit();
                break;
        }

        Glide.with(this).load(SPUtil.get(this, IConstants.AVATAR, "")).apply(new RequestOptions()).into(ivUserHead);
        tvUserName.setText((String) SPUtil.get(this, IConstants.NAME, ""));
        tvUserPhone.setText((String) SPUtil.get(this, IConstants.PHONE, ""));

        //定义底部标签图片大小
        Drawable drawableMemberber = getResources().getDrawable(R.drawable.select_icon_my_information);
        drawableMemberber.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y50), (int) getResources().getDimension(R.dimen.y50));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbNavigationMyInformation.setCompoundDrawables(null, drawableMemberber, null, null);//只放上面

        Drawable drawableCoupon = getResources().getDrawable(R.drawable.select_icon_documents_received);
        drawableCoupon.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y50), (int) getResources().getDimension(R.dimen.y50));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbNavigationDocumentsReceived.setCompoundDrawables(null, drawableCoupon, null, null);//只放上面

        Drawable drawableActivities = getResources().getDrawable(R.drawable.select_icon_documents_of_concern);
        drawableActivities.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y50), (int) getResources().getDimension(R.dimen.y50));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbNavigationDocumentsOfConcern.setCompoundDrawables(null, drawableActivities, null, null);//只放上面

        dlMyInformation.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        dlMyInformation.setScrimColor(Color.TRANSPARENT);//侧滑菜单打开后主内容区域的颜色
        dlMyInformation.addDrawerListener(drawerListener);

        //定义底部标签图片大小
        ivBlueServiceOrganization.setImageResource(R.drawable.bg_sidebar_item_select);
        ViewGroup.LayoutParams paramsBlueServiceOrganization = ivBlueServiceOrganization.getLayoutParams();
        paramsBlueServiceOrganization.width = 15;
        ivBlueServiceOrganization.setLayoutParams(paramsBlueServiceOrganization);

        ivServiceOrganization.setImageResource(R.drawable.ic_sidebar_service_organization);
        ViewGroup.LayoutParams paramsServiceOrganization = ivServiceOrganization.getLayoutParams();
        paramsServiceOrganization.height = 50;
        paramsServiceOrganization.width = 50;
        ivServiceOrganization.setLayoutParams(paramsServiceOrganization);

        ivServiceOrganization.setImageResource(R.drawable.ic_sidebar_service_organization_select);
        ViewGroup.LayoutParams paramsServiceOrganizationSelect = ivServiceOrganization.getLayoutParams();
        paramsServiceOrganizationSelect.height = 50;
        paramsServiceOrganizationSelect.width = 50;
        ivServiceOrganization.setLayoutParams(paramsServiceOrganizationSelect);

        ivBlueContactPlatform.setImageResource(R.drawable.bg_sidebar_item_select);
        ViewGroup.LayoutParams paramsBlueContactPlatform = ivBlueContactPlatform.getLayoutParams();
        paramsBlueContactPlatform.width = 15;
        ivBlueContactPlatform.setLayoutParams(paramsBlueContactPlatform);

        ivContactPlatform.setImageResource(R.drawable.ic_sidebar_contact_platform_select);
        ViewGroup.LayoutParams paramsContactPlatformSelect = ivContactPlatform.getLayoutParams();
        paramsContactPlatformSelect.height = 50;
        paramsContactPlatformSelect.width = 50;
        ivContactPlatform.setLayoutParams(paramsContactPlatformSelect);

        ivContactPlatform.setImageResource(R.drawable.ic_sidebar_contact_platform);
        ViewGroup.LayoutParams paramsContactPlatform = ivContactPlatform.getLayoutParams();
        paramsContactPlatform.height = 50;
        paramsContactPlatform.width = 50;
        ivContactPlatform.setLayoutParams(paramsContactPlatform);

        ivBlueBusinessCooperation.setImageResource(R.drawable.bg_sidebar_item_select);
        ViewGroup.LayoutParams paramsBlueBusinessCooperation = ivBlueBusinessCooperation.getLayoutParams();
        paramsBlueBusinessCooperation.width = 15;
        ivBlueBusinessCooperation.setLayoutParams(paramsBlueContactPlatform);

        ivBusinessCooperation.setImageResource(R.drawable.ic_sidebar_business_cooperation_select);
        ViewGroup.LayoutParams paramsBusinessCooperationSelect = ivBusinessCooperation.getLayoutParams();
        paramsBusinessCooperationSelect.height = 50;
        paramsBusinessCooperationSelect.width = 50;
        ivBusinessCooperation.setLayoutParams(paramsBusinessCooperationSelect);

        ivBusinessCooperation.setImageResource(R.drawable.ic_sidebar_business_cooperation);
        ViewGroup.LayoutParams paramsBusinessCooperation = ivBusinessCooperation.getLayoutParams();
        paramsBusinessCooperation.height = 50;
        paramsBusinessCooperation.width = 50;
        ivBusinessCooperation.setLayoutParams(paramsBusinessCooperation);

        ivBlueLockedFiles.setImageResource(R.drawable.bg_sidebar_item_select);
        ViewGroup.LayoutParams paramsBlueLockedFiles = ivBlueLockedFiles.getLayoutParams();
        paramsBlueLockedFiles.width = 15;
        ivBlueLockedFiles.setLayoutParams(paramsBlueLockedFiles);

        ivLockedFiles.setImageResource(R.drawable.ic_sidebar_locked_files_select);
        ViewGroup.LayoutParams paramsLockedFilesSelect = ivLockedFiles.getLayoutParams();
        paramsLockedFilesSelect.height = 50;
        paramsLockedFilesSelect.width = 50;
        ivLockedFiles.setLayoutParams(paramsLockedFilesSelect);

        ivLockedFiles.setImageResource(R.drawable.ic_sidebar_locked_files);
        ViewGroup.LayoutParams paramsLockedFiles = ivLockedFiles.getLayoutParams();
        paramsServiceOrganization.height = 50;
        paramsServiceOrganization.width = 50;
        ivLockedFiles.setLayoutParams(paramsLockedFiles);

        ivBlueSetting.setImageResource(R.drawable.bg_sidebar_item_select);
        ViewGroup.LayoutParams paramsBlueSetting = ivBlueSetting.getLayoutParams();
        paramsBlueSetting.width = 15;
        ivBlueSetting.setLayoutParams(paramsBlueSetting);

        ivSetting.setImageResource(R.drawable.ic_sidebar_setting_select);
        ViewGroup.LayoutParams paramsSettingSelect = ivSetting.getLayoutParams();
        paramsSettingSelect.height = 50;
        paramsSettingSelect.width = 50;
        ivSetting.setLayoutParams(paramsSettingSelect);

        ivSetting.setImageResource(R.drawable.ic_sidebar_setting);
        ViewGroup.LayoutParams paramsSetting = ivSetting.getLayoutParams();
        paramsSetting.height = 50;
        paramsSetting.width = 50;
        ivSetting.setLayoutParams(paramsSetting);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    /**
     * 双击退出程序
     */
    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            ToastUtil.showShortToast(getResources().getString(R.string.click_out_again));
            firstTime = secondTime;
        } else {
            ApplicationUtil.getManager().exitApp();
        }
    }

    //Fragment优化
    public FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.ll_main, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getStringExtra("refresh") != null) {
            if (intent.getStringExtra("refresh").equals("1")) {
                myInformationFragment.initData();
            }
        }
    }

    DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            // 得到contentView
            View content = dlMyInformation.getChildAt(0);
            int offset = (int) (drawerView.getWidth() * slideOffset);
            content.setTranslationX(offset);
        }

        @Override
        public void onDrawerOpened(View drawerView) {

        }

        @Override
        public void onDrawerClosed(View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    private void deleteCalendarEvent() {
        setOnPermissionListener(new OnPermissionListener() {
            @Override
            public void openIntent() {//设置权限监听之后，执行自己的操作
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "400-806-7299");
                intent.setData(data);
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
        openPermission(new int[]{PermissionUtils.CODE_PHONE});//请求日历和内存卡权限
    }

    private void setContactPlatformDialog() {
        TextView tv;
        final Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
        //Dialog布局
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.dialog_center, null);
        root.findViewById(R.id.tv_dialog_center_start).setVisibility(View.VISIBLE);
        tv = root.findViewById(R.id.tv_dialog_center_end);
        tv.setText("400-806-7299");
        //初始化视图
        root.findViewById(R.id.dialog_center_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCalendarEvent();
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IConstants.REQUEST_CODE_98:
                Glide.with(this).load(SPUtil.get(this, IConstants.AVATAR, "")).apply(new RequestOptions()).into(ivUserHead);
                tvUserName.setText((String) SPUtil.get(this, IConstants.NAME, ""));
                break;
        }
    }

    @OnClick({R.id.rb_navigation_my_information, R.id.iv_top_sidebar, R.id.rb_navigation_documents_received, R.id.rb_navigation_documents_of_concern, R.id.rl_service_organization, R.id.rl_contact_platform, R.id.rl_business_cooperation, R.id.rl_locked_files, R.id.rl_setting, R.id.ll_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_top_sidebar:
                if (!dlMyInformation.isDrawerOpen(llSidebarMyInformation)) {
                    dlMyInformation.openDrawer(llSidebarMyInformation);
                }
                break;
            case R.id.rb_navigation_my_information:
                switchFragment(myInformationFragment).commit();
                break;
            case R.id.rb_navigation_documents_received:
                switchFragment(documentsReceivedFragment).commit();
                break;
            case R.id.rb_navigation_documents_of_concern:
                switchFragment(documentsOfConcernFragment).commit();
                break;
            case R.id.ll_head:
                if (dlMyInformation.isDrawerOpen(llSidebarMyInformation)) {
                    dlMyInformation.closeDrawer(llSidebarMyInformation);
                }
                startActivityForResult(new Intent(this, PersonalDataActivity.class), IConstants.REQUEST_CODE_98);
                break;
            case R.id.rl_service_organization:
                if (dlMyInformation.isDrawerOpen(llSidebarMyInformation)) {
                    dlMyInformation.closeDrawer(llSidebarMyInformation);
                }
                startActivity(new Intent(this, ServiceOrganizationActivity.class));

                ivWhiteServiceOrganization.setVisibility(View.VISIBLE);
                ivBlueServiceOrganization.setVisibility(View.VISIBLE);
                ivServiceOrganization.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_service_organization_select));
                tvServiceOrganization.setTextColor(this.getResources().getColor(R.color.tx_select_fragment));

                ivWhiteContactPlatform.setVisibility(View.GONE);
                ivBlueContactPlatform.setVisibility(View.INVISIBLE);
                ivContactPlatform.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_contact_platform));
                tvContactPlatform.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteBusinessCooperation.setVisibility(View.GONE);
                ivBlueBusinessCooperation.setVisibility(View.INVISIBLE);
                ivBusinessCooperation.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_business_cooperation));
                tvBusinessCooperation.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteLockedFiles.setVisibility(View.GONE);
                ivBlueLockedFiles.setVisibility(View.INVISIBLE);
                ivLockedFiles.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_locked_files));
                tvLockedFiles.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteSetting.setVisibility(View.GONE);
                ivBlueSetting.setVisibility(View.INVISIBLE);
                ivSetting.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_setting));
                tvSetting.setTextColor(this.getResources().getColor(R.color.black));
                break;
            case R.id.rl_contact_platform:
                setContactPlatformDialog();

                ivWhiteContactPlatform.setVisibility(View.VISIBLE);
                ivBlueContactPlatform.setVisibility(View.VISIBLE);
                ivContactPlatform.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_contact_platform_select));
                tvContactPlatform.setTextColor(this.getResources().getColor(R.color.tx_select_fragment));

                ivWhiteServiceOrganization.setVisibility(View.GONE);
                ivBlueServiceOrganization.setVisibility(View.INVISIBLE);
                ivServiceOrganization.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_service_organization));
                tvServiceOrganization.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteBusinessCooperation.setVisibility(View.GONE);
                ivBlueBusinessCooperation.setVisibility(View.INVISIBLE);
                ivBusinessCooperation.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_business_cooperation));
                tvBusinessCooperation.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteLockedFiles.setVisibility(View.GONE);
                ivBlueLockedFiles.setVisibility(View.INVISIBLE);
                ivLockedFiles.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_locked_files));
                tvLockedFiles.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteSetting.setVisibility(View.GONE);
                ivBlueSetting.setVisibility(View.INVISIBLE);
                ivSetting.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_setting));
                tvSetting.setTextColor(this.getResources().getColor(R.color.black));
                break;
            case R.id.rl_business_cooperation:
                if (dlMyInformation.isDrawerOpen(llSidebarMyInformation)) {
                    dlMyInformation.closeDrawer(llSidebarMyInformation);
                }
                startActivity(new Intent(this, BusinessCooperationActivity.class));

                ivWhiteBusinessCooperation.setVisibility(View.VISIBLE);
                ivBlueBusinessCooperation.setVisibility(View.VISIBLE);
                ivBusinessCooperation.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_business_cooperation_select));
                tvBusinessCooperation.setTextColor(this.getResources().getColor(R.color.tx_select_fragment));

                ivWhiteContactPlatform.setVisibility(View.GONE);
                ivBlueContactPlatform.setVisibility(View.INVISIBLE);
                ivContactPlatform.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_contact_platform));
                tvContactPlatform.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteServiceOrganization.setVisibility(View.GONE);
                ivBlueServiceOrganization.setVisibility(View.INVISIBLE);
                ivServiceOrganization.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_service_organization));
                tvServiceOrganization.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteLockedFiles.setVisibility(View.GONE);
                ivBlueLockedFiles.setVisibility(View.INVISIBLE);
                ivLockedFiles.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_locked_files));
                tvLockedFiles.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteSetting.setVisibility(View.GONE);
                ivBlueSetting.setVisibility(View.INVISIBLE);
                ivSetting.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_setting));
                tvSetting.setTextColor(this.getResources().getColor(R.color.black));
                break;
            case R.id.rl_locked_files:
                if (dlMyInformation.isDrawerOpen(llSidebarMyInformation)) {
                    dlMyInformation.closeDrawer(llSidebarMyInformation);
                }
                startActivity(new Intent(this, LockedFilesActivity.class));

                ivWhiteLockedFiles.setVisibility(View.VISIBLE);
                ivBlueLockedFiles.setVisibility(View.VISIBLE);
                ivLockedFiles.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_locked_files_select));
                tvLockedFiles.setTextColor(this.getResources().getColor(R.color.tx_select_fragment));

                ivWhiteContactPlatform.setVisibility(View.GONE);
                ivBlueContactPlatform.setVisibility(View.INVISIBLE);
                ivContactPlatform.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_contact_platform));
                tvContactPlatform.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteBusinessCooperation.setVisibility(View.GONE);
                ivBlueBusinessCooperation.setVisibility(View.INVISIBLE);
                ivBusinessCooperation.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_business_cooperation));
                tvBusinessCooperation.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteServiceOrganization.setVisibility(View.GONE);
                ivBlueServiceOrganization.setVisibility(View.INVISIBLE);
                ivServiceOrganization.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_service_organization));
                tvServiceOrganization.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteSetting.setVisibility(View.GONE);
                ivBlueSetting.setVisibility(View.INVISIBLE);
                ivSetting.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_setting));
                tvSetting.setTextColor(this.getResources().getColor(R.color.black));
                break;
            case R.id.rl_setting:
                if (dlMyInformation.isDrawerOpen(llSidebarMyInformation)) {
                    dlMyInformation.closeDrawer(llSidebarMyInformation);
                }
                startActivity(new Intent(this, SettingsActivity.class));

                ivWhiteSetting.setVisibility(View.VISIBLE);
                ivBlueSetting.setVisibility(View.VISIBLE);
                ivSetting.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_setting_select));
                tvSetting.setTextColor(this.getResources().getColor(R.color.tx_select_fragment));

                ivWhiteContactPlatform.setVisibility(View.GONE);
                ivBlueContactPlatform.setVisibility(View.INVISIBLE);
                ivContactPlatform.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_contact_platform));
                tvContactPlatform.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteBusinessCooperation.setVisibility(View.GONE);
                ivBlueBusinessCooperation.setVisibility(View.INVISIBLE);
                ivBusinessCooperation.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_business_cooperation));
                tvBusinessCooperation.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteLockedFiles.setVisibility(View.GONE);
                ivBlueLockedFiles.setVisibility(View.INVISIBLE);
                ivLockedFiles.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_locked_files));
                tvLockedFiles.setTextColor(this.getResources().getColor(R.color.black));

                ivWhiteServiceOrganization.setVisibility(View.GONE);
                ivBlueServiceOrganization.setVisibility(View.INVISIBLE);
                ivServiceOrganization.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_sidebar_service_organization));
                tvServiceOrganization.setTextColor(this.getResources().getColor(R.color.black));
                break;
        }
    }
}
