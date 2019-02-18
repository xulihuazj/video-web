<!DOCTYPE html>
<html lang="en">

<head>
    <#include "../commons/public.ftl" />

    <#import "/commons/seo.ftl" as seo >
    <@seo.seo></@seo.seo>

    <link rel="stylesheet" href="${resourcesPath}/resources/css/swiper.min.css">
    <link rel="stylesheet" href="${resourcesPath}/resources/css/detail.css">

    <script src="${resourcesPath}/resources/js/swiper.min.js"></script>
    <script type="text/javascript" src="https://api.map.baidu.com/getscript?v=2.0&ak=ZkhIX4sOHsH7BSQBGNuKKIwp"></script>
</head>

<body>
<div class="box">
    <!-- 导航 -->
     <#include "../commons/header.ftl">

    <#assign detail=response.bizResponse.appHouseDTO >

    <!-- banner -->
    <div class="banner w1920">
        <!--<img src="assets/img/banner.jpg" alt="" class="block-auto w-100">-->
        <div class="banner-pic">
            <div class="swiper-container">
                <div class="swiper-wrapper">

                    <#list detail.roomPic as pic >
                         <div class="swiper-slide">
                             <img src="${pic}" onerror="this.src='${resourcesPath}/resources/img/banner.jpg'">
                             <div class="shadow"></div>
                             <div class="line"></div>
                         </div>
                    </#list>
                </div>
                <!-- 如果需要分页器 -->
                <!--<div class="swiper-pagination"></div>-->

                <!-- 如果需要导航按钮 -->
                <div class="swiper-button-prev">
                    <img src="${resourcesPath}/resources/img/zuo.png" class="block-auto">
                </div>
                <div class="swiper-button-next">
                    <img src="${resourcesPath}/resources/img/you.png" class="block-auto">
                </div>

                <!-- 如果需要滚动条 -->
                <#--<div class="swiper-scrollbar"></div>-->
            </div>

            <#if detail.vrLink?? >
                <img src="${resourcesPath}/resources/img/panorama.gif" class="panorama-btn" onclick="window.open('${detail.vrLink}')">
            </#if>

            <div class="line1"></div>
            <div class="line2"></div>
            <div class="shadow2"></div>

        </div>

        <div class="banner-title flex-between">
            <h1>${detail.projectName}【${detail.houseStyle}】${detail.roomName}</h1>

                <div class="d-price">${detail.monthPrice} <span>元/月起</span></div>
                <div class="d-booking">
                    <span class="order-btn font18 order-box" id="appoint_room">
                        <img src="${resourcesPath}/resources/img/z_yuyue.png" alt="">
                        预约看房
                    </span>

            </div>
        </div>
    </div>

    <!-- content -->
    <div class="content w1120">

        <div class="d-content">
            <div class="d-part part1">
                <div class="d-part-title">
                    <h2><img src="${resourcesPath}/resources/img/fangx.png"> 房型信息</h2>
                </div>
                <div class="d-part-content flex-between">

                    <div class="d-column">
                        <div class="d-label">可入住日期</div>
                        <div class="d-text">${.now?string("yyyy-MM-dd")}</div>
                    </div>
                    <div class="d-column">
                        <div class="d-label">房型</div>
                        <div class="d-text">${detail.roomName}</div>
                    </div>
                    <div class="d-column">
                        <div class="d-label">床位</div>
                        <div class="d-text">${detail.bedNumStr}</div>
                    </div>
                    <div class="d-column">
                        <div class="d-label">最多可住人数</div>
                        <div class="d-text">${detail.liveinNumStr}</div>
                    </div>
                    <div class="d-column">
                        <div class="d-label">建筑面积</div>
                        <div class="d-text">${detail.area}平</div>
                    </div>

                    <div class="d-column">
                        <div class="d-label">装修风格</div>
                        <div class="d-text">${detail.houseStyle}</div>
                    </div>
                    <div class="d-column">
                        <div class="d-label">朝向</div>
                        <div class="d-text">${detail.orientation}</div>
                    </div>
                    <div class="d-column">
                        <div class="d-label">管理费</div>
                        <div class="d-text">${detail.manageFee}元/月</div>
                    </div>
                    <div class="d-column"></div>
                    <div class="d-column"></div>

                </div>
            </div>

            <div class="d-part part2">
                <div class="d-part-title">
                    <h2><img src="${resourcesPath}/resources/img/shez.png"> 配套设施</h2>
                </div>
                <div class="d-part-content flex-between">

                    <#list detail.devicePic as dpic >
                         <div class="d-column">
                             <div class="d-label"><img src="${dpic.devicePic}"></div>
                             <div class="d-text">${dpic.deviceName}</div>
                         </div>
                    </#list>

                </div>
            </div>

            <div class="d-part part3">
                <div class="d-part-title">
                    <h2><img src="${resourcesPath}/resources/img/jiaot.png"> 交通出行</h2>
                </div>
                <div class="d-part-content">

                    <div class="d-part-p">
                        <img src="${resourcesPath}/resources/img/diwei.png">
                        <p>
                            ${response.bizResponse.appHouseDTO.projectName}：${response.bizResponse.appHouseDTO.location}
                        </p>
                        <p>
                            ${response.bizResponse.appHouseDTO.trafficDesc}
                        </p>
                    </div>
                </div>
            </div>

            <div class="d-part part4">
                <div class="d-part-title">
                    <h2><img src="${resourcesPath}/resources/img/ditu.png"> 位置地图</h2>
                </div>
                <div class="d-part-content">
                    <div class="d-map" id="map">

                    </div>
                </div>
            </div>

        </div>


    </div>

    <!-- 侧边导航 -->
    <#include "../commons/aside.ftl">
    <#-- footer -->
    <#include "../commons/footer.ftl">

</div>

<script>
    $(function(){

        $("#appoint_room").click(function () {
            openModel();
            $("#appoint_room_model").show();
        });

        var swiper = new Swiper('.swiper-container', {
            slidesPerView: 3,
            spaceBetween: 10,
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            loop: true,
            autoplay:{
                delay: 3000,
            }
        });

        // 百度地图API功能
        var map = new BMap.Map("map");    // 创建Map实例
        var point = new BMap.Point(${response.bizResponse.appHouseDTO.longtitude}, ${response.bizResponse.appHouseDTO.latitude})
        map.centerAndZoom(point, 16);  // 初始化地图,设置中心点坐标和地图级别
        //添加地图类型控件
        map.addControl(new BMap.MapTypeControl({
            mapTypes:[
                BMAP_NORMAL_MAP,
                // BMAP_HYBRID_MAP
            ]}));
        // map.setCurrentCity("苏州");          // 设置地图显示的城市 此项是必须设置的
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

        var marker = new BMap.Marker(point);        // 创建标注
        map.addOverlay(marker);                     // 将标注添加到地图中


    })
</script>
</body>
</html>