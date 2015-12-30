package com.example.edt2;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText et1 = (EditText)findViewById(R.id.et1);
		et1.addTextChangedListener(new TextWatcher() { 
			@Override 
			public void onTextChanged(CharSequence s, int start, int before, int count) { 
			} 

			@Override 
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { 
			} 

			@Override 
			public void afterTextChanged(Editable s) { 
			} 
			});
		
	}

	
}