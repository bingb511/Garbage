package com.example.edit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ��֤��ķ��ͽ���
 * @author sC
 *
 */
public class MainActivity extends Activity {
   
 private View titleView;//����������
 private LinearLayout titleLayout;//���������ֵ�����
 private Button rightButton;// �˳���ť
 private ProgressBar waite;// Բ�ν�����
 private TextView txtAlert;//��������
 private EditText txt1, txt2, txt3, txt4, txt5, txt6;//������֤���6��EditText
 private String code = "";//���Ҫ���͵���֤��
 private EditText[] txts;
 private static int count = 0;
   
 protected void onCreate(Bundle savedInstanceState) {
   
  super.onCreate(savedInstanceState);
  //�趨���治��ʾ������
  requestWindowFeature(Window.FEATURE_NO_TITLE);
   
  setContentView(R.layout.activity_main);
  initTitle();
  setListener();
   
 }
 /**
  * ������֤��
  */
 private void sendCode() {
  txtAlert.setText("������֤�룬���Ժ�....");
  waite.setVisibility(ProgressBar.VISIBLE);
  for (int i = 0; i < txts.length; i++) {
   Log.e("sc", String.valueOf(i));
   code += txts[i].getText().toString();
   
  }
  Toast.makeText(MainActivity.this, code, Toast.LENGTH_LONG).show();
   
 }
 /**
  * ѭ����ÿ��EditText���Ӽ���
  */
 private void addTextListener() {
  if (txts != null) {
   for (int i = 0; i < txts.length; i++) {
    txts[i].setOnKeyListener(key);
    txts[i].addTextChangedListener(watcher);
   }
  }
 }
   
 OnKeyListener key = new View.OnKeyListener() {
   
  public boolean onKey(View view, int i, KeyEvent keyevent) {
   
   if (i == KeyEvent.KEYCODE_DEL) {
    reset();
   }
   return false;
  }
 };
 /**
  * ���ݸı�ļ����ӿ�
  */
 TextWatcher watcher = new TextWatcher() {
   
  public void onTextChanged(CharSequence charsequence, int i, int j, int k) {
   /**
    * �����ǰ��EditText��û��ֵ����ִ��
    */
   if (j == 0) {
    if (count < txts.length - 1) {
   
     txts[++count].requestFocus();
    } else {
     sendCode();
    }
       
   }
  }
  public void beforeTextChanged(CharSequence charsequence, int i, int j,
    int k) {
  }
  public void afterTextChanged(Editable editable) {
   
  }
 };
 /**
  * �������
  */
 public void reset() {
  txtAlert.setText("��������֤��");
  waite.setVisibility(ProgressBar.INVISIBLE);
  code = "";
  count = 0;
  for (int i = 0; i < txts.length; i++) {
   // ��ոı����
   txts[i].removeTextChangedListener(watcher);
  }
  for (int j = 0; j < txts.length; j++) {
   txts[j].setText("");
   txts[j].clearFocus();
  }
  txt1.requestFocus();
  addTextListener();
 }
   
 /**
  * ���ü�������
  */
 private void setListener() {
  findView();
  addTextListener();
  buttonListener();
 }
 /**
  * ���������ϰ�ť�ļ���
  */
 private void buttonListener() {
  rightButton.setOnClickListener(new View.OnClickListener() {
   
   public void onClick(View view) {
    reset();
   }
  });
 }
 /**
  * ���ؿؼ�
  */
 private void findView() {
  txt1 = (EditText) findViewById(R.id.txtCode1);
  txt1.setFocusable(true);
  txt2 = (EditText) findViewById(R.id.txtCode2);
  txt3 = (EditText) findViewById(R.id.txtCode3);
  txt4 = (EditText) findViewById(R.id.txtCode4);
  txt5 = (EditText) findViewById(R.id.txtCode5);
  txt6 = (EditText) findViewById(R.id.txtCode6);
  txts = new EditText[] { txt1, txt2, txt3, txt4, txt5, txt6 };
  waite = (ProgressBar) findViewById(R.id.waite);
  rightButton = (Button) titleLayout.findViewById(R.id.nav_rightButton);
  txtAlert = (TextView) findViewById(R.id.txtAlert);
 }
   
 /**
  * ��ʼ���Զ��������
  */
 private void initTitle() {
  LayoutInflater inflater = (LayoutInflater) this
    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  titleView = inflater.inflate(R.layout.title, null);
  titleLayout = (LinearLayout) findViewById(R.id.topLinearLayout);
  titleLayout.addView(titleView);
   
 }
 /**
  * ������������ԭ����ͣ�����Ժ�ͬ���������
  */
 protected void onResume() {
  reset();
  super.onResume();
 }
    
}