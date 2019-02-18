<!DOCTYPE html>
<html lang="en">
<head>
    <#include "/commons/public.ftl">

    <#import "/commons/seo.ftl" as seo >
    <@seo.seo></@seo.seo>

    <link rel="stylesheet" href="${resourcesPath}/resources/css/bill.css">

    <script type='text/javascript' src="${resourcesPath}/resources/js/qrcode.js"></script>

</head>
<body>
    <div class="box">
        <!-- 导航 -->
		<#include "../commons/header.ftl">
        <!-- content -->
        <div class="w1120 content flex-between">
            <!-- 侧边导航 -->
        
		<#include "../commons/left.ftl">
            <!-- 右边主体 -->
            <div class="content-body w940">
                <div class="small-nav color99 font12">我的生活 > 我的账单</div>
                <div class="body-list">
                    <ul class="list-top flex-between">
                    
                        <li class="flex-between">
                            <span class="color27 font14">账单类型：</span>
                            <select class="w114 font14 billTypeId" name="billTypeId">
                            </select>
                        </li>
                        <li class="flex-between">
                            <span class="color27 font14">账单周期：</span>
                            <input type="text" class="demo-input w114 font14 billStartTime" placeholder="年/月/日" id="test1" name="billStartTime"
                            autocomplete="off" >
                            <span class="colore1">——</span>
                            <input type="text" class="demo-input w114 font14 billEndTime" placeholder="年/月/日" id="test2" name="billEndTime"
                            autocomplete="off" >
                        </li>
                        <li class="flex-between">
                            <span class="color27 font14">账单状态：</span>
                            <select class="w114 font14 billPayStatus" name="billPayStatus">
                                <option value>全部</option>
                                <#assign billPayStatuss={"PAYMENT":"已支付","NO_PAYMENT":"未支付","PARTIAL_PAYMENT":"部分支付"}> 
                                <#assign keys1=billPayStatuss?keys>
                                <#list keys1 as key>
                                 <option value="${key}">${billPayStatuss[key]}</option>
                                </#list>
                            </select>
                        </li>
                         
                        <li class="text-right">
                            <button type="button" class="text-center search font14" onclick="search()">查询</button>
                        </li>
                    </ul>
                    <div class="order-list">
                        <h3 class="color27 font18">
                            账单列表
                            <small class="font12 color99">（注：仅供查询12个月内的账单）</small>
                        </h3>
                        <table>
                            <thead>
                                <tr>
                                    <th>账单编号</th>
                                    <th>账单类型</th>
                                    <th>账单金额</th>
                                    <th>账单周期</th>
                                    <th>应付时间</th>
                                    <th>账单状态</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody class="page-list">
                            
                            </tbody>
                        </table>
                        <div class="page">
                        </div>
                    </div>
                </div>
            </div>

        </div>


    <!-- 侧边导航 -->
   <#include "../commons/aside.ftl">
    <#include "../commons/footer.ftl">
   <#include "../appointment/appoint_room.ftl">
    </div>

    <!-- 账单详情 -->
    <div class="fix-box pay-box mybilldetail" >
    </div>


    <!-- 账单详情成功 -->
    <div class="fix-box pay-box1 pay-ok">
        <div class="model-box">
            <div class="model-top flex-between">
                <h3>账单支付</h3>
                <img src="${resourcesPath}/resources/img/cc.png" alt="" class="close pay-box1-close">
            </div>
            <div class="model-content ">
                <div class="w332">
                    <div class="dilogs text-center mb100">
                        <img src="${resourcesPath}/resources/img/zhuce.png" alt="" class="block-auto">
                        <h4 class="color27 font18">支付成功！</h4>
                        <p class="color99 font14">本次支付金额：<span id="pay-box1-span"></span>元！</p>
                    </div>
                    <div class="flex-around">
                        <button class="next" onclick="payOkLook()" >查看</button>
                        <button class="next pay-box1-close">关闭</button>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <!-- 重复提示 -->
    <div class="fix-box pay-box2 pay-mgs2">
        <div class="model-box">
            <div class="model-top flex-between">
                <h3>账单详情</h3>
                <img src="${resourcesPath}/resources/img/cc.png" alt="" class="pay-box2-colse">
            </div>
            <div class="model-content ">
              
                    <div class="dilogs text-center mb100">
                        <img src="${resourcesPath}/resources/img/chacha.png" alt="" class="block-auto">
                        <h4 class="color27 font18">该房间其他房客正在操作支付，请勿重复付款！</h4>

                    </div>
                    <div>

                        <button class="next pay-box2-colse">关闭</button>
                    </div>
                </div>
        </div>
    </div>
    <input type="hidden" id="billId" value="${billId!}"/>
    <input type="hidden" id="weixin-orderId" />
	<input type="hidden" id="zhifubao-orderId" />
	 <input type="hidden" id="mybillokid" />
    <script src="${resourcesPath}/resources/js/index.js"></script>
    <script>
    var timerOnOff = false;
	var clock;
	    //控制查询时间 过去12个月以内
	   	function getMinDate(){
	    	var nowdate = new Date();
	    	var year;
			var month;
			var days = "01";
			if(nowdate.getMonth()==11){//12月
				year = nowdate.getFullYear();
				month = "01";
			}else{
				year = nowdate.getFullYear()-1;
				month = nowdate.getMonth()+2;
			}
			return (year+"-"+month+"-"+days).toString();
	    };
	    //账单详情
	    function getMyBillDetail(billId,type,res){
	    	$("#mybillokid").val(null);
	    	$.HTTP.GetHTML($.HTTP.URL.query_bill_detail,{billId: billId},
                function (response) {
	    			$("#mybillokid").val(billId);
	    			$(".mybilldetail").html(response);
	    			$("#pay-code").hide();
	    			$(".mybilldetail").show();
	    			if(type && type=="qrcode"){
	    				$("#pay-code").show();
	    				createQrcode(res);
	    			}
                },function (err) {
	    			console.log(err);
                }
            )
	    };
	    
	    //生成收款二维码
	    function createQrcode(response){
	    	if(null != response && null != response.loginCreateOrderDTOS && response.loginCreateOrderDTOS.length>0 ){
        		for(var i = 0;i<response.loginCreateOrderDTOS.length;i++){
            		 var qrCode = response.loginCreateOrderDTOS[i].qrcode;
                   	 var orderId = response.loginCreateOrderDTOS[i].orderId;
                   	 var orderChannel = response.loginCreateOrderDTOS[i].orderChannel;
                   	 if(orderChannel=="ALIPAY"){
                   		$("#zhifubaoer").empty();
                   		$("#zhifubao-orderId").val(orderId);
                   		var qrcode = new QRCode('zhifubaoer', {
                            text: qrCode,
                            width: 200,
                            height: 200,
                            colorDark: '#000000',
                            colorLight: '#ffffff',
                            correctLevel: QRCode.CorrectLevel.H
                         });
                   	 }
                   	 if(orderChannel=="WEIXIN"){
                   		$("#weixiner").empty();
                   		$("#weixin-orderId").val(orderId);
                   		var qrcode = new QRCode('weixiner', {
                            text: qrCode,
                            width: 200,
                            height: 200,
                            colorDark: '#000000',
                            colorLight: '#ffffff',
                            correctLevel: QRCode.CorrectLevel.H
                         });
                   	 }
            	} 
        		timerOnOff = false;
        		clock = setInterval(checkOrderTimer,5000);
        	}
	    }
	    
	    //查看或支付
	    function look(billId,type){
	    	clock = null;
    		if(type=="look"){
        		getMyBillDetail(billId,null,null);
        	}else if(type=="pay"){ 
        		$.HTTP.Post($.HTTP.URL.crete_order_request,
                        {
                			buinessIds: billId ,
                			orderBuinessType: "BILL"
                        },
                        function (response) {
                        	getMyBillDetail(billId,"qrcode",response);
                        },
                        function (err) {
                        	if(err.statusCode == "90010076"){
                        		$(".pay-mgs2").show();
                        	}
                        }
                ) 
        	}
    	};
    	//关闭详情弹框
    	function closeDetail(){
    		if(clock){
    			cancelPay();
    		}else{
    			$(".mybilldetail").hide();
    			search();
    		}
   		   
    	};
    	
    	//取消支付
    	function cancelPay(){
    		clearInterval(clock);
  	    	var weiXinOrderId = $("#weixin-orderId").val();
	    	var zhiFuBaoOrderId = $("#zhifubao-orderId").val();
    		$.HTTP.Post($.HTTP.URL.cancel_recharge_order,
                    { orderIds: weiXinOrderId+","+zhiFuBaoOrderId },
            function (response) {
                if (response.orderIds && response.orderIds.length > 0) {
                	$(".mybilldetail").hide();
                    // 清空二维码元素
                	$("#zhifubaoer").empty();
                	$("#weixiner").empty();
                	search();
                } else {
                    alert("取消订单失败！");
                }
            }
   		 )
 		};
    	
    	//支付成功后查看
    	function payOkLook(){
    		var lookId = $("#mybillokid").val();
    		look(lookId,'look');
    		$(".pay-ok").hide();
    	}
    	//初始化
        $(function () {
            $("#my-bill-on").attr("class", "on");
        	getBillTypes();
        	lay('#version').html('-v' + laydate.v);
            //执行一个laydate实例
            var startDate = laydate.render({
                elem: '#test1',
                min: getMinDate(),
                btns: ['clear', 'confirm'],
                done: function (value, date) {
                    if (date && date.year) {
                        endDate.config.min = {
                            year: date.year,
                            month: date.month - 1,
                            date: date.date
                        };
                    }
                    else
                    {
                        var nowdate = new Date();
                        var year;
                        var month;
                        var days = "01";
                        if (nowdate.getMonth() == 11) {//12月
                            year = nowdate.getFullYear();
                            month = "01";
                        } else {
                            year = nowdate.getFullYear() - 1;
                            month = nowdate.getMonth() + 2;
                        }
                        endDate.config.min = {
                            year: year,
                            month: month,
                            date: days
                        };
                    }
                }
            });
            var endDate = laydate.render({
                elem: '#test2',
                min: getMinDate(),
                btns: ['clear', 'confirm'],
                done: function (value, date) {
                    if (date && date.year) {
                        startDate.config.max = {
                            year: date.year,
                            month: date.month - 1,
                            date: date.date
                        };
                    } else {
                        startDate.config.max = {
                            year: '2099',
                            month: '11',
                            date: '1'
                        };
                    }
                }
            });
            
            $(".pay-box2-colse").click(function () {
                $(".pay-mgs2").hide();
                search();
            });
            $(".pay-box1-close").click(function () {
            	search();
                $(".pay-ok").hide();
            });
            var billId_to = $("#billId").val();
            var requestParam={
            		billId:billId_to
            };
            $.PAGE.INIT($.HTTP.URL.query_bill_list_p,requestParam);
        });
    	
    	function search(){
    		var billTypeId = $(".billTypeId").val();
        	var billStartTime = $(".billStartTime").val();
        	var billEndTime = $(".billEndTime").val();
        	var billPayStatus = $(".billPayStatus").val();
            var config = {
            		billTypeId:billTypeId,
            		billStartTime:billStartTime,
            		billEndTime:billEndTime,
            		billPayStatus:billPayStatus
                        };
            $(document).trigger("p-param-map.page",config);
    	}
    	
    	
    	//检查支付状态
    	function checkOrderTimer(){
    	  if(timerOnOff){
    		  $(".mybilldetail").hide();
    		  var payAmount = $("#payAmount").val();
    		  $("#pay-box1-span").text(payAmount);
      		  $(".pay-ok").show();
    	      clearInterval(clock);
    	    }else{
    	    	var weiXinOrderId = $("#weixin-orderId").val();
    	    	var zhiFuBaoOrderId = $("#zhifubao-orderId").val();
	   	    	$.HTTP.Get($.HTTP.URL.query_recharge_order,{orderIds: weiXinOrderId+","+zhiFuBaoOrderId},
   	                   function (response) {
  	   	    			if(response && response.orderBatchQueryDTOS){
  	   	    				for(var i =0 ;i<response.orderBatchQueryDTOS.length;i++){
  	   	    					if(response.orderBatchQueryDTOS[i].orderStatus =="SUCCESS"){
  	   	    						timerOnOff = true;
  	   	    						break;
  	   	    					}
  	   	    				}
  	   	    			}
   	                   },function (err) {
   	   	    				console.log(err);
   	                   }
	   	         );
    	  }
    	}
    	
    	function getBillTypes(){
    		$('.billTypeId').empty();
    		$('.billTypeId').append("<option value=''>全部</option>");
    	 	$.HTTP.Get($.HTTP.URL.query_bill_types,{},
                   function (response) {
   	    			if(response && response.billTypes){
   	    				for(var i =0 ;i<response.billTypes.length;i++){
   	    					$('.billTypeId').append("<option value="+response.billTypes[i].billTypeId+">"+response.billTypes[i].billTypeName+"</option>");
   	    				}
   	    			}
                   },function (err) {
   	    				console.log(err);
                   }
	   	     );
    	}
    </script>
</body>

</html>