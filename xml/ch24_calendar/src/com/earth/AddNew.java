
package com.earth;

import net.blogjava.mobile.calendar.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.earth.db.DBHelper;

public class AddNew extends Activity{
	
	private static final int SAVE_ID = Menu.FIRST;
	private static final int RETURN_ID = Menu.FIRST + 1;
	
	EditText title;
	EditText date;
	EditText weather;
	EditText content;
	
	String s_title;
	String s_date;
	String s_weather;
	String s_content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		
		title = (EditText)findViewById(R.id.title);
		date = (EditText)findViewById(R.id.date);
		weather = (EditText)findViewById(R.id.weather);
		content = (EditText)findViewById(R.id.content);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, SAVE_ID, 1, "保存");
		menu.add(0, RETURN_ID, 2, "返回");
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case SAVE_ID:{
				
				s_title = title.getText().toString();
				s_date = date.getText().toString();
				s_weather = weather.getText().toString();
				s_content = content.getText().toString();
				
				if(s_title.equals("")){
					Toast.makeText(AddNew.this, "标题不能为空", Toast.LENGTH_SHORT).show();
					return false;
				}
				if(s_date.equals("")){
					Toast.makeText(AddNew.this, "日期不能为空", Toast.LENGTH_SHORT).show();
					return false;
				}
				
				DBHelper dbHelper = new DBHelper(this);
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("title", s_title);
				values.put("date", s_date);
				values.put("weather", s_weather);
				values.put("content", s_content);
				db.insert("record", null, values);
				Intent intent = new Intent(AddNew.this,Main2.class);
				startActivity(intent);
				finish();
				break;
			}
			case RETURN_ID:{
				Intent intent = new Intent(AddNew.this,Main2.class);
				startActivity(intent);
				finish();
			}
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	
}
