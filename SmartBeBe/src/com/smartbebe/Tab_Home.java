package com.smartbebe;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;
import com.smartbebe.def.SmartBebePreference;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.MovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.*;

public class Tab_Home extends Fragment implements OnClickListener {
	private BabyChange babyChangeListener;
	
	private TextView name, day, height, weight;
	private ImageView gender, picture;
	private ImageButton prev, next;
	
	private String birthday;
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Cursor mCursor;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tab_home, container, false);

		picture = (ImageButton)v.findViewById(R.id.home_photo_add_btn);
		name = (TextView)v.findViewById(R.id.home_name_textview);
		gender = (ImageView)v.findViewById(R.id.home_gender_image);
		day = (TextView)v.findViewById(R.id.home_birthday_text);
		height = (TextView)v.findViewById(R.id.home_height_text);
		weight = (TextView)v.findViewById(R.id.home_weight_text);

		prev = (ImageButton)v.findViewById(R.id.home_change_prev_btn);
		next = (ImageButton)v.findViewById(R.id.home_change_next_btn);

		prev.setOnClickListener(this);
		next.setOnClickListener(this);
		
		mDbOpenHelper = new SmartBebeDBOpenHelper(getActivity());
		mDbOpenHelper.open();
		
	//	if( SmartBebePreference.CURRENT_BABY_ID == -1 )
		
		mCursor = mDbOpenHelper.getAllColumns(SmartBebeDataBase.CreateDB._TABLE_BABY_INFO);
		
	//	else
	//		mCursor = mDbOpenHelper.getMatchAttr(SmartBebeDataBase.CreateDB._TABLE_BABY_INFO,
	//				SmartBebeDataBase.CreateDB.BABY_ID,
	//				String.valueOf(SmartBebePreference.CURRENT_BABY_ID));
			
		setBabyInfo("init");
		
		return v;
	}
	
	public boolean onCreateOptionMenu(Menu menu) {
		getActivity().getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		
		if( id == R.id.baby_remove_menu )
			babyInfoRemove();
				
		return super.onOptionsItemSelected(item);
	}
	
	public void babyInfoRemove() {
		Log.d("sply", "Remove");
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.home_change_prev_btn :
			setBabyInfo("prev");
			break;
		case R.id.home_change_next_btn :
			setBabyInfo("next");
			break;
		}
	}
	
	public interface BabyChange {
		public void babyChange(String id, String name, String birthday);
	}
	
	private void setBabyInfo(String way) {
		if( way == "init")
			mCursor.moveToFirst();
		else if( way == "next") {
			if(mCursor.isLast() )
				mCursor.moveToFirst();
			else mCursor.moveToNext();
		}
		else if( way == "prev") {
			if( mCursor.isFirst() )
				mCursor.moveToLast();
			else mCursor.moveToPrevious();
		}

		String pName = mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.BABY_NAME));
		
		babyChangeListener.babyChange(
				mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.BABY_ID)),
				pName,
				mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.BABY_BIRTHDAY)));
		
		try {
			SimpleDateFormat dateF = new SimpleDateFormat("yyyyMMdd");
			Date date = dateF.parse(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.BABY_BIRTHDAY)));
			
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy년 MM월dd일");
			birthday = format1.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if( mCursor.getInt(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.BABY_GENDER)) == 0 )
			gender.setImageResource(R.drawable.male_128x128);
		else
			gender.setImageResource(R.drawable.female_128x128);
		
		name.setText(pName);
		day.setText(birthday);
		height.setText(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.BABY_HEIGHT)) + "cm");
		weight.setText(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.BABY_WEIGHT)) + "kg");
		
		if( pName.equals("추사랑") )
			picture.setImageDrawable(getResources().getDrawable(R.drawable.sample_baby_image));
		else
			picture.setImageDrawable(getResources().getDrawable(R.drawable.photo_sample));
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		babyChangeListener = (BabyChange)activity;
	}
}
