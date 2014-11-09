package com.smartbebe.vaccine;

import java.util.ArrayList;

public class VaccineData {
	String personal;
	ArrayList<String> VaccineList;
	
	public VaccineData(String option, ArrayList<String> List) {
		personal = option;
		VaccineList = List;
	}
}
