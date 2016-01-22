package net.blogjava.mobile.calendar;

import com.earth.Detail;
//import com.earth.Main;
//import com.earth.Main;
 import com.earth.db.*;
 
import com.earth.db.DBHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
/*
 * @Reader DaXiong
 * 备忘录编辑页面
 */
public class Record extends Activity
{
	//日程Title
	private EditText etTitle;
	//日程内容
	private EditText etContent;
	//是否被修改
	private boolean edit = false;
	//
	private int id, index;
	//时间
	private int hour, minute;
	//提醒方式，振动，响铃
	private boolean shake, ring;
	//
	private String remindTime;
	String s_title;
	private static final int DELETE_ID = Menu.FIRST + 1;

	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record);
		
		etTitle = (EditText) findViewById(R.id.etTitle);
		etContent = (EditText) findViewById(R.id.etContent);
		//获得传送过来的Intent
		Intent intent = getIntent();
		//获得edit的值，如果没有，就返回false
		edit = intent.getBooleanExtra("edit", false);
		//如果修改 ，则取出信息
		if (edit)
		{	//the value to be returned if no value of the 
			//desired type is stored with the given name
			id = intent.getIntExtra("id", 0);
			index = intent.getIntExtra("index", -1);

			Cursor cursor = Grid.dbService.query(id);			
			if (cursor.moveToLast())
			{
				etTitle.setText(cursor.getString(0));
				etContent.setText(cursor.getString(1));
				shake = Boolean.parseBoolean(cursor.getString(2));
				ring = Boolean.parseBoolean(cursor.getString(3));
			}

		}

	}

	class MenuItemClickParent
	{
		protected Activity activity;

		public MenuItemClickParent(Activity activity)
		{
			this.activity = activity;
		}
	}
	//保存
	class OnSaveMenuItemClick extends MenuItemClickParent implements
			OnMenuItemClickListener
	{

		
		public boolean onMenuItemClick(MenuItem item)
		{

			if (edit)
			{
				Grid.dbService.updateRecord(id, etTitle.getText().toString(),
						etContent.getText().toString(),remindTime, shake,ring);

				AllRecord.recordArray.set(index, etTitle.getText().toString());				
				AllRecord.myListActivity.setListAdapter(AllRecord.arrayAdapter);

			}
			else
			{
				Grid.dbService.insertRecord(etTitle.getText().toString(),
						etContent.getText().toString(), AllRecord.year + "-"
								+ AllRecord.month + "-" + AllRecord.day,
						remindTime, shake, ring);
				AllRecord.arrayAdapter.insert(etTitle.getText().toString(), 0);
				
				AllRecord.idList.add(0, Grid.dbService.getMaxId(AllRecord.year
						+ "-" + AllRecord.month + "-" + AllRecord.day));

			}

			activity.finish();
			return true;
		}

		public OnSaveMenuItemClick(Activity activity)
		{
			super(activity);
		}
		
	}
	//设置提醒时间
	class OnSettingMenuItemClick extends MenuItemClickParent implements
			OnMenuItemClickListener, OnClickListener
	{
		private TimePicker tpRemindTime;
		private CheckBox cbShake, cbRing;

		
		public boolean onMenuItemClick(MenuItem item)
		{
			AlertDialog.Builder builder;

			builder = new AlertDialog.Builder(activity);
			builder.setTitle("设置提醒时间");
			//动态生成一个LinearLayout
			LinearLayout remindSettingLayout = (LinearLayout) getLayoutInflater()
					.inflate(R.layout.remindsetting, null);
			//TimePicker
			tpRemindTime = (TimePicker) remindSettingLayout
					.findViewById(R.id.tpRemindTime);
			cbShake = (CheckBox)remindSettingLayout.findViewById(R.id.cbShake);
			cbRing = (CheckBox)remindSettingLayout.findViewById(R.id.cbRing);
			//
			cbShake.setChecked(shake);
			cbRing.setChecked(ring);
			
			tpRemindTime.setIs24HourView(true);
			
			if (remindTime != null)
			{
				tpRemindTime.setCurrentHour(hour);
				tpRemindTime.setCurrentMinute(minute);
			}
			builder.setView(remindSettingLayout);

			builder.setPositiveButton("确定", this);
			builder.setNegativeButton("取消", null);
			AlertDialog adRemindSetting;
			adRemindSetting = builder.create();
			adRemindSetting.show();
			return true;
		}
		//点击
		
		public void onClick(DialogInterface dialog, int which)
		{

			hour = tpRemindTime.getCurrentHour();
			minute = tpRemindTime.getCurrentMinute();
			remindTime = hour + ":" + minute + ":0";
			shake = cbShake.isChecked();
			ring = cbRing.isChecked();

		}

		public OnSettingMenuItemClick(Activity activity)
		{
			super(activity);
		}
//		public OndeleteMenuItemClick(Activity activity)
//		{
//			super(activity);
//		}
		
	}
	/*
	 * 菜单函数
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuItem miSaveRecord = menu.add(0, 1, 1, "完成");
		MenuItem miSetting = menu.add(0, 2, 2, "设置提醒时间");
		MenuItem delete = menu.add(0, DELETE_ID, 3, "删除");
		miSaveRecord.setOnMenuItemClickListener(new OnSaveMenuItemClick(this));
		miSetting.setOnMenuItemClickListener(new OnSettingMenuItemClick(this));
//        delete.setOnMenuItemClickListener(new On(this));
		
		delete.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			public boolean onMenuItemClick(MenuItem item) {
//			Log.e("-----", "dss-___");
			if(item.getItemId()==3) {
//				Intent intent=new Intent();
//			    intent.setClass(activity, com.earth.Main2.class);
//			    activity.startActivity(intent);
//				finish();
					
				}
			return false;
			}
			});
		
	
		
		
		
	
		return true;
	}
//////////////////////////////////////////////
	
	
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		
		switch (DELETE_ID) {
		case DELETE_ID:{
			AlertDialog dialog;
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("确定要删除此记录吗？");
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){

				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					DBHelper dbHelper = new DBHelper(Record.this);
					SQLiteDatabase db = dbHelper.getWritableDatabase();
					db.delete("record", "etTitle=?", new String[]{s_title});
					Intent intent = new Intent(Record.this,Main.class);
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
			
			
			
			
			
			
//			break;
		}
		default:
			break;
		}
		
		
		
		
		
		
		
		
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
	
///////////////////////////////////////////////	
}
