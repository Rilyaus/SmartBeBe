package com.smartbebe.culture;

import java.util.ArrayList;

import com.kw.smartbebe.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.*;

class CultureData {
	String name;
	String link;
	
	public CultureData(String name, String link) {
		this.name = name;
		this.link = link;
	}
}

class CultureListAdapter extends BaseAdapter {
	private Context context;
	private int layoutId;
	private ArrayList<CultureData> pArr;
	private LayoutInflater inflater;
	private String cLink;
	
	public CultureListAdapter(Context _context, int _layoutId, ArrayList<CultureData> _pArr) {
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
		cLink = pArr.get(position).link;
		TextView name = (TextView)convertView.findViewById(R.id.culture_name_textView);
		name.setText(pArr.get(position).name);
		
		return convertView;
	}
}