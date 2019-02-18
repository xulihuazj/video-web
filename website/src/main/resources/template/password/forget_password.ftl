<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../commons/public.ftl">

    <#import "/commons/seo.ftl" as seo >
    <@seo.seo></@seo.seo>

    <link rel="stylesheet" href="${resourcesPath}/resources/css/pdws.css">
    <script src="${resourcesPath}/resources/js/password/forget_password.js?v=${resourcesVersion}"></script>
    <script src="${resourcesPath}/resources/js/verify.js?v=${resourcesVersion}"></script>
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

        <!-- content -->
        <div class="w1120 content ">

            <p class="font18 color27 "> <img src="${resourcesPath}/resources/img/anquan.png" alt=""> &nbsp;安全中心—找回登录密码</p>

            <div class="con">
                <div class="amend relative">
                    <ul class="btn-ul flex-between">
                        <li class="on">安全验证
                            <span class="rings"></span>
                        </li>
                        <li>设置新密码<span class="rings div-2-span"></span></li>
                        <li>设置成功<span class="rings div-3-span"></span></li>
                    </ul>
                    <div class="lines"></div>
                </div>
                <div class="con-li div-1">
                    <div class="w290">
                        <div class="height70">
                            <input id="mobile_value" maxlength="11" type="text" placeholder="请输入注册手机号" class="font14 color99 w-100 ">
                            <p style="display: none;" class="color48 font12 error-text mobileVerify">手机号码格式不正确或该手机号码尚未注册</p>
                        </div>
                        <div class="height70">
                            <div class="flex-between  ">
                                <input maxlength="5" id="img_code_value" type="text" placeholder="图形验证码" class="w162 font14 color66">
                                <div class="code-img w112 ">
                                    <!-- 验证码在此 -->
                                    <img id="imgCode" class="code" style="cursor:pointer;" src="${basePath}/image/code">
                                </div>

                            </div>
                            <p style="display: none;" class="color48 font12 error-text imgCodeVerify">图形验证码错误</p>
                        </div>
                        <div class="height70 mb30">
                            <div class="flex-between ">
                                <input maxlength="5" type="text" id="mobile_code_value" placeholder="短信验证码" class="w162 font14 color66">
                                <!-- bgda 和 bgd4  2个按钮颜色切换 -->
                                <button id="mobile_code_button" class="code-img w112 font14 text-center bgd4">获取验证码</button>

                                <!-- <button class="code-img w112 font14 text-center bgda">60s后重新获取</button> -->
                            </div>
                            <p style="display: none;" class="color48 font12 error-text mobileCodeVerify">短信验证码错误</p>

                        </div>
                        <button id="next_page" class="next-btn w-100 font14 text-center">下一步</button>
                    </div>
                </div>
                <div class="con-li div-2">
                    <div class="w288 ">
                        <div class="relative ">
                            <input type="password" maxlength="20" id="password_value" placeholder="设置6~20位含数字/字母/字符的密码" class="w-100 font14 color99">
                            <!-- 睁眼 闭眼图标 -->
                            <img src="${resourcesPath}/resources/img/biyan.png"  alt="" class="look biyan">
                            <img src="${resourcesPath}/resources/img/look.png" style="display: none;" alt="" class="look zyan">

                        </div>
                        <p style="display: none;" class="color48 font12 error-text mb60 passwordVerify">密码格式错误，请输入6~20位密码，且必须含数字/字母/字符中的至少两种组合</p>

                        <button id="reset_password_button" class="next-btn w-100 font14 text-center">确认重置</button>
                    </div>
                </div>
                <div class="con-li div-3">
                    <div class="w400">
                        <img src="${resourcesPath}/resources/img/cg.png" alt="" class="block-auto">
                        <h4 class="color27 font18 text-center">密码重置成功，请重新登录！</h4>
                        <button onclick="window.location.href='${basePath}/login/out'" class="next-btn w-100 font14 text-center">重新登录</button>
                    </div>
                </div>
            </div>
        </div>


    <#-- footer -->
    <#include "../commons/footer.ftl">

    </div>
</body>

</html>