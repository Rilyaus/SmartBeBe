package com.smartbebe.policy;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;

public class Activity_PolicyDetail extends Activity implements OnClickListener {
	private TextView policy_name_textView, policy_intro_textView, policy_time_textView, policy_content_textView, policy_amount_textView;
	private TextView policy_request_textView, policy_step_textView, policy_document_textView, policy_standard_textView, policy_target_textView;
	private LinearLayout policy_time_linear, policy_target_linear, policy_amount_linear, policy_content_linear, policy_request_linear;
	private LinearLayout policy_standard_linear, policy_document_linear, policy_step_linear;
	private Button close_btn;
	
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Cursor mCursor = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_policydeatil);
	    
	    Intent intent = getIntent();
	    String policy_name = intent.getExtras().getString("policy_name");

	    policy_name_textView = (TextView)findViewById(R.id.policyDetail_name_textView);
	    policy_intro_textView = (TextView)findViewById(R.id.policyDetail_intro_content_textView);
	    policy_time_textView = (TextView)findViewById(R.id.policyDetail_time_content_textView);
	    policy_target_textView = (TextView)findViewById(R.id.policyDetail_target_content_textView);
	    policy_content_textView = (TextView)findViewById(R.id.policyDetail_content_content_textView);
	    policy_amount_textView = (TextView)findViewById(R.id.policyDetail_amount_content_textView);
	    policy_request_textView = (TextView)findViewById(R.id.policyDetail_request_content_textView);
	    policy_step_textView = (TextView)findViewById(R.id.policyDetail_step_content_textView);
	    policy_document_textView = (TextView)findViewById(R.id.policyDetail_document_content_textView);
	    policy_standard_textView = (TextView)findViewById(R.id.policyDetail_standard_content_textView);

	    policy_target_linear = (LinearLayout)findViewById(R.id.policy_Detail_target_linear);
	    policy_time_linear = (LinearLayout)findViewById(R.id.policy_Detail_time_linear);
	    policy_amount_linear = (LinearLayout)findViewById(R.id.policy_Detail_amount_linear);
	    policy_content_linear = (LinearLayout)findViewById(R.id.policy_Detail_content_linear);
	    policy_request_linear = (LinearLayout)findViewById(R.id.policy_Detail_request_linear);
	    policy_standard_linear = (LinearLayout)findViewById(R.id.policy_Detail_standard_linear);
	    policy_document_linear = (LinearLayout)findViewById(R.id.policy_Detail_document_linear);
	    policy_step_linear = (LinearLayout)findViewById(R.id.policy_Detail_step_linear);
	    
	    close_btn = (Button)findViewById(R.id.policyDetail_close_btn);
	    
	    close_btn.setOnClickListener(this);
	    
	    setPolicyDetail(policy_name);
	}
	
	public void setPolicyDetail(String name) {
	    mDbOpenHelper = new SmartBebeDBOpenHelper(this);
	    mDbOpenHelper.open();
	   
	    mCursor = mDbOpenHelper.getMatchAttr(SmartBebeDataBase.CreateDB._TABLE_POLICY_CONTENT, SmartBebeDataBase.CreateDB.POLICY_NAME, name);

	    while( mCursor.moveToNext() ) {
			policy_name_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.POLICY_NAME))));
			policy_intro_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.POLICY_INTRO))));
			policy_time_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.POLICY_TIME))));
			policy_target_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.POLICY_TARGET))));
			policy_content_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.POLICY_CONTENT))));
			policy_amount_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.POLICY_AMOUNT))));
			policy_request_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.POLICY_REQUEST))));
			policy_step_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.POLICY_STEP))));
			policy_standard_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.POLICY_STANDARD))));
			policy_document_textView.setText(String.valueOf(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.POLICY_DOCUMENT))));
		}

	    if( policy_target_textView.getText().toString().equals("") )
	    	policy_target_linear.setVisibility(View.GONE);
	    if( policy_time_textView.getText().toString().equals("") )
	    	policy_time_linear.setVisibility(View.GONE);
	    if( policy_amount_textView.getText().toString().equals("") )
	    	policy_amount_linear.setVisibility(View.GONE);
	    if( policy_content_textView.getText().toString().equals("") )
	    	policy_content_linear.setVisibility(View.GONE);
	    if( policy_request_textView.getText().toString().equals("") )
	    	policy_request_linear.setVisibility(View.GONE);
	    if( policy_document_textView.getText().toString().equals("") )
	    	policy_document_linear.setVisibility(View.GONE);
	    if( policy_standard_textView.getText().toString().equals("") )
	    	policy_standard_linear.setVisibility(View.GONE);
	    if( policy_step_textView.getText().toString().equals("") )
	    	policy_step_linear.setVisibility(View.GONE);
	    
		mCursor.close();
		mDbOpenHelper.close();
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.policyDetail_close_btn :
			finish();
			break;
		}
	}
}
