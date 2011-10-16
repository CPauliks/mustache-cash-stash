package UI;

public class Game
{
	/*
	 * getPlayers
	 * 
	 * Return a string array of players
	 */
	public static String[] getPlayers(String user1, String user2)
	{
		String[] playersArray = new String[2];
		
		playersArray[0] = user1;
		playersArray[1] = user2;
		
		return playersArray;
	}
	
}
