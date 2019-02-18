<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../commons/public.ftl">

    <#import "/commons/seo.ftl" as seo >
    <@seo.seo></@seo.seo>

    <link rel="stylesheet" href="${resourcesPath}/resources/css/setting.css">
</head>
<body>
<div class="box">
    <!-- 导航 -->
    <#include "../commons/header.ftl">
    <!-- content -->
    <div class="w1120 content flex-between" style="margin-top:10px;">
    <!-- 侧边导航 -->
        <#if room_status?? && room_status == "not_check_in">
            <#include "../commons/left_not.ftl">
        <#else>
            <#include "../commons/left.ftl">
        </#if>
    <!-- 右边主体 -->
    <div class="content-body w940">
        <div class="small-nav color99 font12">我的生活 > 账户设置</div>

        <div class="body-list">

            <ul class="btn">
                <li class="on" style="cursor: pointer">修改手机号</li>
                <li style="cursor: pointer">修改登录密码</li>
            </ul>

            <div class="tab1 con">
                <div class="amend relative">
                    <ul class="btn-ul flex-between">
                        <li>验证手机号
                            <span class="rings"></span>
                        </li>
                        <li>绑定新手机号<span class="rings"></span></li>
                        <li>修改成功<span class="rings"></span></li>
                    </ul>
                    <div class="lines"></div>
                </div>
                <div class="step1 con-li">
                    <div class="w290 relative">
                        <input type="text" value="${userPhone?substring(0,3)+'****'+userPhone?substring(7)}" class="font14 color27 w-100" readonly>
                        <div class="flex-between">
                            <input id="sms_code1" type="text" placeholder="短信验证码" class="w162 font14 color66" maxlength="6">
                            <button id="code1" class="code-img w112 font14 text-center">获取验证码</button>
                        </div>
                        <p class="errormsg2 color48 font14 error-text">短信验证码错误</p>
                        <button class="next_step next-btn w-100 font14 text-center">下一步</button>
                    </div>
                </div>
                <div class="step2 con-li">
                    <div class="w290 relative">
                        <input id="newTel" type="text" class="font14 color27 w-100" maxlength="11">
                        <div class="flex-between ">
                            <input id="sms_code2" type="text" placeholder="短信验证码" class="w162 font14 color66" maxlength="6">

                            <!-- bgda 和 bgd4  2个按钮颜色切换 -->
                            <button id="code2" class="code-img w112 font14 text-center">获取验证码</button>
                            <#--<button class="code-img w112 font14 text-center bgda">60s后重新获取</button>-->
                        </div>
                        <p class="errormsg2 color48 font14 error-text">短信验证码错误</p>
                        <button id="resetTel" class="next-btn w-100 font14 text-center">确定修改</button>
                    </div>
                </div>
                <div class="step3 con-li">
                    <div class="w400">
                        <img src="${resourcesPath}/resources/img/cg.png" alt="" class="block-auto">
                        <h4 class="color27 font18 text-center">修改成功！</h4>
                        <p class="color99 font14 text-center">新手机号码为 <span class="new_tel_show"></span> ，再次登录请使用新手机号码。</p>
                    </div>
                </div>
            </div>
            <div class="tab2 con">
                <div class="w288">
                    <input id="oldPwd" type="password" placeholder="旧登录密码" class="font14 color99 w-100" maxlength="20">
                    <div class="relative">
                        <input id="newPwd" type="password" placeholder="新登录密码" class="w-100 font14 color99" maxlength="20">
                        <!-- 睁眼 闭眼图标 -->
                        <img src="${resourcesPath}/resources/img/biyan.png" alt="" class="look biyan">
                        <!-- <img src="${resourcesPath}/resources/img/look.png" alt="" class="look"> -->
                    </div>
                    <p class="pwdhint color48 font14  w-100">密码格式错误，请输入6~20位密码，且必须含数字/字
                        母/字符中的至少两种组合</p>
                    <p class="errormsg color48 font14  w-100"></p>
                    <button id="resetPwd" class="next-btn w-100 font14 text-center">确定重置</button>
                </div>

                <div class="success-box">
                    <img src="${resourcesPath}/resources/img/cg.png" alt="" class="block">
                    <h4 class="color27 font18 text-center">	重置密码完成！<span id="count">3</span>秒后自动跳转到登录页</h4>
                </div>
            </div>
        </div>
    </div>
    </div>
    <!-- 侧边导航 -->
    <#include "../commons/aside.ftl">
    <#include "../commons/footer.ftl">

<script>
    $(function () {
        //设置左边栏选中
        $(".left-nav ul li").eq(5).addClass("on").siblings().removeClass("on");

        $(".success-box").hide();
        $(".errormsg1").hide();
        $(".errormsg2").hide();
        $(".errormsg").hide();
        $(".pwdhint").hide();
        $(".tab1").show();
        $(".tab2").hide();
        $(".btn-ul li").eq(0).addClass("on");
        $(".step1").show();

        $(".btn li").click(function () {
            $(this).siblings().removeClass();
            $(this).addClass("on");
            var num = $(".btn li").index(this);
            $(".con").hide();
            $(".con").eq(num).show();
        })

        $(".look").click(function() {
            if($(this).hasClass("biyan")){
                $(this).attr("src","${resourcesPath}/resources/img/look.png");
                $(this).removeClass("biyan");
                $("#newPwd").attr("type","text");
            }else{
                $(this).attr("src","${resourcesPath}/resources/img/biyan.png");
                $(this).addClass("biyan");
                $("#newPwd").attr("type","password");
            }
        })

        $("#resetPwd").click(function () {
            var oldPassword = $("#oldPwd").val();
            var newPassword = $("#newPwd").val();

            if(!passwordVerify(oldPassword) || !passwordVerify(newPassword)){
                $(".pwdhint").show();
                return false;
            }else{
                $(".pwdhint").hide();
            }
            $.HTTP.Post($.HTTP.URL.MODIFY_PASSWORD,
                    {
                        oldPassword: oldPassword,
                        newPassword: newPassword
                    },
                    function (data) {
                        if(data != null && data.userId != null){
                            $(".success-box").siblings().hide();
                            $(".success-box").show();
                            setTimeout(function () {
                                location.href = "${basePath}/login/page";
                            },3000);
                            setInterval(function () {
                                if(parseInt($("#count").text(), 0) > 0){
                                    $("#count").text(parseInt($("#count").text(), 0) - 1);
                                }
                            },1000);
                        }
                    },
                    function (data){
                        $(".errormsg").show();
                        $(".errormsg").html(data.message);
                    }
            )
        })

        //原有手机号获取验证码
        /**
         * 获取验证码
         */
        var interLogCode1,canGetCode1 = true;
        var interCode1 = function () {
            canGetCode1 = false;
            var second = 60;
            interLogCode1 = setInterval(function () {
                if (second > 0) {
                    $("#code1").text(--second+"s后重新获取");
                    $("#code1").addClass("bgda");
                } else {
                    canGetCode1 = true;
                    $("#code1").text("重新获取");
                    $("#code1").removeClass("bgda");
                    clearInterval(interLogCode1);
                }
            }, 1000)
        };
        $("#code1").on("click", function (){
            if (canGetCode1) {
                $(".errormsg1").hide();
                $.HTTP.Post($.HTTP.URL.GET_SMS_CODE,
                        {
                            mobile: "${userPhone}",
                            codeType: 6
                        },
                        function (data) {
                            interCode1();
                        },
                        function (data){
                            $(".errormsg1").show();
                            $(".errormsg1").html(data.message);
                        }
                )
            }
        });

        //新手机号获取验证码
        /**
         * 获取验证码
         */
        var interLogCode2,canGetCode2 = true;
        var interCode2 = function () {
            canGetCode2 = false;
            var second = 60;
            interLogCode2 = setInterval(function () {
                if (second > 0) {
                    $("#code2").text(--second+"s后重新获取");
                    $("#code2").addClass("bgda");
                } else {
                    canGetCode2 = true;
                    $("#code2").text("重新获取");
                    $("#code2").removeClass("bgda");
                    clearInterval(interLogCode2);
                }
            }, 1000)
        };
        $("#code2").on("click", function (){
            if (canGetCode2) {
                $(".errormsg2").hide();
                var newTel = $("#newTel").val();
                if(!mobileVerify(newTel)){
                    $(".errormsg2").show();
                    $(".errormsg2").html("手机格式不正确");
                    return false;
                }
                $.HTTP.Post($.HTTP.URL.GET_SMS_CODE,
                        {
                            mobile: newTel,
                            codeType: 9
                        },
                        function (data) {
                            interCode2();
                        },
                        function (data){
                            $(".errormsg2").show();
                            $(".errormsg2").html(data.message);
                        }
                )
            }
        });

        /**
         * 换绑手机验证码验证
         */
        $(".next_step").on("click", function () {
            var code = $("#sms_code1").val();
            $.HTTP.Post($.HTTP.URL.VALID_SMS_CODE, {
                        mobile: "${userPhone}",
                        smsCode: code,
                        codeType: 6
                    },
                    function (data) {
                        $(".step1").hide();
                        $(".btn-ul li").eq(1).addClass("on");
                        $(".step2").show();
                    },
                    function (data){
                        $(".errormsg1").show();
                        $(".errormsg1").html(data.message);
                    });
        });

        /**
         * 换绑手机验证码验证
         */
        $("#resetTel").on("click", function () {
            var newTel = $("#newTel").val();
            var code = $("#sms_code2").val();
            $.HTTP.Post($.HTTP.URL.VALID_SMS_CODE, {
                        mobile: newTel,
                        smsCode: code,
                        codeType: 9
                    },
                    function (data) {
                        //换绑
                        $.HTTP.Post($.HTTP.URL.CHANGE_PHONE, {
                                    accountType:"PHONE",
                                    accountCert: newTel
                                },
                                function (data) {
                                    $(".step2").hide();
                                    $(".btn-ul li").eq(2).addClass("on");
                                    $(".step3").show();
                                    $(".new_tel_show").html(newTel);
                                },
                                function (data){
                                    $(".errormsg2").show();
                                    $(".errormsg2").html(data.message);
                                });
                    },
                    function (data){
                        $(".errormsg2").show();
                        $(".errormsg2").html(data.message);
                    });
        });

    });
</script>
</body>
</html>
