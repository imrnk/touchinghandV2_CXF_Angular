package com.touchinghand.common;

public enum ReferenceDataType {
	
	appearance(1), speech(2), eyecontact(3), motoractivity(4), 
	affect(5), mood(6), orientationimpairment(7), memoryimpairment(8),
	attention(9), hallucinations(10), perceptionsother(11), 
	suicidiality(12), homicidality(13), delusions(14), 
	behavior(15), insight(16), judgement(17), therapy(18),
	country(19), state(20);
	
	int value;
	
	ReferenceDataType(int id){
		value = id;
	}
	
	public int getValue() {
		return value;
	}
	
}