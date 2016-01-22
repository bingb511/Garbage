package irdc.ex06_10;

import net.blogjava.mobile.calendar.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class AlarmAlert extends Activity
{
    MediaPlayer mMediaPlayer = new MediaPlayer();
  protected void onCreate(Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
         
    mMediaPlayer.setLooping(true);  
    Uri mediaUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
    try{
        System.out.println("Timedown "+mediaUri.toString());
        mMediaPlayer.setDataSource(this, mediaUri);
//        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
        mMediaPlayer.prepare();
        mMediaPlayer.start();


    }
    catch(Exception e)
    {
      System.out.println("Timedown "+e.toString());
    }




    new AlertDialog.Builder(AlarmAlert.this)
        .setIcon(R.drawable.clock)
        .setTitle("闹钟响了")
        .setMessage("赶快起床吧")
        .setPositiveButton("关掉他",new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface dialog, int whichButton)
          {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            AlarmAlert.this.finish();
          }
        })
        .show();
  } 
}