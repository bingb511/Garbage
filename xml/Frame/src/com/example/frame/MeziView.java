package com.example.frame;

import android.annotation.SuppressLint;
import android.content.Context;  
import android.graphics.Bitmap;  
import android.graphics.BitmapFactory;  
import android.graphics.Canvas;  
import android.graphics.Paint;  
import android.view.View;

public class MeziView extends View {
	
	//������ر�����ͼ���X,Y����
	public float bitmapX;
	public float bitmapY;
	
	public MeziView(Context context){
		
		super(context);
		
		//����ͼ�����ʼ����
		bitmapX = 0;
		bitmapY = 200;
	}
	
	
	//��дView���onDraw����
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		//��������ʵ����
		Paint paint = new Paint();
		//����ͼƬ����λͼ����
		Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.s_jump);
		//����ͼ��
		canvas.drawBitmap(bitmap, bitmapX, bitmapY,paint);  
		//�ж��Ƿ����
		if(bitmap.isRecycled())  
        {  
            bitmap.recycle();  
        }  
		
	}
	
		
}