

/**
 * 
 * Assignment 1
 * COMP249
 * @author 1. Sami Ibrahim ID: 40156134 2. Zhangbin Cai ID:40165425
 * Due Date: February 08
 *This program is a version of Ladder And Snake game. It supports 2 - 4 players (named player#1 -4). At the beginning of the game, each player should roll the dice (press "enter" or any bottom e.x. "a", "d", "t", etc. to roll the dice) to determine their order of playing. The higher dice number they get, the earlier they will play. The one who reaches the end of the board (location 100) will be the winner. In the board there are snakes and ladders which will change players locations by moving down or up.
 ****to roll the dice, please press Enter directly or any bottom + Enter*********
 *
 */
public class Map {

	private String symbo;
	private int location;
	/**
	 * Map constructor; default symbo value will be "-", then will be occupied by players name. location value will be assigned from 1 - 100; 
	 * @param symbo
	 * @param location
	 */
	public Map(String symbo, int location) {
		
		this.symbo = symbo;
		this.location = location;
	}
	public String getSymbo() {
		return symbo;
	}
	public void setSymbo(String symbo) {
		this.symbo = symbo;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	
	
	
}
