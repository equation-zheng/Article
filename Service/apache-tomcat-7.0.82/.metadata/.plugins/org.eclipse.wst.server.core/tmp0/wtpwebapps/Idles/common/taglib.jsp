<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();		//请求上下文路径
	//System.out.println(path);	// /Idles
	int port = request.getServerPort();		//服务器端口号
	//System.out.println(port);	// 8080
	String basePath = null;
	if (port == 80) {
		//getScheme()返回当前页面使用的协议，http 或是 https;
		basePath = request.getScheme()+"://"+request.getServerName()+path;
	} else {
		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	}
	//System.out.println(request.getServerName());   //localhost
	
	request.setAttribute("basePath", basePath);
%>