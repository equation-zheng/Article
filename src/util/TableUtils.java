package util;

import java.lang.reflect.Field;

import annotation.Column;
import annotation.Table;

public class TableUtils {
	
    public static String getCreateTableSQl(Class<?> clazz){
        StringBuilder sb = new StringBuilder();
        //��ȡ����
        Table table = (Table) clazz.getAnnotation(Table.class);		//��Tableע������ clazz(User)���ע��  �򷵻����ע��
        String tableName = table.tableName();		//ͨ����ȡ��ע�ⷽ�� ��ȡ clazz(User) ���ķ��������ֵ
        //������ݿ��д��� tableName �� ��ô�Ͱ� �� �����ݿ���ɾ��
        sb.append("DROP TABLE IF EXISTS ").append(tableName).append(";\n");
        sb.append("create table ");
        sb.append(tableName).append("(\n");
        
        Field[] fields = clazz.getDeclaredFields();		//��ȡ�������е� ���г�Ա����
        String primaryKey = "";
        
//        System.out.println(fields[2].getAnnotations()[0]);
        
        //���������ֶ�
        for (int i = 0; i < fields.length; i++) {
            Column column = (Column) fields[i].getAnnotations()[0];		//��ȡ���г�Ա������ע��
            
            String field = column.field();	//ȡ��fieldע��ֵ
            String type = column.type();	//ȡ��typeע��ֵ
            boolean defaultNull = column.defaultNull();		//ȡ��defaultNullע��ֵ
            
            sb.append("\t" + field).append(" ").append(type);
            if(defaultNull){
            	
                if(type.toUpperCase().equals("TIMESTAMP")){		//TIMESTAMP��ʾʱ��� ��:TIMESTAMP(14) | YYYYMMDDHHMMSS
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
        
        //ɾ������sql���  ���column.primaryKey() Ϊfalse����� �� ����
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