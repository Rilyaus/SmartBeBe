package com.kw.smartbebe;

import android.provider.BaseColumns;

public class SmartBebeDataBase {
	public static final class CreateDB implements BaseColumns {
		public static final String BABY_ID = "baby_id";
		public static final String BABY_NAME = "baby_name";
		public static final String BABY_GENDER = "baby_gender";
		public static final String BABY_BIRTHDAY = "baby_birthday";
		public static final String BABY_HEIGHT = "baby_height";
		public static final String BABY_WEIGHT = "baby_weight";
		
		public static final String DIARY_CONTENT_ID = "diary_content_id";
		public static final String DIARY_CONTENT_TITLE = "diary_content_title";
		public static final String DIARY_CONTENT_TIME = "diary_content_time";
		public static final String DIARY_CONTENT_CONTENT = "diary_content_content";
		public static final String DIARY_CONTENT_LOCATION = "diary_content_location";
		public static final String DIARY_CONTENT_VACCINE = "diary_content_vaccine";
		public static final String DIARY_CONTENT_HEIGHT = "diary_content_height";
		public static final String DIARY_CONTENT_WEIGHT = "diary_content_weight";
		
		public static final String _TABLE_BABY_INFO = "baby_info";
		public static final String _TABLE_DIARY_CONTENT = "diary_content";
		public static final String _TABLE_VACCINE_CONTENT = "vaccine_content";
//id, title, time, content, location, vaccine, weight, height
		
		public static final String _CREATE_BABY_INFO =
				"create table " + _TABLE_BABY_INFO + "(" + BABY_ID + " text primary key, "
						+ BABY_NAME + " text not null, "
						+ BABY_GENDER + " boolean not null, "
						+ BABY_BIRTHDAY + "date not null, "
						+ BABY_HEIGHT + "float not null, "
						+ BABY_WEIGHT + "float not null );";
		
		public static final String _CREATE_DIARY_CONTENT = 
				"create table " + _TABLE_DIARY_CONTENT + "(" + DIARY_CONTENT_ID + " text primary key, "
						+ BABY_ID + " text not null, "
						+ DIARY_CONTENT_TITLE + " text not null, "
						+ DIARY_CONTENT_TIME + " date not null, "
						+ DIARY_CONTENT_CONTENT + " text not null, "
						+ DIARY_CONTENT_LOCATION + " text not null, "
						+ DIARY_CONTENT_VACCINE + " text not null, "
						+ DIARY_CONTENT_HEIGHT + " float not null, "
						+ DIARY_CONTENT_WEIGHT + " float not null );";
		
		public static final String _CREATE_VACCINE = 
				"create table " + _TABLE_VACCINE_CONTENT + "(" + DIARY_CONTENT_CONTENT + " text primary key, "
						+ DIARY_CONTENT_HEIGHT + " text not null, "
						+ DIARY_CONTENT_LOCATION + " text not null );";
	}
}
