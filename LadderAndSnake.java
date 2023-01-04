
import java.util.Random;
import java.util.Scanner;

/**
 * Assignment 1
 * COMP249
 * @author 1. Sami Ibrahim ID: 40156134 2. Zhangbin Cai ID:40165425
 * Due Date: February 08
 *This program is a version of Ladder And Snake game. It supports 2 - 4 players (named player#1 -4). At the beginning of the game, each player should roll the dice (press "enter" or any bottom e.x. "a", "d", "t", etc. to roll the dice) to determine their order of playing. The higher dice number they get, the earlier they will play. The one who reaches the end of the board (location 100) will be the winner. In the board there are snakes and ladders which will change players locations by moving down or up.
 ****to roll the dice, please press Enter directly or any bottom + Enter*********
 *
 */
public class LadderAndSnake {

	/**
	 * create a 5-player obj array
	 * create a 10 x 10 map obj array
	 */

	private int numberOfPlayers;
	Scanner scanner = new Scanner(System.in);
	
	private Players[] playerArray = new Players[5];

	private Map[][] map = new Map[10][10];

//=============================================	
	/**
	 * create a map method with an obj array map, which contains string symbo (default value "-", when occupied by a player it will be a player name); and an integer for the player location
	 */
	public void map() {

		int i = 0, j = 0, k = 1; 

		for (i = 0; i < 10; i++) {
			for (j = 0; j < 10; j++, k++)

				map[i][j] = new Map("-", k);
		}

	}

//====================================================================================
	/**
	 * a method to print out the map. after the user movement, symbo will be signed as players name. After the next movement, obj signed with players name will be changed back to the default "-"; when printing out the map, if the symbo value is "-" will be printed the location number; while the obj with the players name will be printed as the players name;
	 * @param player = player's name
	 * @param currentPosition = player's location number
	 */
	public void printMap(String player, int currentPosition) {

		int i = 0, j = 0;
		for (i = 0; i < 10; i++) {
			for (j = 0; j < 10; j++) {
				if (map[i][j].getSymbo().equals(player)) {
					map[i][j].setSymbo("-");
				}

			}

		}
		for (i = 0; i < 10; i++) {
			for (j = 0; j < 10; j++) {
				if (map[i][j].getLocation() == currentPosition) {
					map[i][j].setSymbo(player);
				}

			}

		}

		for (i = 0; i < 10; i++) {
			for (j = 0; j < 10; j++) {
				if (map[i][j].getLocation() == currentPosition)
					System.out.print(map[i][j].getSymbo() + "                            ");
				else if (map[i][j].getSymbo().equals("-"))
					System.out.print(map[i][j].getLocation() + "                            ");
				else if (!map[i][j].getSymbo().equals("-"))
					System.out.print(map[i][j].getSymbo() + "                         ");
			}
			System.out.println();
		}

	}

//========================================================================
	/**
	 * constructor 
	 * @param numberOfPlayers
	 */
	public LadderAndSnake(int numberOfPlayers) {

		this.numberOfPlayers = numberOfPlayers;
		System.out.println("number of players: " + numberOfPlayers);

		
	}

//=====================================================================
	/**
	 * a method to declare each player in an obj array. create an extra array element as a temporary element for later to sort the player order
	 */

	public void setPlayerArray() {
		playerArray[0] = new Players("player#1", 0, 0, false, false);
		playerArray[1] = new Players("player#2", 0, 0, false, false);
		playerArray[2] = new Players("player#3", 0, 0, false, false);
		playerArray[3] = new Players("player#4", 0, 0, false, false);
		playerArray[4] = new Players("temp", 0, 0, false, false);
	}
	// ===================================================================
/**
 * a method to make users play term by term with the sequence of the players array. while loop will be discontinued once there is a winner
 */
	public void play() {
		printOrder();
		while (!playerArray[0].isWinner() && !playerArray[1].isWinner() && !playerArray[2].isWinner()
				&& !playerArray[3].isWinner()) {
			int i = 0, j = 0;

			for (i = 0; i < numberOfPlayers && !playerArray[0].isWinner() && !playerArray[1].isWinner()
					&& !playerArray[2].isWinner() && !playerArray[3].isWinner(); i++) {
				System.out.println("\n\n" + playerArray[i].getName());
				System.out.println("Please type any buttom to role the dice");
				String enter = scanner.nextLine();
				j = flipDice();
				playerArray[i].playerMove(j);
				playerArray[i].setPosition(snakeNLadder(playerArray[i].getPosition()));//check if the position hit a ladder or a snake, so the position will be further changed accordingly
				if (playerArray[i].getPosition() == 100) {
					playerArray[i].setWinner(true);
				}
				printMap(playerArray[i].getName(), playerArray[i].getPosition());

			}

		}
		scanner.close();
		}

//===============================================================================
	/**
	 * a method to ask the player roll the dice to determine the order of playing turns
	 * @param numberOfPlayers
	 */
	public void decidOrder(int numberOfPlayers) {
		setPlayerArray();
		System.out.println("Before starting, Please role the dice to determine the order of players");
		int counter = 1;
		for (int i = 1; i < numberOfPlayers + 1; i++) {

			System.out.println("player#" + counter + " turn");

			System.out.println("please type any buttom to role the dice:");
			String enter = scanner.nextLine();
			System.out.print("player#" + counter + " got: ");

			//Random rand = new Random();

			int randomNum = flipDice();
					//rand.nextInt(6) + 1;
			System.out.println("*" + randomNum + "*\n");

			if (i == 1) {
				playerArray[0].setDiceNm(randomNum);
			} else if (i == 2) {
				playerArray[1].setDiceNm(randomNum);
			} else if (i == 3) {
				playerArray[2].setDiceNm(randomNum);
			} else if (i == 4) {
				playerArray[3].setDiceNm(randomNum);
			}
			counter++;
		}
		

	}

//=======================================================================================================
	/**
	 * a method to check the order of playing turns. the first for loop to sort the array order (players with higher dice number put at earlier turns); the second for loop to determine if there is a tie. If number of ties>3 will ask all players to roll the dice again, otherwise will user a tie method
	 */
	public void checkOder() {
		int sameDice = 0;
		//boolean tie = false;
		for (int i = 0; i < numberOfPlayers; i++) {
			for (int j = i + 1; j < numberOfPlayers; j++) {
				if (playerArray[i].getDiceNm() < playerArray[j].getDiceNm()) {
					playerArray[4] = playerArray[i];
					playerArray[i] = playerArray[j];
					playerArray[j] = playerArray[4];
				}
			}

		}
		for (int i = 0; i < numberOfPlayers; i++) {
			for (int j = i + 1; j < numberOfPlayers; j++) {
				if (playerArray[i].getDiceNm() == playerArray[j].getDiceNm()) {
					sameDice++;
					System.out.println("******* "+playerArray[i].getName() + " and " + playerArray[j].getName() + " have a tie *******");
					playerArray[i].setRepeatDice(true);
					playerArray[j].setRepeatDice(true);
					//tie = true;
				}
			}
		}
		if (sameDice >= 2) {
			System.out.println("You all need to re-role!\n");
			decidOrder(numberOfPlayers);
		} else if (sameDice == 1) {

			tie();
		}

		
	}

	// ========================================================================================
	/**
	 * a method to solve the tie issue. the first for loop to ask the user to roll the dice. the second for loop to sort the order. if another tie is reached call the tie method again.
	 */
	public void tie() {
		for (int i = 0; i < numberOfPlayers; i++) {
			if (playerArray[i].isRepeatDice()) {
				System.out.println("\n"+playerArray[i].getName() + " turn\n" + "Please type any buttom to roll the dice");
				String enter = scanner.nextLine();
				playerArray[i].setDiceNm(flipDice());
				
				System.out.println(playerArray[i].getName() + " got: *"
						+ playerArray[i].getDiceNm() + "*\n");
			}
		}
		for (int i = 0; i < numberOfPlayers; i++) {
			for (int j = i + 1; j < numberOfPlayers; j++) {
				if (playerArray[i].isRepeatDice() && playerArray[j].isRepeatDice()) {
					if (playerArray[i].getDiceNm() < playerArray[j].getDiceNm()) {
						playerArray[4] = playerArray[i];
						playerArray[i] = playerArray[j];
						playerArray[j] = playerArray[4];
					} else if (playerArray[i].getDiceNm() == playerArray[j].getDiceNm()) {
						System.out.println("Tie again!");
						tie();
					}

				}
			}
		}
	}

//======================================================================================================
	/**
	 * a method to print out the order of playing turns
	 */
	public void printOrder() {
		System.out.println("\nThe order is: ");
		for (int i = 0; i < numberOfPlayers; i++) {

			System.out.print(playerArray[i].getName() + " ");
		}
	}
/**
 * 
 * @return a random value from 1-6
 */
	public int flipDice() {
		int a;
		return a = 1 + (int) (Math.random() * 6);
	}

	// =========================================================================================
/**
 * a method to detect ladders and snakes. if the player location match a ladder or a snake location the player's location will be changed according to the rule
 * 
 * @param userPosition
 * @return player's location after encounter a ladder or a snake
 */
	public int snakeNLadder(int userPosition) {
		if (userPosition == 16) {
			System.out.println("Got bit by a snake! Go down! You are now at 6");
			userPosition = 6;
		}
		if (userPosition == 48) {
			System.out.println("Got bit by a snake! Go down! You are now at 30");
			userPosition = 30;
		}
		if (userPosition == 62) {
			System.out.println("Got bit by a snake! Go down! You are now at 19");
			userPosition = 19;
		}
		if (userPosition == 63) {
			System.out.println("Got bit by a snake! Go down! You are now at 60");
			userPosition = 60;
		}
		if (userPosition == 98) {
			System.out.println("Got bit by a snake! Go down! You are now at 78");
			userPosition = 78;
		}
		if (userPosition == 97) {
			System.out.println("Got bit by a snake! Go down! You are now at 76");
			userPosition = 76;
		}
		if (userPosition == 95) {
			System.out.println("Got bit by a snake! Go down! You are now at 24");
			userPosition = 24;
		}
		if (userPosition == 93) {
			System.out.println("Got bit by a snake! Go down! You are now at 68");
			userPosition = 68;
		}

		if (userPosition == 4) {
			System.out.println("Reach a ladder! Go up! You are now at 14");
			userPosition = 14;
		}
		if (userPosition == 9) {
			System.out.println("Reach a ladder! Go up! You are now at 31");
			userPosition = 31;
		}
		if (userPosition == 20) {
			System.out.println("Reach a ladder! Go up! You are now at 38");
			userPosition = 38;
		}
		if (userPosition == 40) {
			System.out.println("Reach a ladder! Go up! You are now at 42");
			userPosition = 42;
		}
		if (userPosition == 36) {
			System.out.println("Reach a ladder! Go up! You are now at 44");
			userPosition = 44;
		}
		if (userPosition == 28) {
			System.out.println("Reach a ladder! Go up! You are now at 84");
			userPosition = 84;
		}
		if (userPosition == 71) {
			System.out.println("Reach a ladder! Go up! You are now at 91");
			userPosition = 91;
		}
		if (userPosition == 80) {
			System.out.println("Reach a ladder! Go up! You are now at 100");
			userPosition = 100;

		}
		return userPosition;
	}
//===============================================================================
	

}