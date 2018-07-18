package com.segurosamerica.nicaragua;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.segurosamerica.nicaragua.utils.Sms;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;

import android.support.v4.app.FragmentActivity;

public class WhereIAmActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private static final String TAG = WhereIAmActivity.class.getSimpleName();

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 3;
    private final int MY_PERMISSIONS_REQUEST_SEND_SMS = 4;

    private static final int DEFAULT_ZOOM = 16;

    private String phone;

    private GoogleMap mMap;
    private TextView latituteField;
    private TextView longitudeField;

    private Boolean load = false;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private boolean mLocationPermissionGranted;

    private Location mLastKnownLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_iam);

        buttonsActions();

        latituteField = findViewById(R.id.textViewLatitud);
        longitudeField = findViewById(R.id.textViewLongitud);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = false;
        if (service != null) {
            enabled = service
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
        }

        // Check if enabled and if not send user to the GSP settings
        // Better solution would be to display a dialog and suggesting to
        // go to the settings
        if (!enabled) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // set title
            alertDialogBuilder.setTitle(R.string.gps_dialog_title);

            // set dialog message
            alertDialogBuilder
                    .setMessage(getString(R.string.gps_dialog_message))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.yes),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    Intent intent = new Intent(
                                            Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivity(intent);
                                }
                            })
                    .setNegativeButton(getString(R.string.no),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    finish();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        }
    }

    private void doPermissionGrantedCallPhoneStuffs(Intent intentCall, String phone) {
        intentCall.setData(Uri.parse("tel:" + phone));
        startActivity(intentCall);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;

        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
            break;
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(this.getClass().getSimpleName(), "Llamar doPermissionGrantedStuffs()");
                    // READ_PHONE_STATE permission has been granted, proceed with displaying IMEI Number
                    // Get the location manager
                    Intent intentCall = new Intent(Intent.ACTION_CALL);
                    doPermissionGrantedCallPhoneStuffs(intentCall, phone);
                } else {
                    Log.i(this.getClass().getSimpleName(), "Permiso Denegado.");
                }
            }
            break;
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                Log.i(this.getClass().getSimpleName(), "Acceso concedido a Send SMS.");
                String message = String.format(
                        getString(R.string.message_coordinates),
                        latituteField.getText().toString(), longitudeField
                                .getText().toString());

                Sms sms = new Sms(getApplicationContext());
                sms.setNumber(getString(R.string.mobile));
                sms.setMessage(message);
                sms.send();
                Toast.makeText(getApplicationContext(),
                        getString(R.string.sms_message), Toast.LENGTH_LONG)
                        .show();
            }
            break;
            default:
                Log.i(TAG, "Permiso Denegado.");
                break;
        }

        updateLocationUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }

    private void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());

        latituteField.setText(String.valueOf(location.getLatitude()));
        longitudeField.setText(String.valueOf(location.getLongitude()));

        LatLng coordinate = new LatLng(location.getLatitude(),
                location.getLongitude());
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(
                coordinate, 17);
        if (mMap != null && !load) {
            mMap.animateCamera(yourLocation);
            load = true;
        }
    }

    private boolean isEmptyCoordinates() {
        return latituteField.getText().equals("0.00")
                || longitudeField.getText().equals("0.00");
    }

    private void buttonsActions() {

        ImageView imageViewEmail = (ImageView) findViewById(R.id.imageViewEmail);
        imageViewEmail.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (isEmptyCoordinates()) {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.empty_coordinates),
                            Toast.LENGTH_LONG).show();
                } else {
                    String message = String.format(
                            getString(R.string.message_coordinates),
                            latituteField.getText().toString(), longitudeField
                                    .getText().toString());

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_EMAIL,
                            new String[]{getString(R.string.email_address_coordinates)});
                    intent.putExtra(Intent.EXTRA_SUBJECT,
                            getString(R.string.email_subject_coordinates));
                    intent.putExtra(Intent.EXTRA_TEXT, message);
                    try {
                        startActivity(Intent.createChooser(intent,
                                getString(R.string.email_subject_coordinates)));
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.no_email_app),
                                Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });

        ImageView imageViewSms = (ImageView) findViewById(R.id.imageViewSms);
        imageViewSms.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmptyCoordinates()) {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.empty_coordinates),
                            Toast.LENGTH_LONG).show();
                } else {


                    if (ActivityCompat.checkSelfPermission(WhereIAmActivity.this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(WhereIAmActivity.this, new String[]{android.Manifest.permission.SEND_SMS},
                                MY_PERMISSIONS_REQUEST_SEND_SMS);
                    } else {
                        String message = String.format(
                                getString(R.string.message_coordinates),
                                latituteField.getText().toString(), longitudeField
                                        .getText().toString());

                        Sms sms = new Sms(getApplicationContext());
                        sms.setNumber(getString(R.string.mobile));
                        sms.setMessage(message);
                        sms.send();
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.sms_message), Toast.LENGTH_LONG)
                                .show();
                    }
                }
            }

        });

        ImageView imageViewCall = (ImageView) findViewById(R.id.imageViewCall);
        imageViewCall.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(WhereIAmActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    phone = getString(R.string.phone);
                    ActivityCompat.requestPermissions(WhereIAmActivity.this,
                            new String[]{android.Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                } else {
                    Intent intentCall = new Intent(Intent.ACTION_CALL);
                    phone = getString(R.string.phone);
                    doPermissionGrantedCallPhoneStuffs(intentCall, phone);
                }
            }

		});

		ImageView imageViewWhereIAmBack = (ImageView) findViewById(R.id.imageViewWhereIAmBack);
		imageViewWhereIAmBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goToHome();
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getLocationPermission();

        updateLocationUI();

        getDeviceLocation();
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }

        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            handleNewLocation(mLastKnownLocation);
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

}