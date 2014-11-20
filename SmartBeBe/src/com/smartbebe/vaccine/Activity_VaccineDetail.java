package com.smartbebe.vaccine;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;

public class Activity_VaccineDetail extends Activity implements OnClickListener {
	private TextView vaccine_name_textView, vaccine_disease_textView, vaccine_time_textView, vaccine_content_textView, vaccine_channel_textView;
	private TextView vaccine_sympton_textView, vaccine_cure_textView, vaccine_prevention_textView;
	private Button close_btn;
	
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Cursor mCursor = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_vaccinedetail);
	    
	    Intent intent = getIntent();
	    String vaccine_name = intent.getExtras().getString("vaccine_name");

	    vaccine_disease_textView = (TextView)findViewById(R.id.vaccineDetail_disease_textView);
	    vaccine_name_textView = (TextView)findViewById(R.id.vaccineDetail_name_content_textView);
	    vaccine_time_textView = (TextView)findViewById(R.id.vaccineDetail_time_content_textView);
	    vaccine_content_textView = (TextView)findViewById(R.id.vaccineDetail_content_content_textView);
	    vaccine_channel_textView = (TextView)findViewById(R.id.vaccineDetail_channel_content_textView);
	    vaccine_sympton_textView = (TextView)findViewById(R.id.vaccineDetail_sympton_content_textView);
	    vaccine_cure_textView = (TextView)findViewById(R.id.vaccineDetail_cure_content_textView);
	    vaccine_prevention_textView = (TextView)findViewById(R.id.vaccineDetail_prevention_content_textView);
	    
	    close_btn = (Button)findViewById(R.id.vaccineDetail_close_btn);
	    
	    close_btn.setOnClickListener(this);
	    
	    setVaccineDetail(vaccine_name);
	}
	
	public void setVaccineDetail(String name) {
	    mDbOpenHelper = new SmartBebeDBOpenHelper(this);
	    mDbOpenHelper.open();
	   
	    mCursor = mDbOpenHelper.getMatchAttr(SmartBebeDataBase.CreateDB._TABLE_VACCINE_CONTENT, SmartBebeDataBase.CreateDB.VACCINE_DISEASE, name);

	    while( mCursor.moveToNext() ) {
			vaccine_disease_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.VACCINE_DISEASE))));
			vaccine_name_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.VACCINE_NAME))));
			vaccine_time_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.VACCINE_TIME))));
			vaccine_content_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.VACCINE_CONTENT))));
			vaccine_channel_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.VACCINE_CHANNEL))));
			vaccine_sympton_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.VACCINE_SYMPTOM))));
			vaccine_cure_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.VACCINE_CURE))));
			vaccine_prevention_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.VACCINE_PREVENTION))));
		}

		mCursor.close();
		mDbOpenHelper.close();
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.vaccineDetail_close_btn :
			finish();
			break;
		}
	}
}
