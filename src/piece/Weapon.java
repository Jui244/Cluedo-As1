package piece;
import board.Room;
/**
 * Probably going to need to add a few more things in here as time
 * go's on.
 * @author Potato
 *
 */
public class Weapon extends GameObject{
	private String name;
	private Room location;
	
	public Weapon(String name, Room location){
		super();
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
