package com.example.radiobutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final RadioGroup radio = (RadioGroup)findViewById(R.id.rg);
		radio.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(RadioGroup group,int checkedId){
				RadioButton btn = (RadioButton)findViewById(checkedId);
				Toast.makeText(getApplicationContext(), "��ť��ֵ�����ı�,��ѡ��" + btn.getText(), Toast.LENGTH_LONG).show();
				
			}
		});
		
		Button bto = (Button)findViewById(R.id.btn);
		bto.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				for(int i = 0; i<radio.getChildCount();i++){
					RadioButton rb = (RadioButton) radio.getChildAt(i);
					if(rb.isChecked()){
						Toast.makeText(getApplicationContext(), "����ύ��ť��ȡ���ѡ���ǣ�" + rb.getText(),Toast.LENGTH_LONG).show();
					}
				}
			}
		});
	}

	
}