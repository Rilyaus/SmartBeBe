package com.smartbebe.def;

import android.provider.BaseColumns;

public class SmartBebeDataBase {
	public static final class CreateDB implements BaseColumns {
		public static final String BABY_ID = "baby_id";
		public static final String BABY_NAME = "baby_name";
		public static final String BABY_GENDER = "baby_gender";
		public static final String BABY_BIRTHDAY = "baby_birthday";
		public static final String BABY_HEIGHT = "baby_height";
		public static final String BABY_WEIGHT = "baby_weight";
 
		public static final String DIARY_ID = "diary_id";
		public static final String DIARY_BABY = "diary_baby";
		public static final String DIARY_TITLE = "diary_title";
		public static final String DIARY_TIME = "diary_time";
		public static final String DIARY_CONTENT = "diary_content";
		public static final String DIARY_LOCATION = "diary_location";
		public static final String DIARY_VACCINE = "diary_vaccine";
		public static final String DIARY_HEIGHT = "diary_height";
		public static final String DIARY_WEIGHT = "diary_weight";
		
		public static final String _TABLE_BABY_INFO = "baby_table";
		public static final String _TABLE_DIARY_CONTENT = "diary_table";
		public static final String _TABLE_VACCINE_CONTENT = "vaccine_table";
//id, title, time, content, location, vaccine, weight, height
		
		public static final String _CREATE_BABY_INFO =
				"create table " + _TABLE_BABY_INFO + "(" + BABY_ID + " integer primary key, "
						+ BABY_NAME + " text not null, "
						+ BABY_GENDER + " integer not null, "
						+ BABY_BIRTHDAY + " text not null, "
						+ BABY_HEIGHT + " real not null, "
						+ BABY_WEIGHT + " real not null);";
		
		public static final String _CREATE_DIARY_CONTENT = 
				"create table " + _TABLE_DIARY_CONTENT + "(" + DIARY_ID + " integer primary key, "
						+ BABY_ID + " text not null, "
						+ DIARY_TITLE + " text not null, "
						+ DIARY_TIME + " text not null, "
						+ DIARY_CONTENT + " text not null, "
						+ DIARY_LOCATION + " text, "
						+ DIARY_VACCINE + " text, "
						+ DIARY_HEIGHT + " real, "
						+ DIARY_WEIGHT + " real);";
	}
}
