package com.example.yilu_counter;

import java.util.ArrayList;
import java.util.Date;


public class CounterModel{
	
	/* counter name */
	private String name;
	/* dates clicked for the counter */
	private ArrayList<Date> dateList;
	/* total number of counts */
	private int count;

	/* initialize the counter model */
	public CounterModel(String input) {
		// TODO Auto-generated constructor stub
		this.name = input;
		this.count = 0;
		this.dateList = new ArrayList<Date>();
	}
	
	public int readCount() {
		return count;
	}
	
	public int writeCount(int newCount) {
		count = newCount;
		return count;
	}
	
	public String readName() {
		return name;
	}
	
	public void reName(String input) {
		name = input;
	}
	
	public void appendDate() {
		dateList.add(new Date());
	}
	
	public ArrayList<Date> readDateList() {
		return dateList;
	}
	
	public void clearDate() {
		dateList.clear();
	}
	
	public void writeDate(ArrayList<Date> newDateList) {
		System.out.println("size:      " + newDateList.size());
		for (int i = 0; i < newDateList.size(); i++) {
			dateList.add(newDateList.get(i));
		}
	}
	
	public String readable()
	{
		return name + "\t     Count:" + count;
	}
	
	public String toString() {
		String re = this.name;
		int a = 15 - this.name.length();
		String bla = "\t";
//		System.out.println("    " + a + "    ");
		for (int i = 0; i < a; i++) {
			re = re + bla;
		}
		return re + "  " + this.count;
	}

}
