package com;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

/* NOTES
 * Now that I am storing client socket in players, I MIGHT not need to pass clients to the functions
 * I can just pass game, which has the players object from which I can get the respective client
 * sockets
 * 
 * playGame can entire by part of Bhabhi class
 */

class GameServer {
	private final int totalPlayers  = 3;
    private final int portNumber    = 6789;
   
    ServerSocket serverSocket                   = null;
    private ArrayList<Socket> clients           = null;
	private BhabhiGame game                     = null;
	
	public GameServer() throws IOException {
    	System.out.println("Game Server started");
    	this.serverSocket = new ServerSocket(portNumber);
    	this.clients      = new ArrayList<Socket>();
    	this.game         = new BhabhiGame();
	}

	public static void main(String argv[]) throws IOException, InterruptedException {
		GameServer gs = new GameServer();
		gs.addPlayers();
		gs.dealCards();
		gs.notifyPlayers();
		gs.playGame();
		gs.finishGame();        
    }
	
	public void playGame() throws IOException {
		game.initTurnSeq();
				
		while(!game.isgameOver()) {
			HashMap<Player, Card> currentPile = game.resetCurrentPile();
			boolean thochoo = false;
			for (Player p : game.getPlayers()) {
				if (p.isPlayerOut()) {
					continue;
				}
				Card c = p.playCard();
				p.removeCard(c);
				if (game.thochoo(c)) {
					thochoo = true;
					break;
				} else {
					currentPile.put(p, c);
				}
			}
			
			Player highestPlayer = game.highestCardPlayer();
			if (thochoo) {
				highestPlayer.getCards().addAll(currentPile.values());
			} else {
				game.getPile().addAll(currentPile.values());	
			}
			game.updateTurnSequence(highestPlayer);
			notifyPlayers();
		}
	}
		
	
	public void finishGame() throws IOException {
    	// Tell each client game over TODO create new thread, and do this in parallel 
    	for (Socket client : clients) {
    		DataOutputStream outToClient = new DataOutputStream(client.getOutputStream());
			outToClient.writeBytes("GAME_OVER\n");
    	}
        System.out.println("Game Over, shutting down server now");
	}
	
	public void notifyPlayers() throws IOException {
    	for (Player p : game.getPlayers()) {
    		DataOutputStream outToClient = new DataOutputStream(p.getSocket().getOutputStream());
			outToClient.writeBytes("CARDS: ");
    		for (Card c : p.getCards()) {
    			outToClient.writeBytes(c.toString() + " ");
    		}
			outToClient.writeBytes("\n");
    	}
	}
	
	public void addPlayers() throws InterruptedException, IOException {
    	ArrayList<RequestThread> requests   = new ArrayList<RequestThread>();
    	
    	while (this.clients.size() < totalPlayers) {
        	Socket client = serverSocket.accept();
        	clients.add(client);
        	RequestThread rt = new RequestThread(client, game);
            requests.add(rt);
            rt.start();
        }
    	
    	// wait for all the threads to finish
    	for(RequestThread rt : requests) {
    		rt.join();
    	}	
	}
		
	public void dealCards() {
    	DeckOfCards deck = new DeckOfCards();
		deck.shuffle();
		game.setDeck(deck);
		game.dealCards();
		
		
		/******** RIG the dealed cards to give the 3rd player Ace of Spades ****/
		/* only for testing */
		/*
		int index = 0;
		int index2 = 0;
		first: for (int i=0; i<game.getPlayers().size(); i++) {
			Player p = game.getPlayers().get(i);
			System.out.println(" i is: " + i + " player is: " + p.getName());

			for (int j=0; j<p.getCards().size(); j++) {
				Card c = p.getCards().get(j);
				if (c.getValue() == CardValue.ACE && c.getSuite() == SuiteType.SPADES) {
					index = i;
					index2 = j;
					break first;
				}
			}
		}
		
		LinkedList<Player> list = game.getPlayers();
		Card temp = list.get(2).getCards().get(0);
		Card ace  = list.get(index).getCards().get(index2);
		
		list.get(2).getCards().set(0, ace);
		list.get(index).getCards().set(index2, temp);
		*/
	}
	
}
