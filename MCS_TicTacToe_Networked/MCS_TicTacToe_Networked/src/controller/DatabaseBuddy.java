//BEGIN FILE DatabaseBuddy.java
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//BEGIN CLASS DatabaseBuddy
public class DatabaseBuddy {
	
	private Connection conn;
	private Random rng;
	
	//BEGIN CONSTRUCTOR DatabaseBuddy(String databaseURI)
	public DatabaseBuddy(String databaseURI)
	{
		try{
			conn = DriverManager.getConnection(databaseURI);
			//conn.createStatement().execute("CREATE TABLE users(userName VARCHAR(25) NOT NULL,characterCode INT NOT NULL,lastKeptAlive TIMESTAMP)");
			//conn.createStatement().execute("CREATE TABLE requests(requesteeName VARCHAR(25) NOT NULL,requesteeCode INT NOT NULL,requesterName VARCHAR(25) NOT NULL,requesterCode INT NOT NULL)");
			//conn.createStatement().execute("CREATE TABLE activeGames(gameNum INT NOT NULL,XName VARCHAR(25) NOT NULL,XCode INT NOT NULL,OName VARCHAR(25) NOT NULL,OCode INT NOT NULL)");
		}catch(Exception e){
			e.printStackTrace();
		}
		rng = new Random();
	}
	//END CONSTRUCTOR DatabaseBuddy(String databaseURI)
	
	public void deleteAll()
	{
		this.deleteAllUsers();
		this.deleteAllRequests();
	}
	
	public void deleteAllUsers()
	{
		try{
			conn.createStatement().execute("DELETE FROM users");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteAllRequests()
	{
		try{
			conn.createStatement().execute("DELETE FROM requests");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//BEGIN METHOD boolean addUser(User u)
	public boolean addUser(User u)
	{
		if(!this.containsUser(u))
		{
			String query = "INSERT INTO users(userName, characterCode) VALUES('"+u.getUserName()+"', "+u.getCharacterCode()+")";
			try{
				Statement stmt = conn.createStatement();
				stmt.execute(query);
			}catch(SQLException sqle)
			{
				sqle.printStackTrace();
			}
			return true;
		}
		return false;
	}
	//END METHOD addUser(User U)
	
	//BEGIN METHOD boolean keepAlive(User u)
	public boolean keepAlive(User u)
	{
		try
		{
			conn.createStatement().execute("UPDATE users SET lastKeptAlive=CURRENT_TIMESTAMP WHERE userName='"+u.getUserName()+"' AND characterCode="+u.getCharacterCode());
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	//BEGIN METHOD keepAlive(User u)
	
	//BEGIN METHOD List<User> getLiveUsers(long timeToKeepAlive)
	public List<User> getLiveUsers(long timeToKeepAlive)
	{
		Timestamp ts;
		List<User> returnList = new ArrayList<User>();
		try
		{
			ResultSet rs = conn.createStatement().executeQuery("VALUES CURRENT_TIMESTAMP");
			rs.next();
			ts = new Timestamp(rs.getTimestamp(1).getTime()-timeToKeepAlive);
			String tsString = ts.toString();
			rs = conn.createStatement().executeQuery("SELECT userName,characterCode FROM users WHERE lastKeptAlive>={ts '"+tsString+"'}");
			while(rs.next())
			{
				returnList.add(new User(rs.getString("userName"), rs.getInt("characterCode")));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
	}
	//END METHOD List<User> getLiveUsers(long timeToKeepAlive)
	
	/**
	 * Requests a game from requester to requestee. If there is a pending reciprocal request, a game is created and the game number is returned.
	 * If the request is successful, 0 is returned.
	 * If every thing goes pear-shaped, you get a -1.
	 * @param requestee
	 * @param requester
	 * @return
	 */
	//TODO needs guards
	//BEGIN int requestGame(User requester, User requestee)
	public int requestGame(User requester, User requestee)
	{
		String queryRecipRequest = "SELECT requesteeName, requesteeCode, requesterName, requesterCode FROM requests WHERE"
				+" requesteeName='"+requester.getUserName()+"' AND requesteeCode="+requester.getCharacterCode()+" AND"
				+" requesterName='"+requestee.getUserName()+"' AND requesterCode="+requestee.getCharacterCode();
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryRecipRequest);
			if(rs.next())
			{
				int i;
				do{
					i= rng.nextInt(8191)+1;
				}while(conn.createStatement().executeQuery("SELECT gameNum FROM activeGames WHERE gameNum="+i).next());
				if(rng.nextBoolean())
				{
					String newGameQuery = "INSERT INTO activeGames(XName, XCode, OName, OCode, gameNum) VALUES('"
							+requestee.getUserName()+"', "+requestee.getCharacterCode()+", '"
							+requester.getUserName()+"', "+requester.getCharacterCode()+", "+i+")";
					conn.createStatement().execute(newGameQuery);
					return i;
				}
			}
			else
			{
				String query = "INSERT INTO requests(requesteeName, requesteeCode, requesterName, requesterCode) VALUES('"
						+requestee.getUserName()+"', "+requestee.getCharacterCode()+", '"
						+requester.getUserName()+"', "+requester.getCharacterCode()+")";
				conn.createStatement().execute(query);
				return 0;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	//END METHOD int requestGame(User requester, User requestee)

	//BEGIN METHOD boolean containsUser(User NewUser)
	public boolean containsUser(User newUser) {
		ResultSet rs;
		String query = "SELECT userName,characterCode FROM Users WHERE userName='"+newUser.getUserName()+"' AND characterCode="+newUser.getCharacterCode();
		try {
			rs = conn.createStatement().executeQuery(query);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//END METHOD boolean containsUser(User NewUser)
}
//END CLASS DatabaseBuddy
//END FILE DatabaseBuddy.java
