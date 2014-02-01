package com.example.yilu_counter;

import java.util.ArrayList;
import java.util.Date;

public class CounterModel{
	/* counter name */
	private String name;
	/* total number of counts */
	private int count;
	/* dates clicked for the counter */
	private ArrayList<Date> dateList;

	/* initialize the counter model */
	public CounterModel(String input) {
		// TODO Auto-generated constructor stub
		name = input;
		count = 0;
		dateList = new ArrayList<Date>();
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
	
	public ArrayList<Date> dateList() {
		return dateList;
	}
	
	public void clearDate() {
		dateList.clear();
	}
	
	public String readable()
	{
		return name + "\t     Count:" + count;
	}

	public void sortCounterList() {
		
	}
}
