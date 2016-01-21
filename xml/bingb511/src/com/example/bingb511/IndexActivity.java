package com.example.bingb511;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import android.app.TabActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class IndexActivity extends TabActivity {

	private TabHost tabHost;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);

		//选项卡
		tabHost = getTabHost();// 从TabActivity上面获取放置Tab的TabHost
		addTab(); // 添加标签
		tabHost.setBackgroundColor(Color.argb(255, 255, 255, 1)); // 设置tabhost的颜色
		tabHost.setBackgroundResource(R.drawable.bg_h); // 设置tabhost的背景图片
		
		//按钮显示图片
		Button button = (Button)findViewById(R.id.b01);    
        button.setText("选择图片");    
        button.setOnClickListener(new Button.OnClickListener(){    
            @Override    
            public void onClick(View v) {    
                Intent intent = new Intent();    
                /* 开启Pictures画面Type设定为image */    
                intent.setType("image/*");    
                /* 使用Intent.ACTION_GET_CONTENT这个Action */    
                intent.setAction(Intent.ACTION_GET_CONTENT);     
                /* 取得相片后返回本画面 */    
                startActivityForResult(intent, 1);    
            }    
                
        });  
        //end 按钮显示图片
	}  
	
	//选项卡
	private void addTab() {
		// setIndicator()此方法用来设置标签和图表
		// 指定内容为一个TextView --->public TabHost.TabSpec setContent(int viewId)
		// 此方法需要一个 viewId 作为参数
		// newTabSpec创建新标签one
		// setIndicator设置标签标题
		// setContent设置该标签的布局内容
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("日历记事", getResources().getDrawable(R.drawable.version))
				.setContent(R.id.tab1));
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("资料备份", getResources().getDrawable(R.drawable.version))
				.setContent(R.id.tab2));
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("压缩图片", getResources().getDrawable(R.drawable.version))
				.setContent(R.id.tab3));
	}
	//end  选项卡

	//按钮显示图片
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {    
	    if (resultCode == RESULT_OK) {    
	        Uri uri = data.getData();    
	        Log.e("uri", uri.toString());    
	        ContentResolver cr = this.getContentResolver();    
	        try {    
	            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));    
	            ImageView imageView = (ImageView) findViewById(R.id.iv01);    
	            /* 将Bitmap设定到ImageView */    
	            imageView.setImageBitmap(bitmap);    
	        } catch (FileNotFoundException e) {    
	            Log.e("Exception", e.getMessage(),e);    
	        }    
	    }    
	    super.onActivityResult(requestCode, resultCode, data);    
	 }
	//end 按钮显示图片
	
    //gridView显示图片
    private List<String> imagePath = new ArrayList<String>();//图片文件路径
    private static String[] imageFormatSet = new String[]{"jpg","png","gif"};
    //判断是否为图片文件
  
	@SuppressWarnings("unused")
	private void getFiles(String url){
    	File files = new File(url);
    	File[] file = files.listFiles();
    	try{
    		for(File f:file){
    			if(f.isDirectory()){
    				getFiles(f.getAbsolutePath());
    			} else {
    				if(isImageFile(f.getPath())){
    					imagePath.add(f.getPath());
    				}
    			}
    		}
    	} catch( Exception e){
    		e.printStackTrace();
    	}
    }
    
	//end gridView显示图片

	private static boolean isImageFile(String path){
    	for(String format:imageFormatSet){
    		if(path.contains(format)){
    			return true;
    		}
    	}
    	return false;
    }
	
}   
