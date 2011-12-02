//BEGIN FILE GameBoardImp.java
package model;

import java.util.LinkedList;

/**
* An implementation for the gameboard model for the TTT system.
* Keeps track of the board and what pieces are where
* @author Benjamin Pellittieri and Christopher Pauliks for Mustache Cash Stash
* @version 0.0 pending CCR approval
*/
//BEGIN CLASS GameBoardImp
public class GameboardImp implements Gameboard, Cloneable {
	
	private PlaceValue[][] currentBoard; 
	private Integer[] rowStates;
	private boolean xsTurn;	
	private boolean osTurn;	
	private GameResult result;
	
	/**
	 * Sets up a GameBoard for a new game.
	 */
	//BEGIN CONSTRUCTOR public GameBoardImp()
	public GameboardImp() 
	{
		currentBoard = new PlaceValue[3][3];
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				currentBoard[i][j] = PlaceValue.BLANK;
			}
		}
		rowStates = new Integer[8];
		for(int i = 0; i<8; i++)
		{
			rowStates[i] = 0;
		}
		xsTurn = true;
		osTurn = false;
		result = GameResult.PENDING;
	}
	//END CONSTRUCTOR public GameBoardImp()
	
	/**
	 * Sets up a GameBoard for a new game.
	 */
	//BEGIN CONSTRUCTOR public GameboardImp(boolean xsTurn, boolean osTurn, GameResult result, PlaceValue[][] board)
	public GameboardImp(boolean xsTurn, boolean osTurn, GameResult result, PlaceValue[][] board)
	{
		this.currentBoard = board;
		this.xsTurn = xsTurn;
		this.osTurn = osTurn;
		this.result = result;
	}
	//END METHOD public GameboardImp(boolean xsTurn, boolean osTurn, GameResult result, PlaceValue[][] board)
	
	@Override
	/**
	 * Returns a new copy of Gameboard
	 */
	//BEGIN METHOD public Gameboard clone() 
	public Gameboard clone() 
	{
		GameboardImp cloneBoard = new GameboardImp();
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				cloneBoard.forceMove(i, j, currentBoard[i][j]);
			}
		}
		cloneBoard.xsTurn = this.xsTurn;
		cloneBoard.osTurn = this.osTurn;
		return cloneBoard;
	}
	//END METHOD public Gameboard clone() 
	
	@Override
	//BEGIN METHOD public PlaceValue[][] getBoard()
	public PlaceValue[][] getBoard()
	{
		PlaceValue[][] cloneBoard = new PlaceValue[3][3];
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				cloneBoard[i][j] = currentBoard[i][j];
			}
		}
		return currentBoard;
	}
	//END METHOD public PlaceValue[][] getBoard()
	
	@Override
	//BEGIN METHOD public GameResult getResult()
	public GameResult getResult()
	{
		return this.result;
	}
	//END METHOD public GameResult getResult()
	

	@Override
	//BEGIN METHOD public Gameboard getState()
	public Gameboard getState() 
	{
		return this.clone();
	}
	//END METHOD public Gameboard getState()
	
	@Override
	//BEGIN METHOD public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace) 
	public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace) 
	{
		if((xPosition > 2)||(yPosition > 2)||(xPosition < 0)||(yPosition < 0))
		{
			return false;
		}
		if((currentBoard[xPosition][yPosition] == PlaceValue.BLANK)&&
				((pieceToPlace == PlaceValue.X && this.xsTurn())||
				 (pieceToPlace == PlaceValue.O && this.osTurn()))) 
		{
			for(Integer rowNum:coordToRows(xPosition, yPosition))
			{
				if(pieceToPlace == PlaceValue.X)
				{
					rowStates[rowNum]++;
				}
				else
				{
					rowStates[rowNum]--;
				}
			}
			this.forceMove(xPosition, yPosition, pieceToPlace);
			if(this.checkResult() == GameResult.PENDING)
			{
				xsTurn = !xsTurn;
				osTurn = !osTurn;
			}
			else 
			{
				xsTurn = false;
				osTurn = false;
			}
			return true;
		}
		return false;
	}
	//END METHOD public boolean requestMove(int xPosition, int yPosition, PlaceValue pieceToPlace) 
	
	@Override
	//BEGIN METHOD public boolean xsTurn()
	public boolean xsTurn() 
	{
		return xsTurn;
	}
	//END METHOD public boolean xsTurn()
	
	@Override
	//BEGIN METHOD public boolean osTurn() 
	public boolean osTurn() 
	{
		return osTurn;
	}
	//END METHOD public boolean osTurn()
	
	@Override
	//BEGIN METHOD public boolean resign(PlaceValue pieceResigning)
	public boolean resign(PlaceValue pieceResigning) {
		if (pieceResigning == PlaceValue.O) {
			this.result = GameResult.XWIN;
			return true;
		}
		else if (pieceResigning == PlaceValue.X) {
			this.result = GameResult.OWIN;
			return true;
		}
		else {
			return false;
		}
	}
	//BEGIN METHOD public boolean resign(PlaceValue pieceResigning)
	
	/**
	 * Returns a List of the rows the requested space is part of.
	 * Rows numbers are as follows, corresponding to the same rows in the rowStates array;
	 * 6   3    4    5   7    
	 * 0 [0,0][0,1][0,2]
	 * 1 [1,0][1,1][1,2]
	 * 2 [2,0][2,1][2,2]
	 * @param x X coordinate of the space
	 * @param y Y coordinate of the space
	 * @return The requested List
	 */
	//BEGIN METHOD private LinkedList<Integer> coordToRows(int x, int y)
	private LinkedList<Integer> coordToRows(int x, int y)
	{
		int switchTrick = (x*10)+y;
		LinkedList<Integer> returnRows = new LinkedList<Integer>();
		switch(switchTrick)
		{
			case 0:  //0,0
				returnRows.add(0);
				returnRows.add(3);
				returnRows.add(6);
				break;
			case 1: //0,1
				returnRows.add(0);
				returnRows.add(4);
				break;
			case 2: //0,2
				returnRows.add(0);
				returnRows.add(5);
				returnRows.add(7);
				break;
			case 10: //1,0
				returnRows.add(1);
				returnRows.add(3);
				break;
			case 11: //1,1
				returnRows.add(1);
				returnRows.add(4);
				returnRows.add(6);
				returnRows.add(7);
				break;
			case 12: //1,2
				returnRows.add(1);
				returnRows.add(5);
				break;
			case 20: //2,0
				returnRows.add(2);
				returnRows.add(3);
				returnRows.add(7);
				break;
			case 21: //2,1
				returnRows.add(2);
				returnRows.add(4);
				break;
			case 22: //2,2
				returnRows.add(2);
				returnRows.add(5);
				returnRows.add(6);
				break;
		}
		return returnRows;
	}
	//END METHOD private LinkedList<Integer> coordToRows(int x, int y)
	
	/**
	 * Checks the status of the game and updates it, if necessary.
	 * @return the current status of the game
	 */
	//BEGIN METHOD private GameResult checkResult()
	private GameResult checkResult()
	{
		for(Integer sum:rowStates)
		{
			if(sum.equals(3))
			{
				this.result = GameResult.XWIN;
				return this.result;
			}
			else if(sum.equals(-3))
			{
				this.result = GameResult.OWIN;
				return this.result;
			}
		}
		for(PlaceValue[] c:currentBoard)
		{
			for(PlaceValue p:c)
			{
				if(p == PlaceValue.BLANK)
				{
					return this.result;
				}
			}
		}
		this.result = GameResult.CAT;
		return this.result;
	}
	//END METHOD private GameResult checkResult()
	
	/**
	 * Private version of RequestMove.
	 * Actually places the piece on the board.
	 * @param xPosition The x (horizontal) coordinate of the requested space to occupy
	 * @param yPosition The y (vertical) coordinate of the requested space to occupy
	 * @param piece The type of piece to put in the location
	 */
	//BEGIN METHOD private void forceMove(int xPosition, int yPosition, PlaceValue piece)
	private void forceMove(int xPosition, int yPosition, PlaceValue piece)
	{
		currentBoard[xPosition][yPosition] = piece;
	}
	//END METHOD private void forceMove(int xPosition, int yPosition, PlaceValue piece)
	
}
//END CLASS GameBoardImp
//END FILE GameBoardImp.java