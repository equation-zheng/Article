package test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import bean.User;
import util.DataBaseUtils;
import util.TableUtils;

public class TestProperties {

	
	private static String path = "jdbc.properties";
	//读取Properties文件的测试类
	
	public static void main(String[] args) {
		//获取本类的class对象.调用类加载器.返回指定资源的输入流
		InputStream inputStream = TestProperties.class.getClassLoader().getResourceAsStream(path);
		//一个持续属性集    可保存在流中或者从流中加载
		Properties p = new Properties();
		try {
			//从输入流中读取属性列表
			p.load(inputStream);
//			System.out.println(p);
//			System.out.println(p.getProperty("db.username"));
//			System.out.println(p.getProperty("db.password"));
//			System.out.println(p.getProperty("db.dataBaseName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DataBaseUtils.config(path);
		@SuppressWarnings("unused")
		Connection conn = DataBaseUtils.getConnection();
//		System.out.println(conn);
		
		//插入数据
//		String id = UUID.randomUUID() + "";
//		String createTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//		DataBaseUtils.update("INSERT INTO t_user(id,username,password,male,create_time,is_delete,address,telephone) "
//			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", id,"王六",45654565,10,createTime,121,"天津","44575268");
		
		@SuppressWarnings("unused")
		String sql = TableUtils.getCreateTableSQl(User.class);
//		System.out.println(sql);
		
		DataBaseUtils.config(path);
		@SuppressWarnings("unused")
		List<?> list = DataBaseUtils.queryForList("select * from t_user");
//		System.out.println(list);
		
		try {
			DataBaseUtils.config("jdbc.properties");
			@SuppressWarnings("unused")
			Map<?, ?> map = DataBaseUtils.queryForMap("select * from t_user where username = ?","小明");
//			System.out.println(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		User user = DataBaseUtils.queryForBean("select * from t_user limit 1", User.class);
//		System.out.println(user);
		
	}

}
