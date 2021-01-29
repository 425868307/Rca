<%@page import="com.yaof.pojo.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>user_info</title>
    <script src="../js/jquery-1.8.3.js"></script>
    <script src="../js/common.js"></script>
    <script type="text/javascript">
        $(function () {
            var html = "";
            if ("${user.sex}" != '') {
                if ("${user.sex}" == 'f') {
                    html = "<li><div>用户性别:</div>女</li>";
                    $('#woman').attr("checked", "checked");
                } else {
                    html = "<li><div>用户性别:</div>男</li>";
                    $('#man').attr("checked", "checked");
                }
                $('#userInfo').append(html);
            }
            if ("${user.birthdate}" != '') {
                html = "<li><div>用户生日:</div>${user.birthdate}</li>";
                $('#userInfo').append(html);
            }
            if ("${user.addr}" != '') {
                html = "<li><div>用户地址:</div>${user.addr}</li>";
                $('#userInfo').append(html);
            }
            if ("${user.phone}" != '') {
                html = "<li><div>用户电话:</div>${user.phone}</li>";
                $('#userInfo').append(html);
            }
            if ("${user.email}" != '') {
                html = "<li><div>用户邮箱:</div>${user.email}</li>";
                $('#userInfo').append(html);
            }
        });
    </script>
    <style type="text/css">
        a {
            margin: 15px 150px;
            text-decoration-line: none;
        }

        ul li {
            list-style-type: none;
            margin: 5px 30px;
        }

        ul li div {
            width: 100px;
            float: left;
        }

        #psdEdit, #infoEdit {
            margin: 15px;
        }

        #psdEdit ul li button, #infoEdit ul li button {
            margin: 12px 45px;
        }
    </style>
</head>
<body>
<div>
    <a href="javascript:infoAddEdit()">个人信息完善修改</a><a href="javascript:psdEdit()">密码修改</a>
    <ul id="userInfo">
        <li>
            <div>用户账号:</div>
            ${user.account}</li>
        <li>
            <div>用户姓名:</div>
            ${user.name}</li>
        <li>
            <div>用户年龄:</div>
            ${user.age}</li>
    </ul>
    <div id="infoEdit" hidden="hidden">个人信息编辑
        <ul>
            <li>
                <div>用户账号:</div>
                ${user.account}</li>
            <li>
                <div>用户姓名:</div>
                <input type="text" value="${user.name}"/></li>
            <li>
                <div>用户年龄:</div>
                <input type="text" value="${user.age}"/></li>
            <li>
                <div>用户性别:</div>
                <input type="radio" id="man" name="sex" value="m"><label for="man">男</label>
                <input type="radio" id="woman" name="sex" value="f"><label for="woman">女</label>
            </li>
            <li>
                <div>用户生日:</div>
                <input type="text" value="${user.birthdate}"/></li>
            <li>
                <div>用户地址:</div>
                <input type="text" value="${user.addr}"/></li>
            <li>
                <div>用户电话:</div>
                <input type="text" value="${user.phone}"/></li>
            <li>
                <div>用户邮箱:</div>
                <input type="text" value="${user.email}"/></li>
            <li>
                <button onclick="editCancel()">取消</button>
                <button onclick="infoSave()">保存</button>
            </li>
        </ul>
    </div>
    <div id="psdEdit" hidden="hidden">密码修改
        <ul>
            <li>
                <div>原密码:</div>
                <input type="password"/></li>
            <li>
                <div>新密码:</div>
                <input type="password"/></li>
            <li>
                <div>确认密码:</div>
                <input type="password"/></li>
            <li>
                <button onclick="editCancel()">取消</button>
                <button onclick="psdEditSave()">保存</button>
            </li>
        </ul>
    </div>
</div>
</body>
</html>