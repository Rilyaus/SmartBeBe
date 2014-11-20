package com.smartbebe.def;

import android.provider.BaseColumns;

public class SmartBebeDataBase {
	public static final class CreateDB implements BaseColumns {
		public static final String _TABLE_BABY_INFO = "baby_table";
		public static final String _TABLE_DIARY_CONTENT = "diary_table";
		public static final String _TABLE_VACCINE_CONTENT = "vaccine_table";
		public static final String _TABLE_POLICY_CONTENT = "policy_table";
		public static final String _TABLE_CULTURE_CONTENT = "culture_table";
		public static final String _TABLE_HEIGHT_CONTENT = "height_table";
		public static final String _TABLE_WEIGHT_CONTENT = "weight_table";
		
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

		public static final String POLICY_ID = "policy_id";
		public static final String POLICY_GROUP = "policy_group";
		public static final String POLICY_NAME = "policy_name";
		public static final String POLICY_INTRO = "policy_intro";
		public static final String POLICY_TIME = "policy_time";
		public static final String POLICY_TARGET = "policy_target";
		public static final String POLICY_CONTENT = "policy_content";
		public static final String POLICY_AMOUNT = "policy_amount";
		public static final String POLICY_REQUEST = "policy_request";
		public static final String POLICY_STEP = "policy_step";
		public static final String POLICY_DOCUMENT = "policy_document";
		public static final String POLICY_STANDARD = "policy_standard";

		public static final String VACCINE_ID = "vaccine_id";
		public static final String VACCINE_DISEASE = "vaccine_disease";
		public static final String VACCINE_NAME = "vaccine_name";
		public static final String VACCINE_TIME = "vaccine_time";
		public static final String VACCINE_CONTENT = "vaccine_content";
		public static final String VACCINE_CHANNEL = "vaccine_channel";
		public static final String VACCINE_SYMPTOM = "vaccine_symptom";
		public static final String VACCINE_CURE = "vaccine_cure";
		public static final String VACCINE_PREVENTION = "vaccine_prevention";
		public static final String VACCINE_MONTH_TIME = "vaccine_month_time";

		public static final String HEIGHT_ID = "height_id";
		public static final String HEIGHT_TIME = "height_time";
		public static final String HEIGHT_VALUE = "height_value";

		public static final String WEIGHT_ID = "weight_id";
		public static final String WEIGHT_TIME = "weight_time";
		public static final String WEIGHT_VALUE = "weight_value";

		public static final String CULTURE_ID = "culture_id";
		public static final String CULTURE_NAME = "culture_name";
		public static final String CULTURE_LINK = "culture_link";

		public static final String _CREATE_CULTURE_CONTENT = 
				"create table " + _TABLE_CULTURE_CONTENT + "(" + CULTURE_ID + " integer primary key, "
						+ CULTURE_NAME + " text not null, "
						+ CULTURE_LINK + " text not null);";
		
		public static final String _CREATE_WEIGHT_CONTENT = 
				"create table " + _TABLE_WEIGHT_CONTENT + "(" + WEIGHT_ID + " integer primary key, "
						+ BABY_ID + " integer not null, "
						+ WEIGHT_TIME + " text not null, "
						+ WEIGHT_VALUE + " real not null);";
		
		public static final String _CREATE_HEIGHT_CONTENT = 
				"create table " + _TABLE_HEIGHT_CONTENT + "(" + HEIGHT_ID + " integer primary key, "
						+ BABY_ID + " integer not null, "
						+ HEIGHT_TIME + " text not null, "
						+ HEIGHT_VALUE + " real not null);";
		
		public static final String _CREATE_VACCINE_CONTENT = 
				"create table " + _TABLE_VACCINE_CONTENT + "(" + VACCINE_ID + " integer primary key, "
						+ VACCINE_DISEASE + " text not null, "
						+ VACCINE_NAME + " text not null, "
						+ VACCINE_TIME + " text, "
						+ VACCINE_CONTENT + " text, "
						+ VACCINE_CHANNEL + " text, "
						+ VACCINE_SYMPTOM + " text, "
						+ VACCINE_CURE + " text, "
						+ VACCINE_PREVENTION + " text, "
						+ VACCINE_MONTH_TIME + " text);";
		
		public static final String _CREATE_BABY_INFO =
				"create table " + _TABLE_BABY_INFO + "(" + BABY_ID + " integer primary key, "
						+ BABY_NAME + " text not null, "
						+ BABY_GENDER + " integer not null, "
						+ BABY_BIRTHDAY + " text not null, "
						+ BABY_HEIGHT + " real not null, "
						+ BABY_WEIGHT + " real not null);";

		public static final String _CREATE_DIARY_CONTENT = 
				"create table " + _TABLE_DIARY_CONTENT + "(" + DIARY_ID + " integer primary key, "
						+ BABY_ID + " integer not null, "
						+ DIARY_TITLE + " text not null, "
						+ DIARY_TIME + " text not null, "
						+ DIARY_CONTENT + " text not null, "
						+ DIARY_LOCATION + " text, "
						+ DIARY_VACCINE + " text, "
						+ DIARY_HEIGHT + " real, "
						+ DIARY_WEIGHT + " real);";
		
		public static final String _CREATE_POLICY_CONTENT = 
				"create table " + _TABLE_POLICY_CONTENT + "(" + POLICY_ID + " integer primary key, "
						+ POLICY_GROUP + " text not null, "
						+ POLICY_NAME + " text not null, "
						+ POLICY_INTRO + " text not null, "
						+ POLICY_TIME + " text, "
						+ POLICY_TARGET + " text, "
						+ POLICY_CONTENT + " text, "
						+ POLICY_AMOUNT + " text, "
						+ POLICY_REQUEST + " text, "
						+ POLICY_STEP + " text, "
						+ POLICY_DOCUMENT + " text, "
						+ POLICY_STANDARD + " text);";
	}
}
