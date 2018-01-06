package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import util.DataBaseUtils;
import bean.User;

	//文章编写/删除服务类

public class EditService {
	
	//我的历史文章获取
	public List<Map<String,Object>> getArticleName(String userName) {
		System.out.println(userName);
		String sql = "select id, header, name,"
				+ "description from t_article where 1 = 1 "
				+ " and is_delete = 0"
				+ " and is_published = 1"
				+ " and author = ?";
		return DataBaseUtils.queryForList(sql, userName);
	}
	
	//文章编写
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
		String content = turn(txt);
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

	// 字符处理函数 换行符变成<br>
	public String turn(String str) {  

	    while (str.indexOf("\n") != -1) {  
	        str = str.substring(0, str.indexOf("\n")) + "<br>"  
	                + str.substring(str.indexOf("\n") + 1);  
	    }  
	    while (str.indexOf(" ") != -1) {  
	        str = str.substring(0, str.indexOf(" ")) + "&nbsp"  
	                + str.substring(str.indexOf(" ") + 1);  
	    }  
	    return str;  
	}  
	
	//删除我的某个历史文章
	public void deleteArticle(String articleId) {
		String sql ="update t_article set is_delete=1 where id = ?";
		DataBaseUtils.update(sql, articleId);
	}

	//文章分类获取
	
	public List<Map<String,Object>> getArticleCategory() {
		
		String sql = "select category_id, category_name from t_category where 1 = ?";
		
		return DataBaseUtils.queryForList(sql, 1);
		
	}
	
}
