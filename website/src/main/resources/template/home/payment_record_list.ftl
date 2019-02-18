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

                    <tbody class="color66">
                        <#if paymentBillRecordListResponse?? && paymentBillRecordListResponse.paymentBillRecords?? &&  paymentBillRecordListResponse.paymentBillRecords?size gt 0 >
                        <#list paymentBillRecordListResponse.paymentBillRecords as paymentBillRecord >
                            <tr>
                                <td>${paymentBillRecord.payTime}</td>
                                <td>${paymentBillRecord.paymentAmount}</td>
                                <td>${paymentBillRecord.paymentType}</td>
                                <td>
                                    <a href="" class="colord4">${paymentBillRecord.tradeNo}</a>
                                </td>
                            </tr>
                        </#list>
                        </#if>
                    </tbody>
                </table>
            </div>
            <div>
                <button class="next" id="close_payment_record_list_model">关闭</button>
            </div>
        </div>
    </div>
</div>