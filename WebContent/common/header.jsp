
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="header">
	<div class="headerin">
	    <div class="logo">原创文字</div>
		    <c:choose>
	    		<c:when test="${empty sessionScope.username}">
	    		</c:when>
	    		<c:otherwise>
	    			<ul >
				        <li class="first" id="indexTitle"><a href="index.jsp">首页</a></li>
				        <li class="item" id="listFileTitle"><a href="wall.jsp">图库</a></li>
				        <li class="item"><a href="javascript:void(0)">待定</a></li>
				        <li class="item"><a href="javascript:void(0)">待定</a></li>
				        <li class="item"><a href="javascript:void(0)">待定</a></li>
				        <li class="item" id="editTitle"><a href="edit.jsp">文章撰写</a></li>
				    </ul>
	    		</c:otherwise>
		    </c:choose>
	    <div class="login">
	    	<c:choose>
	    		<c:when test="${empty sessionScope.username}">
	    			<span><a href="login.jsp">登陆</a></span>
	        		<span>|</span> 
	        		<span><a href="regist.jsp">注册</a></span>
	    		</c:when>
	    		<c:otherwise>
	    			<span><a href="javascript:void(0)">丫的欢迎,${sessionScope.username}</a></span>
	    			<span>|</span>
	    			<span><a href="${basePath}/controller/logoutController.jsp">登出</a></span>
	    		</c:otherwise>
	    	</c:choose>
	    </div>
	</div>
</div>