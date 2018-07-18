package com.segurosamerica.nicaragua;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class TutorialActivity extends Activity implements OnTouchListener, OnClickListener {
	
	private ViewFlipper viewFlipper;
	
	private TextView txtSaltarTutorial;
	
	private float initX;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_tutorial);
		
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipTutorial);
		viewFlipper.setOnTouchListener(this);
		
		txtSaltarTutorial = (TextView) findViewById(R.id.txtSaltarTutorial);
		txtSaltarTutorial.setOnClickListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN: //Cuando el usuario toca la pantalla por primera vez
	        	initX = event.getX();
	            return true;
	        case MotionEvent.ACTION_UP: //Cuando el usuario deja de presionar
	            float distance = initX - event.getX();
	            
	            if(distance < 0) {
	            	if (viewFlipper.getDisplayedChild() != 0) {
		            	viewFlipper.setInAnimation(inFromLeftAnimation());
		            	viewFlipper.setOutAnimation(outToRightAnimation());
		            	viewFlipper.showPrevious();
	            	}
	                // Log.d(this.getClass().getSimpleName(), "distance < 0. El paso es: " + viewFlipper.getDisplayedChild());
	            }
	
	            if(distance > 0) {
	            	if (viewFlipper.getDisplayedChild() == (viewFlipper.getChildCount() -1)) {
	            		SharedPreferences prefs = getSharedPreferences("MostrarTutorial", Context.MODE_PRIVATE);

	    				SharedPreferences.Editor editor = prefs.edit();
	    				editor.putBoolean("mostrarTutorial", false);
	    				editor.commit();
	    				// Log.d(this.getClass().getSimpleName(), "distance > 0. El paso es: " + viewFlipper.getDisplayedChild());
	    				Intent intentMain = new Intent(this.getApplicationContext(), MainActivity.class);
	    				startActivity(intentMain);
	    				finish();
	            	} else {
		            	viewFlipper.setInAnimation(inFromRightAnimation());
		            	viewFlipper.setOutAnimation(outToLeftAnimation());
		                viewFlipper.showNext();
	            	}
	                // Log.d(this.getClass().getSimpleName(), "distance > 0. El paso es: " + viewFlipper.getDisplayedChild());
	            }
	            
	            if(distance == 0) {
	            	if (viewFlipper.getDisplayedChild() == (viewFlipper.getChildCount() - 1)) {
	            		SharedPreferences prefs = getSharedPreferences("MostrarTutorial", Context.MODE_PRIVATE);

	    				SharedPreferences.Editor editor = prefs.edit();
	    				editor.putBoolean("mostrarTutorial", false);
	    				editor.commit();
	    				// Log.d(this.getClass().getSimpleName(), "distance > 0. El paso es: " + viewFlipper.getDisplayedChild());
	    				Intent intentMain = new Intent(this.getApplicationContext(), MainActivity.class);
	    				startActivity(intentMain);
	    				finish();
	            	} else {
		            	viewFlipper.setInAnimation(inFromRightAnimation());
		            	viewFlipper.setOutAnimation(outToLeftAnimation());
		                viewFlipper.showNext();
	            	}
	                // Log.d(this.getClass().getSimpleName(), "distance == 0. El paso es: " + viewFlipper.getDisplayedChild());
	            }
                // Log.d(this.getClass().getSimpleName(), "El paso es: " + viewFlipper.getDisplayedChild());
	
	        default:
	            break;
        }

        return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.txtSaltarTutorial:
				SharedPreferences prefs = getSharedPreferences("MostrarTutorial", Context.MODE_PRIVATE);

				SharedPreferences.Editor editor = prefs.edit();
				editor.putBoolean("mostrarTutorial", false);
				editor.commit();
				
				Intent intentMain = new Intent(this.getApplicationContext(), MainActivity.class);
				startActivity(intentMain);
				finish();
				break;
			default:
				break;
		}
	}
	
	private Animation inFromRightAnimation() {
		 
        Animation inFromRight = new TranslateAnimation(
        Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
        Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f );
 
        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new AccelerateInterpolator());
 
        return inFromRight;
 
    }
 
    private Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(500);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }
 
    private Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(500);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }
 
    private Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(500);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }

}