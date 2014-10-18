package com.smartbebe.diary;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;
import com.smartbebe.def.SmartBebePreference;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.os.Bundle;
import android.widget.*;
import android.os.Handler;
import android.app.ProgressDialog;
import android.widget.Toast;

public class Activity_DiaryWrite extends Activity implements OnClickListener, OnTouchListener {
	final int REQUEST_DAY_INFO = 1000;
	
	private String write_day;
	private ImageButton calendar_btn;
	private Button cancel_btn, complete_btn;
	private EditText day_editText, content_editText, title_editText;
	private ImageButton location_btn, vaccine_btn, height_btn, weight_btn;
	
	private SmartBebeDBOpenHelper mDbOpenHelper;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.diary_write);
	    
	    mDbOpenHelper = new SmartBebeDBOpenHelper(this);
	    mDbOpenHelper.open();

	    cancel_btn = (Button)findViewById(R.id.diary_write_cancel_btn);
	    complete_btn = (Button)findViewById(R.id.diary_write_complete_btn);
	    calendar_btn = (ImageButton)findViewById(R.id.diary_write_viewCalendar_btn);
	    
	    title_editText = (EditText)findViewById(R.id.diary_write_title_editText);
	    day_editText = (EditText)findViewById(R.id.diary_write_day_editText);
	    content_editText = (EditText)findViewById(R.id.diary_write_content_editText);

	    location_btn = (ImageButton)findViewById(R.id.diary_write_location_btn);
	    vaccine_btn = (ImageButton)findViewById(R.id.diary_write_vaccine_btn);
	    height_btn = (ImageButton)findViewById(R.id.diary_write_height_btn);
	    weight_btn = (ImageButton)findViewById(R.id.diary_write_weight_btn);


	    location_btn.setOnTouchListener(this);
	    vaccine_btn.setOnTouchListener(this);
	    height_btn.setOnTouchListener(this);
	    weight_btn.setOnTouchListener(this);
	    
	    cancel_btn.setOnClickListener(this);
	    complete_btn.setOnClickListener(this);
	    calendar_btn.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.diary_write_cancel_btn :
			finish();
			break;
		case R.id.diary_write_complete_btn :
			if( title_editText.getText().toString().equals("") )
				Toast.makeText(getApplicationContext(), getResources().getString(R.string.diary_write_title_excp), Toast.LENGTH_LONG).show();
			else if( day_editText.getText().toString().equals("") )
				Toast.makeText(getApplicationContext(), getResources().getString(R.string.diary_write_day_excp), Toast.LENGTH_LONG).show();
			else if( content_editText.getText().toString().equals("") )
				Toast.makeText(getApplicationContext(), getResources().getString(R.string.diary_write_content_excp), Toast.LENGTH_LONG).show();
			else {
				mDbOpenHelper.insertDiary(SmartBebeDataBase.CreateDB._TABLE_DIARY_CONTENT,
						SmartBebePreference.CURRENT_BABY_ID, title_editText.getText().toString(), write_day, content_editText.getText().toString(),
						"", "", 102, 22);
				finish();
			}
			break;
		case R.id.diary_write_viewCalendar_btn :			
    		Intent intent = new Intent(Activity_DiaryWrite.this, ViewCalendar.class);
    		startActivityForResult(intent,REQUEST_DAY_INFO);
    		break;
		}
	}
	
	public boolean onTouch(View view, MotionEvent event) {
		ImageButton v = (ImageButton)view;
		
		switch(v.getId()) {
		case R.id.diary_write_location_btn :
			if( event.getAction() == MotionEvent.ACTION_DOWN )
				v.setImageResource(R.drawable.diary_location_pressed_128x128);
			else
				v.setImageResource(R.drawable.diary_location_normal_128x128);
			break;		
		case R.id.diary_write_vaccine_btn :
			if( event.getAction() == MotionEvent.ACTION_DOWN )
				v.setImageResource(R.drawable.diary_vaccine_pressed_128x128);
			else
				v.setImageResource(R.drawable.diary_vaccine_normal_128x128);
			break;		
		case R.id.diary_write_height_btn :
			if( event.getAction() == MotionEvent.ACTION_DOWN )
				v.setImageResource(R.drawable.diary_height_pressed_128x128);
			else
				v.setImageResource(R.drawable.diary_height_normal_128x128);
			break;		
		case R.id.diary_write_weight_btn :
			if( event.getAction() == MotionEvent.ACTION_DOWN )
				v.setImageResource(R.drawable.diary_weight_pressed_128x128);
			else
				v.setImageResource(R.drawable.diary_weight_normal_128x128);
			break;		
		}		
		return false;
	}

	protected void onDestroy() {
		mDbOpenHelper.close();
		super.onDestroy();
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if( resultCode == 1001) {
			String week = "";
			switch(data.getIntExtra("dayofweek", 0)) {
				case 1 : week = " (일)"; break;
				case 2 : week = " (월)"; break;
				case 3 : week = " (화)"; break;
				case 4 : week = " (수)"; break;
				case 5 : week = " (목)"; break;
				case 6 : week = " (금)"; break;
				case 7 : week = " (토)"; break;
				default : week = ""; break;
			}
			String str = data.getStringExtra("day_info").toString() + "." + data.getStringExtra("day");
			write_day = str.replaceAll(".", "");
			day_editText.setText(str + week);
		}
	}
}
