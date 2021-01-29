//使用示例：
//<input type="text" class="selecttime" readonly value="" id="dtime1"  />
//必须填写input id
//input class必须包含selecttime,可以初始value 如(12:59:10)也可以为空
//插件会自动根据input的位置自动显示在input位置下左下方
$(function () {
    var hours = "", second = "", minites = "";
    for (var i = 0; i < 24; i++)hours += (i == 0 ? "<span class='crently'>" + i + "</span>" : "<span>" + i + "</span>");
    for (var i = 0; i < 60; i++)minites += (i == 0 ? "<span class='crently'>" + i + "</span>" : "<span>" + i + "</span>");
    for (var i = 0; i < 60; i++)second += (i == 0 ? "<span class='crently'>" + i + "</span>" : "<span>" + i + "</span>");
    var str = "<div class='select_time' style='display:none;' >"
        + "	<div class='data'>"
        + "    	<ul id='select_time_hms' style='display: block;'><li class='tit'>时间</li><li><input readonly value='00' class='h'>:</li><li><input readonly value='00' class='m'>:</li><li><input readonly value='00' class='s'></li></ul>"
        + "        <div class='box'>"
        + "            <div class='hours sp1 hidden'><div class='tit'>小时<span>×</span></div><p class='clearfix'>" + hours + "</p></div>"
        + "            <div class='hours sp2 hidden'><div class='tit'>分钟<span>×</span></div><p class='clearfix'>" + minites + "</p></div>"
        + "            <div class='hours sp3 hidden'><div class='tit'>秒钟<span>×</span></div><p class='clearfix'>" + second + "</p></div>"
        + "        </div>"
        + "        <div class='select_time_btn'><a class='c'>清空</a><a class='t'>现在</a><a class='o'>确认</a></div>"
        + "    </div>"
        + "</div>"
        + "<style>"
        + ".select_time{width:230px;height:32px;border:1px solid #ccc;box-shadow: 2px 2px 5px rgba(0,0,0,.1);position:absolute;right:0;bottom:0;background:#fff;z-index:99999999}"
        + ".select_time .data{position: relative;height: 22px;line-height: 20px;padding:5px;font-size:12px;}"
        + "#select_time_hms{border:1px solid #ccc;position: relative;z-index: 1;float: left;}"
        + "#select_time_hms li:first-child{width: 31px;border-right: 1px solid #ccc;background-color: #fff;text-align: center;}"
        + "#select_time_hms li{float: left;height: 20px;line-height: 20px;border-right: none;font-weight: 900;}"
        + ".select_time .data .select_time_btn {position: absolute;right: 5px;top: 5px;border-right: 1px solid #ccc;}"
        + ".select_time .data .select_time_btn a {float: left;height: 20px;padding: 0 6px; border: 1px solid #ccc;border-right: none;cursor:pointer;}"
        + ".select_time .data .select_time_btn a, .select_time .data .select_time_btn a:hover {color: #333;}"
        + ".select_time .data input{float:left; width:21px; height:20px; line-height:20px; border:none; text-align:center; cursor:pointer; font-size:12px;  font-weight:400;}"
        + ".select_time .box{position:relative;}"
        + ".select_time .box .tit{border-bottom: 1px solid #ccc;height: 20px;line-height: 20px;text-align: center;}"
        + ".select_time .box .tit span{position:absolute; width:20px; top:0; right:0px; cursor:pointer;}"
        + ".select_time .box .hours{position:absolute; left:0; bottom: -1px; width:129px; height:125px; *overflow:hidden;background-color: #fff;border:1px solid #ccc;box-shadow: 2px 2px 5px rgba(0,0,0,.1);}"
        + ".select_time .box .hours p{ padding:5px 0 0 5px;}"
        + ".select_time .box .hours p span{display:block; float:left; width:24px; height:19px; line-height:19px; text-align:center; cursor:pointer;}"
        + ".select_time .box .hours p span.crently {background-color:#f08300;color: #fff;}"
        + ".select_time .box .hours.sp2 {width: 226px;height: 173px;}"
        + ".select_time .box .hours.sp2 p{padding: 6px 0 0 8px;}"
        + ".select_time .box .hours.sp3 {width: 226px;height: 173px;}"
        + ".select_time .box .hours.sp3 p{padding: 6px 0 0 8px;}"
        + "</style>";
    $("body").append(str);

    $(".selecttime").click(function () {
        $(".select_time").eq(0).attr("data", $(this).attr("id"));
        var objy = $(this).offset().top;
        var objx = $(this).offset().left;
        var objw = $(this).width();
        var objh = $(this).height();

        var vcenter = objx + objw / 2;
        var vtop = objy + objh + 1;
        var vleft = objx;
        //console.log(objx,objy,objw,objh);
        if ($(this).val() != "") {
            var spt = $(this).val().split(":");
            $(".select_time  #select_time_hms input").eq(0).val(spt[0]);
            $(".select_time  #select_time_hms input").eq(1).val(spt[1]);
            $(".select_time  #select_time_hms input").eq(2).val(spt[2]);
        }
        $(".select_time").css({"top": vtop + "px", "left": vleft + "px", "display": "block"});
    });

    $("#select_time_hms .h").click(function () {
        $(".select_time .box .hours.sp1").show().siblings().hide();
    });
    $("#select_time_hms .m").click(function () {
        $(".select_time .box .hours.sp2").show().siblings().hide();
    });
    $("#select_time_hms .s").click(function () {
        $(".select_time .box .hours.sp3").show().siblings().hide();
    });

    $(".select_time .data li input").click(function () {
        var obj = $(this);
        if ($(this).hasClass("h")) {
            $(".select_time .box .hours.sp1 span").each(function () {
                if ($(this).text() == parseInt($(obj).val())) {
                    $(this).addClass("crently").siblings().removeClass("crently");
                }
            });
        }
        else if ($(this).hasClass("m")) {
            $(".select_time .box .hours.sp2 span").each(function () {
                if ($(this).text() == parseInt($(obj).val())) {
                    $(this).addClass("crently").siblings().removeClass("crently");
                }
            });
        }
        else {
            $(".select_time .box .hours.sp3 span").each(function () {
                if ($(this).text() == parseInt($(obj).val())) {
                    $(this).addClass("crently").siblings().removeClass("crently");
                }
            });
        }
    });
    $(".select_time .box .hours .tit span").click(function () {
        $(this).parents(".hours").hide();
    });
    $(".select_time .box .hours p span").click(function () {
        if (!$(this).hasClass("crently")) {
            $(this).addClass("crently").siblings().removeClass("crently");
            if ($(this).parents(".hours").hasClass("sp1")) {
                $("#select_time_hms input.h").val(parseInt($(this).text()) >= 10 ? $(this).text() : "0" + $(this).text());
            }
            else if ($(this).parents(".hours").hasClass("sp2")) {
                $("#select_time_hms input.m").val(parseInt($(this).text()) >= 10 ? $(this).text() : "0" + $(this).text());
            }
            else if ($(this).parents(".hours").hasClass("sp3")) {
                $("#select_time_hms input.s").val(parseInt($(this).text()) >= 10 ? $(this).text() : "0" + $(this).text());
            }
            $(this).parents(".hours").hide();
        }
    });
    $(".select_time_btn").on("click", "a", function () {
        if ($(this).hasClass("c")) {
            $("#select_time_hms input.h").val("00");
            $("#select_time_hms input.m").val("00");
            $("#select_time_hms input.s").val("00");
        }
        else if ($(this).hasClass("t")) {
            var now = new Date();
            var h = now.getHours();
            var m = now.getMinutes();
            var s = now.getSeconds();
            $("#select_time_hms input.h").val(h >= 10 ? h : "0" + h);
            $("#select_time_hms input.m").val(m >= 10 ? m : "0" + m);
            $("#select_time_hms input.s").val(s >= 10 ? s : "0" + s);
        }
        else if ($(this).hasClass("o")) {
//			console.log($(this).parents(".select_time").attr("data"));
            $("#" + $(this).parents(".select_time").attr("data")).val($("#select_time_hms input.h").val() + ":" + $("#select_time_hms input.m").val() + ":" + $("#select_time_hms input.s").val());
            $(".select_time").hide();
            $(".select_time .box .hours").hide();
        }
    });

    $(document).mouseup(function (e) {
        var _con = $('.select_time');   // 设置目标区域
        if (!$(e.target).hasClass('.selecttime')) {
            if (!_con.is(e.target) && _con.has(e.target).length === 0) {
                $(".select_time").hide();
                $(".select_time .box .hours").hide();
            }
        }
    });
});

function selectime() {
    $(".selecttime").click(function () {
        $(".select_time").eq(0).attr("data", $(this).attr("id"));
        var objy = $(this).offset().top;
        var objx = $(this).offset().left;
        var objw = $(this).width();
        var objh = $(this).height();

        var vcenter = objx + objw / 2;
        var vtop = objy + objh + 1;
        var vleft = objx;
        //console.log(objx,objy,objw,objh);
        if ($(this).val() != "") {
            var spt = $(this).val().split(":");
            $(".select_time  #select_time_hms input").eq(0).val(spt[0]);
            $(".select_time  #select_time_hms input").eq(1).val(spt[1]);
            $(".select_time  #select_time_hms input").eq(2).val(spt[2]);
        }
        $(".select_time").css({"top": vtop + "px", "left": vleft + "px", "display": "block"});
    });
}