package com.example.yilu_counter;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class CounterListActivity extends Activity {

	private ListView listView;
	private ArrayList<CounterModel> counterList;
	private ArrayAdapter<CounterModel> adapter;
	private DataHandler dataHandler = new DataHandler(this);
	private String input;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counter_list);
		
		listView=(ListView)findViewById(R.id.listView);
		listView.setOnItemClickListener(new listOnItemClickListener());
		
	}
	
	class listOnItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			input = counterList.get(arg2).readName();
			Intent intent = new Intent(CounterListActivity.this, CounterActivity.class);
			intent.putExtra("name", input);
			startActivity(intent);
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		counterList = dataHandler.loadFromFile();
		adapter = new ArrayAdapter<CounterModel>(this,R.layout.single_view,counterList);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counter_list, menu);
		return true;
	}

}
