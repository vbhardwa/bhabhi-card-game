package com;

public class Card {
	SuiteType suite;
	CardValue value;
		
	public void setValue(CardValue value) {
		this.value = value;
	}

	public Card() {
		super(); 
	}

	public SuiteType getSuite() {
		return suite;
	}

	public void setSuite(SuiteType suite) {
		this.suite = suite;
	}

	public CardValue getValue() {
		return value;
	}
	
	public void printCard () {
		System.out.println("Suite\t" + suite + "\tValue\t" + value);
	}
	
	public String toString () {
		String str = value.toString() + "-" + suite.toString();
		return str;
	}
	
	public static Card toCard (String str) {
		int i = str.indexOf("-");
		String cardValue = str.substring(0,i);
		String cardSuite = str.substring(i+1);
		
		Card card = new Card();
		card.setValue(CardValue.toCardValue(cardValue));
		card.setSuite(SuiteType.toSuiteType(cardSuite));
		
		return card;
	}
	
	public boolean equals (Card otherCard) {
		if (this.suite == otherCard.getSuite() && this.value == otherCard.getValue() ){
			return true;
		} else {
			return false;
		}
	}
}
