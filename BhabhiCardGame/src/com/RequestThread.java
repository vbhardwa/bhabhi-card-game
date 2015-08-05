package com;

import java.net.*;
import java.util.LinkedList;
import java.io.*;


public class RequestThread extends Thread {
	private Socket socket = null;
	private BhabhiGame game = null;

    public RequestThread(Socket socket, BhabhiGame game) {
    	super("RequestThread");
    	this.socket = socket;
    	this.game = game;
    }
	    
    public void run() {
    	try {
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());

			String inputData = inFromClient.readLine();					
			if (inputData.contains("JOIN")) {
				// extract name
				int endOfName = inputData.indexOf(" ");
				String name = inputData.substring(0, endOfName);
				System.out.println(name + " joined the game");
				outToClient.writeBytes("Welcome " + name + ", you have successfully joined the game!\n");
				
				// Add new player to the game
				Player p = new Player();
				p.setName(name);
				p.setSocket(socket);
				LinkedList<Player> players = game.getPlayers();
				players.add(p);
				
			}
    	} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
