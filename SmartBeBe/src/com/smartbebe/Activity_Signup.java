package com.smartbebe;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony.Mms.Part;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.*;

public class Activity_Signup extends Activity implements OnClickListener {
	private EditText name, height, weight;
	private EditText year, month, day;
	private Button signup_btn, add_btn;
	private RadioButton male_radio, female_radio;
	
	private int gender;
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Cursor mCursor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_signup);
	    
	    mDbOpenHelper = new SmartBebeDBOpenHelper(this);
	    
	    name = (EditText)findViewById(R.id.baby_name_EditText);
	    height = (EditText)findViewById(R.id.baby_height_EditText);
	    weight = (EditText)findViewById(R.id.baby_weight_EditText);

	    year = (EditText)findViewById(R.id.baby_year_EditText);
	    month = (EditText)findViewById(R.id.baby_month_EditText);
	    day = (EditText)findViewById(R.id.baby_day_EditText);

	    signup_btn = (Button)findViewById(R.id.baby_signup_btn);
	    add_btn = (Button)findViewById(R.id.baby_add_btn);

	    male_radio = (RadioButton)findViewById(R.id.baby_male_Radio);
	    female_radio = (RadioButton)findViewById(R.id.baby_female_Radio);
	    
	    signup_btn.setOnClickListener(this);
	    add_btn.setOnClickListener(this);
	    male_radio.setOnClickListener(this);
	    female_radio.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.baby_signup_btn :
			String birthday = String.format("%04d%02d%02d",
					Integer.parseInt(String.valueOf(year.getText())),
					Integer.parseInt(String.valueOf(month.getText())),
					Integer.parseInt(String.valueOf(day.getText())));
			
			mDbOpenHelper.open();
			mDbOpenHelper.insertBabyInfo(SmartBebeDataBase.CreateDB._TABLE_BABY_INFO, String.valueOf(name.getText()), gender, birthday, 
					Integer.parseInt(String.valueOf(height.getText())), Integer.parseInt(String.valueOf(weight.getText())));
			
			startActivity(new Intent(Activity_Signup.this, Activity_Main.class));
			finish();
			
			break;
		case R.id.baby_add_btn :
			
			break;
		case R.id.baby_male_Radio :
			gender = 0;
			break;
		case R.id.baby_female_Radio :
			gender = 1;
			break;
		}
	}

	protected void onDestroy() {
		mDbOpenHelper.close();
		super.onDestroy();
	}
}
