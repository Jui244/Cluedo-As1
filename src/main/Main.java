package main;

import game.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

import board.Board;
/**
 * This is pretty much all I actually want this to do, as the
 * board should really handle everything else.
 * If we decide to got for the A then I'll add in a few more options for
 * the different game types.
 * @author Potato
 *
 */
public class Main {
	
	private static int players;
	private static HashSet<Player> p;
	
	public static void main(String[] args) {
		System.out.println("Welcome to Cluedo!");
		System.out.println("Please enter the number of players, it must be between 2 and 4.");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/*
		try {
			players = Integer.parseInt(br.readLine());
			p = new HashSet<Player>(players);
			for(int i = 1; i < players + 1; ++i){
				System.out.println("Player: " + i + " please enter your name");
				p.add(new Player(br.readLine(), i));
			}
		} catch (Exception e) {}
		*/
		@SuppressWarnings("unused")
		Board b = new Board(p);
		
	}

}
