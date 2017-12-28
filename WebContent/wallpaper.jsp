
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>wallpaper</title>
</head>
<style>
*{
padding:0;
margin:0
}
h3 {
	text-align: center;
}
/*给大盒子添加样式*/
#con{
width:980px;
margin:60px auto;
border-radius:25px;
box-shadow:5px 5px 10px #ccc;
padding:20px;

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
line-height:20px;
text-indent: 10px;
letter-spacing: 1px;
display: -webkit-box;
-webkit-line-clamp: 5;
-webkit-box-orient: vertical;
overflow:hidden;
/*很多文字，一行显示不下，用省略号显示的代码*/
text-overflow:ellipsis;
-o-text-overflow:ellipsis; 
-moz-text-overflow: ellipsis;
-webkit-text-overflow: ellipsis;
}
</style>
<body>
<h3></h3>
<div id="con">
	<div class="pic">
		<img src="${basePath}/static/image/1.jpg" width="188px" />
        <h3><a href="#">野蛮生长</a></h3>
        <p>
        	人长大的标志：试着听从自己内心的声音，而不去在乎外面的声   
         	音，等待和拖延是世界上最容易压垮一个人得东西。犹豫不决是你
         	最大的敌人。能看书就不要发呆，能碎觉就不要拖延，能吃饭就不
        	要饿着，能亲吻就不要说话，能找到自己想做的事情就不容易了，
         	青春得浪费在美好事物上。
         </p>
	</div>
</div>
</body>
</html>