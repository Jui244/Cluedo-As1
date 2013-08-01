package board;

import java.util.HashSet;

import game.Player;
import piece.Weapon;

public class Room {
	private Room hiddenPassage;
	private boolean hasPassage;
	private HashSet<Weapon> contains;
	private HashSet<Player> players;
	
	public Room(int players, HashSet<Weapon> startingWeapons){
		this.players = new HashSet<Player>(players);
		contains = startingWeapons;
		hasPassage = false;
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
	public void setPassage(Room r){
		hiddenPassage = r;
		hasPassage = true;
	}
	public boolean hasPassage(){
		return hasPassage;
	}
	public Room getPassage(){
		return hiddenPassage;
	}
}
