<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户登录</title>
    <script src="js/jquery-1.8.3.js" type="text/javascript"></script>
    <script type="text/javascript">

        $(function () {
            var jsons = [];
            $("#btn").click(function () { //给注册按钮绑定事件
                var json = {};
                json.id = $("input[name='username']").val();
                json.name = $("input[name='password']").val();
                jsons.push(json);
                console.info(jsons);
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
                        self.location = 'swagger-ui.html';
                    }
                },
                error: function () {
                    ajaxbg.hide();
                    window.alert("登陆失败!");
                }
            });
        }
    </script>
    <style type="text/css">
        li {
            list-style: thai;
            padding-top: 10px;
        }
    </style>
</head>
<body>
<div style="padding-left: 30px; padding-top: 20px;">
    <fieldset
            style="border-width: thin; border-color: gray; width: 500px;">
        <legend>用户登陆</legend>
        <ul>
            <li><span>用户名:</span>
                <input type="text" name="username"></li>
            <li><span>用户密码:</span>
                <input type="password" name="password"></li>
            <li><input style="width: 80px; margin-left: 60px;" type="submit" onclick="login()" value="登陆"/></li>
            <li><input style="width: 80px; margin-left: 60px;" type="button" id="btn" value="新增"/></li>
        </ul>
    </fieldset>
</div>
</body>
</html>