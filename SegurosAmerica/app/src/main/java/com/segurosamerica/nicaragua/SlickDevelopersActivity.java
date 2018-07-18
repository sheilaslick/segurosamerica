package com.segurosamerica.nicaragua;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class SlickDevelopersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slick_developers);
		
		/*SharedPreferences prefs =
			     getSharedPreferences("MostrarTutorial", Context.MODE_PRIVATE);*/

		boolean verTutorial = false; // prefs.getBoolean("mostrarTutorial", true);
		
		if(verTutorial) {
			new Handler().postDelayed(new Runnable() {
	            @Override
	            public void run() {
	    			Intent intent = new Intent();
	    			intent.setClass(getApplicationContext(), TutorialActivity.class);
	    			
	    			intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
	    					| Intent.FLAG_ACTIVITY_CLEAR_TOP
	    					| Intent.FLAG_ACTIVITY_NEW_TASK);
	    			startActivity(intent);
	    			finish();
	            }
	        }, 1000);
		} else {
			new Handler().postDelayed(new Runnable() {
	            @Override
	            public void run() {
	    			Intent intent = new Intent();
	    			intent.setClass(getApplicationContext(), MainActivity.class);
	    			
	    			intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
	    					| Intent.FLAG_ACTIVITY_CLEAR_TOP
	    					| Intent.FLAG_ACTIVITY_NEW_TASK);
	    			startActivity(intent);
	    			finish();
	            }
	        }, 1000);
		}
	}

}
