package com.example.bing0;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class IndexActivity extends FragmentActivity {
	
	private ViewPager viewpager;    
    //幻灯片图片资源
    private int [] imgArray={R.drawable.view1,R.drawable.view2,R.drawable.view3};
    //图片数组
    private ArrayList<View> viewlist;

    private ImageView imageView;
    //点点
    private ViewGroup pointgroup;
    private ImageView[] pointarr;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		
		//初始化
        viewpager =(ViewPager) findViewById(R.id.viewpager);
        pointgroup =(ViewGroup) findViewById(R.id.pointgroup);
        viewlist = new ArrayList<View>();
        
        //将图片装载到数组    
        for(int i=0;i<imgArray.length;i++){
            imageView =new ImageView(this);
            //设置图片
            imageView.setBackgroundResource(imgArray[i]);
            //设置图片id
            imageView.setId(imgArray[i]);
            viewlist.add(imageView);
            
        }
        
        /*
         * viewpager加载适配器
         * MyPageAdapter为自定义适配器
         * */
        
        //传递上下文
        Context context = MainActivity.this;
        MyPageAdapter pageadapter2 =new MyPageAdapter(viewlist,context);
        viewpager.setAdapter(pageadapter2);        
        
        
        //点数组
        pointarr=new ImageView[imgArray.length];
        //把点加入布局，和设置点状态
        for(int i=0;i<imgArray.length;i++){
            ImageView point=new ImageView(this);        
            
            //设置点大小
            point.setLayoutParams(new LayoutParams(10,10)); 
            pointarr[i]=point;
            //状态处理
            if(i==0){
                pointarr[i].setBackgroundResource(R.drawable.viewpage_point_focused);
            }else{
                pointarr[i].setBackgroundResource(R.drawable.viewpage_point_unfocused);
            }
            //加入到容器
            pointgroup.addView(pointarr[i]);
        }
        
    
        //viewpager设置监听器
        pointChangeListener pointListener=new pointChangeListener();
        viewpager.setOnPageChangeListener(pointListener);
        //点击图片
        
        
        
    }
        
    
    /*
     * viewpage监听器
     */
    
    public class pointChangeListener implements OnPageChangeListener{

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
            
        }

        @Override
        //比如切换到第二张图片，相应索引的点为选择状态，其他为未选中状态
        public void onPageSelected(int arg0) {
            
            // TODO Auto-generated method stub
            for(int i=0;i<pointarr.length;i++){
                pointarr[arg0].setBackgroundResource(R.drawable.viewpage_point_focused);    
                if(arg0 !=i){
                pointarr[i].setBackgroundResource(R.drawable.viewpage_point_unfocused);    
                }
            }
            
        }
        
        
    }
    

    
}
			
	}
	
	
}