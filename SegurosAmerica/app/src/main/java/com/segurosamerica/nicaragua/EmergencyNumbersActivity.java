package com.segurosamerica.nicaragua;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class EmergencyNumbersActivity extends Activity {

	private final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

	private String phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emergency_numbers);

		buttonsActions();
	}

	private void buttonsActions() {
		ImageButton imageButtonEmergencyNumbersBack = (ImageButton) findViewById(R.id.imageButtonEmergencyNumbersBack);
		imageButtonEmergencyNumbersBack
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						goToHome();

					}

				});

		Button buttonEmergencyNumbersPolice = (Button) findViewById(R.id.buttonEmergencyNumbersPolice);
		buttonEmergencyNumbersPolice.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (ActivityCompat.checkSelfPermission(EmergencyNumbersActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
					phone = getString(R.string.number_police);
					ActivityCompat.requestPermissions(EmergencyNumbersActivity.this,
							new String[]{android.Manifest.permission.CALL_PHONE},
							MY_PERMISSIONS_REQUEST_CALL_PHONE);
				} else {
					Intent intentCall = new Intent(Intent.ACTION_CALL);
					phone = getString(R.string.number_police);
					doPermissionGrantedCallPhoneStuffs(intentCall, phone);
				}

			}

		});

		Button buttonEmergencyNumbersFireFighter = (Button) findViewById(R.id.buttonEmergencyNumbersFireFighter);
		buttonEmergencyNumbersFireFighter
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (ActivityCompat.checkSelfPermission(EmergencyNumbersActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
							phone = getString(R.string.number_firefighter);
							ActivityCompat.requestPermissions(EmergencyNumbersActivity.this,
									new String[]{android.Manifest.permission.CALL_PHONE},
									MY_PERMISSIONS_REQUEST_CALL_PHONE);
						} else {
                            Intent intentCall = new Intent(Intent.ACTION_CALL);
                            phone = getString(R.string.number_firefighter);
                            doPermissionGrantedCallPhoneStuffs(intentCall, phone);
                        }

					}

				});

		Button buttonCruzRojaFireFighter = (Button) findViewById(R.id.buttonCruzRojaFireFighter);
		buttonCruzRojaFireFighter
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (ActivityCompat.checkSelfPermission(EmergencyNumbersActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            phone = getString(R.string.number_cruz_roja);
                            ActivityCompat.requestPermissions(EmergencyNumbersActivity.this,
                                    new String[]{android.Manifest.permission.CALL_PHONE},
                                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
						} else {
                            Intent intentCall = new Intent(Intent.ACTION_CALL);
                            phone = getString(R.string.number_cruz_roja);
                            doPermissionGrantedCallPhoneStuffs(intentCall, phone);
                        }

					}

				});

		Button buttonLineaDirecta = (Button) findViewById(R.id.buttonLineaDirecta);
		buttonLineaDirecta
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (ActivityCompat.checkSelfPermission(EmergencyNumbersActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            phone = getString(R.string.number_linea_directa);
                            ActivityCompat.requestPermissions(EmergencyNumbersActivity.this,
                                    new String[]{android.Manifest.permission.CALL_PHONE},
                                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
						} else {
                            Intent intentCall = new Intent(Intent.ACTION_CALL);
                            phone = getString(R.string.number_linea_directa);
                            doPermissionGrantedCallPhoneStuffs(intentCall, phone);
                        }

					}

				});

		Button buttonCollect = (Button) findViewById(R.id.buttonCollect);
		buttonCollect
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (ActivityCompat.checkSelfPermission(EmergencyNumbersActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            phone = getString(R.string.number_collect);
                            ActivityCompat.requestPermissions(EmergencyNumbersActivity.this,
                                    new String[]{android.Manifest.permission.CALL_PHONE},
                                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
						} else {
                            Intent intentCall = new Intent(Intent.ACTION_CALL);
                            phone = getString(R.string.number_collect);
                            doPermissionGrantedCallPhoneStuffs(intentCall, phone);
                        }

					}

				});

        TextView textViewEmergencyNumbersPharmacyThree = (TextView) findViewById(R.id.textViewEmergencyNumbersPharmacyThree);
        textViewEmergencyNumbersPharmacyThree.setText(convertFromStringToSpannableForActionDial(getString(R.string.number_pharmacy_three)));
        textViewEmergencyNumbersPharmacyThree.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textViewEmergencyNumbersPharmacyThreeTwo = (TextView) findViewById(R.id.textViewEmergencyNumbersPharmacyThreeTwo);
        textViewEmergencyNumbersPharmacyThreeTwo.setText(convertFromStringToSpannableForActionDial(getString(R.string.number_pharmacy_three_two)));
        textViewEmergencyNumbersPharmacyThreeTwo.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textViewEmergencyNumbersPharmacyThreeThree = (TextView) findViewById(R.id.textViewEmergencyNumbersPharmacyThreeThree);
        textViewEmergencyNumbersPharmacyThreeThree.setText(convertFromStringToSpannableForActionDial(getString(R.string.number_pharmacy_three_three)));
        textViewEmergencyNumbersPharmacyThreeThree.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textViewEmergencyNumbersPharmacyFour = (TextView) findViewById(R.id.textViewEmergencyNumbersPharmacyFour);
        textViewEmergencyNumbersPharmacyFour.setText(convertFromStringToSpannableForActionDial(getString(R.string.number_pharmacy_three_three_one)));
        textViewEmergencyNumbersPharmacyFour.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textViewEmergencyNumbersPharmacySix = (TextView) findViewById(R.id.textViewEmergencyNumbersPharmacySix);
        textViewEmergencyNumbersPharmacySix.setText(convertFromStringToSpannableForActionDial(getString(R.string.number_pharmacy_three_six)));
        textViewEmergencyNumbersPharmacySix.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textViewEmergencyNumbersPharmacySeven = (TextView) findViewById(R.id.textViewEmergencyNumbersPharmacySeven);
        textViewEmergencyNumbersPharmacySeven.setText(convertFromStringToSpannableForActionDial(getString(R.string.number_pharmacy_three_seven)));
        textViewEmergencyNumbersPharmacySeven.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textViewEmergencyNumbersPharmacyFive = (TextView) findViewById(R.id.textViewEmergencyNumbersPharmacyFive);
        textViewEmergencyNumbersPharmacyFive.setText(convertFromStringToSpannableForActionDial(getString(R.string.number_pharmacy_five)));
        textViewEmergencyNumbersPharmacyFive.setMovementMethod(LinkMovementMethod.getInstance());

		TextView textViewEmergencyNumbersPharmacyEight = (TextView) findViewById(R.id.textViewEmergencyNumbersPharmacyEight);
		textViewEmergencyNumbersPharmacyEight.setText(convertFromStringToSpannableForActionDial(getString(R.string.number_pharmacy)));
		textViewEmergencyNumbersPharmacyEight.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textViewEmergencyNumbersPharmacyNine = (TextView) findViewById(R.id.textViewEmergencyNumbersPharmacyNine);
        textViewEmergencyNumbersPharmacyNine.setText(convertFromStringToSpannableForActionDial(getString(R.string.number_pharmacy_three_nine)));
        textViewEmergencyNumbersPharmacyNine.setMovementMethod(LinkMovementMethod.getInstance());

		Button buttonDiagnosticCenter = (Button) findViewById(R.id.buttonDiagnosticCenter);
		buttonDiagnosticCenter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();

				intent.setClass(getApplicationContext(),
						DiagnosticCenterActivity.class);

				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);

			}

		});
		
		Button buttonDepartments = (Button) findViewById(R.id.buttonDepartments);
		buttonDepartments.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();

				intent.setClass(getApplicationContext(),
						DepartmentsActivity.class);

				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);

			}

		});
		
		Button buttonSpecialists = (Button) findViewById(R.id.buttonSpecialists);
		buttonSpecialists.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();

				intent.setClass(getApplicationContext(),
						SpecialistsActivity.class);

				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);

			}

		});
		
		Button buttonRedFunerarias = (Button) findViewById(R.id.buttonRedFunerarias);
		buttonRedFunerarias.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),
						RedFunerariasActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
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
				Log.i(this.getClass().getSimpleName(), "Permiso Denegado.");
			}
		}
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

	public SpannableStringBuilder convertFromStringToSpannableForActionDial(String phoneNumbers) {
	    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
	    final String SLASH = "/";
        final String COMMA = ",";
        final String[] numbers;
        final String formatDial = "tel:%s";

	    if (phoneNumbers.contains(SLASH)) {
            numbers = phoneNumbers.split(SLASH);
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = numbers[i].trim();
            }
        } else {
	        numbers = new String[1];
            numbers[0] = phoneNumbers.trim();
        }

        for (int i = 0; i < numbers.length; i++) {
            final String number = numbers[i];
            final boolean numberContainSymbolComma = numbers[i].contains(COMMA);
            final int numberLength = numberContainSymbolComma ? numbers[i].indexOf(COMMA) : numbers[i].length();

            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    startActivity(
                            new Intent(
                                    Intent.ACTION_DIAL,
                                    Uri.parse(
                                            String.format(
                                                    formatDial,
                                                    number.substring(0, numberLength)
                                            )
                                    )
                            )
                    );
                }
            };

            if (i == 0) {
                spannableStringBuilder.append(number);
                spannableStringBuilder.setSpan(
                        clickableSpan,
                        0,
                        numberLength,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                );
            } else {
                String secondPhoneNumber = String.format(" %s %s", SLASH, number);
                spannableStringBuilder.append(secondPhoneNumber);
                spannableStringBuilder.setSpan(
                        clickableSpan,
                        spannableStringBuilder.length() - numberLength,
                        spannableStringBuilder.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                );
            }
        }

        return spannableStringBuilder;
    }

}
