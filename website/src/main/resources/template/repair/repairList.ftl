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
<#include "../commons/header.ftl" />
    <!-- content -->
    <div class="w1120 content flex-between">
        <!-- 侧边导航 -->
       <#include "../commons/left.ftl" />


        <div class="content-body w940">
            <div class="small-nav color99 font12">我的生活 > 保洁维修</div>
            <#include "../home/appAd.ftl" >


            <div class="body-list" id="search_div">
                <input type="hidden"  id="repairId" value="${repairId!}"/>
                <ul class="list-top flex-between">
                    <li class="flex-between">
                        <span class="color27 font14">服务类型：</span>
                        <select class="w114 font14" name="type">
                            <option value="">全部</option>
                            <option value="REPAIR">报修</option>
                            <option value="CLEAN">保洁</option>
                        </select>
                    </li>
                    <li class="flex-between">
                        <span class="color27 font14">提交时间：</span>
                         <input type="text" readonly="readonly" name="clean_startTime" class="demo-input w114 font14" placeholder="年/月/日"
                                id="clean_test1">
                        <span class="colore1">——</span>
                        <div id="endTime2">
                         <input type="text" readonly="readonly" name="clean_endTime" class="demo-input w114 font14" placeholder="年/月/日"
                                id="clean_test2">
                        </div>
                        <span class="color48 font12" id="warning"></span>
                    </li>
                    <li class="flex-between">
                        <span class="color27 font14">服务状态：</span>
                        <select class="w114 font14" name="status">
                            <option value="">全部</option>
                            <option value="WAITING">待确认</option>
                            <option value="EFFECTIVE">处理中</option>
                            <option value="FIXED">已处理</option>
                            <option value="INVALID">已拒绝</option>
                        </select>
                    </li>
                    <li class="text-right">
                        <button class="text-center search font14" id="search">查询</button>
                    </li>
                </ul>
                <div class="order-list">
                    <h3 class="color27 font18">
                        服务列表
                        <small class="font12 color99">（注：仅供查询3个月内的服务信息）</small>
                    </h3>

                    <#if response.bizResponse?? && response.bizResponse.clientRepairInfoDTOS??>
                        <table>
                            <thead>
                            <tr>
                                <th>服务申请单号</th>
                                <th>服务类型</th>
                                <th>提交时间</th>
                                <th class="w20">内容</th>
                                <th>预计服务时间</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody class="page-list">
                            </tbody>
                        </table>
                        <div class="page">
                        </div>
                    <#else>
                    <div class="no-num">
                        <img src="${resourcesPath}/resources/img/BJJ.png" alt="" class="mb40 block-auto">
                        <p class="color66 font16 text-center">您未提交任何服务申请</p>
                    </div>
                    </#if>
                </div>
            </div>
        </div>

    </div>
    <!-- 右边主体 -->
    <#include "../commons/aside.ftl" />
    <!-- footer -->
   <#include "../commons/footer.ftl">

</div>
<div class="fix-box pay-box4" id="detail">

</div>
<script>

    $(function () {
        //设置左边栏选中
        $(".left-nav ul li").eq(3).addClass("on").siblings().removeClass("on");

        $("#detail").hide();
        lay('#version').html('-v' + laydate.v);

        //计算当前时间到3个月前的时间差，单位：天
        function getMinTime() {
            var time =  new Date();
            var m = time.getMonth() + 1;
            m = m - 4;
            time.setMonth(m);
            var min = (new Date() - time) / (1000*60*60*24);
            min = parseInt(min);
            return min;
        };

        var minTime1 = getMinTime(),
            minTime2 = getMinTime(),
            maxTime1 = 0,
            maxTime2 = 0;
        //执行一个laydate实例
     var start =   {
            elem: '#clean_test1',
            min: - minTime1,
            max: maxTime1,
            done: function(value, date){
                $("#clean_test2").remove();
                $("#endTime2").html('<input type="text" readonly name="clean_endTime" class="demo-input w114 font14" placeholder="年/月/日" ' +
                        'id="clean_test2">');
                var d = new Date() - Date.parse(value);
                end.min = -parseInt(d / (1000*60*60*24));
                laydate.render(end);
            }
        };
       var end =  {
            elem: '#clean_test2',
            min: - minTime2,
            max: maxTime2
        };

        laydate.render(start);

        var requestParam={
            repairId:$("#repairId").val()
        };

        $.PAGE.INIT($.HTTP.URL.REPAIR,requestParam);

        $("#search").on("click",function () {
            var sTime = $("#search_div input[name=clean_startTime]").val();
            var eTime = $("#search_div input[name=clean_endTime]").val();
            var type = $("#search_div select[name=type]").val();
            var status = $("#search_div select[name=status]").val();

            if((sTime && eTime && (eTime >= sTime)) || (!sTime && !eTime)){
                var config = {
                                repairType:type,
                                repairStatus:status,
                                startTime:sTime,
                                endTime:eTime
                            };
                $(document).trigger("p-param-map.page",config);

                $("#warning").html("");
            }else{
                $("#warning").html("请输入正确时间");
            }
        });

        checkDetail = function(repairId) {
            $.HTTP.GetHTML($.HTTP.URL.REPAIR_DETAIL,
                    {
                        repairId:repairId
                    },
                    function (data) {
                        $("#detail").html(data);
                        $("#detail").show();
                    });
        };

        closeDiv = function () {
            $("#detail").hide();
        };

    })
</script>
</body>

</html>