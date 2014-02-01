package com.example.yilu_counter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;

import com.google.gson.Gson;

public class DataHandler {

	private static final String FILENAME = "counter961.sav";

	private ArrayList<CounterModel> counterList;
	private Gson gson = new Gson();
	private Context context;

	public DataHandler(Context context)
	{
		this.context = context;
	}

	/* Load counterList from the file */
	public ArrayList<CounterModel> loadFromFile() {
		counterList = new ArrayList<CounterModel> ();
		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			while (line != null) {
				CounterModel json=gson.fromJson(line, CounterModel.class);
				counterList.add(json);
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return counterList;
	}	
	
	/* Save counterList from the file */
	public void saveToFile(ArrayList<CounterModel> newCounterList) {
		sortCounterList(newCounterList);
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
			String json;
			for (int i = 0; i < newCounterList.size(); i++) {
				json = gson.toJson(newCounterList.get(i));
//				System.out.println(json);
				out.write(json+ "\n");
			}
			out.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* sort the counters */
	public void sortCounterList(ArrayList<CounterModel> newCounterList) {
		int max ;
		for (int i = 0; i < newCounterList.size(); i++) {
			max = i;
			for (int j = i+1; j < newCounterList.size(); j++) {
				if (newCounterList.get(max).readCount() < newCounterList.get(j).readCount()) {
					max = j;
				}
			}
			if (max != i) {
				swapCounter(newCounterList, i, max);
			}
		}
	}
	
	/* swap two element of the ArrayList */
	public void swapCounter (ArrayList<CounterModel> newCounterList, int i, int j) {
		CounterModel temp = newCounterList.get(i);
		newCounterList.set(i, newCounterList.get(j));
		newCounterList.set(j, temp);
	}

}