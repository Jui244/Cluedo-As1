package piece;


/**
 * So a board tile can either be a Room, hallway or entrance.
 * @author Jui Deshpande
 *
 */
public class BoardTile extends GameObject{
	private Room room;
	private String s;
	public final int cost;
	
	public BoardTile(String s, Room r, int cost){
		this.s = s;
		this.room = r;
		this.cost = cost;
	}
	public Room getRoom(){
		return room;
	}
	public String toString(){
		return s;
	}
	@Override
	public boolean compare(String s) {
		if(this.s.equalsIgnoreCase(s))
			return true;
		return false;
	}
}
