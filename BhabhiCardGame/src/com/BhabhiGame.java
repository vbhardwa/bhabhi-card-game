package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

public class BhabhiGame {
	private DeckOfCards deck                    = null;
	private LinkedList<Player> players          = new LinkedList<Player>();
	private ArrayList<Card> pile                = new ArrayList<Card>();
	
	public ArrayList<Card> getPile() {
		return pile;
	}

	public void setPile(ArrayList<Card> pile) {
		this.pile = pile;
	}

	private HashMap<Player, Card> currentPile   = new HashMap<Player, Card>();
	
	public LinkedList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(LinkedList<Player> players) {
		this.players = players;
	}

	public BhabhiGame() {
		super();
	}

	public DeckOfCards getDeck() {
		return deck;
	}

	public void setDeck(DeckOfCards deck) {
		this.deck = deck;
	}
	
	// deal the cards among the players
	public void dealCards () {	

		int numOfPlayers = players.size();
		int count = 0;
		Card[] cards = deck.getDeck();
		for (int i=0; i<cards.length; i++) {
				// Go through all the cards, for each player deal 1 card at a time
				(players.get(count % numOfPlayers).getCards()).add(cards[i]); 
				count++;
		}
	} 
	
	public void initTurnSeq() {
		ListIterator<Player> it = players.listIterator();
		LinkedList<Player> temp = new LinkedList<Player>();

		first: 
		while(it.hasNext()) {
			Player p = it.next();
			
			for (Card c: p.getCards()) {
				if (c.getValue() == CardValue.ACE && c.getSuite() == SuiteType.SPADES) {
					break first;
				}
			}
			
			temp.add(p);
			it.remove();
		}
		
		it = temp.listIterator();
		while(it.hasNext()) {
			Player p = it.next();
			players.add(p);
			it.remove();
		}
	}
	
	public boolean thochoo (Card card) {
		for (Player p : currentPile.keySet()) {
			Card c = currentPile.get(p);
			if (card.getSuite() != c.suite) {
				return true;
			}
		}
		return false;
	}
	
	public HashMap<Player, Card> resetCurrentPile() {
		this.currentPile = null;
		this.currentPile = new HashMap<Player, Card>();
		return this.currentPile;
	}
	
	public Player highestCardPlayer () {
		int max = 0;
		Player highestPlayer = null;
		
		for (Player p : currentPile.keySet()) {
			Card c = currentPile.get(p);
			if (c.getValue().toNumeric() > max) {
				max = c.getValue().toNumeric();
				highestPlayer = p;
			}
		}
		return highestPlayer;
	}
	
	public void updateTurnSequence(Player startPlayer) {
		ListIterator<Player> it = players.listIterator();
		LinkedList<Player> temp = new LinkedList<Player>();

		while(it.hasNext()) {
			Player p = it.next();
			if (p.equals(startPlayer)) {
					break;
			}
			
			temp.add(p);
			it.remove();
		}
		
		it = temp.listIterator();
		while(it.hasNext()) {
			Player p = it.next();
			players.add(p);
			it.remove();
		}
	}
	
	public boolean isgameOver() {
		int totalPlayers = players.size();
		int finishedPlayer = 0;
		
		for (Player p : players) {
			if (p.isPlayerOut()) {
				finishedPlayer++;
			}
		}
		
		if ( (totalPlayers - finishedPlayer) > 1) {
			return false;
		} else {
			return true;
		}
	}
	
}
