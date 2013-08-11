package piece;

/**
 * Probably going to need to add a few more things in here as time
 * go's on.
 * @author Jui Deshpande
 *
 */
public class Weapon extends GameObject{
	private String name;
	public Room r;
	
	public Weapon(String name){
		super();
		this.name = name;

	}
	public boolean compare(String s){
		if(s.equalsIgnoreCase(name))
			return true;
		return false;
	}
	public String toString() {
		return name;
	}


}
