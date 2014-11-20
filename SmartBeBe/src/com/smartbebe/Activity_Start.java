package com.smartbebe;

import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;
import com.smartbebe.def.SmartBebePreference;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Activity_Start extends Activity {
	private SmartBebePreference pref;
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Cursor mCursor;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_start);

	    pref = new SmartBebePreference(this);
	    
	    mDbOpenHelper = new SmartBebeDBOpenHelper(this);
	    mDbOpenHelper.open();
	    
	    mCursor = mDbOpenHelper.getAllColumns(SmartBebeDataBase.CreateDB._TABLE_BABY_INFO);
	    
	    if( !pref.getPref(SmartBebePreference.DOCUMENT_LOAD_STRING, false) )
	    	setSmartBeBeData();
	    else {
			Handler handler = new Handler() {
				public void handleMessage(Message msg) {
					if( mCursor.getCount() == 0 ) {
						startActivity(new Intent(Activity_Start.this, Activity_Signup.class));
						finish();
					}
					else {
						startActivity(new Intent(Activity_Start.this, Activity_Main.class));
						finish();
					}
				}
			};
			handler.sendEmptyMessageDelayed(0, 1300);
	    }
	}
	
	protected void onDestroy() {
		mDbOpenHelper.close();
		super.onDestroy();
	}

	public void setSmartBeBeData() {
		class createPolicyAsyncTask extends AsyncTask<String, Void, String> {
			@Override
			protected String doInBackground(String... params) {
				try {
					createPolicy();
					createVaccine();
					createCulture();
					
					return "sply";
				} catch (Exception e) {
					Log.d("sply", "Exception Category : " + e);
				}
				return null;
			}
			
			protected void onPostExecute(String result) {
				if( result.equals("sply") ) {
				    mDbOpenHelper.insertBabyInfo(SmartBebeDataBase.CreateDB._TABLE_BABY_INFO, "추사랑", 1, "20111024", 93, 25);
					//	startActivity(new Intent(Activity_Start.this, Activity_Signup.class));
						startActivity(new Intent(Activity_Start.this, Activity_Main.class));
					finish();
				}
			}
		}
		createPolicyAsyncTask createPolicy = new createPolicyAsyncTask();
		createPolicy.execute();
	}
	
	private void createVaccine() {
		Workbook workbook = null;
		Sheet[] sheet = null;
		
		try {
			InputStream is = getBaseContext().getResources().getAssets().open("vaccine.xls");
			workbook = Workbook.getWorkbook(is);	
			
			if( workbook != null ) {
				sheet = workbook.getSheets();
				
				for( int i=0 ; i<sheet.length ; i++ ) {
					int nRowEndIndex = sheet[i].getColumn(0).length - 1;
					
					if( sheet[i] != null ) {
						for( int nRow = 1 ; nRow <= nRowEndIndex ; nRow++ ) {
							mDbOpenHelper.insertVaccine(
									sheet[i].getCell(0, nRow).getContents(),
									sheet[i].getCell(1, nRow).getContents(),
									sheet[i].getCell(2, nRow).getContents(),
									sheet[i].getCell(3, nRow).getContents(),
									sheet[i].getCell(4, nRow).getContents(),
									sheet[i].getCell(5, nRow).getContents(),
									sheet[i].getCell(6, nRow).getContents(),
									sheet[i].getCell(7, nRow).getContents(),
									sheet[i].getCell(8, nRow).getContents());
						}
					}
				}
			}
		} catch( Exception e ) {
			Log.d("sply", String.valueOf(e));
		} finally {
			if( workbook != null )
				pref.putPref(SmartBebePreference.DOCUMENT_LOAD_STRING, true);
				workbook.close();
				
				mCursor = mDbOpenHelper.getAllColumns(SmartBebeDataBase.CreateDB._TABLE_VACCINE_CONTENT);
		}		
	}

	private void createPolicy() {
		Workbook workbook = null;
		Sheet[] sheet = null;
		
		try {
			InputStream is = getBaseContext().getResources().getAssets().open("policy.xls");
			workbook = Workbook.getWorkbook(is);	
			
			if( workbook != null ) {
				sheet = workbook.getSheets();
				
				for( int i=0 ; i<sheet.length ; i++ ) {
					int nRowEndIndex = sheet[i].getColumn(0).length - 1;
				//	Log.d("sply", String.valueOf(nRowEndIndex));
					if( sheet[i] != null ) {
						for( int nRow = 1 ; nRow <= nRowEndIndex ; nRow++ ) {
							mDbOpenHelper.insertPolicy(
									sheet[i].getCell(0, nRow).getContents(),
									sheet[i].getCell(1, nRow).getContents(),
									sheet[i].getCell(2, nRow).getContents(),
									sheet[i].getCell(3, nRow).getContents(),
									sheet[i].getCell(4, nRow).getContents(),
									sheet[i].getCell(5, nRow).getContents(),
									sheet[i].getCell(6, nRow).getContents(),
									sheet[i].getCell(7, nRow).getContents(),
									sheet[i].getCell(8, nRow).getContents(),
									sheet[i].getCell(9, nRow).getContents(),
									sheet[i].getCell(10, nRow).getContents());
						}
					}
				}
			}
		} catch( Exception e ) {
			Log.d("sply", String.valueOf(e));
		} finally {
			if( workbook != null )
				pref.putPref(SmartBebePreference.DOCUMENT_LOAD_STRING, true);
				workbook.close();
				
				mCursor = mDbOpenHelper.getAllColumns(SmartBebeDataBase.CreateDB._TABLE_POLICY_CONTENT);
			//	Log.d("sply", String.valueOf(mCursor.getCount()));
		}
	}
	
	private void createCulture() {
		Workbook workbook = null;
		Sheet[] sheet = null;
		
		try {
			InputStream is = getBaseContext().getResources().getAssets().open("culture.xls");
			workbook = Workbook.getWorkbook(is);	
			
			if( workbook != null ) {
				sheet = workbook.getSheets();
				
				for( int i=0 ; i<sheet.length ; i++ ) {
					int nRowEndIndex = sheet[i].getColumn(0).length - 1;
					
					if( sheet[i] != null ) {
						for( int nRow = 1 ; nRow <= nRowEndIndex ; nRow++ ) {
							mDbOpenHelper.insertCulture(
									sheet[i].getCell(0, nRow).getContents(),
									sheet[i].getCell(1, nRow).getContents());
						}
					}
				}
			}
		} catch( Exception e ) {
			Log.d("sply", String.valueOf(e));
		} finally {
			if( workbook != null )
				pref.putPref(SmartBebePreference.DOCUMENT_LOAD_STRING, true);
				workbook.close();
				
				mCursor = mDbOpenHelper.getAllColumns(SmartBebeDataBase.CreateDB._TABLE_CULTURE_CONTENT);
		}
	}
}
