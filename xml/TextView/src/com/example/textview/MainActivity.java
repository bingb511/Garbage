package com.example.textview;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//����
	private TextView hello;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//pro1 start
		hello = (TextView) findViewById(R.id.Hello);
		Drawable[] draw = hello.getCompoundDrawables();
		draw[1].setBounds(0,0,200,100);
		//����setBounds�����������������,�������������˴�������: ����:������������߿�ʼ0dp����200dp�� ����:�������Ϸ�0dp����������200dp
		// �����±�0~3,������:��������  
		hello.setCompoundDrawables(draw[0], draw[1], draw[2], draw[3]);
		//pro1 end 
		
		//pro2 start
		TextView t1 = (TextView)findViewById(R.id.t1);
		String s1 = "ͼƬ��<a href='http://www.baidu.com'><img src='icon' /></a><br /><br />";
		s1 += "<a href='http://www.baidu.com'>�ٶ�һ�£����֪��</a>";
		t1.setText(Html.fromHtml(s1,new Html.ImageGetter() {
			
			@SuppressWarnings("deprecation")
			@Override
			public Drawable getDrawable(String source) {
				// TODO Auto-generated method stub
				Drawable draw = null;
				try{
					java.lang.reflect.Field field = R.drawable.class.getField(source);
					int resourceId = Integer.parseInt(field.get(null).toString());
					draw = getResources().getDrawable(resourceId);
                    draw.setBounds(0, 0, draw.getIntrinsicWidth(), draw.getIntrinsicHeight());
                } catch (Exception e) {
                    e.printStackTrace();
                }
				return draw;
			}
		},null));
		t1.setMovementMethod(LinkMovementMethod.getInstance());  //�������ˣ�����˲�����ת�����ӵĻ������û��Ч��
		// pro2 end
		
		//pro3 start
				//TextView text1 = (TextView)findViewById(R.id.textone);
				SpannableString span = new SpannableString("��ɫ��绰б��ɾ������ɫ�»���ͼƬ");
				//1.���ñ���ɫ,setSpanʱ��Ҫָ����flag,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(ǰ�󶼲�����)
				span.setSpan(new ForegroundColorSpan(Color.RED),0,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				//2.�ó����ӱ���ı�
				span.setSpan(new URLSpan("tel:4155551212"),2,5,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				//3.����ʽ����ı���б�壩
				span.setSpan(new StyleSpan(Typeface.BOLD_ITALIC) , 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				//4.��ɾ���߱���ı�
				span.setSpan(new StrikethroughSpan(), 7, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				//5.���»��߱���ı�
				span.setSpan(new UnderlineSpan(), 10, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				//6.����ɫ���
				span.setSpan(new ForegroundColorSpan(Color.GREEN), 10, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

				//7.//��ȡDrawable��Դ
		        /*@SuppressWarnings("deprecation")
				Drawable d = getResources().getDrawable(R.drawable.icon);
		        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
		        //8.����ImageSpan,Ȼ����ImageSpan���滻�ı�
		        ImageSpan imgspan = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
		        span.setSpan(imgspan, 18, 19, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		        text1.setText(span);*/
				//pro3 end
	}
}