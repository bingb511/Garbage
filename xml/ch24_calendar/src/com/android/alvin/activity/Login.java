package com.android.alvin.activity;

import net.blogjava.mobile.calendar.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.alvin.db.DBOper;

public class Login extends Activity {

	private DBOper db;
	private EditText et;
	private Button login_button;
	private Button change_button;
	private String password;
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		db = new DBOper(this);
		password = db.getPwd();
		if(password.equals("")){
			Intent intent = new Intent(Login.this,PasswordManage.class);
			startActivity(intent);
			finish();
			return;
		}
		setContentView(R.layout.login);
		
		et = (EditText)findViewById(R.id.login_password);
		login_button = (Button)findViewById(R.id.note_login);
		
		login_button.setOnClickListener(new Button.OnClickListener(){
			
			public void onClick(View v) {
				String input_pwd = et.getText().toString();
				if(password.equals(input_pwd)){
					Toast.makeText(Login.this, R.string.login_success, Toast.LENGTH_LONG).show();
					Intent intent = new Intent(Login.this,MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(Login.this, R.string.error_password, Toast.LENGTH_LONG).show();
					et.setText("");
				}
			}
			
		});
	}
}
