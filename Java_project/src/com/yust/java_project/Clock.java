package com.yust.java_project;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class Clock extends Canvas implements Runnable{
	CalendarPad cp;
	Thread t;
	String time;
	Clock(CalendarPad cp){
		this.cp = cp;
		setSize(400,40);
		t = new Thread(this);
		t.start();
	}
	public void run() {
		while(true) {
			try {
				t.sleep(1000);
			}catch(InterruptedException e) {
				System.out.println("Error");
			}
			this.repaint(100);
		}
	}
	public void paint(Graphics g) {
		Font f = new Font("宋体",Font.BOLD,16);
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy'年'MM'月'dd'日'HH:mm:ss");
		Calendar now = Calendar.getInstance();
		time = SDF.format(now.getTime());
		g.setFont(f);
		g.setColor(Color.red);
		g.drawString(time, 85, 25);
	}
	
}