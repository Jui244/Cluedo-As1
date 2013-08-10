package piece;

/**
 * Probably going to need to add a few more things in here as time
 * go's on.
 * @author Potato
 *
 */
public class Weapon extends GameObject{
	private String name;

	
	public Weapon(String name){
		super();
		this.name = name;

	}
	public String print() {
		return name;
	}
}
