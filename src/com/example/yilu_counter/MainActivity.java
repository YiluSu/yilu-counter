package com.example.yilu_counter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
//import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	/* text view and buttons */
	private Button createButton;
	private Button counterButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/* get the widgets and setText */
		createButton = (Button)findViewById(R.id.createButton);
		counterButton = (Button)findViewById(R.id.counterButton);
		createButton.setText(R.string.button_create);
		counterButton.setText(R.string.button_counters);
		
		createButton.setOnClickListener(new CreateButtonListener());
		counterButton.setOnClickListener(new CounterButtonListener());
		
	}
	
	class CreateButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this, CreateActivity.class);
    		startActivity(intent);
		}

	}
	
	class CounterButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this, CounterListActivity.class);
    		startActivity(intent);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
