package com.android.alvin.activity;

import java.util.Calendar;

import com.android.alvin.db.DBOper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class AlarmService extends Service {

	private static String TAG = "alarmService";
	private static AlarmService alarmService = null;
	private AlarmManager am;
	
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	public Service getService(){
		return alarmService;
	}
	
	public void onCreate(){
		super.onCreate();
		am = (AlarmManager)getSystemService(ALARM_SERVICE);
		alarmService = this;
	}
	
	public void onStart(Intent intent,int startId){
		super.onStart(intent, startId);
		Log.d(TAG, "service start");
		String id = intent.getStringExtra("id");
		String time = intent.getStringExtra("time");
		Calendar cal = Calendar.getInstance();
		
		String[] temp = time.split(":");
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(temp[0]));
		cal.set(Calendar.MINUTE, Integer.parseInt(temp[1]));
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Intent i = new Intent(AlarmService.this,CallAlarm.class);
		intent.putExtra(DBOper.CLOCK_ID, id);
		PendingIntent pi = PendingIntent.getBroadcast(AlarmService.this, Integer.parseInt(id), intent, 0);
		am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);
	}

	public void onDestroy(){
		Intent intent = new Intent(AlarmService.this,CallAlarm.class);
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
		am.cancel(pi);
		super.onDestroy();
	}
	
}
