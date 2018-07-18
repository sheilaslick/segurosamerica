package com.segurosamerica.nicaragua;

import android.os.Bundle;
import android.app.Activity;

import android.content.Intent;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

public class PageActivity extends Activity {
	private String raw_file;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page);

		buttonsActions();

		raw_file = this.getIntent().getExtras().getString("raw_file");

		loadContent(raw_file);
	}

	private void loadContent(String text_html) {
		WebView webView = (WebView) findViewById(R.id.webViewPage);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/" + text_html + ".html");
	}

	private void buttonsActions() {
		ImageButton imageButtonPageBack = (ImageButton) findViewById(R.id.imageButtonPageBack);
		Button buttonPageRequest = (Button) findViewById(R.id.buttonPageRequest);
		buttonPageRequest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),
						RequestTheProductActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}

		});

		imageButtonPageBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.page, menu);
		return true;
	}

}
