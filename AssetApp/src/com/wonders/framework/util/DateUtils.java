package com.wonders.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DateUtils {
	
	public static String Date2String(Date date){
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
			return sdf.format(date);
		}
		return "";
	}
	
	public static Date String2Date(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
		Date currentdate = null;
		try {
			currentdate = sdf.parse(date);
		} catch (ParseException e) {
		}
		return currentdate;
	}
	
	public static Date Date2Date(Date date){
		return String2Date(Date2String(date));
	}
	
	public static String Date2String(Date date, String pattern){
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
			return sdf.format(date);
		}
		return "";
	}
	
	public static Date String2Date(String date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
		Date currentdate = null;
		if(date ==null ||date.trim().equals("")){
		    return null;
		}
		try {
			currentdate = sdf.parse(date);
		} catch (ParseException e) {
		}
		return currentdate;
	}
	
	public static Date Date2Date(Date date, String pattern){
		return String2Date(Date2String(date,pattern),pattern);
	}


	
	/**
	 * 判断是否符合日期规范
	 * @param s
	 * @param pattern
	 * @return
	 */
	public static boolean isValidDate(String s,String pattern)
    {
		 SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		 if(s == null || s.trim().equals("")){
		     return true;
		 }
        try
        {
             return sdf.format(sdf.parse(s)).equals(s);
         }
        catch (Exception e)
        {
            return false;
        }
    }
	
	public static Long StringToLong(String s) {
		if ("".equals(s) || null == s) {
			return null;
		} else {
			return Long.parseLong(s);
		}
	}

	public static Integer StringToInt(String s) {
		if ("".equals(s) || null == s) {
			return null;
		} else {
			return Integer.parseInt(s);
		}
	}
	
	/**
	 * 获得两个日期之间的所有日期
	 */
    public static Date[] getDateArrays(Date start, Date end, int calendarType) {
      List<Date> ret = new ArrayList<Date>();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(start);
      Date tmpDate = calendar.getTime();
      long endTime = end.getTime();
      while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
        ret.add(calendar.getTime());
        calendar.add(calendarType, 1);
        tmpDate = calendar.getTime();
      }
      Date[] dates = new Date[ret.size()];
      return (Date[]) ret.toArray(dates);
    }
    
	/**
	 * 获取明天的日期
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getTomorrow(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date tomorrow = new Date(date.getTime()+1000*60*60*24);
		String strDate = df.format(tomorrow);
		try {
			tomorrow = df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tomorrow;
	}

    /**
	 * 获得今天的00:00分
	 */
	public static Date getZeroOfToday() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获得某一天的00:00分
	 */
	public static Date getZeroOfOneday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获得今天的23:59分
	 */
	public static Date getLatestTimeOfToday() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
	/**
	 * 获得某一天的23:59分
	 */
	public static Date getLatestTimeOfOneday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
}
