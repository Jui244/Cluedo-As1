package board;
/**
 * So a board tile can either be a Room, hallway or entrance.
 * @author Potato
 *
 */
public class BoardTile {
	private boolean entrance;
	private Room room;
	
	public BoardTile(Room r, boolean entrance){
		room = r;
		this.entrance = entrance;
	}
	public Room getRoom(){
		return room;
	}
	public void setEntrance(){
		entrance = true;
	}
	public boolean isEntrance(){
		return entrance;
	}
	public String print(){
		return room.getName().substring(0,1);
	}
}
