<!-- 侧边导航 -->
    <aside>
        <div id="right-nav">
            <div class="right-nav">
                <ul>
                    <li class="order-box" id="appoint_room2">
                        <img src="${resourcesPath}/resources/img/z_yuyue.png" >
                        <p class="font12 text-center">预约看房</p>
                    </li>
                    <li id="tel-box">
                        <img src="${resourcesPath}/resources/img/z_dianhua.png" alt="">
                        <div class="tel-box">
                            <div class="position">
                                <p class="font16 colord4 text-center bold-x">400-820-6537</p>
                                <span class="triangle_border_right"></span>
                            </div>
                        </div>
                    </li>
                    <li id="orde-box">
                        <img src="${resourcesPath}/resources/img/z_erweim.png" alt="">
                        <div class="orde-box">
                            <div class="position">
                                <div class="orde-img">
                                    <img src="${resourcesPath}/resources/img/lejing_20180717.png" alt="">
                                </div>
                                <span class="triangle_border_right"></span>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="up-btn">
                <a href="javascript:scroll(0,0)">
                    <img src="${resourcesPath}/resources/img/zhid.png" alt="">
                </a>
            </div>
        </div>
    </aside>


<!-- 预约看房 -->
<#include "../appointment/appoint_room.ftl">

<script>
    $(function () {
        $("#appoint_room2").click(function () {
            openModel();
            $("#appoint_room_model").show();
        });
    })
</script>