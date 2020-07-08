<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/jquery-1.8.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
function showEmail(){
	$("#email").show();
}
function hideEmail(){
	$("#email").hide();
}
</script>
</head>
<body>
<div style="height: 30px;">welcome to my <a href="main.jsp" onmouseover="showEmail()" onmouseout="hideEmail()">Home</a>!!!</div>
<span id="email" style="display: none;">425868307@qq.com</span>
</body>
</html>