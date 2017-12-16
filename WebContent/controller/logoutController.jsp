<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%  
    //注销
    session.removeAttribute("username");		//移除session中指定名称的对象
    session.invalidate();		//将session无效化，解绑任何与该session绑定的对象
    String path = request.getContextPath();		//返回request URI中指明的上下文路径
    int port = request.getServerPort();		//返回服务器端口号
    String basePath  = null;
    if(port == 80){
        basePath = request.getScheme()+"://"+request.getServerName()+path;
    }else{
        basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    }
    response.sendRedirect(basePath + "/login.jsp");		//进行页面重定向
%>