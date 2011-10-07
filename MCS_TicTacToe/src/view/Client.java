package view;

public interface Client {
	public void updateGameboard();
	public void updateStatistics();
	public boolean requestMove(int xPosition, int yPosition);
	public boolean resign();
	public void endSession();
}
