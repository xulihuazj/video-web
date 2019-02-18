$(function () {
    $(".btn li").removeClass();
    $(".con").hide();
    $(".btn li").eq(0).addClass("on");
    $(".con").eq(0).show();
    $(".btn li").click(function () {
        $(this).siblings().removeClass();
        $(this).addClass("on");
        var num = $(".btn li").index(this);
        $(".con").hide();
        $(".con").eq(num).show();
    })


    $('.tab-cont li').hide();
    $('.tab-cont li:eq(0)').show();
    $(".tabbox .tab li").mouseover(function () {
        $(this).addClass('on').siblings().removeClass('on');
        var index = $(this).index();
        number = index;
        $('.tab-cont li').hide();
        $('.tab-cont li:eq(' + index + ')').show();
    });

    var auto = 1; //等于1则自动切换，其他任意数字则不自动切换
    if (auto == 1) {
        var number = 0;
        var maxNumber = $('.tabbox .tab li').length;

        function autotab() {
            number++;
            number == maxNumber ? number = 0 : number;
            $('.tabbox .tab li:eq(' + number + ')').addClass('on').siblings().removeClass('on');
            $('.tab-cont ul li:eq(' + number + ')').show().siblings().hide();
        }
        var tabChange = setInterval(autotab, 3000);
        //鼠标悬停暂停切换
        $('.tabbox').mouseover(function () {
            clearInterval(tabChange);
        });
        $('.tabbox').mouseout(function () {
            tabChange = setInterval(autotab, 3000);
        });
    }
});