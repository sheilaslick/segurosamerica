package com.segurosamerica.nicaragua;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 0;
    private final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION_OR_ACCESS_COARSE_LOCATION = 1;

	private String phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		buttonActions();
	}

	private void buttonActions() {
		ImageView imageButtonHomeEmergencyAndAssistance = (ImageView) findViewById(R.id.imageButtonHomeEmergencyAndAssistance);
		ImageView imageButtonHomeWhereIAm = (ImageView) findViewById(R.id.imageButtonHomeWhereIAm);
		ImageView imageButtonHomeEmergencyNumbers = (ImageView) findViewById(R.id.imageButtonHomeEmergencyNumbers);
		ImageView imageButtonHomeDigitalCard = (ImageView) findViewById(R.id.imageButtonHomeDigitalCard);

		ImageView imageButtonHomeInsuranceExpress = (ImageView) findViewById(R.id.imageButtonHomeInsuranceExpress);
		ImageView imageButtonHomeInsuranceAmerica = (ImageView) findViewById(R.id.imageButtonHomeInsuranceAmerica);

		ImageView imageButtonRegistrate = (ImageView) findViewById(R.id.imageButtonRegistrate);

		imageButtonHomeEmergencyAndAssistance.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
					phone = getString(R.string.emergency_hone);
					ActivityCompat.requestPermissions(MainActivity.this,
							new String[]{android.Manifest.permission.CALL_PHONE},
							MY_PERMISSIONS_REQUEST_CALL_PHONE);
				} else {
					Intent intentCall = new Intent(Intent.ACTION_CALL);
					phone = getString(R.string.emergency_hone);
					doPermissionGrantedCallPhoneStuffs(intentCall, phone);
				}
			}

		});

		imageButtonHomeWhereIAm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				goTo(1);
			}

		});

		imageButtonHomeEmergencyNumbers.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				goTo(2);
			}

		});

		imageButtonHomeDigitalCard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				goTo(3);
			}

		});

		imageButtonHomeInsuranceExpress.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
					phone = getString(R.string.insurance_hone);
					ActivityCompat.requestPermissions(MainActivity.this,
							new String[]{android.Manifest.permission.CALL_PHONE},
							MY_PERMISSIONS_REQUEST_CALL_PHONE);
				} else {
					Intent intentCall = new Intent(Intent.ACTION_CALL);
					phone = getString(R.string.insurance_hone);
					doPermissionGrantedCallPhoneStuffs(intentCall, phone);
				}
			}

		});

		imageButtonHomeInsuranceAmerica.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				goTo(5);
			}

		});

		imageButtonRegistrate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				goTo(6);
			}
		});

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION_OR_ACCESS_COARSE_LOCATION);
        }
	}

	private void doPermissionGrantedCallPhoneStuffs(Intent intentCall, String phone) {
		intentCall.setData(Uri.parse("tel:" + phone));
		startActivity(intentCall);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
			// Received permission result for READ_PHONE_STATE permission.est.");
			// Check if the only required permission has been granted
			if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				Log.i(this.getClass().getSimpleName(), "Llamar doPermissionGrantedStuffs()");
				// READ_PHONE_STATE permission has been granted, proceed with displaying IMEI Number
				Intent intentCall = new Intent(Intent.ACTION_CALL);
				doPermissionGrantedCallPhoneStuffs(intentCall, phone);
			} else {
				Log.i(this.getClass().getSimpleName(), "Permiso Denegado a Call Phone.");
			}
		}

        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION_OR_ACCESS_COARSE_LOCATION) {
            Log.i(this.getClass().getSimpleName(), "Acceso concedido a Location.");
        } else {
            Log.i(this.getClass().getSimpleName(), "Permiso Denegado a Location.");
        }
	}

	private void goTo(int pos) {
		Intent intent = new Intent();
		switch (pos) {
		case 1:
			intent.setClass(getApplicationContext(), WhereIAmActivity.class);
			break;
		case 2:
			intent.setClass(getApplicationContext(), EmergencyNumbersActivity.class);
			break;
		case 3:
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.mainactivity_imageview_browser_url)));
			break;
		case 5:
			intent.setClass(getApplicationContext(), InsuranceAmericaActivity.class);
			break;
		case 6:
			intent.setClass(getApplicationContext(), RequestTheProductActivity.class);
			intent.putExtra("esRegistrate", true);
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
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
