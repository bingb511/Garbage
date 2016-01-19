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
 * 验证码的发送界面
 * @author sC
 *
 */
public class MainActivity extends Activity {
   
 private View titleView;//标题栏布局
 private LinearLayout titleLayout;//标题栏布局的容器
 private Button rightButton;// 退出按钮
 private ProgressBar waite;// 圆形进度条
 private TextView txtAlert;//提醒文字
 private EditText txt1, txt2, txt3, txt4, txt5, txt6;//接收验证码的6个EditText
 private String code = "";//最后要发送的验证码
 private EditText[] txts;
 private static int count = 0;
   
 protected void onCreate(Bundle savedInstanceState) {
   
  super.onCreate(savedInstanceState);
  //设定界面不显示标题栏
  requestWindowFeature(Window.FEATURE_NO_TITLE);
   
  setContentView(R.layout.activity_main);
  initTitle();
  setListener();
   
 }
 /**
  * 发送验证码
  */
 private void sendCode() {
  txtAlert.setText("发送验证码，请稍候....");
  waite.setVisibility(ProgressBar.VISIBLE);
  for (int i = 0; i < txts.length; i++) {
   Log.e("sc", String.valueOf(i));
   code += txts[i].getText().toString();
   
  }
  Toast.makeText(MainActivity.this, code, Toast.LENGTH_LONG).show();
   
 }
 /**
  * 循环给每个EditText添加监听
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
  * 内容改变的监听接口
  */
 TextWatcher watcher = new TextWatcher() {
   
  public void onTextChanged(CharSequence charsequence, int i, int j, int k) {
   /**
    * 如果当前的EditText中没有值，则执行
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
  * 清空数据
  */
 public void reset() {
  txtAlert.setText("请输入验证码");
  waite.setVisibility(ProgressBar.INVISIBLE);
  code = "";
  count = 0;
  for (int i = 0; i < txts.length; i++) {
   // 清空改变监听
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
  * 设置监听动作
  */
 private void setListener() {
  findView();
  addTextListener();
  buttonListener();
 }
 /**
  * 标题栏右上按钮的监听
  */
 private void buttonListener() {
  rightButton.setOnClickListener(new View.OnClickListener() {
   
   public void onClick(View view) {
    reset();
   }
  });
 }
 /**
  * 加载控件
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
  * 初始化自定义标题栏
  */
 private void initTitle() {
  LayoutInflater inflater = (LayoutInflater) this
    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  titleView = inflater.inflate(R.layout.title, null);
  titleLayout = (LinearLayout) findViewById(R.id.topLinearLayout);
  titleLayout.addView(titleView);
   
 }
 /**
  * 当程序因其他原因暂停返回以后，同样清空数据
  */
 protected void onResume() {
  reset();
  super.onResume();
 }
    
}
