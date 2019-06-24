package com.siruiman.crosslogistics.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;



public class DateUtil {

	// ~ Static fields/initializers
	// =============================================

	private static Logger log = LoggerFactory.getLogger(DateUtil.class);
	private static String defaultDatePattern = null;
	private static String timePattern = "HH:mm";
	public static final String TS_FORMAT = DateUtil.getDatePattern()
			+ " HH:mm:ss.S";
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdf3 = new SimpleDateFormat("EEEE");
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final String PATTERN_DATE = "yyyy-MM-dd";

	// ~ Methods
	// ================================================================

	public DateUtil() {
	}

	/**
	 * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回
	 */
	public static String getDateTime() {
		Calendar cale = Calendar.getInstance();
		try {
			return sdf2.format(cale.getTime());
		} catch (Exception e) {
			log.debug("DateUtil.getDateTime():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得服务器当前日期，以格式为：yyyy-MM-dd的日期字符串形式返回
	 */
	public static String getDate() {
		Calendar cale = Calendar.getInstance();
		try {
			return sdf.format(cale.getTime());
		} catch (Exception e) {
			log.debug("DateUtil.getDate():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得服务器当前时间，以格式为：HH:mm:ss的日期字符串形式返回
	 */
	public static String getTime() {
		Calendar cale = Calendar.getInstance();
		String temp = "";
		try {
			temp += sdf1.format(cale.getTime());
			return temp;
		} catch (Exception e) {
			log.debug("DateUtil.getTime():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 统计时开始日期的默认值, 今年的开始时间
	 */
	public static String getStartDate() {
		try {
			return getYear() + "-01-01";
		} catch (Exception e) {
			log.debug("DateUtil.getStartDate():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 统计时结束日期的默认值
	 */
	public static String getEndDate() {
		try {
			return getDate();
		} catch (Exception e) {
			log.debug("DateUtil.getEndDate():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得服务器当前日期的年份
	 */
	public static String getYear() {
		Calendar cale = Calendar.getInstance();
		try {
			// 返回的int型，需要字符串转换
			return String.valueOf(cale.get(Calendar.YEAR));
		} catch (Exception e) {
			log.debug("DateUtil.getYear():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得服务器当前日期的年份
	 */
	public static String getWeek() {
		Calendar cale = Calendar.getInstance();
		try {
			// 返回的int型，需要字符串转换
			return sdf3.format(cale.getTime());
		} catch (Exception e) {
			log.debug("DateUtil.getYear():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得服务器当前日期的月份
	 */
	public static String getMonth() {
		Calendar cale = Calendar.getInstance();
		try {
			// 一个数字格式，非常好
			java.text.DecimalFormat df = new java.text.DecimalFormat();
			df.applyPattern("00");
			return df.format((cale.get(Calendar.MONTH) + 1));
			// return String.valueOf(cale.get(Calendar.MONTH) + 1);
		} catch (Exception e) {
			log.debug("DateUtil.getMonth():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得服务器在当前月中天数
	 */
	public static String getDay() {
		Calendar cale = Calendar.getInstance();
		try {
			return String.valueOf(cale.get(Calendar.DAY_OF_MONTH));
		} catch (Exception e) {
			log.debug("DateUtil.getDay():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 比较两个日期相差的天数, 第一个日期要比第二个日期要晚
	 */
	public static int getMargin(String date1, String date2) {
		int margin;
		try {
			ParsePosition pos = new ParsePosition(0);
			ParsePosition pos1 = new ParsePosition(0);
			Date dt1 = sdf.parse(date1, pos);
			Date dt2 = sdf.parse(date2, pos1);
			long l = dt1.getTime() - dt2.getTime();
			margin = (int) (l / (24 * 60 * 60 * 1000));
			return margin;
		} catch (Exception e) {
			log.debug("DateUtil.getMargin():" + e.toString());
			return 0;
		}
	}

	/**
	 * 比较两个日期相差的天数，格式不一样 第一个日期要比第二个日期要晚
	 */
	public static double getDoubleMargin(String date1, String date2) {
		double margin;
		try {
			ParsePosition pos = new ParsePosition(0);
			ParsePosition pos1 = new ParsePosition(0);
			Date dt1 = sdf2.parse(date1, pos);
			Date dt2 = sdf2.parse(date2, pos1);
			long l = dt1.getTime() - dt2.getTime();
			margin = (l / (24 * 60 * 60 * 1000.00));
			return margin;
		} catch (Exception e) {
			log.debug("DateUtil.getMargin():" + e.toString());
			return 0;
		}
	}

	/**
	 * 比较两个日期相差的天数，格式不一样 第一个日期要比第二个日期要晚
	 */
	public static double getDoubleHoursMargin(String date1, String date2) {
		double margin;
		try {
			ParsePosition pos = new ParsePosition(0);
			ParsePosition pos1 = new ParsePosition(0);
			Date dt1 = sdf2.parse(date1, pos);
			Date dt2 = sdf2.parse(date2, pos1);
			long l = dt1.getTime() - dt2.getTime();
			margin = (l / (60 * 60 * 1000.00));
			return margin;
		} catch (Exception e) {
			log.debug("DateUtil.getMargin():" + e.toString());
			return 0;
		}
	}

	/**
	 * 比较两个日期相差的月数
	 */
	public static int getMonthMargin(String date1, String date2) {
		int margin;
		try {
			margin = (Integer.parseInt(date2.substring(0, 4)) - Integer
					.parseInt(date1.substring(0, 4))) * 12;
			margin += (Integer.parseInt(date2.substring(4, 7).replaceAll("-0",
					"-")) - Integer.parseInt(date1.substring(4, 7).replaceAll(
					"-0", "-")));
			return margin;
		} catch (Exception e) {
			log.debug("DateUtil.getMargin():" + e.toString());
			return 0;
		}
	}

	/**
	 * 返回日期加X天后的日期
	 */
	public static String addDay(String date, int i) {
		try {
			GregorianCalendar gCal = new GregorianCalendar(
					Integer.parseInt(date.substring(0, 4)),
					Integer.parseInt(date.substring(5, 7)) - 1,
					Integer.parseInt(date.substring(8, 10)));
			gCal.add(GregorianCalendar.DATE, i);
			return sdf.format(gCal.getTime());
		} catch (Exception e) {
			log.debug("DateUtil.addDay():" + e.toString());
			return getDate();
		}
	}

	/**
	 * 返回日期加X月后的日期
	 */
	public static String addMonth(String date, int i) {
		try {
			GregorianCalendar gCal = new GregorianCalendar(
					Integer.parseInt(date.substring(0, 4)),
					Integer.parseInt(date.substring(5, 7)) - 1,
					Integer.parseInt(date.substring(8, 10)));
			gCal.add(GregorianCalendar.MONTH, i);
			return sdf.format(gCal.getTime());
		} catch (Exception e) {
			log.debug("DateUtil.addMonth():" + e.toString());
			return getDate();
		}
	}

	/**
	 * 返回日期加X年后的日期
	 */
	public static String addYear(String date, int i) {
		try {
			GregorianCalendar gCal = new GregorianCalendar(
					Integer.parseInt(date.substring(0, 4)),
					Integer.parseInt(date.substring(5, 7)) - 1,
					Integer.parseInt(date.substring(8, 10)));
			gCal.add(GregorianCalendar.YEAR, i);
			return sdf.format(gCal.getTime());
		} catch (Exception e) {
			log.debug("DateUtil.addYear():" + e.toString());
			return "";
		}
	}

	/**
	 * 返回某年某月中的最大天
	 */
	public static int getMaxDay(String year, String month) {
		int day = 0;
		try {
			int iyear = Integer.parseInt(year);
			int imonth = Integer.parseInt(month);
			if (imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7
					|| imonth == 8 || imonth == 10 || imonth == 12) {
				day = 31;
			} else if (imonth == 4 || imonth == 6 || imonth == 9
					|| imonth == 11) {
				day = 30;
			} else if ((0 == (iyear % 4)) && (0 != (iyear % 100))
					|| (0 == (iyear % 400))) {
				day = 29;
			} else {
				day = 28;
			}
			return day;
		} catch (Exception e) {
			log.debug("DateUtil.getMonthDay():" + e.toString());
			return 1;
		}
	}

	/**
	 * 格式化日期
	 */
	@SuppressWarnings("static-access")
	public String rollDate(String orgDate, int Type, int Span) {
		try {
			String temp = "";
			int iyear, imonth, iday;
			int iPos = 0;
			char seperater = '-';
			if (orgDate == null || orgDate.length() < 6) {
				return "";
			}

			iPos = orgDate.indexOf(seperater);
			if (iPos > 0) {
				iyear = Integer.parseInt(orgDate.substring(0, iPos));
				temp = orgDate.substring(iPos + 1);
			} else {
				iyear = Integer.parseInt(orgDate.substring(0, 4));
				temp = orgDate.substring(4);
			}

			iPos = temp.indexOf(seperater);
			if (iPos > 0) {
				imonth = Integer.parseInt(temp.substring(0, iPos));
				temp = temp.substring(iPos + 1);
			} else {
				imonth = Integer.parseInt(temp.substring(0, 2));
				temp = temp.substring(2);
			}

			imonth--;
			if (imonth < 0 || imonth > 11) {
				imonth = 0;
			}

			iday = Integer.parseInt(temp);
			if (iday < 1 || iday > 31)
				iday = 1;

			Calendar orgcale = Calendar.getInstance();
			orgcale.set(iyear, imonth, iday);
			temp = this.rollDate(orgcale, Type, Span);
			return temp;
		} catch (Exception e) {
			return "";
		}
	}

	public static String rollDate(Calendar cal, int Type, int Span) {
		try {
			String temp = "";
			Calendar rolcale;
			rolcale = cal;
			rolcale.add(Type, Span);
			temp = sdf.format(rolcale.getTime());
			return temp;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 
	 * 返回默认的日期格式
	 * 
	 */
	public static synchronized String getDatePattern() {
		defaultDatePattern = "yyyy-MM-dd";
		return defaultDatePattern;
	}

	/**
	 * 将指定日期按默认格式进行格式代化成字符串后输出如：yyyy-MM-dd
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 取得给定日期的时间字符串，格式为当前默认时间格式
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	/**
	 * 取得当前时间的Calendar日历对象
	 */
	public Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));
		return cal;
	}

	/**
	 * 将日期类转换成指定格式的字符串形式
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * 将指定的日期转换成默认格式的字符串形式
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * 将日期字符串按指定格式转换成日期类型
	 * 
	 * @param aMask
	 *            指定的日期格式，如:yyyy-MM-dd
	 * @param strDate
	 *            待转换的日期字符串
	 */

	public static final Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			log.error("ParseException: " + pe);
			throw pe;
		}
		return (date);
	}

	/**
	 * 将日期字符串按默认格式转换成日期类型
	 */
	public static Date convertStringToDate(String strDate)
			throws ParseException {
		Date aDate = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + getDatePattern());
			}
			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate
					+ "' to a date, throwing exception");
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	/**
	 * 返回一个JAVA简单类型的日期字符串
	 */
	public static String getSimpleDateFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat();
		String NDateTime = formatter.format(new Date());
		return NDateTime;
	}

	/**
	 * 将两个字符串格式的日期进行比较
	 * 
	 * @param last
	 *            要比较的第一个日期字符串
	 * @param now
	 *            要比较的第二个日期格式字符串
	 * @return true(last 在now 日期之前),false(last 在now 日期之后)
	 */
	public static boolean compareTo(String last, String now) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date temp1 = formatter.parse(last);
			Date temp2 = formatter.parse(now);
			if (temp1.after(temp2))
				return false;
			else if (temp1.before(temp2))
				return true;
		} catch (ParseException e) {
			log.debug(e.getMessage());
		}
		return false;
	}



	/**
	 * 取得当前时间的日戳
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getTimestamp() {
		Date date = new Date();
		String timestamp = "" + (date.getYear() + 1900) + date.getMonth()
				+ date.getDate() + date.getMinutes() + date.getSeconds()
				+ date.getTime();
		return timestamp;
	}

	/*
	 * 将10 or 13 位时间戳转为时间字符串
	 * convert the number 1407449951 1407499055617 to date/time format timestamp
	 */
	public static String timestamp2Date(String str_num,String format ) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (str_num.length() == 13) {
			String date = sdf.format(new Date(Long.parseLong(str_num)));
//			LogUtil.debug("timestamp2Date"+ "将13位时间戳:" + str_num + "转化为字符串:", date);
			return date;
		} else {
			String date = sdf.format(new Date(Integer.parseInt(str_num) * 1000L));
//			LogUtil.debug("timestamp2Date" + "将10位时间戳:" + str_num + "转化为字符串:", date);
			return date;
		}
	}

//	/**
//	 * 时间戳转时间(10位时间戳)
//	 * @param time
//	 * @return
//	 */
	public static String timestampToDate(long time) {
//		String dateTime;
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//		long timeLong = Long.valueOf(time);
//		dateTime = simpleDateFormat.format(new Date(timeLong * 1000L));

		long timeStamp = time * 1000;//直接是时间戳
//            long timeStamp = System.currentTimeMillis();  //获取当前时间戳,也可以是你自已给的一个随机的或是别人给你的时间戳(一定是long型的数据)
		System.out.println(timeStamp);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
		String sd = sdf.format(new Date(timeStamp));   // 时间戳转换成时间
		return sd;
	}

//	public static String timestampToDate(String s){
//		String res;
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		long lt = new Long(s);
//		Date date = new Date(lt);
//		res = simpleDateFormat.format(date);
//		return res;
//	}






	/**
	 * 取得指定时间的日戳
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getTimestamp(Date date) {
		String timestamp = "" + (date.getYear() + 1900) + date.getMonth()
				+ date.getDate() + date.getMinutes() + date.getSeconds()
				+ date.getTime();
		return timestamp;
	}

	public static String getMondayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return dateFormat.format(calendar.getTime());
	}

	public static List<String> getQujianDateList(String startDate,
			String endDate) throws ParseException {
		List<String> dateList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date begin = sdf.parse(startDate);
		Date end = sdf.parse(endDate);
		double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		double day = between / (24 * 3600);
		dateList.add(startDate);
		for (int i = 1; i <= day; i++) {

			Calendar cd = Calendar.getInstance();
			cd.setTime(sdf.parse(startDate));
			cd.add(Calendar.DATE, i);// 增加一天
			// cd.add(Calendar.MONTH, n);//增加一个月
//			 System.out.println(sdf.format(cd.getTime()));
			dateList.add(sdf.format(cd.getTime()));

		}
		return dateList;
	}

	public static void main(String[] args) {
		 System.out.println(getDateTime());//获取当年时间月份08
		try {
			getQujianDateList("2015-01-08", "2015-01-09");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt * 1000L);
		res = simpleDateFormat.format(date);
		return res;
	}

	/**
	 * 将时间戳转换为时间
	 */
	public static String stamp13ToDate(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}


	/**
	 * 获取微信支付的开始时间
	 * @return
	 */
	public static String getWXPayStartTime(){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		long lt = System.currentTimeMillis();
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;

	}


	/**
	 * 获取微信支付的订单结束时间
	 * @param s 微信支付订单的时间有效期单位秒
	 * @return yyyyMMddHHmmss
	 */
	public static String getWXPayExpireTime(long s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		long lt = System.currentTimeMillis()+s*1000;
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;

	}

	/**
	 * 获取发送短信所需的时间戳
	 *
	 * @return  MMddHHmmss
	 */
	public static String getMessageTimeStamp(){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddHHmmss");
		long lt = System.currentTimeMillis();
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;

	}


	public static String getmerchantTxnDtm(){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" yyyyMMdd HH:mm:ss.SSS ");
		long lt = System.currentTimeMillis();
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;

	}
}
