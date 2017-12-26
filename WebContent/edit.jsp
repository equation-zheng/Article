<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="service.EditService"%>
<% EditService editService = new EditService();%>
<%
   String userName = session.getAttribute("username").toString();
   List<Map<String,Object>> list = editService.getArticleName(userName);
   //System.out.println(list+" "+userName);
   pageContext.setAttribute("comments", list);
%>

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
                <option value="1">连载部分</option>
                <option value="2">编程代码类</option>
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
	<span class="light-font">我的文章:</span>
    <label id="lblSelect">
        <select class="selectPointOfInterest" id="articleId">
	        <c:forEach items="${comments}" var = "articleName">
	            <option value="${articleName.id}">${articleName.name}</option>
	        </c:forEach>
        </select>
    </label>
	<br>
	<span class="light-font">封面简介:</span>
	<textarea class="subTxt" id="description"></textarea>
	<button class="deleteButton" id="deleteButton">删除我的文章</button>
	<div class="author">
	    	<img src="${basePath}/static/image/1.jpg" class="header_pic" width="90" height="90"></img>
	   		 作者：${sessionScope.username}
	</div>
</div>	
</body>
<script src="${basePath}/static/js/jQuery.js"></script>
<script>
	$("#deleteButton").eq(0).on("click", function() {
		var articleId = $("#articleId").val();
		var checkText = $("#articleId").find("option:selected").text();	
		if(checkText == "") {
			alert("没有文章了");
			return;
		}
		var checked = confirm("你确定要删除 \""+checkText+"\" 这篇文章?");
		if(checked){
			$.ajax({
				type : "post",
				url : "${basePath}/controller/editController.jsp",
				data : {
					"articleId" : articleId
				},
				error : function() {
					alert("删除出错!");
				},
				success : function(data) {
					//alert("完成");
					window.location.href = "${basePath}/edit.jsp";	
				}
			});
		}
	});
	$(".subButton").eq(0).on("click", function() {
		var subtitle = $("#subtitle").val();
		var categoryId = $("#categoryId").val();
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
				alert("error");
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