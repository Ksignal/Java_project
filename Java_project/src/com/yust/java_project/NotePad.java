package com.yust.java_project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class NotePad extends JPanel implements ActionListener{
	JTextArea text;
	JButton saveText,deleteText;
	Hashtable table;
	JLabel msg;
	int year,month,day;
	File file;
	CalendarPad calendar;
	public NotePad(CalendarPad calendar) {
		this.calendar = calendar;
		year = calendar.getYear();
		month = calendar.getMonth();
		day = calendar.getDay();
		table = calendar.getHashtable();
		file = calendar.getFile();
		msg = new JLabel(""+year+"年"+month+"月"+day+"日",JLabel.CENTER);
		msg.setFont(new Font("TimeRoman",Font.BOLD,16));
		msg.setForeground(Color.blue);
		text = new JTextArea(10,10);
		saveText = new JButton("保存日志");
		deleteText = new JButton("删除日志");
		saveText.addActionListener(this);
		deleteText.addActionListener(this);
		setLayout(new BorderLayout());
		JPanel pSouth = new JPanel();
		add(msg,BorderLayout.NORTH);
		pSouth.add(saveText);
		pSouth.add(deleteText);
		add(pSouth,BorderLayout.SOUTH);
		add(new JScrollPane(text),BorderLayout.CENTER);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==saveText) {
			save_Text(year,month,day);
		}else if(e.getSource()==deleteText) {
			delete_Text(year,month,day);
		}
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getYear() {
		return year;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getMonth() {
		return month;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getDay() {
		return day;
	}
	public void setMessage(int year,int month,int day) {
		msg.setText(""+year+"年"+month+"月"+day+"日");
	}
	public void setTheText(String s) {
		text.setText(s);
	}
	public void getTheText(int year,int month,int day) {
		String key=""+year+""+month+""+day;
		try {
			FileInputStream inOne = new FileInputStream(file);
			ObjectInputStream inTwo = new ObjectInputStream(inOne);
			table = (Hashtable)inTwo.readObject();
			inOne.close();
			inTwo.close();
		}catch(Exception ee) {
		}
		if(table.containsKey(key)) {
			text.setText((String)table.get(key));
		}else {
			text.setText("无记录");
		}
	}
	public void save_Text(int year,int month,int day) {
		String save = text.getText();
		String key = ""+year+""+month+""+day;
		String m = "保存"+year+"年"+month+"月"+day+"日志吗";
		int ok = JOptionPane.showConfirmDialog(this, m,"询问",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(ok == JOptionPane.YES_OPTION) {
			try {
				FileInputStream inOne = new FileInputStream(file);
				ObjectInputStream inTwo = new ObjectInputStream(inOne);
				table = (Hashtable)inTwo.readObject();
				inOne.close();
				inTwo.close();
				table.put(key,save);
				FileOutputStream out = new FileOutputStream(file);
				ObjectOutputStream objectOut = new ObjectOutputStream(out);
				objectOut.writeObject(table);
				objectOut.close();
				out.close();
			}catch(Exception ee) {
			}
		}
	}
	public void delete_Text(int year, int month,int day) {
		String key = ""+year+""+month+""+day;
		if(table.containsKey(key)) {
			String m = "删除"+year+"年"+month+"月"+day+"的日志吗";
			int ok = JOptionPane.showConfirmDialog(this, m,"询问",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(ok == JOptionPane.YES_OPTION) {
				try {
					FileInputStream inOne = new FileInputStream(file);
					ObjectInputStream inTwo = new ObjectInputStream(inOne);
					table = (Hashtable)inTwo.readObject();
					inOne.close();
					inTwo.close();
					table.remove(key);
					FileOutputStream out = new FileOutputStream(file);
					ObjectOutputStream objectOut = new ObjectOutputStream(out);
					objectOut.writeObject(table);
					objectOut.close();
					out.close();
					text.setText(null);
				}catch(Exception ee) {
				}
			}else {
				String message = "删除"+year+"年"+month+"月"+day+"无日志记录";
				JOptionPane.showMessageDialog(this, message, "提示",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
