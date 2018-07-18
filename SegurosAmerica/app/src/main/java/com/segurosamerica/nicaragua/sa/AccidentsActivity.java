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

public class AccidentsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accidents);
		
		buttonsActions();
	}

	private void buttonsActions(){
		ImageButton imageButtonAccidentsBack = (ImageButton) findViewById(R.id.imageButtonAccidentsBack);
		imageButtonAccidentsBack.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
			
		});
		
		Button buttonAccidentsIndividualOrFamily = (Button) findViewById(R.id.buttonAccidentsIndividualOrFamily);
		buttonAccidentsIndividualOrFamily.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "accidents_individual_or_family");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
		
		Button buttonAccidentsPersonalInjurySchool = (Button) findViewById(R.id.buttonAccidentsPersonalInjurySchool);
		buttonAccidentsPersonalInjurySchool.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "accidents_personal_injury_school");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
		
		Button buttonAccidentsTransportPersonalAccident = (Button) findViewById(R.id.buttonAccidentsTransportPersonalAccident);
		buttonAccidentsTransportPersonalAccident.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("raw_file", "accidents_transport_personal_accident");
				intent.setClass(getApplicationContext(), PageActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
			
		});
	}

}
