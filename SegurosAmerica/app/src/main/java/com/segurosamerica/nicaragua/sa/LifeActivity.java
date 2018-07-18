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

public class LifeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_life);
		
		buttonsActions();
	}

	private void buttonsActions(){
		ImageButton imageViewCarsBack = (ImageButton) findViewById(R.id.imageButtonViewLifeBack);
		imageViewCarsBack.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
			
		});
		
		Button buttonProLifeGoes = (Button) findViewById(R.id.buttonProLifeGoes);
		buttonProLifeGoes.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "pro_life_goes");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
		
		Button buttonProFamilyPlan = (Button) findViewById(R.id.buttonProFamilyPlan);
		buttonProFamilyPlan.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "pro_family_plan");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
		
		Button buttonProLifeCredo = (Button) findViewById(R.id.buttonProLifeCredo);
		buttonProLifeCredo.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "pro_life_credo");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
		
		Button buttonProBurial = (Button) findViewById(R.id.buttonProBurial);
		buttonProBurial.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "pro_burial");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
		
		Button buttonProInsuranceDebtors = (Button) findViewById(R.id.buttonProInsuranceDebtors);
		buttonProInsuranceDebtors.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "pro_insurance_debtors");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
	}

}
