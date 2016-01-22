package net.blogjava.mobile.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.blogjava.mobile.calendar.Record.MenuItemClickParent;
import net.blogjava.mobile.calendar.db.DBService;

import android.R.integer;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/*
 * @Reader DaXiong
 * Display all record 显示所有记录
 */

public class AllRecord extends ListActivity
{
//  variable Year ,Month , Day
	public static int year, month, day;
//	string list 文本项列表
	public static List<String> recordArray;
//	String's arrayadapter 数组适配器 
	public static ArrayAdapter<String> arrayAdapter;
//	行
	public static List<Integer> idList = new ArrayList<Integer>();
//	one listactivity object
	public static ListActivity myListActivity;
//	menuitem object
	private MenuItem miNewRecord;
	private MenuItem miModifyRecord;
	private MenuItem miDeleteRecord;
//	监听器
	private OnEditRecordMenuItemClick editRecordMenuItemClick = new OnEditRecordMenuItemClick(
			this);

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//get this from intent
		year = getIntent().getExtras().getInt("year");
		month = getIntent().getExtras().getInt("month");
		day = getIntent().getExtras().getInt("day");
        //数据集合 形如：ADO.NET
		Cursor cursor = Grid.dbService.query(year + "-" + month + "-" + day);
		//初始化recordArray
		if (recordArray == null)
			recordArray = new ArrayList<String>();
		//初始化arrayAdapter
		if (arrayAdapter == null)
			arrayAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, recordArray);
		else
			arrayAdapter.clear();
		//清空列表
		idList.clear();
		//把数据放入arrayAdapter
		while (cursor.moveToNext())
		{
			arrayAdapter.add(cursor.getString(1));
			idList.add(cursor.getInt(0));
		}
		//设定Activity String的格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
		//Calendar rightNow = Calendar.getInstance()
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		//Sets the year, month and day of the month fields
		calendar.set(year, month, day);
		//设置ListActivity的Title 
		setTitle(sdf.format(calendar.getTime()));
		
		
		//设置ListActivity的ListAdapter
		setListAdapter(arrayAdapter);
		//先清空myListActivity object 
		myListActivity = null;
		//then 给myListActivity object 赋予AllRecord activity 值
		//使它成为AllRecord Activity 对象
		myListActivity = this;

	}
	/*
	 * 当一个Menu选项被选中或点击时
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		//调用编辑Activity
		startEditRecordActivity(position);
	}
	
	/*
	 * @菜单
	 */
	class MenuItemClickParent
	{
		protected Activity activity;

		public MenuItemClickParent(Activity activity)
		{
			this.activity = activity;
		}
	}
	//Add Record by AddMenu clicking
	class OnAddRecordMenuItemClick extends MenuItemClickParent implements
			OnMenuItemClickListener
	{

		
		public boolean onMenuItemClick(MenuItem item)
		{
			Intent intent = new Intent(activity, Record.class);
			activity.startActivity(intent);
			return true;
		}

		public OnAddRecordMenuItemClick(Activity activity)
		{
			super(activity);
		}

	}
	/*
	 * 备忘录编辑Activity
	 */
	public void startEditRecordActivity(int index)
	{
		Intent intent = new Intent(this, Record.class);
		//添加数据
		intent.putExtra("edit", true);
		intent.putExtra("id", idList.get(index));
		intent.putExtra("index", index);
		startActivity(intent);
	}
	/*
	 * 修改记录
	 */
	class OnEditRecordMenuItemClick extends MenuItemClickParent implements
			OnMenuItemClickListener
	{

		
		public boolean onMenuItemClick(MenuItem item)
		{
			AllRecord allRecord = (AllRecord) activity;
			//根据allrecord得到选中删除数据的行数
			int index = allRecord.getSelectedItemPosition();

			if (index < 0)
				return false;
			//调用编辑页面
			allRecord.startEditRecordActivity(index);
			return true;
		}

		public OnEditRecordMenuItemClick(Activity activity)
		{
			super(activity);
		}

	}
	
	/*
	 * 删除记录
	 */
	class OnDeleteRecordMenuItemClick extends MenuItemClickParent implements
			OnMenuItemClickListener
	{

		public OnDeleteRecordMenuItemClick(Activity activity)
		{
			super(activity);
			// TODO Auto-generated constructor stub
		}

		
		public boolean onMenuItemClick(MenuItem item)
		{
			
			AllRecord allRecord = (AllRecord) activity;
			//根据allrecord得到选中删除数据的行数
			int index = allRecord.getSelectedItemPosition();

			if (index < 0)
				return false;
			//删除
			recordArray.remove(index);
			int id = idList.get(index);
			idList.remove(index);
			//页面更新
			allRecord.setListAdapter(arrayAdapter);
			//数据永久删除
			Grid.dbService.deleteRecord(id);
			
			return true;
		}

	}
	/*
	 * 添加menu菜单
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		miNewRecord = menu.add(0, 1, 1, "添加记录");
		miModifyRecord = menu.add(0, 2, 2, "修改/查看");
		miDeleteRecord = menu.add(0, 3, 3, "删除记录");
		
		
		
	//绑定监听器
		miNewRecord.setOnMenuItemClickListener(new OnAddRecordMenuItemClick(
				this));
		miModifyRecord.setOnMenuItemClickListener(editRecordMenuItemClick);
		miDeleteRecord.setOnMenuItemClickListener(new OnDeleteRecordMenuItemClick(this));
		return true;
	}

}
