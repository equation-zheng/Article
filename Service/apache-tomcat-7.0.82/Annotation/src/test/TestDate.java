package test;

import java.lang.reflect.Method;
import java.util.Date;

import annotation.MethodNote;
import util.DateUtil;

public class TestDate {

	public static void main(String args[]) {
		Date now = new Date();
		System.out.println(now);
		System.out.println(DateUtil.formatDate(now, "yyyy-MM-dd hh:mm:ss"));
		
		Class classOfDateUtil = DateUtil.class;
		try {								// 获取对应类中指定的公共成员方法.
			Method formatDate = classOfDateUtil.getMethod("formatDate", Date.class, String.class);
											// 如果存在该元素的指定类型的注释，则返回这些注释
			MethodNote mothodNote = formatDate.getAnnotation(MethodNote.class);
			
			System.out.println("方法描述: "+mothodNote.description());
			System.out.println("创建日期: "+mothodNote.createTime());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
