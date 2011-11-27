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
	X ('X'),
	O ('O'),
	BLANK ('_');
	
	PlaceValue(char repr){
		this.repr = repr;
	}
	public char getRepr(){
		return this.repr;
	}
	private final char repr;
}
//END ENUM PlaceValue
//END FILE PlaceValue.java