package com.smartbebe.diary;

import java.util.ArrayList;
import java.util.Calendar;









import com.kw.smartbebe.R;

import android.R.integer;
import android.app.Activity;
import android.app.Notification.Action;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class ViewCalendar extends Activity {
	private TextView maintext;
	
	private GridView mGridView;
	private DateAdapter adapter;
	private ArrayList<CalData> arrData;
	private Calendar mCalToday, mCal;
	private int thisYear = 0;
	private int thisMonth = 0;
	private Button mCalPrev, mCalNext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary_calendar);
		
		mCalPrev = (Button)findViewById(R.id.prev);
		mCalNext = (Button)findViewById(R.id.next);
		
		mCalPrev.setOnClickListener(btnClickListener);
		mCalNext.setOnClickListener(btnClickListener);

		maintext = (TextView)findViewById(R.id.maintext);
		
		mCalToday = Calendar.getInstance();
		mCal = Calendar.getInstance();
		//TextView displayDate = (TextView)findViewById(R.id.displayDate);
		thisYear = mCal.get(Calendar.YEAR);
		thisMonth = mCal.get(Calendar.MONTH);
		
		setCalendarDate(thisYear,thisMonth+1);   
	}

	private Button.OnClickListener btnClickListener = new Button.OnClickListener(){
    	public void onClick(View v){
    		switch(v.getId()){
    		case R.id.prev:
    			if(thisMonth>1)
    			{
    				thisMonth--;
    				setCalendarDate(thisYear, thisMonth);
    			}
    			else
    			{
    				thisYear--;
    				thisMonth=12;
    				setCalendarDate(thisYear, thisMonth);
    			}
    			break;
    		case R.id.next:
    			if(thisMonth<12)
    			{
    				thisMonth++;
    				setCalendarDate(thisYear, thisMonth);
    			}
    			else
    			{
    				thisYear++;
    				thisMonth=1;
    				setCalendarDate(thisYear, thisMonth);
    			}
    			break;
    		}
    	}
    };
    
	public void setCalendarDate(int year, int month){
		arrData = new ArrayList<CalData>();	
		mCalToday.set(mCal.get(Calendar.YEAR), month-1, 1);
		
		if( month < 10 )
			maintext.setText(year + ".0" + month);
		else
			maintext.setText(year + "." + month);
   
		int startday = mCalToday.get(Calendar.DAY_OF_WEEK);
		if(startday != 1)
			for(int i=0; i<startday-1; i++)
				arrData.add(null);
		
		mCal.set(Calendar.MONTH, month-1);
		
		for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			mCalToday.set(mCal.get(Calendar.YEAR), month-1, (i+1));
			arrData.add(new CalData((i+1), mCalToday.get(Calendar.DAY_OF_WEEK)));
		}
				
		adapter = new DateAdapter(this, arrData, maintext.getText().toString());
		
		mGridView = (GridView)findViewById(R.id.calGrid);
		mGridView.setAdapter(adapter);
	}
}

class DateAdapter extends BaseAdapter implements OnClickListener{
	final int RESULT_DAY_INFO = 1001;
	private String dayInfo;
	private Context context;
	private ArrayList<CalData> arrData;
	private LayoutInflater inflater;
	private Intent intent = new Intent();
	
	public DateAdapter(Context c, ArrayList<CalData> arr, String dI) {
		this.dayInfo = dI;
		this.context = c;
		this.arrData = arr;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public int getCount() {
		return arrData.size();
	}
	
	public Object getItem(int position) {
		return arrData.get(position);
	}
	
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView = inflater.inflate(R.layout.calendar_viewitem, parent, false);
		
		TextView calendar_item = (TextView)convertView.findViewById(R.id.calendar_item_textView);
		calendar_item.setOnClickListener(this);
		
		if(arrData.get(position) == null)
			calendar_item.setText("");
		else {
			calendar_item.setTag(arrData.get(position).getDayofweek());
			calendar_item.setText(arrData.get(position).getDay()+"");
			if(arrData.get(position).getDayofweek() == 1)
				calendar_item.setTextColor(Color.RED);
			else if(arrData.get(position).getDayofweek() == 7)
				calendar_item.setTextColor(Color.BLUE);
			else
				calendar_item.setTextColor(Color.BLACK);

			calendar_item.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if( event.getAction() == MotionEvent.ACTION_DOWN )
						v.setBackgroundColor(Color.rgb(255, 187, 0));
					else
						v.setBackgroundColor(Color.TRANSPARENT);
					return false;
				}
			});
		}
		return convertView;
	}

	@Override
	public void onClick(View v) {
		TextView tv = (TextView)v;
		String str = tv.getText().toString();
		
		if( Integer.parseInt(str) < 10 )
			str = "0" + str;
		
		if( str != "") {
			intent.putExtra("day", str);
			intent.putExtra("day_info", dayInfo);
			((Activity)context).setResult(RESULT_DAY_INFO, intent);
			((Activity)context).finish();
		}
	}
}