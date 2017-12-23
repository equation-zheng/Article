<%@ page language="java" import="java.util.*,service.RegistService,service.LoginService,util.StringUtils,bean.*"
 pageEncoding="UTF-8"%>
<%
	//获取客户端传递过来的参数
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String sex = request.getParameter("sex");
	String address = request.getParameter("address");
	String telephone = request.getParameter("telephone");
	
	//System.out.println(username);
	//System.out.println(password);
	//System.out.println(sex);
	//System.out.println(address);
	//System.out.println(telephone);
	
	//不能有空项
	if (StringUtils.isEmpty(username) ||
		StringUtils.isEmpty(password) ||
		StringUtils.isEmpty(sex) ||
		StringUtils.isEmpty(address) ||
		StringUtils.isEmpty(telephone)) {
		out.print("-1");    //错误码 -1 : 不能有一项为空!
	} else {
		LoginService loginService = new LoginService();
		User user = loginService.getUser(username);
		if (user != null) {
			out.print("-2");    //错误码 -2 : 用户名已存在
		} else {
			RegistService registService = new RegistService();
			boolean win = registService.setUser(username,password,sex,address,telephone);
			if(win) {
				out.print("1");
				session.setAttribute("username", username);
			}
		}
	}
	out.flush();
	out.close();
%>