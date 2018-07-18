package com.segurosamerica.nicaragua;

import com.segurosamerica.nicaragua.utils.Database;
import com.segurosamerica.nicaragua.utils.OdbPolicy;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DigitalCardActivity extends Activity {
	
	private Database database = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_digital_card);
		
		database = new Database(this);

		buttonsActions();
		loadData();
	}
	
	private void loadData(){
		Cursor data = database.getAllPolicies();
		
		if (data.getCount() > 0) {

			LayoutInflater inflater = (LayoutInflater) this
					.getApplicationContext().getSystemService(
							Context.LAYOUT_INFLATER_SERVICE);
			
			LinearLayout container = (LinearLayout) findViewById(R.id.linearLayoutDigitalCardLoader);
			
			container.removeAllViews();
			
			int i = 0;
			
			while (data.moveToNext()) {
				final OdbPolicy policy = new OdbPolicy(
						database.getPolicy(data.getString(data
								.getColumnIndex("policy_id"))));
				
				View myViewSeparator = inflater.inflate(R.layout.item_separator, null);
				
				View myView = inflater.inflate(R.layout.item_digital_card, null);
				
				TextView textViewInsured = (TextView) myView.findViewById(R.id.textViewInsured);
				textViewInsured.setText( policy.getInsured() );
				
				TextView textViewInsurancePolicyNumber = (TextView) myView.findViewById(R.id.textViewInsurancePolicyNumber);
				textViewInsurancePolicyNumber.setText( policy.getInsuranceNumber() );
				
				TextView textViewValidity = (TextView) myView.findViewById(R.id.textViewValidity);
				textViewValidity.setText( policy.getValidity() );
				
				TextView textViewExpires = (TextView) myView.findViewById(R.id.textViewExpires);
				textViewExpires.setText( policy.getExpires() );
				
				myView.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent();
						intent.setClass(getApplicationContext(),
								DigitalCardFormActivity.class);
						intent.putExtra("id", policy.getId());
						intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
								| Intent.FLAG_ACTIVITY_CLEAR_TOP
								| Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent);
						finish();
					}

				});
				
				container.addView(myView);
				
				if( (i+1) == data.getCount() ){
					
				}
				else{
					container.addView(myViewSeparator);
				}
				
				
				
				i++;
			}
			data.close();
		}
	}

	private void buttonsActions() {

		ImageView imageViewDigitalCardBack = (ImageView) findViewById(R.id.imageViewDigitalCardBack);
		ImageView imageViewDigitalCardAdd = (ImageView) findViewById(R.id.imageViewDigitalCardAdd);
		imageViewDigitalCardBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goToHome();
			}

		});

		imageViewDigitalCardAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),
						DigitalCardFormActivity.class);
				intent.putExtra("id", 0);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				finish();
			}

		});

	}

	private void goToHome() {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		finish();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (database != null) {
			database.sqlite.close();
		}
	}

}
