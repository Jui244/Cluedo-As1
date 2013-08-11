package piece;

import java.util.HashSet;

import game.Player;
/**
 * So each room can store a number of players and weapons,
 * A room has a name, a set of weapons and, a set of players
 * Some rooms may have a passage to another room, the corner pieces;
 * Later on I'll set the co-or of the walls and the entrance way, not sure
 * if it's worth having a contains method for this, just sort of assuming
 * that if they reach the 2 block entrance way they can move anywhere
 * in the room.
 * @author Potato
 *
 */
public class Room extends GameObject{
	
	private Room hiddenPassage;
	private String name;
	
	public Room(String name){
		this.name = name;
	}

	public Room getPassage(){
		return hiddenPassage;
	}
	public void setPassage(Room r){
		hiddenPassage = r;
	}
	public String toString(){
		return name;
	}

	@Override
	public boolean compare(String s) {
		if(s.equalsIgnoreCase(name))
			return true;
		return false;
	}
}