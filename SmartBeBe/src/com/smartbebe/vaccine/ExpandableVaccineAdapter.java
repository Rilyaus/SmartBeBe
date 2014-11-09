package com.smartbebe.vaccine;

import java.util.ArrayList;

import com.kw.smartbebe.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class ExpandableVaccineAdapter extends BaseExpandableListAdapter {
    Context mContext;
    ArrayList<VaccineData> mVaccineData;
     
    public ExpandableVaccineAdapter(Context context, ArrayList<VaccineData> phone) {
        mContext = context;
        mVaccineData = phone; 
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return mVaccineData.get(groupPosition).VaccineList.get(childPosition);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }
 
    //ChildView에 데이터 뿌리기 
    @Override
    public View getChildView(int groupPosition, int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null) {
            view = getChildGenericView();
        } else {
            view = convertView;
        }
         
        TextView text = (TextView)view.findViewById(R.id.vaccine_child_textView);
        text.setText(mVaccineData.get(groupPosition).VaccineList.get(childPosition));
        return view;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return mVaccineData.get(groupPosition).VaccineList.size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return mVaccineData.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return mVaccineData.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    //GroupView에 데이터 뿌리
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
         
        View view;
        if(convertView == null) {
            view = getParentGenericView();
        } else {
            view = convertView;
        }
         
        TextView text = (TextView)view.findViewById(R.id.vaccine_title_textView);
        text.setText(mVaccineData.get(groupPosition).personal);
        return view;
    }
 
    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }
     
    @Override
    public boolean areAllItemsEnabled() {
        // TODO Auto-generated method stub
        return super.areAllItemsEnabled();
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return false;
    }
     
    //Child의 View의 XML을 생성 
    public View getChildGenericView() {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.vaccine_listitem_child, null);
        return view;
    }
     
    //Parent(Group)의 View의 XML을 생성 
    public View getParentGenericView() {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.vaccine_listitem, null);
        return view;
    }
}