package com.labwinner.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeContainsUtil {
	
	public static List<Date> findDates(Date dBegin, Date dEnd) throws ParseException {
		List<Date> lDate = new ArrayList<Date>();
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
		dBegin=time.parse(time.format(dBegin));
		dEnd=time.parse(time.format(dEnd));
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
		// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
		calBegin.add(Calendar.DAY_OF_MONTH, 1);
		lDate.add(calBegin.getTime());
		}
		return lDate;
		}
	
	

}
