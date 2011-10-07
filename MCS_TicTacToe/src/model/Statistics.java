package model;

public interface Statistics {
	public int getWins(String playerName);
	public int getLosses(String playerName);
	public int getTies(String playerName);
	public int getGameCount(String playerName);
	public void addWin(String playerName);
	public void addLoss(String playerName);
	public void addTie(String playerName);
}
