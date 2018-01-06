<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>wall</title>
</head>
<style>
*{
	margin: 0;
	padding: 0;
	font-family: "微软雅黑";
}
</style>
<body>
	${message}
</body>
<script src="${basePath}/static/js/jQuery.js"></script>
<script>
$(function(){
	$(document).bind("click",function(e){ 
		window.location.href = "${basePath}/listFile";
	});
	window.setTimeout(window.location.href = "${basePath}/listFile",3000);
}); 
</script>
</html>