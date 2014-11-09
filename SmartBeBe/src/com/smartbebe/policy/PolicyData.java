package com.smartbebe.policy;

import java.util.ArrayList;

public class PolicyData {
	String category;
	ArrayList<String> policyList;
	
	public PolicyData(String cate, ArrayList<String> List) {
		category = cate;
		policyList = List;
	}
}
