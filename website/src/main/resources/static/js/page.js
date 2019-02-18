$.PAGE = {},
    $.PAGE.data = {
        p: 1,
        total: 0,
        pageParam: "pageNum",
        pageUrl: "",
        param: {}
    },
    $.PAGE.config = {contentId: "page-list", content: "<tr></tr>"}
;

$.PAGE.INIT = function (url, param) {

    $.PAGE.data.pageUrl = url;

    // init
    $.PAGE.data.param[$.PAGE.data.pageParam] = null;

    $(document).on("p-gopage.page", function (events) {
        var p, param;
        if (arguments.length == 3) {
            p = arguments[1], param = arguments[2];
        } else {
            p = arguments[1];
        }

        var obj = {};
        if (param != null) {
            obj = Object.assign({}, param);
        }
        obj[$.PAGE.data.pageParam] = p;
        var parameter = Object.assign($.PAGE.data.param, obj);
        $.HTTP.GetHTML($.PAGE.data.pageUrl,
            parameter,
            function (data) {

                var content = $("<div>" + data + "</div>");

                var temp = content.find("template");

                $(".page").html(temp.html());

                $("." + $.PAGE.config.contentId).html(data);
            },
            function () {

            }
        );
    });

    $(document).on("p-param.page", function (k, v) {
        $.PAGE.data.param.push(k, v);
    })

    $(document).on("p-param-map.page", function (e, map) {
        var a = Object.assign({}, map);
        $.PAGE.data.param = a;

        $(document).trigger("p-gopage.page", 1);
    })

    $(document).trigger("p-gopage.page", [1, param]);

}
