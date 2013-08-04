package piece;

import board.Room;

public class Character extends GameObject{
	private String name;
	public Character(String name){
		this.name = name;
	}
	public Room getLocation() {
		return null;
	}

	public String getName() {
		return name;
	}

}
