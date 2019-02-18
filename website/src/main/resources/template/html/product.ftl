<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../commons/public.ftl" />

    <#import "/commons/seo.ftl" as seo >
    <@seo.seo></@seo.seo>

    <link rel="stylesheet" href="${resourcesPath}/resources/css/product.css">

</head>
<body>
<div class="box">
    <!-- 导航 -->
    <#include "/commons/header.ftl" />

    <!-- banner -->
    <div class="banner w1920 relative">
        <img src="${resourcesPath}/resources/img/datutu.jpg" alt="" class="block-auto w-100">
        <h1 class="text-center">入住乐璟 乐享生活 前程似璟</h1>
    </div>

    <!-- content -->
    <div class="content w1120">

        <div class="part">
            <h2>乐璟APARTMENT——公共空间</h2>
            <div class="part-content relative">
                <img src="${resourcesPath}/resources/img/tu1.jpg">
                <div class="part-text text-center">
                    书吧<br>
                    咖啡吧<br>
                    桌球<br>
                    健身室<br>
                    按摩椅<br>
                    社交厨房<br>
                    无人超市<br>
                </div>
            </div>
        </div>

        <div class="part">
            <h2>乐璟APARTMENT——室内空间</h2>
            <div class="part-content relative">
                <img src="${resourcesPath}/resources/img/tu2.jpg">
                <div class="part-text text-center">
                    一室、1+1R<br>
                    时尚设计<br>
                    品牌家电<br>
                    温馨家具<br>
                    高速WIFI<br>
                    智能水电表<br>
                    烟感喷淋<br>
                    24H中央热水<br>
                    充足储物空间<br>
                </div>
            </div>
        </div>

        <div class="part">
            <h2>乐璟APARTMENT——公共空间</h2>
            <div class="part-content relative">
                <img src="${resourcesPath}/resources/img/tu3.jpg">
                <div class="part-text text-center">
                    共享洗衣房<br>
                    晾晒区<br>
                    无人超市<br>
                    唱吧<br>
                    快递箱<br>
                    抓娃娃机<br>
                    休闲空间<br>
                </div>
            </div>
        </div>

        <div class="part">
            <h2>乐璟APARTMENT——室内空间</h2>
            <div class="part-content relative">
                <img src="${resourcesPath}/resources/img/tu4.jpg">
                <div class="part-text text-center">
                    1 / 2 / 4人间<br>
                    独立私密空间<br>
                    双台盆<br>
                    无线WIFI<br>
                    智能水电表<br>
                    烟感喷淋<br>
                    24H中央热水<br>
                    独立储物空间<br>
                </div>
            </div>
        </div>
    </div>



</div>

<!-- 侧边导航 -->
    <#include "../commons/aside.ftl">
<#-- footer -->
    <#include "../commons/footer.ftl">

</div>

<script>
    $(function () {
    })
</script>
</body>
</html>