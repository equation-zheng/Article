<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@include file="common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${basePath}/static/css/header.css">
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/login.css">

<title></title>
</head>
<style>
*{
	margin: 0;
	padding: 0;
	font-family: "微软雅黑";
}
</style>
<body id="bodyImg">
	<%@include file="common/header.jsp"%>
	<div class="loginText">
		<p>写出动人的故事</p>
		<p>原来如此简单</p>
	</div>
	<div class="content">
		<div class="title">用户登录</div>
		<div class="inputBox">
			<input id="username" type="search" placeholder=" 请输入账号/邮箱/手机号" value="小明">
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
	var bodyImg = $("#bodyImg").eq(0);
	var myDate = new Date();
	var h = myDate.getHours();
	if(h >= 6 && h <= 11) {
		bodyImg.css("background-image", "url(${basePath}/static/image/morning.jpg)");
	}else if(h >= 12 && h <= 15) {
		bodyImg.css("background-image", "url(${basePath}/static/image/noon.jpg)");
	}else if(h >= 16 && h <= 18) {
		bodyImg.css("background-image", "url(${basePath}/static/image/evening.jpg)");
	}else{
		bodyImg.css("background-image", "url(${basePath}/static/image/at_night.jpg)");
	}

	function login() {
		var username = $("#username").val();
		var password = $("#password").val();
		$.ajax({
			type : "post",  //请求方式
			url : "${basePath}/controller/loginController.jsp",  //请求地址
			data : {  //传递给controller的接送\数据
				"username" : username,
				"password" : password
			},
			error : function() {  //如果出错了,将事件重新绑定
				//alert("error");
			},
			success : function(data) {  //返回成功执行回调函数
				if (data == -1) alert("用户名和密码不能为空");
				else if (data == -2) alert("用户名不存在");
				else if (data == -3) alert("用户或密码错误");
				else window.location.href = "${basePath}";
			}
		});
	}
</script>
</html>