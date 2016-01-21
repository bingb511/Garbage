package com.zhhlk;
import java.util.*;
import java.io.*;

import android.util.Log;
public class FileList {
	private static List<Map<String,Object>> lsmap=new ArrayList<Map<String,Object>>();;
	public static List<Map<String,Object>> findFile(String fpath){
		
		File fl=new File(fpath);
		File[] fls=fl.listFiles();
		if(fls!=null){
			for (int i = 0; i < fls.length; i++) {
				if(fls[i].isDirectory()){
					findFile(fls[i].getAbsolutePath());
				}else{
					
					Map<String,Object> map=new HashMap<String, Object>();
					String fname=fls[i].getAbsolutePath();
					Log.v("fpath-->",fname);
					String ext=fname.substring(fname.lastIndexOf("."),fname.length());
					Log.v("ext===>", ext);
					if(ext.equalsIgnoreCase(".jpg")||ext.equalsIgnoreCase(".png")||ext.equalsIgnoreCase(".icon")){
						Log.v("ÅÐ¶Ï³É¹¦","===");
						map.put("fname",fname);
						lsmap.add(map);
					}
				}
			}
		}
		return lsmap;
	}
}
