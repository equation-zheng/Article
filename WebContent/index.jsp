<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="service.ArticleService"%>
<% ArticleService articleService = new ArticleService(); %>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${basePath}/static/css/header.css">
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/index.css">

<title>首页</title>
</head>
<body id="bodyImg">

<%@include file="common/header.jsp" %>

<div class="banner">
	<div class="content">
		<i class="leftBtn"></i>
		<i class="rightBtn"></i>
		<ul>
			<li>
				<a href="javascript:void(0)">
					<img src="${basePath}/static/image/morning.jpg" alt="5" />
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<img src="${basePath}/static/image/noon.jpg" alt="1" />
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<img src="${basePath}/static/image/evening.jpg" alt="2" />
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<img src="${basePath}/static/image/at_night.jpg" alt="3" />
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<img src="${basePath}/static/image/noon.jpg" alt="4" />
				</a>
			</li>
			<li>
				<a href="javascript:void(0)">
					<img src="${basePath}/static/image/morning.jpg" alt="5" />
				</a>
			</li>
		</ul>
	</div>
</div>

<div class="h600" style="border:1px solid #ccc">
	<div class="category">
		<div class="title">连载部分</div>
		<ul class="items">
			<li class="item">
				<div class="item_banner">
					<div class="item_header">生活总是充满了乐趣</div>
					<div class="item_name">聊聊我的大学室友</div>
					<div class="item_author">@张三 著</div>
				</div>
				<div class="item_description">那些回忆,那些事...</div>
			</li>
			<li class="item"></li>
			<li class="item"></li>
			<div style="clear:both"></div>
		</ul>
	</div>
	<%
    //查询出编程代码类的相关文章
    List<Map<String,Object>>  articles2 = articleService.getArticlesByCategoryId(2, 0, 7);
    pageContext.setAttribute("articles2", articles2);
	%>
	<!-- ${articles2} -->
	<div class="category">
	    <div class="title">编程代码类</div>
	    <ul class="items">
	        <c:forEach items="${articles2}" var="item">
            <li class="item" onclick="detail('${item.id}');">
                <div class="item_banner">
                    <div class="item_header">${item.header}</div>
                    <div class="item_name" title="${item.name}">${item.name}</div>
                    <div class="item_author">@${item.author} 著</div>
                </div>
                <div class="item_description">${item.description}</div>
            </li>
        </c:forEach>
         <div style="clear:both"></div>
	    </ul>
	</div>
</div>
</body>
<script src="${basePath}/static/js/jQuery.js"></script>
<script src="${basePath}/static/js/index.js"></script>
<script>
//body背景
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
</script>
</html>


















