<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath(); //返回站点根目录
	//System.out.println(path);
    int port = request.getServerPort();	//得到的是服务器的配置文件中配置的端口号 比如 8080等等
    //System.out.println(port);
    String basePath  = null;
    if (port == 80){
    	//System.out.println(port+"==80");
        basePath = request.getScheme()+"://"+request.getServerName()+path;
    } else {
    	//System.out.println(port+"else");
    	//request.getScheme() 等到的是协议名称，默认是http
    	//request.getServerName() 得到的是在服务器的配置文件中配置的服务器名称 比如:localhost .baidu.com 等等
        basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    }
    request.setAttribute("basePath", basePath);
%>