package com.smartbebe.culture;

import java.util.ArrayList;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class Tab_Culture extends Fragment {
	private ListView culture_listview;
	private ArrayList<CultureData> cultureList;
	private CultureListAdapter cultureAdapter;
	
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Cursor mCursor;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tab_culture, container, false);
		
		culture_listview = (ListView)v.findViewById(R.id.culture_listview);
		
		cultureList = new ArrayList<CultureData>();
		
		mDbOpenHelper = new SmartBebeDBOpenHelper(getActivity());
		mDbOpenHelper.open();
		
		mCursor = mDbOpenHelper.getAllColumns(SmartBebeDataBase.CreateDB._TABLE_CULTURE_CONTENT);
		
		while( mCursor.moveToNext() ) {
			cultureList.add(new CultureData(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.CULTURE_NAME)),
					mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.CULTURE_LINK))));
		}
		
		cultureAdapter = new CultureListAdapter(getActivity(), R.layout.culture_listitem, cultureList);
		
		culture_listview.setAdapter(cultureAdapter);
		
		culture_listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> av, View v, int position, long id) {
				Intent intent = new Intent(getActivity(), Activity_CultureDetail.class);
				intent.putExtra("culture_id", id);
				
				startActivity(intent);
			}
		});
		return v;
	}
}
