package com.yust.java_project;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CalendarPad extends JFrame implements MouseListener {
	int year,month,day;
	Hashtable hashtable;
	File file;
	JTextField showDay[];
	JLabel title[];
	Calendar calendar;//日历
	int week_num;//
	NotePad notepad = null;
	Month month_num;//负责改变月
	Year year_num;//负责改变年
	String week[] = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
	JPanel leftPanel,rightPanel;
	public CalendarPad(int year,int month,int day) {
		super("万年历");
		
		leftPanel = new JPanel();
		JPanel leftCenter = new JPanel();
		JPanel leftNorth = new JPanel();
		leftCenter.setLayout(new GridLayout(7,7));
		rightPanel = new JPanel();
		
		this.year = year;
		this.month = month;
		this.day = day;
		
		year_num = new Year(this);
		year_num.setYear(year);
		month_num = new Month(this);
		month_num.setMonth(month);
		title = new JLabel[7];
		showDay = new JTextField[42];
		for(int j=0;j<7;j++) {
			title[j] = new JLabel();
			title[j].setText(week[j]);
			title[j].setBorder(BorderFactory.createRaisedBevelBorder());
			leftCenter.add(title[j]);
		}
		title[0].setForeground(Color.red);
		title[6].setForeground(Color.blue);
		
		for(int i=0;i<42;i++) {
			showDay[i] = new JTextField();
			showDay[i].addMouseListener(this);
			showDay[i].setEditable(false);
			leftCenter.add(showDay[i]);
		}
		calendar = Calendar.getInstance();
		Box box = Box.createHorizontalBox();
		box.add(year_num);
		box.add(month_num);
		leftNorth.add(box);
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(leftNorth,BorderLayout.NORTH);
		leftPanel.add(leftCenter,BorderLayout.CENTER);
		leftPanel.add(new Clock(this),BorderLayout.SOUTH);
		leftPanel.validate();
		Container con = getContentPane();
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftPanel,rightPanel);
		con.add(split, BorderLayout.CENTER);
		con.validate();
		
		hashtable = new Hashtable();
		file = new File("日历记事本.txt");
		if(!file.exists()) {
			try {
				FileOutputStream out = new FileOutputStream(file);
				ObjectOutputStream objectOut = new ObjectOutputStream(out);
				objectOut.writeObject(hashtable);
				objectOut.close();
				out.close();
			}catch(IOException e) {
			}
		}
		notepad = new NotePad(this);
		rightPanel.add(notepad);
		
		setTheCalendar(year,month);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);
		setBounds(150,50,624,285);
		validate();
	}
	public void setTheCalendar(int year_1,int month_1) {
		calendar.set(year_1, month_1-1,1);
		week_num = calendar.get(Calendar.DAY_OF_WEEK)-1;
		if(month_1==1||month_1==3||month_1==5||month_1==7||month_1==8||month_1==10||month_1==12) {
			sortNumber(week_num,31);
		}else if(month_1==4||month_1==6||month_1==9||month_1==11) {
			sortNumber(week_num,30);
		}else if(month_1==2) {
			if((year_1%4==0&&year_1%100!=0)||(year_1%400==0)) {
				sortNumber(week_num,29);
			}else {
				sortNumber(week_num,28);
			}
		}
	}
	public void sortNumber(int week_num,int dateOfmonth) {
		for(int i=week_num,n=1;i<week_num+dateOfmonth;i++) {
			showDay[i].setText(""+n);
			if(n==day) {
				showDay[i].setForeground(Color.green);
				showDay[i].setFont(new Font("TimesRoman",Font.BOLD,20));
			}else {
				showDay[i].setFont(new Font("TimesRoman",Font.BOLD,12));
				showDay[i].setForeground(Color.black);
			}
			if(i%7==6) {
				showDay[i].setForeground(Color.blue);
			}
			if(i%7==0) {
				showDay[i].setForeground(Color.red);
			}
			n++;
		}
		for(int i=0;i<week_num;i++) {
			showDay[i].setText("");
		}
		for(int i=week_num+dateOfmonth;i<42;i++) {
			showDay[i].setText("");
		}
	}
	public int getYear() {
		return year;
	}
	public void setYear(int y) {
		year = y;
		notepad.setYear(year);
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int m) {
		month = m;
		notepad.setMonth(month);
	}
	public int getDay() {
		return day;
	}
	public void setDay(int d) {
		day = d;
		notepad.setDay(day);
	}
	public Hashtable getHashtable() {
		return hashtable;
	}
	public File getFile() {
		return file;
	}	
	public void mousePressed(MouseEvent e) {
		JTextField source = (JTextField)e.getSource();
		try {
			day = Integer.parseInt(source.getText());
			notepad.setDay(day);
			notepad.setMessage(year, month, day);
			notepad.setTheText(null);
			notepad.getTheText(year, month, day);
		}catch(Exception ee) {
		}
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) { 	
	}
	public void mouseEntered(MouseEvent e) {	
	}
	public void mouseExited(MouseEvent e) {	
	}
}