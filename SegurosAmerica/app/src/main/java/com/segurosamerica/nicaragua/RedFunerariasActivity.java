package com.segurosamerica.nicaragua;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class RedFunerariasActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_red_funerarias);
		
		buttonsActions();
	}

	private void buttonsActions() {
		ImageButton imageButtonRedFunerariasCenterBack = (ImageButton) findViewById(R.id.imageButtonRedFunerariasCenterBack);
		imageButtonRedFunerariasCenterBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

}