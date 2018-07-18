package com.segurosamerica.nicaragua;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class DiagnosticCenterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diagnostic_center);

		buttonsActions();
	}

	private void buttonsActions() {
		ImageButton imageButtonDiagnosticCenterBack = (ImageButton) findViewById(R.id.imageButtonDiagnosticCenterBack);
		imageButtonDiagnosticCenterBack.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				finish();				
			}
			
		});
	}

}
