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

public class HealthActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health);
		
		buttonsActions();
	}

	private void buttonsActions(){
		ImageButton imageViewHealthBack = (ImageButton) findViewById(R.id.imageViewHealthBack);
		imageViewHealthBack.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
			
		});
		
		Button buttonProHealthGolden = (Button) findViewById(R.id.buttonProHealthGolden);
		buttonProHealthGolden.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "pro_health_golden");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
		
		
		Button buttonProHealthSilver = (Button) findViewById(R.id.buttonProHealthSilver);
		buttonProHealthSilver.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "pro_health_silver");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
		
		Button buttonHospitalInsurance = (Button) findViewById(R.id.buttonHospitalInsurance);
		buttonHospitalInsurance.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "hospital_insurance");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
	}

}
