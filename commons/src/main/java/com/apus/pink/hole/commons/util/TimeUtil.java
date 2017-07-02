package com.apus.pink.hole.commons.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 程序用到的常量类
 * Created by wangmaojun on 2017-04-19.
 */
public final class TimeUtil {
	/*
	 * 默认的时间格式
	 */
	public final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/*
	 * 默认截取自然天时间格式
	 */
	public final static String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd";

	/**
	 * 获得某天的字符串表达
	 * @param time : 日期字符串
	 * @param style : 日期格式，如 yyyy-MM-dd
	 * @param leaveToToday : -n : time的前n天，+n : time的后n天
	 * @return 获得某天的字符串表达
	 */
	public static String getCommonDay(String time, String style, Integer leaveToToday) {
		String defaultStyle = DEFAULT_DATE_FORMAT_PATTERN;
		if (null != style && style.length() > 1) {
			defaultStyle = style;
		}
		Date date = getFormatDate(time, defaultStyle);
		SimpleDateFormat format = new SimpleDateFormat(defaultStyle);
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.set(Calendar.DAY_OF_MONTH, instance.get(instance.DAY_OF_MONTH) + leaveToToday);
		return format.format(instance.getTime());
	}

	/**
	 * 获得某天的字符串表达
	 * @param time : 日期字符串
	 * @param style : 日期格式，如 yyyy-MM-dd
	 * @param leaveToMonth : -n : time的前n月，+n : time的后n月
	 * @return 获得某天的字符串表达
	 */
	public static String getCommonMonth(String time, String style, Integer leaveToMonth) {
		String defaultStyle = DEFAULT_DATE_FORMAT_PATTERN;
		if (null != style && style.length() > 1) {
			defaultStyle = style;
		}
		Date date = getFormatDate(time, defaultStyle);
		SimpleDateFormat format = new SimpleDateFormat(defaultStyle);
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.set(Calendar.MONTH, instance.get(instance.MONTH) + leaveToMonth);
		return format.format(instance.getTime());
	}

	/**
	 * 获得某小时的字符串表达
	 * @param time : 时间字符串
	 * @param style : 时间格式，例如 yyyy-MM-dd HH
	 * @param plus :  -n : time的前n小时，+n : time的后n小时
	 * @return 获得某小时的字符串表达
	 */
	public static String getCommonHour(String time, String style, Integer plus) {
		String defaultStyle = "yyyy-MM-dd HH";
		if (null != style && style.length() > 1) {
			defaultStyle = style;
		}
		Date date = getFormatDate(time, defaultStyle);
		SimpleDateFormat format = new SimpleDateFormat(defaultStyle);
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.set(Calendar.HOUR, instance.get(instance.HOUR) + plus);
		return format.format(instance.getTime());
	}

	/**
	 *
	 * @param time ： 时间
	 * @param pattern : 日期格式
	 * @return Date类型的时间对象
	 */
	public static Date getFormatDate(String time, String pattern) {
		if (null == time || "".equals(time)) {
			return null;
		}
		if (null == pattern || "".equals(pattern)) {
			pattern = TimeUtil.DEFAULT_DATE_PATTERN;
		}
		DateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @param style : 时间格式
	 * @return 满足时间格式的当前时间
	 */
	public static String getNow(String style) {
		String timeStyle = null;
		if (null == style || style.trim().length() == 0) {
			timeStyle = DEFAULT_DATE_PATTERN;
		} else {
			timeStyle = style;
		}
		SimpleDateFormat format = new SimpleDateFormat(timeStyle);
		Calendar instance = Calendar.getInstance();
		return format.format(instance.getTime());
	}

	public static void main(String[] args) {
		String yesterday = TimeUtil.getCommonDay(TimeUtil.getNow("yyyy-MM-dd"),"yyyy-MM-dd",-1);
		System.out.println(yesterday);
	}


}
