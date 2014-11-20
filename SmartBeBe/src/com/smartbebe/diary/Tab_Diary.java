package com.smartbebe.diary;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;
import com.smartbebe.def.SmartBebePreference;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class Tab_Diary extends Fragment implements OnClickListener {
	private DiaryContentAdapter diaryAdapter;
	private ListView diary_listview, calendar_listview;
	private ImageButton diary_view_btn, calendar_view_btn;
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Boolean isResume = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tab_diary, container, false);

		diary_view_btn = (ImageButton)v.findViewById(R.id.diary_view_btn);
		calendar_view_btn = (ImageButton)v.findViewById(R.id.calendar_view_btn);
		
		calendar_listview = (ListView)v.findViewById(R.id.diary_listview2);
		diary_listview = (ListView)v.findViewById(R.id.diary_listview);
		
		diary_view_btn.setOnClickListener(this);
		calendar_view_btn.setOnClickListener(this);
		
		setDiaryContent();
		
		diary_listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> av, View v, int position, long id) {
				Intent intent = new Intent(getActivity(), Activity_DiaryWrite.class);
				intent.putExtra("isModify", true);
				intent.putExtra("diary_id", av.getItemIdAtPosition(position));
				
				startActivity(intent);
			}
		});
		
		return v;
	}
	
	public void onResume() {
		super.onResume();
		
		if( isResume ) {
			isResume = false;
			Fragment frg = null;
			frg = getActivity().getSupportFragmentManager().findFragmentByTag("HomeTab");
			final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
			ft.detach(frg);
			ft.attach(frg);
			ft.commit();
		}
	}
	
	public void onPause() {
		super.onPause();
		
		isResume = true;
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.diary_view_btn :
			calendar_listview.setVisibility(View.INVISIBLE);
			diary_listview.setVisibility(View.VISIBLE);
			break;
		case R.id.calendar_view_btn :
			calendar_listview.setVisibility(View.VISIBLE);
			diary_listview.setVisibility(View.INVISIBLE);
			break;			
		}
	}
	
	public void setDiaryContent() {
		ArrayList<Diary_ContentList> diaryContentList = new ArrayList<Diary_ContentList>();
		Cursor mCursor = null;
		mDbOpenHelper = new SmartBebeDBOpenHelper(getActivity());
		mDbOpenHelper.open();

		mCursor = mDbOpenHelper.getOrderByAllColumns(SmartBebeDataBase.CreateDB._TABLE_DIARY_CONTENT, SmartBebeDataBase.CreateDB.DIARY_TIME);
		Log.d("sply", String.valueOf(mCursor.getCount()));
		
		try {
			while( mCursor.moveToNext() ) {
				if( SmartBebePreference.CURRENT_BABY_ID == mCursor.getInt(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.BABY_ID))) {
					SimpleDateFormat dateF = new SimpleDateFormat("yyyyMMdd");
					Date date = dateF.parse(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_TIME)));
					DateFormat format3 = DateFormat.getDateInstance(DateFormat.MEDIUM);
					String time_str = format3.format(date).replace(". ", "-").replace(".", "");
					
					diaryContentList.add(new Diary_ContentList(
							mCursor.getInt(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_ID)),
							mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_TITLE)),
							time_str,
							mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_CONTENT)),					
							mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_LOCATION)),
							mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_VACCINE)),
							mCursor.getFloat(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_HEIGHT)),
							mCursor.getFloat(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_WEIGHT))));
				}
			}
			Collections.reverse(diaryContentList);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mCursor.close();
		mDbOpenHelper.close();

		diaryAdapter = new DiaryContentAdapter(getActivity(), R.layout.diary_listitem, diaryContentList);
		diary_listview.setAdapter(diaryAdapter);
	}
}
