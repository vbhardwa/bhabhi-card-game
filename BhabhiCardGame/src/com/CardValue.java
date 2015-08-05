package com;

public enum CardValue {
	ACE, TWO, THREE, FOUR,
	FIVE, SIX, SEVEN, EIGHT,
	NINE, TEN, JACK, QUEEN,
	KING;
		
	public String toString () {
		switch(this) {
			case ACE     : return "A";
			case TWO     : return "2";
			case THREE   : return "3";
			case FOUR    : return "4";
			case FIVE    : return "5";
			case SIX     : return "6";
			case SEVEN   : return "7";
			case EIGHT   : return "8";
			case NINE    : return "9";
			case TEN     : return "10";
			case JACK    : return "J";
			case QUEEN   : return "Q";
			case KING    : return "K";
			default : return null;
		}
	}
	
	public static CardValue toCardValue (String str) {
		switch(str) {
			case "A"     : return ACE;
			case "2"     : return TWO;
			case "3"     : return THREE;
			case "4"     : return FOUR;
			case "5"     : return FIVE;
			case "6"     : return SIX;
			case "7"     : return SEVEN;
			case "8"     : return EIGHT;
			case "9"     : return NINE;
			case "10"    : return TEN;
			case "J"     : return JACK;
			case "Q"     : return QUEEN;
			case "K"     : return KING;
			default : return null;
		}
	}
	
	public int toNumeric () {
		switch(this) {
			case ACE     : return 14;
			case TWO     : return 2;
			case THREE   : return 3;
			case FOUR    : return 4;
			case FIVE    : return 5;
			case SIX     : return 6;
			case SEVEN   : return 7;
			case EIGHT   : return 8;
			case NINE    : return 9;
			case TEN     : return 10;
			case JACK    : return 11;
			case QUEEN   : return 12;
			case KING    : return 13;
			default : return -1;
		}
	}
	
}


