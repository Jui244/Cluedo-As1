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
	private HashSet<Weapon> contains;
	private HashSet<Player> players;
	private String name;
	
	public Room(String name){
		this.players = new HashSet<Player>();
		contains = new HashSet<Weapon>();
		this.name = name;
	}

	public HashSet<Player> getPlayers(){
		return players;
	}
	public boolean removePlayer(Player p){
		return players.remove(p);
	}
	public boolean containsPlayer(Player p){
		return players.contains(p);
	}
	public void addWeapon(Weapon w){
		contains.add(w);
	}
	public boolean removeWeapon(Weapon w){
		return contains.remove(w);
	}
	public boolean containsWeapon(Weapon w){
		return contains.contains(w);
	}
	public Room getPassage(){
		return hiddenPassage;
	}
	public void setPassage(Room r){
		hiddenPassage = r;
	}

	public String print(){
		return name;
	}
}
