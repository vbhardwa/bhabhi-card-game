package com;

import java.io.*;
import java.net.*;


class GameClient {
	public static void main(String argv[]) throws Exception {
		String name;
		System.out.println("Welcome to Bhabhi, please enter your name");
		BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
		name = inFromUser.readLine();
		
		String command = name + " wants to JOIN" + "\n";
		Socket socket = new Socket("localhost", 6789); // create socket to talk to server
		
		DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
		outToServer.writeBytes(command); // send command to server
		
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String receivedData = inFromServer.readLine();
		System.out.println(receivedData);
		
		// game loop
		while(true) {
			receivedData = inFromServer.readLine();

			if (receivedData.contains("GAME_OVER")) {
				break;
			}
			
			System.out.println(receivedData);
			
			if (!receivedData.contains("CARDS:")) {
				String userInput = inFromUser.readLine();
				outToServer.writeBytes(userInput + "\n");	
			}			
		} 
		System.out.println("Game Over! Thank You for playing.");
	}
}
