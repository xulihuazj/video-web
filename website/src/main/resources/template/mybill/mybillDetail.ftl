<!-- 账单详情 -->
<#if apiRresponse?? && apiRresponse.bizResponse?? && apiRresponse.bizResponse.myBill??>
<#assign mybill = apiRresponse.bizResponse.myBill> 
	<div class="model-box">
		<div class="model-top flex-between">
			<h3>账单详情</h3>
			<img src="${resourcesPath}/resources/img/cc.png" alt="" class="close" onclick="closeDetail()">
		</div>
		<div class="model-content">
			<div class="w630">
				<ul class="flex-between">
					<li class="color27 font14 bold-s w-50">账单编号：${mybill.billNo!}</li>
					<li class="color27 font14 bold-s w-50">账单类型：${mybill.billTypeName!}</li>
					<li class="color27 font14 bold-s w-50">账单金额：
					<b class="bold-x colord4">${mybill.billAmount!}</b>元
					</li>
					<li class="color27 font14 bold-s w-50">账单周期：${(mybill.billStartTime?string("yyyy.MM.dd"))!}-${(mybill.billEndTime?string("yyyy.MM.dd"))!}</li>
					<li class="color27 font14 bold-s w-50">应付时间：${(mybill.planPayTime?string("yyyy.MM.dd"))!}</li>
					<li class="color27 font14 bold-s w-50">所属合同：${mybill.contractNum!}</li>
					<li class="color27 font14 bold-s w-50">所属房间：${mybill.roomName!}</li>
					<li class="color27 font14 bold-s w-50">账单状态：
					<#if mybill?? && mybill.billStatus?? && (mybill.billStatus == "PAYMENT")>
						${mybill.billStatusName!}
					<#else>
						<span class="color48 font14">${mybill.billStatusName!}</span>
					</#if>
					
					</li>
				</ul>
			</div>
			<div class="table-list">
				<h3 class="color27 font18 mb20">
					财务明细 <small class="font12 color99">（共<span id="payLogSize">${((mybill.billpayLogList![])?size)}</span>条）
					</small>
				</h3>
				<table class="table text-left">
					<thead class="bold-x color27">
						<tr>
							<th>交易时间</th>
							<th>财务类型</th>
							<th>具体类型</th>
							<th>支付金额</th>
							<th>相关支付流水或凭证</th>
						</tr>
					</thead>

					<tbody class="color66" id="payLogList">
						<#if mybill.billpayLogList?? && (mybill.billpayLogList?size>0)>
							<#list mybill.billpayLogList as payLog>
								<tr>
									<td>${(payLog.payTime?string("yyyy.MM.dd HH:mm:ss"))!}</td>
									<td>${payLog.billType!}</td>
									<td>${payLog.payType!}</td>
									<td>${payLog.payAmount!0}</td>
									<td>${payLog.sourceTypeName!}<span class='colord4'>${payLog.sourceNo!}</span></td>
								</tr>
							</#list>
						<#else>
							<tr><td></td><td></td><td></td><td></td><td></td></tr>
						</#if>
					</tbody>
				</table>
			</div>
			<div id='pay-code'>
				<h3 class="color27 font18 mb20">
					扫码支付 <span class="font14 color27 ml20">应付金额：<i
						class="colord4 font14 pay_amount">${mybill.payAmount!0} </i>元</i></span>
				</h3>
				<div class="coder-img flex-around">
					<div class="text-center">
						<div class="borders " id="weixiner"></div>
						<p class="color99 font14 mt20">
							<img src="${resourcesPath}/resources/img/weix.png" alt="">
							使用微信扫码付款
						</p>
					</div>
					<div class="text-center">
						<div class="borders " id="zhifubaoer"></div>
						<p class="color99 font14 mt20">
							<img src="${resourcesPath}/resources/img/zhifb.png" alt="">
							使用支付宝扫码付款
						</p>
					</div>
				</div>
			</div>
			<div class="mt80">
				<#if mybill?? && mybill.billStatus?? && (mybill.billStatus == "PAYMENT")>
					<button class="next close" onclick="closeDetail()">关闭</button>
				</#if>
				
			</div>

		</div>
	</div>
<input type="hidden" value="${mybill.payAmount!0}" id="payAmount">
</#if>
