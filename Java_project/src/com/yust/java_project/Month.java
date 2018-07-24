package com.yust.java_project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Month extends Box implements ActionListener {
	int month;
	JTextField showMonth = null;
	JButton nextMonth,lastMonth;
	CalendarPad calendar;
	public Month(CalendarPad calendar) {
		super(BoxLayout.X_AXIS);
		this.calendar = calendar;
		showMonth = new JTextField(2);
		showMonth.setBackground(Color.white);
		month = calendar.getMonth();
		showMonth.setEditable(false);
		showMonth.setForeground(Color.blue);
		showMonth.setFont(new Font("TimesRoman",Font.BOLD,16));
		lastMonth = new JButton("上一月");
		nextMonth = new JButton("下一月");
		add(lastMonth);
		add(showMonth);
		add(nextMonth);
		showMonth.addActionListener(this);
		lastMonth.addActionListener(this);
		nextMonth.addActionListener(this);
		showMonth.setText(""+month);
	}
	public void setMonth(int month) {
		if(month<=12&&month>=1) {
			this.month = month;
		}else {
			this.month = 1;
		}
		showMonth.setText(""+month);
	}
	public int getMonth() {
		return month;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==lastMonth) {
			if(month>=2) {
				month = month-1;
				calendar.setMonth(month);
				calendar.setTheCalendar(calendar.getYear(),month);
			}else if(month==1) {
				month=12;
				calendar.setMonth(month);
				calendar.setTheCalendar(calendar.getYear(),month);
			}
			showMonth.setText(""+month);
		}else if(e.getSource()==nextMonth) {
			if(month<12) {
				month = month+1;
				calendar.setMonth(month);
				calendar.setTheCalendar(calendar.getYear(),month);
			}else if(month==12){
				month = 1;
				calendar.setMonth(month);
				calendar.setTheCalendar(calendar.getYear(), month);
			}
			showMonth.setText(""+month);
		}
	}
}
