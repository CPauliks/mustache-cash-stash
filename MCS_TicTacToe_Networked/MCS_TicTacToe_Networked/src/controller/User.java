//BEGIN FILE User.java
package controller;
import java.util.StringTokenizer;

import model.Statistics;

/**
 * A representation of a User for Networked games
 * Duplicate user names are handled by giving each User a character code.
 * @author Mustache Cash Stash
 * @version 1.0
 */
//BEGIN CLASS User
public class User implements Comparable<User>
{
	private String userName;
	private int characterCode;
	private Statistics userStats;
	
	/**
	 * Default constructor for Users
	 * @param uname The String name of the User
	 * @param charCode The character code for this User, to allow duplicate usernames
	 */
	//BEGIN CONSTRUCTOR public User(String uname, int charCode)
	public User(String uname, int charCode)
	{
		this.userName = uname;
		this.characterCode = charCode;
	}
	//END CONSTRUCTOR public User(String uname, int charCode)

	/**
	 * Returns the username part of this User
	 * @return The String username
	 */
	//BEGIN METHOD public String getUserName()
	public String getUserName()
	{
		return userName;
	}
	//END METHOD public String getUserName()

	/**
	 * Returns the character code part of this User
	 * @return The integer character code
	 */
	//BEGIN METHOD public int getCharacterCode()
	public int getCharacterCode()
	{
		return characterCode;
	}
	//END METHOD public int getCharacterCode()

	/**
	 * Returns the User's Statistics
	 * @return the User's Statistics
	 */
	//BEGIN METHOD public Statistics getUserRecord()
	public Statistics getUserRecord()
	{
		return userStats.getState();
	}
	//END METHOD public Statistics getUserRecord()

	@Override
	//BEGIN METHOD public String toString()
	public String toString()
	{
		return this.userName+"."+this.characterCode;
	}
	//END METHOD public String toString()

	/**
	 * Utility method for creating a user from a String representing the User
	 * in the form of Username.CharacterCode
	 * @param userString the string to parse as a user.
	 * @return This userString as a User
	 */
	//BEGIN METHOD public static User parseUser(String userString)
	public static User parseUser(String userString)
	{
		StringTokenizer tkizer = new StringTokenizer(userString, ".");
		String uname = tkizer.nextToken();
		String codeString = tkizer.nextToken();
		return new User(uname, Integer.parseInt(codeString));
	}
	//END METHOD public static User parseUser(String userString)

	@Override
	//BEGIN METHOD public boolean equals(Object obj)
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		else if(!(obj instanceof User))
		{
			return false;
		}
		else
		{
			return ((User) obj).getUserName().equals(this.userName) && (((User) obj).getCharacterCode() == this.characterCode);
		}
	}
	//END METHOD public boolean equals(Object obj)

	@Override
	//BEGIN METHOD public int compareTo(User comp)
	public int compareTo(User comp)
	{
		int compValue = this.getUserName().compareTo(comp.getUserName());
		if(compValue == 0)
		{
			compValue = this.getCharacterCode() - comp.getCharacterCode();
		}
		return compValue;
	}
	//END METHOD public int compareTo(User comp)
	
	/**
	 * Returns the User's Statistics
	 * @return the User's Statistics
	 */
	//BEGIN METHOD protected Statistics getUserStatistics()
	protected Statistics getUserStatistics()
	{
		return userStats;
	}
	//END METHOD protected Statistics getUserStatistics()
}
//END CLASS User
//END FILE User.java