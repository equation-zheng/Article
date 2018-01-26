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
<style>
*{
	margin: 0;
	padding: 0;
	font-family: "微软雅黑";
}
</style>
<body id="bodyImg">

<%@include file="common/header.jsp" %>


<%
   //查询出连载部分的相关文章
   String[]  img = {"1.jpg",
		"2.jpg",
		"3.jpg",
		"1.png",
		"morning.jpg",
		"noon.jpg"};
   pageContext.setAttribute("img", img);
   pageContext.setAttribute("imgButton", img);
%>

<div class="banner">
	<div id="box">
		<i class="leftBtn"></i>
		<i class="rightBtn"></i>
		<ul>	
			<c:forEach items="${img}" var="img">
				<li>
					<a href="javascript:void(0)">
						<img src="${basePath}/static/image/${img}" alt="${img}" />
					</a>
				</li>
			</c:forEach>
		</ul>
		<div class="spiner">
			<c:forEach items="${imgButton}" var="imgButton">
	            <p></p>
	        </c:forEach>
        </div>
	</div>
</div>
<%
   //查询出连载部分的相关文章
   List<Map<String,Object>>  articles1 = articleService.getArticlesByCategoryId(1, 0, 21);
   pageContext.setAttribute("articles1", articles1);
%>
<div class="h600" style="border:1px solid #ccc">
	<div class="category">
		<div class="title">
			连载部分
			<a style="text-decoration: none;color: #6598B3;"
			   href="#"
			   onclick="itemAll(0)">
	     	>>
	     	</a>
	     </div>
		<ul class="items">
	        <c:forEach items="${articles1}" var="item">
            <li class="item" onclick="detail('${item.id}');">
                <div class="item_banner">
                    <div class="item_header">${item.header}</div>
                    <div class="item_name" title="${item.name}">${item.name}</div>
                    <div class="item_author">@${item.author} 著</div>
                </div>
                <div class="item_description">${item.description}</div>
            </li>
        </c:forEach>
         <div style="clear:both">---</div>
	    </ul>
	</div>
	<%
    //查询出编程代码类的相关文章
    List<Map<String,Object>>  articles2 = articleService.getArticlesByCategoryId(2, 0, 21);
    pageContext.setAttribute("articles2", articles2);
	%>
	<div class="category">
	    <div class="title">
	    	编程代码类
			<a style="text-decoration: none;color: #6598B3;"
			   href="#"
			   onclick="itemAll(1)">
	     	>>
	     	</a>
	     </div>
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
	<%
    //查询出编程代码类的相关文章
    List<Map<String,Object>>  articles3 = articleService.getArticlesByCategoryId(3, 0, 21);
    pageContext.setAttribute("articles3", articles3);
	%>
	<div class="category">
	    <div class="title">
	   		 生活感悟类
			 <a style="text-decoration: none;color: #6598B3;" 
				 href="#"
				 onclick="itemAll(2)">
	     	>>
	     	</a>
	     </div>
	    <ul class="items">
	        <c:forEach items="${articles3}" var="item">
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
var username = "${sessionScope.username}";
	if(!username) window.location.href = "${basePath}/login.jsp";
body背景
$("#bodyImg").css("background-image", "url(${basePath}/static/image/bodyBackground.jpg)");
</script>
</html>




