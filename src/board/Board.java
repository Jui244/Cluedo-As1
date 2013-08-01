package board;

import java.util.HashSet;

import piece.Weapon;

import game.Player;
/**
 * So well, this is going to where the gameloop is,
 * I will first initialize the board, each players hands, 
 * the 'killer' and, the players start positions (guess I 
 * I should probably make that random?)
 * 
 * Also this is where I'll have the 2D array for the board,
 * the only things I'll store on the board is if it's part of the 
 * hallway or an entrance way, if it's an entrance way I'll sort
 * out which room it's trying to get into... somehow.
 * @author Potato
 *
 */
public class Board {
	
	private HashSet<Player> players;
	private HashSet<Room> rooms;
	private HashSet<Weapon> weapons;
	private BoardTile[][] board;
	private boolean hasKillerBeenFound;
	

	public Board(HashSet<Player> players){
		rooms = new HashSet<Room>(9);
		
		rooms.add(new Room("Kitchen", players.size(), null));
		rooms.add(new Room("Dining Room", players.size(), null));
		rooms.add(new Room("Lounge", players.size(), null));
		rooms.add(new Room("Ball Room", players.size(), null));
		rooms.add(new Room("Conservatory", players.size(), null));
		rooms.add(new Room("Billiard Room", players.size(), null));
		rooms.add(new Room("Library", players.size(), null));
		rooms.add(new Room("Study", players.size(), null));
		rooms.add(new Room("Hall", players.size(), null));
		
		//add rooms to deck
		//add characters to deck
		//shuffle deck
		
		
		board = new BoardTile[25][25];
		
		hasKillerBeenFound = false;
	}

	public HashSet<Player> getPlayers() {
		return players;
	}
	
	public void gameLoop(){
		int turnSize = players.size();
		while(1 == 1){
			for(int i = 1; i < turnSize + 1; i++){
				
			}
		}
	}
}
