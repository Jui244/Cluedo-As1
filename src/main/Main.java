package main;

import game.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import board.Board;

public class Main {
	
	private static int players;
	private static Player[] p;
	
	public static void main(String[] args) {
		System.out.println("Welcome to Cluedo!");
		System.out.println("Please enter the number of players, it must be between 2 and 4.");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			players = Integer.parseInt(br.readLine());
			p = new Player[players];
			for(int i = 1; i < players + 1; ++i){
				System.out.println("Player: " + i + " please enter your name");
				p[i] = new Player(br.readLine());
			}
		} catch (Exception e) {}
		
		Board b = new Board(p);
		
	}

}
