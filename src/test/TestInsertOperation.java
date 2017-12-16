package test;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import bean.Article;
import util.DataBaseUtils;

public class TestInsertOperation {

	//测试给文章插入数据
	@Test     //把光标放在@Test上面，ctrl + 1
	public void insertArticle() {
		String sql = "INSERT INTO t_article(id,header,name,content,author,"
	            + "description,is_published,is_delete,create_time,update_time"
	            + ",user_id,category_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
		String id = UUID.randomUUID().toString(); //主键
		String header = "壁纸背景-get-小技巧";
		String name  = "如何取到-百搭-的壁纸背景-素材";
		
		String content = "我们";
		
		String author = "壁纸哥";
		String description = "get好素材...哈哈";
		int isPublished = 1 ;
		int isDelete = 0;
		String create_time = "2017-12-15 16:43:10";
		String update_time = "2017-12-15 16:43:10";
		String userId = "319600c3-550a-4f9f-80cf-deebe2377858";
		int categoryId = 2;
		DataBaseUtils.update(sql, id,header,name,content,author,description,isPublished,isDelete,create_time,update_time,userId,categoryId);
		System.out.println("新增成功！");
	}
}
