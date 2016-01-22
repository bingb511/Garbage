package com.test.mydialog;

import net.blogjava.mobile.calendar.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OtherActivity extends Activity {
	TextView text;
	Button button1;
	Button button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		// text=(TextView)findViewById(R.id.text);
		button1 = (Button) findViewById(R.id.otherActivity);
		button1.setText("进入记事本");
		button1.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent();
				intent.setClass(OtherActivity.this,
						com.android.alvin.activity.MainActivity.class);
				startActivity(intent);

			}
		});

		button2 = (Button) findViewById(R.id.otherActivity1);
		button2.setText("进入日程提醒");
		button2.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

			}

		});
	}

}
