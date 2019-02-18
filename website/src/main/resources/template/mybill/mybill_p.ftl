
<#if apiResponse.myBillList?? && (apiResponse.myBillList?size>0)>
 <#list apiResponse.myBillList as bill>
    <tr>
        <td>${bill.billNo}</td>
        <td>${bill.billTypeName}</td>
        <td>${bill.billAmount}</td>
        <td>
        ${(bill.billStartTime?string("yyyy-MM-dd"))!}
        <#if bill.billEndTime?? || bill.billStartTime??>-</#if>
        ${(bill.billEndTime?string("yyyy-MM-dd"))!}
        </td>
        <td>
        <#if bill.planPayTime??> </#if>
        ${(bill.planPayTime?string("yyyy-MM-dd"))!}
       
        </td>
        <td>
        <#if bill.billStatus == "PAYMENT">
        <span class="color27 font14">已支付</span>
        <#elseif bill.billStatus == "NO_PAYMENT">
         <span class="color48 font14">未支付</span>
        <#else>
         <span class="colord4 font14">部分支付</span>
        </#if>
        </td>
        <td>
        <#if bill.billStatus == "PAYMENT">
         <button class="look btn font12" onclick="look(${bill.billId},'look')" >查看</button>
      <#else>
       <button class="pay btn font12" onclick="look(${bill.billId},'pay')" >支付</button>
      </#if>
      </td>
  </tr>
  </#list>
  </#if>
<#include "../commons/page/page.ftl" >