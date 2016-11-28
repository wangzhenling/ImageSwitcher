package com.example.imageswitcher;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {

	private Button btnBack,btnNext;
	private ImageSwitcher switcher;
	List<Drawable> list = new ArrayList<Drawable>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnBack=(Button) this.findViewById(R.id.btnBack);
		btnNext=(Button) this.findViewById(R.id.btnNext);
		switcher=(ImageSwitcher) this.findViewById(R.id.imageSwitcher);
		
		switcher.setFactory(new ViewFactory(){

			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				ImageView imageView=new ImageView(MainActivity.this);
				imageView.setBackgroundColor(0xff0000);
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				return imageView;
			}	
		});
		
		switcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
		switcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
		
		list.add(getResources().getDrawable(R.drawable.a));
		list.add(getResources().getDrawable(R.drawable.b));
		list.add(getResources().getDrawable(R.drawable.c));
		switcher.setImageResource(R.drawable.a);
		switcher.setImageResource(R.drawable.b);
		switcher.setImageResource(R.drawable.c);
		
		ChangeListener myChangeListener=new ChangeListener();
		btnBack.setOnClickListener(myChangeListener);
		btnNext.setOnClickListener(myChangeListener);
	}
	private class ChangeListener implements OnClickListener{

		int index=1;
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
	         case R.id.btnBack:           //向前箭头按钮按键处理
	             index--;
	             if (index < 0) {
	                 index = list.size() - 1;
	             }
	             switcher.setImageDrawable(list.get(index));
	             break;
	         case R.id.btnNext:              //向后箭头按钮按键处理
	             index++;
	             if (index >= list.size()) {
	                 index = 0;
	             }
	             switcher.setImageDrawable(list.get(index));
	             break;
			}
		}
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
