package com.example.ipd.yueyue.common.config;

/**
 * Description ：URL 配置
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface UrlConfig {

    String BASE_URL = "http://121.199.8.244:2837";

    /**
     * 登陆
     */
    String VERIFICATION_CODE = "api/mobileCode"; //验证码
    String LOGIN = "api/login"; //登陆
    String REGISTER = "api/reg"; //注册
    String FORGET_PWD = "api/forget"; //忘记密码


    /**
     * 资料
     */
    String UPLOAD_DATA = "api/profile/add"; //资料上传
    String UPLOAD_IMG = "api/upload"; //图片上传
    String MODIFY_DATA = "api/profile/edit"; //资料修改
    String GET_MY_INFO = "api/profile/get"; //获取我的资料
    String IS_SELECT_INFO = "api/oper/getfirst"; //查看上传了那些资料


    /**
     * 我的资料
     */
    String MY_DATA = "api/oper/profilelist"; //资料列表
    String MY_FORWARD = "api/deta/myfor"; //转发自己的资料
    String MY_OFF = "api/oper/profiledel"; //我的资料-开启与关闭
    String MY_BROWSE_RECORD = "api/deta/brow"; //浏览记录
    String SELECT_MY_INFO = "api/deta/mylook"; //资料详情-自己查看
    String SELECT_OTHER_INFO = "api/obta/talook"; //资料详情-他人查看
    String SELECT_BRIEF_SUMMARY = "api/schema/get"; //资料详情-简述概要


    /**
     * 收到的资料
     */
    String DOCUMENTS_RECEIVED = "api/obta/list"; //资料列表
    String RECEIVE_FORWARD = "api/obta/zyfa"; //转发他人的资料
    String RECEIVE_DOWNLOAD = "api/download"; //下载PDF
    String RECEIVE_FOLLOW = "api/obta/atte"; //关注与取消关注

    /**
     * 关注的资料
     */
    String DOCUMENTS_OF_CONCERN = "api/obta/attelist"; //资料列表


    /**
     * 修改证件
     */
    String ID_IMG_MODIFY = "api/card/edit"; //身份证-修改
    String ID_IMG_GET = "api/card/get"; //身份证-获取
    String MARRY_IMG_MODIFY = "api/marry/edit"; //结婚证-修改
    String MARRY_IMG_GET = "api/marry/get"; //结婚证-获取
    String ACCOUNT_IMG_MODIFY = "api/account/edit"; //户口本-修改
    String ACCOUNT_IMG_GET = "api/account/get"; //户口本-获取
    String HOUSE_IMG_MODIFY = "api/estate/edit"; //房产证-修改
    String HOUSE_IMG_GET = "api/estate/get"; //房产证-获取
    String COLLATEL_IMG_MODIFY = "api/mort/edit"; //抵押物-修改
    String COLLATEL_IMG_GET = "api/mort/get"; //抵押物-获取
    String ENTERPRISE_IMG_GET = "api/licen/get"; //企业信息-获取
    String ENTERPRISE_IMG_MODIFY = "api/licen/edit"; //企业信息-修改
    String CREDIT_IMG_GET = "api/cred/get"; //信用报告-获取
    String CREDIT_IMG_MODIFY = "api/cred/edit"; //信用报告-修改
    String BANK_IMG_GET = "api/bank/get"; //银行流水-获取
    String BANK_IMG_MODIFY = "api/bank/edit"; //银行流水-修改
    String ASSESSMENT_IMG_MODIFY = "api/asse/edit"; //评估报告-修改
    String ASSESSMENT_IMG_GET = "api/asse/get"; //评估报告-获取
    String PRODUCTION_ADJUSTMENT_IMG = "api/yield/edit"; //产调-修改
    String PRODUCTION_ADJUSTMENT_GET = "api/yield/get"; //产调-获取
    String ADD_SUPPLEMENTARY_MODIFY = "api/fart/edit"; //补充材料-修改
    String ADD_SUPPLEMENTARY_GET = "api/fart/get"; //补充材料-获取


    /**
     * 侧边栏
     */
    String SERVICE_ORGANIZATION = "api/myupdate"; //服务机构/职位修改
    String FEED_BACK = "api/feed/add"; //意见反馈
    String TEXT = "api/text"; //协议内容


    /**
     * 锁定文件
     */
    String LOCKED_FILES_LIST = "api/lock/list"; //锁定文件列表
    String LOCKED_FILES_ADD = "api/lock/add"; //锁定文件添加
    String LOCKED_FILES_DEL = "api/lock/del"; //锁定文件删除
    String LOCKED_FILES_SELECT = "api/lock/look"; //锁定文件查看


    /**
     * 主页
     */
    String GET_USER_INFO = "member_center/info"; //获取or提交用户信息
    String GROWTH_VALUE_LIST = "member_center/exp"; //获取用户成长值记录列表
    String COUPON_LIST = "member_center/tickets"; //获取券列表
    String COUPON_DETAILS = "member_center/ticket"; //获取券详情
    String COUPON_DETAILS_BT_URL = "member_center/unif_token"; //券详情中立即使用按钮跳转的URL
    String ACTIVITIES = "member_center/activities"; //获取活动列表
}
