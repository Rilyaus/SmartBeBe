package com.kw.smartbebe;

import java.util.ArrayList;

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
	private ArrayList<Diary_ContentList> diaryListArr;
	private ListView diary_listview;
	private SmartBebeDBOpenHelper mDbOpenHelper;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tab_diary, container, false);
		
		diary_listview = (ListView)v.findViewById(R.id.diary_listview);
		diary_listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> av, View v, int position, long id) {
				Log.d("sply", String.valueOf(av.getAdapter().getItem(position)));
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
		
		diaryListArr = new ArrayList<Diary_ContentList>();
		
		setDiaryContent();
		
		return v;
	}
	
	public void setDiaryContent() {
	/*	ArrayList<Diary_ContentList> diaryContentList = new ArrayList<Diary_ContentList>();
		Cursor mCursor = null;
		mDbOpenHelper = new SmartBebeDBOpenHelper(getActivity());
		
		mDbOpenHelper.open();
	
		mCursor = mDbOpenHelper.getDiaryContentList(SmartBebeDataBase.CreateDB._CREATE_DIARY_CONTENT);

		while( mCursor.moveToNext() ) {
			diaryContentList.add(new Diary_ContentList(mCursor.getInt(mCursor.getColumnIndex("id")),
					mCursor.getString(mCursor.getColumnIndex("title")),
					mCursor.getString(mCursor.getColumnIndex("time")),
					mCursor.getString(mCursor.getColumnIndex("content")),					
					mCursor.getString(mCursor.getColumnIndex("location")),
					mCursor.getString(mCursor.getColumnIndex("vaccine")),
					mCursor.getFloat(mCursor.getColumnIndex("height")),
					mCursor.getFloat(mCursor.getColumnIndex("weight"))));
		}
		
		mCursor.close();
		mDbOpenHelper.close();*/
		
		
		diaryListArr.add(new Diary_ContentList(1 ,"사랑이의 생일", "2014-3-24", "사랑이의 생일을 맞아 미니마우스 인형을 사고 할머니와 함께 밥을 먹었다.",
				"Seoul","", 0, 23));

		diaryListArr.add(new Diary_ContentList(2 ,"유토랑 디즈니랜드", "2014-4-26", "사랑이의 남자친구 유토와 함께 디즈니랜드에 놀러 갔다.",
				"NonHyun","", 0, 0));
		
		diaryListArr.add(new Diary_ContentList(3 ,"병원에서 주사 맞은 날", "2014-6-10", "DTP 예방접종을 하러 병원에 갔다. 주사가 많이 아팠는지 엄청 울었다.",
				"NonHyun","DTP", 0, 0));
		
		diaryListArr.add(new Diary_ContentList(4 ,"신체검사!", "2014-7-4", "신체 검사를 했더니 키가 8cm나 자랐다! 몸무게도 3kg나 늘었다.",
				"","", 98, 24));

		diaryAdapter = new DiaryContentAdapter(getActivity(), R.layout.diary_listitem, diaryListArr);

		diary_listview.setAdapter(diaryAdapter);
	}
}
