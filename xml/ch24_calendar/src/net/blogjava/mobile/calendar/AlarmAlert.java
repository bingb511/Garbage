package net.blogjava.mobile.calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
/*
 * 提醒界面
 */

public class AlarmAlert extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//获得传过来的Intent
		Intent intent = getIntent();
		//利用getExtras，把数据传入Bundle
		Bundle bundle = intent.getExtras();
//		从Bundle中取的remindMsg
		String remindMsg = bundle.getString("remindMsg");
		//if 铃声
		if (bundle.getBoolean("ring"))
		{
			Main.mediaPlayer = MediaPlayer.create(this, R.raw.ring);
			try
			{
				Main.mediaPlayer.setLooping(true);
				Main.mediaPlayer.prepare();
			}
			catch (Exception e)
			{
				setTitle(e.getMessage());
			}
			Main.mediaPlayer.start();
		}
		//if 振动
		if(bundle.getBoolean("shake"))
		{
			//获得Vibrator对象
			Main.vibrator = (Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);
			//设置振动频率，以及重复次数
			Main.vibrator.vibrate(new long[]{1000, 100, 100,1000}, -1);
		}
		//Creates a AlertDialog with the arguments 
		//supplied to this builder and show()'s the dialog.
		new AlertDialog.Builder(AlarmAlert.this).setIcon(R.drawable.clock)
				.setTitle("提醒").setMessage(remindMsg).setPositiveButton("关掉他",
						//Interface used to allow the creator of a dialog 
						//to run some code when an item on the dialog is clicked.. 
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog,
									int whichButton)
							{
								AlarmAlert.this.finish();
								//关闭铃声或振动
								if (Main.mediaPlayer != null)
									Main.mediaPlayer.stop();
								if(Main.vibrator != null)
									Main.vibrator.cancel();
							}
						}).show();

	}
}
