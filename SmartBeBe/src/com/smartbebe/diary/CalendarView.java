package com.smartbebe.diary;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
 
public class CalendarView extends CTCalendarView {
     
    private Oneday basisDay;
    private int during;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
        setTitle("TITLE");
        initialize();
         
        basisDay = new Oneday(this);
        
        Intent intent = getIntent();
        int[] b = intent.getIntArrayExtra("basisDay");
        during = intent.getIntExtra("during", 0);
        if(b != null){
            basisDay.setYear(b[0]);
            basisDay.setMonth(b[1]);
            basisDay.setDay(b[2]);
        } else {
            Calendar cal = Calendar.getInstance();
            basisDay.setYear(cal.get(Calendar.YEAR));
            basisDay.setMonth(cal.get(Calendar.MONTH));
            basisDay.setDay(cal.get(Calendar.DAY_OF_MONTH));
        }
    }
}