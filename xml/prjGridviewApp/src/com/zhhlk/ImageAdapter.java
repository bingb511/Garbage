package com.zhhlk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.media.ThumbnailUtils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.*;
import java.io.*;
public class ImageAdapter extends BaseAdapter {
	private Context ctx;
	private List<Map<String,Object>> lsmap;
	public ImageAdapter(Context ctx,List<Map<String,Object>> lsmap){
		this.ctx=ctx;
		this.lsmap=lsmap;
		if(this.lsmap==null){
			this.lsmap=new ArrayList<Map<String,Object>>();
		}
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lsmap.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return lsmap.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView iv=new ImageView(ctx);
		iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
		
		//设置图片在GridView上的大小
		iv.setLayoutParams(new GridView.LayoutParams(100, 100));
		iv.setPadding(8, 8, 8, 8);//设置边框大小
		Map<String,Object> map=lsmap.get(position);
		String fname=(String) map.get("fname");
		BitmapFactory.Options opt=new BitmapFactory.Options();
		opt.inPreferredConfig=Config.RGB_565;
		opt.inPurgeable=true;
		opt.inSampleSize=5;
		opt.inInputShareable=true;
		File fl=new File(fname);
		try {
			FileInputStream fin=new FileInputStream(fl);
			BufferedInputStream bin=new BufferedInputStream(fin);
			
			Bitmap bm=BitmapFactory.decodeStream(bin,null,opt);
			Bitmap thbm=ThumbnailUtils.extractThumbnail(bm, 100,100);
			iv.setImageBitmap(thbm);//将略缩图设置到ImageView上
			bin.close();
			if(!bm.isRecycled()){
				bm.recycle();
				System.gc();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return iv;
	}

}
