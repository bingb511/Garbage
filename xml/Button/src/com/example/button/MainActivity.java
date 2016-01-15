package com.example.button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button btn1,btn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById(R.id.btnOne);
		btn2 = (Button) findViewById(R.id.btnTwo);
		
		btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
			 if(btn2.getText().toString().equals("��ť������")){
				btn1.setEnabled(false);
				btn2.setText("��ť����");
				
			} else {
				btn1.setEnabled(true);
				btn2.setText("��ť������");
			}
		}
		});
	}
}