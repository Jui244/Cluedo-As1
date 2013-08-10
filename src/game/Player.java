package game;

import java.util.ArrayList;


import piece.BoardTile;
import piece.GameCharacter;
import piece.GameObject;
import piece.Room;

/**
 * A player on the board, nuff said.
 * Going to make a separate 'Character' class.
 * @author Potato
 *
 */
public class Player {
	private GameCharacter c;

	public int x;
	public int y;
	public final int playerNumber;
	private BoardTile prev;
	private BoardTile piece;
	private Room room;
	
	private ArrayList<GameObject> hand;
	
	public Player(GameCharacter c, int playerNumber) {
		this.c = c;
		this.playerNumber = playerNumber;
		piece = new BoardTile(String.valueOf(playerNumber), null);
		hand = new ArrayList<GameObject>();
		room = null;
	}
	public String getCharacter() {
		return c.print();
	}
	public boolean getTurn(int i){
		return i == playerNumber;
	}
	public BoardTile getPiece(){
		return piece;
	}	
	public ArrayList<GameObject> getHand(){
		return hand;
	}
	public void addToHand(GameObject o){
		hand.add(o);
	}
	public void setPrevPos(int x, int y, BoardTile b){
		this.x = x;
		this.y = y;
		prev = b;
	}
	public BoardTile getPrevPos(){
		return prev;
	}
	public boolean inRoom(){
		return room != null;
	}
	public Room getRoom(){
		return room;
	}
	public void setRoom(Room r){
		room = r;
	}
	public void printhand(){
		for (int i = 0; i < hand.size(); i ++){
			System.out.println(hand.get(i).print());
		}
	}
	
}
