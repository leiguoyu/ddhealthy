package com.dd.medication.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * ����ʱ���ʽ�ַ���ת��Ϊʱ�� yyyy-MM-dd HH:mm:ss
	 *
	 */
	public static String getStringDate(Long date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);

		return dateString;
	}

	// ������תΪ�ַ���
	public static String ConverToString(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	// ���ַ���תΪ����
	public static Date ConverToDate(String strDate) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy��MM��dd��");
		return df.parse(strDate);
	}

	/**
	 * �õ�ϵͳ��ǰ���ڵ�ǰ���ߺ���
	 *
	 * @param iDate
	 *            ���Ҫ���ǰ�������ڣ��ò���Ϊ������ ���Ҫ��ú������ڣ��ò���Ϊ����
	 * @see java.util.Calendar#add(int, int)
	 * @return Date ����ϵͳ��ǰ���ڵ�ǰ���ߺ���
	 */
	public static Date getDateBeforeOrAfter(int iDate) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, iDate);
		return cal.getTime();
	}

	/**
	 * �õ����ڵ�ǰ���ߺ���
	 *
	 * @param iDate
	 *            ���Ҫ���ǰ�������ڣ��ò���Ϊ������ ���Ҫ��ú������ڣ��ò���Ϊ����
	 * @see java.util.Calendar#add(int, int)
	 * @return Date ���ز���<code>curDate</code>�������ڵ�ǰ���ߺ���
	 */
	public static Date getDateBeforeOrAfter(Date curDate, int iDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		cal.add(Calendar.DAY_OF_MONTH, iDate);
		return cal.getTime();
	}

	/**
	 * �Ƚ���������֮��Ĵ�С
	 * 
	 * @param d1
	 * @param d2
	 * @return ǰ�ߴ��ں��߷���true ��֮false
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
	 * ��ȡϵͳ��ǰ��������
	 * **/
	public static String getIndexYearMonthDate(Date curDate) {
		// ��ʼʱ��Ĭ��Ϊ��ǰϵͳ����
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd��");
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * ��ȡϵͳ��ǰ��������
	 * **/
	public static String getYearMonthDate() {
		// ��ʼʱ��Ĭ��Ϊ��ǰϵͳ����
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd��");
		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * ��ȡϵͳ��ǰ������
	 * **/
	public static String getMonthDate() {
		// ��ʼʱ��Ĭ��Ϊ��ǰϵͳ����
		SimpleDateFormat formatter = new SimpleDateFormat("MM��dd��");
		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * ��ȡϵͳ��ǰ��ʱ��
	 * **/
	public static String getHourMinute() {
		// ��ʼʱ��Ĭ��Ϊ��ǰϵͳ����
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * ��ȡϵͳ��ǰ��ʱ��
	 * **/
	public static String getDateTime() {
		// ��ʼʱ��Ĭ��Ϊ��ǰϵͳ����
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd�� HHʱmm��");
		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * �Ƚ���������֮��Ĵ�С
	 * 
	 * @param d1
	 * @param d2
	 * @return ǰ�ߴ��ں��߷���true ��֮false
	 */
	public static boolean compareDate(String str1, String str2) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("HH:mm");
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		try {
			c1.setTime(df.parse(str1));
			c2.setTime(df.parse(str2));
		} catch (java.text.ParseException e) {
			System.err.println("��ʽ����ȷ");
		}
		int result = c1.compareTo(c2);
		if (result == 0)
			return true;
		else if (result < 0)
			// System.out.println("c1С��c2");
			return true;
		else
			System.out.println("c1����c2");
		return false;
	}

	public static int compareYearMonthDate(String str1, String str2) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy��MM��dd��");
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		try {
			c1.setTime(df.parse(str1));
			c2.setTime(df.parse(str2));

			int result = c1.compareTo(c2);
			if (result == 0) {
				System.out.println("c1����c2");
				return 0;
			}else if (result < 0) {
				System.out.println("c1С��c2");
				return -1;
			}else {
				System.out.println("c1����c2");
			}
		} catch (java.text.ParseException e) {
			System.err.println("��ʽ����ȷ");
		}
		return 1;
	}

	/**
	 * ��ȡϵͳ��ǰ��ʱ��
	 * **/
	public static String getCurrentHourMinute(long date) {
		// ��ʼʱ��Ĭ��Ϊ��ǰϵͳ����
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		String str = formatter.format(date);// ��ȡ��ǰʱ��
		return str;
	}

	/**
	 * ��ȡϵͳ��ǰ������
	 * **/
	public static String getYearMonth() {
		// ��ʼʱ��Ĭ��Ϊ��ǰϵͳ����
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��");
		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * ��ȡϵͳ��ǰ������
	 * **/
	public static String getYearMonth(Date date) {
		// ��ʼʱ��Ĭ��Ϊ��ǰϵͳ����
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��");
		String str = formatter.format(date);
		return str;
	}

	public static String getDay(Date indexDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String str = formatter.format(indexDate);
		return str;
	}

}
