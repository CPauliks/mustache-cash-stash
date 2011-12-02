
package controller;
import model.GameboardImp;
import model.PlaceValue;

public class Game {
	
	private GameboardImp board;
	private User xPlayer;
	private User oPlayer;
	
	public Game(User xPlayer, User oPlayer)
	{
		this.board = new GameboardImp();
		this.xPlayer = xPlayer;
		this.oPlayer = oPlayer;
	}
	
	public User getXPlayer()
	{
		return this.xPlayer;
	}
	
	public User getOPlayer()
	{
		return this.oPlayer;
	}
	
	public GameboardImp getBoard()
	{
		return (GameboardImp) this.board.getState();
	}
	
	public boolean hasAPlayer(User requester)
	{
		return (requester.equals(this.xPlayer)||requester.equals(this.oPlayer));
	}
	
	public boolean hasXPlayer(User requester)
	{
		return this.xPlayer.equals(requester);
	}
	
	public boolean hasOPlayer(User requester)
	{
		return this.oPlayer.equals(requester);
	}
	
	public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace)
	{
		return this.board.requestMove(xPosition, yPosition, pieceToPlace);
	}
	
	public boolean resign(PlaceValue piece) 
	{		
		return this.board.resign(piece);
	}
	
}
