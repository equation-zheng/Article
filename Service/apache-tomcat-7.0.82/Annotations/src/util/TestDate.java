package util;

import java.lang.reflect.Method;
import java.util.Date;

import annotation.MethodNote;

public class TestDate {

	public static void main(String[] args) throws Exception {
//		Date now = new Date();		//获取当前日期
//		System.out.println(now);
//		System.out.println(DateUtil.formatDate(now, "yyy-MM-dd HH:mm:ss:SS"));
		
		Class classOfDateUtil = DateUtil.class;
		Method formatDate = classOfDateUtil.getMethod("formatDate", Date.class, String.class);
		MethodNote methodNote = formatDate.getAnnotation(MethodNote.class);
		
		System.out.println(methodNote.description());
		System.out.println(methodNote.createTime());
		
	}

}
