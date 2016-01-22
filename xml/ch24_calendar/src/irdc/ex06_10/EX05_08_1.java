package irdc.ex06_10;
import android.app.Activity; 
import android.app.NotificationManager;
import android.os.Bundle; 
import android.widget.Toast;

public class EX05_08_1 extends Activity 
{ NotificationManager myNotiManager;
  protected void onCreate(Bundle savedInstanceState)
  {  

    super.onCreate(savedInstanceState); 
    myNotiManager=(NotificationManager)this.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
    myNotiManager.cancel(0);
    Toast.makeText(EX05_08_1.this,
                   "Ä£ÄâMSN",
                   Toast.LENGTH_SHORT
                  ).show();
    System.out.println("this is EX05_08_1");
 /*after the following finish,EX05_08 is activated, this is syntax.
 based on myNoti.setLatestEventInfo(EX05_08.this,"MSNµÇÂ¼×´Ì¬",text,appIntent); */
    finish();
  } 
} 