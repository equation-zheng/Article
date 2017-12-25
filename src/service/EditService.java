package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import util.DataBaseUtils;
import bean.User;

	//用户登陆服务类    暂时  没有用到

public class EditService {
	
	public boolean insertArticle(String subtitle,
								 String category,
								 String titleTxt,
								 String txt,
								 String author,
								 String description) {
		
		String createTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		
		String sql = "INSERT INTO t_article(id,header,name,content,author,"
	            + "description,is_published,is_delete,create_time,update_time"
	            + ",user_id,category_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
		String id = UUID.randomUUID().toString(); //主键
		
		String header = subtitle;
		String name  = titleTxt;
		String content = txt;
		//String author = "壁纸哥";
		//String description = "简介";
		int isPublished = 1 ;
		int isDelete = 0;
		
		String create_time = createTime;
		String update_time = createTime;
		
		LoginService loginService = new LoginService();
		User user = loginService.getUser(author);
		String userId = user.getId();
		
		int categoryId = Integer.parseInt(category);
		DataBaseUtils.update(sql, id,header,name,content,author,description,isPublished,isDelete,create_time,update_time,userId,categoryId);
		System.out.println("新增成功！");
		return true;
	}
}
