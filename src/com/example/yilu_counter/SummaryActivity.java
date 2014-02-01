package com.example.yilu_counter;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SummaryActivity extends Activity {

	private ListView listView;
	private ArrayAdapter<String> adapter;
	private ArrayList<String> summaryList;
	private DataHandler dataHandler = new DataHandler(this);
	private ArrayList<CounterModel> counterList;
	private String input;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("SummaryActivity");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
		
		listView=(ListView)findViewById(R.id.summary);
		
		Intent intent = getIntent();
		input = intent.getStringExtra("name");
	}

	
	@Override
	protected void onStart () {
		super.onStart();
		Stats at = new Stats();
		counterList = dataHandler.loadFromFile();
		summaryList = at.statistics(input, counterList);
		adapter = new ArrayAdapter<String>(this,R.layout.single_view,summaryList);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
		return true;
	}

}
