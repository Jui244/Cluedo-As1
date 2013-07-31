package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import board.Board;

public class Main {
	private static int players;
	
	public static void main(String[] args) {
		System.out.println("Cluedo");
		System.out.println("Please enter the number of players");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			players = Integer.parseInt(br.readLine());
		} catch (Exception e) {
			
		}
		System.out.println(players);
		Board b = new Board();
	}

}
