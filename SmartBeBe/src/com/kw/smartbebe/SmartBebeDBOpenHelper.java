package com.kw.smartbebe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SmartBebeDBOpenHelper {
	public static final String DATABASE_NAME = "porter.db";
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
		//	db.execSQL(PorterDataBase.CreateDB._CREATE_LARGE_CATEGORY);
		//	db.execSQL(PorterDataBase.CreateDB._CREATE_MIDDLE_CATEGORY);
		//	db.execSQL(PorterDataBase.CreateDB._CREATE_BRAND);
		//	db.execSQL(PorterDataBase.CreateDB._CREATE_CAR);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//	db.execSQL("DROP TABLE IF EXISTS " + PorterDataBase.CreateDB._TABLE_LARGE_CATEGORY);
		//	onCreate(db);
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
/*
	public long insertColumn(String table, String name, String code) {
		ContentValues value = new ContentValues();
		value.put(PorterDataBase.CreateDB.CODE, code);
		value.put(PorterDataBase.CreateDB.NAME, name);
		
		return mDB.insert(table, null, value);
	}
	
	public long insertColumn(String table, String name, String code, String parent_code) {
		ContentValues value = new ContentValues();
		value.put(PorterDataBase.CreateDB.CODE, code);
		value.put(PorterDataBase.CreateDB.NAME, name);
		value.put(PorterDataBase.CreateDB.PARENT_CODE, parent_code);
		
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
		return mDB.rawQuery("select * from " + table + " where name=" + "'" + name + "'", null);
	}d
	
	public Cursor getAllColumns(String table) {
		return mDB.query(table, null, null, null, null, null, null);
	}*/

	public Cursor getDiaryContentList(String createDiaryContent) {
		// TODO Auto-generated method stub
		return null;
	}
}
