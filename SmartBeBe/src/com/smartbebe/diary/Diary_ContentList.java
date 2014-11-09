package com.smartbebe.diary;

import java.text.NumberFormat;
import java.util.ArrayList;

import com.kw.smartbebe.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.*;
import android.view.ViewGroup;
import android.widget.*;

public class Diary_ContentList {
	int pId;
	
	String pTitle;
	String pTime;
	String pContent;
	
	String pLocation;
	String pVaccine;
	float pWeight;
	float pHeight;
	
	public Diary_ContentList(int id, String title, String time, String content, String location, String vaccine, float weight, float height) {
		pId = id;
		
		pTitle = title;
		pTime = time;
		pContent = content;
		
		pLocation = location;
		pVaccine = vaccine;
		pWeight = weight;
		pHeight = height;
	}
}

class DiaryContentAdapter extends BaseAdapter implements OnTouchListener {
	private NumberFormat format = NumberFormat.getCurrencyInstance();
	private Context context;
	private int layoutId;
	private ArrayList<Diary_ContentList> pArr;
	private LayoutInflater inflater;
	
	public DiaryContentAdapter(Context _context, int _layoutId, ArrayList<Diary_ContentList> _pArr) {
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
		return pArr.get(position).pId;
	}
	
	public float getHeight(int position) {
		return pArr.get(position).pHeight;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		
		if(convertView == null) {
			convertView = inflater.inflate(layoutId, parent, false);
		}

		TextView title = (TextView)convertView.findViewById(R.id.diary_title_text);
		title.setText(pArr.get(position).pTitle);
		
		TextView time = (TextView)convertView.findViewById(R.id.diary_time_text);
		time.setText(pArr.get(position).pTime);
		
		TextView content = (TextView)convertView.findViewById(R.id.diary_content_text);
		content.setText(pArr.get(position).pContent);

		ImageButton location = (ImageButton)convertView.findViewById(R.id.diary_location_btn);
		if(pArr.get(position).pLocation.equals(""))
			location.setBackgroundResource(R.drawable.diary_location_disable_128x128);
		else
			location.setOnTouchListener(this);
			
		
		ImageButton vaccine = (ImageButton)convertView.findViewById(R.id.diary_vaccine_btn);
		if(pArr.get(position).pVaccine.equals(""))
			vaccine.setBackgroundResource(R.drawable.diary_vaccine_disable_128x128);
		else
			vaccine.setOnTouchListener(this);
		
		ImageButton height = (ImageButton)convertView.findViewById(R.id.diary_height_btn);
		if( !(pArr.get(position).pHeight > 0) )
			height.setBackgroundResource(R.drawable.diary_height_disable_128x128);
		else
			height.setOnTouchListener(this);
		
		ImageButton weight = (ImageButton)convertView.findViewById(R.id.diary_weight_btn);
		if( !(pArr.get(position).pWeight > 0) )
			weight.setBackgroundResource(R.drawable.diary_weight_disable_128x128);
		else
			weight.setOnTouchListener(this);
		
		return convertView;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(v.getId()) {
		case R.id.diary_location_btn :
			if( event.getAction() == MotionEvent.ACTION_DOWN )
				v.setBackgroundResource(R.drawable.diary_location_pressed_128x128);
			else if( event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL )
				v.setBackgroundResource(R.drawable.diary_location_normal_128x128);
			break;
		case R.id.diary_vaccine_btn :
			if( event.getAction() == MotionEvent.ACTION_DOWN )
				v.setBackgroundResource(R.drawable.diary_vaccine_pressed_128x128);
			else if( event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL )
				v.setBackgroundResource(R.drawable.diary_vaccine_normal_128x128);
			break;
		case R.id.diary_height_btn :
			if( event.getAction() == MotionEvent.ACTION_DOWN )
				v.setBackgroundResource(R.drawable.diary_height_pressed_128x128);
			else if( event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL )
				v.setBackgroundResource(R.drawable.diary_height_normal_128x128);
			break;
		case R.id.diary_weight_btn :
			if( event.getAction() == MotionEvent.ACTION_DOWN )
				v.setBackgroundResource(R.drawable.diary_weight_pressed_128x128);
			else if( event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL )
				v.setBackgroundResource(R.drawable.diary_weight_normal_128x128);
			break;
		}
		return false;
	}
}