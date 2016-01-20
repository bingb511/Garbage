package com.example.bingb511;

import android.app.TabActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class IndexActivity extends TabActivity {

	private TabHost tabHost;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);

		tabHost = getTabHost();// ��TabActivity�����ȡ����Tab��TabHost
		addTab(); // ���ӱ�ǩ
		tabHost.setBackgroundColor(Color.argb(255, 255, 255, 1)); // ����tabhost����ɫ
		tabHost.setBackgroundResource(R.drawable.bg_h); // ����tabhost�ı���ͼƬ
	}

	private void addTab() {
		// setIndicator()�˷����������ñ�ǩ��ͼ��
		// ָ������Ϊһ��TextView --->public TabHost.TabSpec setContent(int viewId)
		// �˷�����Ҫһ�� viewId ��Ϊ����
		// newTabSpec�����±�ǩone
		// setIndicator���ñ�ǩ����
		// setContent���øñ�ǩ�Ĳ�������
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("��������", getResources().getDrawable(R.drawable.version))
				.setContent(R.id.tab1));
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("���ϱ���", getResources().getDrawable(R.drawable.version))
				.setContent(R.id.tab2));
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("ѹ��ͼƬ", getResources().getDrawable(R.drawable.version))
				.setContent(R.id.tab3));
	}
}