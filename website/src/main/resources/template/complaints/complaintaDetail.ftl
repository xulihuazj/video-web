<!-- 投诉详情 -->
<link rel="stylesheet" href="${resourcesPath}/resources/css/bill.css?v=${resourcesVersion!}">
    <div class="model-box">
        <div class="model-top flex-between">
            <h3>投诉详情</h3>
            <img src="${resourcesPath}/resources/img/cc.png" alt="" class="close" onclick="detail(null,'close')">
        </div>
        <div class="model-content padd-bottom">
            <div class="ys-auto">
                <div class="flex-between mb20">
                    <p class="color27 font14 bold-s w-50">投诉单号：${detailResponse.bizResponse.serviceNum!}</p>
                    <p class="color27 font14 bold-s w-50">提交时间：${detailResponse.bizResponse.createTime?string("yyyy.MM.dd HH:mm:ss")}</p>
                </div>
                <p class="color27 font14 bold-s mb20"> 投诉内容：${detailResponse.bizResponse.content!}</p>
                     <div class="mb20 flex-start">
                         <span class="color27 font14 bold-s">相关附件:</span>
                         <#if detailResponse.bizResponse.annexDTOS??>
                               <ul class="flex-start wrap ml20 adjunct">
                                 <#list detailResponse.bizResponse.annexDTOS as image>
                                     <li>
                                         <img src="${image.annexUrl}" alt="">
                                     </li>
                                 </#list>
                             </ul>
                         </#if>
                     </div>
                <div class="flex-between mb20">
                    <p class="color27 font14 bold-s  w-50">最后处理时间：
                        <#if detailResponse.bizResponse.solveTime??>
                        ${detailResponse.bizResponse.solveTime?string("yyyy.MM.dd HH:mm:ss")}
                        </#if>
                    </p>
                    <p class="color27 font14 bold-s  w-50">最后处理人员：
                    <#if detailResponse.bizResponse.solveTime??>
                        工作人员
                    </#if>
                    </p>
                </div>
                <p class="color27 font14 bold-s mb20">处理状态：
                <#if detailResponse.bizResponse.complaintsStatus.code=='WAITING'>
                    <span class="colord4 font14">未处理</span>
                <#elseif detailResponse.bizResponse.complaintsStatus.code=='PROCESSING'>
                    <span class="color48 font14">处理中</span>
                <#elseif detailResponse.bizResponse.complaintsStatus.code=='FIXED'>
                    <span class="color27 font14">处理完成</span>
                </#if>
                </p>
                <p class="color27 font14 bold-s mb20">处理结果：</p>
            <#if logResponse?? && logResponse.bizResponse?? && logResponse.bizResponse.complaintsLogInfoDTOS??>

                    <#list logResponse.bizResponse.complaintsLogInfoDTOS as log >
                        <div class="company-list mb20">
                        <div class="flex-between">
                            <p class="color27  bold-s mb14">
                                <span class="font14">
                                <#if log.operateUserType=='RENTER'>
                                    租客
                                <#elseif log.operateUserType == 'SYSTEM'>
                                    工作人员
                                </#if>
                                </span>
                                <span class="ml5 font14">${log.createTime?string("yyyy.MM.dd HH:mm:ss")}</span>
                                <#--<span class="ml5 font14">12:06 </span>-->
                            </p>
                                <#if log.logType=='WAITING'>
                                    <p class="color48 font14 bold-s text-right">未处理</p>
                                <#elseif log.logType == 'PROCESSING'>
                                    <p class="color48 font14 bold-s text-right">处理中</p>
                                <#elseif log.logType == 'FIXED'>
                                    <p class="color27 font14 bold-s text-right"">处理完成</p>
                                <#elseif log.logType == 'COMPLAINTS'>
                                    <p class="colord4 font14 bold-s text-right">追加投诉</p>
                                </#if>


                        </div>
                        <p class="color66 font14 mb14">${(log.logContent)!}</p>
                        <ul class="flex-start wrap ml20 adjunct1">
                            <#if log.annexDTOS??>
                                <#list log.annexDTOS as image>
                                    <li>
                                        <img src="${image.annexUrl}" alt="">
                                    </li>
                                </#list>
                            </#if>
                        </ul>
                    </div>
                    </#list>
                </#if>

            </div>
            <div class="mt60">
                <button class="next" onclick="detail(null,'close')">关闭</button>
            </div>
        </div>
    </div>