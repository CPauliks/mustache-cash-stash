//BEGIN FILE Gameboard.java
package model;
//imports would go here if we had any

/*
* An interface for the gameboard model for the TTT system.
* Keeps track of the board and what pieces are where
* @author Benjamin Pellittieri and Christopher Pauliks for Mustache Cash Stash
* @version 0.0 pending CCR approval
*/
//BEGIN INTERFACE Gameboard
public interface Gameboard 
{
	
	/*
	 * Returns an immutable copy of this Gameboard for use by views to update their model.
	 * @return An immutable copy of this Gameboard
	 */
	//BEGIN METHOD public Gameboard getState()
	public Gameboard getState();
	//END METHOD public Gameboard getState()
	
	/*
	 * Returns whether it is X player's turn to place a piece.
	 * @return true if it is X's turn, else false
	 */
	//BEGIN METHOD public boolean xsTurn()
	public boolean xsTurn();
	//END METHOD public boolean xsTurn()
	
	/*
	 * Returns whether it is O player's turn to place a piece.
	 * @return true if it is O's turn, else false
	 */
	//BEGIN METHOD public boolean osTurn()
	public boolean osTurn();
	//END METHOD public boolean osTurn()
	
	/*
	 * A request from a client to place a piece on the Gameboard at a certain position.
	 * The Gameboard should check whether the move is in turn and is valid before placing the piece.
	 * @param xPosition The x (horizontal) coordinate of the requested space to occupy
	 * @param yPosition The y (vertical) coordinate of the requested space to occupy
	 * @param pieceToPlace The type of piece to put in the requested location.
	 * @return Whether the move request was successful in updating the Gameboard.
	 */
	//BEGIN METHOD public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace)
	public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace);
	//END METHOD public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace)
}
//END INTERFACE Gameboard

//END FILE Gameboard.java