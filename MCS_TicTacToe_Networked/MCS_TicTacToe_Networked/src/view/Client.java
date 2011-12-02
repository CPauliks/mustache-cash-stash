//BEGIN FILE Client.java
package view;

import model.PlaceValue;

/**
 * A simple interface for a TTT client. Clients provide the means of communication between a user (or AI) and the controller.
 * This composes part of the View for the MVC pattern.
 * A UI or AI module is attached to the Client and the clients attach to the controller.
 * @author Benjamin Pellittieri and Christopher Pauliks for Mustache Cash Stash
 * @version 1.0
 */
//BEGIN INTERFACE Client
public interface Client 
{
	
	/**
	 * Instructs a Client to update its Gameboard model.
	 * A client receiving this call should attempt to retrieve a new Gameboard and update any relevant info,
	 * such as the GUI view of the Gameboard or the AI's move system for an AI attached client.
	 */
	//BEGIN METHOD public void updateGameboard()
	public void updateGameboard();
	//END METHOD public void updateGameboard()
	
	/**
	 * Instructs a Client to update its Statistics model. 
	 * A client receiving this call should attempt to retrieve a new Statistics item and update any relevant info.
	 */
	//BEGIN METHOD public void updateStatistics()
	public void updateStatistics();
	//END METHOD public void updateStatistics()
	
	/**
	 * Requests a move on the behalf of the client's attached user.
	 * A client receiving this call should pass the call towards the controller along with it's relevant piece information.
	 * @param xPosition The x (horizontal) coordinate of the requested space to occupy
	 * @param yPosition The y (vertical) coordinate of the requested space to occupy
	 * @return Whether the move request was successful.
	 */
	//BEGIN METHOD public boolean requestMove(int xPosition, int yPosition)
	public boolean requestMove(int xPosition, int yPosition, PlaceValue side);
	//END METHOD public boolean requestMove(int xPosition, int yPosition)
	
	/**
	 * Requests to forfeit the current game on behalf of the client's attached user.
	 * A client receiving this call should pass the call towards the controller along with itself.
	 * @return Whether the request to resign was successful.
	 */
	//BEGIN METHOD public boolean resign()
	public boolean resign(PlaceValue side);
	//END METHOD public boolean resign()
	
	/**
	 * Requests to quit the game entirely on behalf of the client's attached user.
	 * This should also forfeit the current game (if any).
	 * A client receiving this call should pass the call towards the controller along with itself.
	 */
	//BEGIN METHOD public void endSession()
	public void endSession(PlaceValue side);
	//END METHOD public void endSession()
}
//END INTERFACE Client
//END FILE Client.java