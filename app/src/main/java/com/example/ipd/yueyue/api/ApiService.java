package com.example.ipd.yueyue.api;

import com.example.ipd.yueyue.bean.BriefSummaryBean;
import com.example.ipd.yueyue.bean.BrowseRecordBean;
import com.example.ipd.yueyue.bean.CaptchaBean;
import com.example.ipd.yueyue.bean.CardInfoBean;
import com.example.ipd.yueyue.bean.DocumentsOfConcernBean;
import com.example.ipd.yueyue.bean.DocumentsReceivedBean;
import com.example.ipd.yueyue.bean.FeedBackBean;
import com.example.ipd.yueyue.bean.GetAddSupplementaryBean;
import com.example.ipd.yueyue.bean.GetAssessmentImgBean;
import com.example.ipd.yueyue.bean.GetBankImgBean;
import com.example.ipd.yueyue.bean.GetCollateralImgBean;
import com.example.ipd.yueyue.bean.GetCompanyInform;
import com.example.ipd.yueyue.bean.GetCreditReportBean;
import com.example.ipd.yueyue.bean.GetHouseImgBean;
import com.example.ipd.yueyue.bean.GetHouseholdRegistrationBookBean;
import com.example.ipd.yueyue.bean.GetIdImgBean;
import com.example.ipd.yueyue.bean.GetMarryImgBean;
import com.example.ipd.yueyue.bean.GetMyInfoBean;
import com.example.ipd.yueyue.bean.LockedFilesAddBean;
import com.example.ipd.yueyue.bean.LockedFilesBean;
import com.example.ipd.yueyue.bean.LockedFilesDelBean;
import com.example.ipd.yueyue.bean.LockedFilesSelectBean;
import com.example.ipd.yueyue.bean.LoginBean;
import com.example.ipd.yueyue.bean.ModifyPersonalDataBean;
import com.example.ipd.yueyue.bean.ModifyServiceOrganizationBean;
import com.example.ipd.yueyue.bean.MyForwardsBean;
import com.example.ipd.yueyue.bean.MyInformationBean;
import com.example.ipd.yueyue.bean.MyOffBean;
import com.example.ipd.yueyue.bean.ReceiveDownloadBean;
import com.example.ipd.yueyue.bean.ReceiveFollowBean;
import com.example.ipd.yueyue.bean.ReceiveForwardBean;
import com.example.ipd.yueyue.bean.RegisterBean;
import com.example.ipd.yueyue.bean.SelectMyInfoBean;
import com.example.ipd.yueyue.bean.SelectOtherBean;
import com.example.ipd.yueyue.bean.TextBean;
import com.example.ipd.yueyue.bean.UploadDataBean;
import com.example.ipd.yueyue.bean.UploadImgBean;
import com.example.ipd.yueyue.common.config.UrlConfig;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * Description ：请求配置
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/27.
 */
public interface ApiService {

    //验证码
    @FormUrlEncoded
    @POST(UrlConfig.VERIFICATION_CODE)
    Observable<CaptchaBean> getCaptcha(@FieldMap Map<String, String> map);

    //登录
    @FormUrlEncoded
    @POST(UrlConfig.LOGIN)
    Observable<LoginBean> login(@FieldMap Map<String, String> map);

    //注册
    @FormUrlEncoded
    @POST(UrlConfig.REGISTER)
    Observable<RegisterBean> getRegister(@FieldMap Map<String, String> map);

    //忘记密码
    @FormUrlEncoded
    @POST(UrlConfig.FORGET_PWD)
    Observable<RegisterBean> getForgetPwd(@FieldMap Map<String, String> map);

    //资料上传
    @FormUrlEncoded
    @POST(UrlConfig.UPLOAD_DATA)
    Observable<UploadDataBean> getUploadData(@FieldMap Map<String, String> map);

    //查看上传了那些资料
    @FormUrlEncoded
    @POST(UrlConfig.IS_SELECT_INFO)
    Observable<CardInfoBean> getCardInfo(@FieldMap Map<String, String> map);

    //资料修改
    @FormUrlEncoded
    @POST(UrlConfig.MODIFY_DATA)
    Observable<UploadDataBean> getModifyData(@FieldMap Map<String, String> map);

    //获取我的资料
    @FormUrlEncoded
    @POST(UrlConfig.GET_MY_INFO)
    Observable<GetMyInfoBean> getMyInfo(@FieldMap Map<String, String> map);

    //上传图片
    @Multipart
    @POST(UrlConfig.UPLOAD_IMG)
    Observable<UploadImgBean> getUploadImg(@PartMap Map<String, RequestBody> map);

    //我的资料列表
    @FormUrlEncoded
    @POST(UrlConfig.MY_DATA)
    Observable<MyInformationBean> getMyInformation(@FieldMap Map<String, String> map);

    //收到的文件列表
    @FormUrlEncoded
    @POST(UrlConfig.DOCUMENTS_RECEIVED)
    Observable<DocumentsReceivedBean> getDocumentsReceived(@FieldMap Map<String, String> map);

    //关注的文件列表
    @FormUrlEncoded
    @POST(UrlConfig.DOCUMENTS_OF_CONCERN)
    Observable<DocumentsOfConcernBean> getDocumentsOfConcern(@FieldMap Map<String, String> map);

    //服务机构/职位修改
    @FormUrlEncoded
    @POST(UrlConfig.SERVICE_ORGANIZATION)
    Observable<ModifyServiceOrganizationBean> getModifyServiceOrganization(@FieldMap Map<String, String> map);

    //修改个人资料
    @FormUrlEncoded
    @POST(UrlConfig.SERVICE_ORGANIZATION)
    Observable<ModifyPersonalDataBean> getModifyPersonalData(@FieldMap Map<String, String> map);

    //意见反馈
    @FormUrlEncoded
    @POST(UrlConfig.FEED_BACK)
    Observable<FeedBackBean> getFeedBack(@FieldMap Map<String, String> map);

    //协议内容
    @FormUrlEncoded
    @POST(UrlConfig.TEXT)
    Observable<TextBean> getAboutUs(@FieldMap Map<String, String> map);

    //资料详情-简述概要
    @FormUrlEncoded
    @POST(UrlConfig.SELECT_BRIEF_SUMMARY)
    Observable<BriefSummaryBean> getBriefSummary(@FieldMap Map<String, String> map);

    //锁定文件列表
    @FormUrlEncoded
    @POST(UrlConfig.LOCKED_FILES_LIST)
    Observable<LockedFilesBean> getLockedFiles(@FieldMap Map<String, String> map);

    //锁定文件添加
    @FormUrlEncoded
    @POST(UrlConfig.LOCKED_FILES_ADD)
    Observable<LockedFilesAddBean> getLockedFilesAdd(@FieldMap Map<String, String> map);

    //锁定文件删除
    @FormUrlEncoded
    @POST(UrlConfig.LOCKED_FILES_DEL)
    Observable<LockedFilesDelBean> getLockedFilesDel(@FieldMap Map<String, String> map);

    //锁定文件删除
    @FormUrlEncoded
    @POST(UrlConfig.LOCKED_FILES_SELECT)
    Observable<LockedFilesSelectBean> getLockedFilesSelect(@FieldMap Map<String, String> map);

    //转发自己的资料
    @FormUrlEncoded
    @POST(UrlConfig.MY_FORWARD)
    Observable<MyForwardsBean> getMyForward(@FieldMap Map<String, String> map);

    //转发他人的资料
    @FormUrlEncoded
    @POST(UrlConfig.RECEIVE_FORWARD)
    Observable<ReceiveForwardBean> getReceiveForward(@FieldMap Map<String, String> map);

    //下载PDF
    @FormUrlEncoded
    @POST(UrlConfig.RECEIVE_DOWNLOAD)
    Observable<ReceiveDownloadBean> getReceiveDownload(@FieldMap Map<String, String> map);

    //我的资料-开启与关闭
    @FormUrlEncoded
    @POST(UrlConfig.MY_OFF)
    Observable<MyOffBean> getMyOff(@FieldMap Map<String, String> map);

    //浏览记录
    @FormUrlEncoded
    @POST(UrlConfig.MY_BROWSE_RECORD)
    Observable<BrowseRecordBean> getBrowseRecord(@FieldMap Map<String, String> map);

    //资料详情-自己查看
    @FormUrlEncoded
    @POST(UrlConfig.SELECT_MY_INFO)
    Observable<SelectMyInfoBean> getSelectMyInfo(@FieldMap Map<String, String> map);

    //资料详情-他人查看
    @FormUrlEncoded
    @POST(UrlConfig.SELECT_OTHER_INFO)
    Observable<SelectOtherBean> getSelectOtherInfo(@FieldMap Map<String, String> map);

    //关注与取消关注
    @FormUrlEncoded
    @POST(UrlConfig.RECEIVE_FOLLOW)
    Observable<ReceiveFollowBean> getReceiveFollow(@FieldMap Map<String, String> map);

    //身份证-修改
    @FormUrlEncoded
    @POST(UrlConfig.ID_IMG_MODIFY)
    Observable<String> getModifyIdImgData(@FieldMap Map<String, String> map);

    //身份证-获取
    @FormUrlEncoded
    @POST(UrlConfig.ID_IMG_GET)
    Observable<GetIdImgBean> getIdImgData(@FieldMap Map<String, String> map);

    //结婚证-修改
    @FormUrlEncoded
    @POST(UrlConfig.MARRY_IMG_MODIFY)
    Observable<String> getModifyMarryImgData(@FieldMap Map<String, String> map);

    //结婚证-获取
    @FormUrlEncoded
    @POST(UrlConfig.MARRY_IMG_GET)
    Observable<GetMarryImgBean> getMarryImgData(@FieldMap Map<String, String> map);

    //户口本-修改
    @FormUrlEncoded
    @POST(UrlConfig.ACCOUNT_IMG_MODIFY)
    Observable<String> getModifyHouseholdRegistrationBookImgData(@FieldMap Map<String, String> map);

    //户口本-获取
    @FormUrlEncoded
    @POST(UrlConfig.ACCOUNT_IMG_GET)
    Observable<GetHouseholdRegistrationBookBean> getGetHouseholdRegistrationBookImgData(@FieldMap Map<String, String> map);

    //房产证-修改
    @FormUrlEncoded
    @POST(UrlConfig.HOUSE_IMG_MODIFY)
    Observable<String> getModifyHouseImgData(@FieldMap Map<String, String> map);

    //房产证-获取
    @FormUrlEncoded
    @POST(UrlConfig.HOUSE_IMG_GET)
    Observable<GetHouseImgBean> getHouseImgData(@FieldMap Map<String, String> map);

    //抵押物-修改
    @FormUrlEncoded
    @POST(UrlConfig.COLLATEL_IMG_MODIFY)
    Observable<String> getModifyCollateralImgData(@FieldMap Map<String, String> map);

    //抵押物-获取
    @FormUrlEncoded
    @POST(UrlConfig.COLLATEL_IMG_GET)
    Observable<GetCollateralImgBean> getCollateralImgData(@FieldMap Map<String, String> map);

    //企业信息-修改
    @FormUrlEncoded
    @POST(UrlConfig.ENTERPRISE_IMG_MODIFY)
    Observable<String> getModifyEnterpriseImgData(@FieldMap Map<String, String> map);

    //企业信息-获取
    @FormUrlEncoded
    @POST(UrlConfig.ENTERPRISE_IMG_GET)
    Observable<GetCompanyInform> getEnterpriseImgData(@FieldMap Map<String, String> map);

    //信用报告-修改
    @FormUrlEncoded
    @POST(UrlConfig.CREDIT_IMG_MODIFY)
    Observable<String> getModifyCreditReportImgData(@FieldMap Map<String, String> map);

    //信用报告-获取
    @FormUrlEncoded
    @POST(UrlConfig.CREDIT_IMG_GET)
    Observable<GetCreditReportBean> getCreditReportImgData(@FieldMap Map<String, String> map);

    //银行流水-修改
    @FormUrlEncoded
    @POST(UrlConfig.BANK_IMG_MODIFY)
    Observable<String> getModifyBankImgData(@FieldMap Map<String, String> map);

    //银行流水-获取
    @FormUrlEncoded
    @POST(UrlConfig.BANK_IMG_GET)
    Observable<GetBankImgBean> getBankImgData(@FieldMap Map<String, String> map);

    //评估报告-修改
    @FormUrlEncoded
    @POST(UrlConfig.ASSESSMENT_IMG_MODIFY)
    Observable<String> getModifyAssessmentImgData(@FieldMap Map<String, String> map);

    //评估报告-获取
    @FormUrlEncoded
    @POST(UrlConfig.ASSESSMENT_IMG_GET)
    Observable<GetAssessmentImgBean> getAssessmentImgData(@FieldMap Map<String, String> map);

    //产调-修改
    @FormUrlEncoded
    @POST(UrlConfig.PRODUCTION_ADJUSTMENT_IMG)
    Observable<String> getProductionAdjustmentImgData(@FieldMap Map<String, String> map); //产调-修改

    //产调-获取
    @FormUrlEncoded
    @POST(UrlConfig.PRODUCTION_ADJUSTMENT_GET)
    Observable<String> getProductionAdjustmentDataList(@FieldMap Map<String, String> map);

    //补充材料-修改
    @FormUrlEncoded
    @POST(UrlConfig.ADD_SUPPLEMENTARY_MODIFY)
    Observable<String> getModifyAddSupplementaryImgData(@FieldMap Map<String, String> map);

    //补充材料-获取
    @FormUrlEncoded
    @POST(UrlConfig.ADD_SUPPLEMENTARY_GET)
    Observable<GetAddSupplementaryBean> getAddSupplementaryImgData(@FieldMap Map<String, String> map);
}
