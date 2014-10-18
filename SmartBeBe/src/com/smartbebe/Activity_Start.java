package com.smartbebe;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;
import com.smartbebe.def.SmartBebePreference;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Activity_Start extends Activity {
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Cursor mCursor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_start);
	    
	    mDbOpenHelper = new SmartBebeDBOpenHelper(this);
	    mDbOpenHelper.open();
	    
	    mCursor = mDbOpenHelper.getAllColumns(SmartBebeDataBase.CreateDB._TABLE_BABY_INFO);
	    
	//  mDbOpenHelper.insertBabyInfo(SmartBebeDataBase.CreateDB._TABLE_BABY_INFO, "Chu", 0, "20120928", 92, 22);
	    
		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				if( mCursor.getCount() == 0 ) {
					startActivity(new Intent(Activity_Start.this, Activity_Signup.class));
					finish();
				}
				else {
					startActivity(new Intent(Activity_Start.this, Activity_Main.class));
					finish();
				}
			}
		};
		handler.sendEmptyMessageDelayed(0, 1300);
	}
	
	protected void onDestroy() {
		mDbOpenHelper.close();
		super.onDestroy();
	}
}
