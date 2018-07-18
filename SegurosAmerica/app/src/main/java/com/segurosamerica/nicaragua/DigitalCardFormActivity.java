package com.segurosamerica.nicaragua;

import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import com.segurosamerica.nicaragua.utils.Database;
import com.segurosamerica.nicaragua.utils.OdbPolicy;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.content.DialogInterface;

public class DigitalCardFormActivity extends Activity {

	private EditText editTextValidity;
	private EditText editTextExpires;
	private EditText editTextInsured;
	private EditText editTextPolicyNumber;
	private TextView textViewDigitalCardTitle;
	private LinearLayout linearLayoutButtonDigitalCardForm;
	private Button buttonDigitalCardDelete;

	private AlertDialog.Builder alertDialogBuilder;
	private AlertDialog alertDialog;

	private int year;
	private int month;
	private int day;

	private int myId = 0;

	private Database database = null;

	private String input;

	static final int DATE_DIALOG_ID = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_digital_card_form);

		database = new Database(this);

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		myId = this.getIntent().getExtras().getInt("id", 0);

		if (myId != 0) {
			textViewDigitalCardTitle = (TextView) findViewById(R.id.textViewDigitalCardTitle);
			textViewDigitalCardTitle
					.setText(R.string.title_activity_digital_card_form_edit);
		}

		alertDialogBuilder = new AlertDialog.Builder(this);

		// set title
		alertDialogBuilder
				.setTitle(getString(R.string.delete_digital_card_title));

		// set dialog message
		alertDialogBuilder
				.setMessage(getString(R.string.delete_digital_card_message))
				.setCancelable(false)
				.setPositiveButton(getString(R.string.yes),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// if this button is clicked, close
								// current activity
								database.deletePolicy("" + myId);
								goToDigitalCard();

								finish();
							}
						})
				.setNegativeButton(getString(R.string.no),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
							}
						});

		// create alert dialog
		alertDialog = alertDialogBuilder.create();

		editTextActions();
	}

	private void editTextActions() {
		buttonDigitalCardDelete = (Button) findViewById(R.id.buttonDigitalCardDelete);
		linearLayoutButtonDigitalCardForm = (LinearLayout) findViewById(R.id.linearLayoutButtonDigitalCardForm);
		ImageButton imageViewDigitalCardFormBack = (ImageButton) findViewById(R.id.imageViewDigitalCardFormBack);
		ImageButton imageViewDigitalCarFormSave = (ImageButton) findViewById(R.id.imageViewDigitalCarFormSave);
		editTextInsured = (EditText) findViewById(R.id.editTextInsured);
		editTextPolicyNumber = (EditText) findViewById(R.id.editTextPolicyNumber);
		editTextValidity = (EditText) findViewById(R.id.editTextValidity);
		editTextExpires = (EditText) findViewById(R.id.editTextExpires);

		if (myId != 0) {
			linearLayoutButtonDigitalCardForm.setVisibility(View.VISIBLE);
			final OdbPolicy policy = new OdbPolicy(database.getPolicy("" + myId));

			editTextInsured.setText(policy.getInsured());
			editTextPolicyNumber.setText(policy.getInsuranceNumber());
			editTextValidity.setText(policy.getValidity());
			editTextExpires.setText(policy.getExpires());
		}

		buttonDigitalCardDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// show it
				alertDialog.show();
			}

		});

		editTextValidity.setOnFocusChangeListener(new OnFocusChangeListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				if (hasFocus) {
					input = "a";
					showDialog(DATE_DIALOG_ID);
				}
			}

		});

		editTextValidity.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				input = "a";
				showDialog(DATE_DIALOG_ID);
			}
		});

		editTextExpires.setOnFocusChangeListener(new OnFocusChangeListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				if (hasFocus) {
					input = "b";
					showDialog(DATE_DIALOG_ID);
				}
			}

		});

		editTextExpires.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				input = "b";
				showDialog(DATE_DIALOG_ID);
			}
		});

		imageViewDigitalCarFormSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				JSONObject row = new JSONObject();
				try {
					if (myId != 0) {
						row.put("id", "" + myId);
					}
					row.put("insured", editTextInsured.getText().toString());
					row.put("policy_number", editTextPolicyNumber.getText()
							.toString());
					row.put("validity", editTextValidity.getText().toString());
					row.put("expires", editTextExpires.getText().toString());

					if (myId != 0) {
						database.updatePolicy(row);
					} else {
						database.insertPolicy(row);
					}

					goToDigitalCard();

					finish();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		imageViewDigitalCardFormBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goToDigitalCard();

				finish();

			}

		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		}
		return null;
	}

	private void goToDigitalCard() {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), DigitalCardActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

			String string_month = "";
			String string_day = "";
			year = selectedYear;
			month = selectedMonth + 1;
			day = selectedDay;

			if (month < 10) {
				string_month = "0" + Integer.toString(month);
			}
			else{
				string_month = Integer.toString(month);
			}

			if (day < 10) {
				string_day = "0" + Integer.toString(day);
			}
			else{
				string_day = Integer.toString(day);
			}

			if (input.equals("a")) {
				editTextValidity.setText(new StringBuilder().append(string_day)
						.append("/").append(string_month).append("/")
						.append(year).append(" "));
			}

			if (input.equals("b")) {
				editTextExpires.setText(new StringBuilder().append(string_day)
						.append("/").append(string_month).append("/")
						.append(year).append(" "));
			}

		}
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	goToDigitalCard();

			finish();
	    }
	    return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (database != null) {
			database.sqlite.close();
		}
	}

}
