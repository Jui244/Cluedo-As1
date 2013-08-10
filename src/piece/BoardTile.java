package piece;


/**
 * So a board tile can either be a Room, hallway or entrance.
 * @author Potato
 *
 */
public class BoardTile extends GameObject{
	private Room room;
	private String s;
	
	public BoardTile(String s, Room r){
		this.s = s;
		this.room = r;
	}
	public Room getRoom(){
		return room;
	}
	public String print(){
		return s;
	}
}
