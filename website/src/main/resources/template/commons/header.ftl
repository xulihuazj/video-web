<#if !userInfo??>
<div class="bgff">
    <nav class="w1120 flex-between">
        <div class="nav-left flex-between">
            <a href="${basePath}/">
                <img src="${resourcesPath}/resources/img/logo.png" alt="">
            </a>
            <span>
         </span>
        </div>

        <label class="search_label" for="keywords">搜索关键词</label>
        <div class="search_keywords">
            <input type="text" name="q" id="keywords" class="search_input" placeholder="请输入关键词" autocomplete="off"
                   _stat="顶部导航区_搜索框" value="神奇动物在哪里">
        </div>

        <div class="nav">
            <ul class="flex-between text-center">
                <li><a href="${basePath}/">首页</a></li>
                <li><a href="${basePath}/html/product">乐璟产品</a></li>
                <li><a href="${basePath}/html/about">关于乐璟</a></li>
                <li><a href="#">记录</a></li>
                <li><a href="#">上传</a></li>
                <li class="flex-between text-center">
                    <a href="${basePath}/login/page">登录</a>
                    <a href="${basePath}/user/register/page">注册</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
</#if>

<#if userInfo??>
<div class="bgff">
    <nav class="w1120 flex-between">
        <div class="nav-left flex-between">
            <a href="${basePath}/">
                <img src="${resourcesPath}/resources/img/logo.png" alt="">
            </a>
            <span>
            <img src="${resourcesPath}/resources/img/didi.png" alt="">
            苏州
        </span>
        </div>
        <div class="nav">
            <ul class="flex-between text-center">
                <li><a href="${basePath}/">首页</a></li>
                <li><a href="${basePath}/html/product">乐璟产品</a></li>
                <li><a href="${basePath}/html/about">关于乐璟</a></li>
                <li class="flex-between photo">
                    <div class="photo-img">
                        <img src="${resourcesPath}/resources/img/dtx.png" alt="" class="block">
                    </div>
                    <span class="triangle_border_down"></span>

                    <!-- 下拉列表 -->
                    <div id="nav-down">
                        <ul>
                            <li><a href="${basePath}/home/room/detail">我的房间</a></li>
                            <li><a href="${basePath}/user/account/page">账户设置</a></li>
                            <li><a href="${basePath}/login/out">退出登录</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</div>
</#if>


<script>
    $(function () {
        var href = location.href;
        var acher = $(".bgff .flex-between li>a");
        $.each(acher, function (data) {
            var link = $(acher[data]).attr("href");
            if (href.endsWith(link) > 0) {
                $(acher[data]).addClass("active");
            }
        })
    })
</script>
