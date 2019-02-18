<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../commons/public.ftl">

    <#import "/commons/seo.ftl" as seo >
    <@seo.seo></@seo.seo>

    <link rel="stylesheet" href="${resourcesPath}/resources/css/login.css?v=${resourcesVersion}">
    <script src="${resourcesPath}/resources/js/verify.js?v=${resourcesVersion}"></script>
    <script src="${resourcesPath}/resources/js/reg/reg.js?v=${resourcesVersion}"></script>
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
            <div class="nav">
                <ul class="flex-between text-center">
                    <li class="active"><a href="${basePath}/">乐璟首页</a></li>
                    <li></li>
                </ul>
            </div>
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
            <div class="login-title text-center">
                欢迎入住
            </div>

            <div style="display: none;" id="reg_success" class="login-content">

                <img class="reg-success" src="${resourcesPath}/resources/img/zhuce.png">
                <div class="reg-success-text text-center">
                    注册成功!
                </div>

                <button type="button" class="btn b login_page">立即登录</button>

            </div>


            <div id="reg_info" class="login-content">

                <div class="form-group">
                    <input id="mobile_value" name="account" maxlength="11" class="w-100" placeholder="请输入注册手机号码">

                    <div style="display: none;" class="text-error mobileVerify">
                        手机号码格式不正确
                    </div>
                </div>
                <div class="form-group">
                    <div class="flex-between">
                        <input name="code" id="img_code_value" maxlength="5" class="w60 " placeholder="图形验证码">
                        <img id="imgCode" class="code" style="cursor:pointer;" src="${basePath}/image/code">
                    </div>
                    <div style="display: none;" class="text-error imgCodeVerify">
                        图形验证码错误
                    </div>
                </div>
                <div class="form-group">
                    <div class="flex-between">
                        <input name="code" id="mobile_code_value" maxlength="6" class="w60" placeholder="短信验证码">
                        <button id="mobile_code_button" class="code btn">获取验证码</button>
                    </div>

                    <div style="display: none;" class="text-error mobileCodeVerify">
                        短信验证码错误
                    </div>
                </div>
                <div class="form-group relative">
                    <input name="pwd" id="password_value" maxlength="20" type="password" class="w-100"
                           placeholder="请输入6-20位登录密码">
                    <img src="${resourcesPath}/resources/img/biyan.png" class="eye" id="eye-close">
                    <img src="${resourcesPath}/resources/img/look.png" class="eye" style="display: none" id="eye-show">

                    <div style="display: none;" class="text-error passwordVerify">
                        密码格式错误，请输入6~20位密码，且必须含数字/字母/字符中的至少两种组合
                    </div>
                </div>


                <button id="register_button" type="button" class="btn b">立即注册</button>


                <div class="login-reg font14">已有账号，现在就 <a class="login_page font14">登录</a></div>
            </div>

        </div>

    </div>


    <!-- footer -->
   <#include "../commons/footer.ftl">

</div>

<script type="text/javascript">
    $(function () {
        $("#eye-close").on("click", function () {
            $("#eye-show").show();
            $("#eye-close").hide();
            $("input[name=pwd]").attr("type", "text");
        })
        $("#eye-show").on("click", function () {
            $("#eye-close").show();
            $("#eye-show").hide();
            $("input[name=pwd]").attr("type", "password");
        })
    })
</script>

</body>
</html>