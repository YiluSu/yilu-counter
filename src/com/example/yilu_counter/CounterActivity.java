package com.example.yilu_counter;

import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CounterActivity extends Activity {

//	private TextView click;
	private String input;
	private Button click;
	private Button reset;
	private Button rename;
	private Button summary;
	private Button back;
	private ArrayList<CounterModel> counterList;
	private DataHandler dataHandler = new DataHandler(this);
	private int count;
	private int pos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counter);

		click = (Button)findViewById(R.id.click);
		reset = (Button)findViewById(R.id.reset);
		rename = (Button)findViewById(R.id.rename);
		summary = (Button)findViewById(R.id.summary);
		back = (Button)findViewById(R.id.back);
		
		count = 0;
		Intent intent = getIntent();
		input = intent.getStringExtra("name");
		
//		dataHandler ;
		counterList = dataHandler.loadFromFile();
		/* if not new counter, get old count */
		if (intent.getBooleanExtra("exist", true)) {
			System.out.println("old counter");
			for (int i=0; i<counterList.size(); i++) {
				if (counterList.get(i).readName().equals(input)) {
					pos = i;
					count = counterList.get(i).readCount();
					break;
				}
			}
		}
		
		click.setText(input + "\n\n" + count + "\n");
		
		click.setOnClickListener(new ClickButtonListener());
		reset.setOnClickListener(new ResetButtonListener());
		rename.setOnClickListener(new RenameButtonListener());
		summary.setOnClickListener(new SummaryButtonListener());
		back.setOnClickListener(new BackButtonListener());
		System.out.println("CounterActivity " + pos);
	}
	
	class ClickButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			count++;
			/*  save data */
			System.out.println("aaaaaaaaaaaaaa1111111");
			counterList.get(pos).writeCount(count);
			System.out.println("aaaaaaaaaaaaaa2222222");
			counterList.get(pos).appendDate();
			System.out.println("aaaaaaaaaaaaaa3333333");
        	dataHandler.saveToFile(counterList);
        	System.out.println("aaaaaaaaaaaaaa4444444");
			click.setText(input + "\n\n" + count + "\n");
			
			
//			count++;
//			setResult(RESULT_OK);
//			//String lText=(text+ new Date(System.currentTimeMillis())+clicked).toString();
//			DataObject obj = new DataObject(text,new Date(System.currentTimeMillis()),clicked);
//			tweets.add(obj);
//			saveInFile(obj);
//			adapter.notifyDataSetChanged();
		}

	}
	
	class ResetButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			count = 0;
			/* save data */
			counterList.get(pos).writeCount(count);
			counterList.get(pos).appendDate();
        	dataHandler.saveToFile(counterList);
			click.setText(input + "\n\n" + count + "\n");
			
		}

	}
	
	class RenameButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(CounterActivity.this, RenameActivity.class);
    		intent.putExtra("exist", false);
    		intent.putExtra("old", input);
    		startActivity(intent);
		}

	}
	
	class SummaryButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(CounterActivity.this, SummaryActivity.class);
    		intent.putExtra("name", input);
    		startActivity(intent);
		}

	}
	
	class BackButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(CounterActivity.this, MainActivity.class);
    		startActivity(intent);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counter, menu);
		return true;
	}

	/* disable the back key in order to avoid bugs */
	@Override
	public void onBackPressed() {
		Toast.makeText(CounterActivity.this,R.string.warning, Toast.LENGTH_SHORT).show();
	}

}
