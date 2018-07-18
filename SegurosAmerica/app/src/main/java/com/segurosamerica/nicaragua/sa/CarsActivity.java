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

public class CarsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cars);
		
		buttonsActions();
	}
	
	private void buttonsActions(){
		ImageButton imageViewCarsBack = (ImageButton) findViewById(R.id.imageViewCarsBack);
		imageViewCarsBack.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
			
		});
		
		Button buttonProCarsCar = (Button) findViewById(R.id.buttonProCarsCar);
		buttonProCarsCar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "cars_pro_car");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
		
		Button buttonProMotorVehicleLiabilityInsurance = (Button) findViewById(R.id.buttonProMotorVehicleLiabilityInsurance);
		buttonProMotorVehicleLiabilityInsurance.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "cars_compulsory_insurance");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
	}

}
