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

public class RenameActivity extends Activity {
	private EditText newName;
	private Button renameButton;
	private ArrayList<CounterModel> counterList;
	private DataHandler dataHandler = new DataHandler(this);
	private String old;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rename);
		newName = (EditText)findViewById(R.id.rename);
		renameButton = (Button)findViewById(R.id.renameButton);
		Intent intent = getIntent();
		old = intent.getStringExtra("old");
		
		/* button listener */
		renameButton.setOnClickListener(new renameButtonListener());
	}
	
	class renameButtonListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String input = newName.getText().toString();
			
			/* name validation */
            if (input.equals("")) {
            	Toast.makeText(RenameActivity.this, R.string.please_enter_name,
            			Toast.LENGTH_SHORT).show();
            } else if (input.equals(old)){
            	Toast.makeText(RenameActivity.this, R.string.enter_new_name,
            			Toast.LENGTH_SHORT).show();
            } else {
            	Boolean exist = false;
//            	dataHandler ;
            	counterList = dataHandler.loadFromFile();
            	for (int i=0; i<counterList.size(); i++) {
            		if (counterList.get(i).readName().equals(input)) {
            			exist = true;
            			break;
            		}
            	}
            	if (exist) {
            		Toast.makeText(RenameActivity.this,R.string.exist,Toast.LENGTH_SHORT).show();
            	} else {
            		/*
            		 * change name in database
            		 */
            		Intent intent = new Intent(RenameActivity.this, CounterActivity.class);
            		intent.putExtra("exist", true);
            		intent.putExtra("name", input);
            		startActivity(intent);
            	}
            }
			
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rename, menu);
		return true;
	}

}
