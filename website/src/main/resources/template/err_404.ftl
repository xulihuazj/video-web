<!DOCTYPE html>
<html lang="en">
<head>

    <#include "/commons/public.ftl" />

    <#import "/commons/seo.ftl" as seo >
    <@seo.seo></@seo.seo>

    <!-- css -->
    <link rel="stylesheet" type="text/css" href="${resourcesPath}/resources/css/erroe.css"/>

</head>
<body >

<div class="box">
    <!-- 导航 -->
     <#include "./commons/header.ftl">

    <div class="content">
        <img src="${resourcesPath}/resources/img/err404.png" alt="" class="block-auto">
        <p class="font16 color66 text-center mtb50">抱歉！页面不存在或无法找到...</p>
        <a class="back text-center font18 colord4 block-auto" href="${basePath}/">返回首页</a>
    </div>

    <#-- footer -->
    <#include "./commons/footer.ftl">
</div>

</body>
</html>