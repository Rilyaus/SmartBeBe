package com.smartbebe.graph;

import java.text.NumberFormat;
import java.util.ArrayList;

import com.kw.smartbebe.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.*;
import android.view.ViewGroup;
import android.widget.*;

public class Graph_ContentList {
	int pId;
	String pTime;
	String pValue;
	
	public Graph_ContentList(int id, String time, String value) {
		pId = id;
		pTime = time;
		pValue = value;
	}
}

class GraphContentAdapter extends BaseAdapter {
	private NumberFormat format = NumberFormat.getCurrencyInstance();
	private Context context;
	private int layoutId;
	private ArrayList<Graph_ContentList> pArr;
	private LayoutInflater inflater;
	
	public GraphContentAdapter(Context _context, int _layoutId, ArrayList<Graph_ContentList> _pArr) {
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
	public Object getItem(int position) {
		return pArr.get(position);
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

		TextView time = (TextView)convertView.findViewById(R.id.graph_time_textView);
		time.setText(pArr.get(position).pTime);
		
		TextView value = (TextView)convertView.findViewById(R.id.graph_value_textView);
		value.setText(pArr.get(position).pValue);
		
		return convertView;
	}
}