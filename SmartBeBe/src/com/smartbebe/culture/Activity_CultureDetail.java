package com.smartbebe.culture;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;

public class Activity_CultureDetail extends Activity {
	private WebView culture_web;
	private Button close_btn;
	private TextView culture_title;
	
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Cursor mCursor;
	private String culture_id;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_culture);
	    
	    Intent intent = getIntent();
	    culture_id = String.valueOf(intent.getLongExtra("culture_id", 0)+1);
	    culture_web = (WebView)findViewById(R.id.culture_webview);
	    close_btn = (Button)findViewById(R.id.cultureDetail_close_btn);
	    culture_title = (TextView)findViewById(R.id.cultureDetail_title_textView);
	    
	    mDbOpenHelper = new SmartBebeDBOpenHelper(this);
	    mDbOpenHelper.open();
	    
	    mCursor = mDbOpenHelper.getMatchAttr(SmartBebeDataBase.CreateDB._TABLE_CULTURE_CONTENT, SmartBebeDataBase.CreateDB.CULTURE_ID, culture_id);
	    mCursor.moveToNext();
	    
	    String culture_url = mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.CULTURE_LINK));
	    culture_title.setText(mCursor.getString(mCursor.getColumnIndex(SmartBebeDataBase.CreateDB.CULTURE_NAME)));
	    
	    culture_web.setWebViewClient(new WebViewClient());
	    WebSettings set = culture_web.getSettings();
	    set.setJavaScriptEnabled(true);
	    set.setSupportZoom(true);
	    set.setBuiltInZoomControls(true);
	    
	    culture_web.loadUrl(culture_url);
	    close_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

	protected void onDestroy() {
		mDbOpenHelper.close();
		super.onDestroy();
	}
}
