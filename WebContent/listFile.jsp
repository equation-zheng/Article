<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/taglib.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${basePath}/static/css/header.css">

<title>listFile</title>
</head>
<style>
*{
	padding:0;
	margin:0;
	font-family: "微软雅黑";
}
	/*给大盒子添加样式*/
	#con{
	width:980px;
	margin:60px auto;
	border-radius:25px;
	box-shadow:5px 5px 10px #ccc;
	padding:20px 20px 200px 20px;
	position: relative;
	/*下面代码是兼容各个浏览器的，并实现了四列，没两列之间间距为30px，*/
	-moz-column-count:4;
	-moz-column-gap:30px;
	-moz-column-rule:0px solid #ff0000;   //火狐浏览器
	-webkit-column-count:4;
	-webkit-column-gap:30px;
	-webkit-column-rule:0px solid #ff0000;   //Google chrome 
	-o-column-count:4;
	-o-column-gap:30px;
	-o-column-rule:0px solid #ff0000;   //Opera浏览器的
}

/*小盒子内容区的样式，display:inline-block：实现 效果*/
#con .pic{
	width:188px;
	min-height:100px;
	box-shadow:2px 2px 6px #b5b5b5;
	padding:20px 15px;
	margin:10px;
	display:inline-block;
	border-radius: 5px;
}
#con .pic h3{
	border-bottom:1px solid #ddd;
	line-height:30px;
	text-align:center;
	padding:5px 5px;
}
#con .pic h3 a{
	text-decoration:none;
	color:#999;
}
#con .pic p{
	font-size:12px;
	color:#666;
	line-height:25px;
	text-align: right;
}
#con .pic p a {
	text-decoration: none;
	color: #6598B3;
}
.bigImg{
	position: fixed;
	top: 50px;
	display: none;
    max-width: 980px;
    z-index: 99;
}
#img{
	width: auto;  
    height: auto;  
    max-width: 100%;  
    max-height: 800px;
}
.wallUpLoadBtn {
	position: fixed;
	top: 60px;
	right: 0;
	padding: 10px 20px;
	z-index: 100;
}
.UpLoad {
	position: fixed;
	top: 100px;
	right: 30px;
	z-index: 100;
	display: none;
}
</style>

<body>
<%@include file="common/header.jsp"%>
<button class="wallUpLoadBtn">上传壁纸</button>
<div class="UpLoad">
    	<form action="${pageContext.request.contextPath }/wall"
    		  enctype="multipart/form-data" method="post">
			  <input style="display: none" type="text" name="username"
			  		 value="${sessionScope.username}"><br/>
         	          上传壁纸:
         	  <input type="file" name="file1">
         	  <input type="submit" value="提交" class="submit">
	</form>
</div>
<div id="con">
	<div class="bigImg"><img id="img" /></div>
	<c:forEach var="me" items="${fileNameMap}">
		<div class="pic">
			<c:url value="/download" var="downurl">
	            <c:param name="filename" value="${me.key}"></c:param>
	        </c:url>
			<img class="small_img" width="185px" height="210px" src="${downurl}"/>
	        <h3 class="imgname">${me.value}</h3>
	        <p>
	        	作者: <span class="username">${me.value}</span>
	        	<br>
	        	<a href="${downurl}">下载原图</a>
	        </p>	
        </div>
    </c:forEach>
</div>
</body>

<script src="${basePath}/static/js/jQuery.js"></script>
<script>

$("#listFileTitle").css("background", "#74b0e2");

$(function(){
	var username = "${sessionScope.username}";
	if(!username) window.location.href = "${basePath}/login.jsp";
	
	$(".wallUpLoadBtn").click(function(e) {
		$(".UpLoad").show();
	});
	
	$(document).bind("click",function(e){ 
		var target = $(e.target); 
		if(target.closest(".UpLoad").length == 0 &&
		   target.closest(".wallUpLoadBtn").length == 0) {
			$(".UpLoad").hide();
		}
	}); 
	
	$(".username").each(function(index, element) {
		var $old=$(this).html();
		var $new=$old.substring(0, $old.indexOf('_'));
		$(this).html($new);
	});
	
	$(".imgname").each(function(index, element) {
		var $old=$(this).html();
		var $new=$old.substring($old.indexOf('_') + 1, $old.length);
		$(this).html($new);
	});

	$(".small_img").click(function(e) {
		$("#img").attr("src",this.src);
		$(".bigImg").show();
	});
	
	$(document).bind("click",function(e){ 
		var target = $(e.target); 
		console.log(target.closest("#img").length+"||"+target.closest(".small_img").length);
		if(target.closest("#img").length == 0 &&
		   target.closest(".small_img").length == 0) {
			$(".bigImg").hide();
		}
	}); 
	
}); 
</script>
</html>