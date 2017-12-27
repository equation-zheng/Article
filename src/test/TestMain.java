package test;

import java.util.List;

import bean.Article;
import bean.Comment;
import bean.Pic;
import bean.User;
import service.ArticleService;
import util.DataBaseUtils;
import util.TableUtils;

public class TestMain {

	public static void main(String[] args) {
		
//		String sql = "select * from t_article where id = ?";
//	    Article article = DataBaseUtils.queryForBean(sql, Article.class,
//	    		"5c68733b-7e14-4b78-8df4-197327b14376");
//	    System.out.println(article);
		
		//测试获取分类列表
//	    String sql = "select * from t_category where 1 = 1";
//		List<?> list = DataBaseUtils.queryForList(sql);
//		System.out.println(list);
	    
/*DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;*/
		
//		String sq1 = TableUtils.getCreateTableSQl(Pic.class);
//		System.out.println(sq1);
		
//		ArticleService ArticleService = new ArticleService();
//		List list = ArticleService.getArticlesByCategoryId(2,0,10);
//		System.out.println(list);
		
//		ArticleService ArticleService = new ArticleService();
//		List list = ArticleService.getContentByArticleId("fa92fa40-08f1-4adc-8b00-571826670be7");
//		System.out.println(list);
		
		
		TestInsertOperation tio = new TestInsertOperation();
		tio.insertPic();
	
	}
	
}
