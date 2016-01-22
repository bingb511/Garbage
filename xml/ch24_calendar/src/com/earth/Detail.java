package com.earth;

import java.util.HashMap;

import net.blogjava.mobile.calendar.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.earth.db.DBHelper;

public class Detail extends Activity{
	
	private static final int UPDATE_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;
	private static final int RETURN_ID = Menu.FIRST + 2;
	
	EditText title;
	EditText date;
	EditText weather;
	EditText content;
	
	String s_title;
	String s_date;
	String s_weather;
	String s_content;
	

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		
		title = (EditText)findViewById(R.id.title);
		date = (EditText)findViewById(R.id.date);
		weather = (EditText)findViewById(R.id.weather);
		content = (EditText)findViewById(R.id.content);
		
		Intent intent = getIntent();
		HashMap map = (HashMap)intent.getSerializableExtra("map");
		
		title.setText(String.valueOf(map.get("title")));
		date.setText(String.valueOf(map.get("date")));
		weather.setText(String.valueOf(map.get("weather")));
		content.setText(String.valueOf(map.get("content")));
		
		s_title = title.getText().toString();
		s_date = date.getText().toString();
		s_weather = weather.getText().toString();
		s_content = content.getText().toString();
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, UPDATE_ID, 1, "保存修改");
		menu.add(0, DELETE_ID, 2, "删除此记录");
		menu.add(0, RETURN_ID, 3, "返回首页");
		
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case UPDATE_ID:{
				
				if(s_title.equals("")){
					Toast.makeText(Detail.this, "标题不能为空", Toast.LENGTH_SHORT).show();
					return false;
				}
				if(s_date.equals("")){
					Toast.makeText(Detail.this, "日期不能为空", Toast.LENGTH_SHORT).show();
					return false;
				}
				
				DBHelper dbHelper = new DBHelper(this);
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put("title", s_title);
				values.put("date", s_date);
				values.put("weather", s_weather);
				values.put("content", s_content);
				db.update("record", values, "title=?", new String[]{s_title});

				Intent intent = new Intent(Detail.this,Main2.class);
				startActivity(intent);
				finish();
				break;
			}
			case DELETE_ID:{
				AlertDialog dialog;
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("确定要删除此记录吗？");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){

					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						DBHelper dbHelper = new DBHelper(Detail.this);
						SQLiteDatabase db = dbHelper.getWritableDatabase();
						db.delete("record", "title=?", new String[]{s_title});
						Intent intent = new Intent(Detail.this,Main2.class);
						startActivity(intent);
						finish();
					}
					
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){

					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
					
				});
				dialog = builder.create();
				dialog.show();
				break;
			}
			case RETURN_ID:{
				Intent intent = new Intent(Detail.this,Main2.class);
				startActivity(intent);
				finish();
			}
		}
		
		return super.onOptionsItemSelected(item);
	}
}
