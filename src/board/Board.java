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
 * How could you ever think I would play Digimon.. I'm quite offended.
 * @author Potato
 *
 */
public class Board {
	
	private ArrayList<Player> players;
	private ArrayList<Room> rooms;
	private ArrayList<Weapon> weapons;
	private ArrayList<GameCharacter> characters;
	private ArrayList<GameObject> deck;
	
	@SuppressWarnings("unused")
	private Crime crime;
	private GameCharacter murderer;
	private Room murderRoom;
	private Weapon murderWeapon;
	
	private BoardTile[][] board;
	
	private boolean killerFound = false;

	

	public Board() throws IOException{
		
		rooms = new ArrayList<Room>(9);
		weapons = new ArrayList<Weapon>(6);
		characters = new ArrayList<GameCharacter>(6);
		deck = new ArrayList<GameObject>();
		players = new ArrayList<Player>(4);
		
		//Only place a player can move on is the 'hallway' or an 'entrance'
		//change null to a set of starting items in each room.
		
		GameCharacter mScar = new GameCharacter("Miss Scarlett");
		GameCharacter cMust = new GameCharacter("Colonel Mustard");
		GameCharacter mWhit = new GameCharacter("Mrs White");
		GameCharacter rGree = new GameCharacter("Reverent Green");
		GameCharacter mPeac = new GameCharacter("Mrs Peacock");
		GameCharacter pPlum = new GameCharacter("Professor Plum");
		
		characters.add(mScar);
		characters.add(cMust);
		characters.add(mWhit);
		characters.add(rGree);
		characters.add(mPeac);
		characters.add(pPlum);
		Collections.shuffle(characters);
		murderer = characters.get(0);
		for(int i = 1; i < characters.size(); i++)
			deck.add(characters.get(i));
		
		init();
		
		for(Player p: players){
			System.out.println(p.getCharacter());
		}
		
		Weapon candleStick = new Weapon("Candle Stick");
		Weapon dagger = new Weapon("Dagger");
		Weapon leadPipe = new Weapon("Lead Pipe");
		Weapon revolver = new Weapon("Revolver");
		Weapon rope = new Weapon("Rope");
		Weapon spanner = new Weapon("Spanner");
		
		weapons.add(candleStick);
		weapons.add(dagger);
		weapons.add(leadPipe);
		weapons.add(revolver);
		weapons.add(rope);
		weapons.add(spanner);
		
		Collections.shuffle(weapons);
		murderWeapon = weapons.get(0);
		for(int i = 1; i < weapons.size(); i++)
			deck.add(weapons.get(i));
		
		Room kitchen = new Room("Kitchen");
		kitchen.addWeapon(weapons.get(0));
		Room diningRoom = new Room("Kitchen");
		diningRoom.addWeapon(weapons.get(1));
		Room lounge = new Room("Lounge");
		lounge.addWeapon(weapons.get(2));
		Room ballRoom = new Room("Ball Room");
		ballRoom.addWeapon(weapons.get(3));
		Room conservatory = new Room("Conservatory");
		conservatory.addWeapon(weapons.get(4));
		Room billiardRoom = new Room("Billiard Room");
		billiardRoom.addWeapon(weapons.get(5));
		Room library = new Room("Library");
		Room study = new Room("Study");
		Room hall = new Room("Hall");

		rooms.add(kitchen);
		rooms.add(diningRoom);
		rooms.add(lounge);
		rooms.add(ballRoom);
		rooms.add(conservatory);
		rooms.add(billiardRoom);
		rooms.add(library);
		rooms.add(study);
		rooms.add(hall);
	
		Collections.shuffle(rooms);
		murderRoom = rooms.get(0);
		for(int i = 1; i < rooms.size(); i++)
			deck.add(rooms.get(i));
		
		Collections.shuffle(deck);
		//Initializes each players hand
		int count = 0;
		for(int i = 0; i < deck.size();i++){
			if(count == players.size())
				count = 0;
			players.get(count).addToHand(deck.get(i));
			count++;
		}
		
		crime = new Crime(murderWeapon, murderRoom, murderer);
		
		BoardTile k = new BoardTile("K", kitchen); //Kitchen
		BoardTile dr = new BoardTile("D", diningRoom); //DiningRoom
		BoardTile l = new BoardTile("l", lounge); //Lounge
		BoardTile br = new BoardTile("B", ballRoom); //BallRoom
		BoardTile c = new BoardTile("C", conservatory); //Conservatory
		BoardTile b = new BoardTile("b", billiardRoom); //BilliardRoom
		BoardTile li = new BoardTile("L", library); //Library
		BoardTile s = new BoardTile("S", study); //Study
		BoardTile h = new BoardTile("H", hall); //Hall
		BoardTile w = new BoardTile("W", null); //Wall, check if the position is a wall if it is don't move through it... duh.
		BoardTile r = new BoardTile("-", null); //Room, while a player is inside a room he should be able to move freely without any cost, so before moving check if it's an instance of this.
		BoardTile hw = new BoardTile(" ", null); //Hall Way, the best kind of way
		BoardTile st = new BoardTile("S", null); //Start Point, spawn point

		// Each room keeps track of the items in it. Just need to write a contains method or something :D
		
		BoardTile[][] initBoard = {
				{ w, w, w, w, w, w, w, w, w,st, w, w, w,st, w, w, w, w, w, w, w, w, w},
				{ w, r, r, r, r, w, w,hw,hw,hw, w, r, w,hw,hw,hw, w, w, r, r, r, r, w},
				{ w, r, r, r, r, w,hw,hw, w, w, w, r, w, w, w,hw,hw, w, r, r, r, r, w},
				{ w, r, r, r, r, w,hw,hw, w, r, r, r, r, r, w,hw,hw, c, r, r, r, r, w},
				{ w, r, r, r, r, w,hw,hw,br, r, r, r, r, r,br,hw,hw,hw, w, w, w, w, w},
				{ w, w, w, w, k, w,hw,hw, w, r, r, r, r, r, w,hw,hw,hw,hw,hw,hw,hw,st},
				{ w,hw,hw,hw,hw,hw,hw,hw, w,br, w, w, w,br, w,hw,hw,hw,hw,hw,hw,hw, w},
				{ w,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw, w, w, w, w, w, w},
				{ w, w, w, w, w,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw, b, r, r, r, r, w},
				{ w, r, r, r, w, w, w, w,hw,hw, w, w, w, w,hw,hw,hw, w, r, r, r, r, w},
				{ w, r, r, r, r, r, r, w,hw,hw, w, w, w, w,hw,hw,hw, w, r, r, r, r, w},
				{ w, r, r, r, r, r, r,dr,hw,hw, w, w, w, w,hw,hw,hw, w, w, w, w, b, w},
				{ w, r, r, r, r, r, r, w,hw,hw, w, w, w, w,hw,hw,hw,hw,hw,hw,hw,hw, w},
				{ w, r, r, r, r, r, r, w,hw,hw, w, w, w, w,hw,hw,hw, w, w,li, w, w, w},
				{ w, w, w, w, w, w,dr, w,hw,hw, w, w, w, w,hw,hw, w, w, r, r, r, r, w},
				{ w,hw,hw,hw,hw,hw,hw,hw,hw,hw, w, w, w, w,hw,hw,li, r, r, r, r, r, w},
				{st,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw,hw, w, w, w, r, r, r, w},
				{ w,hw,hw,hw,hw,hw,hw,hw,hw, w, w, h, h, w, w,hw,hw,hw, w, w, w, w, w},
				{ w, w, w, w, w, w, w,hw,hw, w, r, r, r, r, w,hw,hw,hw,hw,hw,hw,hw,st},
				{ w, r, r, r, r, r, l,hw,hw, w, r, r, r, r, h,hw,hw,hw,hw,hw,hw,hw, w},
				{ w, r ,r, r, r, r, w,hw,hw, w, r, r, r, r, w,hw,hw, w, s, w, w, w, w},
				{ w, r ,r, r, r, r, w,hw,hw, w, r, r, r, r, w,hw,hw, w, r, r, r, r, w},
				{ w, r ,r, r, r, r, w,hw,hw, w, r, r, r, r, w,hw,hw, w, r, r, r, r, w},
				{ w, w, w, w, w, w, w,st, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w}
		};

		this.board = initBoard;
		count = 0;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length;j++){
				if(board[i][j].equals(st)){
					players.get(count).setPrevPos(i, j, board[i][j]);
					board[i][j] = players.get(count).getPiece();
					count++;
				}
				if(count == players.size())
					break;
			}
			if(count == players.size())
				break;
		}
		
		printBoard();
		
		while(!killerFound){
			for(int i = 0; i < players.size();i++){
				Player temp = players.get(i);
				String[] read = null;
				temp.printhand();
				System.out.println("Would you like to make your final accusation? y/n");
				BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
				String ans = buff.readLine();
				if(ans.equalsIgnoreCase("y")){
					while(read == null || read.length!=3){
						System.out.println("Please enter your accusation. Weapon,Room,Character");
						ans = buff.readLine();
						read = ans.split(",");
					}
					//check if the accusations is true, otherwise remove the player from the game
				}
				else{
					if(temp.getRoom()!=null && temp.getRoom().getPassage()!=null){
						System.out.println("Would you like to move to: " + temp.getRoom().print() + ". y/n");
						ans = buff.readLine();
						if(ans.equalsIgnoreCase("y"))break;//move player to next room allow them to make a guess
					}else{
						int roll = 1 + (int)(Math.random() * ((6 - 1) + 1));
						System.out.println("You rolled a " + roll + "!");
						System.out.println("What co-or would you like to move to?");
						System.out.println("Hint: Enter how many units you would like to move horizontal by 'x=*' and how many verticle by 'y=*', you can have multiple" +
								"\n of these if you wish to have a more complex move.\nExample input x=2,y=3 to move east 2 and north 3 or y=-2,x=3 to move south 2 and east 3.");
						read = null;
						while(read == null || read.length <= 1){
							ans = buff.readLine();
							read = ans.split(",");
						}
						for(int j = 0; j < read.length; j++){//need to check if the length is equal to the roll or if they enter a room, also if there is a wall in the path;
							if(read[j].startsWith("x=")){
								
							}
							if(read[j].startsWith("y=")){
								
							}
						}
						//move the player to the new position, might make a special case for when in a room.
						if(temp.getRoom() != null){
							System.out.println("Make a guess.");
							ans = buff.readLine();
							//iterate though all the players seeing if the can disprove
						}
					}
				}
				printBoard();
			}
			//print out who ever's turn it is
			//print players hand
			//Ask if they want to make there final accusation
			//check if they're in a room with a passageway
			//if so ask if they would like to go to other room.
			//roll dice and ask for co-or. Use a* to check if they can reach it otherwise re ask for input.
			//if they enter a room allow them to make a guess
			//guess must use the same room the player is in. move the player accused and the weapon to the room the players in.
			//iterate through the players, if the next player can prove that the accusation is wrong they must otherwise the next player must
			//print board
			//if everyone passes player wins.
		}
	}


	public void printBoard(){
		for(int i = 0; i < board.length; i++){
			System.out.printf("%2d: ",i);
			for(int j = 0; j < board[i].length; j++){
				System.out.printf("%s ",board[i][j].print());
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean selected;
		try {
			while(player < 2)
				player = Integer.parseInt(br.readLine());
			for(int i = 1; i < player + 1; i++){
				System.out.println("Player: " + i + " please select a character from:");
				for(GameCharacter c: characters){
					System.out.printf("%s \n",c.print());
				}
				System.out.printf("-----------------------------------------\n");

				selected = false;
				while(!selected){
					String playerChoice = br.readLine();
					playerChoice.trim();
					for(int j = 0; j < characters.size(); j++){ 
						GameCharacter c = characters.get(j);
						if(c.print().equalsIgnoreCase(playerChoice)){
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
		@SuppressWarnings("unused")
		public boolean accusation(Weapon w, Room r, GameCharacter c){
			if(this.w.equals(w) && this.r.equals(r) && this.c.equals(c)){
				return true;
			}
			return false;
		}
		
	}
}
