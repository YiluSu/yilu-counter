package com.example.yilu_counter;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Stats {
	
	private ArrayList<Date> dateList;
	private ArrayList<String> summaryList = new ArrayList<String>();
//	private ArrayList<CounterModel> counterList;
//	private DataHandler dataHandler = new DataHandler(this);
	private int hour, month, day, year;
//	private int hour2, month2, day2, year2;
	private String[] mon = {"Jan", "Feb", "Mar", "Apr", 
			"May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
	private String time;
	private int num;
	public ArrayList<String> statistics (String input, ArrayList<CounterModel> counterList) {
		System.out.println("Stats11");
		
		
		System.out.println("here?");
		for (int i = 0; i < counterList.size(); i++) {
			if (counterList.get(i).readName().equals(input)) {
				dateList = counterList.get(i).readDateList();
    			break;
    		}
		}
		System.out.println("Stats2");
		if (dateList.size() == 0) {
			return summaryList;
		}
		System.out.println("Stats3");
		
		sortDateList(dateList);
		
		System.out.println("Stats4");
		statsByHour(dateList);
		statsByDay(dateList);
		System.out.println("Stats5");
		statsByWeek(dateList);
		System.out.println("Stats6");
		statsByMonth(dateList);

		System.out.println("Stats7");
		return summaryList;
		
	}
	
	private void statsByHour (ArrayList<Date> dateList) {
		System.out.println("in statsByHour");
		summaryList.add("Count Per Hour:");
		System.out.println("first add");
		hourIniHelper(0);

		for (int i = 1; i < dateList.size(); i++) {
			if ( hourCmpHelper(i)) {
				num ++;
			} else {
				summaryList.add(time + num);
				hourIniHelper(i);
			}
		}
		summaryList.add(time + num);
		System.out.println("end statsByHour");
	}
	
	@SuppressWarnings("deprecation")
	private void hourIniHelper (int a) {
		System.out.println("in hourIniHelper");
		hour = dateList.get(a).getHours();
		day = dateList.get(a).getDate();
		month = dateList.get(a).getMonth();
		year = dateList.get(a).getYear();
		
		if (hour < 12) {
			time = "   " + mon[month-1] + " " + day + " " + hour + ":00AM -- ";
		} else if (hour > 12) {
			time = "   " + mon[month-1] + " " + day + " " + (hour-12) + ":00PM -- ";
		} else if (hour == 12){
			time = "   " + mon[month-1] + " " + day + " " + "12:00PM -- ";
		} else {
			time = "   " + mon[month-1] + " " + day + " " + "12:00AM -- ";
		}
		num = 1;
		System.out.println("out hourIniHelper");
	}
	
	@SuppressWarnings("deprecation")
	private Boolean hourCmpHelper (int a) {
		System.out.println("in hourCmpHelper");
		if (hour == dateList.get(a).getHours() 
				&& day == dateList.get(a).getDate()
				&& month == dateList.get(a).getMonth() 
				&& year == dateList.get(a).getYear())
			return true;
		else 
			return false;
	}
	
	private void statsByDay (ArrayList<Date> dateList) {
		summaryList.add("Count Per Day:");
		
		dayIniHelper(0);

		for (int i = 1; i < dateList.size(); i++) {
			if ( dayCmpHelper(i)) {
				num ++;
			} else {
				summaryList.add(time + num);
				dayIniHelper(i);
			}
		}
		summaryList.add(time + num);
	}
	
	@SuppressWarnings("deprecation")
	private void dayIniHelper (int a) {
		day = dateList.get(a).getDate();
		month = dateList.get(a).getMonth();
		year = dateList.get(a).getYear();
		time = "   " + mon[month-1] + " " + day + " -- ";
		num = 1;
	}
	
	@SuppressWarnings("deprecation")
	private Boolean dayCmpHelper (int a) {
		if (day == dateList.get(a).getDate() 
				&& month == dateList.get(a).getMonth() 
				&& year == dateList.get(a).getYear())
			return true;
		else 
			return false;
	}
	
	@SuppressWarnings("deprecation")
	private void statsByWeek (ArrayList<Date> dateList) {
//		summaryList.add("Count Per Week:");
//		
//		ArrayList<String> sdf = simpleDaateConvert(dateList);
//		
//		
//		int weekdate = dateList.get(0).getDay();
//		int rmn = 8 - weekdate;
//		
//		int a = dateList.get(0).;
//		long a = sdf.parse(dateList.get(0)).getTime();
//		long b = sdf.parse(bb).getTime();
//		
//		int julianDay = (int) ((a - b) / (1000*60*60*24)+1);
//
//		System.out.println(aa + " - " + bb + " = " + julianDay + " days");
//		
//		
//		
//		
////		if (dateList.size() == 0)
////			return;
////		int weekdate = dateList.get(0).getDay();
////		int rmn = 8 - weekdate;
////		SimpleDateFormat sdf = new SimpleDateFormat(dateList.get(0));
////		
////		
////		for (int i = 0; i < dateList.size(); i++) {
////			
////		}
		
		
	}
	
	@SuppressWarnings("deprecation")
	private ArrayList<String> simpleDaateConvert(ArrayList<Date> dateList) {
		// TODO Auto-generated method stub
		ArrayList<String> sdf = new ArrayList<String> () ;
		int yyyy, MM, dd;
		for (int i = 0; i < dateList.size(); i++) {
			yyyy = dateList.get(i).getYear();
			MM = dateList.get(i).getMonth();
			dd = dateList.get(i).getDate();
			sdf.add(yyyy+"-"+MM+"-"+dd);
		}
		
		return sdf;
	}

	private void statsByMonth (ArrayList<Date> dateList) {
		summaryList.add("Count Per Month:");
		
		monthIniHelper(0);

		for (int i = 1; i < dateList.size(); i++) {
			if ( monthCmpHelper(i)) {
				num ++;
			} else {
				summaryList.add(time + num);
				monthIniHelper(i);
			}
		}
		summaryList.add(time + num);
	}
	
	@SuppressWarnings("deprecation")
	private void monthIniHelper (int a) {
		month = dateList.get(a).getMonth();
		year = dateList.get(a).getYear();
		time = "   Month of " + mon[month-1] + " -- ";
		num = 1;
	}
	
	@SuppressWarnings("deprecation")
	private Boolean monthCmpHelper (int a) {
		if (month == dateList.get(a).getMonth() 
				&& year == dateList.get(a).getYear())
			return true;
		else 
			return false;
	}
	
	/* sort the dateList in an increasing order */
	private void sortDateList(ArrayList<Date> oldDateList) {
		int min ;
		for (int i = 0; i < oldDateList.size(); i++) {
			min = i;
			for (int j = i+1; j < oldDateList.size(); j++) {
				if (oldDateList.get(j).before(oldDateList.get(min))) {
					min = j;
				}
			}
			if (min != i) {
				swapDate(oldDateList, i, min);
			}
		}
	}
	
	/* swap two element of the ArrayList */
	private void swapDate (ArrayList<Date> oldDateList, int i, int j) {
		Date temp = oldDateList.get(i);
		oldDateList.set(i, oldDateList.get(j));
		oldDateList.set(j, temp);
	}




}
