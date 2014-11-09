package com.smartbebe.policy;

import java.util.ArrayList;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class Tab_Policy extends Fragment {
	private ExpandableListView listview;
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Cursor mCursor;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tab_policy, container, false);
		listview = (ExpandableListView)v.findViewById(R.id.policy_listview);
		
		listview.setAdapter(new ExpandablePolicyAdapter(getActivity(), createData()));
		
		return v;
	}
	
	@SuppressWarnings("unchecked")
	protected ArrayList<PolicyData> createData() {
		mDbOpenHelper = new SmartBebeDBOpenHelper(getActivity());
		mDbOpenHelper.open();
		
		ArrayList<Object> obj = new ArrayList<Object>();
		ArrayList<String> policy1 = new ArrayList<String>();
		ArrayList<String> policy2 = new ArrayList<String>();
		ArrayList<String> policy3 = new ArrayList<String>();
		ArrayList<PolicyData> PolicyData = new ArrayList<PolicyData>();

		mCursor = mDbOpenHelper.getMatchPolicyGroup(SmartBebeDataBase.CreateDB._TABLE_POLICY_CONTENT, getString(R.string.policy_group1));
		while(mCursor.moveToNext())
			policy1.add(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.POLICY_NAME)));
		obj.add(policy1);

		mCursor = mDbOpenHelper.getMatchPolicyGroup(SmartBebeDataBase.CreateDB._TABLE_POLICY_CONTENT, getString(R.string.policy_group2));
		while(mCursor.moveToNext())
			policy2.add(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.POLICY_NAME)));
		obj.add(policy2);

		mCursor = mDbOpenHelper.getMatchPolicyGroup(SmartBebeDataBase.CreateDB._TABLE_POLICY_CONTENT, getString(R.string.policy_group3));
		while(mCursor.moveToNext())
			policy3.add(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.POLICY_NAME)));
		obj.add(policy3);
		
		for(int i=0; i< obj.size(); i++)
			PolicyData.add(new PolicyData(getResources().getStringArray(R.array.policyList)[i], (ArrayList<String>)obj.get(i)));
		
		mDbOpenHelper.close();
		
		return PolicyData;
	}
}
