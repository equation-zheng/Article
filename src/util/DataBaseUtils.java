package util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DataBaseUtils {

	private static String username;
	private static String password;
	private static String dataBaseName;
	
	//一调用该类的方法 就配置连接信息了
	static {
		config("jdbc.properties");
	}
	//配置数据库的基本信息
	public static void config(String path) {
		//表示字节输入流	超类										     返回该类的类加载器		返回读取指定资源的输入流							
		InputStream inputStream = DataBaseUtils.class.getClassLoader().getResourceAsStream(path);	
		//表示了一个持久的属性集Properties 可保存在流中或从流中加载
		Properties p = new Properties();
		try {
			p.load(inputStream);
			username = p.getProperty("db.username");
			password = p.getProperty("db.password");
			dataBaseName = p.getProperty("db.dataBaseName");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//获取数据库链接
	public static Connection getConnection(){
	    Connection connection = null;
	    try {
	    	//首先加载一个驱动类,之后就可以取得 connection 了
	        Class.forName("com.mysql.jdbc.Driver"); //貌似没有用处    (有用的 会报500 自己挖坑找了半天错)-_-||
	    	
	        //通过DriverManager类的getConnection方法 试图建立到给定数据库URL的连接
	        String url = "jdbc:mysql://localhost:3306/"
	    	+dataBaseName+
	    	"?useUnicode=true&characterEncoding=utf8";
	        connection = DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return connection;		//数据库链接 com.mysql.jdbc.JDBC4Connection@2328c243
	}
	

	
	//关闭资源
	public static void closeConnection(Connection connection,
									   PreparedStatement statement,
									   ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (statement != null) connection.close();
			if (connection != null) connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//DML操作
	public static void update(String sql, Object...objects) {
		//调用上面的方法 获取数据库连接     com.mysql.jdbc.JDBC4Connection@2328c243
		Connection connection = getConnection();
		
		//表示预编译的 SQL 语句的对象,SQL 语句被预编译并存储在 PreparedStatement 对象中
		PreparedStatement statement = null;
		
		try {
			statement = (PreparedStatement) connection.prepareStatement(sql);	//参数化SQL语句
			for (int i = 0; i < objects.length; i++) {
				statement.setObject(i+1, objects[i]);	//使用给定对象设置 指定参数的值
			}
			statement.executeUpdate();		//在此 PreparedStatement 对象中执行 SQL 语句
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection, statement, null);	//关闭流
		}
	}
	
	//查询出数据,并且 list 返回
	public static List<Map<String, Object>> queryForList(String sql, Object...objects){
		
	    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
	    Connection connection = getConnection();		//调用上面的方法 获取数据库连接
	    PreparedStatement statement = null;			//用于存储 预编译的SQL语句
	    ResultSet rs = null;		//存储返回该查询生成的ResultSet对象
	    
	    try {
	    	
	        statement = connection.prepareStatement(sql);	//参数化SQL语句
	        for (int i = 0; i < objects.length; i++) {
	            statement.setObject(i+1, objects[i]);	//给对象设定参数值
	        }
	        
	        rs = statement.executeQuery();		//返回该查询生成的ResultSet对象
	        //System.out.println(rs);
	        while (rs.next()) {			//设置成  第一行成为当前行；第二次调用使第二行成为当前行
	            
	        	ResultSetMetaData resultSetMetaData = rs.getMetaData();		//获取对象的列编号 类型 属性
	            int count = resultSetMetaData.getColumnCount(); 	//获取列数
	            Map<String, Object> map = new HashMap<String, Object>();
	            for (int i = 0; i < count; i++) {
	            	//给map插入 key 列  , values 列值
	                map.put(resultSetMetaData.getColumnName(i+1),
	                		rs.getObject(resultSetMetaData.getColumnName(i+1)));
	            }
	            //再把map放到ArrayList中
	            result.add(map);
	        };
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally{
	        closeConnection(connection, statement, rs);
	    }
	    return result;
	}
		
	//查询出数据,并且map返回
	public static Map<String, Object> queryForMap(String sql, Object...objects) throws SQLException{
	    Map<String, Object> result = new HashMap<String, Object>();
	    //System.out.println("queryForMap方法  1");
	    List<Map<String, Object>> list = queryForList(sql, objects);
	    //System.out.println(list.size());
	    if(list.size() == 0){
	    	return null;
	    }
	    result = list.get(list.size()-1);
	    return result;
	}
	
	//查询出数据,并且返回一个javaBean
	@SuppressWarnings("unchecked")
	public static <T>T queryForBean(String sql, Class<?> clazz, Object...objects){
	    T obj = null;
	    Map<String, Object> map = null;
	    Field field = null;
	    try {
	        obj = (T) clazz.newInstance();   //创建一个新的Bean实例
	        map = queryForMap(sql, objects); //先将结果集放在一个Map中
	    } catch (InstantiationException | IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    if(map == null){
	        return null;
	    }
	    //遍历Map
	    for (String columnName : map.keySet()) {
//	    	System.out.println(columnName);
	        Method method = null;	        
	        String propertyName = StringUtils.columnToProperty(columnName); //返回bean的类(user) 里的所有成员变量
//	        System.out.println(propertyName);	//一堆成员变量\n
	        
	        try {
	            field = clazz.getDeclaredField(propertyName);
//	            System.out.println(field);
	            
	        } catch (NoSuchFieldException e1) {
//	        	System.out.println("Error");
//	            e1.printStackTrace();
	        } catch (SecurityException e1) {
//	            e1.printStackTrace();
	        } 
	        
	        //获取JavaBean中的字段
	        String fieldType = field.toString().split(" ")[1]; //获取该字段的类型
	        //System.out.println(fieldType);
	        Object value = map.get(columnName);
//	        System.out.println(String.valueOf(value)/*value*/);
	        if(value == null){
	            continue;
	        }
	        /**获取set方法的名字* */
	        String setMethodName = "set" + StringUtils.upperCaseFirstCharacter(propertyName);
//	        System.out.println(setMethodName);
	        try {
	            /**获取值的类型* */
	            String valueType = value.getClass().getName();
//	            System.out.println(valueType);
	            /**查看类型是否匹配* */
	            if(!fieldType.equalsIgnoreCase(valueType)){
	                //System.out.println("类型不匹配");
	                if(fieldType.equalsIgnoreCase("java.lang.Integer")){
	                    value = Integer.parseInt(String.valueOf(value));
	                }else if(fieldType.equalsIgnoreCase("java.lang.String")){
	                    value = String.valueOf(value);
	                }else if(fieldType.equalsIgnoreCase("java.util.Date")){ 
	                    valueType = "java.util.Date";
	                    //将value转换成java.util.Date
	                    String dateStr = String.valueOf(value);
	                    Timestamp ts = Timestamp.valueOf(dateStr);
	                    Date date = new Date(ts.getTime());
	                    value = date;
	                }
	            }
	            
	            /**获取set方法* */
	            //System.out.println(valueType);
	            method = clazz.getDeclaredMethod(setMethodName, Class.forName(fieldType));
	            /**执行set方法* */
	            method.invoke(obj, value);
	        }catch(Exception e){
	            e.printStackTrace();
	            /**如果报错，基本上是因为类型不匹配* */
	        }
	    }
//	    System.out.println(obj);
	    return obj;
	}
}



















