<%@include file="common/taglib.jsp" %>

<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>登陆</title>
<link rel="stylesheet" href="${basePath}/static/css/index.css">
<link rel="stylesheet" href="${basePath}/static/css/login.css">
</head>
<body style="background: #96B6D2">
<%@include file="common/header.jsp" %>
	<div class="loginText">
		<p>写出动人的故事</p>
		<p>原来如此简单</p>
	</div>
	<div class="content">
		<div class="title">用户登录</div>
		<div class="inputBox">
			<input id="username" type="search" placeholder=" 请输入账号/邮箱/手机号" value="666">
		</div>
		<div class="inputBox">
			<input id="password" type="password" placeholder=" 请输入密码" value="123456">
		</div>
		<div class="loginBtn">
			<button onclick="login()">登陆</button>
		</div>
	</div>
</body>
<script src="${basePath}/static/js/jQuery.js"></script>
<script>
	function login() {
		var username = $("#username").val();
		var password = $("#password").val();
		$.ajax({
			type:"post",	//请求方式
			url:"${basePath}/controller/loginController.jsp",	//请求地址
			data:{"username":username,"password":password},		//传递给controller的json数据
			error:function() {
				alert("我的天又出错了!...");
			},
			success:function(data) {
						//执行成功回调函数
			}
		})
	}
</script>
</html>













