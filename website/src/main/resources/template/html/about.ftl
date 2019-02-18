<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../commons/public.ftl" />

    <#import "/commons/seo.ftl" as seo >
    <@seo.seo></@seo.seo>

    <link rel="stylesheet" href="${resourcesPath}/resources/css/about.css">

</head>
<body>
<div class="box">
    <!-- 导航 -->
    <#include "/commons/header.ftl" />

    <!-- banner -->
    <div class="banner w1920">
        <img src="${resourcesPath}/resources/img/about.jpg" alt="" class="block-auto w-100">
    </div>

    <!-- content -->
    <div class="content w1120 flex-between">

        <div class="fix-left">
            <ul>
                <li class="active nav-item">乐璟介绍</li>
                <li class="nav-item">乐璟愿景</li>
                <li class="nav-item">乐璟使命</li>
                <li class="nav-item">乐璟目标</li>
            </ul>
        </div>

        <div class="right-content">

            <div class="part spy-item">
                <h1>乐璟介绍</h1>
                <p>
                    乐璟公寓社区是中富旗下从事长租公寓投资和运营的平台，通过创新商业模式打造以品质居住为核心，社交、娱乐、体育、休闲、餐饮等多功能叠加的社区生态，通过共享经济和社群经济引领年青一代生活方式。
                </p>
                <p class="important">
                    <img src="${resourcesPath}/resources/img/cf_logo.png"> 中富旅居—— 国内领先的综合公寓服务商
                </p>
                <p>
                    中富旅居由中富投资、易居中国联合创办，新加坡城市发展（CDL）、新城控股战略入股的企业。致力于打造以公寓服务为核心的服务平台，帮助公寓提升服务品质，为租户带来卓越的公寓入住体验。
                </p>
                <p class="important">
                    中富控股，16年地产公寓投资与运营
                </p>
                <p>
                    ——与新加坡辉盛国际(Frasers Hospitality Pte Ltd) 合作创立的“ MODENA ( 名致)精品酒店公寓”连锁品牌<br>
                    ——与新加坡最大地产及酒店管理集团新加坡城市发展（CDL），创建瑞贝庭公寓酒店连锁品牌<br>
                    ——在上海、天津、苏州、大连、无锡等全国多地，开发投资多个品牌酒店公寓<br>
                    ——和高和资本组建了公寓地产基金。<br>
                </p>
            </div>

            <div class="part spy-item">
                <a id="part2"></a>
                <h1>乐璟愿景</h1>
                <p>
                    提升青年生活品质 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;助力中国经济发展
                </p>
            </div>

            <div class="part spy-item">
                <h1>乐璟使命</h1>
                <p>
                    <img src="${resourcesPath}/resources/img/bangs.png"> 政府帮手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <img src="${resourcesPath}/resources/img/guanj.png"> 企业管家&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <img src="${resourcesPath}/resources/img/huob.png"> 青年伙伴
                </p>
            </div>

            <div class="part spy-item">
                <a id="part4"></a>
                <h1>乐璟目标</h1>
                <p>
                    成为中国领先的社区服务运营商
                </p>
            </div>

        </div>


    </div>

    <!-- 侧边导航 -->
    <#include "../commons/aside.ftl">
<#-- footer -->
    <#include "../commons/footer.ftl">

</div>

<script src="${resourcesPath}/resources/js/jq.nav-scroll-spy.js"></script>
<script>
    $(function () {
        $(document).jqNavScrollSpy();
        // // nav 吸顶
        // var navFix = $(".fix-left"),
        //     navHeight = navFix.offset().top;
        //
        // $(window).scroll(function () {
        //     if ($(this).scrollTop() > navHeight) {
        //         navFix.addClass("navFix");
        //     } else {
        //         navFix.removeClass("navFix");
        //     }
        // })
    })
</script>
</body>
</html>