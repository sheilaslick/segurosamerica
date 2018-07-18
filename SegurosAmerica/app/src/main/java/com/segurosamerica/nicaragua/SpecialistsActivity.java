package com.segurosamerica.nicaragua;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class SpecialistsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specialists);
		
		buttonsActions();
	}

	private void buttonsActions() {
		ImageButton imageButtonSpecialistsBack = (ImageButton) findViewById(R.id.imageButtonSpecialistsBack);
		imageButtonSpecialistsBack.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				finish();				
			}
			
		});
	}

}
