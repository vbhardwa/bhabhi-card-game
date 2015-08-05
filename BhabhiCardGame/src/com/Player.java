package com;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Card> cards;
	private Socket socket;
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Player() {
		cards = new ArrayList<Card>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public Card playCard() throws IOException {
		// Send command to play a card
		DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
		String greeting = "Which Card would you like to play ? Give in the following format 'A-D' for Ace of Diamonds";
		outToClient.writeBytes(greeting + "\n");
	
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String cardStr = inFromClient.readLine();
		Card card = Card.toCard(cardStr);

		for (Card c: cards) {
			if (c.equals(card)) {
				return c;
			}
		}
		return null;
	}
	
	public boolean equals (Player p) {
		if (this.name.equals(p.getName())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removeCard (Card card) {
		return cards.remove(card);
	}
	
	public boolean isPlayerOut() {
		if (cards.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
