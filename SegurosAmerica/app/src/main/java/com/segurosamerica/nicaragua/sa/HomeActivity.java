package com.segurosamerica.nicaragua.sa;

import com.segurosamerica.nicaragua.PageActivity;
import com.segurosamerica.nicaragua.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		buttonsActions();
	}
	
	private void buttonsActions(){
		ImageButton imageButtonHomeBack = (ImageButton) findViewById(R.id.imageButtonHomeBack);
		imageButtonHomeBack.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
			
		});
		
		Button buttonProHome = (Button) findViewById(R.id.buttonProHome);
		buttonProHome.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "pro_home");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
		
		Button buttonProHomePlus = (Button) findViewById(R.id.buttonProHomePlus);
		buttonProHomePlus.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "pro_home_plus");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
	}


}
