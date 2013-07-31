package piece;
import board.Room;

public class Weapon extends GameObject{
	private String name;
	private Room location;
	
	public Weapon(String name, Room location){
		
	}

	public Room getLocation() {
		return location;
	}

	public void setLocation(Room location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}
}
