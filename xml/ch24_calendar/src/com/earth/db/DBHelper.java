package com.earth.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBHelper extends SQLiteOpenHelper{

	private static final int VERSION = 1;
	private static final String DB_NAME = "notepad";
	private static final String DB_TABLE_NAME="record";
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		StringBuffer tableCreate = new StringBuffer();
//		注意table后面要有个空格，不加空格SQL语句就会变成"create tablerecord"
		tableCreate.append("create table ")
					.append(DB_TABLE_NAME)
					.append("(")
					.append("id integer primary key autoincrement,")
					.append("title text,")
					.append("date text,")
					.append("weather text,")
					.append("content text")
					.append(")");
		db.execSQL(tableCreate.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
