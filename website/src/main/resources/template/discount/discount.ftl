<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../commons/public.ftl">

    <#import "/commons/seo.ftl" as seo >
    <@seo.seo></@seo.seo>

    <link rel="stylesheet" href="${resourcesPath}/resources/css/bill.css">

</head>
<body>
<div class="box">
    <!-- 导航 -->
    <#include "../commons/header.ftl" />

    <!-- content -->
    <div class="w1120 content flex-between">
        <!-- 侧边导航 -->
        <#include "../commons/left.ftl" />

        <!-- 右边主体 -->
        <div class="content-body w940">
            <div class="small-nav color99 font12">我的生活 > 我的优惠券</div>

            <div class="body-list">

                <div class="order-list">

                    <div class="no-num">
                        <img src="${resourcesPath}/resources/img/YHQQ.png" alt="" class="mb40 block-auto">
                        <p class="color66 font16 text-center">暂无优惠券信息！</p>
                    </div>
                </div>
            </div>
        </div>

    </div>


    <!-- footer -->
    <#include "../commons/footer.ftl">
    <!-- 侧边导航 -->
    <#include "../commons/aside.ftl">
</div>


<script src="${resourcesPath}/resources/js/index.js"></script>
<script>
    $(function () {
        $(".left-nav ul li").eq(2).addClass("on").siblings().removeClass("on");
    })
</script>
</body>

</html>