<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>user_manage</title>
    <link rel="stylesheet" href="../css/windows.css">
    <script src="../js/jquery-1.8.3.js"></script>
    <script src="../js/common.js"></script>
    <script type="text/javascript">
        $(function () {
            if ("${user.power}" == '1') {/* $('#manage').hide(); */
                $('#manage').remove();
            }
            if ("${user.power}" == '9') {
                $('#usual').remove();
                $('#manage').remove();
            }

            $('.account-add .theme-poptit .close').click(function () {
                $('.theme-popover-mask').fadeOut(100);
                $('.account-add').slideUp(200);
                resetRcaUserInfo();
                $(".account-add .hint").text("");
                $(".account-add input[type='button']").eq(0).removeAttr("disabled");
            });

            $('.password-info .theme-poptit .close').click(function () {
                $('.theme-popover-mask').fadeOut(100);
                /* $('.password-info').slideUp(200); */
                $(".password-info").animate({opacity: 'hide'}, 500);
            });

            $(".account-add input[name='account']").blur(function () {
                checkAccount()
            });
        });
    </script>
    <style type="text/css">
        button {
            margin: 10px 60px;
            background-color: #1AAD19;
            color: #FFFFFF;
            border: 1px solid #1AAD19;
            cursor: pointer;
            border-radius: 3px;
        }

        table {
            margin: 5px 25px;
            width: 60%;
        }

        table tr td {
            text-align: center;
        }

        table, table tr td {
            border: 1px solid #9E9E9E;
        }

        .account {
            width: 20%;
            text-align: left;
        }

        .sex input {
            margin: 11px 6px 0px 40px;
        }
    </style>
</head>
<body>
<a id='usual' href="javascript:usualUser()">普通用户</a>
<a id='manage' href="javascript:systemManage()">系统管理员</a>
<div id="usualUserEdit" hidden="hidden">
    <div>普通用户</div>
    <button onclick="editCancel()">返回</button>
    <button onclick="userAdd(9)">添加客户</button>
    <button onclick="userDelete(9)">删除客户</button>
    <button onclick="psdCheck(9)">查询重置密码</button>
    <table>
        <tr>
            <td style="width: 15px;"></td>
            <td class="account">用户账号</td>
            <td hidden="hidden">密码</td>
            <td>姓名</td>
            <td>年龄</td>
        </tr>
    </table>
</div>
<div id="managerEdit" hidden="hidden">
    <div>系统管理员</div>
    <button onclick="editCancel()">返回</button>
    <button onclick="userAdd(1)">添加系统管理员</button>
    <button onclick="userDelete(1)">删除系统管理员</button>
    <button onclick="psdCheck(1)">查询重置密码</button>
    <table>
        <tr>
            <td style="width: 15px;"></td>
            <td class="account">系统管理员账号</td>
            <td hidden="hidden">密码</td>
            <td>系统管理员姓名</td>
            <td>系统管理员年龄</td>
        </tr>
    </table>
</div>
<div id="managerEdit" hidden="hidden">系统管理员</div>
<div class="account-add">
    <div class="theme-poptit">
        <button title="关闭" class="close">×</button>
        <h3>用户添加</h3>
    </div>
    <div class="theme-popbod dform">
        <ol class="theme-signin">
            <li><strong>用户名：</strong><input class="ipt" type="text" name="account" size="20"/>
                <span class="hint"></span></li>
            <li><strong>密码：</strong><input class="ipt" type="password" name="pwd" size="20"/></li>
            <li><strong>重复密码：</strong><input class="ipt" type="password" name="repwd" size="20"/></li>
            <li><strong>姓名：</strong><input class="ipt" type="text" name="name" size="20"/></li>
            <li><strong>年龄：</strong><input class="ipt" type="text" name="age" size="20"/></li>
            <li class="sex"><strong>性别：</strong>
                <input type="radio" id="male" name="sex" checked="checked" value="m"><label for="male">男</label>
                <input type="radio" id="female" name="sex" value="f"><label for="female">女</label></li>
            <li><input class="btn btn-primary" type="button" value="保 存" onclick="saveRcaUserInfo()"/>
                <input class="btn btn-primary" type="button" value="重 置" onclick="resetRcaUserInfo()"/></li>
        </ol>
    </div>
</div>
<div class="password-info">
    <div class="theme-poptit">
        <button title="关闭" class="close">×</button>
        <h3>密码查看</h3>
    </div>
    <div class="theme-popbod dform">
        <ol class="theme-signin">
            <li><strong>用户名：</strong><tt>未查询到此账号.</tt></li>
            <li><strong>密码：</strong><tt>未查询到此账号密码.</tt>
                <input style="width: 100px;" class="ipt" type="text" name="psd"/></li>
            <li><input class="btn btn-primary" type="button" value="修改密码" onclick="editUserPsd()"/>
                <input class="btn btn-primary" type="button" value="确定修改" onclick="saveUserPsd()"/></li>
        </ol>
    </div>
</div>
<div class="theme-popover-mask"></div>
</body>
</html>