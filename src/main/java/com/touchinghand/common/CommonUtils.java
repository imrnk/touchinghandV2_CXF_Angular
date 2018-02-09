package com.touchinghand.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

	private static final Map<Integer, ReferenceDataType> intToTypeMap = new HashMap<Integer, ReferenceDataType>();
	
	static {
	    for (ReferenceDataType type : ReferenceDataType.values()) {
	        intToTypeMap.put(type.value, type);
	    }
	}
	
	
	public String fromBool(boolean b) {
		return b ? "Yes" : "No";
	}
	
	public boolean toBool(String s) {
		return s == null ? false : (s.equalsIgnoreCase("true") 
									|| s.equalsIgnoreCase("Yes")
									|| s.equalsIgnoreCase("Y")) ? true : false;
	}
	
	public String fullName(String f, String l) {
		if(f != null && l != null) return f + " " + l;
		if(f != null && l == null) return f;
		if(f == null && l != null) return l;
		return "";
	}
	
	public boolean changed(Object before, Object after) {
		
		if(after != null && (before == null || before instanceof String) && after instanceof String) {
			return StringUtils.isBlank((String)after) ? false : !StringUtils.equals((String)before, (String)after);
		}
		
		if(after != null && (before == null ||before instanceof Integer) && after instanceof Integer) {
			return before != after;
		}
		
		return false;
	}
	

	public ReferenceDataType fromInt(int i) {
		ReferenceDataType type = intToTypeMap.get(Integer.valueOf(i));
	    return type;
	}
	
}
