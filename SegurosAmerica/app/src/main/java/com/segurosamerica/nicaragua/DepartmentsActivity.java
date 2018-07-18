package com.segurosamerica.nicaragua;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class DepartmentsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_departments);
		
		buttonsActions();
	}

	private void buttonsActions() {
		ImageButton imageButtonDepartmentsBack = (ImageButton) findViewById(R.id.imageButtonDepartmentsBack);
		imageButtonDepartmentsBack.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				finish();				
			}
			
		});
	}

}
