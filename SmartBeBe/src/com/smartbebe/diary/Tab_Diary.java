package com.smartbebe.diary;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;
import com.smartbebe.def.SmartBebePreference;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Tab_Diary extends Fragment implements OnClickListener {
	private DiaryContentAdapter diaryAdapter;
	private ListView diary_listview, calendar_listview;
	private ImageButton diary_view_btn, calendar_view_btn;
	private SmartBebeDBOpenHelper mDbOpenHelper;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tab_diary, container, false);

		diary_view_btn = (ImageButton)v.findViewById(R.id.diary_view_btn);
		calendar_view_btn = (ImageButton)v.findViewById(R.id.calendar_view_btn);
		
		calendar_listview = (ListView)v.findViewById(R.id.diary_listview2);
		diary_listview = (ListView)v.findViewById(R.id.diary_listview);

		diary_view_btn.setOnClickListener(this);
		calendar_view_btn.setOnClickListener(this);
		
		diary_listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> av, View v, int position, long id) {
			/*	Fragment temp = getSupportFragmentManager().findFragmentById(R.id.mainview_linear);
				FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
				
				switch((int)id) {
					case 0 : {
						Tab_Diary tDiaryFragment = new Tab_Diary();
						
						homeStack.push(temp);
						ft.replace(R.id.mainview_linear, tDiaryFragment, "HomeTab");
					}
				}
				ft.addToBackStack("HomeTab");
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
				ft.commitAllowingStateLoss();*/
			}
		});
		setDiaryContent();
		
		return v;
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

		mCursor = mDbOpenHelper.getAllColumns(SmartBebeDataBase.CreateDB._TABLE_DIARY_CONTENT);
		
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
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mCursor.close();
		mDbOpenHelper.close();

		diaryAdapter = new DiaryContentAdapter(getActivity(), R.layout.diary_listitem, diaryContentList);
		diary_listview.setAdapter(diaryAdapter);
	}
}
