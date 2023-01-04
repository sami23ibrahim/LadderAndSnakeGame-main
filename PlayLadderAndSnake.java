
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

public class PlayLadderAndSnake {

	public static void main(String[] args) {
		/**
		 * ask user to enter the number of players using while loop to make sure the
		 * user enter a number within the range 2-4
		 */

		System.out.println("Entere the number of player");

		Scanner input = new Scanner(System.in);

		int numberOfPlayrs = input.nextInt();

		while (numberOfPlayrs != 2 && numberOfPlayrs != 3 && numberOfPlayrs != 4) {
			System.out.println("The number of players must be between 2 and 4 inclusively, please enter again");
			numberOfPlayrs = input.nextInt();
		}

		/**
		 * create a game and call the methods
		 */
		LadderAndSnake s = new LadderAndSnake(numberOfPlayrs);
		s.map();
		s.decidOrder(numberOfPlayrs);
		s.checkOder();
		s.play();
		input.close();
	}

}
