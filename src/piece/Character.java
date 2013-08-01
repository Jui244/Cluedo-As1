package piece;

import board.Room;

public class Character implements GameObject{
	private String name;
	public Character(String name){
		this.name = name;
	}
	@Override
	public Room getLocation() {
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

}
