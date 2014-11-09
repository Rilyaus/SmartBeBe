package com.smartbebe.policy;

import java.util.ArrayList;

import com.kw.smartbebe.R;
import com.smartbebe.Activity_Signup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;

public class ExpandablePolicyAdapter extends BaseExpandableListAdapter {
    Context mContext;
    ArrayList<PolicyData> mPolicyData;
     
    public ExpandablePolicyAdapter(Context context, ArrayList<PolicyData> phone) {
        mContext = context;
        mPolicyData = phone; 
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return mPolicyData.get(groupPosition).policyList.get(childPosition);
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
         
        TextView text = (TextView)view.findViewById(R.id.policy_child_textView);
        text.setText(mPolicyData.get(groupPosition).policyList.get(childPosition));
        
        text.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				TextView tv = (TextView)v;
				Intent intent = new Intent(mContext, Activity_PolicyDetail.class);
				intent.putExtra("policy_name", tv.getText());
				
				mContext.startActivity(intent);
			}
		});
        
        return view;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return mPolicyData.get(groupPosition).policyList.size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return mPolicyData.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return mPolicyData.size();
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
         
        TextView text = (TextView)view.findViewById(R.id.policy_group_textView);
        text.setText(mPolicyData.get(groupPosition).category);
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
        View view = inflater.inflate(R.layout.policy_listitem_child, null);
        return view;
    }
     
    //Parent(Group)의 View의 XML을 생성 
    public View getParentGenericView() {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.policy_listitem, null);
        return view;
    }
}