<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>user_assist</title>
    <style type="text/css">
        /*为了使菜单居中*/
        body {
            padding-top: 15px;
        }

        /* -------------菜单css代码----------begin---------- */
        .menuDiv {
            overflow: hidden;
            display: inline-block;
        }

        /* 去掉a标签的下划线 */
        .menuDiv a {
            text-decoration: none;
        }

        /* 设置ul和li的样式 */
        .menuDiv ul, .menuDiv li {
            list-style: none;
            margin: 0;
            padding: 0;
            float: left;
        }

        /* 设置二级菜单绝对定位，并隐藏 */
        .menuDiv > ul > li > ul {
            position: absolute;
            display: none;
        }

        /* 设置二级菜单的li的样式 */
        .menuDiv > ul > li > ul > li {
            float: none;
        }

        /* 鼠标放在一级菜单上，显示二级菜单 */
        .menuDiv > ul > li:hover ul {
            display: block;
        }

        /* 一级菜单 */
        .menuDiv > ul > li > a {
            width: 120px;
            line-height: 40px;
            color: black;
            background-color: #58756d21;
            text-align: center;
            border-left: 1px solid #bbf;
            display: block;
        }

        /* 在一级菜单中，第一个不设置左边框 */
        .menuDiv > ul > li:first-child > a {
            border-left: none;
        }

        /* 在一级菜单中，鼠标放上去的样式 */
        .menuDiv > ul > li > a:hover {
            color: #f0f;
            background-color: #bcf;
        }

        /* 二级菜单 */
        .menuDiv > ul > li > ul > li > a {
            width: 120px;
            line-height: 36px;
            color: #456;
            background-color: #eff;
            text-align: center;
            border: 1px solid #ccc;
            border-top: none;
            display: block;
        }

        /* 在二级菜单中，第一个设置顶边框 */
        .menuDiv > ul > li > ul > li:first-child > a {
            border-top: 1px solid #ccc;
        }

        /* 在二级菜单中，鼠标放上去的样式 */
        .menuDiv > ul > li > ul > li > a:hover {
            color: #a4f;
            background-color: #cdf;
        }
    </style>
</head>
<body>
<div class="menuDiv">
    <ul>
        <li>
            <a href="#">搜索网站</a>
            <ul>
                <li><a href="http://www.baidu.com" target="_parent">跳转百度</a></li>
                <li><a href="https://cn.bing.com/">必应</a></li>
                <li><a href="https://www.cnblogs.com/">进入博客园</a></li>
            </ul>
        </li>
        <li>
            <a href="#">Java虚拟机</a>
            <ul>
                <li><a href="#">Java内存模型</a></li>
                <li><a href="#">垃圾收集</a></li>
            </ul>
        </li>
        <li>
            <a href="#">菜单三</a>
            <ul>
                <li><a href="#">二级菜单</a></li>
                <li><a href="#">二级菜单</a></li>
                <li><a href="#">二级菜单</a></li>
                <li><a href="#">二级菜单</a></li>
                <li><a href="#">二级菜单</a></li>
            </ul>
        </li>
        <li>
            <a href="#">菜单四</a>
        </li>
        <li>
            <a href="#">菜单五</a>
            <ul>
                <li><a href="#">二级菜单</a></li>
                <li><a href="#">二级菜单</a></li>
                <li><a href="#">二级菜单</a></li>
            </ul>
        </li>
    </ul>
</div>
</body>
</html>