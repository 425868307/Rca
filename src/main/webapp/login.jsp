<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="images/icon.jpg" type="image/x-icon"/>
    <title>用户登录</title>
    <script src="js/jquery-1.8.3.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $("#toReg").click(function () { //给注册按钮绑定事件
                $("#bg03").delay(800).show(0);
                $("#regForm").delay(1500).show(0);
                $("input[name='username']").attr("disabled", "disabled");
                $("input[name='password']").attr("disabled", "disabled");
                $("input[type='submit']").attr("disabled", "disabled");
            });
            $("li input[name='password']").keydown( //给密码框绑定事件
                function () {
                    if (event.keyCode == '13') {
                        login();
                    }
                });

        });

        function login() { //登陆
            var account = $("input[name='username']").val();
            var password = $("input[name='password']").val();
            if (account == '' || password == '') {
                alert("用户名或者密码不能为空.");
                return;
            }
            var ajaxbg = $("#background,#progressBar");
            $.ajax({
                type: "POST",
                url: "login",
                dataType: "json",
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify({
                    "account": account,
                    "password": password
                }),
                beforeSend: function (XMLHttpRequest) { //设置头信息
                    ajaxbg.show();
                    XMLHttpRequest.setRequestHeader("test", account);
                },
                success: function (result) {
                    console.info(result);
                    ajaxbg.hide();
                    window.alert(result.msg);
                    if (result.code == '1') {
                        self.location = 'user/index.jsp';
                    }
                },
                error: function () {
                    ajaxbg.hide();
                    window.alert("登陆失败!");
                }
            });
        }

        function cancel() { //取消注册
            reset();
            $("#regForm").delay(800).hide(0);
            $("#bg03").delay(1500).hide(0);
            $("input[name='username']").removeAttr("disabled");
            $("input[name='password']").removeAttr("disabled");
            $("input[type='submit']").removeAttr("disabled");
        }
        function reset() { //注册重置
            $("span[name='acc_warn']").text("");
            $("span[name='acc_warn']").attr("value", "0");
            $("input[name='reg_user']")[0].value = "";
            $("input[name='reg_psd1']")[0].value = "";
            $("input[name='reg_psd2']")[0].value = "";
            $("input[name='user_age']")[0].value = "";
            $("input[name='user_email']")[0].value = "";
            $("input[name='sex']")[0].checked = true;
            $("input[name='sex']")[1].checked = false;
        }
        function reg() { //确认注册
            var psd1 = $("input[name='reg_psd1']").val();
            var psd2 = $("input[name='reg_psd2']").val();
            var right = $("span[name='acc_warn']").attr("value");
            if (right == '0') {
                alert("请输入正确的密码");
                return;
            }
            if (psd1 != psd2) {
                alert("两次密码不一致");
                return;
            } else if (psd1 == '' && psd2 == '') {
                alert("请输入密码");
                return;
            }
            var psd = psd1;
            var account = $("input[name='reg_user']").val();
            var age = $("input[name='user_age']").val();
            var sex = $("input[name='sex']:checked").val();
            var email = $("input[name='user_email']").val();

            var user = {
                "account": account,
                "password": psd,
                "age": age,
                "sex": sex,
                "email": email
            };
            $.ajax({
                type: "POST",
                url: "register",
                dataType: "json",
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify(user),
                success: function (resp) {
                    console.info(resp);
                    window.alert(resp.msg);
                    reset();
                    $("#regForm").delay(800).hide(0);
                    $("#bg03").delay(1500).hide(0);
                    $("input[name='username']").removeAttr("disabled");
                    $("input[name='password']").removeAttr("disabled");
                    $("input[type='submit']").removeAttr("disabled");
                },
                error: function () {
                    window.alert("注册失败");
                }
            });
        }
        function checkAccount() { //检测账号是否可用
            var account = $("input[name='reg_user']").val();
            if (account == '') {
                $("span[name='acc_warn']").text("用户名不能为空").css("color", "red");
                $("span[name='acc_warn']").attr("value", "0")
                return;
            }
            $.ajax({
                type: "POST",
                url: "checkAccount",
                data: {
                    "account": account
                },
                /*  dataType:"json",
                 contentType : 'application/json;charset=UTF-8',
                 data:JSON.stringify({"account":account}), */
                beforeSend: function (request) {
                    request.setRequestHeader("Test", "yaofang");
                },
                success: function (result) {
                    console.info(result);
                    if (result.code == '1') {
                        $("span[name='acc_warn']").text(result.msg).css("color", "green");
                        $("span[name='acc_warn']").attr("value", "1");
                        $("#register").removeAttr("disabled");
                    } else {
                        $("span[name='acc_warn']").text(result.msg).css("color", "red");
                        $("span[name='acc_warn']").attr("value", "0");
                        $("#register").attr("disabled", "disabled");
                    }
                }
            });
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
        ul {
            list-style-type: none;
        }

        li {
            padding-bottom: 10px;
        }

        ul li div {
            width: 150px;
            float: left;
        }

        li button {
            margin: 5px 25px;
            width: 60px;
        }

        a {
            text-decoration-line: none;
        }

        .background {
            display: block;
            width: 100%;
            height: 100%;
            opacity: 0.4;
            filter: alpha(opacity=40);
            background: while;
            position: absolute;
            top: 0;
            left: 0;
            z-index: 2000;
        }

        .progressBar {
            border: solid 2px #86A5AD;
            background: white url(${pageContext.request.contextPath}/images/loading.gif) no-repeat 10px 10px;
        }

        .progressBar {
            display: block;
            width: 150px;
            height: 28px;
            position: fixed;
            top: 40%;
            left: 50%;
            margin-left: -74px;
            margin-top: -14px;
            padding: 10px 10px 10px 50px;
            text-align: left;
            line-height: 27px;
            font-weight: bold;
            position: absolute;
            z-index: 2001;
        }
    </style>
</head>
<body style="margin: 0px;">
<div id="background" class="background" style="display: none; "></div>
<div id="progressBar" class="progressBar" style="display: none; ">登陆中，请稍等...</div>
<img style="float: left; width: 16%; height: 95%;"
     alt="images/bg01.jpg" src="images/bg01.jpg">
<div style="float: left; width: 84%; position: relative;">
    <img alt="images/bg02.jpg" src="images/bg02.jpg" height="120px"
         width="100%">
    <div style="padding-left: 30px; padding-top: 20px;">
        <fieldset
                style="border-width: thin; border-color: gray; width: 500px;">
            <legend>用户登陆</legend>
            <ul>
                <li>
                    <div>用户名:</div>
                    <input type="text" name="username"></li>
                <li>
                    <div>用户密码:</div>
                    <input type="password" name="password"></li>
                <li><input type="button" id="toReg" value="用户注册"/> <input
                        style="width: 80px; margin-left: 60px;" type="submit"
                        onclick="login()" value="登陆"/></li>
            </ul>
        </fieldset>
    </div>
    <div id="bg03" style="height: 85px; display: none;">
        <img style="left: 565px; width: 100px; position: absolute;"
             alt="bg03.png" src="images/bg03.png">
    </div>
    <div id="regForm"
         style="left: 675px; position: absolute; display: none;">
        <fieldset style="width: 550px; height: 280px;">
            <legend>用户注册</legend>
            <ul>
                <li>
                    <div>用户名:</div>
                    <input type="text" name="reg_user" onblur="checkAccount()">
                    <span name="acc_warn" value="0"></span>
                </li>
                <li>
                    <div>用户密码:</div>
                    <input type="password" name="reg_psd1"></li>
                <li>
                    <div>确认密码:</div>
                    <input type="password" name="reg_psd2"></li>
                <li>
                    <div>用户年龄:</div>
                    <input type="text" name="user_age"></li>
                <li>
                    <div>用户邮箱:</div>
                    <input type="text" name="user_email"></li>
                <li>
                    <div>性别:</div>
                    <input type="radio" id="male" name="sex"
                           checked="checked" value="m"><label for="male">男</label> <input
                        type="radio" id="female" name="sex" value="f"><label
                        for="female">女</label>
                </li>
                <li>
                    <button id="cancel" onclick="cancel()">取消</button>
                    <button id="reset" onclick="reset()">重置</button>
                    <button id="register" onclick="reg()">注册</button>
                </li>
            </ul>
        </fieldset>
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