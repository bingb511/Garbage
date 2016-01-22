package com.earth;
import java.util.ArrayList;
import java.util.HashMap;

import net.blogjava.mobile.calendar.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.earth.db.DBHelper;

public class Main2 extends Activity {
	
	private static final int INSERT_ID = Menu.FIRST;
	private static final int ABOUT_ID = Menu.FIRST + 1;
	private static final int EXIT_ID = Menu.FIRST + 2;
	ListView list;
	LinearLayout linearLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        list = (ListView)findViewById(R.id.list);
        DBHelper dbHelper = new DBHelper(Main2.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("record", null, null, null, null, null, null);
		ArrayList arrayList = new ArrayList();
		while(cursor.moveToNext()){
			HashMap map = new HashMap();
			map.put("title", cursor.getString(cursor.getColumnIndex("title")));
			map.put("date", cursor.getString(cursor.getColumnIndex("date")));
			map.put("weather", cursor.getString(cursor.getColumnIndex("weather")));
			map.put("content", cursor.getString(cursor.getColumnIndex("content")));
			arrayList.add(map);
		}
    
        SimpleAdapter listAdapter = new SimpleAdapter(this,
        											arrayList,
        											R.layout.list_item,
        											new String[]{"title","date"},
        											new int[]{R.id.title,R.id.date});
        list.setAdapter(listAdapter);
        
        if(arrayList.size() == 0){
        	Drawable nodata_bg = getResources().getDrawable(R.drawable.nodata_bg);
        	linearLayout.setBackgroundDrawable(nodata_bg);
        	setTitle("没有任何数据");
        }
        
        list.setOnItemClickListener(new OnItemClickListener(){

/*
 * parent:代表所有的数据,使用HashMap的key和value可以取到里边所有的数据
 * View:其中一项的布局
 * position:点中的一项
 * id:标识 
 * */        	
		
			public void onItemClick(AdapterView<?> parent, View arg1, int position,
					long id) {
//				取到单击的那一项的map,里边有arrayList所有的数据
				HashMap map = (HashMap)parent.getItemAtPosition(position);
				Intent intent = new Intent(Main2.this,Detail.class);
				intent.putExtra("map", map);
				startActivity(intent);
				finish();
			}
        	
        });

    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0,INSERT_ID, 1,"写日记");
		menu.add(0,ABOUT_ID,2,"关于");
		menu.add(0,EXIT_ID,3,"退出");
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
			case INSERT_ID:{
				Intent intent = new Intent(this,AddNew.class);
				Main2.this.startActivity(intent);
				finish();
				break;
			}
			case ABOUT_ID:{
				Toast.makeText(Main2.this, "生活要有目标，当你没有目标的时候，生活也就失去了意义", Toast.LENGTH_LONG).show();
				break;
			}
			case EXIT_ID:{
				finish();
			}
		}
		return super.onOptionsItemSelected(item);
	}
    
}