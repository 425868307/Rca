<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>cost_detail</title>
    <script src="../js/jquery-1.8.3.js"></script>
    <script src="../js/common.js"></script>
    <script type="text/javascript">
        $(function () {
            var date = new Date();
            queryAccountDetail({
                'type': 'day',
                'year': date.getFullYear(),
                'month': date.getMonth() + 1
            });
            $("#select input[name='year']").val(date.getFullYear());
            $("#select input[name='month']").val(date.getMonth() + 1);
        })


    </script>
    <style type="text/css">
        table {
            BORDER: 1PX solid #ddd;
        }

        th, td {
            BORDER-RIGHT: 1PX solid #ddd;
        }

        td {
            text-align: center;
            padding: 6px 20px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        div div, input, label {
            float: left;
            margin: 10px;
        }

        a {
            text-decoration: none;
        }

        .num {
            text-align: right;
        }

        #total, #detail {
            margin: 14px 0px;
        }
    </style>
</head>
<body>
<div id="title">统计:</div>
<div id="select">
    <div>年份:</div>
    <input name="year" type="text">
    <div>月份:</div>
    <input name="month" type="text">
    <div>日期:</div>
    <input name="day" type="text">
    <input name="type" type="radio" checked="checked" id="detail"><label for="detail">详细</label>
    <input name="type" type="radio" id="total"><label for="total">汇总</label>
    <input type="button" value="查找" onclick="search()">
</div>
<div id="edit"></div>
<table id="datas" width="90%" style="table-layout: fixed;"></table>
</body>
</html>