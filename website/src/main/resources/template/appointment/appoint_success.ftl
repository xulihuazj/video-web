<div class="fix-box pay-box" id="appoint_success_model">
    <div class="model-box">
        <div class="model-top flex-between">
            <h3>预约看房</h3>
            <img src="${resourcesPath}/resources/img/cc.png" alt="" class="close" onclick="closeModel()">
        </div>
        <div class="model-content">
            <div class="dialogs dilogs text-center ">
                <img src="${resourcesPath}/resources/img/zhuce.png" alt="" class="block-auto">
                <h4 class="color27 font18">提交成功</h4>
                <p class="color99 font14 mb60">24小时内，将有公寓管家联系您安排看房，请耐心等待，<br>
                    期间请保持手机畅通或及时关注微信信息，感谢您关注乐璟服务公寓</p>

                <button class="next" onclick="closeModel()">关闭</button>

            </div>

        </div>
    </div>
</div>
<script>
    var closeModel = function () {
        $("#appoint_success_model").hide();
    }

</script>