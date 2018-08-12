package com.touchinghand.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateResolver {

	private static String pattern= "yyyy-MM-dd";
	
	public DateResolver(){};
	
	public DateResolver(String p){
		pattern = p;
	}
	
	public String toStringDate(LocalDate lDate) {
		return lDate == null ? "" : lDate.toString();
	}
	
	public LocalDate toLocalDate(String sDate){
		return sDate == null ? null : LocalDate.parse(sDate, DateTimeFormatter.ofPattern(pattern));
	}
	
	public String toStringDate(LocalDateTime ldt) {
		return ldt == null ? "" : ldt.toLocalDate().toString();
	}

	
}
