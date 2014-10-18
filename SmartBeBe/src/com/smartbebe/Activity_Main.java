package com.smartbebe;

import java.util.ArrayList;
import java.util.Stack;

import com.kw.smartbebe.R;
import com.kw.smartbebe.R.id;
import com.smartbebe.def.SmartBebeDBOpenHelper;
import com.smartbebe.def.SmartBebeDataBase;
import com.smartbebe.def.SmartBebePreference;
import com.smartbebe.diary.Activity_DiaryWrite;
import com.smartbebe.diary.Tab_Diary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class Activity_Main extends FragmentActivity implements OnClickListener {
	private DrawerLayout dlDrawer;
	private LinearLayout navi_lineaLayout;
	private ListView navi_listview1, navi_listview2;
	private Button navi_toggle_btn, navi_write_btn, navi_bebe_btn;
	private ArrayList<Navigation_MenuList> naviMenuArr1, naviMenuArr2;
	private NaviMenuListAdapter nAdapter1, nAdapter2;
	private SmartBebeDBOpenHelper mDbOpenHelper;
	private Cursor mCursor;
	
	public static Stack<Fragment> homeStack = new Stack<Fragment>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dlDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		navi_toggle_btn = (Button)findViewById(R.id.navi_toggle_btn);
		navi_bebe_btn = (Button)findViewById(R.id.navi_bebe_btn);
		navi_write_btn = (Button)findViewById(R.id.navi_write_btn);
		navi_lineaLayout = (LinearLayout)findViewById(R.id.navi_linearlayout);
		navi_listview1 = (ListView)findViewById(R.id.navi_menulist1);
		navi_listview2 = (ListView)findViewById(R.id.navi_menulist2);

		naviMenuArr1 = new ArrayList<Navigation_MenuList>();
		naviMenuArr2 = new ArrayList<Navigation_MenuList>();
		
		Paint paint = new Paint();
		paint.setARGB(0, 214, 245, 255);;
		paint.setAlpha(240);
		((LinearLayout)findViewById(R.id.navi_linearlayout)).setBackgroundColor(paint.getColor());
		
		setHomeTab();
		setNaviMenu();

		navi_toggle_btn.setOnClickListener(this);
		navi_write_btn.setOnClickListener(this);
		navi_bebe_btn.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.navi_toggle_btn :
			dlDrawer.openDrawer(navi_lineaLayout);
			break;
		case R.id.navi_write_btn :
			startActivity(new Intent(Activity_Main.this, Activity_DiaryWrite.class));
			break;
		case R.id.navi_bebe_btn :
			homeStack.clear();

			Fragment fa = getSupportFragmentManager().findFragmentById(R.id.mainview_linear);
			
			if( !fa.getTag().equals("Home") ) 
				setHomeTab();
			break;
		}
	}
	
	public void setHomeTab() {
		Tab_Home homeFragment = new Tab_Home();
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

		ft.replace(R.id.mainview_linear, homeFragment, "Home");
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
		ft.commitAllowingStateLoss();
	}

	public void setNaviMenu() {
		naviMenuArr1.add(new Navigation_MenuList(getResources().getString(R.string.menu_diary), getResources().getDrawable(R.drawable.diary_128x128)));
		naviMenuArr1.add(new Navigation_MenuList(getResources().getString(R.string.menu_vaccine), getResources().getDrawable(R.drawable.vaccine_128x128)));
		naviMenuArr1.add(new Navigation_MenuList(getResources().getString(R.string.menu_babyfood), getResources().getDrawable(R.drawable.food_128x128)));
		naviMenuArr1.add(new Navigation_MenuList(getResources().getString(R.string.menu_growth), getResources().getDrawable(R.drawable.growth_128x128)));

		naviMenuArr2.add(new Navigation_MenuList(getResources().getString(R.string.menu_baby_add), getResources().getDrawable(R.drawable.mypage_128x128)));
		naviMenuArr2.add(new Navigation_MenuList(getResources().getString(R.string.menu_setting), getResources().getDrawable(R.drawable.setting_128x128)));

		nAdapter1 = new NaviMenuListAdapter(this, R.layout.navi_menulist_listitem, naviMenuArr1);
		nAdapter2 = new NaviMenuListAdapter(this, R.layout.navi_menulist_listitem, naviMenuArr2);

		navi_listview1.setAdapter(nAdapter1);
		navi_listview1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> av, View v, int position, long id) {
				dlDrawer.closeDrawer(navi_lineaLayout);
				Fragment temp = getSupportFragmentManager().findFragmentById(R.id.mainview_linear);
				FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
				
				switch((int)id) {
					case 0 : {
						Tab_Diary tDiaryFragment = new Tab_Diary();
						
						homeStack.push(temp);
						ft.replace(R.id.mainview_linear, tDiaryFragment, "HomeTab");
						break;
					}
				}
				ft.addToBackStack("HomeTab");
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
				ft.commitAllowingStateLoss();
			}
		});
	
		navi_listview2.setAdapter(nAdapter2);
		navi_listview2.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> av, View v, int position, long id) {
				dlDrawer.closeDrawer(navi_lineaLayout);
				
				if( (int) id == 0 ) {
					startActivity(new Intent(Activity_Main.this, Activity_Signup.class));
				}
			}
		});
	}
	
	public void onBackPressed() {
		Fragment fa = getSupportFragmentManager().findFragmentById(R.id.mainview_linear);
		
		try {
			if( fa.getTag().equals("HomeTab") && homeStack.size() > 0 ) {
				homeStack.pop();
				getSupportFragmentManager().popBackStack();
			}
			else {
				new AlertDialog.Builder(Activity_Main.this).setTitle("SmartBeBe")
				.setMessage("앱을 종료하시겠습니까?").setNegativeButton("아니오", null)
				.setPositiveButton("예", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						finish();
					}
				}).show();
			}
		} catch( Exception e ) {
			Log.d("sply", e.toString());
			super.onBackPressed();
		}
	}
}
