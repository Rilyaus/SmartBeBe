package com.smartbebe.graph;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;
import com.smartbebe.def.SmartBebePreference;
import com.smartbebe.diary.Diary_ContentList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.support.v4.app.*;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;

public class Tab_Graph extends Fragment implements OnClickListener {
	private GraphContentAdapter graphAdapter;
	private LinearLayout mLayout;
	private GraphicalView mChartView;
	
	private ImageButton weight_view_btn, height_view_btn;
	private ListView weight_listview, height_listview;
	private Button startBtn = null;
	
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Cursor mCursor;
	
	private String[] mMonth = new String[] {
				"1", "2", "3", "4", "5", "6", "7", "8"
			};

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tab_graph, container, false);

		weight_view_btn = (ImageButton)v.findViewById(R.id.graph_weight_view_btn);
		height_view_btn = (ImageButton)v.findViewById(R.id.graph_height_view_btn);
		weight_listview = (ListView)v.findViewById(R.id.graph_weight_listview);
		height_listview = (ListView)v.findViewById(R.id.graph_height_listview);
        mLayout = (LinearLayout)v.findViewById(R.id.chart);

        weight_view_btn.setOnClickListener(this);
        height_view_btn.setOnClickListener(this);
        
        setGraphContent();
        setGraph();
        
		return v;
    }
    
    public void setGraph() {
    	int[] x = { 1,2,3,4,5,6};
    	int[] average = { 50,53,60,73,80,150};
    	int[] mybaby = {3, 1, 3, 4, 5, 6};
    	
    	// Creating an  XYSeries for Income
    	XYSeries averageSeries = new XYSeries("Average Height");
    	// Creating an  XYSeries for Income
    	XYSeries mybabySeries = new XYSeries("My Baby Height");
    	// Adding data to Income and Expense Series
    	for(int i=0;i<x.length;i++){
    		averageSeries.add(x[i], average[i]);
    		mybabySeries.add(x[i],mybaby[i]);
    	}
    	
    	// Creating a dataset to hold each series
    	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    	// Adding Income Series to the dataset
    	dataset.addSeries(averageSeries);
    	// Adding Expense Series to dataset
    	dataset.addSeries(mybabySeries);    	
    	
    	
    	// Creating XYSeriesRenderer to customize incomeSeries
    	XYSeriesRenderer averageRenderer = new XYSeriesRenderer();
    	averageRenderer.setColor(Color.RED);
    	averageRenderer.setPointStyle(PointStyle.CIRCLE);
    	averageRenderer.setFillPoints(true);
    	averageRenderer.setLineWidth(4);
    	averageRenderer.setDisplayChartValues(true);
    	
    	// Creating XYSeriesRenderer to customize expenseSeries
    	XYSeriesRenderer mybabayRenderer = new XYSeriesRenderer();
    	mybabayRenderer.setColor(Color.BLUE);
    	mybabayRenderer.setPointStyle(PointStyle.CIRCLE);
    	mybabayRenderer.setFillPoints(true);
    	mybabayRenderer.setLineWidth(4);
    	mybabayRenderer.setDisplayChartValues(true);
    	
    	
    	// Creating a XYMultipleSeriesRenderer to customize the whole chart
    	XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
    	multiRenderer.setXLabels(0);
    	//multiRenderer.setChartTitle("Average vs MyBaby Chart");
    	multiRenderer.setMargins(new int[]{45,70,130,60});
    	multiRenderer.setLabelsTextSize(25);
    	multiRenderer.setPointSize(10);
    	multiRenderer.setAxesColor(Color.BLACK);
    	multiRenderer.setGridColor(Color.BLACK);
    	multiRenderer.setXLabelsColor(Color.BLACK);
    	multiRenderer.setYLabelsColor(0, Color.BLACK);
    	multiRenderer.setLabelsColor(Color.BLACK);
    	multiRenderer.setApplyBackgroundColor(true);
    	
    	multiRenderer.setXTitle("MONTH");
    	multiRenderer.setAxisTitleTextSize(40);
    	multiRenderer.setYTitle("HEIGHT");
    	multiRenderer.setLegendTextSize(40);
    	multiRenderer.setBackgroundColor(Color.WHITE);
    	multiRenderer.setMarginsColor(Color.WHITE);
    	multiRenderer.setYAxisMax(180);
    	multiRenderer.setYLabels(10);
    	//multiRenderer.setXLabels(6);
    	multiRenderer.setShowGrid(true);
    	//multiRenderer.setZoomButtonsVisible(true);    	    	
    	for(int i=0;i<mMonth.length;i++){
    		multiRenderer.addXTextLabel(i+1, mMonth[i]);    		
    	}    	
    	
    	
    	//for(int i=0;i<300;i++)
    	//{
    	//	multiRenderer.setYAxisMax(300)
    	//}
    	
    	multiRenderer.addSeriesRenderer(averageRenderer);
    	multiRenderer.addSeriesRenderer(mybabayRenderer);
    	
    	if(mChartView ==null)
        {
        	mChartView = ChartFactory.getLineChartView(getActivity(), dataset, multiRenderer);
        	mLayout.addView(mChartView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
    	else
    		mChartView.repaint();
    }

    public void setGraphContent() {
		ArrayList<Graph_ContentList> heightContentList = new ArrayList<Graph_ContentList>();
		ArrayList<Graph_ContentList> weightContentList = new ArrayList<Graph_ContentList>();
		Cursor mCursor = null;
		mDbOpenHelper = new SmartBebeDBOpenHelper(getActivity());
		mDbOpenHelper.open();

		mCursor = mDbOpenHelper.getAllColumns(SmartBebeDataBase.CreateDB._TABLE_HEIGHT_CONTENT);
		
		try {
			while( mCursor.moveToNext() ) {
				if( SmartBebePreference.CURRENT_BABY_ID == mCursor.getInt(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.BABY_ID))) {
					SimpleDateFormat dateF = new SimpleDateFormat("yyyyMMdd");
					Date date = dateF.parse(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.HEIGHT_TIME)));
					DateFormat format3 = DateFormat.getDateInstance(DateFormat.MEDIUM);
					String time_str = format3.format(date).replace(". ", "-").replace(".", "");
					
					heightContentList.add(new Graph_ContentList(
							mCursor.getInt(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.HEIGHT_ID)),
							time_str,
							mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.HEIGHT_VALUE)) + "cm"));
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		mCursor = null;

		mCursor = mDbOpenHelper.getAllColumns(SmartBebeDataBase.CreateDB._TABLE_WEIGHT_CONTENT);
		
		try {
			while( mCursor.moveToNext() ) {
				if( SmartBebePreference.CURRENT_BABY_ID == mCursor.getInt(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.BABY_ID))) {
					SimpleDateFormat dateF = new SimpleDateFormat("yyyyMMdd");
					Date date = dateF.parse(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.WEIGHT_TIME)));
					DateFormat format3 = DateFormat.getDateInstance(DateFormat.MEDIUM);
					String time_str = format3.format(date).replace(". ", "-").replace(".", "");
					
					weightContentList.add(new Graph_ContentList(
							mCursor.getInt(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.WEIGHT_ID)),
							time_str,
							mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.WEIGHT_VALUE)) + "kg"));
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		graphAdapter = new GraphContentAdapter(getActivity(), R.layout.graph_listitem, heightContentList);
		height_listview.setAdapter(graphAdapter);
		graphAdapter = new GraphContentAdapter(getActivity(), R.layout.graph_listitem, weightContentList);
		weight_listview.setAdapter(graphAdapter);
		

		mCursor.close();
		mDbOpenHelper.close();
    }
    
    @Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.graph_height_view_btn :
			height_listview.setVisibility(View.VISIBLE);
			weight_listview.setVisibility(View.GONE);
			break;
		case R.id.graph_weight_view_btn :
			weight_listview.setVisibility(View.VISIBLE);
			height_listview.setVisibility(View.GONE);
			break;
		}
	}
}

