package model;

public interface Gameboard {
	public Gameboard getState();
	public boolean xsTurn();
	public boolean osTurn();
	public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace);
}
