package com.example.bing0;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText username, password;
	Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				String str = username.getText().toString().trim();
				String str1 = password.getText().toString().trim();
				if (str1.equals(str)) {
					Intent in=new Intent(MainActivity.this,IndexActivity.class);
					startActivity(in);
					
				} else {
					System.out.println("!!!!!!!");
				}
			}
		});

	}
}
