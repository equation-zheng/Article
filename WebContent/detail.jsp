<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp"%>

<%
	String id = request.getParameter("id");
	System.out.println(id);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/index.css">
<link rel="stylesheet" type="text/css" href="${basePath}/static/css/detail.css">
<title>详情页</title>
</head>
<body>
<div class="article">
	<div class="title">文章标题</div>
	<div class="category">
		<span class="light-font">分类:</span>
		<span class="info">编程代码类</span>
	</div>
	<div class="publicDate">
		<span class="light-font">发布时间:</span>
		<span class="info">2016-10-27</span>
	</div>
	<hr/>
	<div class="content">
		<p>初学javascript的人，都会接触到一个东西叫做闭包，听起来感觉很高大上的。网上也有各种五花八门的解释，其实我个人感觉，没必要用太理论化的观念来看待闭包。

        <p>事实上，你每天都在用闭包，只是你不知道罢了。
        
        <p>比如：
        
        var cheese = '奶酪';
        
        var test = function(){
            alert(cheese);
        }
        OK，你已经写了一个闭包。
        
        <p>函数也是一个数据类型</p>
        <p>变量 cheese 是在全局作用域中的一个变量，当你创建了一个 test 函数，那么，test 和 cheese 就共享一个全局作用域。</p>
        
        <p>你要额外明白的一点是，在js中，函数和变量本质上是一个东西。函数也是一个数据类型。</p>
        
        <p>从上面的定义中也能看出来这一点。你要是不相信的话，我们来看一下咯。</p>
        
        <p>alert(cheese);</p>
        <p>alert(test);</p>
        
        <p>Paste_Image.png</p>
        
        <p>Paste_Image.png</p>
        <p>让我们再来看看 test 和 cheese各是什么类型：</p>
	</div>
	<div class="right">
	    <div class="author">
	    	<img src="${basePath}/static/image/1.jpg" class="header_pic" width="90" height="90"></img>
	   					 作者：张三
	    </div>
	</div>
	<div style="clear: both"></div>
	<!-- 评论区 -->
	<div class="commentBox">
		<textarea class="comment_input" id="commenttxt" placeholder="请输入评论信息(600...)" maxlength="600"></textarea>
		<input type="button" value="保存评论" class="button">
	</div>
	<br/><hr/>
	<div class="comment_list">
		<div class="comment_infor clearfix">
			<div style="border-bottom: solid 1px #ccc" class="comment_word">
				<p style="border-bottom: solid 20px #fff">
					${comment.username}dwedewffrg 说:
				</p>
				<p>
					内容不错,感想分享!
				</p>
			</div>
		</div>
	</div>
</div>
</body>
</html>