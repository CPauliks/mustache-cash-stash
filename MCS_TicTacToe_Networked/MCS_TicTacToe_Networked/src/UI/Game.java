//BEGIN FILE Game.java
package UI;

//BEGIN CLASS Game
public class Game
{
	/**
	 * Takes two strings and returns an array containing the strings
	 * Used to send the names as an argument to the GameBoardDisplay
	 * @param user1 the first player's name
	 * @param user2 the second player's name
	 * @return An array containing the two of them.
	 */
	//BEGIN METHOD public static String[] getPlayers(String user1, String user2)
	public static String[] getPlayers(String user1, String user2)
	{
		String[] playersArray = new String[2];

		playersArray[0] = user1;
		playersArray[1] = user2;

		return playersArray;
	}
	//END METHOD public static String[] getPlayers(String user1, String user2)
}
//END CLASS Game
//END FILE Game.java