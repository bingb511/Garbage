package com.example.textview;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//定义
	private TextView hello;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//pro1 start
		hello = (TextView) findViewById(R.id.Hello);
		Drawable[] draw = hello.getCompoundDrawables();
		draw[1].setBounds(0,0,200,100);
		//调用setBounds设置左上右下坐标点,比如这里设置了代表的是: 长是:从离文字最左边开始0dp处到200dp处 宽是:从文字上方0dp处往上延伸200dp
		// 数组下表0~3,依次是:左上右下  
		hello.setCompoundDrawables(draw[0], draw[1], draw[2], draw[3]);
		//pro1 end 
		
		//pro2 start
		TextView t1 = (TextView)findViewById(R.id.t1);
		String s1 = "图片：<a href='http://www.baidu.com'><img src='icon' /></a><br /><br />";
		s1 += "<a href='http://www.baidu.com'>百度一下，你就知道</a>";
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
		t1.setMovementMethod(LinkMovementMethod.getInstance());  //这句加上了，点击了才有跳转，不加的话点击的没有效果
		// pro2 end
		
		//pro3 start
		TextView text1 = (TextView)findViewById(R.id.textone);
		SpannableString span = new SpannableString("红色打电话斜体删除线绿色下划线图片");
		//1.设置背景色,setSpan时需要指定的flag,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括)
		span.setSpan(new ForegroundColorSpan(Color.RED),0,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//2.用超链接标记文本
		span.setSpan(new URLSpan("tel:4155551212"),2,5,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//3.用样式标记文本（斜体）
		span.setSpan(new StyleSpan(Typeface.BOLD_ITALIC) , 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//4.用删除线标记文本
		span.setSpan(new StrikethroughSpan(), 7, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//5.用下划线标记文本
		span.setSpan(new UnderlineSpan(), 10, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//6.用颜色标记
		span.setSpan(new ForegroundColorSpan(Color.GREEN), 10, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		//7.//获取Drawable资源
        @SuppressWarnings("deprecation")
		Drawable d = getResources().getDrawable(R.drawable.icon);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        //8.创建ImageSpan,然后用ImageSpan来替换文本
        ImageSpan imgspan = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        span.setSpan(imgspan, 15, 17, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        text1.setText(span);
		//pro3 end
		        
		//pro4 start
        
        TextView tt1 = (TextView)findViewById(R.id.tt1);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<20; i++){
        	sb.append("好友" + i + ", ");
        }
        String likeUsers = sb.substring(0, sb.lastIndexOf(", ")).toString();
        //TextView可以上下滑动//setMovementMethod上下滑动
        //LinkMovementMethod它实现了一系列的方法，比如它的onKeyDown调用ClickableSpan的onClick, URLSpan实现了ClickableSpan ,用户点击一个url的话，就会调用LinkMovementMethod::onKeyDown， 继而调用URLSpan的onClick
        //对于抽象类，要想对其实例化，只能用getInstance方法，是不能new出来的
        tt1.setMovementMethod(LinkMovementMethod.getInstance());
        //setText将tt1对象的现实内容设置为
        //TextView.BufferType并不是TextView的内部类，是一个枚举类型,SPANNAABLE则用于设置如TextView
        tt1.setText(addClickPart1(likeUsers), TextView.BufferType.SPANNABLE);
      //定义一个点击每个部分文字的处理方法SpannableStringBuilder通常用于显示文字，有时候也可以夹杂着图片，比如qq中有人的名称中有文字夹杂着图片的昵称
        //一些控件，变量。我都会声明为private
	}
        SpannableStringBuilder addClickPart1(String str){
        	//导入图片资源
        	//要让图片替代指定的文字就要用ImageSpan
        	//ImageSpan(Context context, int resourceId) 
        	ImageSpan imgspan1 = new ImageSpan(MainActivity.this,R.drawable.icon);
        	//需要处理的文本，p.是需要被替代的文本 
        	SpannableString spanStr = new SpannableString("p.");
        	//开始替换
        	spanStr.setSpan(imgspan1,0,1,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        	//创建一个SpannableStringBuilder对象，连接多个字符串
        	SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);
        	ssb.append(str);
        	//定义一个数组
        	String[] likeUsers1 = str.split(", ");
        	//
        	if(likeUsers1.length > 0 ){
        		for(int i = 0; i < likeUsers1.length; i++){
        			final String name = likeUsers1[i];
        			final int start = str.indexOf(name) + spanStr.length();
        			ssb.setSpan(new ClickableSpan(){
        				public void onClick(View widget){
        					//Toast是为了友好的提醒用户的提示框，MainActivity.this表示在MainActivity里面显示，LENGTH_SHORT表示Toast的显示时间，这里调用的时间是系统定义好的时间，.show是将定义好的Toast显示出来。
        					Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
        				}
        				public void updateDrawState(TextPaint ds){
        					//super调用父类的方法或者属性
        					super.updateDrawState(ds);
        					//删除下划线，设置字体颜色为蓝色
        					ds.setColor(Color.BLUE);
        					ds.setUnderlineText(false);
        				}
        			},start,start + name.length(),0);
        		}
        	}
        	
        	return ssb.append("等" + likeUsers1.length + "个人觉得很赞");
        }
/*        
 * spannable对象实现对文本进行的各种特别的设置比如颜色，大小等效果；
 * 构建spannable：
 * Spannable spn = new SpannableString("字符串");
 * 或者
 * SpannableStringBuilder对象来进行构建。
 * 构建好了，就可以设置样式：
 * spannable.setSpan(Obj what,int start,int end,int flags);
 *  参数what是具体样式的实现对象，start则是该样式开始的位置，end对应的是样式结束的位置，参数flags，定义在Spannable中的常量，常用的有：
 *  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE --- 不包含两端start和end所在的端点              (a,b)
 *  Spanned.SPAN_EXCLUSIVE_INCLUSIVE --- 不包含端start，但包含end所在的端点       (a,b]
 *  Spanned.SPAN_INCLUSIVE_EXCLUSIVE --- 包含两端start，但不包含end所在的端点   [a,b)
 *  Spanned.SPAN_INCLUSIVE_INCLUSIVE--- 包含两端start和end所在的端点                     [a,b]
 *  但实际测试这其中似乎并未有差别，而在start和end相同的情况下，则只对start所在字符的当前行起作用。
 *  }
*/      
        //pro4 end
        


}
