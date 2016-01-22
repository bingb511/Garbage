package irdc.ex06_10;
import android.app.Activity; 
import android.app.NotificationManager;
import android.os.Bundle; 
import android.widget.Toast;

public class EX05_08_2 extends Activity 
{ NotificationManager myNotiManager;
protected void onCreate(Bundle savedInstanceState)
{  

  super.onCreate(savedInstanceState); 
  myNotiManager=(NotificationManager)this.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
  myNotiManager.cancel(1); 
    Toast.makeText(EX05_08_2.this,
                   "Ä£ÄâMSN2",
                   Toast.LENGTH_SHORT
                  ).show();
    System.out.println("this is EX05_08_2");
    finish();
  } 
} 