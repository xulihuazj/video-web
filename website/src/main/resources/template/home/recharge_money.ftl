<!-- 水电费充值-选择充值金额 -->
<div class="fix-box pay-box" id="recharge_money_model">
    <div class="model-box">
        <div class="model-top flex-between">
            <h3>水电费预充值</h3>
            <img src="${resourcesPath}/resources/img/cc.png" alt="" class="close" id="close_recharge_money_model">
        </div>
        <div class="model-content">
            <div class="flex-2">
                <p class="color27 font18">充值金额：</p>
                <ul class="flex money-btn">
                    <li class="on">
                        <span class="color27 font18 text-center" id="recharge_amount_100">100元</span>
                    </li>
                    <li>
                        <span class="color27 font18 text-center" id="recharge_amount_150">150元</span>
                    </li>
                    <li>
                        <span class="color27 font18 text-center" id="recharge_amount_200">200元</span>
                    </li>
                    <li>
                        <span class="color27 font18 text-center" id="recharge_amount_300">300元</span>
                    </li>
                    <li>
                        <span class="color27 font18 text-center" id="recharge_amount_500">500元</span>
                    </li>
                </ul>
            </div>
            <div class="flex-2">
                <p class="color27 font18">选择支付方式：</p>
                <div class="flex w480">
                    <label for="wx" class="radio flex w-50" id="payment_channel_weixin">
                        <input type="radio" name="sex" checked="checked" id="wx"/>
                        <span class="radio-on"><i class="radios-o"></i></span>
                        <img src="${resourcesPath}/resources/img/weix.png" alt="">
                        <span class="color99 font14">微信支付</span>
                    </label>
                    <label for="zf" class="radio flex w-50" id="payment_channel_alipay">
                        <input type="radio" name="sex" id="zf"/>
                        <span class="radio-on"><i class="radios-o"></i></span>
                        <img src="${resourcesPath}/resources/img/zhifb.png" alt="">
                        <span class="color99 font14">支付宝支付</span>
                    </label>
                </div>
            </div>
            <div class="mt148">
                <button class="next" id="order_recharge">下一步</button>
            </div>
        </div>
    </div>
</div>
