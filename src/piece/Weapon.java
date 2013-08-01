package piece;
import board.Room;

public class Weapon extends GameObject{
	private String name;
	private Room location;
	
	public Weapon(String name, Room location){
		this.name = name;
		this.location = location;
	}

	public Room getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}
}
