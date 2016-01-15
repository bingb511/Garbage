package com.example.bingb511;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LinearLayout layout=(LinearLayout)findViewById(R.id.layout);
		//�����Զ���Ӧ
		MainActivity.AutoBackground(this, layout, R.drawable.bg_v, R.drawable.bg_h);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//��ȡ��Ļ����

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("InlinedApi")
	public static int ScreenOrient(Activity activity){
		int orient = activity.getRequestedOrientation();
		if(orient != ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE && orient != ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT){
			//��>��Ϊ����,����Ϊ����  
			WindowManager windowManager = activity.getWindowManager();
			Display display = windowManager.getDefaultDisplay();
			@SuppressWarnings("deprecation")
			int screenWidth = display.getWidth();
			@SuppressWarnings("deprecation")
			int screenHeight = display.getHeight();
			orient = screenWidth < screenHeight ? ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
		}
		return orient;
	}
	
	public static void AutoBackground(Activity activity, View view, int Background_v, int Background_h){
		int orient = ScreenOrient(activity);
		if (orient == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) { //���� 
            view.setBackgroundResource(Background_v);
        }else{ //����
            view.setBackgroundResource(Background_h);
        }
	}
}