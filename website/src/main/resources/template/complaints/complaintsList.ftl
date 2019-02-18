<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../commons/public.ftl">

    <#import "/commons/seo.ftl" as seo >
    <@seo.seo></@seo.seo>

    <link rel="stylesheet" href="${resourcesPath}/resources/css/bill.css?v=${resourcesVersion!}">

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
                <div class="small-nav color99 font12">我的生活 > 我的投诉</div>


                 <#include "../home/appAd.ftl" >


                <div class="body-list">
                    <#-- 用于存放投诉ID -->
                        <input type="hidden"  id="complaintsId" value="${complaintsId!}">
                        <ul class="list-top flex-between">
                            <li class="flex-between">
                                <span class="color27 font14">提交时间：</span>
                                <input type="text" class="demo-input w114 font14" readonly="readonly" autocomplete="off" placeholder="年/月/日" id="startTime" name="startTime" value="${(request.startTime)!}">
                                <span class="colore1">——</span>
                                <div id="div-end">
                                    <input type="text" class="demo-input w114 font14" autocomplete="off"   readonly="readonly" placeholder="年/月/日" id="endTime" name="endTime" value="${(request.endTime)!}">
                                </div>
                                <span class="color48 font12" id="warning"></span>
                            </li>
                            <li class="flex-between">
                                <span class="color27 font14" >处理状态：</span>
                                <select class="w114 font14" name="complaintsStatusList" id="complaintsStatusList">
                                    <option value="">全部</option>
                                <#assign complaintsStatus={"WAITING":"未处理","PROCESSING":"处理中","FIXED":"处理完成"}>
                                <#assign values=complaintsStatus?keys>
                                <#list values as key>
                                    <#if request??&&request.complaintsStatusList??&& request.complaintsStatusList[0]??&&key  == request.complaintsStatusList[0]>
                                        <option value="${key}" selected>${complaintsStatus[key]}</option>
                                    <#else>
                                        <option value="${key}">${complaintsStatus[key]}</option>
                                    </#if>
                                </#list>
                                </select>
                            </li>
                            <li class="text-right">
                                <button type="button" class="text-center search font14" id="myComplaintsSearch-btn">查询</button>
                            </li>
                        </ul>
                    <div class="order-list">
                        <h3 class="color27 font18">
                            投诉列表
                            <small class="font12 color99">（注：仅供查询3个月内的服务信息）</small>
                        </h3>
                        <#if response?? && response.bizResponse?? && response.bizResponse.complaintsInfoDTOS??>
                            <table>
                                <thead>
                                <tr>
                                    <th>投诉单号</th>
                                    <th>提交时间</th>
                                    <th style="width: 52%;padding-right: 15px;">内容</th>
                                    <th>处理状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody class="page-list">
                            <tr>
                                <td></td>
                            </tr>
                                </tbody>
                            </table>
                        <#else>
                            <div class="no-num">
                                <img src="${resourcesPath}/resources/img/TSS.png" alt="" class="mb40 block-auto">
                                <p class="color66 font16 text-center">未查询到任何投诉信息</p>
                            </div>
                        </#if>

                        <div class="page">
                        </div>
                    </div>
                </div>
            </div>

        </div>
<#--     右边导航栏         -->
    <#include "../commons/aside.ftl">
        <!-- footer -->
    <#include "../commons/footer.ftl">

    </div>
    <#--   投诉详情 -->
    <div class="fix-box detail-box" id="complaints-detail" style="display: none;">

    </div>
    <script src="${resourcesPath}/resources/js/index.js"></script>
    <script>
        $(function () {
            //设置左边栏选中
            $(".left-nav ul li").eq(4).addClass("on").siblings().removeClass("on");
            var s = new Date();
            s.setMonth(s.getMonth() - 2);
            var start = getDateFormat(s);
            //当前日期
            var end = getDateFormat(new Date());
            lay('#version').html('-v' + laydate.v);
            laydate.render({
                elem: '#startTime'
                ,min: start
                , max: end
                , done: function (value, date, endDate) {
                    initSecondDate(value);
                }
            });
            function getDateFormat(nowDate) {
                var year = nowDate.getFullYear();
                var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;
                var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();
                return year + "-" + month + "-" + date;
            }

            function initSecondDate(startTime) {
                $("#endTime").remove();
                $("#div-end").html('<input type="text" class="demo-input w114 font14"  readonly="readonly" autocomplete="off" placeholder="年/月/日" id="endTime" name="endTime" value="${(request.endTime)!}">');
                laydate.render({
                    elem: '#endTime'
                    ,min:startTime
                    , max: getDateFormat(new Date())
                });
            }
            var complaintsId = $("#complaintsId").val();
            var requestParam={
                complaintsId:complaintsId
            };
            $.PAGE.INIT($.HTTP.URL.COMPLAINTS_LIST_PAGE,requestParam);

            /*    列表信息搜索  */
            $("#myComplaintsSearch-btn").click(function () {
                $("#warning").html("");
                var startTime = $("#startTime").val();
                var endTime = $("#endTime").val();
                var complaintsStatusList = $("#complaintsStatusList").val();
                //不可一个为空一个不为空
                if((startTime&&!endTime)||(!startTime&&endTime)){
                    $("#warning").html("请选择一个时间范围");
                    return;
                }
                console.log("startTime ="+startTime);
                if(startTime&&endTime&&(startTime>endTime)){
                    $("#warning").html("结束日期不可在开始日期之前");
                    return;
                }
                var config = {
                    startTime: startTime,
                    endTime: endTime,
                    complaintsStatusList: complaintsStatusList
                };
                $(document).trigger("p-param-map.page", config);
            });

        });

        /*    查看/关闭详情  */
        function detail(complaintsId, type) {
            if (type == "close") {
                $("#complaints-detail").hide();
            } else if (type == "open") {
                $.HTTP.GetHTML($.HTTP.URL.COMPLAINTS_DETAIL,
                        {
                            complaintsId: complaintsId
                        },
                        function (data) {
                            $("#complaints-detail").html(data);
                            $("#complaints-detail").show();
                        });
                $("#complaints-detail").show();
            }
        };
    </script>
</body>

</html>