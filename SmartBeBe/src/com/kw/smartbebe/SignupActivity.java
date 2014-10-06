package com.kw.smartbebe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.*;

public class SignupActivity extends Activity {
	private ArrayAdapter<CharSequence>  adspin;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_signup);
	     
       /* Spinner spinner = (Spinner) findViewById(R.id.spinner);
        
        adspin = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);
 
        adspin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adspin);
    
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        
        adspin = ArrayAdapter.createFromResource(this, R.array.month, android.R.layout.simple_spinner_item);
 
        adspin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adspin);
        
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        adspin = ArrayAdapter.createFromResource(this, R.array.day, android.R.layout.simple_spinner_item);
 
        adspin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adspin);*/
        
	}

}
