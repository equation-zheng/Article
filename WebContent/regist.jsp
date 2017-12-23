<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${basePath}/static/css/header.css">
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/regist.css">

<title>登陆/注册</title>
</head>
<body style="background: #96B6D2">

	<%@include file="common/header.jsp"%>
<div class="warp">
	<div class="loginText">
		<p>写出动人的故事</p>
		<p>原来如此简单</p>
	</div>
	<div class="content">
		<div class="title">用户注册</div>
		<div class="inputBox">
			<span>用户名 :</span>
			<input id="username" type="search" placeholder=" 请输入账号/邮箱/手机号" value="小明">
		</div>
		<div class="inputBox">
			<span>密码 :</span>
			<input id="password" type="password" placeholder=" 请输入密码" value="123456">
		</div>
		<div class="inputBoxRadio" style="text-align: center;">
			<span>性别 :</span>
			<div class="radio" id="radio">
				<input type="radio" name="sex" value="male">男
				<input type="radio" name="sex" value="female">女
			</div>
		</div>
		<div class="inputBox">
			<span>地址 :</span>
			<input id="address" type="search" placeholder=" 请输入地址" value="南平">
		</div>
		<div class="inputBox">
			<span>电话 :</span>
			<input id="telephone" type="tel" placeholder=" 请输入电话号码" value="13048959152">
		</div>
		<div class="loginBtn">
			<button onclick="regist()">注册</button>
		</div>
	</div>
</div>
</body>
<script src="${basePath}/static/js/jQuery.js"></script>
<script>
var sex = null;
var radio = $("#radio input").eq(0);
var radios = $("#radio input").eq(1);
radio.on("click", function() {sex = "男";});
radios.on("click", function() {sex = "女";});

	function regist() {
		var username = $("#username").val();
		var password = $("#password").val();
		var address = $("#address").val();
		var telephone = $("#telephone").val();
	
		$.ajax({
			type : "post",  //请求方式
			url : "${basePath}/controller/registController.jsp",  //请求地址
			data : {  //传递给controller的接送\数据
				"username" : username,
				"password" : password,
				"sex" : sex,
				"address" : address,
				"telephone" : telephone
			},
			error : function() {  //如果出错了,将事件重新绑定
				//alert("error");
			},
			success : function(data) {  //返回成功执行回调函数
				if (data == -1){ 
					alert("不能有一项为空!");
				}else if(data == -2) {
					alert("该用户名已经被使用!")
				}else if(data == 1){
					alert("注册成功!");
					window.location.href = "${basePath}";	
				};
			}
		});
	}
</script>
</html>