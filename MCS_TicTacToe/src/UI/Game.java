package UI;

public class Game
{
	/*
	 * getPlayers
	 * 
	 * Return a string array of players
	 */
	public static String[] getPlayers(String username)
	{
		String[] playersArray = new String[2];
		
		playersArray[0] = username;
		playersArray[1] = "Computer";
		
		return playersArray;
	}
	
}
