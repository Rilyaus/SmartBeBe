package com.smartbebe;

import java.util.ArrayList;

import com.kw.smartbebe.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

class Navigation_MenuList {
	String name;
	Drawable image;
	
	public Navigation_MenuList(String name, Drawable image) {
		this.name = name;
		this.image = image;
	}
}

class NaviMenuListAdapter extends BaseAdapter {
	private Context context;
	private int layoutId;
	private ArrayList<Navigation_MenuList> pArr;
	private LayoutInflater inflater;
	
	public NaviMenuListAdapter(Context _context, int _layoutId, ArrayList<Navigation_MenuList> _pArr) {
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
		TextView name = (TextView)convertView.findViewById(R.id.navi_menu_btn_text);
		name.setText(pArr.get(position).name);

		ImageView image = (ImageView)convertView.findViewById(R.id.navi_menu_btn_image);
		image.setBackground(pArr.get(position).image);
		image.setScaleType(ImageView.ScaleType.CENTER_CROP);
		
		return convertView;
	}
}