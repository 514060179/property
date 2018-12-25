package com.simon.app.util;

import java.util.Date;

public class DateUtil {
	
	/**
	 * 比较两个时间的毫秒差
	 * @param date1
	 * @param date2
	 * @param timeType day:日差，huor时差
	 * @return
	 */
    public static int differentMillisecond(Date date1, Date date2, String timeType)
    {
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
}
