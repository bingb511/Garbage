package irdc.ex06_10;


import java.io.File;

import net.blogjava.mobile.calendar.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

public class CallAlarm extends BroadcastReceiver
{
   NotificationManager myNotiManager;
  
/*abstract void  onReceive(Context context, Intent intent) This method is called when the BroadcastReceiver
is receiving an Intent broadcast. */  
  public void onReceive(Context context, Intent intent)
  {
    myNotiManager=(NotificationManager)context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
    setNotiType(R.drawable.min,context,"马上回来");
    Intent i = new Intent(context, AlarmAlert.class);
    Bundle bundleRet = new Bundle();
    bundleRet.putString("STR_CALLER", "");
 //   i.putExtras(bundleRet);
/*qixy: the following statement can not be removed experimentally, otherwise can not run normally.
public static final int FLAG_ACTIVITY_NEW_TASK 

you must use Intent.FLAG_ACTIVITY_NEW_TASK, otherwise, report 
error.report the error of "Calling startActivity() from outside of an Activity  context requires 
the FLAG_ACTIVITY_NEW_TASK flag."

in the past, in EX03_09,we use like this:

public class EX03_09 extends Activity 
{
        Intent intent = new Intent();
        intent.setClass(EX03_09.this, EX03_09_1.class);
        startActivity(intent); 

now we call startActivity() from outside of an Activity  context, we must use 
context.startActivity(i);       
*/    
    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(i);      
  }
/*设置通知属性，监听事件等*/
  private void setNotiType(int iconId,Context context, String text)
  {
/*when press the notification icon, EX05_08_1 and EX05_08_2 will show up. */
    Intent notifyIntent=new Intent(context,EX05_08_1.class);

    
    Intent notifyIntent2=new Intent(context,EX05_08_2.class); 
/* 
    
    
/* public static PendingIntent getActivity (Context context, int requestCode, Intent intent, int flags) 
Retrieve a PendingIntent that will start a new activity, like calling Context.startActivity(Intent).

Parameters
context  The Context in which this PendingIntent should generate the activity. 
requestCode  Private request code for the sender (currently not used). 
intent  Intent of the activity to be launched. 
flags  May be FLAG_ONE_SHOT, FLAG_NO_CREATE, FLAG_CANCEL_CURRENT, FLAG_UPDATE_CURRENT,
   
qixy: for this example, as long as you don't use FLAG_NO_CREATE and FLAG_ONE_SHOT, all other
values don't make any difference, because if FLAG_NO_CREATE, then return null.if FLAG_ONE_SHOT,
,if you press the notification the second time, Toast of "模拟MSN2" can not be shown any more.  

FLAG_CANCEL_CURRENT: if the described PendingIntent already exists, the current one is cancelled
                     before generating a new one.
FLAG_UPDATE_CURRENT: if the described PendingIntent already exists, then keep it but its replace
                     its extra data with what is in this new Intent.qixy: for this example, we
                     don't have extra data.
Returns
Returns an existing or new PendingIntent matching the given parameters. May return null only if
 FLAG_NO_CREATE has been supplied. 
qixy: the following statement's function is to get an PendingIntent from an ordinary Intent.  
I tried, we must use the following second pair, if we use the first pair, it can not work.
(previously used), it is alleged that the first pair id for broadcast.
*/
 //   PendingIntent appIntent = PendingIntent.getBroadcast(EX05_08.this, 0, notifyIntent, PendingIntent.FLAG_CANCEL_CURRENT);
 //   PendingIntent appIntent2 = PendingIntent.getBroadcast(EX05_08.this, 0, notifyIntent2, PendingIntent.FLAG_UPDATE_CURRENT);
    PendingIntent appIntent=PendingIntent.getActivity(context,0,notifyIntent,PendingIntent.FLAG_CANCEL_CURRENT ); 
    PendingIntent appIntent2=PendingIntent.getActivity(context,0,notifyIntent2,PendingIntent.FLAG_UPDATE_CURRENT ); 
       

    Notification myNoti=new Notification();
    Notification myNoti2=new Notification();
    myNoti.icon=iconId; 
    Uri ringURI=  RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_NOTIFICATION);
   // ringURI= Uri.fromFile(new File("/system/media/audio/notifications/Chiff.mp3"));
    myNoti.sound = ringURI;

    myNoti2.icon=iconId;
/*the following tickerText is the text flash, then disappear. */
    myNoti.tickerText=text+"qixy";
    myNoti2.tickerText=text+"qixy2";

//    myNoti.defaults=Notification.DEFAULT_SOUND;
    
/* qixy: the following statement handles when you drag down the notification icon, 
 * 
 *   background knowledge:      public RemoteViews contentView 
The view that shows when this notification is shown in the expanded status bar. 
 
public void setLatestEventInfo (Context context, CharSequence contentTitle, CharSequence contentText,
 PendingIntent contentIntent) 
Sets the contentView field to be a view with the standard "Latest Event" layout. 

Uses the icon and when fields to set the icon and time fields in the view.


Parameters
context  The context for your application / activity. 
contentTitle  The title that goes in the expanded entry. 
contentText  The text that goes in the expanded entry. 
contentIntent  The intent to launch when the user clicks the expanded notification. If this is
an activity, it must include the FLAG_ACTIVITY_NEW_TASK flag, which requires that you take care of
task management as described in Application Fundamentals: Activities and Tasks.  

*/
   

    
    myNoti.setLatestEventInfo(context,"MSN登录状态",text,appIntent);
    
    

    
    myNoti2.setLatestEventInfo(context,"MSN登录状态2",text+"2",appIntent2);
/*public void notify (int id, Notification notification) 
Persistent notification on the status bar,  
Parameters
id  An identifier for this notification unique within your application. 
notification  A Notification object describing how to notify the user, other than 
the view you're providing. Must not be null.
experimentally, we must have the following statement,otherwise, your notification can not appear in the 
notification queue.  
*/
   
    
 RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.main4);
    
    myNoti.contentView = remoteView;
    
    
   myNotiManager.notify(0,myNoti);
  
   myNotiManager.notify(1,myNoti2);
  } 
}