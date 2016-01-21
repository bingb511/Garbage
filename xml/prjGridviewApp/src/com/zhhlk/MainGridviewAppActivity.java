package com.zhhlk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.*;
import java.io.*;
public class MainGridviewAppActivity extends Activity {
    /** Called when the activity is first created. */
    private List<Map<String,Object>> lsmap;
    private GridView gv;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //从根目录开始遍历图形文件
        lsmap=FileList.findFile(Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.v("lsmap.size====>", ""+lsmap.size());
        //获取GridView控件
        gv=(GridView) findViewById(R.id.photo_gridView);
        ImageAdapter ia=new ImageAdapter(MainGridviewAppActivity.this, lsmap);
        gv.setAdapter(ia);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Map map=lsmap.get(arg2);
				String fname=(String) map.get("fname");
				File fl=new File(fname);
				BitmapFactory.Options opt=new BitmapFactory.Options();
				opt.inPreferredConfig=Config.RGB_565;
				opt.inPurgeable=true;
				opt.inSampleSize=2;
				opt.inInputShareable=true;
				try {
					FileInputStream fin=new FileInputStream(fl);
					BufferedInputStream bin=new BufferedInputStream(fin);
					
					Bitmap bm=BitmapFactory.decodeStream(bin,null,opt);
					ImageView iv=new ImageView(MainGridviewAppActivity.this);
					iv.setImageBitmap(bm);
					iv.setLayoutParams(new LayoutParams(bm.getWidth(),bm.getHeight()));
					iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
					AlertDialog.Builder bu=new Builder(MainGridviewAppActivity.this);
					bu.create();
					
					bu.setView(iv);
					bu.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
					bu.show();
					
					bin.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
        
    }
}