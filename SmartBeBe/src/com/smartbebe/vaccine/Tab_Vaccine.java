package com.smartbebe.vaccine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import android.widget.*;

public class Tab_Vaccine extends Fragment implements OnClickListener {
	private ListView private_listview, all_listview;
	private ImageButton private_view_btn, all_view_btn;
	
	private ArrayList<Vaccine_List> vaccineArr;
	private Vaccine_ListAdapter vaccineAdapter;
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Cursor mCursor;
    private long nowMilliTime = Calendar.getInstance().getTimeInMillis();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tab_vaccine, container, false);
		
		private_view_btn = (ImageButton)v.findViewById(R.id.vaccine_private_view_btn);
		all_view_btn = (ImageButton)v.findViewById(R.id.vaccine_all_view_btn);
		private_listview = (ListView)v.findViewById(R.id.vaccine_private_listview);
		all_listview = (ListView)v.findViewById(R.id.vaccine_all_listview);
		
		vaccineArr = new ArrayList<Vaccine_List>();
		
		private_view_btn.setOnClickListener(this);
		all_view_btn.setOnClickListener(this);
		
		setVaccineList();
		
		return v;
	}
	public void setVaccineList() {
		mDbOpenHelper = new SmartBebeDBOpenHelper(getActivity());
		mDbOpenHelper.open();
		
		mCursor = mDbOpenHelper.getAllColumns(SmartBebeDataBase.CreateDB._TABLE_VACCINE_CONTENT);
		
		while(mCursor.moveToNext())
			vaccineArr.add(new Vaccine_List(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.VACCINE_DISEASE))));
		
		vaccineAdapter = new Vaccine_ListAdapter(getActivity(), R.layout.vaccine_listitem, vaccineArr);
		
		all_listview.setAdapter(vaccineAdapter);
		
		setPrivateList();
		
		mDbOpenHelper.close();
	}
	
	public void setPrivateList() {
	    Date mDate;
	    
		try {
			String date_str = SmartBebePreference.CURRENT_BABY_BIRTHDAY;
			SimpleDateFormat dateF = new SimpleDateFormat("yyyyMMdd");
			mDate = dateF.parse(date_str);
		    long birthday_millis = mDate.getTime();
		    
		    Log.d("sply", String.valueOf(birthday_millis));
		    
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.vaccine_all_view_btn :
			all_listview.setVisibility(View.VISIBLE);
			private_listview.setVisibility(View.GONE);
			break;
		case R.id.vaccine_private_view_btn :
			private_listview.setVisibility(View.VISIBLE);
			all_listview.setVisibility(View.INVISIBLE);
			break;
		}
	}
}
