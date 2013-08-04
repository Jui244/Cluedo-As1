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
 * the only things I'll store on the board is if it'study part of the 
 * hallway or an entrance way, if it'study an entrance way I'll sort
 * out which room it'study trying to get into... somehow.
 * @author Potato
 *
 */
public class Board {
	
	private HashSet<Player> players;
	private HashSet<Room> rooms;
	private HashSet<Weapon> weapons;
	private BoardTile[][] board;
	private boolean hasKillerBeenFound;
	private BoardTile w; //for a wall on the board/unreachable place
	private BoardTile hw; //a hallway on the board
	private BoardTile st;
	

	public Board(HashSet<Player> players){
		rooms = new HashSet<Room>(9);
		
		//Only place a player can move on is the 'hallway' or an 'entrance'
		Room kitchen = new Room("Kitchen", players.size(), null);
		Room diningRoom = new Room("Kitchen", players.size(), null);
		Room lounge = new Room("Lounge", players.size(), null);
		Room ballRoom = new Room("Ball Room", players.size(), null);
		Room conservatory = new Room("Conservatory", players.size(), null);
		Room billiardRoom = new Room("Billiard Room", players.size(), null);
		Room library = new Room("Library", players.size(), null);
		Room study = new Room("Study", players.size(), null);
		Room hall = new Room("Hall", players.size(), null);
		
		w = new BoardTile(null, false);
		hw = new BoardTile(null, false);
		st = new BoardTile(null, false);
		
		rooms.add(kitchen);
		rooms.add(diningRoom);
		rooms.add(lounge);
		rooms.add(ballRoom);
		rooms.add(conservatory);
		rooms.add(billiardRoom);
		rooms.add(library);
		rooms.add(study);
		rooms.add(hall);
		
		BoardTile k = kitchen.getTile();
		BoardTile kE = kitchen.getEntrance();
		
		BoardTile dr = diningRoom.getTile();
		BoardTile drE = diningRoom.getEntrance();

		BoardTile l = lounge.getTile();
		BoardTile lE = lounge.getEntrance();
		
		BoardTile br = ballRoom.getTile();
		BoardTile brE = ballRoom.getEntrance();
		
		BoardTile c = conservatory.getTile();
		BoardTile cE = conservatory.getEntrance();
		
		BoardTile b = billiardRoom.getTile();
		BoardTile bE = billiardRoom.getEntrance();
		
		BoardTile li = library.getTile();
		BoardTile liE = library.getEntrance();
		
		BoardTile s = study.getTile();
		BoardTile sE = study.getEntrance();
		
		BoardTile h = hall.getTile();
		BoardTile hE = hall.getEntrance();
		
		
		BoardTile[][] board = {{ w, w, w, w, w, w, w, w, w,st, w, w, w,st, w, w, w, w, w, w, w, w, w},
							   { k, k, k, k, k, k, w,hw,hw,hw,br,br,br,hw,hw,hw, w, c, c, c, c, c, c},
							   { k, k, k, k, k, k,hw,hw,br,br,br,br,br,br,br,hw,hw, c, c, c, c, c, c},
							   { k, k, k, k, k, k,hw,hw,br,br,br,br,br,br,br,hw,hw, c, c, c, c, c, c},
							   { k, k, k, k, k,k,hw,hw,brE,br,br,br,br,br,brE,hw,hw,hw,c, c, c, c, w},
							   { w, k, k, k,kE, k,hw,hw,br,br,br,br,br,br,br,hw,hw,hw,hw,hw,hw,hw,st},
							   {hw,hw,hw,hw,hw,hw,hw,hw,br,brE,br,br,br,br,brE,br,hw,hw,hw,hw,hw,hw,hw,w},
							   { w,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw, b, b, b, b, b, b},
							   {dr,dr,dr,dr,dr,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,bE, b, b, b, b, b},
							   {dr,dr,dr,dr,dr,dr,dr,dr,hw,hw, w, w, w, w,hw,hw,hw, b, b, b, b, b, b},
							   {dr,dr,dr,dr,dr,dr,dr,dr,hw,hw, w, w, w, w,hw,hw,hw, b, b, b, b, b, b},
							   {dr,dr,dr,dr,dr,dr,dr,drE,hw,hw,w, w, w, w,hw,hw,hw, b, b, b, b,bE, b},
							   {dr,dr,dr,dr,dr,dr,dr,dr,hw,hw, w, w, w, w,hw,hw,hw,hw,hw,hw,hw,hw,hw},
							   {dr,dr,dr,dr,dr,dr,dr,dr,hw,hw, w, w, w, w,hw,hw,hw,li,li,liE,li,li,w},
							   {dr,dr,dr,dr,dr,dr,drE,dr,hw,hw,w, w, w, w,hw,hw,li,li,li,li,li,li,li},
							   { w,hw,hw,hw,hw,hw,hw,hw,hw,hw, w, w, w, w,hw,hw,liE,li,li,li,li,li,li},
							   {st,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,li,li,li,li,li,li,li},
							   { w,hw,hw,hw,hw,hw,hw,hw,hw, h, h,hE,hE, h, h,hw,hw,hw,li,li,li,li,li},
							   { l, l, l, l, l, l,lE,hw,hw, h, h, h, h, h, h,hw,hw,hw,hw,hw,hw,hw,st},
							   { l, l, l, l, l, l, l,hw,hw, h, h, h, h, h,hE,hw,hw,hw,hw,hw,hw,hw, w},
							   { l, l ,l, l, l, l, l,hw,hw, h, h, h, h, h, h,hw,hw,sE, s, s, s, s, s},
							   { l, l ,l, l, l, l, l,hw,hw, h, h, h, h, h, h,hw,hw, s, s, s, s, s, s},
							   { l, l ,l, l, l, l, l,hw,hw, h, h, h, h, h, h,hw,hw, s, s, s, s, s, s},
							   { l, l, l, l, l, l, w,st, w, h, h, h, h, h, h, w,hw, w, s, s, s, s, s}};
		
		
		//add rooms to deck
		//add characters to deck
		//shuffle deck
		
		

		
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
