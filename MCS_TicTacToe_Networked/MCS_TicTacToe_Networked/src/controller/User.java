package controller;
import java.util.StringTokenizer;

import model.Statistics;

public class User {
	public User(String uname, int charCode){
		this.userName = uname;
		this.characterCode = charCode;
	}
	public String getUserName(){
		return userName;
	}
	public int getCharacterCode(){
		return characterCode;
	}
	public Statistics getUserRecord(){
		return userStats.getState();
	}
	public String toString(){
		return this.userName+"."+this.characterCode;
	}
	public static User parseUser(String userString)
	{
		StringTokenizer tkizer = new StringTokenizer(userString, ".");
		String uname = tkizer.nextToken();
		String codeString = tkizer.nextToken();
		return new User(uname, Integer.parseInt(codeString));
	}
	public boolean equals(Object obj){
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
	protected Statistics getUserStatistics(){
		return userStats;
	}
	private String userName;
	private int characterCode;
	private Statistics userStats;
}
