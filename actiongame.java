package battleship;

import java.util.Scanner;

public class actiongame extends gameboard{
	int shipNumber; // Will go down for each ship that is sunk.
	String rowLetter; // Used to convert row to appropriate number.
	int row; // Row number for the game board.
	int column; // Column number for the game board.
	int patrol = 2; // Will go down each time the ship is hit. 
	int destroyer = 3;
	int submarine = 3;
	int battleship = 4;
	int aircraftCarrier = 5;
	boolean gameOver = false; // Changes to true to end the game.
	
	static Scanner input = new Scanner(System.in);

	void takeUserShot() {
		boolean validXCoord = false;
		boolean validYCoord = false;
		do { // Only if the game isn't over.
			do { // Checks if the user enters a valid coordinate.
					System.out.println("\nEnter row letter: "); 
					rowLetter = input.next();
					if (rowLetter == "a" || rowLetter == "A") { // A = 1, B = 2, C = 3, etc.
						row = 0;
						validXCoord = true;
					}
					else if (rowLetter == "b" || rowLetter == "B") {
						row = 1;
						validXCoord = true;
					}
					else if (rowLetter == "c" || rowLetter == "C") {
						row = 2;
						validXCoord = true;
					}
					else if (rowLetter == "d" || rowLetter == "D") {
						row = 3;
						validXCoord = true; }
					else if (rowLetter == "e" || rowLetter == "E") {
						row = 4;
						validXCoord = true;}
					else if (rowLetter == "f" || rowLetter == "F") {
						row = 5; 
						validXCoord = true; }
					else if (rowLetter == "g" || rowLetter == "G" && gameDifficulty == 2) {
						row = 6; 
						validXCoord = true; }
					else if (rowLetter == "h" || rowLetter == "H" && gameDifficulty == 2) {
						row = 7;
						validXCoord = true; }
					else if (rowLetter == "i" || rowLetter == "I" && gameDifficulty == 2) {
						row = 9;
						validXCoord = true; }
					else if (rowLetter == "j" || rowLetter == "J" && gameDifficulty == 3) {
						row = 9;
						validXCoord = true; }
					else if (rowLetter == "k" || rowLetter == "K" && gameDifficulty == 3) {
						row = 10; 
						validXCoord = true; }
					else if (rowLetter == "l" || rowLetter == "L" && gameDifficulty == 3) {
						row = 11;
						validXCoord = true; }
					else {
						System.out.println("Invalid row letter. Try again.");
						printGameBoard();	
					}
			}while(!validXCoord);
			do {
				System.out.println("Enter column number: "); 
				column = input.nextInt();
				if (column > gameBoardLength || column < 0) { // If the user enters a number that is off the board they try again.
					System.out.println("Invalid column number. Try again.");
					printGameBoard();
					System.out.println("Enter column number: "); 
					column = input.nextInt();
					}
				}while(!validYCoord);
			
			calculateUserShot();
		}while(!gameOver);
	}
	void calculateUserShot() {
		if(gameBoard[row][column] == sea) {
			gameBoard[row][column] = 'O';
			System.out.println("You completely missed! You're a lousy shot!");
			printGameBoard();
		}
		else if (gameBoard[row][column] == 'P'){
			gameBoard[row][column] = 'X';
			System.out.println("You hit the patrol ship! Nice job!");
			missiles--;
			patrol--;
			printGameBoard();
			if (patrol == 0) {
				System.out.println("The patrol has sunk! Awesome!");
				shipNumber--;
			}
		}
		else if (gameBoard[row][column] == 'D'){
			gameBoard[row][column] = 'O';
			System.out.println("You hit the destroyer! Nice job!");
			missiles--;
			destroyer--;
			printGameBoard();
			if (destroyer == 0) {
				System.out.println("The destroyer has sunk! Awesome!");
				shipNumber--;	
			}
		}
		else if (gameBoard[row][column] == 'S'){
			gameBoard[row][column] = 'O';
			System.out.println("You hit the submarine! Nice job!");
			missiles--;
			submarine--;
			printGameBoard();
			if (submarine == 0) {
				System.out.println("The submarine has sunk! Awesome!");
				shipNumber--;
			}
		}
		else if (gameBoard[row][column] == 'B'){
			gameBoard[row][column] = 'O';
			System.out.println("You hit the battleship! Nice job!");
			missiles--;
			battleship--;
			printGameBoard();
			if (battleship == 0) {
				System.out.println("The battleship has sunk! Awesome!");
				shipNumber--;
			}
		}
		else if (gameBoard[row][column] == 'A'){
			gameBoard[row][column] = 'O';
			System.out.println("You hit the aircraft carrier! Nice job!");
			missiles--;
			aircraftCarrier--;
			printGameBoard();
			if (aircraftCarrier == 0) {
				System.out.println("The aircraft carrier has sunk! Awesome!");
				shipNumber--;
			}
		
		} else { // Since the user has run out of missiles the game is over.
			System.out.println("You're out of missiles! The compuer wins!");
			gameOver = true;
		}
		
		if (shipNumber == 0) { // Since all the ships are sunken the game is over.
			System.out.println("Mission Accomplished! All ships have been sunken!");
			gameOver = true;
		}
	} // End of calculateUserShot.
} // End of gameboardClass
