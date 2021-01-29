<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>cost_record</title>
    <script src="../js/jquery-1.8.3.js"></script>
    <script src="../js/common.js"></script>
    <script src="../js/laydate.js"></script> <!-- 日期的插件 -->
    <script src="../js/selectime.js"></script> <!-- 时间的插件 -->
    <script>
        //执行一个laydate实例
        laydate.render({
            elem: '#date' //指定元素
        });

    </script>
    <style type="text/css">
        /* CSS Document */
        p, ul, li {
            margin: 0;
            padding: 0;
            font-weight: normal;
            list-style-type: none;
        }

        a {
            text-decoration-line: none;
        }

        em, i {
            font-style: normal;
        }

        b {
            font-weight: normal;
        }

        #add {
            margin: 10px;
        }

        #add tr {
            margin-bottom: 10px;
        }

        #add tr td {
            float: left;
            margin-left: 20px;
        }

        #add tr td input {
            margin-left: 10px;
        }

        .test {
            width: 600px;
            margin: 200px auto;
            line-height: 38px;
            text-align: center;
        }

        .txt {
            width: 80px
        }

        div button {
            margin-left: 100px;
        }

        .hint {
            margin-left: 10px;
            color: red;
        }
    </style>
</head>
<body>
<div id="title">增加:</div>
<div style="margin: 20px;" id="menu">日期:<input type="text" placeholder="请选择日期" id="date">
    <button onclick="addDetail()" style="margin-left: 100px;">明细添加</button>
    <button onclick="saveDetail()">保存</button>
    <button onclick="clearDetail()">清空明细</button>
</div>
<span class="hint">请选择新增记录的时间，添加明细，选择时间，录入费用，添加备注，选择日期的所有费用录入完成后，保存明细。</span>
<div align="center" style="background-color: gray;width: 80%;height: 1px; margin: 10px;"></div>
<table id="add">
</table>
<table id="datas" width="90%"></table>
</body>
</html>