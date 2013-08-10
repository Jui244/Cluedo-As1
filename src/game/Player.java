package game;

import java.util.ArrayList;


import piece.BoardTile;
import piece.GameCharacter;
import piece.GameObject;

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
	
	private ArrayList<GameObject> hand;
	
	public Player(GameCharacter c, int playerNumber) {
		this.c = c;
		this.playerNumber = playerNumber;
		piece = new BoardTile(String.valueOf(playerNumber), null);
		hand = new ArrayList<GameObject>();
	}
	public String getCharacter() {
		return c.getName();
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
		prev = b;
	}
	public BoardTile getPrevPos(){
		return prev;
	}
	
}
