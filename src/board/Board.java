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
	

	
	public Board(HashSet<Player> players){
		rooms = new HashSet<Room>(9);
		rooms.add(new Room("Spa", players.size(), null));
		
	}

	public HashSet<Player> getPlayers() {
		return players;
	}
}
