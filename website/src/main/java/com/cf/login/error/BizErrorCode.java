package com.cf.login.error;

/**
 * 业务异常
 * <p>
 * 规定使用边界，避免push代码存在过多冲突
 * <p>
 * 规定业务码开头使用人
 */
public enum BizErrorCode implements ErrorCode {
    /**
     * 3位系统码+2位业务码+3位异常码
     * 1.api默认系统码100
     */
    REQUEST_PARAM_EMPTY_ERROR("10010000", "请求参数错误或缺失"),

    NO_PROJECT_AUTHORITY_ERROR("10010001", "无此项目操作权限"),

    DEPT_HAS_CHILD("10020001", "此部门包含下级部门，不允许删除"),
    DEPT_HAS_USERS("10020002", "此部门内已有员工，请先将员工移出部门后再删除"),

    POSITION_HAS_USER("10030001", "此岗位已经有员工在使用，无法删除，请先调整员工的岗位"),
    POSITION_NAME_REPEAT("10030002", "同一公司下的岗位名称不可以重复"),

    USER_MAIL_ADDRESS_ERROR("10040001", "此用户邮箱地址异常"),
    USER_MAIL_SEND_ERROR("10040002", "发送邮件失败"),

    COMPANY_PROJECT_EMPTY("10050001", "当前用户所属公司下没有项目"),



    ROOM_BASE_EDIT_ERROR("20010001", "房源添加/编辑类型错误"),

    ROOM_BASE_AUTHORITY_ERROR("20010002", "无此项目操作权限"),

    ROOM_HAS_EXIST_ERROR("20010003", "此房间已存在"),

    ROOM_NOT_EXIST_ERROR("20010004", "此房间不存在"),

    ROOM_DELETE_NOT_SUPPORT("20010005", "不支持删除"),

    ROOM_EDIT_NOT_SUPPORT("20010006", "不支持操作"),

    LEASE_RELET_NOT_SUPPORT("20010007", "不支持续租"),

    LEASE_EXIST_NOT_ERROR("20010008", "租约不存在或无权限查询"),

    LEASE_EXCHANGE_BILL_ERROR("20010010", "原租约存在未收款账单，暂不支持换房"),

    LEASE_EXCHANGE_AMOUNT_ERROR("20010012", "原租约已收款金额大于新租约待收款金额总额，暂不支持换房。"),

    LEASE_RENTER_NOT_ERROR("20010013", "入住人不存在"),

    LEASE_RENTER_CHECK_IN("20010013", "该入住人已经办理过入住,不能重复办理"),

    LEASE_EXIST_COST_ERROR("20010014", "存在未结费用"),

    ROOM_NOT_SUPPORT_ERROR("20010015","房间不支持租售"),

    ROOM_BED_NUM_NOT_SAME("20010016","原房床位数量大于新房床位数量，暂不支持此操作"),

    ROOM_ADD_FLOOR_ERROR("20010017","当前楼层大于总楼层"),

    LEASE_RELTE_TIME_ERROR("20010018","开始日期不能大于结束日期"),

    DEVICE_BRAND_EXIST_ERROR("20010019","设备品牌不存在"),

    DEVICE_LOCK_RESET_ERROR("20010020","不允许重置密码"),

    PAY_TYPE_SUPPORT_ERROR("20010021", "支付方式不支持"),

    LEASE_CANCEL_BED_BILL_ERROR("20010022", "租约存在未收款账单，暂不支持退床"),

    REFUND_EXTRA_NOT_SUPPORT("20010023","额外退款记录不存在"),

    ASSET_GODOWN_NOT_SUPPORT("20010024","入库单异常"),

    ASSET_NOT_SUPPORT("20010025","资产异常"),






    PARAMETER_CHECK_ERROR("30010001", "参数校验异常"),

    LOGIN_ERROR_USER_NULL("30010002", "用户不存在"),

    LOGIN_ERROR_PASSWORD("30010003", "手机号或密码错误"),

    LOGIN_ERROR_USER_STATUS("30010004", "您的账号已经被禁用，如有疑问请联系技术部"),

    DISCOUNT_NULL_ERROR("30010005","用户尚未设置可审核的折扣比例"),

    IMG_CODE_ERROR("30010006","图片验证码错误"),

    SMS_REPEAT_CODE("30010007","一分钟内不能重复获取验证码"),

    SMS_TYPE_ERROR("30010008","未知的短信业务类型"),

    UNIQUETAG_ERROR("30010009","UNIQUETAG必须为32位长度字符串"),

    SMS_CODE_ERROR("30010010","短信验证码错误"),

    SMS_CODE_NOT_SERIVCE("30010011","短信验证码错误次数过多"),

    YUNDING_API_ERROR("30010012","云丁接口请求出现业务异常"),


    LEASE_BILL_PAY_EMPTY("40010001", "账单信息为空"),

    THROWLEASE_ERROR("40010002", "退房失败"),

    ONE_MINUTE_SEND_ONE("40010003", "同一个接收手机号1分钟最多发送1条短信"),

    NO_EXCEED_THREE("40010004", "同样内容的短信发送数量每天不得超过3条"),

    SMS_SEND_ERROR("40010005", "短信发送失败"),

    CREATE_COMPANY_ERROR("40010006", "新增房源失败"),

    CREATE_COMPANY_BANK_ERROR("40010007", "新增企业银行失败"),

    WITHDRAW_EXCEEDING("40010008", "金额不足，无法提现"),

    CREATE_COMPANY_BALANCE_ERROR("40010009", "新增企业账户失败"),

    CREATE_COMPANY_BALANCE_LOG_ERROR("40010010", "新增企业账户金额变动日志失败"),

    MONEY_TYPE_ERROR("40010011", "请输入正确的金额格式"),

    COMPANY_SIGN_A_CONTRACT("40010012", "企业已签约，企业名称不能修改"),

    SMS_SENT_NO_ALLOW("40010013", "短信今天已发送，或还未到催租日期"),

    COMPANY_BALANCE_IS_NOT_EFFECTIVE("40010014", "企业账户不存在或非有效"),

    COMPANY_IS_NOT_EXIST("40010015", "企业不存在"),

    COMPANY_NAME_IS_EXIST("40010016", "企业名称已存在"),

    NO_ROOM_THROW("40010017", "无可退房间"),

    COMPANY_SIGN_EXISTING_BALANCE("40010018", "企业已签约或账户存在余额，不能修改状态"),

    DEPOSIT_NOT_COLLECTED("40010019", "存在未收款的账单，不能退房"),

    SELECTED_OUTSIDE("40010020", "所选日期已在租约账期之外"),




    DOOR_LOCK_RESET_ERROR("50010001","重置密码时间间隔为10分钟，请稍后再试"),




    LEASEROOMBILL_BILLAMOUNT_ERROR("60010002", "金额不符合要求"),

    LEASEROOMBILL_ADD_CRETIMAGE_ERROR("60020001", "凭证上传失败"),

    LEASEROOMBILL_ADD_ERROR("60020002", "添加账单失败"),
    
    LEASEROOMBILL_ADD_LEASEID_ERROR("60020003", "添加账单失败,无效合同"),

    LEASEROOMBILL_BILL_RRVERSE_ERROR("60030001", "只能冲账未收款账单"),

    LEASEROOMBILL_PAYLOG_RRVERSE_ERROR("60030002", "只能冲未核销收款"),

    LEASEROOMBILL_BILL_RRVERSE_NOADD_ERROR("60030003", "只能冲账手工账单"),

    LEASEROOMBILL_BILL_RRVERSE_DEDUCTION_ERROR("60030004", "抵扣流水不能冲账"),

    LEASEROOMBILL_PAY_RRVERSE_ERROR("60030005", "流水已冲账"),

    LEASEROOMBILL_BILL_RRVERSE_ERROR1("60030005", "账单已冲账"),

    LEASEROOMBILL_SPLIT_BILLAMOUNT_ERROR("60040001", "拆分金额必需小于账单金额"),

    LEASEROOMBILL_SPLIT_PAY_ERROR("60040002", "已支付账单不能拆分"),

    LEASEROOMBILL_SPLIT_ERROR("60040003", "账单已拆分"),

    LEASEROOMBILL_GATHERING_PAYAMOUNT_ERROR("60050001", "收款金额差异"),

    LEASEROOMBILL_GATHERING_BALANCE_ERROR("60050002", "账户余额不足"),

    LEASEROOMBILL_GATHERING_MAX_ERROR("60050003", "输入金额大于未收金额"),

    LEASEROOMBILL_GATHERING_ERROR("60050004", "收款失败"),
    
    LEASEROOMBILL_GATHERING_LEASEID_ERROR("60050005", "收款失败,无效合同"),
    
    LEASEROOMBILL_GATHERING_BOOKING_ERROR("60050006", "收款失败,该合同无预订单可抵扣"),
    
    LEASEROOMBILL_GATHERING_PAYTYPEID_ERROR("60050007", "收款失败,无效支付方式"),
    
    LEASEROOMBILL_GATHERING_SUMAMOUNT_ERROR("60050008", "收款失败,合计收款金额0,无需收款"),
    
    LEASEROOMBILL_GATHERING_BILL_ERROR("60050009", "收款失败,无收款账单"),
    
    LEASEROOMBILL_GATHERING_AMOUNTMAX_ERROR("60050010", "收款失败,收款金额大于账单未收金额"),
    
    LEASEROOMBILL_DEPOSIT_DEDUCTION_ERROR("60050011", "押金抵扣失败,缺失有效参数"),
    
    LEASEROOMBILL_DEPOSIT_DEDUCTION_AMOUNT_ERROR("60050012", "押金抵扣失败,输入金额格式异常"),
    
    LEASEROOMBILL_DEPOSIT_DEDUCTION_AMOUNTMAX_ERROR("60050013", "押金抵扣失败,需要抵扣的金额大于押金"),
    
    LEASEROOMBILL_DEPOSIT_DEDUCTION_PAYAMOUNTMAX_ERROR("60050014", "押金抵扣失败,支付金额大于账单未收金额"),
    
    LEASEROOMBILL_GATHERING_BILLTYPENAME_ERROR("60050015", "收款失败,无效款项类型"),
    
    
    
    CONTRACT_UPLOAD_ERROR("60060001", "上传合同失败"),

    CONTRACT_UPLOAD_TYPE_ERROR("60060002", "上传合同失败,请上传PDF格式"),

    LEASE_BOOKING_ERROR("60070001", "预定单或租约不存在"),

    LEASE_BOOKING_CANCEL_ERROR("60070002", "取消预定单失败"),

    LEASE_BOOKING_BILL_ERROR("60070003", "预定金账单不存在"),
    
    LEASE_BILL_DETAIL_ERROR("60080001", "获取账单详情异常"),
    
    LEASE_BILL_DETAIL_LIST_ERROR("60080002", "获取账单财务明细异常"),
    
    PAY_LOG_DETAIL_ERROR("60090001", "获取支付流水详情异常"),
    
    PAY_LOG_AUDIT_ERROR("60090002", "获取支付流水核销异常"),
    
    PAY_LOG_DETAIL_NO_EXIST_ERROR("60090003", "无效支付流水"),
    

    LEASEROOMBILL_CHECKOUTROOM_ERROR("60090011", "退房退款异常"),
    
    LEASEROOMBILL_CHECKOUTROOM_NO_DEPOSIT_ERROR("60090012", "退押金异常,无押金单可退"),
    
    LEASEROOMBILL_CHECKOUTROOM_AMOUNTMAX_ERROR("60090012", "退押金异常,指定金额大于可退押金"),
    
    
    
    FINANCE_PRINT_ERROR("60090021", "缺少打印参数"),
    
    FINANCE_PRINT_LEASE_ERROR("60090022", "无效合同"),
    
    FINANCE_PRINT_BILL_ERROR("60090023", "无效账单"),
    
    FINANCE_PRINT_PAYLOG_ERROR("60090024", "无效支付流水"),
    
    
    

    LEASE_ROOM_HAS_OCCUPIED_ERROR("70010001", "该房间在此时间段内已经存在租约"),

    LEASE_CUSTOM_RENT_LIST_EMPTY("70010002", "灵活租约不能为空"),

    LEASE_BASE_EDIT_ERROR("70010003", "租约添加/编辑类型错误"),

    LEASE_CREATE_ERROR("70010005", "创建租约失败"),

    LEASE_NOT_REJECT_CANT_EDIT("70010006", "该租约不是驳回状态，不能修改"),

    LEASE_IS_NOT_ORIGIN_CANT_EDIT("70010007", "该租约不是原生租约，不能修改"),

    LEASE_ROOM_ID_REPEATED("70010008", "该租约中存在多条同一个房间租约"),

    LEASE_RENTER_INFO_NO_SEX("70010009", "租约入住人中有人未选择性别"),

    LEASE_ALLOCATE_ROOM_NOT_ENOUGH("70010010", "可分配房间不够"),

    ROOM_TYPE_INFO_EMPTY("70010011", "请填写房型信息"),

    NEED_DOWN_PAYMENT_CANT_EDIT("70010012", "不能修改收取预定金的租约"),

    DOWN_PAYMENT_AMOUNT_TOO_LARGE("70010013", "预定金金额大于租金的30%"),

    COMPANY_IS_INVALID("70010014", "该企业已停用"),

    PARENT_LEASE_NOT_EXIST("70010015", "父租约不存在"),

    BOOKING_LEASE_NOT_EXIST("70010016", "预订单不存在"),

    LEASE_RENTER_PHONE_REPEATED("70010017", "入住人手机号重复"),

    LEASE_HAS_RELETED("70010018", "该租约已经续租过"),

    BOOKING_NOT_ADUIT_SUCCESS_CANT_TO_LEASE("70010017", "预订单未通过审核，无法进行转租约操作"),

    LEASE_BED_HAS_OCCUPIED_ERROR("70020001", "该床位已经存在入住人，不能入住"),

    RENTER_ID_NOT_EXIST("70020002", "入住人id不存在"),

    RENTER_IS_EXISTED("70020003", "入住人已经存在"),

    RENTER_HAS_BED_DEPOSIT("70020004", "入住人存在押金，无法取消"),

    RENTER_STATUS_IS_NOT_CHECKIN("70020005", "入住人为非入住状态，不能取消"),

    LEASE_NOT_START_CANT_CHECKIN("70020006", "租约未开始，无法添加入住人"),

    ROOM_BED_NOT_EFFECTIVE("70020007", "床位无效，不能添加入住人"),

    LEASE_ROOM_IS_INVALID_CANT_ADD_RENTER("70020008", "该房间租约已经退房或者失效，无法添加入住人"),

    LEASE_ROOM_IS_CHECKIN_CHECKOUT_AUDITING_CANT_ADD_RENTER("70020009", "该房间租约处于入住、退房待审核中，无法添加入住人"),

    LEASE_IS_NOT_INEFFECTIVE("70020010", "该租约非待生效状态，不能进行生效操作"),

    EQUIPMENT_DETAILS_IS_NULL("70020011", "水电表设备读数未填写"),

    CASH_BALANCE_NOT_ENOUGH("70030001", "现金余额不足，无法进行结存操作"),

    CASH_BALANCE_INVOICEID_IS_EXIST("70030002", "现金结存的银行单据号已经存在"),

    SETTLE_DAY_PASSWORD_ERROR("70040001", "日审密码错误"),

    SETTLE_DAY_TWICE_IN_ONE_DAY("70040002", "一天不能日审两次"),

    REPORT_TEMPLATE_NOT_FOUND("70040003", "报表不存在"),

    GENERATE_REPORT_ERROR("70040004", "获取报表出错"),

    SETTLE_NIGHT_HAS_EXISTED("70050001", "夜审记录已经存在"),

    SETTLE_NIGHT_ERROR("70050002", "夜审出错"),

    SETTLE_NIGHT_KEY_ERROR("70050003", "夜审key错误"),

    REFUND_NOT_EXIST("70060001", "退款详情不存在"),

    REFUND_CANT_PAY_MONEY("70060002", "退款非未支付状态，不能够支付"),

    LEASE_ROOM_IS_FULL("10060001", "此房间已住满，不能再办理入住，请重新选择房间"),






    DEPART_IS_EMPTY("90010001", "此部门不存在"),

    DEPART_EXIST("90010002", "此部门已经存在"),

    POSITION_IS_EMPTY("90010003", "此用户没有设置岗位，无法使用任何功能"),

    ACCOUNT_EXIST("90010004", "账号已经注册"),

    PROJECT_IS_EMPTY("90010005", "房源项目不存在"),

    POSITION_NOT_IN_DEPART("90010006", "当前岗位不属于此部门"),

    PASSWORD_CHECK_ERROR("90010007", "密码设置错误：必须包含数字,字母,下划线且必须6-18位"),

    OPERATE_NOT_ALLOWED("90010008", "不支持的操作"),

    POSITION_EXIST("90010009", "此岗位已经存在"),

    DISCOUNT_NOT_EXIST("90010010", "优惠信息不存在"),

    IMAGE_UPLOAD_ERROR("90010011", "上传图片失败"),

    OSS_URL_EMPTY("90010012", "请设置阿里云域名配置地址"),

    MODULE_IS_EMPTY("90010013", "模块不存在"),

    NO_PRIVILEGE("90010016", "请联系管理员配置访问权限"),

    MODULE_ID_EMPTY("90010017", "当前模块不存在"),

    DEPART_PARENT_ID_ERROR("90010018", "当前部门的上级部门不能是本身"),

    HOTEL_PROJECT_EMPTY("90010019", "房源项目不能为空"),

    POSITION_TYPE_IN_EMPEY("90010020", "岗位类型不能为空"),

    LOG_TYPE_IS_EMPTY("90010021", "日志类型不能为空"),

    BRAND_IS_EMPTY("90010022", "智能品牌信息未配置"),

    DEVICE_NOT_EXIST("90010023", "当前设备不存在"),

    YUNDING_ERROR("90010024", "云丁业务处理异常"),

    USER_PROJECT_IS_EMPTY("90010025", "当前用户未选择所属项目"),

    WATER_ELECTRIC_FEE_IS_EMPTY("90010026", "请选择需要修改的水电价格"),

    WATER_ELECTRIC_IS_EMPTY("90010027", "水电价格不存在"),

    FEE_TYPE_IS_EMPTY("90010028", "费用梯度值不能为空"),

    FEE_IS_ERROR("90010029", "请输入有效的金额"),

    CALLBACK_NOT_EXIST("90010030", "当前回调不存在"),

    PASSWORD_ERROR("90010031", "密码设置错误：必须包含数字,字母,字符两种及以上且必须6-20位"),

    PASSWORD_IS_EMPTY("90010032", "该账号尚未设置密码"),

    PASSWORD_REPEAT("90010033", "新密码不能与前三次密码相同"),

    SMS_CODE_EXPRIED("90010034", "验证码过期"),

    PHONE_IS_ERROR("90010035", "手机号格式不正确"),

    REPAIR_IMAGE_ERROR("90010036", "图片不能为空或多于5张"),

    REPAIR_CONTENT_EMPTY("90010037", "内容不能为空"),

    REPAIR_IS_EMPTY("90010038", "报修不存在"),

    REPAIR_UPDATE_ERROR("90010039", "处理中或者处理完成的不能修改"),

    NICK_IS_TOO_LONG("90010040", "可使用中英文(区分大小写),数字(不可纯数字),-,_,昵称只能在4-20个字符内"),

    BILL_PAYMENT_SUCCESS("90010041", "账单已支付成功，不可重复支付"),

    ORDER_QUERY_ERROR("90010042","订单查询失败"),

    USER_NO_LEASE("90010043","用户无有效租约，不可充值"),

    CREATE_ORDER_FAIL("90010044","创建订单失败"),

    ORDER_ERROR("90010045","第三方下单失败"),

    ORDER_IS_EMPTY("90010046","订单不存在"),

    RECHANGE_IS_EMPTY("90010047","充值记录不存在"),

    BILL_TYPE_IS_EMPTY("90010048","账单类型不存在"),

    REPAIR_SPECIES_TYPE_IS_EMPTY("90010049", "维修项目不能为空"),

    MERCHANT_IS_EMPTY("90010050", "当前项目所属的商户不存在"),

    ORDER_AMOUNT_ERROR("90010051", "订单金额不正确"),

    ACCOUNT_ALREADY_BIND("90010052", "微信账号已被绑定"),

    RECHANGE_FORMAT_ERROR("90010053","金额格式错误"),

    REPAIR_CONTENT_TOO_LONG("90010054","报修内容不能超过200字"),

    SMS_CODE_IS_EMPTY("90010055","验证码不能为空"),

    USER_PASSWORD_ERROR("90010056","密码必须6-20,并且数字，字母(含大小写),符号(_),至少含有两种"),

    LEASE_SUBJECT_IS_EMPTY("90010057","签约主体不能为空"),

    LEASE_SOURCE_IS_EMPTY("90010058","签约渠道不能为空"),

    LEASE_ROOM_IS_EMPTY("90010059","当前租约无签约房间"),

    NOT_SUPPORT_OPERATE("90010060","不支持的状态流转"),

    NOT_SUPPORT_FILE("90010061","不支持的文件类型"),

    MEMBER_PHONE_IS_EMPTY("90010063","会员手机号不能为空"),

    PAYMENT_CONFIG_IS_EMPTY("90010062","支付配置信息不存在"),

    PAYMENT_USER_INFO_ERROR("90010063","获取第三方用户信息失败"),

    UNKNOW_PAYMENT_TYPE("90010064","未知的支付方式"),

    ORDER_REFUND_ERROR("90010065","订单退款失败"),

    SELLER_BALANCE_NOT_ENOUG("90010066","余额不够无法发起退款"),

    TRADE_EXCEPTION("90010067","第三方支付交易异常，请稍后再试"),

    VAILD_SIGN_ERROR("90010068","验签失败"),

    REFUND_QUERY_ERROR("90010069","退款查询失败"),

    REFUND_IS_EMPTY("90010070","退款订单不存在"),

    CONTENT_ERROR("90010071","投诉内容只能10-500字以内"),

    ANNEX_ERROR("90010072","附件数量不能超过5张或大小不能超过5M"),

    COMPLAINTS_IS_EMPTY("90010073","投诉信息不存在"),

    ANNEX_EMPTY("90010074","附件不能为空"),

    BILL_PROJECT_ERROR("90010044", "不同项目账单不能一起支付"),

    BILL_LEASE_ERROR("90010045", "不同租约账单不能一起支付"),

   PROJECT_COMPANY_IS_EMPTY("90010046", "当前项目所属公司不存在"),




    ;

    BizErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
