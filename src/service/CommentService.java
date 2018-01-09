package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import bean.Comment;
import util.DataBaseUtils;

public class CommentService {
	/*
	 * 保存评论
	 */
	public void saveComment(Comment comment) {
		String sql = "insert into t_comment(id, user_id, content, article_id, create_time, is_delete) values(?,?,?,?,?,?)";
		DataBaseUtils.update(sql, comment.getId(), comment.getUserId(),
				turn(comment.getContent()), comment.getArticleId(),
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()),0);
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
	    while (str.indexOf("\t") != -1) {
	    	str = str.substring(0, str.indexOf("\t")) + "&nbsp&nbsp&nbsp"  
	                + str.substring(str.indexOf("\t") + 1);
	    }
	    return str;  
	}  
	/*
	 * 根据文章id查询它的所有评论
	 */
	public List<Map<String,Object>> getCommentsByArticleId(String id){
	    return DataBaseUtils.queryForList("select b.username ,a.content,a.update_time from t_comment a left JOIN t_user b on a.user_id = b.id  where a.article_id = ?", id);
	}
}
