package com.example.frame2;

import java.util.Timer;    
import java.util.TimerTask;    
import android.os.Bundle;    
import android.os.Handler;    
import android.os.Message;    
import android.widget.FrameLayout;
import android.annotation.SuppressLint;
import android.app.Activity;    
import android.graphics.drawable.Drawable;

public class MainActivity extends Activity {    
    //��ʼ������,֡����    
    FrameLayout frame = null;    
    //�Զ���һ�����ڶ�ʱ����UI�����handler�����    
        @SuppressLint("HandlerLeak")
		Handler handler = new Handler()    
        {    
            int i = 0;    
            @Override    
            public void handleMessage(Message msg) {    
            //�ж���Ϣ�Ƿ�Ϊ��Ӧ�÷�����    
                if(msg.what == 0x123)    
                {    
                    i++;    
                    move(i % 8 );    
                }    
                super.handleMessage(msg);    
             }    
        };          
            
    //������·ʱ�л�ͼƬ�ķ���    
    void move(int i)    
    {    
        @SuppressWarnings("deprecation")
		Drawable a = getResources().getDrawable(R.drawable.s_1);    
        @SuppressWarnings("deprecation")
		Drawable b = getResources().getDrawable(R.drawable.s_2);    
        @SuppressWarnings("deprecation")
		Drawable c = getResources().getDrawable(R.drawable.s_3);    
        @SuppressWarnings("deprecation")
		Drawable d = getResources().getDrawable(R.drawable.s_4);    
        @SuppressWarnings("deprecation")
		Drawable e = getResources().getDrawable(R.drawable.s_5);    
        @SuppressWarnings("deprecation")
		Drawable f = getResources().getDrawable(R.drawable.s_6);    
        @SuppressWarnings("deprecation")
		Drawable g = getResources().getDrawable(R.drawable.s_7);    
        @SuppressWarnings("deprecation")
		Drawable h = getResources().getDrawable(R.drawable.s_8);    
        //ͨ��setForeground������ǰ��ͼ��    
        switch(i)    
        {    
            case 0:    
                frame.setForeground(a);    
                break;    
            case 1:    
                frame.setForeground(b);    
                break;    
            case 2:    
                frame.setForeground(c);    
                break;    
            case 3:    
                frame.setForeground(d);    
                break;    
            case 4:    
                frame.setForeground(e);    
                break;    
            case 5:    
                frame.setForeground(f);    
                break;    
            case 6:    
                frame.setForeground(g);    
                break;    
            case 7:    
                frame.setForeground(h);    
                break;    
        }    
    }    
        
    @Override    
    protected void onCreate(Bundle savedInstanceState) {    
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.activity_main);    
            
        frame = (FrameLayout) findViewById(R.id.myframe);    
        //����һ����ʱ������,��ʱ������Ϣ��handler    
        new Timer().schedule(new TimerTask() {    
                
            @Override    
            public void run() {    
                //����һ������Ϣ��֪ͨϵͳ�ı�ǰ��ͼƬ    
                handler.sendEmptyMessage(0x123);    
            }    
        }, 0,170);     
    }    
}  