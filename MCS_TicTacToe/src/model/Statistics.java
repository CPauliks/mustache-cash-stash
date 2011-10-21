//BEGIN FILE Statistics.java
package model;
//imports would go here if we had any

/**
* An interface for the statistics model for the TTT system.
* Keeps track of wins, losses, and ties for each player in a game.
* @author Benjamin Pellittieri and Christopher Pauliks for Mustache Cash Stash
* @version 0.0 pending CCR approval
*/
//BEGIN INTERFACE Statistics
public interface Statistics 
{
	
	/**
	 * Returns an immutable copy of this Statistics for use by views to update their model.
	 * @return An immutable copy of this Statistics
	 */
	//BEGIN METHOD public Statistics getState()
	public Statistics getState();
	//END METHOD public Statistics getState()
	
	/**
	 * Returns the number of wins for a given player.
	 * @param playerName The name of the player to query
	 * @return The number of wins
	 */
	//BEGIN METHOD public int getWins(String playerName)
	public int getWins(String playerName);
	//END METHOD public int getWins(String playerName)
	
	/**
	 * Returns the number of losses for a given player.
	 * @param playerName The name of the player to query.
	 * @return The number of losses.
	 */
	//BEGIN METHOD getLosses(String playerName)
	public int getLosses(String playerName);
	//END METHOD getLosses(String playerName)
	
	/**
	 * Returns the number of ties for a given player.
	 * @param playerName The name of the player to query.
	 * @return The number of ties.
	 */
	//BEGIN METHOD getTies(String playerName)
	public int getTies(String playerName);
	//END METHOD getTies(String playerName)
	
	/**
	 * Returns the total number of games played by a given player
	 * @param playerName The name of the player to query.
	 * @return The total number of games.
	 */
	//BEGIN METHOD getGameCount(String playerName)
	public int getGameCount(String playerName);
	//END METHOD getGameCount(String playerName)
	
	/**
	 * Adds a win for the given player's record.
	 * @param playerName The player to assign a win to.
	 */
	//BEGIN METHOD addWin(String playerName)
	public void addWin(String playerName);
	//END METHOD addWin(String playerName)
	
	/**
	 * Adds a loss for the given player's record.
	 * @param playerName The player to assign a loss to.
	 */
	//BEGIN METHOD addLoss(String playerName)
	public void addLoss(String playerName);
	//END METHOD addLoss(String playerName)
	
	/**
	 * Adds a tie for the given player's record.
	 * @param playerName The player to assign a tie to.
	 */
	//BEGIN METHOD addTie(String playerName)
	public void addTie(String playerName);
	//END METHOD addTie(String playerName)

	public void addPlayer(String playerName);
}
//END INTERFACE Statistics
//END FILE Statistics.java