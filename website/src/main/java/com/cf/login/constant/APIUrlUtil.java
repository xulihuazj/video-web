package com.cf.login.constant;

public interface APIUrlUtil {

    /**
     * 视频列表
     */
    String GET_CLIENT_VIDEO_LIST = "/client/video/list/";

    /**
     * 视频详情
     */
    String GET_CLIENT_VIDEO_DETIAL = "/client/video/detail/";


    /**
     * 图片验证码
     */
    String GET_IMAGE_CODE = "/app/image/code/";

    /**
     * 维修保洁
     */
    String GET_REPAIR_LIST = "/client/repair/list";

    String GET_REPAIR_DETAIL = "/client/repair/detail";

    /**
     * 获取短信验证码(需要图形验证码)
     */
    String APP_SMS_MOBILE_CODE = "/app/sms/mobile/code/imgCode";

    /**
     * 用户注册
     */
    String CLIENT_USER_REGISTERED = "/client/user/registered";

    /**
     * 登录检查是否需要展示图形验证码
     */
    String CLIENT_USER_CODE_SHOW = "/client/user/code/show";

    /**
     * 乐璟PC登录
     */
    String CLIENT_USER_PC_LOGIN = "/client/user/pc/login";

    /**
     * 我的账单列表
     */
    String MY_BILL_LIST = "/my/bill/list";

    /**
     * 我的账单详情
     */
    String MY_BILL_DETAIL = "/my/bill/detail";

    /**
     * 账单类型
     */
    String BILL_TYPES = "/billtype/list/l";


    String GET_HOUSE_LIST = "/client/common/house";

    String GET_HOUSE_DETAIL = "/client/common/house/detail";

    /**
     * 投诉列表
     */
    String GET_COMPLAINTS_LIST = "/client/complaints/list";
    /**
     * 投诉详情
     */
    String GET_COMPLAINTS_DETAIL = "/client/complaints/detail";
    /**
     * 投诉操作日志
     */
    String GET_COMPLAINTS_DETAIL_LOG = "/client/complaints/log/list";

    /**
     * 我的房间
     */
    String GET_ROOM_RECORD_DETAIL = "/smart/room/record/detail";

    /**
     * 历史入住
     */
    String GET_ROOM_HISTORY_LIST = "/smart/room/history/list";

    /**
     * 无登录态查询项目与房型
     */
    String GET_ROOM_PROJECTS = "/room/projects";

    /**
     * 充值记录
     */
    String GET_SMART_PAYMENT_BILL_RECORD_LIST = "/smart/payment/record/list";

    /**
     * 客户端-新消息提醒
     */
    String GET_CLIENT_MESSAGE_ALERT = "/smart/client/message/alert";

    /**
     * 查看消息
     */
    String POST_CLIENT_MESSAGE_READ = "/smart/client/message/read";

    /**
     * 预约看房
     */
    String POST_HOME_APPOINT_ROOM = "/appoint/room";

    /**
     * 用户下单:乐璟官网:账单和充值下单，账单下单返回支付宝和微信两种收款码，充值根据选择的渠道生成码
     */
    String POST_QR_CODE_ORDER = "/order/qrCode/create";

    /**
     * 订单批量取消
     */
    String POST_CANCEL_ORDER = "/order/cancel";

    /**
     * 订单状态查询
     */
    String GET_QUERY_ORDER = "/order/query";

    /**
     * 获取短信验证码
     */
    String POST_PMS_SMS_CODE = "/sms/user/mobile/code";

    /**
     * 手机号换绑
     */
    String POST_PMS_APP_USER_CHANGE_PHONE = "/client/user/bind/account";

    /**
     * 换绑验证码 code
     */
    String POST_PMS_APP_USER_CHANGE_PHONE_CODE = "/client/user/pc/valid/smsCode";

    /**
     * 重置密码
     */
    String POST_PMS_APP_USER_PWD_RESET = "/client/user/pc/password/reset";

    /**
     * 检查手机验证码是否正确
     */
    String APP_SMS_CODE_CHECK = "/app/sms/code/check";

    /**
     * 验证码重置密码
     */
    String CLIENT_USER_PASSWORD_RESET = "/client/user/password/reset";

}

