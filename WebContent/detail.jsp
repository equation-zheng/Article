<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="service.ArticleService"%>
<%@ page language="java" import="service.CommentService"%>
<% ArticleService articleService = new ArticleService(); %>
<% CommentService commentService = new CommentService(); %>
<%
	String id = request.getParameter("id");
	//System.out.println(id);
	Map<String,Object> map = articleService.getContentByArticleId(id);
    pageContext.setAttribute("article", map);
    
    List<Map<String,Object>> list = commentService.getCommentsByArticleId(id);
    pageContext.setAttribute("comments", list);
    //System.out.println(list);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/index.css">
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/detail.css">
<title>详情页</title>
</head>
<style>
*{
	margin: 0;
	padding: 0;
	font-family: "微软雅黑";
}
</style>
<body>
<div class="article">
	<div class="title">${article.name}</div>
	<div class="category">
		<span class="light-font">分类:</span>
		<span class="info">${article.category_name}</span>
	</div>
	<div class="publicDate">
		<span class="light-font">发布时间:</span>
		<span class="info">${article.create_time.toLocaleString()}</span>
	</div>
	<hr/>
	<div class="content">
		${article.content}
	</div>
	<div class="right">
	    <div class="author">
	    	<img src="${basePath}/static/image/1.jpg" class="header_pic" width="90" height="90"></img>
	   					 作者：${article.author}
	    </div>
	</div>
	<div style="clear: both"></div>
	<!-- 评论区 -->
	<div class="commentBox">
		<textarea class="comment_input" id="commenttxt" placeholder="请输入评论信息(600...)" maxlength="600"></textarea>
		<input type="button" value="保存评论" class="button">
	</div>
	<br/><hr/>
	<div>
		<c:forEach items="${comments}" var = "comment">
		<div>
			<div style="border-bottom: solid 1px #ccc" class="comment_word">
				<p>${comment.update_time.toLocaleString()} ${comment.username}:</p>
				<p>${comment.content}</p>
			</div>
		</div>
		</c:forEach>
	</div>
</div>
</body>
<script src="${basePath}/static/js/jQuery.js"></script>
<script>
	$(".button").eq(0).on("click", function() {
		var txt = $("#commenttxt").val();
		$.post("${basePath}/controller/CommentController.jsp", {txt : txt, articleId : "${article.id}"},
				function(data) {
			data = data.trim();
			if(data == "-1") alert("请先登录!");
			else if(data == "1" && txt.length == 0) alert("评论不能为空!");
			else if(data == "1" && txt.length >= 1) {
				alert("保存成功!");
				location.reload();
			}
		});
	});
</script>
</html>