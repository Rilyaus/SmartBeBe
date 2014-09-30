package com.kw.smartbebe;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class StartActivity extends Activity {
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Cursor mCursor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_start);
	    
	    mDbOpenHelper = new SmartBebeDBOpenHelper(this);
	    mDbOpenHelper.open();
	    
		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				startActivity(new Intent(StartActivity.this, MainActivity.class));
				finish();
			}
		};
		handler.sendEmptyMessageDelayed(0, 1300);
	}
	
	protected void onDestroy() {
		mDbOpenHelper.close();
		super.onDestroy();
	}
}
