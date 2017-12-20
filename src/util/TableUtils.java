package util;

import java.lang.reflect.Field;

import annotation.Column;
import annotation.Table;

public class TableUtils {
	
    public static String getCreateTableSQl(Class<?> clazz){
        StringBuilder sb = new StringBuilder();
        //获取表名
        Table table = (Table) clazz.getAnnotation(Table.class);		//若Table注解中有 clazz(User)里的注解  则返回这个注解
        String tableName = table.tableName();		//通过获取的注解方法 获取 clazz(User) 给改方法赋予的值
        //如果数据库中存在 tableName 表 那么就把 它 从数据库中删除
        sb.append("DROP TABLE IF EXISTS ").append(tableName).append(";\n");
        sb.append("create table ");
        sb.append(tableName).append("(\n");
        
        Field[] fields = clazz.getDeclaredFields();		//获取传入类中的 所有成员变量
        String primaryKey = "";
        
//        System.out.println(fields[2].getAnnotations()[0]);
        
        //遍历所有字段
        for (int i = 0; i < fields.length; i++) {
            Column column = (Column) fields[i].getAnnotations()[0];		//获取所有成员变量的注解
            
            String field = column.field();	//取出field注解值
            String type = column.type();	//取出type注解值
            boolean defaultNull = column.defaultNull();		//取出defaultNull注解值
            
            sb.append("\t" + field).append(" ").append(type);
            if(defaultNull){
            	
                if(type.toUpperCase().equals("TIMESTAMP")){		//TIMESTAMP表示时间戳 如:TIMESTAMP(14) | YYYYMMDDHHMMSS
                    sb.append(",\n");
                }else{
                    sb.append(" DEFAULT NULL,\n");
                }
                
            }else{
                sb.append(" NOT NULL,\n");
                
                if(column.primaryKey()){
                    primaryKey = "PRIMARY KEY ("+field+")";
                }
                
            }
        }
        
        //删除生成sql语句  这个column.primaryKey() 为false情况下 的 逗号
//        Column column = (Column) fields[fields.length-1].getAnnotations()[0];
//        if(!column.primaryKey()){
//            sb.deleteCharAt(sb.length()-2);
//            System.out.println("delete");
//        }
        
        
        if(!StringUtils.isEmpty(primaryKey)){
            sb.append("\t").append(primaryKey);
        }
        
        sb.append("\n) DEFAULT CHARSET =utf8");
        
        return sb.toString();
    }
    
}