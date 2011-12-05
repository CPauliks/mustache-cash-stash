//BEGIN FILE GameResult.java
package model;

/**
 * The enumerated cases for the current status of a Game
 * @author Benjamin Pellittieri for Mustache Cash Stash
 * @version 0.0 pending CCR approval
 */
//BEGIN ENUM GameResult
public enum GameResult
{
	XWIN ("XWIN"),
	OWIN ("OWIN"),
	CAT ("CAT"),
	PENDING ("PENDING");

	private String repr;

	/**
	 * Returns a String representation of this GameResult
	 * @return String representation of this GameResult
	 */
	//BEGIN METHOD public String getRepr()
	public String getRepr() 
	{
		return this.repr;
	}
	//END METHOD public String getRepr()

	/**
	 * Constructs a GameResult from a String representation
	 * @param repr String representation of a GameResult
	 */
	//BEGIN METHOD private GameResult(String repr)
	private GameResult(String repr)
	{
		this.repr = repr;
	}
	//END METHOD private GameResult(String repr)
}
//END ENUM GameResult
//END FILE GameResult.java