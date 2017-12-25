<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp"%>
<%@ page language="java" import="java.util.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/edit.css">
<title>详情页</title>
</head>
<body>
<div class="article">
	<div class="title">文章编写</div>
		<span class="light-font">文章分类:</span>
        <label id="lblSelect">
            <select class="selectPointOfInterest" id="categoryId">
                <option></option>
                <option>连载类</option>
                <option>编程代码类</option>
            </select>
        </label>	
		<span class="light-font">文章标题:</span>
		<input class="titleInput" id="titleTxt">
		<button class="subButton">提交</button>
	<hr/>
	<div class="content">
		<textarea class="comment_input" id="commenttxt"></textarea>
	</div>
	<span class="light-font">封面小标题:</span>
	<input class="titleInput" id="subtitle">
	<br>
	<span class="light-font">封面简介:</span>
	<textarea class="subTxt" id="description"></textarea>
	<div class="author">
	    	<img src="${basePath}/static/image/1.jpg" class="header_pic" width="90" height="90"></img>
	   		 作者：${sessionScope.username}
	</div>
</div>	
</body>
<script src="${basePath}/static/js/jQuery.js"></script>
<script>
	$(".subButton").eq(0).on("click", function() {
		var subtitle = $("#subtitle").val();
		var categoryId = 2;//$("#categoryId").find("option:selected").val();
		var titleTxt = $("#titleTxt").val();
		var txt = $("#commenttxt").val();
		var author = "${sessionScope.username}";
		var description = $("#description").val();
		$.ajax({
			type : "post",  //请求方式
			url : "${basePath}/controller/editController.jsp",  //请求地址
			data : {  //传递给controller的接送\数据
				"subtitle" : subtitle,		//封面小标题
				"categoryId" : categoryId,	//分类id
				"titleTxt" : titleTxt,		//文章标题
				"txt" : txt,				//文章内容
				"author" : author,			//作者名
				"description" : description	//文章封面小简介
			},
			error : function() {  //如果出错了,将事件重新绑定
				//alert("error");
			},
			success : function(data) {  //返回成功执行回调函数
				if (data == -1){ 
					alert("不能有一项为空!");
				}else if(data == 1){
					alert("文章发布成功!");
					window.location.href = "${basePath}";	
				};
			}
		});
	});
</script>
</html>