//BEGIN FILE PlaceValue.java
package model;
//imports would go here if we had any

/**
* The enumerated cases for a space on a Gameboard.
* @author Benjamin Pellittieri and Christopher Pauliks for Mustache Cash Stash
* @version 0.0 pending CCR approval
*/
//BEGIN ENUM PlaceValue
public enum PlaceValue 
{
	X ("X"),
	O ("O"),
	BLANK ("_");
	
	PlaceValue(String repr){
		this.repr = repr;
	}
	public String getRepr(){
		return this.repr;
	}
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
	private final String repr;
}
//END ENUM PlaceValue
//END FILE PlaceValue.java