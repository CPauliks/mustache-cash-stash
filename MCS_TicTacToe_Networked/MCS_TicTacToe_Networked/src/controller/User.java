package controller;
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
	protected Statistics getUserStatistics(){
		return userStats;
	}
	private String userName;
	private int characterCode;
	private Statistics userStats;
}
