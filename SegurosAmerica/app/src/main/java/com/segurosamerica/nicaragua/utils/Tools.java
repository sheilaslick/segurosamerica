package com.segurosamerica.nicaragua.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;

public class Tools {
	final ActivityManager activityManager;
	final ConfigurationInfo configurationInfo;
	final boolean supportsEs2;

	public Tools(Context context) {
		super();
		activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		configurationInfo = activityManager.getDeviceConfigurationInfo();
		supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
	}

	public boolean supportOpenGl2() {
		return supportsEs2;
	}

}
