
$.HTTP = {};

$.HTTP.URL = {
    REPAIR: "/repair/list/p",
    REPAIR_DETAIL: "/repair/detail",
    HOUSELIST: "/house",
    /*  投诉详情 */
    COMPLAINTS_DETAIL: "/client/complaints/detail",
    /*   投诉日志  */
    COMPLAINTS_LOG: "/client/complaints/log/list",

    REPAIR: "/repair/list/p",
    REPAIR_DETAIL: "/repair/detail",
    COMPLAINTS_DETAIL: "/complaints/detail",

    // web预付账户充值记录
    payment_bill_record_list: "/home/payment/record/list",
    // web预付账户充值下单请求
    crete_order_request: "/order/payment/recharge",
    // web预付账户充值取消
    cancel_recharge_order:"/order/recharge/cancel",
    // web预付账户订单查询
    query_recharge_order:"/order/recharge/query",

    // 6张推荐房源
    query_room_list: "/house",
    // 预约看房
    home_appoint_room: "/home/appoint/room",

    REPAIR: "/repair/list/p",
    REPAIR_DETAIL: "/repair/detail",

    // web预付账户充值记录
    payment_bill_record_list: "/home/payment/record/list",

    // web预付账户充值下单请求
    crete_order_request: "/order/payment/recharge",

    // web预付账户充值取消
    cancel_recharge_order:"/order/recharge/cancel",

    // web预付账户订单查询
    query_recharge_order:"/order/recharge/query",

    // 6张推荐房源
    query_room_list: "/house",
    /*  投诉详情 */
    COMPLAINTS_DETAIL: "/complaints/detail",

    /*  投诉列表分页 */
    COMPLAINTS_LIST_PAGE: "/complaints/list/page",

    // 预约看房
    home_appoint_room: "/home/appoint/room",

    // 项目与房型
    home_projects: "/home/projects",

    client_message_read :"/home/client/message/read",

    //修改密码
    MODIFY_PASSWORD:"/user/pwd",
    //获取验证码
    GET_SMS_CODE:"/user/code",
    //换绑验证码验证
    VALID_SMS_CODE:"/user/validcode",
    //换绑手机号
    CHANGE_PHONE:"/user/account",

	// 我的账单列表
    query_bill_list:"/my/bill/list",
    
    // 我的账单列表分页
    query_bill_list_p:"/my/bill/list_p",
    
	// 我的账单详情
    query_bill_detail:"/my/bill/detail",
    
    // 账单类型列表
    query_bill_types:"/my/bill/billtype"
}

$.HTTP.Ajax = function (fun, dataType, path, data, success, error) {

    var type = "GET";
    if (fun.toLowerCase() == "post") {
        type = "POST";
    }

    // var token = $.STORAGE.COOKIE.Get($.STORAGE.KEY.TOKEN), headers = {};
    // if (token) {
    //     headers.Authorization = token;
    // }

    var headers = {};

    $.ajax({
        url: $.baseData.basePath + path,
        type: type,
        dateType: dataType || "json",
        // contentType: "application/json",
        headers: headers,
        data: data,
        success: function (data) {

            if (dataType == "json") {
                if (data && data.statusCode == "SUCCESS") {
                    if (success) {
                        success(data.bizResponse);
                    }
                } else {

                    if (data.statusCode == 50010016) {
                        location.href = $.baseData.basePath + '/login';
                    } else {
                       // $.COMMON.Msg(data.message);

                        if (error) {
                            error(data);
                        }
                    }
                }
            } else {
                success(data);
            }

        },
        error: function (err) {

            if (err.status == 403) {
                location.href = $.baseData.basePath + "/login?backurl=" + encodeURIComponent(location.pathname);
            } else {
                // $.COMMON.Msg(data.message);

                if (error) {
                    error(data);
                }
            }
        }
    });
}


$.HTTP.Get = function (path, data, success, error) {
    $.HTTP.Ajax("GET", "json", path, data, success, error)
}

$.HTTP.Post = function (path, data, success, error) {
    $.HTTP.Ajax("POST", "json", path, data, success, error)
}

$.HTTP.GetHTML = function (path, data, success, error) {
    $.HTTP.Ajax("GET", "html", path, data, success, error)
}

function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}

/**************************************时间格式化处理************************************/
$.dateFtt = function (fmt,date)
{ //author: meizz
	if(null == date){
		return null;
	}
	date = new Date(date);
  var o = {
    "M+" : date.getMonth()+1,                 //月份
    "d+" : date.getDate(),                    //日
    "h+" : date.getHours(),                   //小时
    "m+" : date.getMinutes(),                 //分
    "s+" : date.getSeconds(),                 //秒
    "q+" : Math.floor((date.getMonth()+3)/3), //季度
    "S"  : date.getMilliseconds()             //毫秒
  };
  if(/(y+)/.test(fmt))
    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
  for(var k in o)
    if(new RegExp("("+ k +")").test(fmt))
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
  return fmt;
}