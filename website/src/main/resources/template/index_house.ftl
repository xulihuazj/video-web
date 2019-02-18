<#global basePath=springMacroRequestContext.contextPath>
<#global resourcesPath=Application.resourcesPath>
<#global resourcesVersion=Application.resourcesVersion>

<#if response?? && response.bizResponse?? && response.bizResponse.appHouseDTOList?? >

<div class="room-list ">
    <ul class="flex-between">

            <#list response.bizResponse.appHouseDTOList as house >
                <li class="relative">
                    <a href="${basePath}/house/${house.id}" target="_blank">

                        <#if house.bannerPic?? && house.bannerPic?size gt 0 >
                         <img src="${house.bannerPic[0]}" alt="" onerror="this.src='${resourcesPath}/resources/img/tu.png'">
                        <#else>
                         <img alt="" src="${resourcesPath}/resources/img/tu.png">
                        </#if>

                        <div class="room-text flex-between color27">
                            <span class="w-50 ">${house.projectName}</span>
                            <span class="w-50 text-right font18 ">
                            <b class="font20">${house.monthPrice}</b>
                            <small class="font14">元/月起</small>
                         </span>
                            <span class="w-50 font12 ">${house.simpleLocation}</span>
                            <span class="w-50 text-right font12 ">【${house.houseStyle}】${house.roomName}</span>
                        </div>
                    </a>
                </li>
            </#list>

    </ul>
</div>
</#if>