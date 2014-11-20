package com.smartbebe.diary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;
import com.smartbebe.def.SmartBebePreference;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.text.InputType;
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
	
	private Boolean isModify = false;
	private long modify_id = -1;
	private String write_day = "", location_info = "";
	private float height_info = 0, weight_info = 0;
	private ImageButton calendar_btn;
	private Button cancel_btn, complete_btn;
	private EditText day_editText, content_editText, title_editText;
	private ImageButton location_btn, vaccine_btn, height_btn, weight_btn;
	
	private SmartBebeDBOpenHelper mDbOpenHelper;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.diary_write);
	    
	    Intent intent = getIntent();
	    modify_id = intent.getLongExtra("diary_id", -1);
	    isModify = intent.getBooleanExtra("isModify", false);
	    		
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
	    
	    OnClickListener subInfoClick = new OnClickListener() {
			@Override
			public void onClick(View v) {
				View innerView = getLayoutInflater().inflate(R.layout.diary_subinfo, null);
				final EditText subinfo_editText = (EditText)innerView.findViewById(R.id.diary_subinfo_editText);
				AlertDialog.Builder ad = new AlertDialog.Builder(Activity_DiaryWrite.this);
				
				switch(v.getId()) {
				case R.id.diary_write_location_btn :
					subinfo_editText.setInputType(InputType.TYPE_CLASS_TEXT);
					subinfo_editText.setText(location_info);
					ad.setTitle("Location Option");
					ad.setView(innerView);
					ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							
						}
					}).setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							location_info = subinfo_editText.getText().toString();
						}
					});
					ad.show();
					
					break;
				case R.id.diary_write_height_btn :
					subinfo_editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
					subinfo_editText.setText(String.valueOf(height_info));
					ad.setTitle("Height Option");
					ad.setView(innerView);
					ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							
						}
					}).setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							height_info = Float.parseFloat(subinfo_editText.getText().toString());
						}
					});
					ad.show();
					
					break;
				case R.id.diary_write_weight_btn :
					subinfo_editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
					subinfo_editText.setText(String.valueOf(weight_info));
					ad.setTitle("Weight Option");
					ad.setView(innerView);
					ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							
						}
					}).setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							weight_info = Float.parseFloat(subinfo_editText.getText().toString());
						}
					});
					ad.show();
					
					break;
				}
			}
		};

	    location_btn.setOnClickListener(subInfoClick);
	    vaccine_btn.setOnClickListener(subInfoClick);
	    height_btn.setOnClickListener(subInfoClick);
	    weight_btn.setOnClickListener(subInfoClick);
	    
	    if( isModify == true )
	    	setDiaryContent(modify_id);
	}
	
	public void setDiaryContent(long id) {
		Cursor mCursor = null;
		String diary_id = String.valueOf(id);
		
		mCursor = mDbOpenHelper.getMatchAttr(SmartBebeDataBase.CreateDB._TABLE_DIARY_CONTENT, SmartBebeDataBase.CreateDB.DIARY_ID, diary_id);
		
		mCursor.moveToFirst();

		title_editText.setText(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_TITLE)));
		write_day = mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_TIME));
		content_editText.setText(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_CONTENT)));
		location_info = mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_LOCATION));
		height_info = Float.parseFloat(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_HEIGHT)));
		weight_info = Float.parseFloat(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_WEIGHT)));
		
		mCursor.close();
		
		SimpleDateFormat dayF = new SimpleDateFormat("E"); 
		SimpleDateFormat dateF = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat dateF1 = new SimpleDateFormat("yyyy.MM.dd");
		Date date = null;
		Date date1 = null;
		try {
			date = dateF.parse(write_day);
		} catch (ParseException e) { e.printStackTrace(); }
		String dayName = dayF.format(date);
		String str = dateF1.format(date);
		day_editText.setText(str + " (" + dayName + ")");
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
				if(isModify == true) {
					mDbOpenHelper.updateDiary(SmartBebeDataBase.CreateDB._TABLE_DIARY_CONTENT, SmartBebePreference.CURRENT_BABY_ID,
							String.valueOf(modify_id), title_editText.getText().toString(), write_day, content_editText.getText().toString(),
							location_info, "", height_info, weight_info);
				}
				else {
					mDbOpenHelper.insertDiary(SmartBebeDataBase.CreateDB._TABLE_DIARY_CONTENT,
							SmartBebePreference.CURRENT_BABY_ID, title_editText.getText().toString(), write_day, content_editText.getText().toString(),
							location_info, "", height_info, weight_info);
					if(height_info != 0 )
						mDbOpenHelper.insertHeight(SmartBebeDataBase.CreateDB._TABLE_HEIGHT_CONTENT, SmartBebePreference.CURRENT_BABY_ID, write_day, height_info);
					if(weight_info != 0 )
						mDbOpenHelper.insertWeight(SmartBebeDataBase.CreateDB._TABLE_WEIGHT_CONTENT, SmartBebePreference.CURRENT_BABY_ID, write_day, weight_info);
				}
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
				v.setImageResource(R.drawable.diary_location_disable_128x128);
			else
				v.setImageResource(R.drawable.diary_location_normal_128x128);
			break;		
		case R.id.diary_write_vaccine_btn :
			if( event.getAction() == MotionEvent.ACTION_DOWN )
				v.setImageResource(R.drawable.diary_vaccine_disable_128x128);
			else
				v.setImageResource(R.drawable.diary_vaccine_normal_128x128);
			break;		
		case R.id.diary_write_height_btn :
			if( event.getAction() == MotionEvent.ACTION_DOWN )
				v.setImageResource(R.drawable.diary_height_disable_128x128);
			else
				v.setImageResource(R.drawable.diary_height_normal_128x128);
			break;		
		case R.id.diary_write_weight_btn :
			if( event.getAction() == MotionEvent.ACTION_DOWN )
				v.setImageResource(R.drawable.diary_weight_disable_128x128);
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
			String str = data.getStringExtra("day_info").toString() + "." + data.getStringExtra("day");
			write_day = str.replace(".", "");
			SimpleDateFormat sdf_ = new SimpleDateFormat("E"); 
			SimpleDateFormat dateF = new SimpleDateFormat("yyyyMMdd");
			Date date = null;
			try {
				date = dateF.parse(write_day);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String dayName = sdf_.format(date);
			day_editText.setText(str + " (" + dayName + ")");
		}
	}
}
