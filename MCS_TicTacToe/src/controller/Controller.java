package controller;

import model.*;
import view.Client;

public interface Controller {
	public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace);
	public boolean isClientsTurn(Client thisClient);
	public void newGame(Client xPlayer, Client oPlayer);
	public void resign(Client resigningPlayer);
	public void endSession();
	public Gameboard getGameboardState();
	public Statistics getStatisticsState();
}
