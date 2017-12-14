package util;

import java.text.SimpleDateFormat;
import java.util.Date;

import annotation.MethodNote;

public class DateUtil {
	
	@MethodNote(createTime = "2017-11-21")
	public static String formatDate(Date date, String formatPattern) {
		return new SimpleDateFormat(formatPattern).format(date);
	}
	
}
