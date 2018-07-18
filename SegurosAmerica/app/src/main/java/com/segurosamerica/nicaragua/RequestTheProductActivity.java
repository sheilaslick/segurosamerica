package com.segurosamerica.nicaragua;

import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RequestTheProductActivity extends Activity {

	private EditText editTextNames;
	private EditText editTextSurnames;
	private EditText editTextPhoneNumber;
	private EditText editTextEmail;

	private String message = null;
	
	private boolean isRegistro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_request_the_product);

		buttonActions();
	}

	private void buttonActions() {
		ImageButton imageButtonRequestBack = (ImageButton) findViewById(R.id.imageButtonRequestBack);

		editTextNames = (EditText) findViewById(R.id.editTextNames);
		editTextSurnames = (EditText) findViewById(R.id.editTextSurnames);
		editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
		editTextEmail = (EditText) findViewById(R.id.editTextEmail);
		
		isRegistro = this.getIntent().getBooleanExtra("esRegistrate", false);

		imageButtonRequestBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}

		});

		Button buttonSendRequetTheProduct = (Button) findViewById(R.id.buttonSendRequetTheProduct);
		buttonSendRequetTheProduct.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String names = editTextNames.getText().toString();
				String surnames = editTextSurnames.getText().toString();
				String number = editTextPhoneNumber.getText().toString();
				String email = editTextEmail.getText().toString();

				message = String.format(getString(R.string.email_message),
						names, surnames, number, email);

				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("message/rfc822");
				intent.putExtra(Intent.EXTRA_EMAIL,
						new String[] { getString(R.string.email_address) });
				
				if(isRegistro) {
					intent.putExtra(Intent.EXTRA_SUBJECT,
							getString(R.string.email_subject_registros));
				} else {
					intent.putExtra(Intent.EXTRA_SUBJECT,
							getString(R.string.email_subject));
				}
				
				intent.putExtra(Intent.EXTRA_TEXT, message);
				try {
					startActivity(Intent.createChooser(intent,
							getString(R.string.request_the_product)));
					finish();
				} catch (ActivityNotFoundException e) {
					Toast.makeText(getApplicationContext(),
							getString(R.string.no_email_app),
							Toast.LENGTH_SHORT).show();
				}

			}

		});
	}

}
