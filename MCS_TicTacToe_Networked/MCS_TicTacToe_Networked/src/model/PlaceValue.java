//BEGIN FILE PlaceValue.java
package model;

/**
 * The enumerated cases for a space on a Gameboard.
 * @author Benjamin Pellittieri and Christopher Pauliks for Mustache Cash Stash
 * @version 1.0
 */
//BEGIN ENUM PlaceValue
public enum PlaceValue 
{
	X ("X"),
	O ("O"),
	BLANK ("_");

	private final String repr;

	/**
	 * Constructs a PlaceValue from a String representation
	 * @param repr String representation of a PlaceValue
	 */
	//BEGIN METHOD PlaceValue(String repr) 
	PlaceValue(String repr) 
	{
		this.repr = repr;
	}
	//END METHOD PlaceValue(String repr) 

	/**
	 * Returns a String represention of this PlaceValue
	 * @return a String representation of this PlaceValue
	 */
	//BEGIN METHOD public String getRepr() 
	public String getRepr() 
	{
		return this.repr;
	}
	//END METHOD public String getRepr() 

	/**
	 * Constructs a PlaceValue from a String representation
	 * @param repr String representation of a PlaceValue
	 */
	//BEGIN METHOD public static PlaceValue parsePlaceValue(String repr)
	public static PlaceValue parsePlaceValue(String repr)
	{
		if(repr == "X")
		{
			return PlaceValue.X;
		}
		else if(repr == "O")
		{
			return PlaceValue.O;
		}
		else
		{
			return PlaceValue.BLANK;
		}
	}
	//END METHOD public static PlaceValue parsePlaceValue(String repr)

}
//END ENUM PlaceValue
//END FILE PlaceValue.java