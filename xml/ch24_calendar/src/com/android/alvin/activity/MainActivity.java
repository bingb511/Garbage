package com.android.alvin.activity;

import java.util.ArrayList;
import java.util.List;

import net.blogjava.mobile.calendar.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.alvin.adapter.NoteAdapter;
import com.android.alvin.db.DBOper;

public class MainActivity extends Activity {

	private ListView lv;
	private TextView tv;
	private DBOper db;
	private Cursor cursor;
	private List<String> items;
	private List<String> times;
	private List<String> ids;

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_2);
		db = new DBOper(this);

		lv = (ListView) findViewById(R.id.note_list);
		tv = (TextView) findViewById(R.id.note_textview);

		refresh();

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long arg3) {

			}

		});
		if (items.size() != 0)
			edit(0);
		else
			addNewNote();

		lv.setOnItemLongClickListener(new ListView.OnItemLongClickListener() {
			
			public boolean onItemLongClick(AdapterView<?> arg0, View v,
					final int position, long arg3) {
				OnClickListener listener = new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							edit(position);
							break;
						case 1:
							delete(position);
							break;
						case 2:
							setClock(position);
							break;
						}
					}

				};
				new AlertDialog.Builder(MainActivity.this).setTitle(
						R.string.operate)
						.setItems(R.array.oper_items, listener)
						.setNegativeButton(R.string.cancel,
								new DialogInterface.OnClickListener() {
									
									public void onClick(DialogInterface dialog,
											int which) {
									}

								}).show();
				return false;
			}

		});

	}

	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			refresh();
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, R.string.new_note);
		// menu.add(0,2,1,R.string.refresh);
		// menu.add(0,3,2,R.string.change_password);
		menu.add(0, 4, 3, R.string.exit);

		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case 1:
			addNewNote();
			break;
		case 2:
			refresh();
			break;
		case 3:
			changePassword();
			break;
		case 4:
			finish();
			break;
		}
		return true;
	}

	public void addNewNote() {
		Intent intent = new Intent();
		intent.setClass(this, EditNoteActivity.class);
		this.startActivityForResult(intent, 0);
	}

	public void refresh() {
		cursor = db.selectNotes();
		items = new ArrayList<String>();
		times = new ArrayList<String>();
		ids = new ArrayList<String>();
		int count = cursor.getCount();
		if (count > 0) {
			tv.setText(R.string.note_list);
			for (int i = 0; i < count; i++) {
				cursor.moveToPosition(i);
				ids.add(cursor.getString(0));
				items.add(cursor.getString(1));
				times.add(cursor.getString(2));
			}
		} else {
			tv.setText(R.string.notepad_is_null);
		}
		lv.setAdapter(new NoteAdapter(this, items, times, ids));
	}

	public void edit(int position) {
		String id = ids.get(position);
		String content = items.get(position);
		Intent intent = new Intent();
		intent.setClass(MainActivity.this,
				com.android.alvin.activity.EditNoteActivity.class);
		intent.putExtra("id", id);
		intent.putExtra("content", content);
		this.startActivityForResult(intent, 0);
	}

	public void delete(final int position) {
		new AlertDialog.Builder(this).setTitle(R.string.warn).setMessage(
				R.string.confirm_delete).setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						String id = ids.get(position);
						db.deleteNote(id);
						Toast.makeText(MainActivity.this,
								R.string.delete_success, Toast.LENGTH_LONG);
						refresh();
					}

				}).setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
					}

				}).show();
	}

	public void setClock(int position) {
		Intent intent = new Intent(this, AlarmActivity.class);
		intent.putExtra("id", ids.get(position));
		this.startActivityForResult(intent, 1);
	}

	public void changePassword() {
		Intent intent = new Intent(this, PasswordManage.class);
		startActivityForResult(intent, 0);
	}

}