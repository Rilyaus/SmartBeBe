package com.smartbebe.def;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SmartBebePreference {
	static Context mContext;
	private final String PREFERENCE_NAME = "smartbebe.pref"; 
	
	public static int CURRENT_BABY_ID = 0;

	public SmartBebePreference(Context context) {
		mContext = context;
	}

	public void putPref(String key, String value) {
		SharedPreferences pref = mContext.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		
		editor.putString(key, value);
		editor.commit();
	}
	
	public void putPref(String key, int value) {
		SharedPreferences pref = mContext.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		
		editor.putInt(key, value);
		editor.commit();
	}

	public void putPref(String key, Boolean value) {
		SharedPreferences pref = mContext.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	public String getPref(String key, String defValue) {
		SharedPreferences pref = mContext.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
		
		try {
			return pref.getString(key, defValue);
		} catch(Exception e) {
			return defValue;
		}
	}
	
	public int getPref(String key, int defValue) {
		SharedPreferences pref = mContext.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
		
		try {
			return pref.getInt(key, defValue);
		} catch(Exception e) {
			return defValue;
		}
	}
	
	public Boolean getPref(String key, Boolean defValue) {
		SharedPreferences pref = mContext.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
		
		try {
			return pref.getBoolean(key, defValue);
		} catch(Exception e) {
			return defValue;
		}
	}
}
