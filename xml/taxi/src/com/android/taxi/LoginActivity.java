package com.android.taxi;

//import net.youmi.android.AdManager;
//import net.youmi.android.banner.AdSize;
//import net.youmi.android.banner.AdView;

import java.util.HashMap;
import java.util.Map;
import com.android.taxi.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
//import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // e2d468f618be14b5��4955a15c0ddb7c5bλ����д��ķ���ID��Ӧ����Կ
        //AdManager.init(this,"e2d468f618be14b5", "4955a15c0ddb7c5b", 30, false);
        
        setContentView(R.layout.activity_login);
        
        ///< ��ʼ��Layout�ؼ������ڼ��ع����ͼ
        //layout = new LinearLayout(LoginActivity.this); 
        //layout.setOrientation(LinearLayout.VERTICAL); 

        ///< ��ʼ�������ͼ
        //AdView adView = new AdView(LoginActivity.this, AdSize.SIZE_320x50); 
        //LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, 
                //LayoutParams.WRAP_CONTENT); 
        //layout.addView(adView, params);

        findViews();
        setListeners();
    }
    
    public void findViews()
    {
        button_login = (Button)findViewById(R.id.login);
        user_name = (EditText)findViewById(R.id.user_name);
        password = (EditText)findViewById(R.id.password);
    }
    
    public void setListeners()
    {
        button_login.setOnClickListener(loginTest);
    }
    
    private Button.OnClickListener loginTest = new Button.OnClickListener()
    {
        @SuppressLint("NewApi")
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            String userName = null;
            String passWord = null;
            
            ///< ���ж��û��Ƿ������û������Ƿ���������
            if ((userName = user_name.getText().toString()).isEmpty())
            {
                if (password.getText().toString().isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "�������û���������", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "�������û�����", Toast.LENGTH_SHORT).show();
                return;
            }
            else if ((passWord = password.getText().toString()).isEmpty())
            {
                Toast.makeText(LoginActivity.this, "����������", Toast.LENGTH_SHORT).show();
                return;
            }
            
            loginHandle(userName, passWord);
            
            //            ///< �����û��������뵽����������У�飬����÷���������ֵ
            //            Map<String, String> params = new HashMap<String, String>();
            //            params.put("user_name", userName);
            //            params.put("password", passWord);
            //            String encode = "utf-8";
            //            
            //            //            Toast.makeText(LoginActivity.this, params.get("user_name"), Toast.LENGTH_SHORT).show();
            //            //            Toast.makeText(LoginActivity.this, params.get("password"), Toast.LENGTH_SHORT).show();
            //
            //            int responseCode = HttpUtils.sendMessage(params, encode);
            //            if (0 == responseCode)
            //            {
            //                Toast.makeText(LoginActivity.this, "��¼�ɹ�", Toast.LENGTH_SHORT).show();
            //            }
            //            else if (1 == responseCode)
            //            {
            //                Toast.makeText(LoginActivity.this, "�û�������!", Toast.LENGTH_SHORT).show();
            //            }
            //            else if (2 == responseCode)
            //            {
            //                Toast.makeText(LoginActivity.this, "�������!", Toast.LENGTH_SHORT).show();
            //            }
            //            else
            //            {
            //                Toast.makeText(LoginActivity.this, "�쳣!", Toast.LENGTH_SHORT).show();
            //            }
                        
            //            ///< �����û��������뵽����������У�飬����÷���������ֵ
            //            // TODO
            //            if (userName.equals("admin") && passWord.equals("123"))
            //            {
            //                Toast.makeText(LoginActivity.this, "��¼�ɹ�", Toast.LENGTH_SHORT).show();
            //            }
            //            else
            //            {
            //                Toast.makeText(LoginActivity.this, "��˶��û���Ϣ��", Toast.LENGTH_SHORT).show();
            //            }
        }

    };
    
    public void loginHandle(final String userName, final String password)
    {
        new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
                ///< �����û��������뵽����������У�飬����÷���������ֵ
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_name", userName);
                params.put("password", password);
                String encode = "utf-8";
                
                //            Toast.makeText(LoginActivity.this, params.get("user_name"), Toast.LENGTH_SHORT).show();
                //            Toast.makeText(LoginActivity.this, params.get("password"), Toast.LENGTH_SHORT).show();

                int responseCode = HttpUtils.sendMessage(params, encode);
                Looper.prepare(); 
                if (0 == responseCode)
                {
                    Toast.makeText(LoginActivity.this, "��¼�ɹ�", Toast.LENGTH_SHORT).show();
                }
                else if (1 == responseCode)
                {
                    Toast.makeText(LoginActivity.this, "�û�������!", Toast.LENGTH_SHORT).show();
                }
                else if (2 == responseCode)
                {
                    Toast.makeText(LoginActivity.this, "�������!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "�쳣!", Toast.LENGTH_SHORT).show();
                }
                Looper.loop();
                
                //ִ����Ϻ��handler����һ������Ϣ 
                handler.sendEmptyMessage(0); 
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //����Handler���� 
    private Handler handler =new Handler(){ 
        @Override 
        //������Ϣ���ͳ�����ʱ���ִ��Handler��������� 
        public void handleMessage(Message msg){ 
            super.handleMessage(msg); 
            // TODO ����UI 
        }
    };
        
    private Button button_login;
    private EditText user_name;
    private EditText password;
    
    LinearLayout layout;
}