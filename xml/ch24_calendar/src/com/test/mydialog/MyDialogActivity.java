package com.test.mydialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.android.alvin.activity.PasswordManage;

import net.blogjava.mobile.calendar.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MyDialogActivity extends Activity {
	RelativeLayout bestRelativeLayout;
	LinearLayout password2;
	Button button;
	ImageButton imagebutton;
	private Button button1;

	private int CHANGE_PWD_SUCCESS = 100;
	private File fileDir;
	private File sdcardDir;
	private File cacheDir;
	private EditText et;
	private EditText et1;
	String path = "sdcard";
	String path1 = path + java.io.File.separator + "aaaaaaaaaaaa.txt";
	File f = new File(path1);

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_login);

		if (f.exists()) {
			password2 = (LinearLayout) findViewById(R.id.password2);
			password2.removeAllViews();
		} else {

		}
		cacheDir = this.getCacheDir();

		bestRelativeLayout = (RelativeLayout) findViewById(R.id.bestRelativeLayout);
		LayoutInflater l = LayoutInflater.from(this);
		et = (EditText) findViewById(R.id.login_password);
		et1 = (EditText) findViewById(R.id.login_password2);
		button1 = (Button) findViewById(R.id.save_password);
		button1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				java.io.PrintWriter bw;
				if (!f.exists()) {
					if (et.getText().toString()
							.equals(et1.getText().toString())) {

						try {

							bw = new java.io.PrintWriter(
									new java.io.FileWriter(new java.io.File(
											path1)));
							String str = et.getText().toString();
							bw.write(str);
							bw.close();
							Intent intent = new Intent();
							intent
									.setClass(
											MyDialogActivity.this,
											com.android.alvin.activity.MainActivity.class);

						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						Toast.makeText(MyDialogActivity.this,
								R.string.password_isnot_equal,
								Toast.LENGTH_LONG).show();
					}
				} else {
					// File file = new File(path);
					String s2 = "";
					String b = "";
					try {

						BufferedReader in = new BufferedReader(
								new FileReader(f));

						while ((s2 = in.readLine()) != null) {
							b += s2;
						}
						System.out.println("b===" + b);
						in.close();
					} catch (Exception e1) {

						e1.printStackTrace();
					}
					if (b.equals(et.getText().toString())) {
						Intent intent = new Intent();
						intent.setClass(MyDialogActivity.this,
								com.android.alvin.activity.MainActivity.class);
						startActivity(intent);

					} else {
						Toast.makeText(MyDialogActivity.this,
								R.string.error_password, Toast.LENGTH_LONG)
								.show();

					}

				}
			}

		});

		final LinearLayout myLinearLayout = (LinearLayout) l.inflate(
				R.layout.dialogjpzy, null);
		myLinearLayout.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.out.println("click on outside of the dialog");
			}
		});

		myLinearLayout.setVisibility(LinearLayout.GONE);
		Button jpzyMakeButton = (Button) myLinearLayout
				.findViewById(R.id.jpzyMakeButton);
		jpzyMakeButton.setText("Ë½ÃÜ¼ÇÊÂ±¾");
		jpzyMakeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				myLinearLayout.setVisibility(LinearLayout.GONE);
				Intent intent = new Intent();

			}
		});

		bestRelativeLayout.addView(myLinearLayout);
		// button=(Button)findViewById(R.id.button);
		// button.setOnClickListener(new View.OnClickListener(){
		// public void onClick(View v) {
		// myLinearLayout.setVisibility(LinearLayout.VISIBLE);
		// }
		// });
	}
}