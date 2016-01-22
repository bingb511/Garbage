package com.android.alvin.activity;


import net.blogjava.mobile.calendar.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.alvin.db.DBOper;

public class EditNoteActivity extends Activity {

	private EditText et;
	private DBOper db;
	private String id = "";
	private Intent intent;
	private Button save_button;
	private Button clock;
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.edit_note);
		db = new DBOper(this);
		
		et = (EditText)findViewById(R.id.note_edittext);
		save_button = (Button)findViewById(R.id.save_note_button);
		
		intent = this.getIntent();
		String id = intent.getStringExtra("id");
		String content = intent.getStringExtra("content");
		if(id!=null&&!id.equals("")){
			et.setText(content);
			this.id = id;
		}
		
		save_button.setOnClickListener(new Button.OnClickListener(){
			
			public void onClick(View v) {
				save();
			}
			
		});
		/////ÄÖÁåÌáÐÑ
		clock=(Button)findViewById(R.id.clock);
		clock.setOnClickListener(new Button.OnClickListener(){

			
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(EditNoteActivity.this, irdc.ex06_10.EX06_10.class);
				startActivity(intent);
				
			}
			
		});
		
		
		
		
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		menu.add(0, 1, 0, R.string.save);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		super.onOptionsItemSelected(item);
		switch(item.getItemId()){
			case 1:
				save();
				Intent intent=new Intent();
				intent.setClass(EditNoteActivity.this, com.android.alvin.activity.MainActivity.class);
				startActivity(intent);
		}
		
		return true;
	}
	
	public void save(){
		String content = et.getText().toString();
		if(content.equals("")){
			Toast.makeText(this, R.string.content_is_null, Toast.LENGTH_LONG).show();
			return;
		}
		if(!id.equals("")){
			int i = db.updateNote(id, content);
			if(i > 0){
				Toast.makeText(this, R.string.save_success, Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(this, R.string.save_failure, Toast.LENGTH_LONG).show();
			}
		}else{
			long l = db.insertNote(content);
			if(l>0){
				Toast.makeText(this, R.string.save_success, Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(this, R.string.save_failure, Toast.LENGTH_LONG).show();
			}
		}
		EditNoteActivity.this.setResult(RESULT_OK, intent);
		EditNoteActivity.this.finish();
	}
}
