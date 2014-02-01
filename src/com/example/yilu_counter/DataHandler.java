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

import android.content.Context;

import com.google.gson.Gson;

public class DataHandler {

	private static final String FILENAME = "counter997.sav";

	private ArrayList<CounterModel> counterList;
	private Gson gson = new Gson();
	private Context context;

	public DataHandler(Context context)
	{
		this.context = context;
	}

	/* Load counterList from the file */
	public ArrayList<CounterModel> loadFromFile() {
		System.out.println("load00000000000000");
		counterList = new ArrayList<CounterModel> ();
		try {
			System.out.println("load11111111111111");
			FileInputStream fis = context.openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			System.out.println("load2222222222");
			int oo = 0;
			while (line != null) {
				System.out.println(oo+"");
				System.out.println(line + "       linelinelineline");
				oo++;
				System.out.println("hahahaha");
				CounterModel tem = new CounterModel("ppp");
				System.out.println(tem.toString());
				CounterModel json=gson.fromJson(line, CounterModel.class);
				System.out.println("yiyiyiyiyiyiyiyi");
				counterList.add(json);
				System.out.println("ererererererer");
				line = in.readLine();
			}
			System.out.println("load3333333333");
			
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
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
			String json = gson.toJson(newCounterList);
			out.write(json+ "\n");
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
	
	public void sortCounterList() {
		
	}
}