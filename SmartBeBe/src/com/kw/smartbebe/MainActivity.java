package com.kw.smartbebe;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private DrawerLayout dlDrawer;
	private LinearLayout navi_lineaLayout;
	private ListView navi_listview1, navi_listview2;
	private Button navi_toggle_btn;
	private ArrayList<Navigation_MenuList> naviMenuArr;
	private NaviMenuListAdapter nAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dlDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		navi_toggle_btn = (Button)findViewById(R.id.navi_toggle_btn);
		navi_lineaLayout = (LinearLayout)findViewById(R.id.navi_linearlayout);
		navi_listview1 = (ListView)findViewById(R.id.navi_menulist1);
		
		naviMenuArr = new ArrayList<Navigation_MenuList>();
		
		Paint paint = new Paint();
		paint.setARGB(0, 214, 245, 255);;
		paint.setAlpha(220);
		((LinearLayout)findViewById(R.id.navi_linearlayout)).setBackgroundColor(paint.getColor());
		
		setHomeTab();
		setNaviMenu();
		
		navi_toggle_btn.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.navi_toggle_btn :
			dlDrawer.openDrawer(navi_lineaLayout);
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
		naviMenuArr.add(new Navigation_MenuList(getResources().getString(R.string.menu_diary), getResources().getDrawable(R.drawable.diary_128x128)));
		naviMenuArr.add(new Navigation_MenuList(getResources().getString(R.string.menu_vaccine), getResources().getDrawable(R.drawable.vaccine_128x128)));
		naviMenuArr.add(new Navigation_MenuList(getResources().getString(R.string.menu_babyfood), getResources().getDrawable(R.drawable.food_128x128)));
		naviMenuArr.add(new Navigation_MenuList(getResources().getString(R.string.menu_growth), getResources().getDrawable(R.drawable.growth_128x128)));
		
		nAdapter = new NaviMenuListAdapter(this, R.layout.navi_menulist_listitem, naviMenuArr);
		
		navi_listview1.setAdapter(nAdapter);
		navi_listview1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int position, long id) {
				dlDrawer.closeDrawer(navi_lineaLayout);
				
			/*	Fragment temp = getSupportFragmentManager().findFragmentById(R.id.mainview_linear);
				
				Product_Tab_mCategoryList mCateFragment = new Product_Tab_mCategoryList();

				FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
				
				Bundle args = new Bundle();

				mCateFragment.setArguments(args);
				ft.replace(R.id.mainview_linear, mCateFragment, "HomeTab");
				ft.addToBackStack("HomeTab");
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
				ft.commitAllowingStateLoss();*/
			}
		});
	}
	
	public void onBackPressed() {
		new AlertDialog.Builder(MainActivity.this).setTitle("SmartBeBe")
		.setMessage("앱을 종료하시겠습니까?").setNegativeButton("아니오", null)
		.setPositiveButton("예", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				finish();
			}
		}).show();
	}
	
}
