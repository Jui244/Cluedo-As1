package board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import piece.BoardTile;
import piece.GameObject;
import piece.Room;
import piece.Weapon;
import piece.GameCharacter;

import game.Player;

/**
 * So well, this is going to where the game loop is,
 * I will first initialize the board, each players hands, 
 * the 'killer' and, the players start positions (guess I 
 * I should probably make that random?)
 * 
 * Also this is where I'll have the 2D array for the board,
 * the only things I'll store on the board is if it'study part of the 
 * hallway or an entrance way, if it'study an entrance way I'll sort
 * out which room it'study trying to get into... somehow.
 * 
 * 
 * @author Potato
 *
 */
public class Board {

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

		rooms = new ArrayList<Room>(9);
		weapons = new ArrayList<Weapon>(9);
		characters = new ArrayList<GameCharacter>(6);
		deck = new ArrayList<GameObject>();
		players = new ArrayList<Player>(4);

		//Only place a player can move on is the 'hallway' or an 'entrance'
		//change null to a set of starting items in each room.


		GameCharacter mScar = new GameCharacter("Miss Scarlett", 17, 29);
		GameCharacter cMust = new GameCharacter("Colonel Mustard", 7, 29);
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
		

		Room kitchen = new Room("Kitchen");
		Room diningRoom = new Room("Dining Room");
		Room guestHouse = new Room("Guest House");
		Room patio = new Room("Patio");
		Room spa = new Room("Spa");
		Room theater = new Room("Theater");
		Room livingRoom = new Room("Living Room");
		Room observatory = new Room("Observatory");
		Room hall = new Room("Hall");
		Room clue = new Room("Clue");
		
		observatory.setPassage(kitchen);
		kitchen.setPassage(observatory);
		guestHouse.setPassage(spa);
		spa.setPassage(guestHouse);
		
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
		//Initializes each players hand
		
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

		BoardTile k = new BoardTile("K", kitchen);
		BoardTile kr = new BoardTile(" ", kitchen);
		BoardTile d = new BoardTile("D", diningRoom);
		BoardTile dr = new BoardTile(" ", diningRoom);
		BoardTile g = new BoardTile("G", guestHouse);
		BoardTile gr = new BoardTile(" ", guestHouse);
		BoardTile p = new BoardTile("P", patio);
		BoardTile pr = new BoardTile(" ", patio);
		BoardTile s = new BoardTile("S", spa);
		BoardTile sr = new BoardTile(" ", spa);
		BoardTile t = new BoardTile("T", theater);
		BoardTile tr = new BoardTile(" ", theater);
		BoardTile l = new BoardTile("L", livingRoom);
		BoardTile lr = new BoardTile(" ", livingRoom);
		BoardTile o = new BoardTile("O", observatory);
		BoardTile or = new BoardTile(" ", observatory);
		
		BoardTile c = new BoardTile("C", clue);
		BoardTile cr = new BoardTile(" ", clue);
		
		BoardTile h = new BoardTile("H", hall);
		BoardTile hr = new BoardTile(" ", hall);

		BoardTile w = new BoardTile("W", null); //Wall, check if the position is a wall if it is don't move through it... duh.
		BoardTile hw = new BoardTile(" ", null); //Hall Way, the best kind of way

		// item keeps track of the rooms it's in.

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
				{ w,kr,kr,kr,kr, w,hw,hw, w,dr,dr,dr,dr,dr,dr, w,hw,hw,hw, w,gr,gr,gr,gr, w},
				{ w,kr,kr,kr,kr, w,hw,hw, w,dr,dr,dr,dr,dr,dr, w,hw,hw,hw, w,gr,gr,gr,gr, w},
				{ w,kr,kr,kr,kr, w,hw,hw, w,dr,dr,dr,dr,dr,dr, w,hw,hw,hw, w,gr,gr,gr,gr, w},
				{ w,kr,kr,kr,kr, w,hw,hw, w,dr,dr,dr,dr,dr,dr, w,hw,hw,hw, w,gr,gr,gr,gr, w},
				{ w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w}

		};
		//Puts the players into there starting locations
		this.board = initBoard;
		count = 0;
		for(Player pl:players){
			int x = pl.getCharacter().x;
			int y = pl.getCharacter().y;
			pl.setPrevPos(x, y, board[y][x]);
			board[y][x] = pl.getPiece();
		}

		printBoard();
		gameLoop();
		/*
		 * If I can be bothered later, I'll start fixing the exceptions this will throw if the user enters bad input.. not sure how important that is for this assignment.
		 */
		

	}
	
	
	public void gameLoop() throws IOException{
		while(!killerFound){
			for(int i = 0; i < players.size();i++){
				
				Player temp = players.get(i);
				
				String[] read = null;
				
				temp.printhand();
				
				System.out.println("Would you like to make your final accusation? y/n");
				
				String ans = br.readLine();
				
				if(ans.equalsIgnoreCase("y")){
					while(read == null || read.length!=3){
						System.out.println("Please enter your accusation. Weapon,Room,Character");
						ans = br.readLine();
						read = ans.split(",");
					}
					if(makeAccu(read)){
						System.out.println("Player: " + players.get(i).playerNumber + " wins the game!");
						return;
					} else {
						
						players.remove(i);
						if(players.size() == 1){
							System.out.println("Player: " + players.get(0).playerNumber + " wins the game!");
							return;
						}
						break;
					}
					//Happy with this.
			
				}
				else{
					if(temp.getRoom()!=null && temp.getRoom().getPassage()!=null){
						System.out.println("Would you like to move to: " + temp.getRoom().getPassage().toString() + ". y/n");
						ans = br.readLine();
						if(ans.equalsIgnoreCase("y")){
							GameObject o = makeAttempt(ans, read, i);
							if(o!=null){
								System.out.println("Dispproved: " + o.toString());
							}else{
								System.out.println("Player: " + temp.playerNumber + " wins!");
								return;
							}	
						}
					}else{
						int roll = 1 + (int)(Math.random() * ((6 - 1) + 1));
						System.out.println("You rolled a " + roll + "!");
						System.out.println("What co-or would you like to move to?");
						System.out.println("Hint: Enter how many units you would like to move horizontal by 'x=*' and how many verticle by 'y=*', you can have multiple" +
								"\nof these if you wish to have a more complex move.\nExample input x=2,y=3 to move east 2 and south 3 or y=-2,x=3 to move north 2 and east 3.");

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
						
						while(rollCond!=roll){
							for(int j = 0; j < read.length; j++){//need to check if the length is equal to the roll or if they enter a room, also if there is a wall in the path;
								
								if(canMove == null){
									read = null;
									canMove = temp.getPiece();
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
								int tempI = Integer.parseInt(readC[1]);//works though
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
									System.out.println("Is this reached");
									rollCond=roll;
									break;
								}
							}
						}

						
						BoardTile tempP =  board[y+temp.y][x+temp.x];
						board[y+temp.y][x + temp.x] = temp.getPiece();
						board[temp.y][temp.x] = temp.getPrevPos();
						temp.setPrevPos(x+temp.x, y+temp.y,tempP);
						System.out.println((temp.y + x) + " " + (temp.x + y));


						System.out.println(tempP.getRoom());

						printBoard();

						if(tempP.getRoom() != null){
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
	
	public GameObject makeAttempt(String ans, String[] read, int i) throws IOException{
		System.out.println("Make a guess.");
		System.out.println("Hint: Make sure to spell everything correctly, with commas between each item. Weapon,Character");
		ans = br.readLine();
		read = ans.split(",");
		int count = i + 1;

		if(count == players.size()){
			count = 0;
		}
		GameObject o = null;
		for(int j = count; j < players.get(j).getHand().size();j++){
			
			if(found(players.get(j).getHand(), read)!=null){
				o = found(players.get(j).getHand(), read);
				break;
			}
			/*
			if(j == players.size()-1)
				j = 0;//the only way out of this loop should be buy winning or showing a card and breaking.
			 */
		}
		return o;
	}
	
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

	public boolean makeAccu(String[] s){
		
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
	
	//need to change this to support hitting an entrance
	
	public BoardTile canMove(Player p, int x, int y, String s){
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
				if(board[i][p.x+x].compare("W"))
					return null;
			}
			return(board[tempE-1][p.x+x]);
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
				if(board[p.y+y][i].compare("W"))
					return null;

			}
			return(board[p.y+y][tempE-1]);
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

		System.out.println("    ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨ ¨");
		System.out.println("    1 2 3 4 5 6 7 8 9 1 1 1 1 1 1 1 1 1 1 2 2 2 2");
		System.out.println("                      0 1 2 3 4 5 6 7 8 9 0 1 2 3");
	}

	public void init(){
		System.out.println("Welcome to Cluedo!");
		System.out.println("Please enter the number of players, it must be between 2 and 4.");
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


	/**
	 * reserved for the combination of room/weapon/killer, needs to be guessed to win.
	 * @author Potato
	 *
	 */
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
			if(this.w.toString().equalsIgnoreCase(w.toString()) && this.r.toString().equalsIgnoreCase(r.toString()) && this.c.toString().equalsIgnoreCase(c.toString())){
				return true;
			}
			return false;
		}
	}
}