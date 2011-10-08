//BEGIN FILE Controller.java
package controller;

import view.*;
import model.*;

/*
 * A simple interface for a TTT controller. Keeps track of which views are playing the game as well as the models they have in use.
 * Passes requests from the views to the model and results back to the views.
 * @author Benjamin Pellittieri and Christopher Pauliks for Mustache Cash Stash
 * @version 0.0 pending CCR approval
 */
//BEGIN INTERFACE Controller
public interface Controller 
{
	
	/*
	 * A request from a client to place a piece on the Gameboard at a certain position.
	 * The controller passes this request to the Gameboard
	 * @param xPosition The x (horizontal) coordinate of the requested space to occupy
	 * @param yPosition The y (vertical) coordinate of the requested space to occupy
	 * @param pieceToPlace The type of piece to put in the requested location.
	 * @return Whether the move request was successful in updating the Gameboard.
	 */
	//BEGIN METHOD public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace)
	public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace);
	//END METHOD public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace)
	
	/*
	 * Allows a Client to ask whether it is their turn.
	 * @param thisClient The Client asking the question.
	 * @return true if it is this Client's turn, else false.
	 */
	//BEGIN METHOD public boolean isClientsTurn(Client thisClient)
	public boolean isClientsTurn(Client thisClient);
	//END METHOD public boolean isClientsTurn(Client thisClient)
	
	/*
	 * Begins a new game with the given clients.
	 * @param xPlayer The player controlling X pieces
	 * @param oPlayer The player controlling O pieces
	 */
	//BEGIN METHOD public void newGame(Client xPlayer, Client oPlayer)
	public void newGame(Client xPlayer, Client oPlayer);
	//END METHOD public void newGame(Client xPlayer, Client oPlayer)
	
	/*
	 * Allows a Client to request to forfeit the current game.
	 * @param resigningClient The Client requesting to forfeit the game.
	 * @return Whether the request to resign was successful.
	 */
	//BEGIN METHOD public boolean resign(Client resigningClient)
	public boolean resign(Client resigningClient);
	//END METHOD public boolean resign(Client resigningClient)
	
	/*
	 * Requests to quit the game entirely.
	 * This should also forfeit the current game (if any).
	 * @param quittingClient The client requesting to end the game.
	 */
	//BEGIN METHOD public void endSession(Client quittingClient)
	public void endSession(Client quittingClient);
	//END METHOD public void endSession(Client quittingClient)
	
	/*
	 * Allows a client to request a copy of the Gameboard
	 * This request should be passed to the Gameboard
	 * @return A copy of the Gameboard
	 */
	//BEGIN METHOD public Gameboard getGameboardState()
	public Gameboard getGameboardState();
	//END METHOD public Gameboard getGameboardState()
	
	/*
	 * Allows a client to request a copy of the Statistics
	 * This request should be passed to the Statistics
	 * @return A copy of the Statistics
	 */
	//BEGIN METHOD public Statistics getStatisticsState()
	public Statistics getStatisticsState();
	//END METHOD public Statistics getStatisticsState()
}
//END INTERFACE Controller

//END FILE Controller.java
