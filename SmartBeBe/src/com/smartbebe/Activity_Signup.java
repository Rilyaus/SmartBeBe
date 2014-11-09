package com.smartbebe;

import java.io.File;

import com.kw.smartbebe.R;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Telephony.Mms.Part;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.*;

public class Activity_Signup extends Activity implements OnClickListener {
	private EditText name, height, weight;
	private EditText year, month, day;
	private Button signup_btn, add_btn;
	private ImageButton picture;
	private RadioButton male_radio, female_radio;
	
	final int TAKE_CAMERA = 1001;
	
	private int gender;
	private String[] photo_menu = {"사진 촬영", "앨범에서 선택"};
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Cursor mCursor;
	private int flag;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_signup);
	    
	    mDbOpenHelper = new SmartBebeDBOpenHelper(this);
	    
	    name = (EditText)findViewById(R.id.baby_name_EditText);
	    height = (EditText)findViewById(R.id.baby_height_EditText);
	    weight = (EditText)findViewById(R.id.baby_weight_EditText);

	    year = (EditText)findViewById(R.id.baby_year_EditText);
	    month = (EditText)findViewById(R.id.baby_month_EditText);
	    day = (EditText)findViewById(R.id.baby_day_EditText);
	    
	    picture = (ImageButton)findViewById(R.id.baby_picture_btn);

	    signup_btn = (Button)findViewById(R.id.baby_signup_btn);
	    add_btn = (Button)findViewById(R.id.baby_add_btn);

	    male_radio = (RadioButton)findViewById(R.id.baby_male_Radio);
	    female_radio = (RadioButton)findViewById(R.id.baby_female_Radio);
	    
	    signup_btn.setOnClickListener(this);
	    add_btn.setOnClickListener(this);
	    male_radio.setOnClickListener(this);
	    female_radio.setOnClickListener(this);
	    picture.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.baby_signup_btn :
			String birthday = String.format("%04d%02d%02d",
					Integer.parseInt(String.valueOf(year.getText())),
					Integer.parseInt(String.valueOf(month.getText())),
					Integer.parseInt(String.valueOf(day.getText())));
			
			mDbOpenHelper.open();
			mDbOpenHelper.insertBabyInfo(SmartBebeDataBase.CreateDB._TABLE_BABY_INFO, String.valueOf(name.getText()), gender, birthday, 
					Integer.parseInt(String.valueOf(height.getText())), Integer.parseInt(String.valueOf(weight.getText())));
			
			startActivity(new Intent(Activity_Signup.this, Activity_Main.class));
			finish();
			
			break;
		case R.id.baby_add_btn :
			
			break;
		case R.id.baby_male_Radio :
			gender = 0;
			break;
		case R.id.baby_female_Radio :
			gender = 1;
			break;
	/*	case R.id.baby_picture_btn :
			new AlertDialog.Builder(Activity_Signup.this)
			.setTitle("카메라")
			.setItems(photo_menu, cameraStart) // photo_menu를 띄우고 선택시 onChoice로
			.setCancelable(false) // back 버튼 못쓰게.
			.setNegativeButton("닫기", null)
			.show();
			break;*/
		}
	}
	
/*	android.content.DialogInterface.OnClickListener  cameraStart = new  DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch(which) {
			case 0 :
				flag = 1 ;
				Intent it = new Intent( ) ;
				 
				// 사진 저장 경로를 바꾸기
				File file = new File( Environment.getExternalStorageDirectory( ), 저장될곳+ "/" + "저장될이름" ) ;
				String tempPictuePath = file.getAbsolutePath( ) ;
				it.putExtra( MediaStore.EXTRA_OUTPUT, tempPictuePath ) ;
				it.setAction( MediaStore.ACTION_IMAGE_CAPTURE ) ; // 모든 단말에서 안될 수 있기 때문에 수정해야 함
				 
				startActivityForResult( it, TAKE_CAMERA ) ;
			}
		}
	};*/

	protected void onDestroy() {
		mDbOpenHelper.close();
		super.onDestroy();
	}
}
