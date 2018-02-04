package com.touchinghand.common;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

	public String fromBool(boolean b) {
		return b ? "Yes" : "No";
	}
	
	public boolean toBool(String s) {
		return s == null ? false : (s.equalsIgnoreCase("true") 
									|| s.equalsIgnoreCase("Yes")
									|| s.equalsIgnoreCase("Y")) ? true : false;
	}
	
}
