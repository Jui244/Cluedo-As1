package game;
/**
 * A player on the board, nuff said.
 * Going to make a separate 'Character' class.
 * @author Potato
 *
 */
public class Player {
	private String name;
	private int playerNumber;
	public Player(String name, int position) {
		this.name = name;
		playerNumber = position;
	}
	public String getName() {
		return name;
	}
	public boolean getTurn(int i){
		return i == playerNumber;
	}
	
}
