package com.yust.java_project;

import java.util.Calendar;

public class MainCalendar {
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		//获取本地的年月日
		int y = calendar.get(Calendar.YEAR);
		int m = calendar.get(Calendar.MONTH)+1;
		int d = calendar.get(Calendar.DAY_OF_MONTH);
		new CalendarPad(y,m,d);
	}
}
