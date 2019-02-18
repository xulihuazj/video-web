<link rel="stylesheet" href="${resourcesPath}/resources/css/bill.css?v=${resourcesVersion!}">
    <!-- 服务详情 -->
        <div class="model-box">
            <div class="model-top flex-between">
                <h3>服务详情</h3>
                <img src="${resourcesPath}/resources/img/cc.png" alt="" class="close" onclick="closeDiv()">
            </div>
            <div class="model-content padd-bottom">
                <div class="ys-auto">
                    <div class="flex-between wrap mb20">
                        <p class="color27 font14 bold-s w-50">服务申请单号：${detail.serviceNum!} </p>
                        <p class="color27 font14 bold-s w-50">服务类型：${detail.repairType.desc}</p>
                        <p class="color27 font14 bold-s w-50"> 提交时间：${detail.createTime?string("yyyy-MM-dd HH:mm:ss")}</p>
                        <p class="color27  bold-s w-50 ">
                            <span class="font14 w46 new-line mr10">内容：${detail.repairContent}</span>
                        </p>
                    </div>

                    <div class="mb20 flex-start">
                        <span class="color27 font14 bold-s">相关图片:</span>
                        <ul class="flex-start wrap ml20 adjunct">
                            <#if detail.repairImages??>
                                <#list detail.repairImages as image>
                                    <li>
                                        <img src="${image}" alt="">
                                    </li>
                                </#list>
                            </#if>
                        </ul>
                    </div>
                    <div class="flex-between wrap mb20">
                        <p class="color27 font14 bold-s  w-50">预计服务时间：
                            <#if detail.planFixTime??>
                                ${detail.planFixTime?string("yyyy-MM-dd HH:mm")}
                            </#if>
                        </p>
                         <#if detail.repairStatus.code == 'WAITING'>
                            <p class="color27 font14 bold-s w-50">状态：<span class="font14 colord4">${detail.repairStatus.desc}</span></p>
                         <#elseif detail.repairStatus.code == 'INVALID'>
                            <p class="color27 font14 bold-s w-50">状态：<span class="font14 color48">${detail.repairStatus.desc}</span></p>
                         <#elseif detail.repairStatus.code == 'FIXED'>
                            <p class="color27 font14 bold-s w-50">状态：<span class="font14 color49">${detail.repairStatus.desc}</span></p>
                         <#elseif detail.repairStatus.code == 'EFFECTIVE'>
                            <p class="color27 font14 bold-s w-50">状态：<span class="font14 colord4">${detail.repairStatus.desc}</span></p>
                         </#if>
                    </div>
                    <p class="color27  bold-s flex-start">
                        <span class="font14 ">备注：
                        <#if detail.repairStatus.code == 'INVALID'>
                            <span class="new-line font14">${detail.rejectReason!}</span>
                        <#else>
                            <span class="new-line font14">${detail.remark!}</span>
                        </#if>

                    </p>
                </div>
                <div class="mt60">
                    <button class="next close" onclick="closeDiv()">关闭</button>
                </div>
            </div>
        </div>
