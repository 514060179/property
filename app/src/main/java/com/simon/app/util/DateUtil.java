package com.simon.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 比较两个时间的毫秒差
	 * @param date1
	 * @param date2
	 * @param timeType day:日差，huor时差
	 * @return
	 */
    public static int getDifferentMillisecond(Date date1, Date date2, String timeType) {
        double Millisec = 1;
        switch (timeType) {
		case "day": 
			Millisec = (1000*3600*24);
			break;
		case "hour": 
			Millisec = (1000*3600);
			break;
		}
        double differ = ((date2.getTime() - date1.getTime()) / Millisec);
        return (int) Math.ceil(differ);
    }
    
    /**
     * 
     * @param date1
     * @param date2
     * @param date3
     * @param date4
     * @return
     */
    public static boolean timeLimit(Date date1, Date date2, Date date3, Date date4){
    	if (date1.getTime() >= date3.getTime()
        		&& date2.getTime() <= date4.getTime()) {
        		return true;
        }
    	return false;
    }
    /**
     * 判断某一时间是否在某个时间段范围
     * @param startDate	
     * @param endDate
     * @param date
     * @return
     */
    public static boolean timeLimit(Date startDate, Date endDate, Date date) {
    	if (startDate.getTime() < date.getTime()
        		&& endDate.getTime() > date.getTime()) {
        		return true;
        }
    	return false;
    }
    
    public static void main(String[] args) throws ParseException {
		SimpleDateFormat simple = new SimpleDateFormat("hh:mm:ss");
		Date start = simple.parse("02:00:00");
		Date end = simple.parse("03:30:00");
		
		Date date1 = simple.parse("02:00:00");
		Date date2 = simple.parse("04:00:00");
		if(DateUtil.timeLimit(start, end, date1, date2)){
			System.out.println("存在数据1");
			return;
		}else if(DateUtil.timeLimit(start, end, date1)
				|| DateUtil.timeLimit(start, end, date2)){
			System.out.println("存在数据2");
			return;
		}
		
		System.out.println(DateUtil.timeLimit(date1, date2, start));
	}
}
