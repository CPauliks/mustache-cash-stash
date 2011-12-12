//BEGIN FILE Game.java
package controller;
import model.GameboardImp;
import model.PlaceValue;

/**
 * A representation of a current game in progress
 * @author Mustache Cash Stash
 * @version 1.0
 */
//BEGIN CLASS Game
public class Game {

	private GameboardImp board;
	private User xPlayer;
	private User oPlayer;
	
	/**
	 * Begins a Game with these Users as players
	 * @param xPlayer
	 * @param oPlayer
	 */
	//BEGIN CONSCTUCTOR public Game(User xPlayer, User oPlayer)
	public Game(User xPlayer, User oPlayer)
	{
		this.board = new GameboardImp();
		this.xPlayer = xPlayer;
		this.oPlayer = oPlayer;
	}
	//END CONSTRUCTOR public Game(User xPlayer, User oPlayer)

	/**
	 * Returns who is the X piece in this Game
	 * @return The User placing Xs
	 */
	//BEGIN METHOD public User getXPlayer()
	public User getXPlayer()
	{
		return this.xPlayer;
	}
	//END METHOD public User getXPlayer()

	/**
	 * Returns who is the O piece in this Game
	 * @return The User placing Os
	 */
	//BEGIN METHOD public User getOPlayer()
	public User getOPlayer()
	{
		return this.oPlayer;
	}
	//END METHOD public User getOPlayer()

	/**
	 * Returns this game's underlying GameBoard
	 * @return The current GameBoard for this Game
	 */
	//BEGIN METHOD public GameboardImp getBoard()
	public GameboardImp getBoard()
	{
		return (GameboardImp) this.board.getState();
	}
	//END METHOD public GameboardImp getBoard()
	
	/**
	 * Returns whether the requested player is part of this Game
	 * @param requester The User to check
	 * @return Whether the User is part of this Game
	 */
	//BEGIN METHOD public boolean hasAPlayer(User requester)
	public boolean hasAPlayer(User requester)
	{
		return (requester.equals(this.xPlayer)||requester.equals(this.oPlayer));
	}
	//END METHOD public boolean hasAPlayer(User requester)

	/**
	 * Returns whether this player is the X piece for this Game
	 * @param requester The User to check
	 * @return Whether this User is the X piece for this game
	 */
	//BEGIN METHOD public boolean hasXPlayer(User requester)
	public boolean hasXPlayer(User requester)
	{
		return this.xPlayer.equals(requester);
	}
	//END METHOD public boolean hasXPlayer(User requester)
	
	/**
	 * Returns whether this player is the O piece for this Game
	 * @param requester The User to check
	 * @return Whether this User is the O piece for this game
	 */
	//BEGIN METHOD public boolean hasOPlayer(User requester)
	public boolean hasOPlayer(User requester)
	{
		return this.oPlayer.equals(requester);
	}
	//END METHOD public boolean hasOPlayer(User requester)

	/**
	 * Requests a move to this Game
	 * @see model.Gameboard#requestMove
	 */
	//BEGIN METHOD public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace)
	public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace)
	{
		return this.board.requestMove(xPosition, yPosition, pieceToPlace);
	}
	//END METHOD public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace)

	/**
	 * Requests a resignation from this game
	 * @see model.Gameboard#resign
	 */
	//BEGIN METHOD public boolean resign(PlaceValue piece) 
	public boolean resign(PlaceValue piece) 
	{		
		return this.board.resign(piece);
	}
	//END METHOD public boolean resign(PlaceValue piece) 

}
//END FILE Game.java