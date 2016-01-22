package net.blogjava.mobile.calendar;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
/*
 * @about
 */

public class About extends Activity
{
	private TextView tvAbout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		tvAbout = (TextView) findViewById(R.id.tvAbout);
		String about = "万年历（Android版）1.0\n\n";
		about += "团队：瑞思手机游戏团队\n\n";
		about += "网名：淡入雪\n\n";
		about += "博客：http://nokiaguy.blogjava.net\n\n";
		about += "网址：http://www.enterise.cn/";
		tvAbout.setText(about);
	}
}
