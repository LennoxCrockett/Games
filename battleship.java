package battleship;
/*
Lennox Crockett
12/11/2021
This program functions as a single player battleship game.
Although it is currently designed to display evry ship location.
*/
public class battleship {

	public static void main(String[] args) {
		
		gameboard gameSetup1 = new gameboard(); // Used to set up the game.
		
		// User picks game difficulty.
		gameSetup1.welcomeUser(); // Introduction to the user.
		gameSetup1.selectDifficulty(); // Selects the board size.
		gameSetup1.setGameBoardSize(); // Creates the board size.
		
		// Build the game board.
		gameSetup1.placeWater();	   
		gameSetup1.placeAircraftCarrier();
		gameSetup1.placeBattleship();
		gameSetup1.placePatrol();
		gameSetup1.placeDestroyer();
		gameSetup1.placeSubmarine();
		gameSetup1.printGameBoard(); 
		
		// User begins playing the game.
		actiongame gameplay1 = new actiongame(); // Used to play the game.
		gameplay1.takeUserShot();
	}

}
