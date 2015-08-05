package com;

public enum SuiteType {
	CLUBS, DIAMONDS, HEARTS, SPADES;
	
	public String toString () {
		switch(this) {
			case CLUBS     : return "C";
			case DIAMONDS  : return "D";
			case HEARTS    : return "H";
			case SPADES    : return "S";
			default : return null;
		}
	}
	
	public static SuiteType toSuiteType (String str) {
		switch(str) {
			case "C"     : return CLUBS;
			case "D"     : return DIAMONDS;
			case "H"     : return HEARTS;
			case "S"     : return SPADES;
			default : return null;
		}
	}
}