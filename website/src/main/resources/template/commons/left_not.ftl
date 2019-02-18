<aside>
    <div class="left-nav">
        <ul class="color66">
            <li id="myhome"><a href="${basePath}/home/room/detail" class="font14"> <span
                    class="bg-incon bg-incon1"></span> 我的房间
            </a></li>
            <li class="relative" onmouseover="fdafsadfse('model1')" onmouseleave="fdafsadfse2('model1')">
                <a  class="font14"> <span
                        class="bg-incon bg-incon2"></span> 我的账单
                </a>
                <div class="small-menu" style="display: none" id="model1">
                    <p class="font14 color27 text-center">您尚未办理入住，无法查看该菜单</p>
                </div>
            </li>
            <li  class="relative" onmouseover="fdafsadfse('model2')" onmouseleave="fdafsadfse2('model2')">
                <a  class="font14"> <span
                        class="bg-incon bg-incon3"></span> 我的优惠券
                </a>
                <div class="small-menu" style="display: none" id="model2">
                    <p class="font14 color27 text-center">您尚未办理入住，无法查看该菜单</p>
                </div>
            </li>
            <li class="relative" onmouseover="fdafsadfse('model3')" onmouseleave="fdafsadfse2('model3')"><a class="font14"> <span
                    class="bg-incon bg-incon4"></span> 保洁维修
            </a>
                <div class="small-menu" style="display: none"  id="model3">
                    <p class="font14 color27 text-center">您尚未办理入住，无法查看该菜单</p>
                </div></li>
            <li class="relative" onmouseover="fdafsadfse('model4')" onmouseleave="fdafsadfse2('model4')"><a class="font14"> <span
                    class="bg-incon bg-incon5"></span> 我的投诉
            </a>
                <div class="small-menu" style="display: none" id="model4">
                    <p class="font14 color27 text-center">您尚未办理入住，无法查看该菜单</p>
                </div></li>
            <li><a href="${basePath}/user/account/page" class="font14"> <span
                    class="bg-incon bg-incon6"></span> 账户设置
            </a></li>
        </ul>
    </div>
</aside>
<script>

    var fdafsadfse = function (data) {
        $("#model1").hide();
        $("#model2").hide();
        $("#model3").hide();
        $("#model4").hide();
        switch (data) {
            case "model1":
                $("#model1").show();
                break;
            case "model2":
                $("#model2").show();
                break;
            case "model3":
                $("#model3").show();
                break;
            case "model4":
                $("#model4").show();
                break;
        }
    }

    var fdafsadfse2 = function (data) {
        switch (data) {
            case "model1":
                $("#model1").hide();
                break;
            case "model2":
                $("#model2").hide();
                break;
            case "model3":
                $("#model3").hide();
                break;
            case "model4":
                $("#model4").hide();
                break;
        }
    }

</script>