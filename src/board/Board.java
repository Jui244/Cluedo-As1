package board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import piece.BoardTile;
import piece.GameObject;
import piece.Room;
import piece.Weapon;
import piece.GameCharacter;

import game.Player;
/**
 * This is the class where the bulk of the game is handled
 * first a board is constructed and everything is intialized.
 * from there the constructor calls the gameloop which plays out the game
 * @author Scott Allen
 *
 */

public class Board {
	private JFrame frame;
	private JFrame init;
	private ArrayList<Player> players;
	private ArrayList<Room> rooms;
	private ArrayList<Weapon> weapons;
	private ArrayList<GameCharacter> characters;
	private ArrayList<GameObject> deck;
	private Crime crime;
	private BoardTile[][] board;
	private boolean killerFound = false;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public Board() throws IOException{
		init = new JFrame();
		JOptionPane.showConfirmDialog(
			    frame,
			    "We should set up a game using one of these :D",
			    "Is that ok?",
			    JOptionPane.YES_NO_OPTION);
		

		
		rooms = new ArrayList<Room>(9);
		weapons = new ArrayList<Weapon>(9);
		characters = new ArrayList<GameCharacter>(6);
		deck = new ArrayList<GameObject>();
		players = new ArrayList<Player>(4);

		GameCharacter mScar = new GameCharacter("Miss Scarlett", 17, 27);
		GameCharacter cMust = new GameCharacter("Colonel Mustard", 7, 27);
		GameCharacter mWhit = new GameCharacter("Mrs White", 1, 21);
		GameCharacter rGree = new GameCharacter("Reverent Green", 1, 10);
		GameCharacter mPeac = new GameCharacter("Mrs Peacock", 6, 1);
		GameCharacter pPlum = new GameCharacter("Professor Plum", 20, 1);

		characters.add(mScar);
		characters.add(cMust);
		characters.add(mWhit);
		characters.add(rGree);
		characters.add(mPeac);
		characters.add(pPlum);

		Collections.shuffle(characters);
		for(int i = 1; i < characters.size(); i++)
			deck.add(characters.get(i));

		init();

		for(Player p: players){
			System.out.println(p.getCharacter());
		}

		Weapon knife = new Weapon("Knife");
		Weapon candleStick = new Weapon("Candle Stick");
		Weapon pistol = new Weapon("Pistol");
		Weapon poisen = new Weapon("Poisen");
		Weapon trophy = new Weapon("Trophy");
		Weapon rope = new Weapon("Rope");
		Weapon bat = new Weapon("Bat");
		Weapon ax = new Weapon("Ax");
		Weapon dumbbell = new Weapon("Dumbbell");

		weapons.add(knife);
		weapons.add(candleStick);
		weapons.add(pistol);
		weapons.add(poisen);
		weapons.add(trophy);
		weapons.add(rope);
		weapons.add(bat);
		weapons.add(ax);
		weapons.add(dumbbell);

		Collections.shuffle(weapons);
		for(int i = 1; i < weapons.size(); i++)
			deck.add(weapons.get(i));

		String[] ka = {"5,23"};
		Room kitchen = new Room("Kitchen", ka);
		String[] da = {"11,20", "14,22"};
		Room diningRoom = new Room("Dining Room",da);
		String[] ga = {"19,21"};
		Room guestHouse = new Room("Guest House",ga);
		String[] pa = {"7,14", "7,15", "7,16"};
		Room patio = new Room("Patio", pa);
		String[] sa = {"5,5"};
		Room spa = new Room("Spa",sa);
		String[] ta = {"10,7"};
		Room theater = new Room("Theater", ta);
		String[] la = {"16,9"};
		Room livingRoom = new Room("Living Room", la);
		String[] oa = {"22,9"};
		Room observatory = new Room("Observatory",oa);
		Room hall = new Room("Hall", null);
		String[] ca = {"14,12", "9,17", "17,17"};
		Room clue = new Room("Clue", ca);
		//intalizes all the rooms and the doors that are inside said room.
		
		observatory.setPassage(kitchen);
		kitchen.setPassage(observatory);
		guestHouse.setPassage(spa);
		spa.setPassage(guestHouse);
		//connects the rooms that have passageways. 
		rooms.add(kitchen);
		rooms.add(diningRoom);
		rooms.add(guestHouse);
		rooms.add(patio);
		rooms.add(spa);
		rooms.add(theater);
		rooms.add(livingRoom);
		rooms.add(observatory);
		rooms.add(hall);

		Collections.shuffle(rooms);
		for(int i = 1; i < rooms.size(); i++)
			deck.add(rooms.get(i));


		for(int i = 0; i < rooms.size(); i++){
			weapons.get(i).r = rooms.get(i);
		}

		Collections.shuffle(deck);
		
		int count = 0;

		for(int i = 0; i < deck.size();i++){
			if(count == players.size())
				count = 0;
			players.get(count).addToHand(deck.get(i));
			count++;
		}

		crime = new Crime(weapons.get(0), rooms.get(0), characters.get(0));

		deck.add(weapons.get(0));
		deck.add(characters.get(0));
		deck.add(rooms.get(0));

		BoardTile k = new BoardTile("K", kitchen,0);
		BoardTile kr = new BoardTile(" ", kitchen,0);
		BoardTile d = new BoardTile("D", diningRoom,0);
		BoardTile dr = new BoardTile(" ", diningRoom,0);
		BoardTile g = new BoardTile("G", guestHouse,0);
		BoardTile gr = new BoardTile(" ", guestHouse,0);
		BoardTile p = new BoardTile("P", patio,0);
		BoardTile pr = new BoardTile(" ", patio,0);
		BoardTile s = new BoardTile("S", spa,0);
		BoardTile sr = new BoardTile(" ", spa,0);
		BoardTile t = new BoardTile("T", theater,0);
		BoardTile tr = new BoardTile(" ", theater,0);
		BoardTile l = new BoardTile("L", livingRoom,0);
		BoardTile lr = new BoardTile(" ", livingRoom,0);
		BoardTile o = new BoardTile("O", observatory,0);
		BoardTile or = new BoardTile(" ", observatory,0);
		BoardTile c = new BoardTile("C", clue,0);
		BoardTile cr = new BoardTile(" ", clue,0);

		BoardTile h = new BoardTile("H", hall,0);
		BoardTile hr = new BoardTile(" ", hall,0);

		BoardTile w = new BoardTile("W", null,10000); 
		BoardTile hw = new BoardTile(" ", null,1); 
		//Creates the board
		BoardTile[][] initBoard = {
				{ w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w},
				{ w,sr,sr,sr,sr, w,hw,hw, w,tr,tr,tr, w,hw, w,lr,lr,lr,lr, w,hw,hw, w,or, w},
				{ w,sr,sr,sr,sr, w,hw,hw, w,tr,tr,tr, w,hw, w,lr,lr,lr,lr, w,hw,hw, w,or, w},
				{ w,sr,sr,sr,sr, w,hw,hw, w,tr,tr,tr, w,hw, w,lr,lr,lr,lr, w,hw,hw, w,or, w},
				{ w,sr,sr,sr,sr, w,hw,hw, w,tr,tr,tr, w,hw, w,lr,lr,lr,lr, w,hw,hw, w,or, w},
				{ w,sr,sr,sr,sr, s,hw,hw, w,tr,tr,tr, w,hw, w,lr,lr,lr,lr, w,hw,hw, w,or, w},
				{ w,sr,sr,sr, w,hw,hw,hw, w,tr,tr,tr, w,hw, w,lr,lr,lr,lr, w,hw,hw, w,or, w},
				{ w, w, w, w, w,hw,hw,hw, w, w, t, w, w,hw, w,lr,lr,lr,lr, w,hw,hw, w,or, w},
				{ w,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw, w, w,lr, w, w, w,hw,hw, w,or, w},
				{ w,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw, w, l, w,hw,hw,hw,hw, o, w, w},
				{ w,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw, w},
				{ w, w, w, w, w,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw, w},
				{ w,pr,pr,pr, w, w, w, w,hw, w, w, w, w, w, c, w, w, w,hw, w, w, w, h, w, w},
				{ w,pr,pr,pr,pr,pr,pr, w,hw, w,cr,cr,cr,cr,cr,cr,cr, w,hw, w,hr,hr,hr,hr, w},
				{ w,pr,pr,pr,pr,pr,pr, p,hw, w,cr,cr,cr,cr,cr,cr,cr, w,hw, h,hr,hr,hr,hr, w},
				{ w,pr,pr,pr,pr,pr,pr, p,hw, w,cr,cr,cr,cr,cr,cr,cr, w,hw, h,hr,hr,hr,hr, w},
				{ w,pr,pr,pr,pr,pr,pr, p,hw, w,cr,cr,cr,cr,cr,cr,cr, w,hw, w,hr,hr,hr,hr, w},
				{ w,pr,pr,pr,pr,pr,pr, w,hw, c, w, w, w, w, w, w, w, c,hw, w,hr,hr,hr,hr, w},
				{ w,pr,pr, w, w, w, w, w,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw, w, w, w, w, w, w},
				{ w, w, w, w,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw, w},
				{ w,hw,hw,hw,hw,hw,hw,hw,hw, w, w, d, w, w, w,hw,hw,hw,hw,hw,hw,hw,hw,hw, w},
				{ w,hw,hw,hw,hw,hw,hw,hw,hw, w,dr,dr,dr,dr, w,hw,hw,hw,hw, g, w, w, w, w, w},
				{ w, w, w, w, w,hw,hw,hw,hw, w,dr,dr,dr,dr, d,hw,hw,hw,hw, w,gr,gr,gr,gr, w},
				{ w,kr,kr,kr,kr, k,hw,hw,hw, w,dr,dr,dr,dr, w,hw,hw,hw,hw, w,gr,gr,gr,gr, w},
				{ w,kr,kr,kr,kr, w,hw,hw, w, w,dr,dr,dr,dr, w, w,hw,hw,hw, w,gr,gr,gr,gr, w},
				{ w,kr,kr,kr,kr, w,hw,hw, w,dr,dr,dr,dr,dr,dr, w,hw,hw,hw, w,gr,gr,gr,gr, w},
				{ w,kr,kr,kr,kr, w,hw,hw, w,dr,dr,dr,dr,dr,dr, w,hw,hw,hw, w,gr,gr,gr,gr, w},
				{ w,kr,kr,kr,kr, w,hw,hw, w,dr,dr,dr,dr,dr,dr, w,hw,hw,hw, w,gr,gr,gr,gr, w},
				{ w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w}

		};
		this.board = initBoard;
		count = 0;
		//places each player in the correct position on the board.
		for(Player pl:players){
			int x = pl.getCharacter().x;
			int y = pl.getCharacter().y;
			pl.setPrevPos(x, y, board[y][x]);
			board[y][x] = pl.getPiece();
		}
		frame = new BoardFrame(board);
		printBoard();
		gameLoop();
	}


	public void gameLoop() throws IOException{
		while(!killerFound){
			for(int i = 0; i < players.size();i++){
				Player temp = players.get(i);
				String[] read = null;
				temp.printhand();
				System.out.println("Player: " + temp.playerNumber + " turn.");
				System.out.println("Would you like to make your final accusation? y/n");

				String ans = br.readLine();

				if(ans.equalsIgnoreCase("y")){
					while(read == null || read.length!=3){
						System.out.println("Please enter your accusation. Weapon,Room,Character");
						ans = br.readLine();
						read = ans.split(",");
					}
					if(makeAccusation(read)){
						System.out.println("Player: " + players.get(i).playerNumber + " wins the game!");
						return;
					} else {

						players.remove(i);
						if(players.size() == 1){
							System.out.println("Player: " + players.get(0).playerNumber + " wins the game!");
							return;
						}
						i--;
						break;
					}
				}
				else{
					ans = null;
					if(temp.getPrevPos().getRoom()!=null)//if the player is now inside a room, allow them to make an accusation
					System.out.println(temp.getPrevPos().getRoom().toString());
					if(temp.getPrevPos().getRoom()!=null && temp.getPrevPos().getRoom().getPassage()!=null){
						System.out.println("Would you like to move to: " + temp.getPrevPos().getRoom().getPassage().toString() + ". y/n");
						ans = br.readLine();
						if(ans.equalsIgnoreCase("y")){
							Room r = temp.getPrevPos().getRoom().getPassage();
							String[] newPos = r.doors[0].split(",");
							switchPos(Integer.parseInt(newPos[0]), Integer.parseInt(newPos[1]), temp);
							printBoard();
							GameObject o = makeAttempt(ans, read, i);
							if(o!=null){
								System.out.println("Dispproved: " + o.toString());
							}else{
								System.out.println("Player: " + temp.playerNumber + " wins!");
								return;
							}
						}
					}
					else if(temp.getPrevPos().getRoom()!=null || ans != null && ans.equalsIgnoreCase("n")){
							String[] td = temp.getPrevPos().getRoom().doors;//case for if there are multiple doors.
							System.out.println("Which door would you like to exit from?");
							temp.getPrevPos().getRoom().printDoors();

							String door = br.readLine();

							for(int j = 0; j < td.length; j++){
								if(door.equalsIgnoreCase(td[j])){
									door = td[j];
									break;
								}	
							}
							String[] newPos = door.split(",");
							switchPos(Integer.parseInt(newPos[0]), Integer.parseInt(newPos[1]), temp);
							ans = null;
					}
					if(ans == null){
						int roll = 1 + (int)(Math.random() * ((6 - 1) + 1));
						System.out.println("You rolled a " + roll + "!");
						System.out.println("What co-or would you like to move to?");
						System.out.println("Hint: Enter how many units you would like to move horizontal by 'x=*' and how many verticle by 'y=*', you can have multiple" +
								"\nof these if you wish to have a more complex move.\nExample input x=2,y=3 to move east 2 and south 3 or y=-2,x=3 to move north 2 and east 3.");
						if(temp.getRoom()!= null){

						}
						int rollCond = 0;
						int x = 0;
						int y = 0;
						BoardTile canMove = temp.getPiece();
						read = null;
						//need to rewrite this to handle movement in rooms
						while(read == null || read.length <= 1){
							ans = br.readLine();
							read = ans.split(",");
						}
						while(rollCond!=roll && canMove != null){//how movement is handled, should re ask for input if the player trys to walk into a wall.
							for(int j = 0; j < read.length; j++){//need to check if the length is equal to the roll or if they enter a room, also if there is a wall in the path;
								if(canMove == null){
									read = null;
									//canMove = temp.getPiece();
									while(read == null || read.length <= 1){
										ans = br.readLine();
										read = ans.split(",");
									}
									rollCond = 0;
									x = 0;
									y = 0;
									j = 0;
								}
								String[] readC = read[j].split("=");//I guess this loop is just making sure the player enters a valid move... feel like it could be better
								int tempI = Integer.parseInt(readC[1]);//no longer works
								rollCond += Math.abs(tempI);
								if(readC[0].equalsIgnoreCase("x")){	
									x += tempI;
									canMove = (canMove(temp,tempI,y,"x"));
									//check if it can move west or east
								}
								if(readC[0].equalsIgnoreCase("y")){
									y+=tempI;
									canMove = (canMove(temp,x, tempI,"y"));
									//check if it can move north or south
								}
								if(canMove!=null && canMove.getRoom()!=null){
									rollCond=roll;
									break;
								}
							}
						}
						System.out.println((temp.y + x) + " " + (temp.x + y));
						BoardTile t = switchPos(x+temp.x, y+temp.y,temp);
						System.out.println(t.getRoom());
						printBoard();
						if (ans!= null && temp.getPrevPos().getRoom()!=null)
						ans += "," + temp.getPrevPos().getRoom().toString();
						if(t.getRoom() != null){
							//move player and weapon to room
							GameObject o = makeAttempt(ans, read, i);
							if(o!=null){
								System.out.println("Dispproved: " + o.toString());
							}else{
								System.out.println("Player: " + temp.playerNumber + " wins!");
								return;
							}	
						}
					}
				}
			}
		}
	}

	/*
	 * return a player based on the character he's playing and a string.
	 */
	public BoardTile switchPos(int x, int y, Player p){
		BoardTile temp =  board[y][x];
		board[y][x] = p.getPiece();
		board[p.y][p.x] = p.getPrevPos();
		p.setPrevPos(x, y,temp);
		return temp;
	}
	//returns a player based on a string entered
	public Player getPlayer(String s){
		for(Player p: players){
			if(p.getCharacter().compare(s))
				return p;
		}
		return null;
	}
	//used for when a player makes a guess as to who did it, should move the player and weapon into that room.
	public GameObject makeAttempt(String ans, String[] read, int i) throws IOException{
		System.out.println("Make a guess.");
		System.out.println("Hint: Make sure to spell everything correctly, with commas between each item. Weapon,Character");
		ans = br.readLine() + "," + players.get(i).getPrevPos().getRoom().toString();
		read = ans.split(",");
		int count = i + 1;
		if(count == players.size()){
			count = 0;
		}
		GameObject o = null;
		Room rt = null;
		//Moves the weapon and player if he/she is in the game to the room.
		for(Room r: rooms){
			if(r.compare(read[2])){
				rt = r;
			}
		}
		if(getPlayer(read[1])!=null){
			String[] temp = rt.doors[0].split(",");
			switchPos(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), getPlayer(read[1]));
			printBoard();
			for (Weapon w: weapons){
				if(w.compare(read[0])){
					w.r = rt;
				}
			}
		}
		//checks to see if the other players can disprove the player
		for(int j = count; j < players.size();j++){
			if(found(players.get(j).getHand(), read)!=null){
				o = found(players.get(j).getHand(), read);
				break;
			}
			
				if(j == count-1)
					break;
				if(j == players.size()-1 && players.size()>2)
					j = 0;
			 
		}

		return o;
	}
	//returns the game object in a players hand if it matches a string.
	public GameObject found(ArrayList<GameObject>a, String[] sa){
		//iterates through a players hand returning an object if it's found.
		for (GameObject o : a){
			for(int i = 0; i < sa.length;i++){
				if(o.compare(sa[i]))
					return o;
			}
		}
		return null;
	}

	public boolean makeAccusation(String[] s){

		//will throw an exception if the user enters in the incorrect order
		Weapon w = null;
		Room r = null;
		GameCharacter c = null;

		for(GameObject g: deck){
			if(g.compare(s[0]))
				w=(Weapon) g;
			if(g.compare(s[1]))
				r=(Room)g;
			if(g.compare(s[2]))
				c=(GameCharacter)g;
		}
		return crime.accusation(w, r, c);
	}

	//bug here
	/**
	 * checks if the player can move in an X or Y direction, should return null if it cannot.
	 * @param p
	 * @param x
	 * @param y
	 * @param s
	 * @return
	 */
	public BoardTile canMove(Player p, int x, int y, String s){
		// note just add costs so if we can change this method to only move if the cost is = to the player roll or the player is in a room, cost of navigting a room is zero.
		int tempS = 0;
		int tempE = 0;
		if(s.equals("y")){
			if(y < 0){
				tempS = p.y+y; 
				tempE = p.y;
			}
			else {
				tempS = p.y;
				tempE = p.y + y;
			}
			for(int i = tempS; i < tempE; i++){
				if(board[i][p.x+x].getRoom()!=null){
					return board[i][p.x+x];
				}
				if(board[i][p.x+x].toString().equalsIgnoreCase("W"))
					return null;
			}
			return(board[Math.max(tempE, tempS)][p.x+x]);
		} else {
			if(x < 0){
				tempS = p.x+x; 
				tempE = p.x;
			}
			else {
				tempS = p.x;
				tempE = p.x+x;
			}
			for(int i = tempS; i < tempE; i++){
				if(board[p.y+y][i].getRoom()!=null)
					return board[p.y+y][i];
				if(board[p.y+y][i].toString().equalsIgnoreCase("W"))
					return null;
			}
			return(board[p.y+y][Math.max(tempE, tempS)]);
		}
	}

	public void printBoard(){
		for(int i = 0; i < board.length; i++){
			System.out.printf("%2d: ",i);
			for(int j = 0; j < board[i].length; j++){
				System.out.printf("%s ",board[i][j].toString());
			}
			System.out.println();
		}
		System.out.println("    � � � � � � � � � � � � � � � � � � � � � � � � �");
		System.out.println("    0 1 2 3 4 5 6 7 8 9 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2");
		System.out.println("                        0 1 2 3 4 5 6 7 8 9 0 1 2 3 4");
	}
	/*
	 * Gets input for the number of players and which character they wish to play.
	 */
	public void init(){
		System.out.println("Welcome to Cluedo!");
		System.out.println("Please enter the number of players, it must be between 2 and 6.");
		int player = 0;
		boolean selected;
		try {
			while(player < 2)
				player = Integer.parseInt(br.readLine());
			for(int i = 1; i < player + 1; i++){
				System.out.println("Player: " + i + " please select a character from:");
				for(GameCharacter c: characters){
					System.out.printf("%s \n",c.toString());
				}
				System.out.printf("-----------------------------------------\n");
				selected = false;
				while(!selected){
					String playerChoice = br.readLine();
					playerChoice.trim();
					for(int j = 0; j < characters.size(); j++){ 
						GameCharacter c = characters.get(j);
						if(c.toString().equalsIgnoreCase(playerChoice)){
							players.add(new Player(c, i));
							characters.remove(j);
							selected = true;
						}
					}
				}
			}
			System.out.println(player);
		} catch (Exception e) {}
	}
	//Inner class, used when making the final accusation.
	private class Crime{
		public final Weapon w;
		public final Room r;
		public final GameCharacter c;
		public Crime(Weapon w, Room r, GameCharacter c){
			this.w = w;
			this.r = r;
			this.c = c;
		}
		public boolean accusation(Weapon w, Room r, GameCharacter c){
			if(w == null || r == null || c == null){
				return false;
			}
			if(this.w.compare(w.toString()) && this.r.compare(r.toString()) && this.c.compare(c.toString())){
				return true;
			}
			return false;
		}
	}
}