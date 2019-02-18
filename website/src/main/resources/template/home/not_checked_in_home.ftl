<!DOCTYPE html>
<html lang="en">
<head>
     <#include "../commons/public.ftl" />

    <#import "/commons/seo.ftl" as seo >
    <@seo.seo></@seo.seo>

    <link rel="stylesheet" href="${resourcesPath}/resources/css/no-check.css">
</head>
<body>
<div class="box">
    <!-- 导航 -->
<#include "../commons/header.ftl" />

    <!-- content -->
    <div class="w1120 content flex-between">
        <!-- 侧边导航 -->
      <#include "../commons/left_not.ftl">
        <!-- 右边主体 -->
        <div class="content-body w940">
            <div class="account-top">
                <img src="${resourcesPath}/resources/img/dtx.png" alt="">
                <div class="account-text">
                    <#if userInfoDTO??>
                        <h3 class="colord4 font20">
                            ${userInfoDTO.userPhone? substring(0,3)}****${userInfoDTO.userPhone ? substring(7,11)}
                            ，欢迎您回家！&nbsp;<small class="color48 font14">【<a href="${basePath}/login/out"
                                                                                                   class="color48 font14">退出账户</a>】
                        </small>
                        </h3>
                    </#if>
                    <h5 class="color27 font14">您尚未办理入住，尚无房间信息！联系管家预约看房，入住乐璟，乐享生活。</h5>
                </div>
            </div>
             <#include "./appAd.ftl" >
            <div class="part1">
                <div class="part-top relative">
                    <h2 class="color27 text-center font26">推荐房源</h2>

                    <span class="order-btn font14" id="appoint_to_room">
                            <img src="${resourcesPath}/resources/img/z_yuyue.png" alt="">
                            预约看房
                        </span>

                </div>
                <div class="room-list ">
                    <ul class="flex-between">
                        <#if apiResponse??>
                            <#list apiResponse.bizResponse.appHouseDTOList as house>

                              <li class="relative">
                                  <a href="${basePath}/house/${house.id}" target="_blank" >
                                      <#if house.bannerPic?? && house.bannerPic?size gt 0 >
                                             <img src="${house.bannerPic[0]}" alt="" onerror="this.src='${resourcesPath}/resources/img/tu.png'">
                                                          <#else>
                                             <img alt="" src="${resourcesPath}/resources/img/tu.png">
                                      </#if>
                                      <div class="room-text flex-between color27">
                                          <span class="w-50 color27">${house.projectName}</span>
                                          <span class="w-50 text-right font18 color27"><b>${house.monthPrice}</b>
                                                                        <small class="font14">元/月起</small>
                                                                    </span>
                                          <span class="w-50 font12 color66">${house.simpleLocation}</span>
                                          <span class="w-50 text-right font12 color66">【${house.houseStyle}】${house.roomName}</span>
                                      </div>
                                      <div class="border-blue"></div>
                                  </a>
                              </li>
                            </#list>
                        </#if>
                    </ul>
                </div>

            </div>
        </div>
    </div>
    <!-- 侧边导航 -->
    <#include "../commons/aside.ftl">
    <!-- footer -->
    <#include "../commons/footer.ftl">
    <#include "../appointment/appoint_room.ftl">
</div>
<script src="${resourcesPath}/resources/js/index.js"></script>
</body>
<script>
    $(function () {
        $("#myhome").attr("class", "on");
        $("#appoint_to_room").click(function () {
            openModel();
            $("#appoint_room_model").show();
        });
    })


</script>

</html>