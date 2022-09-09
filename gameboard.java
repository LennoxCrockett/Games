package battleship;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class gameboard {
	protected char sea = '-';
	protected int missiles = 30;
	protected int gameDifficulty = 1; // 1 = Beginner, 2 = Standard, 3 = Advanced
	protected int gameBoardLength = 6;
	protected char gameBoard[][];
	private char aircraftCarrier = 'A';
	private char battleship = 'B';
	private char destroyer = 'D';
	private char submarine = 'S';
	private char patrol = 'P';
	private boolean vertical = false;
	Random rand = new Random();     // Used to generate random coordinates.
	private int xCoord; int yCoord; // These variables holds a random x-coordinate and y-coordinate.
	private boolean isOccupied = false;
	private boolean canPlaceOnBoard = false;
	static Scanner input = new Scanner(System.in);
	
	public void welcomeUser() {
		System.out.println("WELCOME TO BATTLESHIP!");
	}
	public void selectDifficulty() {
		boolean selectingDifficulty = true;
		
		do {	// The user must select Beginner = 6x6, Standard = 9x9, or Advance = 12x12 by typing 1, 2, or 3.
					System.out.println("Select your game difficulty.");
					System.out.printf("\n1 - Beginner(6 x 6) \n2 - Standard(9 x 9) \n3 - Advanced(12 x 12)");
					System.out.printf("\nType 1, 2, or 3: ");

					gameDifficulty = input.nextInt();
					switch (gameDifficulty) {
					case 1:
						System.out.println("\nDifficulty: Beginner"); 
						System.out.println("You want an easy game? Fair Enough!"); 
						missiles = 30; 
						gameBoardLength = 6;
						selectingDifficulty = false;  // User succeeds therefore the loop is closed.
						break;
					case 2:
						System.out.println("\nDifficulty: Standard"); 
						System.out.println("Not too hard not too easy I see, respectable!");
						missiles = 50; 
						gameBoardLength = 9;
						selectingDifficulty = false;
						break;
					case 3:
						System.out.println("\nDifficulty: Advanced"); 
						System.out.println("Looking for a challenge huh? Bring it on!"); 
						missiles = 75; 
						gameBoardLength = 12;
						selectingDifficulty = false;
						break;
					default: // If user doesn't type 1, 2, or 3.
						System.out.println("Must input 1, 2, or 3. Try Again.");
					}		
			
		}
		while (selectingDifficulty);
		
		this.missiles = missiles;
	}
	public void setGameBoardSize() {
		gameBoard = new char[gameBoardLength][gameBoardLength]; // Sets the game board sides 6x6, 9x9, or 12x12.
		System.out.printf("Game board length is %d x %d.", gameBoardLength, gameBoardLength);
		System.out.println("\nYou have "+ missiles + " missiles use them wisely.");
		}

	
	public void placeWater() {
		// Fills game board with water.
		for (int x = 0; x < gameBoard.length; x++) {
			for (int y = 0; y < gameBoard[x].length; y++) {
				gameBoard[x][y] = sea;
			}
		}
	}
	public void placeAircraftCarrier() { /* Places Aircraft Carrier it will not check for
									 		previously placed ships because it goes first */
		do {
			xCoord = rand.nextInt(gameBoardLength); // Random X-Coordinate.
			yCoord = rand.nextInt(gameBoardLength); // Random Y-Coordinate.
			vertical = rand.nextBoolean(); 			// Randomly picks vertical or horizontal.
			gameBoard[xCoord][yCoord] = aircraftCarrier;
			
			if(!vertical) { // Will attempt horizontal placement.
				if (gameBoardLength >= (yCoord + 4)) { // Checks for room for the rest of the ship to the right.
					gameBoard[xCoord][yCoord+1] = aircraftCarrier;
					gameBoard[xCoord][yCoord+2] = aircraftCarrier;
					gameBoard[xCoord][yCoord+3] = aircraftCarrier;
					gameBoard[xCoord][yCoord+4] = aircraftCarrier;
					canPlaceOnBoard = true;
				} else if (1 <= (yCoord - 4)){ // Checks for room for the rest of the ship to the left.
					gameBoard[xCoord][yCoord-1] = aircraftCarrier;
					gameBoard[xCoord][yCoord-2] = aircraftCarrier;
					gameBoard[xCoord][yCoord-3] = aircraftCarrier;
					gameBoard[xCoord][yCoord-4] = aircraftCarrier;
					canPlaceOnBoard = true;
				} else // Since there is no room for the ship break from the if/else statement and randomize again.
					break;
			}
			else if (vertical){ // Will attempt vertical placement
				if (gameBoardLength >= (xCoord + 4)) { // Checks for room for the rest of the ship upwards.
					gameBoard[xCoord+1][yCoord] = aircraftCarrier;
					gameBoard[xCoord+2][yCoord] = aircraftCarrier;
					gameBoard[xCoord+3][yCoord] = aircraftCarrier;
					gameBoard[xCoord+4][yCoord] = aircraftCarrier;
				} else if (1 <= (xCoord - 4)) { // Checks for room for the rest of the ship downwards.
					gameBoard[xCoord-1][yCoord] = aircraftCarrier;
					gameBoard[xCoord-2][yCoord] = aircraftCarrier;
					gameBoard[xCoord-3][yCoord] = aircraftCarrier;
					gameBoard[xCoord-4][yCoord] = aircraftCarrier;
				} else // Since there is no room for the ship break from the if/else statement and randomize again.
					break;
			} else {
				System.out.println("Placing Aircraft Carrier...");
			}
		} while (!canPlaceOnBoard);
	}
	public void placePatrol() {// Places Patrol 
		canPlaceOnBoard = false;
		isOccupied = true;
		do { // Goes until the ship is placed.
			xCoord = rand.nextInt(gameBoardLength); // Random X-Coordinate.
			yCoord = rand.nextInt(gameBoardLength); // Random Y-Coordinate.
			vertical = rand.nextBoolean(); // Randomly picks vertical or horizontal.
			do { // Goes until a free spot is found.
				if (gameBoard[xCoord][yCoord] == sea) {
					isOccupied = false;
				} else {
					xCoord = rand.nextInt(gameBoardLength); // Random X-Coordinate.
					yCoord = rand.nextInt(gameBoardLength); // Random Y-Coordinate.
					vertical = rand.nextBoolean(); // Randomly picks vertical or horizontal.
					}
				} while (isOccupied);
			gameBoard[xCoord][yCoord] = patrol;
		
			if(!vertical) { // Horizontal placement. If there is 1 available spaces to the right place the ship.
				if (gameBoardLength >= (yCoord + 1) && gameBoard[xCoord][yCoord + 1] == sea) {
					gameBoard[xCoord][yCoord+1] = patrol;
					canPlaceOnBoard = true;	// The ship could place therefore the loop ends.	
				}		   // Horizontal placement. If there is 1 available spaces to the left place the ship.
				else if (gameBoardLength >= (yCoord - 1) && gameBoard[xCoord][yCoord - 1] == sea) {
					gameBoard[xCoord][yCoord-1] = patrol;
					canPlaceOnBoard = true;	// The ship could place therefore the loop ends.
				} else {
					System.out.println("Placing Patrol...");
				}
			
			}
			else if (vertical) { // Vertical placement. If there is 1 available spaces above place the ship.
				if (gameBoardLength <= (xCoord +1) && gameBoard[xCoord + 1][yCoord] == sea) { 
					gameBoard[xCoord+1][yCoord] = patrol;
					canPlaceOnBoard = true; // The ship could place therefore the loop ends.
				}				// Vertical placement. If there is 1 available spaces below place the ship.
				else if (gameBoardLength <= (xCoord - 1) && gameBoard[xCoord - 1][yCoord] == sea) {
					gameBoard[xCoord-1][yCoord] = patrol;
					canPlaceOnBoard = true;	// The ship could place therefore the loop ends.
				} else {
					System.out.println("Placing Patrol...");
				}
			}
		}while(!canPlaceOnBoard);
	}// End of placePatrol.
	public void placeDestroyer() {	// Places Destroyer
		canPlaceOnBoard = false;
		isOccupied = true;
		do { // Goes until the ship is placed.
			xCoord = rand.nextInt(gameBoardLength); // Random X-Coordinate.
			yCoord = rand.nextInt(gameBoardLength); // Random Y-Coordinate.
			vertical = rand.nextBoolean(); // Randomly picks vertical or horizontal.
			do { // Checks if a ship is already there.
				if (gameBoard[xCoord][yCoord] == sea) {
					isOccupied = false;
				} else {
					xCoord = rand.nextInt(gameBoardLength); // Random X-Coordinate.
					yCoord = rand.nextInt(gameBoardLength); // Random Y-Coordinate.
					vertical = rand.nextBoolean(); // Randomly picks vertical or horizontal.
					}
				} while (isOccupied);
			gameBoard[xCoord][yCoord] = destroyer;
		
			if(!vertical) { // Horizontal placement. If there is 2 available spaces to the right place the ship.
				if (gameBoardLength >= (yCoord + 1) && gameBoard[xCoord][yCoord + 1] == sea &&
					gameBoardLength >= (yCoord + 2) && gameBoard[xCoord][yCoord + 2] == sea) {
					gameBoard[xCoord][yCoord+1] = destroyer;
					gameBoard[xCoord][yCoord+2] = destroyer;
					canPlaceOnBoard = true;	// The ship could place therefore the loop ends.
				}		   // Horizontal placement. If there is 2 available spaces to the left place the ship.
				else if (gameBoardLength >= (yCoord - 1) && gameBoard[xCoord][yCoord - 1] == sea &&
						 gameBoardLength >= (yCoord - 2) && gameBoard[xCoord][yCoord - 2] == sea) {
					gameBoard[xCoord][yCoord-1] = destroyer;
					gameBoard[xCoord][yCoord-2] = destroyer;
					canPlaceOnBoard = true;	// The ship could place therefore the loop ends.
				} else {
					System.out.println("Placing Destroyer...");
				}
			
			}
			else if (vertical) { // Vertical placement. If there is 2 available spaces above place the ship.
				if (gameBoardLength >= (xCoord + 1) && gameBoard[xCoord + 1][yCoord] == sea &&
					gameBoardLength >= (xCoord + 2) && gameBoard[xCoord + 2][yCoord] == sea) { 
					gameBoard[xCoord+1][yCoord] = destroyer;
					gameBoard[xCoord+2][yCoord] = destroyer;
					canPlaceOnBoard = true; // The ship could place therefore the loop ends.
				}				// Vertical placement. If there is 2 available spaces below place the ship.
				else if (gameBoardLength >= (xCoord - 1) && gameBoard[xCoord - 1][yCoord] == sea &&
						 gameBoardLength >= (xCoord - 2) && gameBoard[xCoord - 2][yCoord] == sea) {
					gameBoard[xCoord-1][yCoord] = destroyer;
					gameBoard[xCoord-2][yCoord] = destroyer;
					canPlaceOnBoard = true;	// The ship could place therefore the loop ends.
				} else {
					System.out.println("Placing Destroyer...");	
				}
			}
		}while(!canPlaceOnBoard);
	}// End of placeDestroyer.
	public void placeSubmarine() {// Places Submarine
		canPlaceOnBoard = false;
		isOccupied = true;
		do { // Goes until the ship is placed.
			xCoord = rand.nextInt(gameBoardLength); // Random X-Coordinate.
			yCoord = rand.nextInt(gameBoardLength); // Random Y-Coordinate.
			vertical = rand.nextBoolean(); // Randomly picks vertical or horizontal.
			do { // Checks if a ship is already there.
				if (gameBoard[xCoord][yCoord] == sea) {
					isOccupied = false;
				} else {
					xCoord = rand.nextInt(gameBoardLength); // Random X-Coordinate.
					yCoord = rand.nextInt(gameBoardLength); // Random Y-Coordinate.
					vertical = rand.nextBoolean(); // Randomly picks vertical or horizontal.
					}
				} while (isOccupied);
			gameBoard[xCoord][yCoord] = submarine;
		
			if(!vertical) { // Horizontal placement. If there is 2 available spaces to the right place the ship.
				if (gameBoardLength >= (yCoord + 1) && gameBoard[xCoord][yCoord + 1] == sea &&
					gameBoardLength >= (yCoord + 2) && gameBoard[xCoord][yCoord + 2] == sea) {
					gameBoard[xCoord][yCoord+1] = submarine;
					gameBoard[xCoord][yCoord+2] = submarine;
					canPlaceOnBoard = true;	// The ship could place therefore the loop ends.
				}		  // Horizontal placement. If there is 2 available spaces to the left place the ship.
				else if (gameBoardLength >= (yCoord - 1) && gameBoard[xCoord][yCoord - 1] == sea &&
						 gameBoardLength >= (yCoord - 2) && gameBoard[xCoord][yCoord - 2] == sea) {
					gameBoard[xCoord][yCoord-1] = submarine;
					gameBoard[xCoord][yCoord-2] = submarine;
					canPlaceOnBoard = true;	// The ship could place therefore the loop ends.
				} else {
					System.out.println("Placing Submarine...");
				}
			
			}
			else if (vertical) { // Vertical placement. If there is 2 available spaces above place the ship.
				if (gameBoardLength >= (xCoord + 1) && gameBoard[xCoord + 1][yCoord] == sea &&
					gameBoardLength >= (xCoord + 2) && gameBoard[xCoord + 2][yCoord] == sea) { 
					gameBoard[xCoord+1][yCoord] = submarine;
					gameBoard[xCoord+2][yCoord] = submarine;
					canPlaceOnBoard = true; // The ship could place therefore the loop ends.
				}				// Vertical placement. If there is 2 available spaces below place the ship.
				else if (gameBoardLength >= (xCoord - 1) && gameBoard[xCoord - 1][yCoord] == sea &&
						 gameBoardLength >= (xCoord - 2) && gameBoard[xCoord - 2][yCoord] == sea) {
					gameBoard[xCoord-1][yCoord] = submarine;
					gameBoard[xCoord-2][yCoord] = submarine;
					canPlaceOnBoard = true;	// The ship could place therefore the loop ends.
				} else {
					System.out.println("Placing Submarine...");		
				}
			}
		}while(!canPlaceOnBoard);
	}// End of placeSubmarine.
	public void placeBattleship() { // Places BattleShip
		canPlaceOnBoard = false;
		isOccupied = true;
		do { // Goes until the ship is placed.
			xCoord = rand.nextInt(gameBoardLength); // Random X-Coordinate.
			yCoord = rand.nextInt(gameBoardLength); // Random Y-Coordinate.
			vertical = rand.nextBoolean(); // Randomly picks vertical or horizontal.
			do { // Checks if a ship is already there.
				if (gameBoard[xCoord][yCoord] == sea) {
					isOccupied = false;
				} else {
					xCoord = rand.nextInt(gameBoardLength); // Random X-Coordinate.
					yCoord = rand.nextInt(gameBoardLength); // Random Y-Coordinate.
					vertical = rand.nextBoolean(); // Randomly picks vertical or horizontal.
					}
				} while (isOccupied);
			gameBoard[xCoord][yCoord] = battleship;
		
			if(!vertical) { // Horizontal placement. If there is 2 available spaces to the right place the ship.
				if (gameBoardLength >= (yCoord + 1) && gameBoard[xCoord][yCoord + 1] == sea &&
					gameBoardLength >= (yCoord + 2) && gameBoard[xCoord][yCoord + 2] == sea &&
					gameBoardLength >= (yCoord + 3) && gameBoard[xCoord][yCoord + 3] == sea) {
					
					gameBoard[xCoord][yCoord+1] = battleship;
					gameBoard[xCoord][yCoord+2] = battleship;
					gameBoard[xCoord][yCoord+3] = battleship;
					canPlaceOnBoard = true;	// The ship could place therefore the loop ends.
				}		  // Horizontal placement. If there is 2 available spaces to the left place the ship.
				else if (gameBoardLength >= (yCoord - 1) && gameBoard[xCoord][yCoord - 1] == sea &&
						 gameBoardLength >= (yCoord - 2) && gameBoard[xCoord][yCoord - 2] == sea &&
						 gameBoardLength >= (yCoord - 3) && gameBoard[xCoord][yCoord - 3] == sea) {
					gameBoard[xCoord][yCoord-1] = battleship;
					gameBoard[xCoord][yCoord-2] = battleship;
					gameBoard[xCoord][yCoord-3] = battleship;
					canPlaceOnBoard = true;	// The ship could place therefore the loop ends.
				} else {
					System.out.println("Placing Battleship...");	
				}
			
			}
			else if (vertical) { // Vertical placement. If there is 2 available spaces above place the ship.
				if (gameBoardLength >= (xCoord + 1) && gameBoard[xCoord + 1][yCoord] == sea &&
					gameBoardLength >= (xCoord + 2) && gameBoard[xCoord + 2][yCoord] == sea &&
					gameBoardLength >= (xCoord + 3) && gameBoard[xCoord + 3][yCoord] == sea) { 
					gameBoard[xCoord+1][yCoord] = battleship;
					gameBoard[xCoord+2][yCoord] = battleship;
					gameBoard[xCoord+2][yCoord] = battleship;
					canPlaceOnBoard = true; // The ship could place therefore the loop ends.
				}				// Vertical placement. If there is 2 available spaces below place the ship.
				else if (gameBoardLength >= (xCoord - 1) && gameBoard[xCoord - 1][yCoord] == sea &&
						 gameBoardLength >= (xCoord - 2) && gameBoard[xCoord - 2][yCoord] == sea &&
						 gameBoardLength >= (xCoord - 3) && gameBoard[xCoord - 3][yCoord] == sea) {
					gameBoard[xCoord-1][yCoord] = battleship;
					gameBoard[xCoord-2][yCoord] = battleship;
					gameBoard[xCoord-2][yCoord] = battleship;
					canPlaceOnBoard = true;	// The ship could place therefore the loop ends.
				} else {
					System.out.println("Placing Submarine...");	
				}
			}
		}while(!canPlaceOnBoard);
	} // End of placeBattleship.
	public void printGameBoard() {

		int counter = 0;
		char letter = 'A';
		System.out.print("  ");
    	for (int x = 0; x < gameBoardLength; x++) {
			System.out.print((x+1) + " ");
		}
    	System.out.println();
    	System.out.print(letter + " ");
     	letter++;
    	for (int x = 0; x < gameBoard.length; x++) {
    		for (int y = 0; y < gameBoard[x].length; y++) {
    			
    			if (counter == (gameBoardLength - 1)) {
    			System.out.print(gameBoard[x][y] + "\n" + letter + " ");
    			letter++;
    			counter = 0;
    			}
    			else {
    				System.out.print(gameBoard[x][y] + " ");
    				counter++;
    			}
    		}
    	}
	} // End of printGameBoard.
}
