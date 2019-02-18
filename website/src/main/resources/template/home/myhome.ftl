<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../commons/public.ftl" />

    <#import "/commons/seo.ftl" as seo >
    <@seo.seo></@seo.seo>

    <link rel="stylesheet" href="${resourcesPath}/resources/css/check.css?v=${resourcesVersion}">
    <script type='text/javascript' src="${resourcesPath}/resources/js/qrcode.js"></script>

</head>
<body>
<div class="box">
    <!-- 导航 -->
    <#include "../commons/header.ftl" />

    <!-- content -->
    <div class="w1120 content flex-between">
        <!-- 侧边导航 -->
        <#include "../commons/left.ftl">

        <!-- 右边主体 -->
        <div class="content-body w940">
            <div class="account-top">
                <img src="${resourcesPath}/resources/img/dtx.png" alt="">
                <div class="account-text">
                    <#if userInfoDTO??>
                         <h3 class="colord4 font20"> ${userInfoDTO.userPhone? substring(0,3)}****${userInfoDTO.userPhone ? substring(7,11)}
                             ，欢迎您回家！&nbsp;
                             <small class="color48 font14">【<a href="${basePath}/login/out"
                                                               class="color48 font14">退出账户</a>】
                             </small>
                         </h3>
                    </#if>
                        <#if roomRecordResponse??>
                               <div class="flex-between">
                                   <h5 class="color27 font14">${roomRecordResponse.bizResponse.roomName?replace('>','-')}</h5>
                                   <p class="color99 font14 text-right">入住时段：
                                       ${roomRecordResponse.bizResponse.startTime?string("yyyy.MM.dd")}
                                       -${roomRecordResponse.bizResponse.endTime?string("yyyy.MM.dd")}</p>
                               </div>
                        </#if>

                </div>
            </div>

            <#include "./appAd.ftl" >

            <div class="flex-between height-center">
                <div class="img-text flex">
                    <img src="${resourcesPath}/resources/img/ht.png" alt="">
                    <h4 class="color27 font16">我的合同</h4>
                </div>
                      <#if roomRecordResponse??>
                    <a
                            class="color27 font16 text-right">《乐璟生活社区租房合约<span>${roomRecordResponse.bizResponse.contractNum}</span>》</a>
                      </#if>
            </div>
            <div class="message">
                <div class="img-text flex">
                    <img src="${resourcesPath}/resources/img/xx.png" alt="">
                    <h4 class="color27 font16">新消息提醒</h4>
                </div>
                <div class="message-list w760">
                    <ul>
                                     <#if clientMessageAlertResponse?? && clientMessageAlertResponse.bizResponse.clientMessages?? && clientMessageAlertResponse.bizResponse.clientMessages?size gt 0 >
                                         <#list clientMessageAlertResponse.bizResponse.clientMessages as resultMessage >
                                             <#if resultMessage?? && resultMessage.bizType == "LEASE_BILL" >
                                                <li class="font14 color27">您有一笔
                                                    <span class="colord4">【<a
                                                            onclick="serviceLeaseBillConvert(${resultMessage.bizId})"
                                                            class="colord4 font14">${resultMessage.clientMessage.billType}</a>】</span>
                                                    <b class="color27 font14">账单</b>待支付，账单金额：<span>${resultMessage.clientMessage.billAmount}</span>元，请及时支付，逾期将产生违约费用。
                                                </li>
                                             </#if>
                                             <#if resultMessage?? && resultMessage.bizType == "REPAIR" >
                                                <li class="font14 color27"
                                                    onclick="serviceRepairConvert(${resultMessage.bizId})">您申请的<b
                                                        class="color27 font14">维修服务</b>
                                                    <span class="colord4">【<a
                                                            class="colord4 font14">${resultMessage.serviceNum}</a>】</span>
                                                    保洁员已受理，预计将于【<a
                                                            class="colord4 font14">${resultMessage.clientMessage.planFixTime}</a>】前往房间提供服务。
                                                </li>
                                             </#if>
                                             <#if resultMessage?? && resultMessage.bizType == "CLEAN" >
                                                <li class="font14 color27"
                                                    onclick="serviceCleanConvert(${resultMessage.bizId})">您申请的<b
                                                        class="color27 font14">保洁服务</b>
                                                    <span class="colord4">【<a
                                                            class="colord4 font14">${resultMessage.serviceNum}</a>】</span>
                                                    保洁员已受理，预计将于【<a
                                                            class="colord4 font14">${resultMessage.clientMessage.planFixTime}</a>】前往房间提供服务。
                                                </li>
                                             </#if>
                                             <#if resultMessage?? && resultMessage.bizType == "COMPLAINTS" >
                                                <li class="font14 color27"
                                                    onclick="serviceComplaintsConvert(${resultMessage.bizId})"
                                                >您提交的<b class="color27 font14">投诉</b>
                                                    <span class="colord4">【<a
                                                            class="colord4 font14">${resultMessage.serviceNum}</a>】</span>
                                                    ，管家已处理完毕，如若对处理结果不满意，可再次提交投诉，感谢您宝贵的入住建议。
                                                </li>
                                             </#if>
                                         </#list>
                                     </#if>
                    </ul>
                          <#if clientMessageAlertResponse??&&clientMessageAlertResponse.bizResponse.total == 0 >
                              <p class="color27 font14">暂无新消息提醒~</p>
                          </#if>
                </div>
            </div>
            <div class="money">
                <div class="flex-between height-center">
                    <div class="img-text flex">
                        <img src="${resourcesPath}/resources/img/ht.png" alt="">
                        <h4 class="color27 font14">房间水电费余额（元）:
                                <#if roomRecordResponse?? && roomRecordResponse.bizResponse.energyConsume??>
                                    <span class="colord4 font26">${roomRecordResponse.bizResponse.energyConsume.prepayTotal}</span>
                                <#else>
                                    <span class="colord4 font26">0.00</span>
                                </#if>
                        </h4>
                    </div>
                    <div class="money-btn">
                        <button class="text-center font14" id="recharge_money">充值</button>
                        <a class="colord4 font12 text-right" id="recharge_record">查看充值记录</a>
                    </div>
                </div>
                <div class="flex-between money-month w760">
                    <div class="w-50 flex">
                        <img src="${resourcesPath}/resources/img/sd.png" alt="">
                                <#if roomRecordResponse?? && roomRecordResponse.bizResponse.isInstallWater == "INSTALLED">
                                      <p class="font14 color66">
                                          当月水费（元）:
                                          <span class="colord4 font18">${roomRecordResponse.bizResponse.energyConsume.waterConsumeMonth}</span>
                                      </p>
                                <#else>
                                      <!-- 无数据时 -->
                                     <span class="color66 font14">无智能水表，或智能水表
                                     未联网暂时无法展示实时能耗</span>
                                </#if>
                    </div>
                    <div class="w-50 flex">
                        <img src="${resourcesPath}/resources/img/shuif.png" alt="">
                              <#if roomRecordResponse?? && roomRecordResponse.bizResponse.isInstallEle == "INSTALLED">
                                 <p class="font14 color66">
                                     当月电费（元）:
                                     <span class="colord4 font18">${roomRecordResponse.bizResponse.energyConsume.eleConsumeMonth}</span>
                                 </p>
                              <#else>
                                    <!-- 无数据时 -->
                                    <span class="color66 font14">无智能电表，或智能电表
                                     未联网暂时无法展示实时能耗</span>
                              </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 侧边导航 -->
    <#include "../commons/aside.ftl">
    <!-- footer -->
    <#include "../commons/footer.ftl">
</div>

<!-- 水电费充值-选择充值金额 -->
<div class="fix-box pay-box" id="recharge_money_model">
    <div class="model-box">
        <div class="model-top flex-between">
            <h3>水电费预充值</h3>
            <img src="${resourcesPath}/resources/img/cc.png" alt="" class="close" id="close_recharge_money_model">
        </div>
        <div class="model-content">
            <div class="flex-2">
                <p class="color27 font18">充值金额：</p>
                <ul class="flex money-btn">
                    <li class="on">
                        <span class="color27 font18 text-center" id="recharge_amount_100">100元</span>
                    </li>
                    <li>
                        <span class="color27 font18 text-center" id="recharge_amount_150">150元</span>
                    </li>
                    <li>
                        <span class="color27 font18 text-center" id="recharge_amount_200">200元</span>
                    </li>
                    <li>
                        <span class="color27 font18 text-center" id="recharge_amount_300">300元</span>
                    </li>
                    <li>
                        <span class="color27 font18 text-center" id="recharge_amount_500">500元</span>
                    </li>
                </ul>
            </div>
            <div class="flex-2">
                <p class="color27 font18">选择支付方式：</p>
                <div class="flex w480 ml20">
                    <label for="wx" class="radio flex w-50" id="payment_channel_weixin">
                        <input type="radio" name="sex" checked="checked" id="wx"/>
                        <span class="radio-on"><i class="radios-o"></i></span>
                        <img src="${resourcesPath}/resources/img/weix.png" alt="">
                        <span class="color99 font14">微信支付</span>
                    </label>
                    <label for="zf" class="radio flex w-50" id="payment_channel_alipay">
                        <input type="radio" name="sex" id="zf"/>
                        <span class="radio-on"><i class="radios-o"></i></span>
                        <img src="${resourcesPath}/resources/img/zhifb.png" alt="">
                        <span class="color99 font14">支付宝支付</span>
                    </label>
                </div>
            </div>
            <div class="mt148">
                <button class="next" id="order_recharge">下一步</button>
            </div>
        </div>
    </div>
</div>

<!-- 水电费预充值-扫码 -->
<div class="fix-box pay-box1" id="recharge_qrcode_model">
    <div class="model-box">
        <div class="model-top flex-between">
            <h3>水电费预充值</h3>
        </div>
        <div class="model-content">
            <div class="w640 flex-between">
                <div class="color27 font18 w-50 bold-x">充值金额：<span class="font30 colord4 bold-x"
                                                                   id="order_amount"></span>元
                </div>
                <div class="color27 font18 w-50 bold-x">支付方式：
                    <img src="${resourcesPath}/resources/img/weix.png" alt="" id="pay_wx_img">
                    <img src="${resourcesPath}/resources/img/zhifb.png" alt="" id="pay_alipay_img">
                    <small class="color99 font14" id="order_channel"></small>
                </div>
            </div>
            <div class="w640 mrg">
                <h4 class="color27 font18">请扫描下方二维码进行支付：</h4>
            </div>
            <div class="borders" id="qr_code">
            </div>
            <div class="mt44">
                <button class="next" onclick="cancelRechargeOrder()">取消</button>
            </div>

        </div>
    </div>
</div>

<!-- 水电费预充值成功 -->
<div class="fix-box pay-box2" id="order_recharge_success_model">
    <div class="model-box">
        <div class="model-top flex-between">
            <h3>水电费预充值</h3>
        </div>
        <div class="model-content">
            <div class="dilogs text-center ">
                <img src="${resourcesPath}/resources/img/zhuce.png" alt="" class="block-auto">
                <h4 class="color27 font18">支付成功！</h4>
                <p class="color99 font14">本次充值金额：<span id="success_order_amount"></span>元！</p>
            </div>
            <div>
                <button class="next" onclick="closeOrderRechargeSuccessModel()">关闭</button>
            </div>

        </div>
    </div>
</div>

<!-- 水电费充值记录 -->
<div class="fix-box pay-box3" id="payment_record_list_model">
    <div class="model-box">
        <div class="model-top flex-between">
            <h3>水电费充值记录</h3>
        </div>
        <div class="model-content">
            <div class="table-list" style="max-height: 252px;overflow-y: auto">
                <table class="table text-left">
                    <thead class="bold-x color27">
                    <tr>
                        <th>充值时间</th>
                        <th>充值金额（元）</th>
                        <th>支付方式</th>
                        <th>交易流水号</th>
                    </tr>
                    </thead>

                    <tbody class="color66" id="recordsBody">
                    <#--<#if paymentBillRecordListResponse?? && paymentBillRecordListResponse.paymentBillRecords?? &&  paymentBillRecordListResponse.paymentBillRecords?size gt 0 >-->
                    <#--<#list paymentBillRecordListResponse.paymentBillRecords as paymentBillRecord >-->
                    <#--<tr>-->
                    <#--<td>${paymentBillRecord.payTime}</td>-->
                    <#--<td>${paymentBillRecord.paymentAmount}</td>-->
                    <#--<td>${paymentBillRecord.paymentType}</td>-->
                    <#--<td>-->
                    <#--<a href="" class="colord4">${paymentBillRecord.tradeNo}</a>-->
                    <#--</td>-->
                    <#--</tr>-->
                    <#--</#list>-->
                    <#--</#if>-->
                    </tbody>
                </table>
            </div>
            <div class="no-record" id="no_recharge_record" >
                <p class="color66 font16 text-center">暂无充值记录~</p>
            </div>
            <div>
                <button class="next" id="close_payment_record_list_model">关闭</button>
            </div>
        </div>
    </div>
</div>

<script src="${resourcesPath}/resources/js/index.js"></script>
<script>

    // 充值金额
    var orderAmount = 100;
    // 充值方式
    var paymentChannel = "WEIXINWEB";
    var qrCode = '';
    var orderChannel = '';
    var orderId = '';
    var inter;
    var orderResult = 'waiting';

    $(function () {
        $("#myhome").attr("class", "on");
        //开始充值
        $("#recharge_money").click(function () {
            $("#recharge_money_model").show();
        });
        $("#close_recharge_money_model").click(function () {
            $("#recharge_money_model").hide();
        });

        // 查看充值记录
        $("#recharge_record").click(function () {
            // 请求接口渲染模态框
            $.HTTP.Get($.HTTP.URL.payment_bill_record_list,
                    {},
                    function (response) {
                        if (response && response.paymentRecords) {
                            if (response.total > 0) {
                                $("#no_recharge_record").hide();
                                var result = response.paymentRecords;
                                var fdafdas = "";
                                for (var i = 0; i < result.length; i++) {
                                    var time = timestampToTime(result[i].payTime);
                                    var aaa = "<tr>\n" +
                                            " <td>" + time + "</td>\n" +
                                            " <td>" + result[i].rechargeAmount + "</td>\n" +
                                            " <td>" + result[i].payChannel + "</td>\n" +
                                            " <td>\n" +
                                            " <a class=\"colord4\">" + result[i].tradeNo + "</a>\n" +
                                            "</td>\n" +
                                            "</tr>";
                                    fdafdas = fdafdas + aaa;
                                }
                                $("#recordsBody").append(fdafdas);
                            }
                        }else{
                            $("#no_recharge_record").show();
                        }
                    }
            )
            $("#payment_record_list_model").show();
        });

        var timestampToTime = function (timestamp) {
            var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
            Y = date.getFullYear() + '-';
            M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
            D = date.getDate() + ' ';
            h = date.getHours() + ':';
            m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
            s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
            return Y + M + D + h + m + s;
        }

        // 关闭充值记录模态框
        $("#close_payment_record_list_model").click(function () {
            $("#payment_record_list_model").hide();
            $("#recordsBody").text("");
        });

        $(".money-btn li span").click(function () {
            $(this).parent().siblings().removeClass();
            $(this).parent().addClass('on');
        });
        // 充值金额选择
        $("#recharge_amount_100").click(function () {
            orderAmount = 100;
        });
        $("#recharge_amount_150").click(function () {
            orderAmount = 150;
        });
        $("#recharge_amount_200").click(function () {
            orderAmount = 200;
        });
        $("#recharge_amount_300").click(function () {
            orderAmount = 300;
        });
        $("#recharge_amount_500").click(function () {
            orderAmount = 500;
        });
        $("#payment_channel_weixin").click(function () {
            paymentChannel = "WEIXINWEB";
        });
        $("#payment_channel_alipay").click(function () {
            paymentChannel = "ALIPAYQRCODE";
        });

        //点击下一步，开始下订单
        $("#order_recharge").click(
                function () {
                    if (orderAmount == 0 || paymentChannel == '') {
                        alert(orderAmount);
                        alert(paymentChannel)
                        return;
                    } else {
                        $.HTTP.Post($.HTTP.URL.crete_order_request,
                                {
                                    orderAmount: orderAmount,
                                    paymentChannel: paymentChannel,
                                    orderBuinessType: "RECHANGE"
                                },
                                function (response) {
                                    if (paymentChannel == "WEIXINWEB") {
                                        $("#pay_wx_img").show();
                                        $("#pay_alipay_img").hide();
                                    } else {
                                        $("#pay_alipay_img").show();
                                        $("#pay_wx_img").hide();
                                    }
                                    qrCode = response.loginCreateOrderDTOS[0].qrcode;
                                    orderId = response.loginCreateOrderDTOS[0].orderId;
                                    orderChannel = response.loginCreateOrderDTOS[0].orderChannel;
                                    $("#recharge_money_model").hide();
                                    $("#order_amount").text(orderAmount);
                                    var qrcode = new QRCode('qr_code', {
                                        text: qrCode,
                                        width: 200,
                                        height: 200,
                                        colorDark: '#000000',
                                        colorLight: '#ffffff',
                                        correctLevel: QRCode.CorrectLevel.H
                                    });
                                    $("#qr_code").value = qrcode;
                                    if (orderChannel == "ALIPAY") {
                                        $("#order_channel").text("支付宝");
                                    } else {
                                        $("#order_channel").text("微信");
                                    }
                                    $("#recharge_qrcode_model").show();
                                    interOrderQuery(orderId);
                                }
                        )
                    }
                }
        )
    })

    // 取消订单
    var cancelRechargeOrder = function () {
        $.HTTP.Post($.HTTP.URL.cancel_recharge_order,
                {
                    orderIds: orderId
                },
                function (response) {
                    if (response.orderIds && response.orderIds.length > 0) {
                        // alert("取消订单成功！");
                        $("#recharge_qrcode_model").hide();
                        // 清空二维码元素
                        $('#qr_code').html("");
                        // 清楚定时器
                        clearInterval(inter);
                    } else {
                        // alert("取消订单失败！");
                        $("#recharge_qrcode_model").hide();
                    }
                }
        )
    }

    // 查询订单
    var queryRechargeOrder = function (data) {
        $.HTTP.Get($.HTTP.URL.query_recharge_order,
                {
                    orderIds: data
                },
                function (response) {
                    console.log(response)
                    if (response && response.orderBatchQueryDTOS) {
                        var result = response.orderBatchQueryDTOS;
                        console.log(result);
                        if (result && result[0].orderStatus == "SUCCESS") {
                            orderResult = "success";
                            $("#recharge_qrcode_model").hide();
                            $("#order_recharge_success_model").show();
                        }
                    }
                }
        )
    }

    // 支付成功关闭模态框
    var closeOrderRechargeSuccessModel = function () {
        $("#order_recharge_success_model").hide();
        location.href = $.baseData.basePath + "/home/room/detail";
    }

    // 定时查询清单
    var interOrderQuery = function (data) {
        // 设置定时器
        inter = setInterval(function () {
            var result = queryRechargeOrder(data);
            // 支付成功
            if (orderResult == "success") {
                // 支付成功模态框
                $("#success_order_amount").text(orderAmount);
                $("#order_recharge_success_model").show();
                // 清楚定时器
                clearInterval(inter);
            }
        }, 1500, 60 * 15);
    }

    // 定位列表
    var serviceRepairConvert = function (data) {
        // 读消息
        readMessage(data);
        location.href = $.baseData.basePath + "/repair/list" + "?" + "repairId=" + data;
    }
    var serviceCleanConvert = function (data) {
        readMessage(data);
        location.href = $.baseData.basePath + "/repair/list" + "?" + "repairId=" + data;
    }
    var serviceComplaintsConvert = function (data) {
        readMessage(data);
        location.href = $.baseData.basePath + "/complaints/list" + "?" + "complaintsId=" + data;
    }
    var serviceLeaseBillConvert = function (data) {
        readMessage(data);
        location.href = $.baseData.basePath + "/my/bill/list" + "?" + "billId=" + data;
    }

    var readMessage = function (value) {
        // 读消息
        $.HTTP.Get($.HTTP.URL.client_message_read,
                {
                    clientMessageId: value
                },
                function (response) {
                    console.log(response)
                }
        )
    }

</script>
</body>

</html>