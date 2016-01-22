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

public class PasswordManage extends Activity {
	
	private EditText et1;
	private EditText et2;
	private Button button;
	private DBOper db;
	private int CHANGE_PWD_SUCCESS = 100;
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		db = new DBOper(this);
		setContentView(R.layout.password_manage);
		
		et1 = (EditText)findViewById(R.id.new_password);
		et2 = (EditText)findViewById(R.id.repeat_password);
		button = (Button)findViewById(R.id.save_password);
		
		button.setOnClickListener(new Button.OnClickListener(){
			
			public void onClick(View v) {
				String new_pwd = et1.getText().toString();
				String repeat_pwd = et2.getText().toString();
				if(new_pwd==null||new_pwd.equals("")){
					Toast.makeText(PasswordManage.this, R.string.password_is_null, Toast.LENGTH_LONG).show();
				}else{
					if(new_pwd.equals(repeat_pwd)){
						if(db.getPwd().equals("")){
							db.insertPwd(new_pwd);
							Toast.makeText(PasswordManage.this, R.string.password_set_success, Toast.LENGTH_LONG).show();
							toNoteList();
						}else{
							db.updatePwd(new_pwd);
							Toast.makeText(PasswordManage.this, R.string.password_change_success, Toast.LENGTH_LONG).show();
							PasswordManage.this.setResult(CHANGE_PWD_SUCCESS);
							PasswordManage.this.finish();
						}
					}else{
						Toast.makeText(PasswordManage.this, R.string.password_isnot_equal, Toast.LENGTH_LONG).show();
					}
				}
			}
		});
	}
	
	public void toNoteList(){
		Toast.makeText(PasswordManage.this, R.string.password_set_success, Toast.LENGTH_LONG);
		Intent intent = new Intent(PasswordManage.this,MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		finish();
	}

}
