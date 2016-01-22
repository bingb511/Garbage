package net.blogjava.mobile.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

//import com.earth.Main;
import com.earth.Detail;
//import com.earth.Main;
import com.earth.Main2;
//import com.earth.R;
//import com.earth.R;
import com.earth.db.DBHelper;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.DatePicker.OnDateChangedListener;

public class Main extends Activity
{
//	日历视图
	private CalendarView calendarView;	
	//用于数据传输
	private AlertDialog.Builder builder;
	//
	private AlertDialog adMyDate;
	//
	public static Remind remindQueue;
	//
	public List<Remind> remindList = new ArrayList<Remind>();
	//
	public static Activity activity;
	//AlarmManager 对象
	public AlarmManager am;
	//播放器类
	public static MediaPlayer mediaPlayer;
	//振动器类
	public static Vibrator vibrator;
	
	ListView list;
	LinearLayout linearLayout;
	

	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//初始化
		if (activity == null)
		{
			activity = this;
		}
		if (remindQueue == null)
		{
			remindQueue = new Remind();
		}
		if (am == null)
		{
			am = (AlarmManager) getSystemService(ALARM_SERVICE);
		}
		
		Window window = getWindow();
		//获得布局
		LinearLayout mainLayout = (LinearLayout) getLayoutInflater().inflate(
				R.layout.main, null);
		setContentView(mainLayout);
		
		
		calendarView = new CalendarView(this);
		mainLayout.addView(calendarView);
		try
		{
			Intent intent = new Intent(activity, CallAlarm.class);
			PendingIntent sender = PendingIntent.getBroadcast(activity, 0,
					intent, 0);
			//每隔一分钟，发送一次广播
			am.setRepeating(AlarmManager.RTC, 0, 60 * 1000, sender);
		}
		catch (Exception e)
		{	
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

	 class OnRecordRemindMenuItemClick extends MenuItemClickParent implements
			OnMenuItemClickListener
	{

		public OnRecordRemindMenuItemClick(Activity activity)
		{
			super(activity);

		}

		
		public boolean onMenuItemClick(MenuItem item)
		{
			Intent intent = new Intent(activity, AllRecord.class);
			intent.putExtra("year", calendarView.ce.grid.currentYear);
			intent.putExtra("month", calendarView.ce.grid.currentMonth);
			intent.putExtra("day", calendarView.ce.grid.currentDay1);
			activity.startActivity(intent);
			return true;
		}

	}

	class OnTodayMenuItemClick extends MenuItemClickParent implements
			OnMenuItemClickListener
	{

		public OnTodayMenuItemClick(Activity activity)
		{
			super(activity);

		}

		
		public boolean onMenuItemClick(MenuItem item)
		{
			Calendar calendar = Calendar.getInstance();

			calendarView.ce.grid.currentYear = calendar.get(Calendar.YEAR);
			calendarView.ce.grid.currentMonth = calendar.get(Calendar.MONTH);
			calendarView.ce.grid.currentDay = calendar.get(Calendar.DATE);
			calendarView.invalidate();

			return true;
		}

	}

	class OnMyDateMenuItemClick extends MenuItemClickParent implements
			OnMenuItemClickListener, OnClickListener, OnDateChangedListener
	{
		private DatePicker dpSelectDate;
		private LinearLayout myDateLayout;
		private TextView tvDate;
		private TextView tvLunarDate;

		public OnMyDateMenuItemClick(Activity activity)
		{
			super(activity);
			myDateLayout = (LinearLayout) getLayoutInflater().inflate(
					R.layout.mydate, null);
			dpSelectDate = (DatePicker) myDateLayout
					.findViewById(R.id.dpSelectDate);

		}

		
		public void onDateChanged(DatePicker view, int year, int monthOfYear,
				int dayOfMonth)
		{

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
			java.util.Calendar calendar = java.util.Calendar.getInstance();
			calendar.set(year, monthOfYear, dayOfMonth);
			if (tvDate != null)
				tvDate.setText(sdf.format(calendar.getTime()));
			else
				adMyDate.setTitle(sdf.format(calendar.getTime()));

			Calendar calendar1 = Calendar.getInstance();
			if (calendar1.get(Calendar.YEAR) == year
					&& calendar1.get(Calendar.MONTH) == monthOfYear
					&& calendar1.get(Calendar.DATE) == dayOfMonth)
			{
				if (tvDate != null)
					tvDate.setText(tvDate.getText() + "(今天)");
				else
					adMyDate.setTitle(sdf.format(calendar.getTime()) + "(今天)");
			}

			if (tvLunarDate == null)
				return;
			
		}

		
		public void onClick(DialogInterface dialog, int which)
		{
			calendarView.ce.grid.currentYear = dpSelectDate.getYear();
			calendarView.ce.grid.currentMonth = dpSelectDate.getMonth();
			calendarView.ce.grid.currentDay = dpSelectDate.getDayOfMonth();
			calendarView.invalidate();

		}

		
		public boolean onMenuItemClick(MenuItem item)
		{
			// Create a builder
			builder = new AlertDialog.Builder(activity);
			builder.setTitle("指定日期");

			myDateLayout = (LinearLayout) getLayoutInflater().inflate(
					R.layout.mydate, null);
			dpSelectDate = (DatePicker) myDateLayout
					.findViewById(R.id.dpSelectDate);
			tvDate = (TextView) myDateLayout.findViewById(R.id.tvDate);
			tvLunarDate = (TextView) myDateLayout
					.findViewById(R.id.tvLunarDate);

			dpSelectDate.init(calendarView.ce.grid.currentYear,
					calendarView.ce.grid.currentMonth,
					calendarView.ce.grid.currentDay, this);

			builder.setView(myDateLayout);

			builder.setPositiveButton("确定", this);
			builder.setNegativeButton("取消", null);
			builder.setIcon(R.drawable.calendar_small);
			adMyDate = builder.create();
			onDateChanged(dpSelectDate, dpSelectDate.getYear(), dpSelectDate
					.getMonth(), dpSelectDate.getDayOfMonth());
			adMyDate.show();

			return true;
		}
	}



	
	class OnAboutMenuItemClick extends MenuItemClickParent implements
			OnMenuItemClickListener
	{

		public OnAboutMenuItemClick(Activity activity)
		{
			super(activity);
		}

		
		public boolean onMenuItemClick(MenuItem item)
		{
			Intent intent = new Intent(activity, About.class);
			activity.startActivity(intent);
			return true;
		}

	}
	//记事本
  class ondiaryMenuItemClick extends MenuItemClickParent implements 
           OnMenuItemClickListener{

	public ondiaryMenuItemClick(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	
	public boolean onMenuItemClick(MenuItem item) {
		// TODO Auto-generated method stub
//		if (item.getItemId()==4) {
//			Intent intent=new Intent();
//		    intent.setClass(activity, com.earth.Main2.class);
//		    activity.startActivity(intent);
//		}
		return true;
	}
	  
	  
	  
	  
  }


	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);

		MenuItem miToday = menu.add(0, 1, 1, "今天");
		MenuItem miMyDate = menu.add(0, 2, 2, "指定日期");
		MenuItem miRecordRemind = menu.add(0, 3, 3, "日程提醒");	
		MenuItem dirary=menu.add(0, 4, 4, "私密记事本");
		dirary.setIcon(R.drawable.notapad26).setOnMenuItemClickListener(new OnMenuItemClickListener() {
		
		public boolean onMenuItemClick(MenuItem item) {
//		Log.e("-----", "dss-___");
		if(item.getItemId()==4) {
			Intent intent=new Intent();
		    intent.setClass(activity, com.earth.Main2.class);
		    activity.startActivity(intent);
		}
		return true;
		}
		});
		MenuItem miAbout = menu.add(0, 18, 18, "关于");
		miToday.setIcon(R.drawable.clock);
		miToday.setOnMenuItemClickListener(new OnTodayMenuItemClick(this));
		miMyDate.setIcon(R.drawable.calendar_small);
		miMyDate.setOnMenuItemClickListener(new OnMyDateMenuItemClick(this));
		miRecordRemind.setIcon(R.drawable.diary);
		miRecordRemind
				.setOnMenuItemClickListener(new OnRecordRemindMenuItemClick(
						this));
		miAbout.setIcon(R.drawable.about);
		miAbout.setOnMenuItemClickListener(new OnAboutMenuItemClick(this));
		
		return true;
	}
//		
//		
//	}

	
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		calendarView.onKeyDown(keyCode, event);
		return super.onKeyDown(keyCode, event);
	}

}