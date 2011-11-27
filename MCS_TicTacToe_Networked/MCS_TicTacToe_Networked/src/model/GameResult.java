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
	
	public String getRepr(){
		return this.repr;
	}
	
	private GameResult(String repr)
	{
		this.repr = repr;
	}
	
	private String repr;
}
//END ENUM GameResult
//END FILE GameResult.java