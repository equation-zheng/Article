package bean;

import annotation.Column;
import annotation.Table;

@Table(tableName = "t_pic")
public class Pic {
	
	@Column(type = "varchar(100)", field = "id", primaryKey = true, defaultNull = false)
	private String id;// 主键采用UUID
	
	@Column(type = "varchar(45)", field = "caption")
	private String caption;
	
	@Column(type = "longblob", field = "img")
	private String img;
	
	@Column(field = "user_id", type = "varchar(100)", defaultNull = false)
	private String userId;	//作者ID
}