<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
    <link rel="icon" href="../images/icon.jpg" type="image/x-icon"/>
    <link rel="stylesheet" href="../css/common.css">
    <script src="../js/jquery-1.8.3.js"></script>
    <script src="../js/jquery-ui.js"></script>
    <script>

        $(function () {
            $('#costDetail').css("background", "#cf301a");
            $("#tabs").tabs();
            if ("${user.power}" == '9') {
                /* $('#userManage').hide(); */
                $('#userManage').remove();
            }
        });
        function choose(btn) {
            /* $('button span').css("background", "#dddddd");
             $(btn).find("span").css("background", "white"); */
            $('button').css("background", "#f1f1f1");
            $(btn).css("background", "#cf301a");
            $('#tabs div div :eq(0)').text($(btn).text());
        }

        function logout() {
            $.ajax({
                url: "../logout",
                type: "post",
                dataType: "json",
                success: function (res) {
                    console.info(res)
                    if (res.code == '1') {
                        self.location = '../login.jsp';
                    }
                    if (res.code == '-1') {
                        alert(res.msg);
                        self.location = '../login.jsp';
                    }
                }
            });
        }

        function userInfo() {
            $('#userInfo').click();
        }
        $(function () {
            $('#prop').hover(function () {
                var tooltipHtml = "<div id='tooltip' class='tooltip'>请联系邮箱425868307@qq.com!!!</div>";
                $('#prop').parent('div').append(tooltipHtml); //添加到页面中
                $("#tooltip").css({
                    "position": "fixed",
                    "top": ($('#prop').offset().top - $("#tooltip").height() + 3) + "px",
                    "left": ($('#prop').width() + $('#prop').offset().left) + "px"
                }).show("fast"); //设置提示框的坐标，并显示
            }, function () {
                $("#tooltip").remove();
            });
        })
    </script>
    <style type="text/css">
        button {
            margin: 5px;
            width: 70px;
            height: 40px;
        }

        a {
            width: 95%;
            text-decoration: none;
        }

        ul li {
            list-style-type: none;
        }

        ul li a {
            width: 98%;
            float: none;
        }

        ul li a button {
            width: 100%;
            border: 0;
            margin: 10px;
            padding: 8px;
            font-size: 18px;
        }

        button span {
            float: right;
            background: #dddddd;
            height: 100%;
            width: 2px;
        }

        iframe {
            width: 100%;
            height: 100%;
            border: none;
            padding: 8px;
        }

        #top_bar {
            height: 1px;
            width: 100%;
            background-color: red;
        }

        #user_info {
            height: 20px;
            width: 100%;
        }

        #user_info div {
            float: right;
            width: 240px;
        }

        #tabs-1, #tabs-2, #tabs-3, #tabs-4, #tabs-5 {
            height: 100%;
        }

        #user_info a:hover {
            font-size: 19px;
            color: royalblue;
            font-weight: bolder;
        }

        #user_info div a:hover {
            font-size: 19px;
            color: royalblue;
            font-weight: bolder;
        }

        #tabs div div:hover {
            font-size: 19px;
            color: blueviolet;
            font-weight: bolder;
        }
    </style>
</head>
<body>
<div id="user_info">
    <a href="javascript:location.reload();">返回首页</a>
    <div>welcome,<a href="javascript:userInfo()">${user.account}&nbsp;</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="#" onclick="logout()">退出</a></div>
</div>
<div id="top_bar"></div>
<div id="tabs" style="height: 666px;">
    <ul style="width: 15%; height: 100%; float: left; padding: 0px;">
        <li><a href="#tabs-1">
            <button id='costDetail' onclick="choose(this)">费用明细<span id="init"></span></button>
        </a></li>
        <li><a href="#tabs-2">
            <button id='costRecord' onclick="choose(this)">新增记录<span></span></button>
        </a></li>
        <li><a href="#tabs-3">
            <button id='userAssist' onclick="choose(this)">辅助功能<span></span></button>
        </a></li>
        <li><a href="#tabs-4">
            <button id='userInfo' onclick="choose(this)">个人信息<span></span></button>
        </a></li>
        <li><a href="#tabs-5">
            <button id='userManage' onclick="choose(this)">用户管理<span></span></button>
        </a></li>
    </ul>
    <div style="width: 2px; height: 100%; background-color: gray; float: left;"></div>
    <div style="width: 80%; float: right; height: 100%; margin: 1% 2%;">
        <div style="height: 25px; border-bottom: 1px solid #9E9E9E;">费用明细</div>
        <div id="tabs-1">
            <iframe src="cost_detail.jsp"></iframe>
        </div>
        <div id="tabs-2">
            <iframe src="cost_record.jsp"></iframe>
        </div>
        <div id="tabs-3">
            <iframe src="user_assist.jsp"></iframe>
        </div>
        <div id="tabs-4">
            <iframe src="user_info.jsp"></iframe>
        </div>
        <div id="tabs-5">
            <iframe src="user_manage.jsp"></iframe>
        </div>
    </div>
</div>
<div style="float: left; width: 100%;">
    <div
            style="margin: 0 auto; width: 60%; height: 3px; background-color: purple;">
        <div style="width: 100%; height: 20px;" align="center">
            <a href="#" id="prop">版权所有</a>
        </div>
    </div>
</div>
</body>
</html>