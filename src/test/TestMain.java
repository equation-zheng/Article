package test;

import java.util.List;

import bean.Article;
import bean.User;
import service.ArticleService;
import util.TableUtils;

public class TestMain {

	public static void main(String[] args) {
		String sq1 = TableUtils.getCreateTableSQl(Article.class);
//		System.out.println(sq1);
		
		ArticleService ArticleService = new ArticleService();
		List list = ArticleService.getArticlesByCategoryId(2,0,10);
//		System.out.println(list);
	}

	
}
