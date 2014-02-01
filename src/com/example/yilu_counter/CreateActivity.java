package com.example.yilu_counter;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends Activity {

	private EditText newName;
	private Button createNewButton;
	private String input;
	private ArrayList<CounterModel> counterList;
	private DataHandler dataHandler = new DataHandler(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		newName = (EditText)findViewById(R.id.newName);
		createNewButton = (Button)findViewById(R.id.createNewButton);
		
		/* button listener */
		createNewButton.setOnClickListener(new createNewButtonListener());
		
	}
	
	class createNewButtonListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			input = newName.getText().toString();
			
			/* name validation */
            if (input.equals("")) {
            	Toast.makeText(CreateActivity.this, R.string.please_enter_name,
            			Toast.LENGTH_SHORT).show();
            } else {
            	/* check if counter already exist */
            	Boolean exist = false;
            	counterList = dataHandler.loadFromFile();
            	for (int i=0; i<counterList.size(); i++) {
            		if (counterList.get(i).readName().equals(input)) {
            			exist = true;
            			break;
            		}
            	}
            	if (exist) {
            		Toast.makeText(CreateActivity.this,R.string.exist,Toast.LENGTH_SHORT).show();
            	} else {
            		/* initialize new counter */
            		CounterModel newCounter = new CounterModel(input);
            		counterList.add(newCounter);
            		dataHandler.saveToFile(counterList);
            		/* initialize intent */
            		Intent intent = new Intent(CreateActivity.this, CounterActivity.class);
            		intent.putExtra("exist", exist);
            		intent.putExtra("name", input);
            		
            		startActivity(intent);
            	}
            	
            }
			
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
		return true;
	}

}
