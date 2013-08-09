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
	private int playerNumber;

	private BoardTile piece;
	
	private ArrayList<GameObject> hand;
	
	public Player(GameCharacter c, int playerNumber) {
		this.c = c;
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
}
