package test;

import java.util.List;
import java.util.UUID;

import util.DataBaseUtils;

public class TestInsertOperation {

	//测试给文章插入数据
	public void insertArticle() {
		String sql = "INSERT INTO t_article(id,header,name,content,author,"
	            + "description,is_published,is_delete,create_time,update_time"
	            + ",user_id,category_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
		String id = UUID.randomUUID().toString(); //主键
		String header = "net 实用技术";
		String name  = "如何将net语法练熟悉";
		String content = "我们经常会用到，或者从别的地方迁移一些项目进来，但经常会发现导入后各种报错。这是初学java肯定会遇到的问题，本文对一些常见的处理方案做一个总结。（本文将MyEclipse项目导入eclipse的过程为例，其他情况也可参考这个流程）";
		String author = "JacNte";
		String description = "解决net Git冲突问题...";
		int isPublished = 1 ;
		int isDelete = 0;
		String create_time = "2017-11-21 16:43:10";
		String update_time = "2017-11-21 16:43:10";
		String userId = "319600c3-550a-4f9f-80cf-deebe2377858";
		int categoryId = 2;
		DataBaseUtils.update(sql, id,header,name,content,author,description,isPublished,isDelete,create_time,update_time,userId,categoryId);
		System.out.println("新增成功！");
	}
	
	//测试获取分类列表
	public void getCategoryList(){
		String sql = "select * from t_category where 1 = 1";
		List<?> list = DataBaseUtils.queryForList(sql);
//		System.out.println(list);
	}
}
