<#if response?? && response.bizResponse?? && response.bizResponse.complaintsInfoDTOS??>
    <#list response.bizResponse.complaintsInfoDTOS as complaint >
    <tr>
        <td>${complaint.serviceNum!}</td>
        <td> ${complaint.createTime?string("yyyy.MM.dd HH:mm:ss")} </td>
        <td>
    <#if complaint.content?length gt 32>
               ${complaint.content?substring(0,32)}...
        <#else>
             ${complaint.content!}
        </#if>
         </td>
        <td>
            <#if complaint.complaintsStatus.code=='WAITING'>
                <span class="colord4 font14">未处理</span>
            <#elseif complaint.complaintsStatus.code=='PROCESSING'>
                <span class="color48 font14">处理中</span>
            <#elseif complaint.complaintsStatus.code=='FIXED'>
                <span class="color27 font14">处理完成</span>
            </#if>

        </td>
        <td>
            <button class="look btn font12" onclick="detail(${complaint.complaintsId!},'open')">查看</button>
        </td>
    </tr>
    </#list>
<#--<#else>
    <img src="${resourcesPath}/resources/img/TSS.png" alt="" class="mb40 block-auto">
    <p class="color66 font16 text-center">您未提交任何投诉信息</p>-->
</#if>
<#include "../commons/page/page.ftl" >