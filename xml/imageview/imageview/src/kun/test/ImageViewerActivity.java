package kun.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

/**
 * ͨ��Intent��ȡͼƬ·��������ʾ
 */
public class ImageViewerActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //����ʾ��Activity����
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        //��ȡIntent
        Intent receiveIntent=getIntent();
        //��ȡͼƬ·��
        String picPath=receiveIntent.getStringExtra("picPath");
        ImageView iv=(ImageView)findViewById(R.id.imageView);
        //ʹ��BitmapFactory��ImageView����ʾͼƬ
        iv.setImageBitmap(BitmapFactory.decodeFile(picPath));
    }
    
}
