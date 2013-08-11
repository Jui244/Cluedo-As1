package piece;

/**
 * Class for a game character, the x and y are the starting position for the character and the name
 * is mainly used when making comparisons
 * @author Scott
 *
 */
public class GameCharacter extends GameObject{
	private String name;
	public final int x;
	public final int y;
	public GameCharacter(String name, int x, int y){
		this.x = x;
		this.y = y;
		this.name = name;
	}
	public String toString() {
		return name;
	}
	public boolean compare(String s){
		if(s.equalsIgnoreCase(name))
			return true;
		return false;
	}

}
