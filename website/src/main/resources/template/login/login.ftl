<!DOCTYPE html>
<html lang="en">

<head>
   	<#include "../commons/public.ftl">

    <#import "/commons/seo.ftl" as seo >
    <@seo.seo></@seo.seo>

    <link rel="stylesheet" href="${resourcesPath}/resources/css/login.css">
    <script src="${resourcesPath}/resources/js/verify.js?v=${resourcesVersion}"></script>
    <script src="${resourcesPath}/resources/js/login/login.js?v=${resourcesVersion}"></script>
</head>
<body>
<div class="box">
    <!-- 导航 -->
    <div class="bgff">
        <nav class="w1120 flex-between">
            <div class="nav-left flex-between">
                <img src="${resourcesPath}/resources/img/logo.png" alt="">
                <span>
                        <img src="${resourcesPath}/resources/img/didi.png" alt="">
                        苏州
                    </span>
            </div>

            <a href="${basePath}/" class="font18 colord4 index">乐璟首页</a>

        </nav>
    </div>
    <!-- banner -->
    <div class="banner w1920">
        <img src="${resourcesPath}/resources/img/login.jpg" alt="" class="block-auto login-banner">

        <h1 class="login-banner">
            乐璟——<br>
            致力于提供安全和便利的住所
        </h1>


        <div class="login-card">
            <div class="login-title">
                欢迎回家
            </div>
            <div class="login-content">

                <div class="form-group">
                    <input name="account" maxlength="11" id="mobile_value" class="w-100" placeholder="请输入注册手机号码">

                    <div style="display: none;" class="text-error mobileVerify">
                        手机号码不正确
                    </div>
                </div>
                <div id="img_code_div" style="display: none;" class="form-group">
                    <div class="flex-between">
                        <input name="code" maxlength="5" id="img_code_value" class="w60" placeholder="图形验证码">
                        <img class="code" id="imgCode" style="cursor:pointer;" src="${basePath}/image/code">
                    </div>
                    <div style="display: none;" class="text-error imgCodeVerify">
                        图形验证码不正确
                    </div>
                </div>
                <div class="form-group relative">
                    <input name="pwd" type="password" maxlength="20" id="password_value" placeholder="请输入6-20位登录密码" class="w-100">
                    <img src="${resourcesPath}/resources/img/biyan.png" class="eye" id="eye-close">
                    <img src="${resourcesPath}/resources/img/look.png" class="eye" style="display: none" id="eye-show">

                    <div style="display: none;" class="text-error passwordVerify">
                        密码格式错误，请输入6~20位密码，且必须含数字/字母/字符中的至少两种组合
                    </div>
                    <div style="display: none;" class="text-error loginVerify">
                    </div>
                </div>


                <button type="button" id="login_button" class="btn b">立即登录</button>

                <div class="login-forgot" ><span class="font14" onclick="window.location.href='${basePath}/user/forget/password'" style="cursor: pointer;">忘记密码</span></div>

                <div class="login-reg font14">没有账号，立即 <a href="${basePath}/user/register/page" class="font14">注册</a> </div>
            </div>

        </div>

    </div>



    <!-- footer -->
   <#include "../commons/footer.ftl">

</div>

<script type="text/javascript">
    $(function(){
        $("#eye-close").on("click",function(){
            $("#eye-show").show();
            $("#eye-close").hide();
            $("input[name=pwd]").attr("type","text");
        })
        $("#eye-show").on("click",function(){
            $("#eye-close").show();
            $("#eye-show").hide();
            $("input[name=pwd]").attr("type","password");
        })
    })
</script>

</body>
</html>