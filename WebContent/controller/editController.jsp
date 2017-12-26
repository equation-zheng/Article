<%@ page language="java" import="java.util.*,service.RegistService,service.EditService,util.StringUtils"
 pageEncoding="UTF-8"%>
<%
	EditService editService = new EditService();

	//获取客户端传递过来的参数
	String subtitle = request.getParameter("subtitle");
	String category = request.getParameter("categoryId");
	String titleTxt = request.getParameter("titleTxt");
	String txt = request.getParameter("txt");
	String author = request.getParameter("author");
	String description = request.getParameter("description");

	String articleId = request.getParameter("articleId");

	//System.out.println(category);
	//System.out.println(titleTxt);
	//System.out.println(txt);
	//System.out.println(author);
	//System.out.println(telephone);
	
	//不能有空项
	if (StringUtils.isEmpty(subtitle) ||
		StringUtils.isEmpty(category) ||
		StringUtils.isEmpty(titleTxt) ||
		StringUtils.isEmpty(txt) ||
		StringUtils.isEmpty(author) ||
		StringUtils.isEmpty(description)) {
		out.print("-1");    //错误码 -1 : 不能有一项为空!
	} else {
		System.out.println("ok");
		boolean win = editService.insertArticle(subtitle,category,titleTxt,txt,author,description);
		if(win) {
			out.print("1");
		}
	}
	
	
	System.out.println(articleId);
	editService.deleteArticle(articleId);
	
	out.flush();
	out.close();
%>