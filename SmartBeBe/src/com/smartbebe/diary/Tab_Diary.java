package com.smartbebe.diary;

import java.util.ArrayList;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Tab_Diary extends Fragment {
	private DiaryContentAdapter diaryAdapter;
	private ListView diary_listview;
	private SmartBebeDBOpenHelper mDbOpenHelper;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tab_diary, container, false);
		
		diary_listview = (ListView)v.findViewById(R.id.diary_listview);
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
	
	public void setDiaryContent() {
		ArrayList<Diary_ContentList> diaryContentList = new ArrayList<Diary_ContentList>();
		Cursor mCursor = null;
		mDbOpenHelper = new SmartBebeDBOpenHelper(getActivity());
		mDbOpenHelper.open();
	
		mCursor = mDbOpenHelper.getAllColumns(SmartBebeDataBase.CreateDB._TABLE_DIARY_CONTENT);
		
		while( mCursor.moveToNext() ) {
			diaryContentList.add(new Diary_ContentList(
					mCursor.getInt(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_ID)),
					mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_TITLE)),
					mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_TIME)),
					mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_CONTENT)),					
					mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_LOCATION)),
					mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_VACCINE)),
					mCursor.getFloat(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_HEIGHT)),
					mCursor.getFloat(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.DIARY_WEIGHT))));
		}
		mCursor.close();
		mDbOpenHelper.close();

		diaryAdapter = new DiaryContentAdapter(getActivity(), R.layout.diary_listitem, diaryContentList);
		diary_listview.setAdapter(diaryAdapter);
	}
}
