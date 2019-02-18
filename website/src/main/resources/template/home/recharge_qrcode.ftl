<!-- 水电费预充值-扫码 -->
<div class="fix-box pay-box1" id="recharge_qrcode_model">
    <div class="model-box">
        <div class="model-top flex-between">
            <h3>水电费预充值</h3>
        </div>
        <div class="model-content">
            <div class="w640 flex-between">
                <div class="color27 font18 w-50 bold-x">充值金额：<span class="font30 colord4 bold-x"
                                                                   id="order_amount"></span>元
                </div>
                <div class="color27 font18 w-50 bold-x">支付方式：
                    <img src="${resourcesPath}/resources/img/weix.png" alt="" id="pay_wx_img">
                    <img src="${resourcesPath}/resources/img/zhifb.png" alt="" id="pay_alipay_img">
                    <small class="color99 font14" id="order_channel"></small>
                </div>
            </div>
            <div class="w640 mrg">
                <h4 class="color27 font18">请扫描下方二维码进行支付：</h4>
            </div>
            <div class="borders" id="qr_code">
            </div>
            <div class="mt44">
                <button class="next" onclick="cancelRechargeOrder()">取消</button>
            </div>

        </div>
    </div>
</div>
