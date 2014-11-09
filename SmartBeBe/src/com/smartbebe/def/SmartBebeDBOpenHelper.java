package com.smartbebe.def;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

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
			db.execSQL(SmartBebeDataBase.CreateDB._CREATE_POLICY_CONTENT);
			db.execSQL(SmartBebeDataBase.CreateDB._CREATE_VACCINE_CONTENT);
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
	
	public long insertPolicy(String group, String name, String intro, String time, String target, String content,
			String amount, String request, String step, String document, String standard) {
		ContentValues value = new ContentValues();
		value.put(SmartBebeDataBase.CreateDB.POLICY_GROUP, group);
		value.put(SmartBebeDataBase.CreateDB.POLICY_NAME, name);
		value.put(SmartBebeDataBase.CreateDB.POLICY_INTRO, intro);
		value.put(SmartBebeDataBase.CreateDB.POLICY_TIME, time);
		value.put(SmartBebeDataBase.CreateDB.POLICY_TARGET, target);
		value.put(SmartBebeDataBase.CreateDB.POLICY_CONTENT, content);
		value.put(SmartBebeDataBase.CreateDB.POLICY_AMOUNT, amount);
		value.put(SmartBebeDataBase.CreateDB.POLICY_REQUEST, request);
		value.put(SmartBebeDataBase.CreateDB.POLICY_STEP, step);
		value.put(SmartBebeDataBase.CreateDB.POLICY_DOCUMENT, document);
		value.put(SmartBebeDataBase.CreateDB.POLICY_STANDARD, standard);
		
		return mDB.insert(SmartBebeDataBase.CreateDB._TABLE_POLICY_CONTENT, null, value);
	}
	
	public long insertVaccine(String disease, String name, String time, String content, String channel, String symptom,
			String cure, String prevention, String month_time) {
		ContentValues value = new ContentValues();
		value.put(SmartBebeDataBase.CreateDB.VACCINE_DISEASE, disease);
		value.put(SmartBebeDataBase.CreateDB.VACCINE_NAME, name);
		value.put(SmartBebeDataBase.CreateDB.VACCINE_TIME, time);
		value.put(SmartBebeDataBase.CreateDB.VACCINE_CONTENT, content);
		value.put(SmartBebeDataBase.CreateDB.VACCINE_CHANNEL, channel);
		value.put(SmartBebeDataBase.CreateDB.VACCINE_SYMPTOM, symptom);
		value.put(SmartBebeDataBase.CreateDB.VACCINE_CURE, cure);
		value.put(SmartBebeDataBase.CreateDB.VACCINE_PREVENTION, prevention);
		value.put(SmartBebeDataBase.CreateDB.VACCINE_MONTH_TIME, month_time);
		
		return mDB.insert(SmartBebeDataBase.CreateDB._TABLE_VACCINE_CONTENT, null, value);
	}

	public Cursor getMatchNameAndParentCode(String table, String name, String code) {
		return mDB.rawQuery("select * from " + table + " where name=" + "'" + name + "'"
				+ " and " + "parent_code=" + "'" + code + "'", null);
	}

	public Cursor getMatchPolicyGroup(String table, String group) {
		return mDB.rawQuery("select * from " + table + " where " + SmartBebeDataBase.CreateDB.POLICY_GROUP + "=" + "'" + group + "'", null);
	}
	
	public Cursor getMatchAttr(String table, String attr, String name) {
		return mDB.rawQuery("select * from " + table + " where " + attr + "=" + "'" + name + "'", null);
	}
	
	public Cursor getAllColumns(String table) {
		return mDB.query(table, null, null, null, null, null, null);
	}

	public Cursor getDiaryContentList(String createDiaryContent) {
		return null;
	}
}
