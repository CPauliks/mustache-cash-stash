//BEGIN FILE StatisticsImp.java
package model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//BEGIN CLASS StatisticsImp
public class StatisticsImp implements Statistics, Cloneable{
	
	private Map<String, List<Integer>> sessionStatistics;
	
	/**
	 * Generates a new Statistics class gives session data for a game going on
	 */
	//BEGIN CONSTRUCTOR StatisticsImp
	public StatisticsImp(){
		//setSessionStatistics(sessionStatistics);
		this.sessionStatistics = new HashMap<String, List<Integer>>();
	}
	//END CONSTRUCTOR StatisticsImp	
	
	//BEGIN CONSTRUCTOR StatisticsImp
	public StatisticsImp(Map<String, List<Integer>> stats){
		this.sessionStatistics = stats;
	}
	//END CONSTRUCTOR StatisticsImp
	
	@Override
	/**
	 * Returns an immutable copy of this Statistics for use by views to update their model.
	 * @return An immutable copy of this Statistics
	 */
	//BEGIN METHOD public Statistics getState()
	public Statistics getState() {
		// TODO Auto-generated method stub
		return this.clone();
	}
	//END METHOD public Statistics getState()

	@Override
	/**
	 * Returns the number of wins for a given player.
	 * @param playerName The name of the player to query
	 * @return The number of wins
	 */
	//BEGIN METHOD public int getWins()
	public int getWins(String playerName) {
		// TODO Auto-generated method stub
		return sessionStatistics.get(playerName).get(0);
	}
	//END METHOD public int getWins()

	@Override
	/**
	 * Returns the number of losses for a given player.
	 * @param playerName The name of the player to query.
	 * @return The number of losses.
	 */
	//BEGIN METHOD public int getLosses()
	public int getLosses(String playerName) {
		// TODO Auto-generated method stub
		List<Integer> stats = sessionStatistics.get(playerName);
		return stats.get(1);
	}
	//END METHOD public int getLosses()

	@Override
	/**
	 * Returns the number of ties for a given player.
	 * @param playerName The name of the player to query.
	 * @return The number of ties.
	 */
	//BEGIN METHOD public int getTies()
	public int getTies(String playerName) {
		return sessionStatistics.get(playerName).get(2);
	}
	//END METHOD public int getTies()

	@Override
	/**
	 * Returns the total number of games played by a given player
	 * @param playerName The name of the player to query.
	 * @return The total number of games.
	 */
	//BEGIN METHOD public int getTies()
	public int getGameCount(String playerName) {
		// TODO Auto-generated method stub
		
		return sessionStatistics.get(playerName).get(3);
	}
	//END METHOD public int getTies()

	@Override
	/**
	 * Adds a win for the given player's record.
	 * @param playerName The player to assign a win to.
	 */
	//BEGIN METHOD public addWind()
	public void addWin(String playerName) {
		// TODO Auto-generated method stub
		List<Integer> stats = sessionStatistics.get(playerName);
		stats.add(0, stats.get(0) + 1);
		sessionStatistics.put(playerName,  stats);
	}

	@Override
	/**
	 * Adds a loss for the given player's record.
	 * @param playerName The player to assign a loss to.
	 */
	//BEGIN METHOD public addLoss()
	public void addLoss(String playerName) {
		// TODO Auto-generated method stub
		List<Integer> stats = this.sessionStatistics.get(playerName);
		stats.add(1, stats.get(1) + 1);
		this.sessionStatistics.put(playerName,  stats);
	}
	//END METHOD public addLoss()
	
	@Override
	/**
	 * Adds a tie for the given player's record.
	 * @param playerName The player to assign a tie to.
	 */
	//BEGIN METHOD public addTie()
	public void addTie(String playerName) {
		// TODO Auto-generated method stub
		
		List<Integer> stats = this.sessionStatistics.get(playerName);
		stats.add(2, stats.get(2) + 1);
		
		this.sessionStatistics.put(playerName, stats);
	}
	//END METHOD public addTie()
	
	//BEGIN METHOD public getSessionStatistics
	public Map<String, List<Integer>> getSessionStatistics() {
		return this.sessionStatistics;
	}
	//END METHOD getSessionStatistics
	
	//BEGIN METHOD public setSessionStatistics()
	public void setSessionStatistics(Map<String, List<Integer>> sessionStatistics) {
		this.sessionStatistics = sessionStatistics;
	}
	//END METHOD getSessionStatistics
	
	//BEGIN METHOD public Statistics clone()
	@Override
	public Statistics clone(){
		
		Statistics c = new StatisticsImp(sessionStatistics);
		
		return c;
		
	}
	//END METHOD public Statistics clone()
	
	//BEGIN METHOD public addPlayer()
	@Override
	public void addPlayer(String playerName){
		List<Integer> stats = new ArrayList<Integer>();
		for(int i = 0; i < 4; i++){
			stats.add(i, (Integer) 0);
		}
		this.sessionStatistics.put(playerName, stats);
	}
	//BEGIN METHOD public addPlayer()
	
}
//END CLASS StatisticsImp
