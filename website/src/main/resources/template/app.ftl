<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <#include "./commons/public.ftl">

    <#import "/commons/seo.ftl" as seo >
    <@seo.seo></@seo.seo>

    <link rel="stylesheet" href="${resourcesPath}/resources/css/app.css">
</head>

<body>
<div class="container">
    <div class="top waps">
        <img src="${resourcesPath}/resources/img/LogoApp.png" alt="" class="logo">
        <img src="${resourcesPath}/resources/img/Menuicon.png" alt="" class="nav">
        <div class="shapes shp-wap"></div>
    </div>
    <!-- page 1 -->
    <img src="${resourcesPath}/resources/img/coming_soon.jpg">

</div>
<!-- wap nav -->
<div class="wap-nav">
        <span id="close">
            <img src="${resourcesPath}/resources/img/Fork.png" alt="">
        </span>
    <ul>
        <li>
            <a href="/">首页</a>
        </li>
        <li>
            <a href="./about.html">关于乐璟</a>
        </li>
        <li>
            <a href="./app.html">乐璟APP</a>
        </li>
        <li>
            <a href="./contact.html">立即入住</a>
        </li>
    </ul>
</div>
<script type="text/javascript">
    $(".nav").click(function () {
        $(".wap-nav").show();
        $("#close img").click(function () {
            $(".wap-nav").hide();
        })
    })

    function checkIsAppleDevice() {
        var u = navigator.userAgent,
                app = navigator.appVersion;
        var ios = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
        var iPad = u.indexOf('iPad') > -1;
        var iPhone = u.indexOf('iPhone') > -1;
        if (ios || iPad || iPhone) {
            return true;
        } else {
            return false;
        }
    }

    function checkIsAndroidDevice() {
        var u = navigator.userAgent;
        if (u.indexOf('Android') > -1 || u.indexOf('Adr') > -1) {
            return true;
        } else {
            return false;
        }
    }

    function is_weixin() {
        var u = navigator.userAgent.toLowerCase();
        if (u.match(/MicroMessenger/i) == "micromessenger") {
            return true;
        } else {
            return false;
        }
    }

    if (is_weixin()) {
        alert('当前微信扫码，如不能自动跳转，请在浏览器中打开本页面');
    }
    if (checkIsAppleDevice()) {
        window.location.href =
                "https://itunes.apple.com/cn/app/%E4%B9%90%E7%92%9F%E7%94%9F%E6%B4%BB/id1404760132?l=zh&ls=1&mt=8";
    } else if (checkIsAndroidDevice()) {
        window.location.href = "https://img1.openpms.cn/hotel/16222211655437062.apk"
    }
</script>

</body>


</html>