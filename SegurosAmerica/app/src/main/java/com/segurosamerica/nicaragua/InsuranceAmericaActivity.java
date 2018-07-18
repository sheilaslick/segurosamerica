package com.segurosamerica.nicaragua;

import com.segurosamerica.nicaragua.sa.AccidentsActivity;
import com.segurosamerica.nicaragua.sa.CarsActivity;
import com.segurosamerica.nicaragua.sa.HealthActivity;
import com.segurosamerica.nicaragua.sa.HomeActivity;
import com.segurosamerica.nicaragua.sa.LifeActivity;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class InsuranceAmericaActivity extends Activity {
	
	private ImageButton imageButtonInsuranceAmericaCars;
	private ImageButton imageButtonInsuranceAmericaAccidents;
	private ImageButton imageButtonInsuranceAmericaHealth;
	private ImageButton imageButtonInsuranceAmericaHome;
	private ImageButton imageButtonInsuranceAmericaLife;
	private ImageButton imageButtonInsuranceAmericaOtherInsurance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insurance_america);
		
		buttonsActions();
	}
	
	private void buttonsActions(){
		ImageButton imageButtonInsuranceAmericaBack = (ImageButton) findViewById(R.id.imageButtonInsuranceAmericaBack);
		imageButtonInsuranceAmericaCars = (ImageButton) findViewById(R.id.imageButtonInsuranceAmericaCars);
		imageButtonInsuranceAmericaAccidents = (ImageButton) findViewById(R.id.imageButtonInsuranceAmericaAccidents);
		imageButtonInsuranceAmericaHealth = (ImageButton) findViewById(R.id.imageButtonInsuranceAmericaHealth);
		imageButtonInsuranceAmericaHome = (ImageButton) findViewById(R.id.imageButtonInsuranceAmericaHome);
		imageButtonInsuranceAmericaLife = (ImageButton) findViewById(R.id.imageButtonInsuranceAmericaLife);
		imageButtonInsuranceAmericaOtherInsurance = (ImageButton) findViewById(R.id.imageButtonInsuranceAmericaOtherInsurance);
	
		imageButtonInsuranceAmericaBack.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				finish();
			}
			
		});
		
		imageButtonInsuranceAmericaCars.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				goTo(0);
			}
			
		});
		
		imageButtonInsuranceAmericaAccidents.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				goTo(1);
			}
			
		});
		
		imageButtonInsuranceAmericaHealth.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				goTo(2);
			}
			
		});
		
		imageButtonInsuranceAmericaHome.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				goTo(3);
			}
			
		});
		
		imageButtonInsuranceAmericaLife.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				goTo(4);
			}
			
		});
		
		imageButtonInsuranceAmericaOtherInsurance.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.button_other_insurances)));
				startActivity(browserIntent);
			}
			
		});
		
		
		
	}
	
	private void goTo(int pos) {
		Intent intent = new Intent();
		switch (pos) {
		case 0:
			intent.setClass(getApplicationContext(), CarsActivity.class);
			break;
		case 1:
			intent.setClass(getApplicationContext(), AccidentsActivity.class);
			break;
		case 2:
			intent.setClass(getApplicationContext(), HealthActivity.class);
			break;
		case 3:
			intent.setClass(getApplicationContext(), HomeActivity.class);
			break;
		case 4:
			intent.setClass(getApplicationContext(), LifeActivity.class);
			break;
		}
		
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insurance_america, menu);
		return true;
	}
	
	

}
