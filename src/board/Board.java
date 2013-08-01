package board;

import java.util.HashSet;

import game.Player;

public class Board {
	private HashSet<Player> players;
	private String[][] board;
	private String w = "%";
	private String r = "r";
	private String h = " ";
	private String s = "s";
	
	public Board(HashSet<Player> players){
		String[][] board = {
				{w,w,w,w,w,w,w,h,h,h,r,r,r,h,h,h,w,w,w,w,w,w,w},
				
		};
	}

	public HashSet<Player> getPlayers() {
		return players;
	}
}
