package com.smartbebe.vaccine;

import java.util.ArrayList;

import com.kw.smartbebe.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

class Vaccine_List {
	String name;
	
	public Vaccine_List(String name) {
		this.name = name;
	}
}

class Vaccine_ListAdapter extends BaseAdapter {
	private Context context;
	private int layoutId;
	private ArrayList<Vaccine_List> pArr;
	private LayoutInflater inflater;
	
	public Vaccine_ListAdapter(Context _context, int _layoutId, ArrayList<Vaccine_List> _pArr) {
		context = _context;
		layoutId = _layoutId;
		pArr = _pArr;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return pArr.size();
	}

	@Override
	public String getItem(int position) {
		return pArr.get(position).name;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		
		if(convertView == null) {
			convertView = inflater.inflate(layoutId, parent, false);
		}
		TextView name = (TextView)convertView.findViewById(R.id.vaccine_title_textView);
		name.setText(pArr.get(position).name);
		
		return convertView;
	}
}