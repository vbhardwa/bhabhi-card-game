package com;

import java.util.Random;

public class DeckOfCards {
	private Card[] deck;
	private static final int NUM_OF_CARDS = 52;
	private static final int SHUFFLE_MAGIC = 250;
	
	public DeckOfCards() {
		deck = new Card[NUM_OF_CARDS];
		
		int count = 0;
		for (SuiteType s : SuiteType.values()) {
			for (CardValue v : CardValue.values()) {
				Card card = new Card();
				card.setSuite(s);
				card.setValue(v);
				deck[count] =  card;
				count++;
			}
		}
	}
	
	public void print() {
		for (int i=0; i<NUM_OF_CARDS; i++) {
			deck[i].printCard();
		}
	}

	public Card[] getDeck() {
		return deck;
	}

	public void setDeck(Card[] deck) {
		this.deck = deck;
	}

	public static int getNumOfCards() {
		return NUM_OF_CARDS;
	}
	
	// swap two random chosen cards, 50 times
	public void shuffle() {
		for (int i=0; i<SHUFFLE_MAGIC; i++) {
			Random rand = new Random();
			int randPos = rand.nextInt(NUM_OF_CARDS);
			int randPos2 = rand.nextInt(NUM_OF_CARDS);
			
			Card temp = deck[randPos];
			deck[randPos] = deck[randPos2];
			deck[randPos2] = temp;
		}
	}
}
