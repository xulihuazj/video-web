
<#if response.bizResponse?? && response.bizResponse.clientRepairInfoDTOS??>
    <#list response.bizResponse.clientRepairInfoDTOS as repair >
        <tr>
            <td>${repair.serviceNum!}</td>
            <td>${repair.repairType.desc}</td>
            <td>${repair.createTime?string("yyyy.MM.dd HH:mm:ss")}</td>
            <td>
                <#if repair.repairContent?length gt 11>
                     ${repair.repairContent?substring(0,10)}...
                <#else>
                     ${repair.repairContent}
                </#if>
            </td>
            <td>
                <#if repair.planFixTime?? >
                    ${repair.planFixTime?string("yyyy.MM.dd HH:mm")}
                </#if>
            </td>
            <#if repair.repairStatus.code == 'WAITING'>
                <td>
                    <span class="colord4 font14">${repair.repairStatus.desc}</span>
                </td>
            <#elseif repair.repairStatus.code == 'INVALID'>
                <td>
                    <span class="color48 font14">${repair.repairStatus.desc}</span>
                </td>
            <#elseif repair.repairStatus.code == 'FIXED'>
                <td>
                    <span class="color49 font14">${repair.repairStatus.desc}</span>
                </td>
            <#elseif repair.repairStatus.code == 'EFFECTIVE'>
                <td>
                    <span class="colord4 font14">${repair.repairStatus.desc}</span>
                </td>
            </#if>
            <td>
                <button class="look btn font12" id="check" onclick="checkDetail(${repair.repairId})">查看</button>
            </td>
        </tr>
    </#list>
</#if>

<#include "../commons/page/page.ftl" >







