package com.smartbebe.def;

import com.smartbebe.def.SmartBebeDataBase.CreateDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class SmartBebeDBOpenHelper {
	public static final String DATABASE_NAME = "smartbebe.db";
	private static final int DATABASE_VERSION = 1;
	public static SQLiteDatabase mDB;
	private DatabaseHelper mDBHelper;
	private Context mContext;
	
	private class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context, String name,	CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SmartBebeDataBase.CreateDB._CREATE_BABY_INFO);
			db.execSQL(SmartBebeDataBase.CreateDB._CREATE_DIARY_CONTENT);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + SmartBebeDataBase.CreateDB._CREATE_BABY_INFO);
			db.execSQL("DROP TABLE IF EXISTS " + SmartBebeDataBase.CreateDB._CREATE_DIARY_CONTENT);
			onCreate(db);
		}
	}
	
	public SmartBebeDBOpenHelper(Context context) {
		this.mContext = context;
	}
	
	public SmartBebeDBOpenHelper open() throws SQLException {
		mDBHelper = new DatabaseHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
		mDB = mDBHelper.getWritableDatabase();
		
		return this;
	}
	
	public void close() {
		mDB.close();
	}

	public long insertBabyInfo(String table, String name, int gender, String day, int height, int weight) {
		ContentValues value = new ContentValues();
	//	value.put(SmartBebeDataBase.CreateDB.BABY_ID, id);
		value.put(SmartBebeDataBase.CreateDB.BABY_NAME, name);
		value.put(SmartBebeDataBase.CreateDB.BABY_GENDER, gender);
		value.put(SmartBebeDataBase.CreateDB.BABY_BIRTHDAY, day);
		value.put(SmartBebeDataBase.CreateDB.BABY_HEIGHT, height);
		value.put(SmartBebeDataBase.CreateDB.BABY_WEIGHT, weight);
		
		return mDB.insert(table, null, value);
	}
	
	public long insertDiary(String table, int baby, String title, String time, String content,
			String location, String vaccine, float height, float weight) {
		ContentValues value = new ContentValues();
		value.put(SmartBebeDataBase.CreateDB.BABY_ID, baby);
		value.put(SmartBebeDataBase.CreateDB.DIARY_TITLE, title);
		value.put(SmartBebeDataBase.CreateDB.DIARY_TIME, time);
		value.put(SmartBebeDataBase.CreateDB.DIARY_CONTENT, content);
		value.put(SmartBebeDataBase.CreateDB.DIARY_LOCATION, location);
		value.put(SmartBebeDataBase.CreateDB.DIARY_VACCINE, vaccine);
		value.put(SmartBebeDataBase.CreateDB.DIARY_HEIGHT, height);
		value.put(SmartBebeDataBase.CreateDB.DIARY_WEIGHT, weight);
		
		return mDB.insert(table, null, value);
	}

	public Cursor getMatchNameAndParentCode(String table, String name, String code) {
		return mDB.rawQuery("select * from " + table + " where name=" + "'" + name + "'"
				+ " and " + "parent_code=" + "'" + code + "'", null);
	}
	
	public Cursor getMatchParentCode(String table, String code) {
		return mDB.rawQuery("select * from " + table + " where parent_code=" + "'" + code + "'", null);
	}
	
	public Cursor getMatchName(String table, String name) {
		return mDB.rawQuery("select * from " + table + " where baby_name=" + "'" + name + "'", null);
	}
	
	public Cursor getAllColumns(String table) {
		return mDB.query(table, null, null, null, null, null, null);
	}

	public Cursor getDiaryContentList(String createDiaryContent) {
		return null;
	}
}
