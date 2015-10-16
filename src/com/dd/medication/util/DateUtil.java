package com.dd.medication.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 *
	 */
	public static String getStringDate(Long date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);

		return dateString;
	}

	// 把日期转为字符串
	public static String ConverToString(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	// 把字符串转为日期
	public static Date ConverToDate(String strDate) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		return df.parse(strDate);
	}

	/**
	 * 得到系统当前日期的前或者后几天
	 *
	 * @param iDate
	 *            如果要获得前几天日期，该参数为负数； 如果要获得后几天日期，该参数为正数
	 * @see java.util.Calendar#add(int, int)
	 * @return Date 返回系统当前日期的前或者后几天
	 */
	public static Date getDateBeforeOrAfter(int iDate) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, iDate);
		return cal.getTime();
	}

	/**
	 * 得到日期的前或者后几天
	 *
	 * @param iDate
	 *            如果要获得前几天日期，该参数为负数； 如果要获得后几天日期，该参数为正数
	 * @see java.util.Calendar#add(int, int)
	 * @return Date 返回参数<code>curDate</code>定义日期的前或者后几天
	 */
	public static Date getDateBeforeOrAfter(Date curDate, int iDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		cal.add(Calendar.DAY_OF_MONTH, iDate);
		return cal.getTime();
	}

	/**
	 * 比较两个日期之间的大小
	 * 
	 * @param d1
	 * @param d2
	 * @return 前者大于后者返回true 反之false
	 */
	public static boolean compareDate(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);

		int result = c1.compareTo(c2);
		if (result >= 0)
			return true;
		else
			return false;
	}

	/**
	 * 获取系统当前的年月日
	 * **/
	public static String getIndexYearMonthDate(Date curDate) {
		// 开始时间默认为当前系统日期
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * 获取系统当前的年月日
	 * **/
	public static String getIndexYearMonthDate1(Date curDate) {
		// 开始时间默认为当前系统日期
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * 获取系统当前的年月日
	 * **/
	public static String getYearMonthDate() {
		// 开始时间默认为当前系统日期
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * 获取系统当前的月日
	 * **/
	public static String getMonthDate() {
		// 开始时间默认为当前系统日期
		SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * 获取系统当前的时分
	 * **/
	public static String getHourMinute() {
		// 开始时间默认为当前系统日期
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * 这里当作当前提醒闹钟的id
	 * **/
	public static int getyyyyMMddHHmm() {
		Calendar cal = Calendar.getInstance();
		String year = cal.get(Calendar.YEAR)+"";//获取年份
		String month=cal.get(Calendar.MONTH)+"";//获取月份
		String day=cal.get(Calendar.DATE)+"";//获取日
		String hour=cal.get(Calendar.HOUR)+"";//小时
		String minute=cal.get(Calendar.MINUTE)+"";//分  
		String str=/*year+*/month+day+hour+minute;
		return Integer.valueOf(str);
	}

	/**
	 * 获取系统当前的时分
	 * **/
	public static String getDateTime() {
		// 开始时间默认为当前系统日期
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * 比较两个日期之间的大小
	 * 
	 * @param d1
	 * @param d2
	 * @return 前者大于后者返回true 反之false
	 */
	public static boolean compareDate(String str1, String str2) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("HH:mm");
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		try {
			c1.setTime(df.parse(str1));
			c2.setTime(df.parse(str2));
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		int result = c1.compareTo(c2);
		if (result == 0)
			return true;
		else if (result < 0){
			System.out.println("c1小于c2");
			return true;
		}else
			System.out.println("c1大于c2");
		return false;
	}

	public static int compareYearMonthDate(String str1, String str2) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy年MM月dd日");
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		try {
			c1.setTime(df.parse(str1));
			c2.setTime(df.parse(str2));

			int result = c1.compareTo(c2);
			if (result == 0) {
				System.out.println("c1等于c2");
				return 0;
			} else if (result < 0) {
				System.out.println("c1小于c2");
				return -1;
			} else {
				System.out.println("c1大于c2");
			}
		} catch (java.text.ParseException e) {
			System.err.println("格式不正确");
		}
		return 1;
	}

	/**
	 * 获取系统当前的时分
	 * **/
	public static String getCurrentHourMinute(long date) {
		// 开始时间默认为当前系统日期
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		String str = formatter.format(date);// 获取当前时间
		return str;
	}

	/**
	 * 获取系统当前的年月
	 * **/
	public static String getYearMonth() {
		// 开始时间默认为当前系统日期
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * 获取系统当前的年月
	 * **/
	public static String getYearMonth(Date date) {
		// 开始时间默认为当前系统日期
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
		String str = formatter.format(date);
		return str;
	}

	public static String getDay(Date indexDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String str = formatter.format(indexDate);
		return str;
	}

	/**
	 * <pre>
	 * 根据指定的日期字符串获取星期几
	 * </pre>
	 * 
	 * @param strDate
	 *            指定的日期字符串(yyyy-MM-dd 或 yyyy/MM/dd)
	 * @return week
	 *         星期几(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY)
	 */
	public static String getWeekByDateStr(String strDate) {
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(5, 7));
		int day = Integer.parseInt(strDate.substring(8, 10));

		Calendar c = Calendar.getInstance();

		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);

		String week = "";
		int weekIndex = c.get(Calendar.DAY_OF_WEEK);

		switch (weekIndex) {
		case 1:
			// week = "SUNDAY";
			week = "星期日";
			break;
		case 2:
			// week = "MONDAY";
			week = "星期一";
			break;
		case 3:
			// week = "TUESDAY";
			week = "星期二";
			break;
		case 4:
			// week = "WEDNESDAY";
			week = "星期三";
			break;
		case 5:
			// week = "THURSDAY";
			week = "星期四";
			break;
		case 6:
			// week = "FRIDAY";
			week = "星期五";
			break;
		case 7:
			// week = "SATURDAY";
			week = "星期六";
			break;
		}
		return week;
	}

}
